package utg.mcc.parser.ast;

import utg.mcc.parser.util.ASTIdentityTransformation;

public class ASTNeg extends ASTExpression {

  public final ASTExpression op;

  public ASTNeg(ASTExpression op) {
    super(1);
    this.op = op;
  }

  @Override
  public String toString() {
    return "" + (new ASTIdentityTransformation()).visitNeg(this, "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitNeg(this, o);
  }
}
