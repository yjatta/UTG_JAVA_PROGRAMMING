package alieuCeesay;

import java.util.ArrayList;

public class ArrayListsAssignment {

	public static void printer(ArrayList<Integer> a){
		for (int i = 0; i < a.size(); i++) {
			System.out.print(" "+a.get(i));
		}
		System.out.println();
	}
	
	public static int findMax(ArrayList<Integer> a) {
		int max = a.get(0);
		for (int i = 1; i < a.size(); i++) {
			if(max < a.get(i))
				max = a.get(i);
		}
		return max;
	}
	public static int findMin(ArrayList<Integer> a) {
		int min = a.get(0);
		for (int i = 1; i < a.size(); i++) {
			if(min > a.get(i))
				min = a.get(i);
		}
		return min;
	}
	public static double average(ArrayList<Integer> a) {
		int sum = 0;
		for (int i = 0; i < a.size(); i++) {
			sum += a.get(i);
		}
		return sum/ a.size();
	}
	
	public static int counter(ArrayList<Integer> a, int num) {
		int count = 0;
		for (int i = 0; i < a.size(); i++) {
			if(a.get(i) == num)
				count++;
		}
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> a= new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		System.out.print("The set of numbers:");
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
