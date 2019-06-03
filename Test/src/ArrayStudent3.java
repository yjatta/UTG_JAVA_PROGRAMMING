
public class ArrayStudent3<T> {


	private final T[] bag;
	private static final int DEFAULT_CAPACITY = 25;
	private int numberOfEntries;
	
	public ArrayStudent3(int capacity)
	{
		numberOfEntries = 0;
		// the cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempBag = (T[])new Object[capacity]; // unchecked cast
		bag = tempBag;
	} // end constructor
	
	/** Creates an empty bag whose capacity is 25. */
	public ArrayStudent3()
	{
		this(DEFAULT_CAPACITY);
	} // end default constructor
	
	/** Adds a new entry to this bag.
	@param newEntry the object to be added as a new entry
	@return true if the addition is successful, or false if not */
	public boolean add(T newEntry)
	{
		//< Body to be defined >
		boolean result = true;
		if (isFull())
		{
			result = false;
		}
		else
		{ // assertion: result is true here
			bag[numberOfEntries] = newEntry;
			numberOfEntries++;
		} // end if
		return result;
	} // end add
	/** Retrieves all entries that are in this bag.
	@return a newly allocated array of all the entries in the bag */
	public T[] toArray()
	{
		//< Body to be defined >
		// the cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries]; // unchecked cast
		for (int index = 0; index < numberOfEntries; index++)
		{
			result[index] = bag[index];
		} // end for
		return result;
	} // end toArray
	
	/** Sees whether this bag is full.
	@return true if the bag is full, or false if not */
	public boolean isFull()
	{
		//< Body to be defined >
		return numberOfEntries == bag.length;
	} // end isFull
	
	//========================================================================
	/** Sees whether this bag is empty.
	@return true if the bag is empty, or false if not */
	public boolean isEmpty()
	{
		return numberOfEntries == 0;
	} // end isEmpty
	/** Gets the current number of entries in this bag.
	@return the integer number of entries currently in the bag */
	public int getCurrentSize()
	{
		return numberOfEntries;
	} // end getCurrentSize
	
	/** Counts the number of times a given entry appears in this bag.
	@param anEntry the entry to be counted
	@return the number of times anEntry appears in the bag */
	public int getFrequencyOf(T anEntry)
	{
		int counter = 0;
		for (int index = 0; index < numberOfEntries; index++)
		{
			if (anEntry.equals(bag[index]))
			{
				counter++;
			} // end if
		} // end for
		return counter;
	} // end getFrequencyOf
	
	/** Tests whether this bag contains a given entry.
	@param anEntry the entry to locate
	@return true if the bag contains anEntry, or false otherwise */
	public boolean linearSearch(T anEntry) // linear search
	{
		boolean found = false;
		for (int index = 0; !found && (index < numberOfEntries); index++)
		{
			if (anEntry.equals(bag[index]))
			{
				found = true;
			} // end if
		} // end for
		return found;
	} // end contains
	
	// Question 1
	// without using Arrays.sort 
	//a. Sort the elements in the array
	//b. what is the Big Oh of your algorithm 
	public void sortArray() {
		//
		//bublle sort
		if (bag[0] instanceof String) {
			 int n = numberOfEntries;
		        for (int i = 0; i < n-1; i++)
		            for (int j = 0; j < n-i-1; j++)
		                if (bag[j].toString().compareToIgnoreCase(bag[j+1].toString()) > 0)
		                {
		                	//System.out.println("Test"+ bag[j].toString());
		                    // swap temp and arr[i]
		                    T temp = bag[j];
		                    bag[j] = bag[j+1];
		                    bag[j+1] = temp;
		                }
		}
		else {
			int n = numberOfEntries;
	        for (int i = 0; i < n-1; i++)
	            for (int j = 0; j < n-i-1; j++)
	                if ((double)bag[j] > (double)bag[j+1])
	                {
	                    // swap temp and arr[i]
	                    T temp = bag[j];
	                    bag[j] = bag[j+1];
	                    bag[j+1] = temp;
	                }
		}
		 
		// implement
//	        b Big O n^2
	}

	//Question 2
	// Make research on binary search and implement the function below 
	public boolean binarySearch(T anEntry) // linear search
	{
		// Implement the binary search
		//NOTE : This is for only number types
		int l = 0;
		int r = numberOfEntries;
		if(anEntry instanceof String) {
			 while (l <= r) { 
				 
			        int m = l + (r - l) / 2; 
			  
			        // Check if x is present at mid 
			        if (bag[m].toString().equalsIgnoreCase((String) anEntry))
			            return true; 
			        
			        // If x greater, ignore left half 
			        if (bag[m].toString().compareToIgnoreCase(anEntry.toString()) > 0) {
			        	
			            l = m + 1; 
			          
			        }
			        // If x is smaller, ignore right half 
			        else
			            r = m - 1; 
			    } 
		}
		
		else {
			 while (l <= r) { 
				 
			        int m = l + (r - l) / 2; 
			  
			        // Check if x is present at mid 
			        if (bag[m] == anEntry)
			            return true; 
			        
			        // If x greater, ignore left half 
			        if ((double)bag[m] < (double)anEntry) {
			        	
			            l = m + 1; 
			          
			        }
			        // If x is smaller, ignore right half 
			        else
			            r = m - 1; 
			    } 
		}
		
		return false;
	} // end contains
	
	

}
