
public class Tester {
public static void printArray( Object [] result) {
	
	for (int i = 0; i < result.length; i++) {
		System.out.print(result[i] + ", ");
	}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayStudent3<Double> arr= new ArrayStudent3<Double>(6);
//		arr.add("Ebriama");
//		arr.add("Yoromang");
//		arr.add("Abdoulie");
//		arr.add("Jatta");
//		arr.add("Bunja");
//		arr.add("Manneh");
		
		arr.add(1.0);
		arr.add(2.3);
		arr.add(2.0);
		arr.add(7.5);
		arr.add(5.9);
		arr.add(4.3);
		Object [] result = arr.toArray();
				
		System.out.println("########################## Before Sorting   ###############################");
		printArray(result);
		arr.sortArray();
		System.out.println();
		System.out.println("########################## After Sorting   ###############################");
		Object [] test = arr.toArray();
		printArray(test);
		
		System.out.println();
		System.out.println("#################### BINARYSEARCH (4)###############################");
System.out.println(arr.binarySearch(5.9));
	}

}
