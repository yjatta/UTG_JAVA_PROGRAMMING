package utg.mcc.semantic;

import utg.mcc.parser.ast.ASTAdd;
import utg.mcc.parser.ast.ASTAnd;
import utg.mcc.parser.ast.ASTAssignStatement;
import utg.mcc.parser.ast.ASTBlockStatement;
import utg.mcc.parser.ast.ASTCall;
import utg.mcc.parser.ast.ASTDiv;
import utg.mcc.parser.ast.ASTEq;
import utg.mcc.parser.ast.ASTExpression;
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
import utg.mcc.semantic.types.BoolType;
import utg.mcc.semantic.types.FunType;
import utg.mcc.semantic.types.IntType;
import utg.mcc.semantic.types.RecordType;
import utg.mcc.semantic.types.Type;
import utg.utils.Message;

public class TypeChecker extends ASTVisitor {

  private final Message m = new Message("type checking");

  @Override
  public Object visitAdd(ASTAdd add, Object o) {
    m.verbose("> add");
    m.i();
    Type t1 = (Type) add.op1.accept(this, null);
    Type t2 = (Type) add.op2.accept(this, null);
    if (!Type.checkType(IntType.intType(), t1, t2)) {
      throw new RuntimeException("incompatible types near "
          + add.position);
    }
    m.d();
    m.verbose("< add");
    return t1;
  }

  @Override
  public Object visitAnd(ASTAnd and, Object o) {
    m.verbose("> and");
    m.i();
    Type t1 = (Type) and.op1.accept(this, null);
    Type t2 = (Type) and.op2.accept(this, null);
    if (!Type.checkType(BoolType.boolType(), t1, t2)) {
      throw new RuntimeException("incompatible types near "
          + and.position);
    }
    m.d();
    m.verbose("< and");
    return t1;
  }

  @Override
  public Object visitAssignStatement(
      ASTAssignStatement assignStatement, Object o) {
    m.verbose("> assign");
    m.i();
    Type t1 = (Type) assignStatement.variable.accept(this, null);
    Type t2 = (Type) assignStatement.expression.accept(this, null);
    if (!Type.checkType(IntType.intType(), t1, t2)) {
      throw new RuntimeException("incompatible types near "
          + assignStatement.position);
    }
    m.d();
    m.verbose("< assign");
    return null;
  }

  @Override
  public Object visitBlock(ASTBlockStatement block, Object o) {
    m.verbose("> block");
    m.i();
    for (int i = 0; i < block.varDeclarations.size(); i++) {
      block.varDeclarations.elementAt(i).accept(this, null);
    }
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
    FunType type = call.entry.type;
    RecordType blockType = type.blockType;
    Type fType = null;
    if (blockType != null) {
      fType = blockType.fieldType;
    }
    for (int i = 0; i < call.parameters.size(); i++) {
      ASTExpression expression = call.parameters.elementAt(i);
      Type t = (Type) expression.accept(this, null);
      if (!Type.checkType(fType, t)) {
        throw new RuntimeException("incompatible types near "
            + call.position);
      }
      blockType = blockType.tail;
      if (blockType != null) {
        fType = blockType.fieldType;
      }
    }
    m.d();
    m.verbose("< call");
    return type.returnType;
  }

  @Override
  public Object visitDiv(ASTDiv div, Object o) {
    m.verbose("> div");
    m.i();
    Type t1 = (Type) div.op1.accept(this, null);
    Type t2 = (Type) div.op2.accept(this, null);
    if (!Type.checkType(IntType.intType(), t1, t2)) {
      throw new RuntimeException("incompatible types near "
          + div.position);
    }
    m.d();
    m.verbose("< div");
    return t1;
  }

  @Override
  public Object visitEq(ASTEq eq, Object o) {
    m.verbose("> eq");
    m.i();
    Type t1 = (Type) eq.op1.accept(this, null);
    Type t2 = (Type) eq.op2.accept(this, null);
    if (!Type.checkType(IntType.intType(), t1, t2)) {
      throw new RuntimeException("incompatible types near "
          + eq.position);
    }
    m.d();
    m.verbose("< eq");
    return BoolType.boolType();
  }

