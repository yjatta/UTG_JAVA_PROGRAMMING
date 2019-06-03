package utg.mcc.semantic.types;

public class StringType extends Type {

	private static StringType instance = null;

	public static StringType stringType() {
		if (instance == null) {
			instance = new StringType();
		}
		return instance;
	}

	@Override
	public String signature() {
		return "Ljava/lang/String;";
	}
}
