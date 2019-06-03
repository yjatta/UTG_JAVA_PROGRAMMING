package gm.utg.fw.tree;

public class Plus extends Statement {
  
  private Node arg1;
  private Node arg2;
  
  public Plus(Node arg1, Node arg2) {
    this.arg1 = arg1;
    this.arg2 = arg2;
  }

  @Override
  public int eval() {
    return arg1.eval() + arg2.eval();
  }

  public String toString() {
    return arg1 + " + " + arg2;
  }
}
