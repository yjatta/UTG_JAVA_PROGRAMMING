package utg.mcc.parser.ast;

public abstract class ASTExpression extends ASTNode {

  // the priority of the expression
  public int priority;

  public ASTExpression(int priority) {
    super();
    this.priority = priority;
  }
}
