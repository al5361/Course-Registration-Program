

import java.util.ArrayList;

public interface AdminControls {
	void createCourse(ArrayList<Course> list);
	void editCourse(ArrayList<Course> list);
	void createStudent(ArrayList<Student> list);
	void deleteCourse(ArrayList <Course> list, ArrayList <Student> stulist);
}