  @Override
  public Object visitFunDeclaration(ASTFunDeclaration funDeclaration,
      Object o) {
    m.verbose("> function declaration");
    m.i();
    FunEntry entry = funDeclaration.entry;
    Type returnType = Type.makeType(funDeclaration.type);
    RecordType blockType = null;
    for (int i = 0; i < funDeclaration.parameters.size(); i++) {
      ASTParDeclaration declaration = funDeclaration.parameters
          .elementAt(i);
      Type t = Type.makeType(declaration.type);
      if (blockType == null) {
        blockType = new RecordType(declaration.name, t, null);
      } else {
        blockType.tail = new RecordType(declaration.name, t, null);
      }
    }
    // enter type information into function entry
    FunType type = new FunType(returnType, blockType);
    entry.type = type;
    for (int i = 0; i < funDeclaration.parameters.size(); i++) {
      ASTParDeclaration declaration = funDeclaration.parameters
          .elementAt(i);
      declaration.accept(this, null);
    }
    funDeclaration.block.accept(this, null);
    m.d();
    m.verbose("< function declaration [" + funDeclaration.entry + "]");

    return null;
  }

  @Override
  public Object visitGe(ASTGe ge, Object o) {
    m.verbose("> ge");
    m.i();
    Type t1 = (Type) ge.op1.accept(this, null);
    Type t2 = (Type) ge.op2.accept(this, null);
    if (!Type.checkType(IntType.intType(), t1, t2)) {
      throw new RuntimeException("incompatible types near "
          + ge.position);
    }
    m.d();
    m.verbose("< ge");
    return BoolType.boolType();
  }

  @Override
  public Object visitGt(ASTGt gt, Object o) {
    m.verbose("> gt");
    m.i();
    Type t1 = (Type) gt.op1.accept(this, null);
    Type t2 = (Type) gt.op2.accept(this, null);
    if (!Type.checkType(IntType.intType(), t1, t2)) {
      throw new RuntimeException("incompatible types near "
          + gt.position);
    }
    m.d();
    m.verbose("< gt");
    return BoolType.boolType();
  }

  @Override
  public Object visitIfStatement(ASTIfStatement ifStatement, Object o) {
    m.verbose("> if");
    m.i();
    Type t = (Type) ifStatement.expression.accept(this, null);
    if (!Type.checkType(BoolType.boolType(), t)) {
      throw new RuntimeException("incompatible types near "
          + ifStatement.expression.position);
    }
    ifStatement.thenStatement.accept(this, null);
    if (ifStatement.elseStatement != null) {
      ifStatement.elseStatement.accept(this, null);
    }
    m.d();
    m.verbose("< if");
    return null;
  }

  @Override
  public Object visitIntegerConstant(
      ASTIntegerConstant integerConstant, Object o) {
    m.verbose("> integer constant");
    m.verbose("< integer constant");
    return IntType.intType();
  }

  @Override
  public Object visitLe(ASTLe le, Object o) {
    m.verbose("> le");
    m.i();
    Type t1 = (Type) le.op1.accept(this, null);
    Type t2 = (Type) le.op2.accept(this, null);
    if (!Type.checkType(IntType.intType(), t1, t2)) {
      throw new RuntimeException("incompatible types near "
          + le.position);
    }
    m.d();
    m.verbose("< le");
    return BoolType.boolType();
  }

  @Override
  public Object visitLt(ASTLt lt, Object o) {
    m.verbose("> lt");
    m.i();
    Type t1 = (Type) lt.op1.accept(this, null);
    Type t2 = (Type) lt.op2.accept(this, null);
    if (!Type.checkType(IntType.intType(), t1, t2)) {
      throw new RuntimeException("incompatible types near "
          + lt.position);
    }
    m.d();
    m.verbose("< lt");
    return BoolType.boolType();
  }

  @Override
  public Object visitMul(ASTMul mul, Object o) {
    m.verbose("> mul");
    m.i();
    Type t1 = (Type) mul.op1.accept(this, null);
    Type t2 = (Type) mul.op2.accept(this, null);
    if (!Type.checkType(IntType.intType(), t1, t2)) {
      throw new RuntimeException("incompatible types near "
          + mul.position);
    }
    m.d();
    m.verbose("< mul");
    return t1;
  }

  @Override
  public Object visitNe(ASTNe ne, Object o) {
    m.verbose("> ne");
    m.i();
    Type t1 = (Type) ne.op1.accept(this, null);
    Type t2 = (Type) ne.op2.accept(this, null);
    if (!Type.checkType(IntType.intType(), t1, t2)) {
      throw new RuntimeException("incompatible types near "
          + ne.position);
    }
    m.d();
    m.verbose("< ne");
    return BoolType.boolType();
  }

