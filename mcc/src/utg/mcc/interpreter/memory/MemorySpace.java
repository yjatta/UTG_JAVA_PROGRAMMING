package utg.mcc.interpreter.memory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MemorySpace<Value> {

	protected MemorySpace<Value> parent = null;

	protected final Map<Integer, Value> members = new HashMap<Integer, Value>();

	public MemorySpace() {
		super();
	}

	public Value get(Integer i) {
		return members.get(i);
	}

	public void put(Integer i, Value value) {
		members.put(i, value);
	}

	@Override
	public String toString() {
		String s = "[";
		Iterator<Integer> iterator = members.keySet().iterator();
		while (iterator.hasNext()) {
			Integer i = iterator.next();
			s += i + ":" + members.get(i);
			if (iterator.hasNext()) {
				s += ", ";
			}
		}
		return s + "]";
	}
}
