package utg.mcc.semantic;

import utg.mcc.parser.ast.ASTAdd;
import utg.mcc.parser.ast.ASTAnd;
import utg.mcc.parser.ast.ASTAssignStatement;
import utg.mcc.parser.ast.ASTBlockStatement;
import utg.mcc.parser.ast.ASTCall;
import utg.mcc.parser.ast.ASTDiv;
import utg.mcc.parser.ast.ASTEq;
import utg.mcc.parser.ast.ASTFunDeclaration;
import utg.mcc.parser.ast.ASTGe;
import utg.mcc.parser.ast.ASTGt;
import utg.mcc.parser.ast.ASTIfStatement;
import utg.mcc.parser.ast.ASTIntegerConstant;
import utg.mcc.parser.ast.ASTLe;
import utg.mcc.parser.ast.ASTLt;
import utg.mcc.parser.ast.ASTMul;
import utg.mcc.parser.ast.ASTNe;
import utg.mcc.parser.ast.ASTNeg;
import utg.mcc.parser.ast.ASTNot;
import utg.mcc.parser.ast.ASTOr;
import utg.mcc.parser.ast.ASTParDeclaration;
import utg.mcc.parser.ast.ASTPrintStatement;
import utg.mcc.parser.ast.ASTProgram;
import utg.mcc.parser.ast.ASTReturnStatement;
import utg.mcc.parser.ast.ASTSub;
import utg.mcc.parser.ast.ASTVarDeclaration;
import utg.mcc.parser.ast.ASTVariable;
import utg.mcc.parser.ast.ASTVisitor;
import utg.mcc.parser.ast.ASTWhileStatement;
import utg.mcc.semantic.environment.FunEntry;
import utg.mcc.semantic.environment.GlobalEntry;
import utg.mcc.semantic.environment.VarEntry;
import utg.mcc.table.Table;
import utg.utils.Message;

public class ComputeAddresses extends ASTVisitor {

  private final Message m = new Message("addresses");

  /** contains the entries of all variables */
  private final Table<VarEntry> varTable = new Table<VarEntry>();

  /** contains the entries of all functions */
  private final Table<FunEntry> funTable = new Table<FunEntry>();

  FunEntry actualFunctionEntry = null;

  /**
   * during the first pass, the visitor will analyse all top variable
   * declarations and function declarations (without body) thus detecting
   * recursive symbols that could cause problems during the analysis. during the
   * second pass, all blocks will be analysed
   */
  private int pass = 1;

  /** the virtual address of the symbol */
  private int address = 0;

  @Override
  public Object visitAdd(ASTAdd add, Object o) {
    add.op1.accept(this, null);
    add.op2.accept(this, null);
    return null;
  }

  @Override
  public Object visitAnd(ASTAnd and, Object o) {
    and.op1.accept(this, null);
    and.op2.accept(this, null);
    return null;
  }

  @Override
  public Object visitAssignStatement(
      ASTAssignStatement assignStatement, Object o) {
    assignStatement.variable.accept(this, null);
    assignStatement.expression.accept(this, null);
    return null;
  }

  @Override
  public Object visitBlock(ASTBlockStatement block, Object o) {
    varTable.beginScope();
    for (int i = 0; i < block.varDeclarations.size(); i++) {
      block.varDeclarations.elementAt(i).accept(this, null);
    }
    for (int i = 0; i < block.statements.size(); i++) {
      block.statements.elementAt(i).accept(this, null);
    }
    varTable.endScope();
    return null;
  }

  @Override
  public Object visitCall(ASTCall call, Object o) {
    m.verbose("> call [" + call.name + "]");
    m.i();
    // look for corresponding entry in functions table
    FunEntry entry = funTable.resolve(call.name);
    if (entry == null) {
      throw new RuntimeException("function " + call.name + " at "
          + call.position + " has not been declared");
    }
    // save entry into call object
    call.entry = entry;
    for (int i = 0; i < call.parameters.size(); i++) {
      call.parameters.elementAt(i).accept(this, null);
    }
    m.d();
    m.verbose("< call");
    return null;
  }

  @Override
  public Object visitDiv(ASTDiv div, Object o) {
    div.op1.accept(this, null);
    div.op2.accept(this, null);
    return null;
  }

  @Override
  public Object visitEq(ASTEq eq, Object o) {
    eq.op1.accept(this, null);
    eq.op2.accept(this, null);
    return null;
  }

  @Override
  public Object visitFunDeclaration(ASTFunDeclaration funDeclaration,
      Object o) {
    if (pass == 1) {
      m.verbose("> declaration [pass 1]");
      m.i();
      // put entry into function and into functions table
      FunEntry entry = new FunEntry(funDeclaration.name);
      entry.block = funDeclaration.block;
      funDeclaration.entry = entry;
      funTable.define(funDeclaration.name, entry);
      m.d();
      m.verbose("< declaration");
    } else {
      m.verbose("> declaration [pass 2]");
      m.i();
      // save global address
      int oldAddress = address;
      address = 0;
      varTable.beginScope();
      for (int i = 0; i < funDeclaration.parameters.size(); i++) {
        ASTParDeclaration declaration = funDeclaration.parameters
            .elementAt(i);
        declaration.accept(this, null);
      }
      actualFunctionEntry = funDeclaration.entry;
      funDeclaration.block.accept(this, null);
      // save number of addresses into function entry
      funDeclaration.entry.addresses = address;
      funDeclaration.entry.formals = funDeclaration.parameters.size();
      varTable.endScope();
      // restore old global address
      address = oldAddress;
      m.d();
      m.verbose("< declaration [" + funDeclaration.entry + "]");
    }
    return null;
  }

