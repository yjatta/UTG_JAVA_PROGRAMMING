
public class Employee implements person{
	private String Department;
	private String Lecturer;
	private String Name;
	private String Email;
	private String Grade;
	
	public Employee(String department,String lecturer,String name, String email, String grade) {
		this.Department=department;
		this.Lecturer=lecturer;
		this.Name=name;
		this.Email=email;
		this.Grade=grade;
		
		
	}

	@Override
	public String getdescription() {
		Employee emp=new Employee("ict" ,"lect", "ida manneh","idamanneh91@gmail.com","A");
		
		return this.Department;

		
		
	}

}

