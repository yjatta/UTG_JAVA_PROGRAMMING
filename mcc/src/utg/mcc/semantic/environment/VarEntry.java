package utg.mcc.semantic.environment;

import utg.mcc.semantic.types.Type;

public class VarEntry extends Entry {

  public Type type = null;

  public int address = -1;

  public int addresses = -1;

  public boolean global = false;

  public VarEntry(String name) {
    super(name);
  }

  @Override
  public String toString() {
    if (type == null) {
      return name + " " + address;
    } else {
      return name + " " + address + " " + type.signature();
    }
  }
}
