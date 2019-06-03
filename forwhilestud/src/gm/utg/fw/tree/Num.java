package gm.utg.fw.tree;

public class Num extends Node {
  
  public Num(String image) {
    value = Integer.parseInt(image);
  }

  @Override
  public int eval() {
    return value;
  }

  public String toString() {
    return "" + value;
  }
}
