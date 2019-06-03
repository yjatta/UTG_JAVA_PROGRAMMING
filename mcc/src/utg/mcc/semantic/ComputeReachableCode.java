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
import utg.utils.Message;

/**
 * 
 * this class tests if the program contains unreachable code or if the program
 * contains a return statement outside of a function block. the visitor will
 * only visit statements.
 * 
 */
public class ComputeReachableCode extends ASTVisitor {

  Message m = new Message("reachable");

  private boolean insideFunction = false;;

  @Override
  public Object visitAdd(ASTAdd add, Object o) {
    return null;
  }

  @Override
  public Object visitAnd(ASTAnd and, Object o) {
    return null;
  }

  @Override
  public Object visitAssignStatement(
      ASTAssignStatement assignStatement, Object o) {
    boolean reachable = (Boolean) o;
    if (!reachable) {
      m.error("unreachable statement at " + assignStatement.position
          + "\n" + assignStatement);
    }
    // an assignment never contains a return statement
    return false;
  }

  @Override
  public Object visitBlock(ASTBlockStatement block, Object o) {
    boolean reachable = (Boolean) o;
    if (!reachable) {
      m.error("unreachable statement at " + block.position + "\n"
          + block);
    }
    reachable = true;
    boolean hasReturn = false;
    for (int i = 0; i < block.statements.size(); i++) {
      boolean hr = (Boolean) block.statements.elementAt(i).accept(this,
          reachable);
      hasReturn = hr || hasReturn;
      if (hasReturn) {
        reachable = false;
      }
    }
    return hasReturn;
  }

  @Override
  public Object visitCall(ASTCall call, Object o) {
    return null;
  }

  @Override
  public Object visitDiv(ASTDiv div, Object o) {
    return null;
  }

  @Override
  public Object visitEq(ASTEq eq, Object o) {
    return null;
  }

  @Override
  public Object visitFunDeclaration(ASTFunDeclaration funDeclaration,
      Object o) {
    return null;
  }

  @Override
  public Object visitGe(ASTGe ge, Object o) {
    return null;
  }

  @Override
  public Object visitGt(ASTGt gt, Object o) {
    return null;
  }

  @Override
  public Object visitIfStatement(ASTIfStatement ifStatement, Object o) {
    boolean reachable = (Boolean) o;
    if (!reachable) {
      m.error("unreachable statement at " + ifStatement.position + "\n"
          + ifStatement);
    }
    boolean hasReturnT;
    boolean hasReturnE = false;
    hasReturnT = (Boolean) ifStatement.thenStatement.accept(this,
        reachable);
    if (ifStatement.elseStatement != null) {
      hasReturnE = (Boolean) ifStatement.elseStatement.accept(this,
          reachable);
    }
    // an if statement contains a return statement if its then statement
    // an its else statement contain a return statement. if the else part
    // is empty, we may jump over a return statement of the then part,
    // provided the expression is not true
    return hasReturnT && hasReturnE;
  }

  @Override
  public Object visitIntegerConstant(
      ASTIntegerConstant integerConstant, Object o) {
    return null;
  }

  @Override
  public Object visitLe(ASTLe le, Object o) {
    return null;
  }

  @Override
  public Object visitLt(ASTLt lt, Object o) {
    return null;
  }

  @Override
  public Object visitMul(ASTMul mul, Object o) {
    return null;
  }

  @Override
  public Object visitNe(ASTNe ne, Object o) {
    return null;
  }

  @Override
  public Object visitNeg(ASTNeg neg, Object o) {
    return null;
  }

  @Override
  public Object visitNot(ASTNot not, Object o) {
    return null;
  }

  @Override
  public Object visitOr(ASTOr or, Object o) {
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
    boolean reachable = (Boolean) o;
    if (!reachable) {
      m.error("unreachable statement at " + printStatement.position
          + "\n" + printStatement);
    }
    // a print statement never contains a return statement
    return false;
  }

  @Override
  public Object visitProgram(ASTProgram program, Object o) {
    // test if statements inside function blocks are reachable
    insideFunction = true;
    for (int i = 0; i < program.funDeclarations.size(); i++) {
      program.funDeclarations.elementAt(i).block.accept(this, true);
    }
    // test if statements of the program are reachable
    insideFunction = false;
    boolean reachable = true;
    boolean hasReturn = false;
    for (int i = 0; i < program.statements.size(); i++) {
      // if statement i contains a return statement then statement j
      // is not reachable (j > i)
      boolean hr = (Boolean) program.statements.elementAt(i).accept(
          this, reachable);
      hasReturn = hasReturn || hr;
      if (hasReturn) {
        reachable = false;
      }
    }
    return null;
  }

  @Override
  public Object visitReturnStatement(
      ASTReturnStatement returnStatement, Object o) {
    boolean reachable = (Boolean) o;
    if (!insideFunction) {
      m
          .error("illegal return statement at "
              + returnStatement.position);

    }
    if (!reachable) {
      m.error("unreachable statement at " + returnStatement.position
          + "\n" + returnStatement);
    }
    // a return statement always contains a return statement
    return true;
  }

  @Override
  public Object visitSub(ASTSub sub, Object o) {
    return null;
  }

  @Override
  public Object visitVarDeclaration(ASTVarDeclaration varDeclaration,
      Object o) {
    return null;
  }

  @Override
  public Object visitVariable(ASTVariable variable, Object o) {
    return null;
  }

  @Override
  public Object visitWhileStatement(ASTWhileStatement whileStatement,
      Object o) {
    boolean reachable = (Boolean) o;
    if (!reachable) {
      m.error("unreachable statement at " + whileStatement.position
          + "\n" + whileStatement);
    }
    // a while statement contains a return statement if its statement
    // contains a return statement
    return whileStatement.statement.accept(this, reachable);
  }

}
