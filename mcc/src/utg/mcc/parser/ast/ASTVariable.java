package utg.mcc.parser.ast;

import utg.mcc.parser.util.ASTIdentityTransformation;
import utg.mcc.semantic.environment.VarEntry;

public class ASTVariable extends ASTExpression {

  public final String name;

  public VarEntry entry;

  public ASTVariable(String name) {
    super(0);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return ""
        + (new ASTIdentityTransformation()).visitVariable(this, "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitVariable(this, o);
  }
}
