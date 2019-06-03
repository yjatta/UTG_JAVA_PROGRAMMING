package utg.mcc.interpreter;

import utg.mcc.interpreter.memory.FunctionSpace;
import utg.mcc.interpreter.memory.MemorySpace;
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

public class Interpreter extends ASTVisitor {

  private final Message m = new Message("interpreter");

  public MemorySpace<Integer> memory = new MemorySpace<Integer>();

  private int returnAddress = -1;

  private boolean returned = false;

  @Override
  public Object visitAdd(ASTAdd add, Object o) {
    m.verbose("> add");
    m.i();
    int r = (Integer) add.op1.accept(this, o)
        + (Integer) add.op2.accept(this, o);
    m.d();
    m.verbose("< add [" + r + "]");
    return r;
  }

  @Override
  public Object visitAnd(ASTAnd and, Object o) {
    m.verbose("> and");
    m.i();
    int r = (Integer) and.op1.accept(this, o)
        * (Integer) and.op2.accept(this, o);
    m.d();
    m.verbose("< and [" + r + "]");
    return r;
  }

  @Override
  public Object visitAssignStatement(
      ASTAssignStatement assignStatement, Object o) {
    m.verbose("> assign");
    m.i();
    int r = (Integer) assignStatement.expression.accept(this, o);
    memory.put(assignStatement.variable.entry.address, r);
    m.d();
    m.verbose("< assign [" + r + "]");
    return r;
  }

  @Override
  public Object visitBlock(ASTBlockStatement block, Object o) {
    m.verbose("> block");
    m.i();
    int r = 0;
    for (int i = 0; i < block.statements.size(); i++) {
      if (!returned) {
        r = (Integer) block.statements.elementAt(i).accept(this, o);
      }
    }
    m.d();
    m.verbose("< block [" + r + "]");
    return r;
  }

  @Override
  public Object visitCall(ASTCall call, Object o) {
    m.verbose("> call [" + call.name + "]");
    m.i();
    int r;
    MemorySpace<Integer> prevMemory = memory;
    memory = new FunctionSpace<Integer>(memory);
    int prevReturnAddress = returnAddress;
    returnAddress = call.parameters.size();
    for (int i = 0; i < call.parameters.size(); i++) {
      Integer v = (Integer) call.parameters.elementAt(i)
          .accept(this, o);
      memory.put(i, v);
    }
    ASTBlockStatement block = call.entry.block;
    block.accept(this, o);
    r = memory.get(returnAddress);
    memory = prevMemory;
    returnAddress = prevReturnAddress;
    returned = false;
    m.d();
    m.verbose("< call [" + r + "]");
    return r;
  }

  @Override
  public Object visitDiv(ASTDiv div, Object o) {
    m.verbose("> div");
    m.i();
    int r = (Integer) div.op1.accept(this, o)
        / (Integer) div.op2.accept(this, o);
    m.d();
    m.verbose("< div [" + r + "]");
    return r;
  }

  @Override
  public Object visitEq(ASTEq eq, Object o) {
    m.verbose("> eq");
    m.i();
    int r = ((Integer) eq.op1.accept(this, o) == (Integer) eq.op2
        .accept(this, o) ? 1 : 0);
    m.d();
    m.verbose("< eq [" + r + "]");
    return r;
  }

  @Override
  public Object visitFunDeclaration(ASTFunDeclaration funDeclaration,
      Object o) {
    return null;
  }

  @Override
  public Object visitGe(ASTGe ge, Object o) {
    m.verbose("> ge");
    m.i();
    int r = ((Integer) ge.op1.accept(this, o) >= (Integer) ge.op2
        .accept(this, o) ? 1 : 0);
    m.d();
    m.verbose("< ge [" + r + "]");
    return r;
  }

  @Override
  public Object visitGt(ASTGt gt, Object o) {
    m.verbose("> gt");
    m.i();
    int r = ((Integer) gt.op1.accept(this, o) > (Integer) gt.op2
        .accept(this, o) ? 1 : 0);
    m.d();
    m.verbose("< gt [" + r + "]");
    return r;
  }

  @Override
  public Object visitIfStatement(ASTIfStatement ifStatement, Object o) {
    m.verbose("> if");
    m.i();
    int r = 0;
    if ((Integer) ifStatement.expression.accept(this, o) != 0) {
      m.verbose("> then");
      m.i();
      r = (Integer) ifStatement.thenStatement.accept(this, o);
      m.d();
      m.verbose("< then [" + r + "]");
    } else if (ifStatement.elseStatement != null) {
      m.verbose("> else");
      m.i();
      r = (Integer) ifStatement.elseStatement.accept(this, o);
      m.d();
      m.verbose("< else [" + r + "]");
    }
    m.d();
    m.verbose("< if [" + r + "]");
    return r;
  }

