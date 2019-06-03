package barrow.utg;

import java.util.ArrayList;

public class Testing {
	public static void reverse(int []a) {
		int [] helper = new int[a.length];
		int j= 0;
		for (int i = 0; i < a.length; i++) {
			helper[i] = a[i];
		}
		for (int i = helper.length-1; i >=0; i--,j++) {
			a[j] = helper[i];
		
	}
}
	public static void printer(int[]a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int []a = {1,2,3,4,5};
		reverse(a);
		printer(a);
	}
}
