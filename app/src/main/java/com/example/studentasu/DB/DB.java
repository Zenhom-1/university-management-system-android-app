package com.example.studentasu.DB;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.studentasu.Courses;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper
{
    private Context context;

    public DB(@Nullable Context context)
    {
        super(context, Constant.dbname, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constant.create_student_query);
        db.execSQL(Constant.create_course_query);
        db.execSQL(Constant.create_student_course_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constant.drop_student_table);
        db.execSQL(Constant.drop_course_table);
        db.execSQL(Constant.drop_student_course_table);
        onCreate(db);
    }

    public boolean insertCoursesData() {
        SQLiteDatabase db = this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("database_shared_preferences", Context.MODE_PRIVATE);
        Course[] courses = Constant.courseData;

        if (!sharedPreferences.getBoolean("is_data_inserted", false))
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putBoolean("is_data_inserted" , true);
            editor.apply();

             for(Course course : courses) {
                 ContentValues values = new ContentValues();

                 values.put(Constant.Coursename, course.getName());
                 values.put(Constant.Coursecode, course.getCode());
                 values.put(Constant.Coursesemester, course.getSemester());
                 values.put(Constant.CourseDrname, course.getDrname());
                 values.put(Constant.Courselevel, course.getLevel());
                 values.put(Constant.Coursecredithour, course.getCredithour());

                 long result = db.insert(Constant.tableCourses, null, values);
                 if (result == -1) {
                     return false;
                 }
             }
        }
        return true;
    }

    public boolean addStudent(long id,String username, String email, String password, String mobile, String department, int level) {
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constant.Studentid, id);
        values.put(Constant.Studentname, username);
        values.put(Constant.Studentemail, email);
        values.put(Constant.Studentpassword, password);
        values.put(Constant.Studentmobile, mobile);
        values.put(Constant.Studentdepartment, department);
        values.put(Constant.Studentlevel, level);

        long result = db.insert(Constant.tableStudent, null, values);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public Student getStudent() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + Constant.tableStudent;

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        if (cursor != null) {
            cursor.moveToFirst();
        } else {
            return null;
        }

        return new Student(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5),
                cursor.getString(6)
        );
    }

    public int getStudentLevel() {
        SQLiteDatabase db = this.getReadableDatabase();
        int studentLevel = -1; // Default to -1 to indicate an error or not found

        String query = "SELECT " + Constant.Studentlevel + " FROM " + Constant.tableStudent;
        Cursor cursor = db.rawQuery(query, null); // Pass null if there are no selection arguments


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                studentLevel = cursor.getInt(0);
            }
            cursor.close(); // Close the cursor to avoid memory leaks
        }
        db.close(); // Close the database as well

        return studentLevel;
    }


    public String[] getCoursesByStudentLevel(int level, String semester) {
        SQLiteDatabase db = this.getReadableDatabase();


        String query = "SELECT " + Constant.Coursename +
                " FROM " + Constant.tableCourses +
                " WHERE " + Constant.Courselevel +
                " = " + level + " AND " + Constant.Coursesemester +
                " = '" + semester + "';";

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        if (cursor != null) {
            String[] courses =  new String[cursor.getCount() + 1];
            int i = 1;
            courses[0] = "Select Course";

            while(cursor.moveToNext()) {
                courses[i] = cursor.getString(0);
                i += 1;
            }
            return courses;
        }
        return null;
    }

    public boolean add_studentcourse(String courseName) {
        SQLiteDatabase db = this.getWritableDatabase();
        long studentId = this.getStudentId();
        int courseId = this.getCourseIdByName(courseName);

        if(courseId == -1 || studentId == -1) {
            return false;
        }

        ContentValues values = new ContentValues();

        values.put(Constant.Student_Courses_Studentid, studentId);
        values.put(Constant.Student_Courses_Courseid, courseId);

        long res = db.insert(Constant.tableStudent_Courses, null, values);

        return res != -1;
    }

    public long getStudentId() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + Constant.Studentid + " FROM "+ Constant.tableStudent;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            return cursor.getLong(0);
        }
        return -1;
    }


    public int getCourseIdByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + Constant.Courseid + " FROM "+
                Constant.tableCourses + " WHERE " + Constant.Coursename +
                " = '" + name + "';";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            return cursor.getInt(0);
        }
        return -1;
    }

    public List<Course> getCoursesByStudent()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Constant.tableCourses +
                " INNER JOIN " + Constant.tableStudent_Courses + " ON " + Constant.Courseid + " = " +
                Constant.Student_Courses_Courseid + ";";

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        if (cursor != null) {
            List<Course>  courses =  new ArrayList<>();
            while(cursor.moveToNext()) {
                courses.add( new Course(
                        cursor.getString(1),cursor.getString(2),
                        cursor.getString(4),cursor.getString(3),
                        cursor.getInt(5),cursor.getInt(6)
                ));
            }
            return courses;
        }
        return null;
    }

    public Cursor getStudentData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Constant.tableStudent + ";";

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateDate(long id, String username, String mobile, String department, int level)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constant.Studentid, id);
        values.put(Constant.Studentname, username);
        values.put(Constant.Studentmobile, mobile);
        values.put(Constant.Studentdepartment, department);
        values.put(Constant.Studentlevel, level);

        long result = db.update(Constant.tableStudent, values, null,null);
        if(result != -1)
        {
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
        }

    }
    public void deleteCourseforStudent(String name)
    {
        long id = getCourseIdByName(name);
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(Constant.tableStudent_Courses,
                Constant.Student_Courses_Courseid + "=?", new String[]{String.valueOf(id)});
        if(result == -1)
        {
            Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Delete Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void logOut()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result1 = db.delete(Constant.tableStudent, null,null);
        long result2 = db.delete(Constant.tableStudent_Courses, null,null);
        if(result1 == -1 && result2 == -1)
        {
            Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Delete Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}