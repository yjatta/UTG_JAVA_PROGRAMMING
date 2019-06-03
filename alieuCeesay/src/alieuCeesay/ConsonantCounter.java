package alieuCeesay;

public class ConsonantCounter {
 private  final char [] vowels =  new char[] {'a','e','i','o','u'};
 private  int count = 0;
 
 public  boolean isFound( char a) {
	 for (int i = 0; i < vowels.length; i++) {
		 
		 if (vowels[i] == Character.toLowerCase(a)) 
			 return true;
		 
		
	}
	 return false;
	 
 }
 
 public   int counter(String name) {
	 
	 if(name.length() == 0) //base
		 return count;
	 
	 if ( ! isFound(name.charAt(name.length()-1)))
			 count ++;
	 
	 return counter(name.substring(0, name.length()- 1));
	 
	
			 
			 
 }
	
}
