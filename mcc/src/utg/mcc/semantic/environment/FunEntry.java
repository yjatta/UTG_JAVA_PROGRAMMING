package utg.mcc.semantic.environment;

import utg.mcc.parser.ast.ASTBlockStatement;
import utg.mcc.semantic.types.FunType;

public class FunEntry extends Entry {

  public FunType type;

  public ASTBlockStatement block;

  public int addresses;

  public int formals = 0;

  public FunEntry(String name) {
    super(name);
  }

  @Override
  public String toString() {
    if (type == null) {
      return name;
    } else {
      return name + " " + type.signature();
    }
  }
}
