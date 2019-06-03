package utg.mcc.parser.ast;

import utg.mcc.parser.util.ASTIdentityTransformation;
import utg.mcc.semantic.environment.VarEntry;

public class ASTParDeclaration extends ASTDeclaration {

  public final String name;

  public VarEntry entry;

  public final String type;

  public ASTParDeclaration(String name, String type) {
    super();
    this.name = name;
    this.type = type;
  }

  @Override
  public String toString() {
    return ""
        + (new ASTIdentityTransformation()).visitParDeclaration(this,
            "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitParDeclaration(this, o);
  }
}
