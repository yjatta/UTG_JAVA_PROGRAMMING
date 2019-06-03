package utg.mcc.semantic.types;

public abstract class Type {

  public static Type makeType(String type) {
    if (type.equals("int")) {
      return IntType.intType();
    } else if (type.equals("boolean")) {
      return BoolType.boolType();
    } else if (type.equals("void")) {
      return VoidType.voidType();
    } else if (type.equals("string")) {
      return StringType.stringType();
    } else {
      return null;
    }
  }

  public abstract String signature();

  private boolean hasType(Type t) {
    return this.signature().equals(t.signature());
  }

  public static boolean checkType(Type t, Type t1, Type t2) {
    if (!t1.hasType(t) || !t2.hasType(t)) {
      return false;
    } else {
      return true;
    }
  }

  public static boolean checkType(Type t, Type t1) {
    if (!t1.hasType(t)) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public String toString() {
    return signature();
  }
}