  @Override
  public Object visitGe(ASTGe ge, Object o) {
    ge.op1.accept(this, null);
    ge.op2.accept(this, null);
    return null;
  }

  @Override
  public Object visitGt(ASTGt gt, Object o) {
    gt.op1.accept(this, null);
    gt.op2.accept(this, null);
    return null;
  }

  @Override
  public Object visitIfStatement(ASTIfStatement ifStatement, Object o) {
    ifStatement.expression.accept(this, null);
    ifStatement.thenStatement.accept(this, null);
    if (ifStatement.elseStatement != null) {
      ifStatement.elseStatement.accept(this, null);
    }
    return null;
  }

  @Override
  public Object visitIntegerConstant(
      ASTIntegerConstant integerConstant, Object o) {
    return null;
  }

  @Override
  public Object visitLe(ASTLe le, Object o) {
    le.op1.accept(this, null);
    le.op2.accept(this, null);
    return null;
  }

  @Override
  public Object visitLt(ASTLt lt, Object o) {
    lt.op1.accept(this, null);
    lt.op2.accept(this, null);
    return null;
  }

  @Override
  public Object visitMul(ASTMul mul, Object o) {
    mul.op1.accept(this, null);
    mul.op2.accept(this, null);
    return null;
  }

  @Override
  public Object visitNe(ASTNe ne, Object o) {
    ne.op1.accept(this, null);
    ne.op2.accept(this, null);
    return null;
  }

  @Override
  public Object visitNeg(ASTNeg neg, Object o) {
    neg.op.accept(this, null);
    return null;
  }

  @Override
  public Object visitNot(ASTNot not, Object o) {
    not.op.accept(this, null);
    return null;
  }

  @Override
  public Object visitOr(ASTOr or, Object o) {
    or.op1.accept(this, null);
    or.op2.accept(this, null);
    return null;
  }

  @Override
  public Object visitParDeclaration(ASTParDeclaration parDeclaration,
      Object o) {
    m.verbose("> declaration");
    m.i();
    VarEntry entry = new VarEntry(parDeclaration.name);
    entry.address = address++;
    parDeclaration.entry = entry;
    varTable.define(parDeclaration.name, entry);
    m.d();
    m.verbose("< declaration [" + entry + "]");
    return null;
  }

  @Override
  public Object visitPrintStatement(ASTPrintStatement printStatement,
      Object o) {
    printStatement.expression.accept(this, null);
    return null;
  }

  @Override
  public Object visitProgram(ASTProgram program, Object o) {
    m.verbose("> program");
    m.i();
    varTable.beginScope();
    funTable.beginScope();
    // put all global variables into variables table
    for (int i = 0; i < program.varDeclarations.size(); i++) {
      program.varDeclarations.elementAt(i).accept(this, null);
      program.varDeclarations.elementAt(i).entry.global = true;
    }
    // pass 1 enter function names into functions table
    for (int i = 0; i < program.funDeclarations.size(); i++) {
      program.funDeclarations.elementAt(i).accept(this, null);
    }
    // pass 2 put all function variable entries into the variable
    // table
    pass = 2;
    // process functions
    for (int i = 0; i < program.funDeclarations.size(); i++) {
      program.funDeclarations.elementAt(i).accept(this, null);
    }
    // process statements
    GlobalEntry entry = new GlobalEntry();
    entry.globals = program.varDeclarations.size();
    for (int i = 0; i < program.statements.size(); i++) {
      program.statements.elementAt(i).accept(this, null);
    }
    entry.locals = address - entry.globals;
    program.entry = entry;
    funTable.endScope();
    varTable.endScope();
    m.d();
    m.verbose("< program");
    return null;
  }

  @Override
  public Object visitReturnStatement(
      ASTReturnStatement returnStatement, Object o) {
    m.verbose("> return");
    m.i();
    returnStatement.entry = actualFunctionEntry;
    returnStatement.expression.accept(this, null);
    m.d();
    m.verbose("< return ");
    return null;
  }

  @Override
  public Object visitSub(ASTSub sub, Object o) {
    sub.op1.accept(this, null);
    sub.op2.accept(this, null);
    return null;
  }

  @Override
  public Object visitVarDeclaration(ASTVarDeclaration varDeclaration,
      Object o) {
    m.verbose("> declaration");
    m.i();
    VarEntry entry = new VarEntry(varDeclaration.name);
    entry.address = address++;
    varDeclaration.entry = entry;
    varTable.define(varDeclaration.name, entry);
    m.d();
    m.verbose("< declaration [" + entry + "]");
    return null;
  }

  @Override
  public Object visitVariable(ASTVariable variable, Object o) {
    m.verbose("> variable [" + variable.name + "]");
    m.i();
    // look for the entry in the variables table, save it into variable
    VarEntry entry = varTable.resolve(variable.name);
    if (entry == null) {
      throw new RuntimeException("variable " + variable.name + " at "
          + variable.position + " has not been declared");
    }
    variable.entry = entry;
    m.d();
    m.verbose("< variable");
    return null;
  }

  @Override
  public Object visitWhileStatement(ASTWhileStatement whileStatement,
      Object o) {
    whileStatement.expression.accept(this, null);
    whileStatement.statement.accept(this, null);
    return null;
  }
}
