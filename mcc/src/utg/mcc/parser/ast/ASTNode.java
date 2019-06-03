package utg.mcc.parser.ast;

import utg.mcc.parser.Token;
import utg.utils.Message;

public abstract class ASTNode {

  protected Message m = new Message("interpreter");

  public String position = "";

  public String getPosition() {
    return position;
  }

  public void setPosition(Token t) {
    position = "line " + t.beginLine;
  }

  public abstract Object accept(ASTVisitor visitor, Object o);

}
