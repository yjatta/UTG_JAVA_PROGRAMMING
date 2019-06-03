import java.util.Scanner;

public class maja_zero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the length of the triangle");
		int len = input.nextInt();
		for ( int i = 1; i <= len; i++) {
			for (int j = 1; j <= len; j++) {
				if ( j >= i) {
					System.out.print((-j + len + i) + " ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
		
	}

}
