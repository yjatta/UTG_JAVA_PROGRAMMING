package iterator;

import java.util.NoSuchElementException;

public interface Position<E> {

	 public E getElement();

	  public void setElement(E element);

	  public Position<E> getLeft();

	  public void setLeft(Position<E> left);

	  public Position<E> getRight();

	  public void setRight(Position<E> right);

	  public Position<E> getParent() throws NoSuchElementException;

	  public void setParent(Position<E> parent);
}
