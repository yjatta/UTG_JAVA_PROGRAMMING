
public class Student implements person {
	private String Name;
	private String Email;
	private String Grade;
	
	
public Student (String name, String email, String grade) {
	this.Name = name;
	this.Email = email;
	this.Grade = grade;
}
public String getGrade() {
	return this.Grade;
}
public String getdescription() {
	this.Grade = "A C";
	return this.Grade +" grade Stduent";
}

public static void main(String[] args) {
	person student = new Lecturer("Yoromang Jatta", "yjatta@cayorenterprises.com", "A", "math");
	System.out.println(student.getdescription());
}
}