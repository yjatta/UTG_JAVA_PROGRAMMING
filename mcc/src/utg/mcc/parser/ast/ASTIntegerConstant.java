package utg.mcc.parser.ast;

import utg.mcc.parser.util.ASTIdentityTransformation;

public class ASTIntegerConstant extends ASTExpression {

  public final int value;

  public ASTIntegerConstant(int value) {
    super(0);
    this.value = value;
  }

  @Override
  public String toString() {
    return ""
        + (new ASTIdentityTransformation()).visitIntegerConstant(this,
            "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitIntegerConstant(this, o);
  }
}
