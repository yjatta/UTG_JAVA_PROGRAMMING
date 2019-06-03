package utg.mcc.parser.ast;

import java.util.Vector;

import utg.mcc.parser.util.ASTIdentityTransformation;
import utg.mcc.semantic.environment.GlobalEntry;

public class ASTProgram extends ASTNode {

  public GlobalEntry entry = null;

  public ASTProgram() {
    super();
  }

  public final Vector<ASTVarDeclaration> varDeclarations = new Vector<ASTVarDeclaration>();
  public final Vector<ASTFunDeclaration> funDeclarations = new Vector<ASTFunDeclaration>();
  public final Vector<ASTStatement> statements = new Vector<ASTStatement>();

  public void addDeclaration(ASTNode d) {
    if (d instanceof ASTVarDeclaration) {
      varDeclarations.add((ASTVarDeclaration) d);
    } else {
      funDeclarations.add((ASTFunDeclaration) d);
    }
  }

  public void addStatement(ASTStatement statement) {
    statements.add(statement);
  }

  @Override
  public String toString() {
    return ""
        + (new ASTIdentityTransformation()).visitProgram(this, "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitProgram(this, o);
  }
}
