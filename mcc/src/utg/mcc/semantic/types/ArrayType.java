package utg.mcc.semantic.types;

public class ArrayType extends Type {

	public Type element;

	public ArrayType(Type element) {
		this.element = element;
	}

	@Override
	public String signature() {
		return "[" + element.signature();
	}
}
