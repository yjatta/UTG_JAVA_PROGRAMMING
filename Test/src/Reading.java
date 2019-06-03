import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reading {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		File file = new File("/home/cayor/eclipse-workspace/Test/src/data.json");
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()) {
			System.out.println(scan.nextLine());
		}
		scan.close();
	}

}
