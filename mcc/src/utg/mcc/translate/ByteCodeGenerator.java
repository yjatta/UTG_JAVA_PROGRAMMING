package utg.mcc.translate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
import utg.utils.Message;

/**
 * this class is responsible for type checking, the allocation of virtual
 * addresses and the translation into intermediate code.
 * 
 * the class uses TIPair objects that wrap an intermediate code subtree together
 * with its type information
 */
public class ByteCodeGenerator extends ASTVisitor {

  protected final Message m = new Message("bytecode");

  protected FileWriter out;

  protected String source = null;

  protected int maxStack = 0;

  protected int actStack = 0;

  protected int labels = 0;

  protected ASTFunDeclaration actualFunction = null;

  protected String returnLabel;

  public ByteCodeGenerator(String source) {
    super();
    this.source = source;
    try {
      this.out = new FileWriter(new File("A.j"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * this method updates the java stack maximal height
   * 
   * @param i
   *          the actual stack increment (decrement)
   */
  protected void as(int i) {
    actStack += i;
    if (actStack > maxStack)
      maxStack = actStack;
  }

  /**
   * this method is used for writing the target code
   * 
   * @param s
   *          the target instruction string
   */
  protected void emit(String s) {
    try {
      out.write(s + "\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void em(String s) {
    emit(s);
  }

  protected void eml(String s) {
    emit(s + ":");
  }

  protected void emt(String s) {
    emit("\t" + s);
  }

  protected String label() {
    return "L" + labels++;
  }

  @Override
  public Object visitAdd(ASTAdd add, Object o) {
    m.verbose("> add");
    m.i();
    add.op1.accept(this, null);
    add.op2.accept(this, null);
    emt("iadd");
    as(-1);
    m.d();
    m.verbose("< add");
    return null;
  }

  @Override
  public Object visitAnd(ASTAnd and, Object o) {
    m.verbose("> and");
    m.i();
    String lend = label();
    String lfalse = label();
    and.op1.accept(this, null);
    emt("ifeq " + lfalse);
    as(-1);
    and.op2.accept(this, null);
    emt("ifeq " + lfalse);
    as(-1);
    emt("iconst_1");
    as(1);
    emt("goto " + lend);
    eml(lfalse);
    emt("iconst_0");
    as(1);
    eml(lend);
    m.d();
    m.verbose("< and");
    return null;
  }

  @Override
  public Object visitAssignStatement(
      ASTAssignStatement assignStatement, Object o) {
    m.verbose("> assignment statement");
    m.i();
    emt("\t\t; start assignment");
    assignStatement.expression.accept(this, null);
    ASTVariable variable = assignStatement.variable;
    if (variable.entry.global) {
      emt("putstatic utg/mcc/bytecode/A/" + variable.entry.name + " "
          + variable.entry.type.signature());
    } else {
      emt("istore " + variable.entry.address + "\t\t; store "
          + variable.entry.name);
    }
    as(-1);
    emt("\t\t; end assignment");
    m.d();
    m.verbose("< assignment statement");
    return false;
  }

  @Override
  public Object visitBlock(ASTBlockStatement block, Object o) {
    m.verbose("> block");
    m.i();
    for (int i = 0; i < block.statements.size(); i++) {
      block.statements.elementAt(i).accept(this, null);
    }
    m.d();
    m.verbose("< block");
    return null;
  }

  @Override
  public Object visitCall(ASTCall call, Object o) {
    m.verbose("> call [" + call.name + "]");
    m.i();
    for (int i = 0; i < call.parameters.size(); i++) {
      call.parameters.elementAt(i).accept(this, null);
    }
    emt("invokestatic utg/mcc/bytecode/A/" + call.entry.name
        + call.entry.type.signature());
    as(1 - call.entry.formals);
    m.d();
    m.verbose("< call");
    return null;
  }

  @Override
  public Object visitDiv(ASTDiv div, Object o) {
    m.verbose("> div");
    m.i();
    div.op1.accept(this, null);
    div.op2.accept(this, null);
    emt("idiv");
    as(-1);
    m.d();
    m.verbose("< div");
    return null;
  }

  @Override
  public Object visitEq(ASTEq eq, Object o) {
    m.verbose("> eq");
    m.i();
    String ltrue = label();
    String lend = label();
    eq.op1.accept(this, null);
    eq.op2.accept(this, null);
    emt("isub");
    as(-1);
    emt("ifeq " + ltrue);
    as(-1);
    emt("iconst_0");
    as(1);
    emt("goto " + lend);
    eml(ltrue);
    emt("iconst_1");
    as(1);
    eml(lend);
    m.d();
    m.verbose("< eq");
    return null;
  }

  @Override
  public Object visitFunDeclaration(ASTFunDeclaration funDeclaration,
      Object o) {
    m.verbose("> function declaration [" + funDeclaration.name + "]");
    m.i();
    actualFunction = funDeclaration;
    returnLabel = label();
    actStack = 0;
    maxStack = 0;
    em(".method public static " + funDeclaration.name
        + funDeclaration.entry.type.signature());
    funDeclaration.block.accept(this, null);
    eml(returnLabel);
    emt("ireturn");
    as(-1);
    em(".limit locals " + funDeclaration.entry.addresses);
    em(".limit stack " + maxStack);
    em(".end method");
    em("");
    actualFunction = null;
    m.d();
    m.verbose("< function declaration");
    return null;
  }

  @Override
  public Object visitGe(ASTGe ge, Object o) {
    m.verbose("> ge");
    m.i();
    String ltrue = label();
    String lend = label();
    ge.op1.accept(this, null);
    ge.op2.accept(this, null);
    emt("isub");
    as(-1);
    emt("ifge " + ltrue);
    as(-1);
    emt("iconst_0");
    as(1);
    emt("goto " + lend);
    eml(ltrue);
    emt("iconst_1");
    as(1);
    eml(lend);
    m.d();
    m.verbose("< ge");
    return null;
  }

  @Override
  public Object visitGt(ASTGt gt, Object o) {
    m.verbose("> gt");
    m.i();
    String ltrue = label();
    String lend = label();
    gt.op1.accept(this, null);
    gt.op2.accept(this, null);
    emt("isub");
    as(-1);
    emt("ifgt " + ltrue);
    as(-1);
    emt("iconst_0");
    as(1);
    emt("goto " + lend);
    eml(ltrue);
    emt("iconst_1");
    as(1);
    eml(lend);
    m.d();
    m.verbose("< gt");
    return null;
  }

  @Override
  public Object visitIfStatement(ASTIfStatement ifStatement, Object o) {
    m.verbose("> if statement");
    m.i();
    emt("\t\t; start if");
    emt("\t\t; if condition");
    String lelse = label();
    String lend = label();
    ifStatement.expression.accept(this, null);
    emt("\t\t; then");
    emt("ifeq " + lelse);
    as(-1);
    ifStatement.thenStatement.accept(this, null);
    emt("goto " + lend);
    eml(lelse);
    if (ifStatement.elseStatement != null) {
      emt("\t\t; else");
      ifStatement.elseStatement.accept(this, null);
    }
    eml(lend);
    emt("\t\t; end if");
    m.d();
    m.verbose("< if statement");
    return null;
  }

  @Override
  public Object visitIntegerConstant(
      ASTIntegerConstant integerConstant, Object o) {
    m.verbose("> integer constant [" + integerConstant.value + "]");
    m.i();
    emt("ldc " + integerConstant.value);
    as(1);
    m.d();
    m.verbose("< integer constant");
    return null;
  }

  @Override
  public Object visitLe(ASTLe le, Object o) {
    m.verbose("> le");
    m.i();
    String ltrue = label();
    String lend = label();
    le.op1.accept(this, null);
    le.op2.accept(this, null);
    emt("isub");
    as(-1);
    emt("ifle " + ltrue);
    as(-1);
    emt("iconst_0");
    as(1);
    emt("goto " + lend);
    eml(ltrue);
    emt("iconst_1");
    as(1);
    eml(lend);
    m.d();
    m.verbose("< le");
    return null;
  }

  @Override
  public Object visitLt(ASTLt lt, Object o) {
    m.verbose("> lt");
    m.i();
    String ltrue = label();
    String lend = label();
    lt.op1.accept(this, null);
    lt.op2.accept(this, null);
    emt("isub");
    as(-1);
    emt("iflt " + ltrue);
    as(-1);
    emt("iconst_0");
    as(1);
    emt("goto " + lend);
    eml(ltrue);
    emt("iconst_1");
    as(1);
    eml(lend);
    m.d();
    m.verbose("< lt");
    return null;
  }

  @Override
  public Object visitMul(ASTMul mul, Object o) {
    m.verbose("> mul");
    m.i();
    mul.op1.accept(this, null);
    mul.op2.accept(this, null);
    emt("imul");
    as(-1);
    m.d();
    m.verbose("< mul");
    return null;
  }

  @Override
  public Object visitNe(ASTNe ne, Object o) {
    m.verbose("> ne");
    m.i();
    String ltrue = label();
    String lend = label();
    ne.op1.accept(this, null);
    ne.op2.accept(this, null);
    emt("isub");
    as(-1);
    emt("ifne " + ltrue);
    as(-1);
    emt("iconst_0");
    as(1);
    emt("goto " + lend);
    eml(ltrue);
    emt("iconst_1");
    as(1);
    eml(lend);
    m.d();
    m.verbose("< ne");
    return null;
  }

  @Override
  public Object visitNeg(ASTNeg neg, Object o) {
    m.verbose("> neg");
    m.i();
    neg.op.accept(this, null);
    emt("ineg");
    m.d();
    m.verbose("< neg");
    return null;
  }

  @Override
  public Object visitNot(ASTNot not, Object o) {
    m.verbose("> not");
    m.i();
    String lend = label();
    String ltrue = label();
    not.op.accept(this, null);
    emt("ifeq " + ltrue);
    as(-1);
    emt("iconst_0");
    as(1);
    emt("goto " + lend);
    eml(ltrue);
    emt("iconst_1");
    as(1);
    eml(lend);
    m.d();
    m.verbose("< not");
    return null;
  }

  @Override
  public Object visitOr(ASTOr or, Object o) {
    m.verbose("> or");
    m.i();
    String lend = label();
    String ltrue = label();
    or.op1.accept(this, null);
    emt("ifne " + ltrue);
    as(-1);
    or.op2.accept(this, null);
    emt("ifne " + ltrue);
    as(-1);
    emt("iconst_0");
    as(1);
    emt("goto " + lend);
    eml(ltrue);
    emt("iconst_1");
    as(1);
    eml(lend);
    m.d();
    m.verbose("< or");
    return null;
  }

  @Override
  public Object visitParDeclaration(ASTParDeclaration parDeclaration,
      Object o) {
    return null;
  }

  @Override
  public Object visitPrintStatement(ASTPrintStatement printStatement,
      Object o) {
    m.verbose("> print statement");
    m.i();
    emt("\t\t; start print");
    emt("getstatic java/lang/System/out Ljava/io/PrintStream;");
    as(1);
    printStatement.expression.accept(this, null);
    emt("invokevirtual java/io/PrintStream/println(I)V");
    as(-1);
    emt("\t\t; end print");
    m.d();
    m.verbose("< print statement");
    return null;
  }

  @Override
  public Object visitProgram(ASTProgram program, Object o) {
    m.verbose("> program");
    m.i();
    // class header
    em(".bytecode 50.0");
    em(".source " + source);
    em(".class public utg/mcc/bytecode/A");
    em(".super java/lang/Object");
    em("");
    // global variables
    for (int i = 0; i < program.varDeclarations.size(); i++) {
      program.varDeclarations.elementAt(i).accept(this, null);
    }
    em("");
    // class constructor
    em(".method public <init>()V");
    emt(".limit locals 1");
    emt(".limit stack 1");
    emt("aload_0");
    emt("invokespecial java/lang/Object/<init>()V");
    emt("return");
    em(".end method");
    em("");
    // functions
    for (int i = 0; i < program.funDeclarations.size(); i++) {
      program.funDeclarations.elementAt(i).accept(this, null);
    }
    // executer header
    em(".method public static exec()V");
    actStack = 0;
    maxStack = 0;
    for (int i = 0; i < program.statements.size(); i++) {
      program.statements.elementAt(i).accept(this, null);
    }
    // executer footer
    emt("return");
    emt(".limit locals " + program.entry.locals);
    emt(".limit stack " + maxStack);
    em(".end method");
    try {
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    m.d();
    m.verbose("< program");
    return null;
  }

  @Override
  public Object visitReturnStatement(
      ASTReturnStatement returnStatement, Object o) {
    m.verbose("> return statement");
    m.i();
    emt("\t\t; start return");
    returnStatement.expression.accept(this, null);
    emt("goto " + returnLabel);
    emt("\t\t; end return");
    m.d();
    m.verbose("< return statement");
    return true;
  }

  @Override
  public Object visitSub(ASTSub sub, Object o) {
    m.verbose("> sub");
    m.i();
    sub.op1.accept(this, null);
    sub.op2.accept(this, null);
    emt("isub");
    as(-1);
    m.d();
    m.verbose("< sub");
    return null;
  }

  @Override
  public Object visitVarDeclaration(ASTVarDeclaration varDeclaration,
      Object o) {
    m.verbose("> variable declaration");
    m.i();
    if (varDeclaration.entry.global) {
      em(".field public static " + varDeclaration.entry.name + " "
          + varDeclaration.entry.type.signature());
    }
    m.d();
    m.verbose("< variable declaration");
    return null;
  }

  @Override
  public Object visitVariable(ASTVariable variable, Object o) {
    m.verbose("> variable [" + variable.name + "]");
    m.i();
    if (variable.entry.global) {
      emt("getstatic utg/mcc/bytecode/A/" + variable.entry.name + " "
          + variable.entry.type.signature());
    } else {
      emt("iload " + variable.entry.address + "\t\t; load "
          + variable.entry.name);
    }
    as(1);
    m.d();
    m.verbose("< variable");
    return null;
  }

  @Override
  public Object visitWhileStatement(ASTWhileStatement whileStatement,
      Object o) {
    m.verbose("> while statement");
    m.i();
    emt("\t\t; start while");
    emt("\t\t; while condition");
    String lstart = label();
    String lend = label();
    eml(lstart);
    whileStatement.expression.accept(this, null);
    emt("ifeq " + lend);
    as(-1);
    emt("\t\t; while statement");
    whileStatement.statement.accept(this, null);
    emt("goto " + lstart);
    eml(lend);
    emt("\t\t; end if");
    m.d();
    m.verbose("< while statement");
    return null;
  }
}
