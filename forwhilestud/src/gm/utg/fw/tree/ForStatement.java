package gm.utg.fw.tree;

import gm.utg.fw.parser.Parser;

public class ForStatement extends Statement{

  private Identifier identifier;
  private StatementList statementList; 
  
  public ForStatement(Identifier identifier, StatementList list, Parser parser) {
    super();
    this.identifier = identifier;
    this.statementList = list;
    this.parser = parser;
  }

  @Override
  public int eval() {
    int to = identifier.eval();
    for  (int i = 0; i < to; i++) {
      statementList.eval();
    }
    return parser.first.getValue();
  }
  
  public String toString() {
    String s = indent + "for " + identifier + " do \n";
    indent += "  ";
    s += statementList;
    indent = indent.substring(2);
    s += indent + "od";
    return s;
  }

}
