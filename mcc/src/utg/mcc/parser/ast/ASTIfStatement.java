package utg.mcc.parser.ast;

import utg.mcc.parser.util.ASTIdentityTransformation;

public class ASTIfStatement extends ASTStatement {

  public final ASTExpression expression;
  public final ASTNode thenStatement;
  public final ASTNode elseStatement;

  public ASTIfStatement(ASTExpression expression, ASTNode thenStatement) {
    super();
    this.expression = expression;
    this.thenStatement = thenStatement;
    this.elseStatement = null;
  }

  public ASTIfStatement(ASTExpression expression,
      ASTNode thenStatement, ASTNode elseStatement) {
    super();
    this.expression = expression;
    this.thenStatement = thenStatement;
    this.elseStatement = elseStatement;
  }

  @Override
  public String toString() {
    return ""
        + (new ASTIdentityTransformation()).visitIfStatement(this, "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitIfStatement(this, o);
  }
}
