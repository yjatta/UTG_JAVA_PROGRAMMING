package gm.utg.fw.tree;

public class Minus extends Statement {

  Node arg1;
  Node arg2;
  
  public Minus(Node arg1, Node arg2) {
    this.arg1 = arg1;
    this.arg2 = arg2;
  }

  @Override
  public int eval() {
    int res = arg1.eval() - arg2.eval();
    if (res < 0) {
      res = 0;
    }
    return res;
  }

  public String toString() {
    return arg1 + " - " + arg2;
  }
}
