package utg.mcc.parser.ast;

import utg.mcc.parser.util.ASTIdentityTransformation;

public class ASTAssignStatement extends ASTStatement {

  public final ASTVariable variable;

  public final ASTExpression expression;

  public ASTAssignStatement(ASTVariable variable,
      ASTExpression expression) {
    super();
    this.variable = variable;
    this.expression = expression;
  }

  @Override
  public String toString() {
    return ""
        + (new ASTIdentityTransformation()).visitAssignStatement(this,
            "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitAssignStatement(this, o);
  }
}
