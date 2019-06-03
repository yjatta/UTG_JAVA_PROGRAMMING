package utg.mcc.parser.ast;

import utg.mcc.parser.util.ASTIdentityTransformation;
import utg.mcc.semantic.environment.VarEntry;

public class ASTVarDeclaration extends ASTDeclaration {

  public final String name;
  public final String type;

  public VarEntry entry;

  public ASTVarDeclaration(String name, String type) {
    super();
    this.name = name;
    this.type = type;
  }

  @Override
  public String toString() {
    return ""
        + (new ASTIdentityTransformation()).visitVarDeclaration(this,
            "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitVarDeclaration(this, o);
  }
}
