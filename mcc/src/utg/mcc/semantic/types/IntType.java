package utg.mcc.semantic.types;

public class IntType extends Type {

	private static IntType instance = null;

	private IntType() {
	}

	public static IntType intType() {
		if (instance == null) {
			instance = new IntType();
		}
		return instance;
	}

	@Override
	public String toString() {
		return "I";
	}

	@Override
	public String signature() {
		return "I";
	}
}
