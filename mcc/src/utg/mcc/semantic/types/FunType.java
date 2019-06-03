package utg.mcc.semantic.types;

public class FunType extends Type {

  public Type returnType;

  public RecordType blockType;

  public FunType(Type returnType, RecordType blockType) {
    this.returnType = returnType;
    this.blockType = blockType;
  }

  @Override
  public String signature() {
    if (blockType != null) {
      return "(" + blockType.signature() + ")" + returnType.signature();
    } else {
      return "()" + returnType.signature();
    }
  }

}
