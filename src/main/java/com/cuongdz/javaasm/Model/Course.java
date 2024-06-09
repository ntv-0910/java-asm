package com.cuongdz.javaasm.Model;

/**
 *
 * @author Admin
 */
public class Course {

    private String id;
    private String name;
    private double fee;
    private int majorid;

    public Course(String id, String name, double fee,int majorid) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.majorid = majorid;
    }

    /**
     * @return the id
     */
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the fee
     */
    public double getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(double fee) {
        this.fee = fee;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the majorid
     */
    public int getMajorid() {
        return majorid;
    }

    /**
     * @param majorid the majorid to set
     */
    public void setMajorid(int majorid) {
        this.majorid = majorid;
    }

}
