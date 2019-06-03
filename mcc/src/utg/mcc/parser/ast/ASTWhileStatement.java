package utg.mcc.parser.ast;

import utg.mcc.parser.util.ASTIdentityTransformation;

public class ASTWhileStatement extends ASTStatement {

  public final ASTExpression expression;
  public final ASTNode statement;

  public ASTWhileStatement(ASTExpression expression, ASTNode statement) {
    super();
    this.expression = expression;
    this.statement = statement;
  }

  @Override
  public String toString() {
    return ""
        + (new ASTIdentityTransformation()).visitWhileStatement(this,
            "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitWhileStatement(this, o);
  }
}
