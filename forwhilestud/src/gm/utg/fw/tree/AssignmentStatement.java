package gm.utg.fw.tree;

import gm.utg.fw.parser.Parser;

public class AssignmentStatement extends Statement {
  
  private Identifier id;
  private Node expression;

  public AssignmentStatement(Identifier id, Node expression, Parser parser) {
    this.id = id;
    this.expression = expression;
    this.parser = parser;
  }

  @Override
  public int eval() {
    int value = expression.eval();
    id.setValue(value);
    return value;
  }

  public String toString() {
    return indent + id + " = " + expression;
  } 
}
