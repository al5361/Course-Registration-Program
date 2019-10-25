
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

@SuppressWarnings("serial")
public class Student extends User implements StudentControls{
	private ArrayList <Course> schedule;
	transient Scanner input;
	//constructor, super calls user constructor
	Student(String un, String pw, String fn, String ln){
		super(un, pw, fn, ln);
		schedule = new ArrayList<Course>();
	}
	
	//runs through student schedule object and prints names
	@Override
	public void printSchedule() {
		for (int i = 0; i<this.schedule.size(); i++) {
			System.out.println(this.schedule.get(i).getCourseName());
		}
	}
	//returns schedule list
	public ArrayList<Course> getSchedule() {
		return this.schedule;
	}
	
	
	//Register in a course (in this case the student must enter the course name, section, and
	//student full name, the name will be added to the appropriate course)
	@Override
	public void registerCourse(ArrayList<Course> arraylist) {
		input = new Scanner(System.in);
		//for else condition
		boolean a = true;
		System.out.println("Course ID?");
		//declare value to reference var to avoid nullpointerexception
		String course = "a";
		course = input.nextLine();
		//section is int, this is used throughout program to avoid input errors
		int section;
		do {
			System.out.println("Course Section?");
			if (input.hasNextInt()) {
				section = input.nextInt();
			}
	        else {
	        	System.out.println("Enter an Integer Please");
	            input.next();
	            continue;
	        }
			break;
		}while(true);
		input.nextLine();
		//Did not include input for first name and last name because 
		//student object includes data already
		for (int i=0; i<arraylist.size(); i++) {
			//condition 1:input course matches course in array //condition 2:input section matches course section// condition 3: class is not full // condition 4 student is not enrolled in the class //condition 5 student schedule does not contain course
			if (course.equals(arraylist.get(i).getCourseID())&&section==arraylist.get(i).getSection()&&arraylist.get(i).getCurrentSize()/arraylist.get(i).getCapacity() < 1 && (arraylist.get(i).studentList.contains(this.getfn()+" "+this.getln()) == false) && (this.schedule.contains(arraylist.get(i)) == false)) {
				//does not print out else statement outside for loop
				a = false;
				//add students full name to roster
				arraylist.get(i).studentList.add(this.getfn() +" "+this.getln());
				//add course to student schedule
				this.schedule.add(arraylist.get(i));
				//update roster size by +1
				arraylist.get(i).setCurrentSize(arraylist.get(i).getCurrentSize()+1);
				System.out.println("Class added");
				break;
			}
		}
		if(a) 
			System.out.println("Could not find Course and Section, Class Size was full, or student is already enrolled");
	}
	
	//4. Withdraw from a course (in this case the student will be asked to enter her/his name and
	//the course name, then the name of the student will be taken off from the given course’s list)
	@Override
	public void withdrawCourse(ArrayList<Course> arraylist, ArrayList<Student> stulist) {
		input = new Scanner(System.in);
		int index = -1;
		boolean b = true;
		System.out.println("Course ID?");
		//declare value to reference var to avoid nullpointerexception
		String course = "a";
		course = input.nextLine();
		//section is int, loops until proper input
		int section;
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
		//closes nextInt
		input.nextLine();
		//Did not include input for first name and last name because 
		//student object includes data already
		for (int i=0; i<arraylist.size(); i++) {
			//the course name, then the name of the student will be taken off from the given course’s list)
			if (course.equals(arraylist.get(i).getCourseID()) && section==arraylist.get(i).getSection() && (arraylist.get(i).studentList.contains(this.getfn()+" "+this.getln()))) {
				//does not print out "Could not find course"
				index = i;
				//update roster size by -1
				arraylist.get(i).setCurrentSize(arraylist.get(i).getCurrentSize()-1);
				//remove students full name to roster
				arraylist.get(i).studentList.remove(this.getfn() +" "+this.getln());
				//remove course from student schedule
				for (int j = 0; j < this.schedule.size(); j++) {
					if (this.schedule.get(j).getCourseID().equals(arraylist.get(index).getCourseID())){
						b = false;
						this.schedule.remove(this.getSchedule().get(j));
						System.out.println("Course withdrawed");
						break;
					}
				}
			}
		}
		//else statement determined by boolean b
		if(b) {
			System.out.println("Could not find Course and Section or you are not on the class roster");
		}
		}
}