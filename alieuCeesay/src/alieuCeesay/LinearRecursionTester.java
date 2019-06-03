package alieuCeesay;

public class LinearRecursionTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr = new int [45];
		LinearRecursioin linear = new LinearRecursioin();
		linear.populateArray(arr);
		linear.printArray(arr);
		System.out.println();
		System.out.println("Index : " + linear.recsearch(arr, arr.length-1, 10));
		
	}

}
