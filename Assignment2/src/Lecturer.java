
public class Lecturer implements person{
	private String Name;
	private String Email;
	private String Grade;
	private String Subject;
	public Lecturer(String name,String email, String grade, String subject) {
		this.Name = name;
		this.Email = email;
		this.Grade = grade;
		this.Subject = subject;
		
	}
	public String getSubject(String getSubject){
		return Subject;
		
	}
	
	@Override
	public String getdescription() {
		this.Subject="teaches " + this.Subject;
		return this.Subject;
	}


}
