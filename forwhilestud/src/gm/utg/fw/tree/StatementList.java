package gm.utg.fw.tree;

import gm.utg.fw.parser.Parser;

import java.util.Vector;

public class StatementList extends Node {
  
  private Vector<Node> list = new Vector<Node>();

  public StatementList(Parser parser) {
    super();
    this.parser = parser;
  }

  @Override
  public int eval() {
    for (int i = 0; i < list.size(); i++) {
      list.elementAt(i).eval();
    }
    return parser.first.getValue();
  }
  
  public void add(Node node) {
    list.add(node);
  }
  
  public String toString() {
   String res = "";
   for (int i = 0; i < list.size(); i++) {
     res += list.elementAt(i);
     if (i < list.size()-1) {
       res += ";";
     }
     res += "\n";
   }
   return res;
  }
}
