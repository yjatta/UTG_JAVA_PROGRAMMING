
public class pLinkedDesk<T> implements Contianer<T> {

	private Node instructor; // reference to first node
	private int numberOfEntries; //counts
	
	public pLinkedDesk()
	{
		instructor = null;
		numberOfEntries = 0;
	} // end default constructor
	  //< Implementations of the public methods declared in BagInterface go here. >
	  //. . .
	private class Node // private inner class
	{
		// implementation
		private T student ;
		private Node next;
		
		public Node(T newEntry) {
			student = newEntry;
			next = null;
		}
	} // end Node
//====================================================================//
	// e.g 1 <-  2 <-  3 <- 4 <- 5 now number 5 is the first entry 
	@Override
	public boolean addFirst(T studentname) {
		// TODO Auto-generated method stub
		if(isEmpty()) { // special case for the first entry
			Node newDesk = new Node(studentname); //place the desk
			instructor = newDesk; // memorize the address the last desk
		}else {
			Node newDesk = new Node(studentname);
			newDesk.next = instructor;//link
			instructor = newDesk;
		}
		numberOfEntries++;
		return true;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return numberOfEntries==0;
	}
	
//====================================================================================//
	
	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		Node currentNode=instructor; //perhaps the instructor
		int index = 0;
		while( (index < numberOfEntries) && (currentNode != null) ) 
		{
			System.out.println(currentNode.student);
			result[index] = currentNode.student;
			index++;
			currentNode = currentNode.next;// follow the link
		}
		
		return result;
	}
//=========================================================================//
	
	@Override
	public int getFrequencyOf(T studentname) {
		int count = 0;
		Node currentNode = instructor;
		while(currentNode != null) {
			if(currentNode.student.equals(studentname))
				count++;
			currentNode = currentNode.next; // advance to the next node or desk
		}
		return count;
	}
	
	@Override
	public int getCurrentSize() {
		// TODO Auto-generated method stub
		return numberOfEntries;
	}
	
	//// Question 1
	// Remove last
	public void removeLast() {
          Node lastNode = instructor;

	while(lastNode != null){
	 if(lastNode.next == null){
             remove(lastNode.student);
       }
	 lastNode = lastNode.next;
	}
	}
	
	// remove the first entry from the linkedlist
	public T removeFirst() {
		// TODO Auto-generated method stub
		Node removeNode = instructor;// request from the instructor
		if(removeNode != null) {
			instructor = removeNode.next; // paper holding the next reference
			numberOfEntries--;
		}
		return removeNode.student;
	}
	
	//Question 2
	// In remove function below, explain what happen when "break" is:
	// A. uncommented and
	// B. commented
          // when break is uncommented the loop will check whether the seachval.student =instructor.student if it is equal to instructor.student it will removeFirst then the condition will continue to the next Node and print TRUE.
       //when the break is commented the loop will check whether the condition is true, if so it will remove first and terminate.
          // if the break is not 
	
//	with the break; the the method removes only the first occurrence of the entry
//	without it , the method remove all occurrence of the entry
	@Override
	public boolean remove(T anEntry) {
		// TODO Auto-generated method stub
		//boolean valid = false;
		Node seachval = instructor;
		while(seachval != null)
		{
			if(seachval.student.equals(anEntry)){
				seachval.student = instructor.student;
				removeFirst();
				break;  
			}
			seachval = seachval.next;
		}
		return true;
	}
	
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		while(!isEmpty()) {
			removeFirst();
		}
	}
	
	// question 3
	// check if the entry contain a particular entry
	@Override
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
		
		  Node currentNode = instructor;

			while(currentNode != null){
			 if(currentNode.student == anEntry){
		           return true;
		       }
			 currentNode = currentNode.next;
		
			}
			return false;
	}
	
	// Question 4
	// e.g -2, -1, 1 <-  2 <-  3 <- 4 <- 5 now number 5 is the first entry 
	//add a new node at the end e.g -1 ,-2 and so on
	public boolean addLast(T entry) {
		if(isEmpty()) { // special case for the first entry
			Node newDesk = new Node(entry); //place the desk
			instructor = newDesk; // memorize the address the last desk
		}else {
			Node newDesk = new Node(entry);
			Node lastNode = instructor;
			while(lastNode != null){
				 if(lastNode.next == null){
			             lastNode.next = newDesk;
			             newDesk.next = null;
			       }
				 lastNode = lastNode.next;
				}
		}
		numberOfEntries++;
		return true;
	}

	}