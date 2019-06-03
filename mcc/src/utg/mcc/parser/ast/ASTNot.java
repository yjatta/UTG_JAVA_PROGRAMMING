package utg.mcc.parser.ast;

import utg.mcc.parser.util.ASTIdentityTransformation;

public class ASTNot extends ASTExpression {

  public final ASTExpression op;

  public ASTNot(ASTExpression op) {
    super(1);
    this.op = op;
  }

  @Override
  public String toString() {
    return "" + (new ASTIdentityTransformation()).visitNot(this, "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitNot(this, o);
  }
}
