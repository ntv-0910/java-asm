package com.cuongdz.javaasm.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Cuongdz
 */
public class UserIO {

    public static final String REGEX_NUMBER = ".*\\d+.*"; //text contains at least one digit
    public static final String REGEX_FLOAT_NUMBER = "-?\\d+(\\.\\d+)?"; //text contains at least one digit
    public static final String REGEX_SPECIAL_CHARACTER = ".*[!@#$%^&*()\\-+=~`\\[\\]\\\\{}|;:'\",.<>/?]+.*"; // contains at least one special character


    public UserIO() {
    }

    public static boolean validate(String fname, String lname, Date dob, byte gender, String address) {
        // Validation
        if (hasNumber(fname) || hasSpecicalChar(fname) || fname.length() > 50 || fname.length() < 1) {
            return false;
        }
        if (hasNumber(lname) || hasSpecicalChar(lname) || lname.length() > 50 || lname.length() < 1) {
            return false;
        }
        if (address.length() > 200) {
            return false;
        }
        return (gender == 1 || gender == 0);

    }
    
    /*
    * @Overloading
    */
    public static boolean validate(String fname, String lname, Date dob, byte gender, String address, int majorid) {
        // Validation
        if (hasNumber(fname) || hasSpecicalChar(fname) || fname.length() > 50 || fname.length() < 1) {
            return false;
        }
        if (hasNumber(lname) || hasSpecicalChar(lname) || lname.length() > 50 || lname.length() < 1) {
            return false;
        }
        if (address.length() > 200) {
            return false;
        }
        if (majorid < 1) {
            return false;
        }
        return (gender == 1 || gender == 0);

    }

    public static boolean hasNumber(String text) {
        return text.matches(REGEX_NUMBER);
    }

    public static boolean hasFloatNumber(String text) {
        return text.matches(REGEX_FLOAT_NUMBER);
    }

    public static boolean hasSpecicalChar(String text) {
        return text.matches(REGEX_SPECIAL_CHARACTER);
    }

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public static Date stringToDate(String string) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(string);

        } catch (Exception ex) {

        }
        return null;
    }

    public static int getSelectedID(javax.swing.JComboBox cbo) {
        if (cbo.getSelectedIndex() < 1) {
            return -1;
        }
        String st = cbo.getSelectedItem() + "";
        return Integer.parseInt(st.substring(0, st.indexOf('-')));
    }

    public static String getSelectedStringID(javax.swing.JComboBox cbo) {
        if (cbo.getSelectedIndex() < 1) {
            return null;
        }
        String st = cbo.getSelectedItem() + "";
        return st.substring(0, st.indexOf('-'));
    }
}
