package com.cuongdz.javaasm.Model;

import java.util.Date;

/**
 *
 * @author Cuongdz
 */
public class Student extends Person {

    public Student(String fname, String lname, byte gender, Date dob, String address) {
        super(fname, lname, gender, dob, address);
    }

    @Override
    public String getObjectName() {
        return "student";
    }

}
