
public interface Contianer<T> {
	public int getCurrentSize();
	//public boolean isFull();
	public boolean isEmpty();
	public boolean addFirst(T newEntry);
	public boolean remove(T anEntry);
	public void clear();
	public int getFrequencyOf(T anEntry);
	public boolean contains(T anEntry);
	public T[] toArray();
}
