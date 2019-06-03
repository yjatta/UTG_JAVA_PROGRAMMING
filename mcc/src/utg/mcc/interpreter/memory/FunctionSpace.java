package utg.mcc.interpreter.memory;


public class FunctionSpace<Value> extends MemorySpace<Value> {

	public FunctionSpace(MemorySpace<Value> parent) {
		this.parent = parent;
	}

	@Override
	public Value get(Integer i) {
		if (members.get(i) != null) {
			return members.get(i);
		} else if (parent != null) {
			return parent.get(i);
		} else {
			return null;
		}
	}

}
