package com.cuongdz.javaasm.Model;

import java.util.Date;
/**
 *
 * @author Admin
 */
public class Teacher extends Person {

    private int majorid;
    public Teacher(String fname, String lname, byte gender, Date dob, String address, int major) {
        super(fname, lname, gender, dob, address);
        this.majorid = major;
    }

    @Override
    public String getObjectName() {
        return "teacher";
    }

    /**
     * @return the major
     */
    public int getMajorid() {
        return majorid;
    }

    /**
     * @param majorid the major to set
     */
    public void setMajorid(int majorid) {
        this.majorid = majorid;
    }


}
