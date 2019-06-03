package question_1;

import java.util.Random;
import java.util.Scanner;

public class GyuessingGame {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int j = 5000000;
		final int l = 500000;
		
		
		int countd = 0;
		while(countd < 10) {
			int count = countd + 1;
			System.out.println("===============================================================================[Attempt" + count + "]" );
			System.out.println("Enter a number in the range 1-15 [predict the right number to win the raffle]");
			System.out.println("Laid money = GMD" + j);
			
			Random generator = new Random();
			int theNumber = generator.nextInt(15);
			
			int userInput = input.nextInt();
			
			if (theNumber == userInput) {
				System.out.println("HOORay!!! you have won yourself a mighty some of GMD" + j);
				System.out.println("Number of attempts " + count);
			}
			else {
				System.out.println("The Devil's number is " + theNumber + ", Sorry, Try Again. [Attempt " + count + "] Missed!, you have " + (10 - (count)) + "more!!!!");
				if (count == 9)
					System.out.println("LAST CHANCE!!");
				
			}
			countd++;
			j = j-l;
		}
		input.close();
			
		System.out.println("===============================================================================[No More Attempts for you]" );
		System.out.println("Your ticket is expired! you will have to buy another ticket to play again");
		System.out.println("Number of Attempts = 10");
			
	}

}

