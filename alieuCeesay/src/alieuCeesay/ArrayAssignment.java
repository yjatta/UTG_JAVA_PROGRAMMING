package alieuCeesay;

public class ArrayAssignment {

	public static void printer(int []a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
		}
		System.out.println();
	}
	
	public static int findMax(int []a) {
		int max = a[0];
		for (int i = 1; i < a.length; i++) {
			if(a[max] < a[i])
				max = a[i];
		}
		return max;
	}
	public static int findMin(int []a) {
		int min = a[0];
		for (int i = 1; i < a.length; i++) {
			if(a[min] > a[i])
				min = a[i];
		}
		return min;
	}
	public static double average(int []a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum/ a.length;
	}
	
	public static int counter(int []a, int num) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if(a[i] == num)
				count++;
		}
		return count;
	}
	public static void main(String[] args) {
		
		// use the above methods here for arrays and arraylist
		//example below is for the printer
		int a[] = {1,2,3,4,5};
		printer(a);
		// the same for the rest
		int max = findMax(a);
		System.out.println("The maximum number is: "+ max);
		int min = findMin(a);
		System.out.println("The minimum number is: "+ min);
		double average = average(a);
		System.out.println("The avearage is : "+ average);
		int count = counter(a, 2);
		System.out.println("2 occurs "+ count + " times in the set");
	}
}
