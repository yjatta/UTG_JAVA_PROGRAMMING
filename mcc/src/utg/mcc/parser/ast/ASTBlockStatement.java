package utg.mcc.parser.ast;

import java.util.Vector;

import utg.mcc.parser.util.ASTIdentityTransformation;

public class ASTBlockStatement extends ASTStatement {

  public final Vector<ASTNode> varDeclarations = new Vector<ASTNode>();
  public final Vector<ASTStatement> statements = new Vector<ASTStatement>();

  public void addDeclaration(ASTNode d) {
    varDeclarations.add(d);
  }

  public void addStatement(ASTStatement s) {
    statements.add(s);
  }

  @Override
  public String toString() {
    return "" + (new ASTIdentityTransformation()).visitBlock(this, "");
  }

  @Override
  public Object accept(ASTVisitor visitor, Object o) {
    return visitor.visitBlock(this, o);
  }
}
