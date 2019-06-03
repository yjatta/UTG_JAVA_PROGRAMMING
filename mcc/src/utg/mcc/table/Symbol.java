package utg.mcc.table;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Symbol {
	private final String name;

	private Symbol(String n) {
		name = n;
	}

	private static Dictionary<String, Symbol> dict = new Hashtable<String, Symbol>();

	/**
	 * Make return the unique symbol associated with a string. Repeated calls to
	 * <tt>symbol("abc")</tt> will return the same Symbol.
	 */

	public static Symbol symbol(String n) {
		String u = n.intern();
		Symbol s = dict.get(u);
		if (s == null) {
			s = new Symbol(u);
			dict.put(u, s);
		}
		return s;
	}

	public static String elementsToString() {
		String s = "";
		Enumeration<Symbol> e = dict.elements();
		while (e.hasMoreElements()) {
			s += e.nextElement();
			if (e.hasMoreElements()) {
				s += ", ";
			}
		}
		return s;
	}

	@Override
	public String toString() {
		return name;
	}
}