  @Override
  public Object visitNeg(ASTNeg neg, Object o) {
    m.verbose("> neg");
    m.i();
    Type t = (Type) neg.op.accept(this, null);
    m.d();
    m.verbose("< neg");
    return t;
  }

  @Override
  public Object visitNot(ASTNot not, Object o) {
    m.verbose("> not");
    m.i();
    Type t = (Type) not.op.accept(this, null);
    if (!Type.checkType(BoolType.boolType(), t)) {
      throw new RuntimeException("incompatible types near "
          + not.position);
    }
    m.d();
    m.verbose("< not");
    return t;
  }

  @Override
  public Object visitOr(ASTOr or, Object o) {
    m.verbose("> or");
    m.i();
    Type t1 = (Type) or.op1.accept(this, null);
    Type t2 = (Type) or.op2.accept(this, null);
    if (!Type.checkType(BoolType.boolType(), t1, t2)) {
      throw new RuntimeException("incompatible types near "
          + or.position);
    }
    m.d();
    m.verbose("< or");
    return BoolType.boolType();
  }

  @Override
  public Object visitParDeclaration(ASTParDeclaration parDeclaration,
      Object o) {
    m.verbose("> par declaration");
    m.i();
    Type type = Type.makeType(parDeclaration.type);
    parDeclaration.entry.type = type;
    m.d();
    m.verbose("< par declaration [" + parDeclaration.entry + "]");
    return type;
  }

  @Override
  public Object visitPrintStatement(ASTPrintStatement printStatement,
      Object o) {
    m.verbose("> print");
    m.i();
    printStatement.expression.accept(this, null);
    m.d();
    m.verbose("< print");
    return null;
  }

  @Override
  public Object visitProgram(ASTProgram program, Object o) {
    m.verbose("> program");
    m.i();
    for (int i = 0; i < program.varDeclarations.size(); i++) {
      program.varDeclarations.elementAt(i).accept(this, null);
    }
    for (int i = 0; i < program.funDeclarations.size(); i++) {
      program.funDeclarations.elementAt(i).accept(this, null);
    }
    for (int i = 0; i < program.statements.size(); i++) {
      program.statements.elementAt(i).accept(this, null);
    }
    m.d();
    m.verbose("< program");
    return null;
  }

  @Override
  public Object visitReturnStatement(
      ASTReturnStatement returnStatement, Object o) {
    m.verbose("> return");
    m.i();
    Type t = (Type) returnStatement.expression.accept(this, null);
    Type type = returnStatement.entry.type.returnType;
    if (!Type.checkType(t, type)) {
      throw new RuntimeException("incompatible types near "
          + returnStatement.position);
    }
    m.d();
    m.verbose("< return " + type + "]");
    return t;
  }

  @Override
  public Object visitSub(ASTSub sub, Object o) {
    m.verbose("> sub");
    m.i();
    Type t1 = (Type) sub.op1.accept(this, null);
    Type t2 = (Type) sub.op2.accept(this, null);
    if (!Type.checkType(IntType.intType(), t1, t2)) {
      throw new RuntimeException("incompatible types near "
          + sub.position);
    }
    m.d();
    m.verbose("< sub");
    return t1;
  }

  @Override
  public Object visitVarDeclaration(ASTVarDeclaration varDeclaration,
      Object o) {
    m.verbose("> variable declaration");
    m.i();
    Type type = Type.makeType(varDeclaration.type);
    varDeclaration.entry.type = type;
    m.d();
    m.verbose("< variable declaration [" + varDeclaration.entry + "]");
    return type;
  }

  @Override
  public Object visitVariable(ASTVariable variable, Object o) {
    m.verbose("> variable");
    m.i();
    Type type = variable.entry.type;
    m.d();
    m.verbose("< variable [" + variable.entry + "]");
    return type;
  }

  @Override
  public Object visitWhileStatement(ASTWhileStatement whileStatement,
      Object o) {
    m.verbose("> while");
    m.i();
    Type t = (Type) whileStatement.expression.accept(this, null);
    if (!Type.checkType(BoolType.boolType(), t)) {
      throw new RuntimeException("incompatible types near "
          + whileStatement.expression.position);
    }
    whileStatement.statement.accept(this, null);
    m.verbose("< while");
    return null;
  }
}
