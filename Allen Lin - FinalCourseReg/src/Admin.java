
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

@SuppressWarnings("serial")
public class Admin extends User implements AdminControls{
	//prevents serialization from crashing
	transient Scanner input = new Scanner(System.in);

	Admin(String un, String pw, String fn, String ln){
		super(un,pw,fn,ln);
	}
@Override
	public void deleteCourse(ArrayList <Course> list, ArrayList <Student> stulist) {
		boolean a = true;
		//declare value to reference var to avoid nullpointerexception
		String courseID = "a";
		//place holders
		int section = -1;
		int index = -1;
			System.out.println("Enter Course ID and Section of Course to edit");
			System.out.println("Course ID?");
			courseID = input.nextLine();
			//place holder and catch for course section input
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
		for (int i=0; i<list.size();i++) {
			if (list.get(i).getCourseID().equals(courseID) && list.get(i).getSection()==section) {
				a = false;
				index = i;
			}
		}
		//stores index of deleted and deletes class from all student schedules
		//try catch for java.lang.ArrayIndexOutOfBoundsException
		try {
			for(int j=0;j<stulist.size();j++) {
				for(int k=0;k<stulist.get(j).getSchedule().size();k++) {
					if(stulist.get(j).getSchedule().get(k).getCourseID().contains(list.get(index).getCourseID())) {
						stulist.get(j).getSchedule().remove(stulist.get(j).getSchedule().get(k));
					}
				}
			}
		}
		catch(java.lang.ArrayIndexOutOfBoundsException e){}
		if (index != -1) {
			list.remove(index);
			System.out.println("Course deleted");
		}
		if (a)
			System.out.println("Did not find course/section combination");
	}
	@Override
	public void editCourse(ArrayList <Course> list) {
		boolean a = true;
		//place holders
		int index = -1;
		int holder = 1;
		while(holder == 1){
				System.out.println("Enter Course ID and Section of Course to edit");
				System.out.println("Course ID?");
				String courseID = input.nextLine();
				//place holder and catch for course section input
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
				for (int i=0; i<list.size();i++) {
					if (list.get(i).getCourseID().equals(courseID) && list.get(i).getSection()==section) {
						index = i;
						holder = 0;
					}
				}
				if (index == -1)
					System.out.println("Did not find course/section combination");
			}
		do {System.out.println("Actions:");
			System.out.println("1: Class Max Capacity");
			System.out.println("2: Class Instructor");
			System.out.println("3: Class Section #");
			System.out.println("4: Class Location");
			System.out.println("5: Back");
				String action = input.nextLine();
				switch(action) {
				case "1": System.out.println("Current Max Capacity: "+list.get(index).getCapacity());
					int cap = -1;
					do {
						try {
							System.out.println("New Max Capacity?");
								cap = input.nextInt();
								if (cap > 0)
									break;
						}
						catch(InputMismatchException e) {
							System.out.println("Invalid Entry");
							input.next();
						}
					}while(true);
					//bug fix
					input.nextLine();
					list.get(index).setCapacity(cap);
					break;
				case "2": System.out.println("Current Instructor: "+list.get(index).getInstructor());
					System.out.println("New Instructor? ");
					String instructor = input.nextLine();
					list.get(index).setInstructor(instructor);
					break;
				case "3": System.out.println("Current Section #: "+list.get(index).getSection());
					//place holder
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
					list.get(index).setSection(section);
					//bug fix
					input.nextLine();
					break;
				case "4": System.out.println("Current Location: "+list.get(index).getLocation());
					System.out.println("New Location? ");
					String z = input.nextLine();
					list.get(index).setLocation(z);
					break;
				case "5":
					a = false;
					break;
				default:System.out.println("Invalid Input");
					break;
				}		
		}while(a);
	}
	@Override
	public void createStudent(ArrayList<Student> list) {
		//declare value to reference var to avoid nullpointerexception
		String un = "a";
		String pw = "a";
		String fn = "a";
		String ln = "a";
		System.out.println("Student Username?");
		un = input.nextLine();
		System.out.println("Student Password?");
		pw = input.nextLine();
		System.out.println("Student First Name?");
		fn = input.nextLine();
		System.out.println("Student Last Name?");
		ln = input.nextLine();
		Student a = new Student(un,pw,fn,ln);
		//counter is used to count if the new student username matches any existing, +1 if it does not
		int counter =0;
		for(int i=0;i<list.size();i++) {
			if(un.equals(list.get(i).getUser()) == false)
				counter++;
		}
		//if username is unique then counter should equal the student list size
		if(counter == list.size()) {
			list.add(a);
			System.out.println("Student successfully enrolled");
		}	
		else {
			System.out.println("Username taken, please try again");
		}
	}

	@Override
	public void createCourse(ArrayList <Course> list) {
		//declare value to reference var to avoid nullpointerexception
		String cname = "a";
		String id = "a";
		System.out.println("Course Name?");
		cname = input.nextLine();
		System.out.println("Course ID?");
		id = input.nextLine();
		//-1 is place holder, loop forces a valid answer
		int cap = -1;
		do {
			try {
				System.out.println("Course Capacity?");
					cap = input.nextInt();
					//cannot be 0 or else loop continues until a valid input is entered
					if (cap > 0)
						break;
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid Entry");
				input.next();
			}
		}while(true);
		//closes input on scanner
		input.nextLine();
		System.out.println("Course Instructor Name?");
		String instructor = input.nextLine();
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
		System.out.println("Course Location?");
		String location = input.nextLine();
		//creates course with the inputs
		Course a = new Course(cname, id, cap, 0, instructor, section, location);
		list.add(a);
		System.out.println("Course Created");
	}


}