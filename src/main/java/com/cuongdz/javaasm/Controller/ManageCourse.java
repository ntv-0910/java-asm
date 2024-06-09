package com.cuongdz.javaasm.Controller;

import com.cuongdz.javaasm.Model.Course;
import com.cuongdz.javaasm.Model.MySQLUtility;
import com.cuongdz.javaasm.Model.UserIO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Cuongdz
 */
public class ManageCourse {

    public static final int MAX_TEACHER_COURSES = 5;
    public static final int MAX_STUDENT_COURSES = 30;
    public static final int MAX_STUDENT_IN_CLASS = 40;

    // Constructor
    public ManageCourse() {
    }

    // Method
    public boolean addCourse(Course c) {
        String Id = c.getId().trim();
        String Name = c.getName().trim();
        double Fee = c.getFee();

        // TODO: Validation
        // Check ID duplication
        try {
            if (MySQLUtility.getTable("courses", "courseID", Id).next()) {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
        if (UserIO.hasSpecicalChar(Id) || Id.length() > 8) {
            return false;
        }
        if (UserIO.hasSpecicalChar(Name) || Name.length() > 200) {
            return false;
        }
        if (Name.length() == 0) {
            return false;
        }
        if (Fee < 0) {
            return false;
        }

        // TODO: Add to server 
        MySQLUtility.addCourse(new Course(Id, Name, Fee,c.getMajorid()));
        return true;
    }

    public boolean setCourse(Course c, String id) {
        String Id = c.getId().trim();
        String Name = c.getName().trim();
        double Fee = c.getFee();
        // TODO: Validation
        if (UserIO.hasSpecicalChar(Name) || Name.length() > 200) {
            System.err.println("1");
            return false;
        }
        if (Name.length() == 0) {
            System.err.println("2");

            return false;
        }
        if (Fee < 0) {
            System.err.println("3");

            return false;
        }

        // TODO: Add to server 
        MySQLUtility.setCourse(new Course(Id, Name, Fee,c.getMajorid()), id);
        return true;
    }

    public void deleteCourse(String id) {
        // remove student from class
        MySQLUtility.deleteBy("students_courses", "courseID", id);
        // remove class teach course
        MySQLUtility.deleteBy("teachers_courses", "courseID", id);
        // remove course
        MySQLUtility.deleteBy("courses", "courseID", id);
    }

    public boolean isTeacherLimitCourse(int teacherid) {
        return MySQLUtility.getNumberCoursesTaken("teacher", teacherid) >= MAX_TEACHER_COURSES;
    }

    public boolean isClassEnoughSpace(int classid) {
        return MySQLUtility.getNumberStudentInClass(classid) <= MAX_STUDENT_COURSES;
    }

    public boolean addClass(int teacherid, String courseID) {
        if (isTeacherLimitCourse(teacherid)) {
            return false;
        }
        // Get current date and convert to string
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return MySQLUtility.addClass(courseID, teacherid, date) > 0;
    }

    public void deleteClass(int classid) {
        MySQLUtility.deleteBy("students_courses", "TCID", String.valueOf(classid));
        MySQLUtility.deleteBy("teachers_courses", "TCID", String.valueOf(classid));
    }

    public void removeStudent(int classid, int studentid) {
        MySQLUtility.deleteBy("students_courses", "TCID", String.valueOf(classid), "studentid", String.valueOf(studentid));
    }
}
