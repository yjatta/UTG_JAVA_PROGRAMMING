
public class OverlappingShapes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//RECTANGLE 1
		double u1_x = 0;
		double u1_y = 10;
		
		double b1_x = 10;
		double b1_y = 0;
		
		//RECTANGLE 2
		double u2_x = 5;
		double u2_y = 5;
		
		double b2_x = 15;
		double b2_y = 0;
		
	
		if(u1_x > b2_x || u2_x > b1_x )
			System.out.println("do not overlap");
		
		else if(u1_y < b2_y || u2_y < b1_y)
			System.out.println("do not Overlaps");
		else
			System.out.println("Overlaps");
	}

}
