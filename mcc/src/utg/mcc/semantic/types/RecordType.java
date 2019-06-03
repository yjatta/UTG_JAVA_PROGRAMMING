package utg.mcc.semantic.types;

import utg.mcc.table.Symbol;

public class RecordType extends Type {

  public Symbol fieldName;
  public Type fieldType;
  public RecordType tail;

  public RecordType(String n, Type t, RecordType x) {
    fieldName = Symbol.symbol(n);
    fieldType = t;
    tail = x;
  }

  @Override
  public String toString() {
    if (tail == null) {
      return fieldType.toString();
    } else {
      return fieldType.toString() + tail;
    }
  }

  @Override
  public String signature() {
    if (tail == null) {
      return fieldType.signature();
    } else {
      return fieldType.signature() + tail.signature();
    }
  }
}
