package utg.mcc.parser.ast;

import utg.mcc.parser.util.ASTIdentityTransformation;

public class ASTEq extends ASTExpression {

  public final ASTExpression op1;
  public final ASTExpression op2;

  public ASTEq(ASTExpression op1, ASTExpression op2) {
    super(4);
    this.op1 = op1;
    this.op2 = op2;
  }

  @Override
  public String toString() {
    return "" + (new ASTIdentityTransformation()).visitEq(this, "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitEq(this, o);
  }
}
