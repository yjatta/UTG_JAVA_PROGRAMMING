package utg.mcc.parser.ast;

import utg.mcc.parser.util.ASTIdentityTransformation;

public class ASTOr extends ASTExpression {

  public final ASTExpression op1;
  public final ASTExpression op2;

  public ASTOr(ASTExpression op1, ASTExpression op2) {
    super(6);
    this.op1 = op1;
    this.op2 = op2;
  }

  @Override
  public String toString() {
    return "" + (new ASTIdentityTransformation()).visitOr(this, "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitOr(this, o);
  }

}
