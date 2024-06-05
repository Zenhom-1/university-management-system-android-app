package com.example.studentasu.DB;

import java.util.ArrayList;
import java.util.List;

public class Constant {

    private Constant() {}
    public static final String dbname = "ums.db";
    public static final String tableStudent = "student";
    public static final String Studentid = "_id";
    public static final String Studentname = "name";
    public static final String Studentmobile = "mobile";
    public static final String Studentemail = "email";
    public static final String Studentpassword = "password";
    public static final String Studentdepartment = "department";
    public static final String Studentlevel = "level";
    public static final String tableCourses = "courses";
    public static final String Courseid = "_id";
    public static final String Coursecode = "code";
    public static final String Coursename = "name";
    public static final String Coursesemester = "semester";
    public static final String Coursecredithour = "creditour";
    public static final String CourseDrname = "drname";
    public static final String Courselevel = "level";
    public static final String tableStudent_Courses = "studentcourses";
    public static final String Student_Courses_Courseid = "_idcourse";
    public static final String Student_Courses_Studentid = "_idstudent";
    public static final String create_student_query = "CREATE TABLE " + tableStudent + " (" +
            Studentid + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Studentname + " TEXT NOT NULL, "+
            Studentmobile + " TEXT NOT NULL, "+
            Studentemail + " TEXT NOT NULL, "+
            Studentdepartment + " TEXT NOT NULL, "+
            Studentlevel + " INTEGER NOT NULL, "+
            Studentpassword + " TEXT NOT NULL);";

    public static final String drop_student_table = "DROP TABLE IF EXIST " + tableStudent + ";";

    public static final String create_course_query = "CREATE TABLE " + tableCourses + " (" +
            Courseid + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Coursename + " TEXT NOT NULL, "+
            Coursecode + " TEXT NOT NULL, "+
            Coursesemester + " TEXT NOT NULL, "+
            CourseDrname + " TEXT NOT NULL, "+
            Coursecredithour + " INTEGER NOT NULL, "+
            Courselevel + " INTEGER NOT NULL);";

    public static final String drop_course_table = "DROP TABLE IF EXIST " + tableCourses + ";";

    public static final String create_student_course_query = "CREATE TABLE " + tableStudent_Courses + " (" +
            Student_Courses_Courseid + " INTEGER, " +
            Student_Courses_Studentid + " INTEGER, " +
            "PRIMARY KEY (" + Student_Courses_Courseid +
            ", " + Student_Courses_Studentid + ")," +
            "FOREIGN KEY (" + Student_Courses_Studentid + ") REFERENCES students (" +  Studentid + ")," +
            "FOREIGN KEY (" + Student_Courses_Courseid + ") REFERENCES students (" +  Courseid + "));";

    public static final String drop_student_course_table = "DROP TABLE IF EXIST " + tableStudent_Courses + ";";

    public static final Course[] courseData = new Course[] {
            new Course("Calculus1", "MATH101", "Essam El Saeedy", "one", 4, 1),
            new Course("Physics", "PHYS101", "Heba Abd El-Maqsoud", "one", 4, 1),
            new Course("Chemistry", "CHEM101", "Mohamed Emad", "one", 3, 1),
            new Course("Chemistry Lab", "CHEM103", "Aymen Ayoub", "one", 1, 1),
            new Course("Statistics", "STAT101", "Ayat El-Masry", "one", 3, 1),
            new Course("Safety", "SAFS101", "Abd EL-Rahman", "one", 1, 1),
            new Course("HumanRight", "HURI101", "Abd EL-Rahman", "one", 0, 1),
            new Course("Calculus2", "MATH102", "Hany El-Deeb", "two", 3, 1),
            new Course("Fundamental Concepts of Mathematics", "MATH104", "Manar El-Badry", "two", 3, 1),
            new Course("Introduction to computers", "COMP102", "Nashwa Abdelghaffar", "two", 3, 1),
            new Course("Computer Programming1", "COMP104", "Mohamed Fakhri", "two", 3, 1),
            new Course("Logic Design", "COMP106", "Nagla Reda", "two", 3, 1),
            new Course("English1", "ENGL102", "Enass", "two", 2, 1),
            new Course("Inco", "INCO102", "Dieaa Nassr", "two", 0, 1),
            new Course("Algorithm", "COMP201", "Neven Samy", "one", 3, 2),
            new Course("Computability", "COMP203", "Ghada Nour El-Dein", "one", 2, 2),
            new Course("OOP", "COMP205", "Dwalat Abd El-Aziz", "one", 3, 2),
            new Course("DataBase", "COMP207", "Wael Zakaria", "one", 4, 2),
            new Course("Linear Algebra", "MATH203", "Manar El-Badry", "one", 4, 2),
            new Course("English2", "ENGL201", "Enass", "one", 2, 2),
            new Course("Data structures", "COMP202", "Wael Zakaria", "two", 3, 2),
            new Course("Computer Networks", "COMP204", "Hatem", "two", 3, 2),
            new Course("Web Programming", "COMP206", "Nashwa Abdelghaffar", "two", 3, 2),
            new Course("Automata", "COMP208", "Azza Abd El-Rahman", "two", 3, 2),
            new Course("Graph", "COMP210", "Neven Samy", "two", 2, 2),
            new Course("Ordinary Differential Equations", "MATH202", "Adel Taha", "two", 3, 2),
            new Course("Java", "COMP301", "Nashwa Abdelghaffar", "one", 3, 3),
            new Course("Sentax", "COMP303", "Azza Abd El-Rahman", "one", 2, 3),
            new Course("Operating System", "COMP307", "Hatem", "one", 3, 3),
            new Course("MultiMedia", "COMP309", "Hussein Karem", "one", 2, 3),
            new Course("Complexity", "COMP305", "Neven Samy", "one", 3, 3),
            new Course("Abstract Algebra", "MATH333", "Ahmed Elsonbaty", "one", 3, 3),
            new Course("Scientific Thinking", "SCTH301", "Abd EL-Rahman", "one", 1, 3),
            new Course("Compiler", "comp 304", "Wael Zakaria", "two", 3, 3),
            new Course("Android", "COMP310", "Dieaa Nassr", "two", 2, 3),
            new Course("Advanced DataBase", "COMP314", "Wael Zakaria", "two", 2, 3),
            new Course("Combinatorics", "COMP302", "Neven Samy", "two", 2, 3),
            new Course("Cryptography", "COMP308", "Dieaa Nassr", "two", 3, 3),
            new Course("Graphics", "COMP306", "Hussein Karem", "two", 3, 3),
            new Course("Reserach Ethics", "ETHR302", "Mohamed Soliman", "two", 3, 3),
            new Course("Computitional Geometry", "COMP411", "Ghada Nour El-Dein", "one", 3, 4),
            new Course("Network Security", "COMP409", "Dieaa Nassr", "one", 3, 4),
            new Course("Image Processing", "COMP411", "Hwayda Lotfy", "one", 3, 4),
            new Course("Parallel", "COMP403", "Mohamed Fakhry", "one", 3, 4),
            new Course("AI", "COMP401", "Azza Abd El-Rahman", "one", 3, 4),
            new Course("Skill Work", "SKILL401", "Abd EL-Rahman", "one", 1, 4),
            new Course("Data Mining", "COMP416", "Dwalat Abd El-Aziz", "two", 3, 4),
            new Course("Advanced Ai", "COMP408", "Azza Abd El-Rahman", "two", 3, 4),
            new Course("Software Engineering", "COMP404", "Hussein Karem", "two", 3, 4),
            new Course("Bioinformatics", "COMP402", "Mohamed Fakhri", "two", 3, 4)
    };
}