  @Override
  public Object visitIntegerConstant(
      ASTIntegerConstant integerConstant, Object o) {
    m.verbose("> int");
    m.verbose("< int [" + integerConstant.value + "]");
    return integerConstant.value;
  }

  @Override
  public Object visitLe(ASTLe le, Object o) {
    m.verbose("> le");
    m.i();
    int r = ((Integer) le.op1.accept(this, o) <= (Integer) le.op2
        .accept(this, o) ? 1 : 0);
    m.d();
    m.verbose("< le [" + r + "]");
    return r;
  }

  @Override
  public Object visitLt(ASTLt lt, Object o) {
    m.verbose("> lt");
    m.i();
    int r = ((Integer) lt.op1.accept(this, o) < (Integer) lt.op2
        .accept(this, o) ? 1 : 0);
    m.d();
    m.verbose("< lt [" + r + "]");
    return r;
  }

  @Override
  public Object visitMul(ASTMul mul, Object o) {
    m.verbose("> mul");
    m.i();
    int r = (Integer) mul.op1.accept(this, o)
        * (Integer) mul.op2.accept(this, o);
    m.d();
    m.verbose("< mul [" + r + "]");
    return r;
  }

  @Override
  public Object visitNe(ASTNe ne, Object o) {
    m.verbose("> ne");
    m.i();
    int r = ((Integer) ne.op1.accept(this, o) != (Integer) ne.op2
        .accept(this, o) ? 1 : 0);
    m.d();
    m.verbose("< ne [" + r + "]");
    return r;
  }

  @Override
  public Object visitNeg(ASTNeg neg, Object o) {
    m.verbose("> neg");
    m.i();
    int r = -(Integer) neg.op.accept(this, o);
    m.d();
    m.verbose("< neg [" + r + "]");
    return r;
  }

  @Override
  public Object visitNot(ASTNot not, Object o) {
    m.verbose("> not");
    m.i();
    int r = 1 - (Integer) not.op.accept(this, o);
    m.d();
    m.verbose("< not [" + r + "]");
    return r;
  }

  @Override
  public Object visitOr(ASTOr or, Object o) {
    m.verbose("> or");
    m.i();
    int r = (Integer) or.op1.accept(this, o)
        + (Integer) or.op2.accept(this, o);
    if (r == 2) {
      r = 1;
    }
    m.d();
    m.verbose("< or [" + r + "]");
    return r;
  }

  @Override
  public Object visitParDeclaration(ASTParDeclaration parDeclaration,
      Object o) {
    return null;
  }

  @Override
  public Object visitPrintStatement(ASTPrintStatement printStatement,
      Object o) {
    m.verbose("> print");
    m.i();
    int r = (Integer) printStatement.expression.accept(this, o);
    m.message("> " + r);
    m.d();
    m.verbose("< print [" + r + "]");
    return r;
  }

  @Override
  public Object visitProgram(ASTProgram program, Object o) {
    for (int i = 0; i < program.statements.size(); i++) {
      program.statements.elementAt(i).accept(this, o);
    }
    return null;
  }

  @Override
  public Object visitReturnStatement(
      ASTReturnStatement returnStatement, Object o) {
    m.verbose("> return");
    m.i();
    Integer i = (Integer) returnStatement.expression.accept(this, o);
    memory.put(returnAddress, i);
    m.d();
    m.verbose("< return [" + i + "]");
    returned = true;
    return i;
  }

  @Override
  public Object visitSub(ASTSub sub, Object o) {
    m.verbose("> sub");
    m.i();
    int r = 0;
    r = (Integer) sub.op1.accept(this, o)
        - (Integer) sub.op2.accept(this, o);
    m.d();
    m.verbose("< sub [" + r + "]");
    return r;
  }

  @Override
  public Object visitVarDeclaration(ASTVarDeclaration varDeclaration,
      Object o) {
    return null;
  }

  @Override
  public Object visitVariable(ASTVariable variable, Object o) {
    m.verbose("> var [" + variable.name + "]");
    m.i();
    int r = memory.get(variable.entry.address);
    m.d();
    m.verbose("< var [" + r + "]");
    return r;
  }

  @Override
  public Object visitWhileStatement(ASTWhileStatement whileStatement,
      Object o) {
    m.verbose("> while");
    m.i();
    int r = 0;
    while ((Integer) whileStatement.expression.accept(this, o) != 0) {
      if (!returned) {
        r = (Integer) whileStatement.statement.accept(this, o);
      }
    }
    m.d();
    m.verbose("< while [" + r + "]");
    return r;
  }
}
