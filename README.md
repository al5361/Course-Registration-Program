# Course-Registration-Program
Simple implementation of a course registration system.

Java files are in SRC folder. Main class file is in Program.java.

There is a section dedicated to importing information from a csv (excel) file and if you would like to import your own class data, uncomment the part in the beginning of main and make sure to the csv file name matches the programs.




Some notes:
▪Method overriding (give at least two examples) 
Admin and Student Class methods overrode the AdminControls interface and StudentControls interface
▪Abstract Class
User class was the abstract class, was extended to Student and Admin classes. 
▪Inheritance
The User and Admin constructor inherits the Abstract Class data types (username, lastname, firstname, lastname) with the function super (username, lastname, firstname, lastname)
▪Polymorphism
Admin is instantiated by the function User admin1 = new Admin(parameters);
▪Encapsulation
All class variables are declared private. Setters and getters methods are used to retrieve and edit the values.
▪The concept of ADT (Abstract Data Types) 
The interfaces of Admincontrols and Studentcontrols declared the Admin and Student class methods but have not implemented it. This is ADT.
