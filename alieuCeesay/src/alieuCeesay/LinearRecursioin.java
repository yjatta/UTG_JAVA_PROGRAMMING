package alieuCeesay;

public class LinearRecursioin {
	int  MAX_SIZE = 45;
	
	
	public int [] populateArray(int [] a) {
		
		for (int i = 0; i < a.length; i++) {
			a[i] = i *2;
		}
		
		return a;
	}
	
	public int recsearch(int [] a, int lenght, int key) {
		
		if (a[lenght] == key) {
			return lenght;
		}
		
		else if(lenght < 0)
			return -1;
		
		return recsearch(a, lenght -1, key);
		
	}
	
	public void printArray(int [] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] +" ");
		}
	}
	

}
