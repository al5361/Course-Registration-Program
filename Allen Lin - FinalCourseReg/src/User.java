

import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class User implements java.io.Serializable {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	User (String un, String pw, String fn, String ln){
		this.username = un;
		this.password= pw;
		this.firstName = fn;
		this.lastName = ln;
	}
	public String getUser() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public String getfn() {
		return this.firstName;
	}
	public String getln() {
		return this.lastName;
	}
	//Admin methods, will not be allowed by students because students is not polymorphised
	public void deleteCourse(ArrayList <Course> list, ArrayList <Student> stulist) {}
	public void editCourse(ArrayList <Course> list) {}
	public void createStudent(ArrayList<Student> list) {}
	public void createCourse(ArrayList <Course> list) {}
	
}