package utg.mcc.parser.ast;

import java.util.Vector;

import utg.mcc.parser.util.ASTIdentityTransformation;
import utg.mcc.semantic.environment.FunEntry;

public class ASTCall extends ASTExpression {

  public final String name;

  public FunEntry entry;

  public ASTBlockStatement block;
  public final Vector<ASTExpression> parameters = new Vector<ASTExpression>();

  public ASTCall(String name) {
    super(0);
    this.name = name;
  }

  public void addParameter(ASTExpression expr) {
    parameters.add(expr);
  }

  @Override
  public String toString() {
    return "" + (new ASTIdentityTransformation()).visitCall(this, "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitCall(this, o);
  }
}
