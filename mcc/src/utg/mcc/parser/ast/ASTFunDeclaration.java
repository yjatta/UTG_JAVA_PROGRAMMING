package utg.mcc.parser.ast;

import java.util.Vector;

import utg.mcc.parser.util.ASTIdentityTransformation;
import utg.mcc.semantic.environment.FunEntry;

public class ASTFunDeclaration extends ASTDeclaration {

  public final String name;

  public FunEntry entry;

  public final String type;
  public final Vector<ASTParDeclaration> parameters = new Vector<ASTParDeclaration>();
  public ASTBlockStatement block;

  public void addDeclaration(ASTParDeclaration parDec) {
    parameters.add(parDec);
  }

  public ASTFunDeclaration(String name, String type) {
    super();
    this.name = name;
    this.type = type;
  }

  public ASTBlockStatement getBlock() {
    return block;
  }

  public void setBlock(ASTBlockStatement block) {
    this.block = block;
  }

  public ASTParDeclaration get(int i) {
    return parameters.elementAt(i);
  }

  @Override
  public String toString() {
    return ""
        + (new ASTIdentityTransformation()).visitFunDeclaration(this,
            "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitFunDeclaration(this, o);
  }
}
