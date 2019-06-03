package gm.utg.fw.tree;

import gm.utg.fw.parser.Parser;

public class WhileStatement extends Statement {

  private Identifier identifier;
  private StatementList statementList;
  
  public WhileStatement(Identifier identifier, StatementList list, Parser parser) {
    super();
    this.identifier = identifier;
    this.statementList = list;
    this.parser = parser;
  }

  @Override
  public int eval() {
    while (identifier.eval()!= 0) {
      statementList.eval();
    }
    return parser.first.getValue();
  }
  
  public String toString() {
    String s = indent + "while " + identifier + " do \n";
    indent += "  ";
    s += statementList;
    indent = indent.substring(2);
    s += indent + "od";
    return s;
  }
}
