package barrow.utg;

public class arrays {
	public static void printer(int []a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
		}
		System.out.println();
	}
	public static void swap(int[]a) {
		int helper = a[0];
		a[0] = a[a.length -1];
		a[a.length-1] = helper;
	}
	public static int power(int base, int exp) {
		int result = 1;
		for(int i=1; i <= exp; i++) {
			result *= base;
//			result= result * base;
		}
		return result;
	}
	public static boolean search(int a[], int value) {
		
		for (int i = 0; i < a.length; i++) {
			if(value == a[i])
				return true;
			
		}
		return false;
		
	}
	public static int sumDigits(int num) {
		String number = Integer.toString(num);
		int sum = 0;
		for (int i = 0; i < number.length(); i++) {
			String c = Character.toString(number.charAt(i));
			sum += Integer.parseInt(c);
		}
		return sum;
	}
	public static int addOdd(int []a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			if(a[i]%2 == 1) {
				sum += a[i];
			}
		}
		return sum;
	}
	public static int findMaxPos(int []a) {
		int maxPos = 0;
		for (int i = 1; i < a.length; i++) {
			if(a[maxPos] < a[i])
				maxPos = i;
		}
		return maxPos;
	}

	public static void main(String[] args) {
//	System.out.println();
	System.out.println(sumDigits(234));
	}
//	int a[][] = {[1][2], t[2][4]};
	int y = 8;
}
