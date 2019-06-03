package utg.mcc.semantic.types;

public class BoolType extends Type {

  private static BoolType instance = null;

  private BoolType() {
  }

  public static BoolType boolType() {
    if (instance == null) {
      instance = new BoolType();
    }
    return instance;
  }

  @Override
  public String toString() {
    return "B";
  }

  @Override
  public String signature() {
    return "B";
  }
}
