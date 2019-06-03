package utg.mcc.parser.util;

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

/**
 * transforms the source code into itself (prety printy). the identity
 * transformation is very usefull for debuging.
 */
public class ASTIdentityTransformation extends ASTVisitor {

  private String binaryOperator(String lexem, int priority,
      ASTExpression op1, ASTExpression op2, String indent) {
    if (op1.priority > priority && op2.priority > priority) {
      return "(" + op1.accept(this, indent) + ")    " + lexem + " ("
          + op2.accept(this, indent) + ")";
    } else if (op1.priority > priority) {
      return "(" + op1.accept(this, indent) + ")" + lexem
          + op2.accept(this, indent);
    } else if (op2.priority > priority) {
      return op1.accept(this, indent) + " " + lexem + " ("
          + op2.accept(this, indent) + ")";
    } else {
      return op1.accept(this, indent) + " " + lexem + " "
          + op2.accept(this, indent);
    }
  }

  private String unaryOperator(String lexem, int priority,
      ASTExpression op, String indent) {
    if (op.priority > priority) {
      return lexem + "(" + op.accept(this, indent) + ")";
    } else {
      return lexem + op.accept(this, indent);
    }
  }

  @Override
  public Object visitAdd(ASTAdd add, Object indent) {
    return binaryOperator("+", add.priority, add.op1, add.op2,
        (String) indent);
  }

  @Override
  public Object visitAnd(ASTAnd and, Object indent) {
    return binaryOperator("&&", and.priority, and.op1, and.op2,
        (String) indent);
  }

  @Override
  public Object visitAssignStatement(
      ASTAssignStatement assignStatement, Object indent) {
    return ((String) indent)
        + assignStatement.variable.accept(this, "") + " = "
        + assignStatement.expression.accept(this, "") + ";\n";
  }

  @Override
  public Object visitBlock(ASTBlockStatement block, Object indent) {
    String s = "{\n";
    for (int i = 0; i < block.varDeclarations.size(); i++) {
      s += block.varDeclarations.elementAt(i).accept(this,
          indent + "  ");
    }
    for (int i = 0; i < block.statements.size(); i++) {
      s += block.statements.elementAt(i).accept(this, indent + "  ");
    }
    s += indent + "} ";
    return s;
  }

  @Override
  public Object visitCall(ASTCall call, Object indent) {
    String s = call.name + "(";
    for (int i = 0; i < call.parameters.size(); i++) {
      s += call.parameters.elementAt(i).accept(this, "");
      if (i < call.parameters.size() - 1) {
        s += ", ";
      }
    }
    s += ")";
    return s;
  }

  @Override
  public Object visitDiv(ASTDiv div, Object indent) {
    return binaryOperator("/", div.priority, div.op1, div.op2,
        (String) indent);
  }

  @Override
  public Object visitEq(ASTEq eq, Object indent) {
    return binaryOperator("==", eq.priority, eq.op1, eq.op2,
        (String) indent);
  }

  @Override
  public Object visitFunDeclaration(ASTFunDeclaration funDeclaration,
      Object indent) {
    String s = indent + "int " + funDeclaration.name + "(";
    for (int i = 0; i < funDeclaration.parameters.size(); i++) {
      s += funDeclaration.parameters.elementAt(i).accept(this, "");
      if (i < funDeclaration.parameters.size() - 1) {
        s += ", ";
      }
    }
    s += ") " + funDeclaration.block.accept(this, indent);
    return s;
  }

  @Override
  public Object visitGe(ASTGe ge, Object indent) {
    return binaryOperator(">=", ge.priority, ge.op1, ge.op2,
        (String) indent);
  }

  @Override
  public Object visitGt(ASTGt gt, Object indent) {
    return binaryOperator(">", gt.priority, gt.op1, gt.op2,
        (String) indent);
  }

  @Override
  public Object visitIfStatement(ASTIfStatement ifStatement,
      Object indent) {
    String s = indent + "if " + "("
        + ifStatement.expression.accept(this, "") + ") ";
    if (ifStatement.thenStatement instanceof ASTBlockStatement) {
      s += ifStatement.thenStatement.accept(this, indent);
    } else {
      s += " " + ifStatement.thenStatement.accept(this, "");
    }
    if (ifStatement.elseStatement != null) {
      if (ifStatement.elseStatement instanceof ASTBlockStatement) {
        s += "else " + ifStatement.elseStatement.accept(this, indent)
            + "\n";
      } else {
        s += indent + "else "
            + ifStatement.elseStatement.accept(this, "");
      }
    } else {
      if (ifStatement.thenStatement instanceof ASTBlockStatement) {
        s += "\n";
      }
    }
    return s;
  }

