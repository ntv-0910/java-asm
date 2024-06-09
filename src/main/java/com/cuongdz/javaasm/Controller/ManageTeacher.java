package com.cuongdz.javaasm.Controller;

import com.cuongdz.javaasm.Model.MySQLUtility;
import com.cuongdz.javaasm.Model.Teacher;
import com.cuongdz.javaasm.Model.UserIO;
import java.util.Date;

/**
 *
 * @author Cuongdz
 */
public class ManageTeacher {

    public ManageTeacher() {

    }

    // Method
    public boolean addTeacher(Teacher tc) {
        String fname = tc.getFirstname().trim();
        String lname = tc.getLastname().trim();
        String address = tc.getAddress().trim();
        Date dob = tc.getDob();
        byte gender = tc.getGender();
        if (!UserIO.validate(fname, lname, dob, gender, address)) {
            return false;
        }
        // Add to database
        return MySQLUtility.addInfo(new Teacher(fname, lname, gender, dob, address,tc.getMajorid())) >= 1;

    }

    public boolean setTeacher(Teacher tc, int id) {
        String fname = tc.getFirstname().trim();
        String lname = tc.getLastname().trim();
        String address = tc.getAddress().trim();
        Date dob = tc.getDob();
        byte gender = tc.getGender();
        if (!UserIO.validate(fname, lname, dob, gender, address,tc.getMajorid())) {
            return false;
        }
        // Add to database
        return MySQLUtility.setInfo(new Teacher(fname, lname, gender, dob, address,tc.getMajorid()), id) >= 1;

    }

    public boolean deleteTeacher(int id) {
        MySQLUtility.deleteBy("teachers_courses", "teacherid", String.valueOf(id));
        MySQLUtility.deleteBy("teachers", "teacherid", String.valueOf(id));
        return true;
    }
}
