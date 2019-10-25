
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Course implements java.io.Serializable{
	//instance variables that all courses have
	private String courseName;
	private String courseID;
	private int maxStudents;
	private int currentStudents;
	private String instructor;
	private int courseSection;
	private String courseLocation;
	ArrayList <String> studentList;
	transient static Scanner input = new Scanner(System.in);
	//course constructor
	Course(String name, String id, int size, int currentsize, String instructor, int section, String location){
		this.courseName = name;
		this.courseID = id;
		this.maxStudents = size;
		this.currentStudents = currentsize;
		this.courseSection = section;
		this.instructor = instructor;
		this.courseLocation = location;
		this.studentList = new ArrayList<>();
	}
	//print all courses
	public static void printCourse(ArrayList <Course> arraylist) {
		for (int i=0; i<arraylist.size(); i++) {
			//returns coursename
			System.out.println("Course Name: " + arraylist.get(i).getCourseName());
			//returns courseID
			System.out.println("Course ID: " + arraylist.get(i).getCourseID());
			//returns capacity
			System.out.println("Max Capacity: " + arraylist.get(i).getCapacity());
			//returns currentsize
			System.out.println("Current Capacity: " + arraylist.get(i).getCurrentSize());
			//returns instructor
			System.out.println("Instructor: " + arraylist.get(i).getInstructor());
			//returns section
			System.out.println("Section: " + arraylist.get(i).getSection());
			//returns location
			System.out.println("Location: " + arraylist.get(i).getLocation());
			System.out.println();
		}
	}
	//print a single course w/ roster
	public static void printSingleCourse(ArrayList <Course> arraylist) {
		System.out.println("Enter a Course ID");
		String courseId = input.nextLine();
		//index is used to store i value of course in course array
		int index = -1;
		//holder value for section
		int section = -1;
		do {
			try {
				System.out.println("Course Section?");
				section = input.nextInt();
				if (section >= 0)
					break;
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid Entry");
				input.next();
			}
		}while(true);
		input.nextLine();
		for (int i=0; i<arraylist.size(); i++) {
			if (arraylist.get(i).getCourseID().equals(courseId)&& arraylist.get(i).getSection()==section){
				index = i;
			}
		}
		if (index != -1 && index <= (arraylist.size() -1) ) {
			//returns coursename
			System.out.println("Course Name: " + arraylist.get(index).getCourseName());
			//returns courseID
			System.out.println("Course ID: " + arraylist.get(index).getCourseID());
			//returns capacity
			System.out.println("Max Capacity: " + arraylist.get(index).getCapacity());
			//returns currentsize
			System.out.println("Current Capacity: " + arraylist.get(index).getCurrentSize());
			//returns instructor
			System.out.println("Instructor: " + arraylist.get(index).getInstructor());
			//returns section
			System.out.println("Section: " + arraylist.get(index).getSection());
			//returns location
			System.out.println("Location: " + arraylist.get(index).getLocation());
			if(arraylist.get(index).getCurrentSize() > 0) {
				System.out.print("Student Roster: ");
				arraylist.get(index).getRoster();
			}
			else
				System.out.println("No Students Enrolled");
			System.out.println();
		}
		else
			System.out.println("Could not find course ID");
	}
	//print roster only of single course
	public static void printCourseRoster(ArrayList <Course> arraylist) {
		input = new Scanner(System.in);
		System.out.println("Enter a Course ID");
		String courseId = input.nextLine();
		int index = -1;
		for (int i=0; i<arraylist.size(); i++) {
			if (arraylist.get(i).getCourseID().equals(courseId)){
				index = i;
			}
		}
		if (index != -1 && index <= (arraylist.size() -1) ) {
			System.out.print("   Student Roster: ");
			arraylist.get(index).getRoster();
		}
		else
			System.out.println("Could not find course ID");
	}
	//print student schedule
	//input would be allstudents array, which includes all students in database
	public static void printStudentSchedule(ArrayList <Student> arraylist) {
		System.out.println("Enter Student's First Name");
		String fn = input.nextLine();
		System.out.println("Enter Student's Last Name");
		String ln = input.nextLine();
		int index = -1;
		for (int i=0; i<arraylist.size(); i++) {
			if (arraylist.get(i).getfn().equals(fn)&& arraylist.get(i).getln().equals(ln)){
				index = i;
			}
		}
		//index changes when method successfully runs, if it did not change then output below will not be printed 
		if (index != -1 && index <= (arraylist.size() -1) ) {
			if(arraylist.get(index).getSchedule().size() > 0) {
			System.out.println("Student Schedule: ");
			System.out.println("-----------------");
			arraylist.get(index).printSchedule();
			System.out.println("");
			}
			else
				System.out.println("No enrolled classes");
		}
		else
			System.out.println("Could not find User");
	}
	//print full courses
	public static void printFullCourses(ArrayList <Course> arraylist) {
		for (int i=0; i<arraylist.size(); i++) {
			if (arraylist.get(i).getCurrentSize()/arraylist.get(i).getCapacity() == 1) {
				//returns coursename
				System.out.println("Course Name: " + arraylist.get(i).getCourseName());
				//returns courseID
				System.out.println("Course ID: " + arraylist.get(i).getCourseID());
				//returns capacity
				System.out.println("Max Capacity: " + arraylist.get(i).getCapacity());
				//returns currentsize
				System.out.println("Current Capacity: " + arraylist.get(i).getCurrentSize());
				//returns instructor
				System.out.println("Instructor: " + arraylist.get(i).getInstructor());
				//returns section
				System.out.println("Section: " + arraylist.get(i).getSection());
				//returns location
				System.out.println("Location: " + arraylist.get(i).getLocation());
				System.out.println();
			}
		}
	}
	// print open courses
	public static void printOpenCourses(ArrayList <Course> arraylist) {
		for (int i=0; i<arraylist.size(); i++) {
			if (arraylist.get(i).getCurrentSize()/arraylist.get(i).getCapacity() < 1) {
				//returns coursename
				System.out.println("Course Name: " + arraylist.get(i).getCourseName());
				//returns courseID
				System.out.println("Course ID: " + arraylist.get(i).getCourseID());
				//returns capacity
				System.out.println("Max Capacity: " + arraylist.get(i).getCapacity());
				//returns currentsize
				System.out.println("Current Capacity: " + arraylist.get(i).getCurrentSize());
				//returns instructor
				System.out.println("Instructor: " + arraylist.get(i).getInstructor());
				//returns section
				System.out.println("Section: " + arraylist.get(i).getSection());
				//returns location
				System.out.println("Location: " + arraylist.get(i).getLocation());
				System.out.println();
			}
		}
	}
	//sort courses by #of students enrolled
	public static void SortCourses(ArrayList <Course> arraylist) {
		for (int i=0; i<arraylist.size(); i++) {
			for (int j = i + 1; j <arraylist.size(); j++) {
				if (arraylist.get(i).getCurrentSize() > arraylist.get(j).getCurrentSize()) {
					//temporary course with place holder values
					Course temp = new Course("a", "a", i, i, "a", i, "a");
					temp = arraylist.get(i);
					arraylist.set(i, arraylist.get(j));
					arraylist.set(j, temp);
				}
			}
		}
	}
	//setters
	void setCapacity(int i) {
		//capacity must be larger than 0
		if (i >= this.studentList.size() && i > 0)
			this.maxStudents = i;
	}
	void setInstructor(String i) {
		this.instructor = i;
	}
	void setSection (int i) {
		this.courseSection = i;
	}
	void setLocation (String i) {
		this.courseLocation = i;
	}
	void setCurrentSize (int i) {
		this.currentStudents = i;
	}
	
	
	//getters
	String getCourseName() {
		return this.courseName;
	}
	String getCourseID() {
		return this.courseID;
	}
	int getCapacity() {
		return this.maxStudents;
	}
	int getCurrentSize() {
		return this.currentStudents;
	}
	String getInstructor() {
		return this.instructor;
	}
	int getSection() {
		return this.courseSection;
	}
	String getLocation() {
		return this.courseLocation;
	}
	void getRoster() {
		System.out.println(this.studentList.size());
		for (int i = 0; i<this.studentList.size(); i++) {
			String a = this.studentList.get(i);
			System.out.println(a);
		}
	}
}