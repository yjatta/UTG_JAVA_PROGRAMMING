package iterator;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		    Tree<String> tree = new Tree<String>();
		    Position<String> a = tree.makePosition("A");
		    tree.SetRoot(a);
		    Position<String> b = tree.makePosition("B");
		    Position<String> c = tree.makePosition("C");
		    a.setLeft(b);
		    a.setRight(c);
		    Position<String> d = tree.makePosition("D");
		    Position<String> e = tree.makePosition("E");
		    b.setLeft(d);
		    b.setRight(e);
		    Position<String> f = tree.makePosition("F");
		    c.setLeft(f);
		    System.out.println("preorder with stack\n");
		    Iterator<String> iterator = tree.preorderIteratorWithStack();
		    while (iterator.hasNext()) {
		      System.out.print(iterator.next());
		    }
		    System.out.println("\n\npreorder without stack\n");
		    iterator = tree.preorderIteratorNoStack();
		    while (iterator.hasNext()) {
		      System.out.print(iterator.next());
		    }

		    System.out.println("\n\npostorder with stack\n");
		    iterator = tree.postorderIteratorWithStack();
		    while (iterator.hasNext()) {
		      System.out.print(iterator.next());
		    }
		    System.out.println("\npostorder without stack\n");
		    iterator = tree.postorderIteratorNoStack();
		    while (iterator.hasNext()) {
		      System.out.print(iterator.next());
		    }
		    System.out.println("\n");
		  

	}

}