package com.cuongdz.javaasm.Model;

import java.util.Date;

/**
 *
 * @author Cuongdz
 */

public class Person extends Entity{

    private String firstname;
    private String lastname;
    private Date dob;
    private byte gender;         // 0: Male , 1: Female
    private String address;

    public Person(String fname, String lname, byte gender, Date dob, String address) {
        firstname = fname;
        lastname = lname;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Setter & Getter
    /**
     * @return the First Name
     */

    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the First Name to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the Last Name
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the Last Name to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the gender
     */
    public byte getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(byte gender) {
        this.gender = gender;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }
    ////////////////////////////////////////////////////////////////////////////

    public String getDobString() {
        return UserIO.dateToString(this.getDob());
    }

    @Override
    public String getObjectName() {
        return "None";
    }

}
