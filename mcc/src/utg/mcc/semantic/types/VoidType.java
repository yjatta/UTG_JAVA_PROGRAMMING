package utg.mcc.semantic.types;

public class VoidType extends Type {

	private static VoidType instance = null;

	private VoidType() {
	}

	public static VoidType voidType() {
		if (instance == null) {
			instance = new VoidType();
		}
		return instance;
	}

	@Override
	public String signature() {
		return "V";
	}
}
