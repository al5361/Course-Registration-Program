

import java.util.ArrayList;

public interface StudentControls {
	ArrayList<Course> getSchedule();
	void registerCourse(ArrayList<Course> arraylist);
	void printSchedule();
	void withdrawCourse(ArrayList<Course> arraylist, ArrayList<Student> stulist);
}
