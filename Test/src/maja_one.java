import java.util.Random;

public class maja_one {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rnd = new Random();
		double amount = 5000000.00;
		boolean found = false;
		int totalAttempts = 10;
		int attempt = 0;
		while ( amount > 0 && !found) {
			attempt++;
			int devilsNum = 1 + rnd.nextInt(15);
			
		}
	}
	
	public static void message(int att, int amt) {
		if ( att < 10 ) {
			System.out.println("=========================================== [ Attempt " + att + " ] ");
			System.out.println("Laid money = " + amt);
			
		} else {
			System.out.println("               LAST CHANCE!!!");
			System.out.println("=========================================== [ Attempt " + att + " ] ");
			System.out.println("Laid money = " + amt);
		}
	}
	
	public static void failMessage(int att, int dvNum) {
		int left = 10 - att;
		if ( att < 10 ) {
			System.out.println("The devils number is " + dvNum + ", sorry, try again. [ Attempt " + att + " ] Missed! you have " + left +" attemps more!!!");
		} else {
			System.out.println("The devils number is " + dvNum + ", sorry, cannot try again. [ Attempt " + att + " ] Missed!");
			System.out.println("============================================ [ No more attempts for you ]");
			System.out.println("Your tickets is expired! you'll have to buy another ticket to play again");
			System.out.println("Number of attempts = " + att);
		}
	}

}
