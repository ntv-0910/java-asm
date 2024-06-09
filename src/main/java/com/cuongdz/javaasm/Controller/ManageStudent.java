package com.cuongdz.javaasm.Controller;

import com.cuongdz.javaasm.Model.MySQLUtility;
import com.cuongdz.javaasm.Model.Student;
import com.cuongdz.javaasm.Model.UserIO;
import java.util.Date;

/**
 *
 * @author Cuongdz
 */
public class ManageStudent {

    public ManageStudent() {

    }

    // Method
    public boolean addStudent(Student st) {
        String fname = st.getFirstname().trim();
        String lname = st.getLastname().trim();
        String address = st.getAddress().trim();
        Date dob = st.getDob();
        byte gender = st.getGender();
        if (!UserIO.validate(fname, lname, dob, gender, address)) {
            return false;
        }
        // Add to database
        return MySQLUtility.addInfo(new Student(fname, lname, gender, dob, address)) >= 1;

    }

    public boolean setStudent(Student st, int id) {
        String fname = st.getFirstname().trim();
        String lname = st.getLastname().trim();
        String address = st.getAddress().trim();
        Date dob = st.getDob();

        byte gender = st.getGender();
        if (!UserIO.validate(fname, lname, dob, gender, address)) {
            return false;
        }
        // Add to database
        return MySQLUtility.setInfo(new Student(fname, lname, gender, dob, address), id) >= 1;

    }

    public boolean deleteStudent(int id) {
        // Delete student in every class they taken
        MySQLUtility.deleteBy("students_courses", "studentid", String.valueOf(id));
        // delete student info
        MySQLUtility.deleteBy("students", "studentid", String.valueOf(id));
        return true;
    }
}
