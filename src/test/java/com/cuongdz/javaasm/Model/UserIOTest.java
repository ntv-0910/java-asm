/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cuongdz.javaasm.Model;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Admin
 */
public class UserIOTest {
    
    public UserIOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validate method, of class UserIO.
     */
    @Test
     public void testValidInput() {
        String fname = "John";
        String lname = "Doe";
        Date dob = new Date(2000, 1, 1);
        byte gender = 1;
        String address = "123 Main St";
        boolean result = UserIO.validate(fname, lname, dob, gender, address);
        assertTrue(result);
    }

    @Test
    public void testInvalidFname() {
        String fname = "123";
        String lname = "Doe";
        Date dob = new Date(2000, 1, 1);
        byte gender = 1;
        String address = "123 Main St";
        boolean result = UserIO.validate(fname, lname, dob, gender, address);
        assertFalse(result);
    }

    @Test
    public void testInvalidLname() {
        String fname = "John";
        String lname = "!@#";
        Date dob = new Date(2000, 1, 1);
        byte gender = 1;
        String address = "123 Main St";
        boolean result = UserIO.validate(fname, lname, dob, gender, address);
        assertFalse(result);
    }

    @Test
    public void testInvalidAddress() {
        String fname = "John";
        String lname = "Doe";
        Date dob = new Date(2000, 1, 1);
        byte gender = 1;
        String address = "1233333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333123213123123131231231231231231231231231231231231232";
        boolean result = UserIO.validate(fname, lname, dob, gender, address);
        assertFalse(result);
    }

    @Test
    public void testInvalidGender() {
        String fname = "John";
        String lname = "Doe";
        Date dob = new Date(2000, 1, 1);
        byte gender = 2;
        String address = "123 Main St";
        boolean result = UserIO.validate(fname, lname, dob, gender, address);
        assertFalse(result);
    }}



