package question_0;

import java.util.Scanner;

public class NumberTriangle {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the height of the triangle");
		int lenghtOfTriangle = scan.nextInt();
		// TODO Auto-generated method stub
		for (int i = 1; i <= lenghtOfTriangle; i++) {
			for(int j = lenghtOfTriangle; j >= i; j--) {
				System.out.print(j + " ");
			}
			System.out.println();
			for(int k= 1; k <= i; k++)
				System.out.print("  ");
		}
		scan.close();

	}

}
