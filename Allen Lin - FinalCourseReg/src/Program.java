//Please run with all files intact. CSV directory is not updated to work on
//graders computer,thus a serialized file exists
//database has only default courses from csv file and no students
//Admin un = Admin //Admin pw = Admin001
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SuppressWarnings("serial")
public class Program implements java.io.Serializable{
	private transient static Scanner input;
	@SuppressWarnings("unchecked")
	public static void main (String[] args) {
		//Already serialized
		//import csv into course.list
/*
		String[][] csvcourses = new String[29][7];
	    String line;
		int x = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("/Users/allen01pd2017/desktop/FinalCourseReg/src/MyUniversityCourses.csv"))) {
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        for(int y = 0; y < values.length;y++) {
			        csvcourses[x][y] = values[y];
		        }
		    x++;
		    }
		} catch (FileNotFoundException e) {
			System.out.print("FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.print("IOException");
			e.printStackTrace();
		}
	ArrayList <Course> allCourses = new ArrayList<>();
	ArrayList <Student> allStudents = new ArrayList<>();
	for (int row = 0;row<csvcourses.length; row++) {
		Course a = new Course(csvcourses[row][0],csvcourses[row][1],Integer.parseInt(csvcourses[row][2]),Integer.parseInt(csvcourses[row][3]),csvcourses[row][4],Integer.parseInt(csvcourses[row][5]),csvcourses[row][6]);
		allCourses.add(a);
	}
*/
		//declare course array
		ArrayList <Student> allStudents = null;
		ArrayList <Course> allCourses = null;
		try{
			  //FileInputSystem recieves bytes from a file
		      FileInputStream fis = new FileInputStream("Courselist.ser");
		      
		      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
		      ObjectInputStream ois = new ObjectInputStream(fis);
		      
		      //Cast as course array. readObject will take the object from ObjectInputStream
		      allCourses = (ArrayList <Course>)ois.readObject();
		      ois.close();
		      fis.close();
		    }
		    catch(IOException ioe) {
		       ioe.printStackTrace();
		       return;
		    }
		 catch(ClassNotFoundException cnfe) {
		       cnfe.printStackTrace();
		       return;
		     }
		//import student array list, already declared in Student method
		try{
			  //FileInputSystem recieves bytes from a file
		      FileInputStream fis1 = new FileInputStream("Studentlist.ser");
		      
		      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
		      ObjectInputStream ois1 = new ObjectInputStream(fis1);
		      
		      //Cast as Employee. readObject will take the object from ObjectInputStream
		      allStudents = (ArrayList <Student>)ois1.readObject();
		      ois1.close();
		      fis1.close();
		    }
		    catch(IOException ioe) {
		       ioe.printStackTrace();
		       return;
		    }
		 catch(ClassNotFoundException cnfe) {
		       cnfe.printStackTrace();
		       return;
		     }
		
		//Default Admin
		User admin1 = new Admin("Admin","Admin001", "Admin", "Main");
		//User or Admin
		System.out.println("User or Admin?");
		input = new Scanner(System.in);
		String userType = input.nextLine();
		//boolean = false if either user or admin logs in
		//else prints enter valid input (user or admin)
		boolean userInputerror = true;
		if (userType.equalsIgnoreCase("admin")) {
			userInputerror = false;
			System.out.print("Username: ");
			String username = input.nextLine();
			System.out.print("Password: ");
			String password = input.nextLine();
			//if user info is correct, displays admin menu
			if (username.equals(admin1.getUser()) && password.equals(admin1.getPassword())){
				System.out.println("Enter 1 for Course Management Actions, 2 for Course Report Actions");
				//mainAction holds values 1,2 - gives course management menu or course reports
				int mainAction = input.nextInt();
				input.nextLine();
				//used to exit program, boolean becomes false when exit action is performed
				boolean menu1 = true;
				boolean menu2 = true;
				//course management menu
				if (mainAction == 1) {
					do {
						System.out.println("Actions: Enter an action number");
						System.out.println("1: Create new course");
						System.out.println("2: Delete a course");
						System.out.println("3: Edit a course");
						System.out.println("4: Display information for a given course");
						System.out.println("5: Register a student");
						System.out.println("6: Exit and save");
						String action1 = input.nextLine();
						switch(action1) {
						case "1": admin1.createCourse(allCourses);
							break;
						case "2":System.out.print("All existing courses");
							Course.printCourse(allCourses);
							admin1.deleteCourse(allCourses,allStudents);
							break;
						case "3": Course.printCourse(allCourses);
							admin1.editCourse(allCourses);
							break;
						case "4": Course.printSingleCourse(allCourses);
							break;
						case "5": admin1.createStudent(allStudents);
							break;
						case "6": menu1 = false;
							break;
						default: 
							System.out.println("Please enter a valid action");
							break;
						}
					}while(menu1);
				}
				//course report menu
				if (mainAction == 2) {
					do {
						System.out.println("Actions: Enter an action number");
						System.out.println("1: View all Courses");
						System.out.println("2: View all Full Courses");
						System.out.println("3: Update Full Courses to text file");
						System.out.println("4: View Students in specific course");
						System.out.println("5: View all courses of a specific student");
						System.out.println("6: Sort courses by number of enrolled students");
						System.out.println("7: Exit and Save");
						String action2 = input.nextLine();
						switch(action2) {
						case "1": Course.printCourse(allCourses);
							break;
						case "2": Course.printFullCourses(allCourses);
							break;
						case "3": 
							//creates a text file with course names of full classes
							PrintWriter writer;
							try {
								writer = new PrintWriter("fullcourses.txt", "UTF-8");
								for (int i=0; i<allCourses.size(); i++) {
									if (allCourses.get(i).getCurrentSize()/allCourses.get(i).getCapacity() == 1) {
										//returns coursename
										writer.println("Course Name: " + allCourses.get(i).getCourseName());
										System.out.println("Sucessfully exported to txt file");
									}
								}
								writer.close();
							} catch (FileNotFoundException e) {
								System.out.println(e);
								e.printStackTrace();
							} catch (UnsupportedEncodingException e) {
								System.out.println(e);
								e.printStackTrace();
							}
							break;
						case "4": Course.printCourseRoster(allCourses);
							break;
						case "5": Course.printStudentSchedule(allStudents);
							break;
						case"6": Course.SortCourses(allCourses);
							Course.printCourse(allCourses);
							System.out.println("Sorted");
							System.out.println();
							break;
						case"7": menu2=false;
							break;
						default: System.out.println("Please enter a valid action");
							break;
						}
					}while(menu2);
				}
			}
			//If login is incorrect, system shuts down without saving
			else {
				System.out.println("Invalid Username or Password");
				System.exit(0);
			}
		}
		if (userType.equalsIgnoreCase("user")) {
			//used to exit student menu and serialize
			boolean menu3 = true;
			//used for error message of inputting user or admin
			userInputerror = false;
			System.out.print("Username: ");
			String un = input.nextLine();
			System.out.print("Password: ");
			String pw = input.nextLine();
			//place holder for index
			int index = -1;
			for(int i=0;i<allStudents.size();i++) {
				if (un.equals(allStudents.get(i).getUser()) && pw.equals(allStudents.get(i).getPassword()))
				//index of user
				index = i;
			}
			try {
				if (un.equals(allStudents.get(index).getUser()) && pw.equals(allStudents.get(index).getPassword())){
					System.out.println("Welcome " + allStudents.get(index).getfn()+" "+allStudents.get(index).getln());
					do {
						//index used to find student inside studentlist 
						//in order to use a instance method of Student
						System.out.println("Actions: Enter an action number");
						System.out.println("1: View all courses");
						System.out.println("2: View all open courses");
						System.out.println("3: Register a course");
						System.out.println("4: Withdraw from a course");
						System.out.println("5: View Schedule");
						System.out.println("6: Exit and save");
						String action3 = input.next();
						switch(action3) {
						case "1": Course.printCourse(allCourses);
							break;
						case "2": Course.printOpenCourses(allCourses);
							break;
						case "3":
							allStudents.get(index).registerCourse(allCourses);
							break;
						case "4": 
							//searches through course and student list, finds student by index, and then deletes courses
							allStudents.get(index).withdrawCourse(allCourses,allStudents);
							break;
						case "5": 
							//prints schedule
							if(allStudents.get(index).getSchedule().size() > 0) {
								System.out.println("Student Schedule: ");
								System.out.println();
								allStudents.get(index).printSchedule();
								System.out.println();
							}
							else
								System.out.println("No enrolled classes");
							break;
						case"6": menu3 = false;
							break;
						default: System.out.println("Please enter a valid action");
							break;
						}
					}while(menu3);
				}
			}
			catch(java.lang.ArrayIndexOutOfBoundsException e){
				System.out.println("Invalid Username or Password");
				System.exit(0);
			}

		}
		//pops up when user or admin is not inputted in the beginning, system does not save
		if(userInputerror) {
			System.out.println("Enter Valid Input Please");
			System.exit(0);
		}
	//Serialization
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("Courselist.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//Writes the specific object to the OOS
			oos.writeObject(allCourses);
			
			//Close both streams
			oos.close();
			fos.close();
			System.out.println("Database saved");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("Studentlist.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//Writes the specific object to the OOS
			oos.writeObject(allStudents);
			
			//Close both streams
			oos.close();
			fos.close();
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		//Not necessary to serialize Admin object because it is static and fixed object
		/*
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("Admin.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//Writes the specific object to the OOS
			oos.writeObject(admin1);
			
			//Close both streams
			oos.close();
			fos.close();
			System.out.println("Serialization of admin complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		*/
	}
}