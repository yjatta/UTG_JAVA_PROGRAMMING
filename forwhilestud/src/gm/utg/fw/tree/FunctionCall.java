package gm.utg.fw.tree;

import java.util.Vector;
import gm.utg.fw.Type;
import gm.utg.fw.parser.Parser;

public class FunctionCall extends Node implements Type {

  public Identifier id;
  public String function;
  public Vector<Node> arguments = new Vector<Node>();
  int type;
  private long time;

  public FunctionCall(String function, Identifier id, int type,
      long time, Parser parser) {
    this.function = function;
    this.id = id;
    this.type = type;
    this.time = time;
    this.parser = parser;
  }

  @Override
  public int eval() {
    Parser p = new Parser(function, type);
    // check illegal recursive call
    if (p.getTime() > time) {
      throw new RuntimeException(
          "possible recursive call, the time stamp [" + time
              + "] of the caller is smaller than the time stamp ["
              + p.getTime() + "] of the function called");
    }
    String input = "";
    for (int i = 0; i < arguments.size(); i++) {
      input += arguments.elementAt(i).value + " ";
    }
    // System.out.println(input);
    p.setInput(input.trim());
    int value = p.parse().eval();
    id.setValue(value);
    // System.out.println("> " + value);
    return value;
  }

  public String toString() {
    String result = id + " = " + function + "(";
    for (int i = 0; i < arguments.size(); i++) {
      result += arguments.elementAt(i);
      if (i < arguments.size() - 1) {
        result += ", ";
      }
    }
    return result + ")";
  }

}
