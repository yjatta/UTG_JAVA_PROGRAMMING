package alieuCeesay;

public class Past {
	public static int sumDigit(int n) {
		
		if(n <= 0) {
			return n; 
		}
		return n%10 + sumDigit((int)n/10);
	}
	
	public static int getcomputedTax() {
		int result = getcomputedTax();
		
		return result;
	}
	
	public static void mystery(int a [], int i) {
		int t = a[i];
		while (i > 0) {
			a[i]= a[i-1];
			i--;
		}
		a[i] =t;
	}
//	123
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a [] = {1,2,3,4,5};
		mystery(a, 3);
		
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
//		System.out.println(sumDigit(1234));
//		System.out.println(getcomputedTax());
		
	}

}