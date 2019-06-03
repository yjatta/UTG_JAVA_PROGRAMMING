package utg.mcc.table;

class Binder<T> {

	T value;

	Symbol prevtop;

	Binder<T> tail;

	Binder(T value, Symbol prevtop, Binder<T> tail) {
		this.value = value;
		this.prevtop = prevtop;
		this.tail = tail;
	}

	@Override
	public String toString() {
		if (tail == null) {
			return "[" + value + "," + prevtop + "] > " + tail;
		} else {
			return "[" + value + "," + prevtop + "]";
		}
	}
}