  @Override
  public Object visitIntegerConstant(
      ASTIntegerConstant integerConstant, Object indent) {
    return "" + integerConstant.value;
  }

  @Override
  public Object visitLe(ASTLe le, Object indent) {
    return binaryOperator("<=", le.priority, le.op1, le.op2,
        (String) indent);
  }

  @Override
  public Object visitLt(ASTLt lt, Object indent) {
    return binaryOperator("<", lt.priority, lt.op1, lt.op2,
        (String) indent);
  }

  @Override
  public Object visitMul(ASTMul mul, Object indent) {
    if (mul.op1.priority > 2 && mul.op2.priority > 2) {
      return "(" + mul.op1.accept(this, indent) + ") * ("
          + mul.op2.accept(this, indent) + ")";
    } else if (mul.op1.priority > 2) {
      return "(" + mul.op1.accept(this, indent) + ") * "
          + mul.op2.accept(this, indent);
    } else if (mul.op2.priority > 2) {
      return mul.op1.accept(this, indent) + " * ("
          + mul.op2.accept(this, indent) + ")";
    } else {
      return mul.op1.accept(this, indent) + " * "
          + mul.op2.accept(this, indent);
    }
  }

  @Override
  public Object visitNe(ASTNe ne, Object indent) {
    return binaryOperator("!=", ne.priority, ne.op1, ne.op2,
        (String) indent);
  }

  @Override
  public Object visitNeg(ASTNeg neg, Object indent) {
    return unaryOperator("-", neg.priority, neg.op, (String) indent);
  }

  @Override
  public Object visitNot(ASTNot not, Object indent) {
    return unaryOperator("!", not.priority, not.op, (String) indent);
  }

  @Override
  public Object visitOr(ASTOr or, Object indent) {
    return binaryOperator("||", or.priority, or.op1, or.op2,
        (String) indent);
  }

  @Override
  public Object visitParDeclaration(ASTParDeclaration parDeclaration,
      Object indent) {
    return "int " + parDeclaration.name;
  }

  @Override
  public Object visitPrintStatement(ASTPrintStatement printStatement,
      Object indent) {
    return ((String) indent) + "print("
        + printStatement.expression.accept(this, "") + ");\n";
  }

  @Override
  public Object visitProgram(ASTProgram program, Object indent) {
    String s = "";
    for (int i = 0; i < program.varDeclarations.size(); i++) {
      s += program.varDeclarations.elementAt(i).accept(this, indent);
    }
    s += "\n";
    for (int i = 0; i < program.funDeclarations.size(); i++) {
      s += program.funDeclarations.elementAt(i).accept(this, indent);
    }
    s += "\n";
    for (int i = 0; i < program.statements.size(); i++) {
      s += program.statements.elementAt(i).accept(this, indent);
    }
    return s;
  }

  @Override
  public Object visitReturnStatement(
      ASTReturnStatement returnStatement, Object indent) {
    return ((String) indent) + "return "
        + returnStatement.expression.accept(this, "") + ";\n";
  }

  @Override
  public Object visitSub(ASTSub sub, Object indent) {
    return binaryOperator("-", sub.priority, sub.op1, sub.op2,
        (String) indent);
  }

  @Override
  public Object visitVarDeclaration(ASTVarDeclaration varDeclaration,
      Object indent) {
    return indent + "int " + varDeclaration.name + ";\n";
  }

  @Override
  public Object visitVariable(ASTVariable variable, Object indent) {
    return variable.name;
  }

  @Override
  public Object visitWhileStatement(ASTWhileStatement whileStatement,
      Object indent) {
    String s = indent + "while " + "("
        + whileStatement.expression.accept(this, "") + ") ";
    if (whileStatement.statement instanceof ASTBlockStatement) {
      s += whileStatement.statement.accept(this, indent) + "\n";
    } else {
      s += whileStatement.statement.accept(this, "") + ";";
    }
    return s;
  }
}
