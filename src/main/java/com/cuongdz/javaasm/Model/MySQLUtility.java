package com.cuongdz.javaasm.Model;

/**
 *
 * @author Cuongdz
 */
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLUtility extends MySQLIO {

    public MySQLUtility() {

    }

    public MySQLUtility(String url, String usn, String pwd) {
        super(url, usn, pwd);
    }

    // Method
    public static ResultSet getTable(String table) {
        String statement = "SELECT * FROM `" + table + "`";
        ResultSet rs = MySQLIO.query(statement);
        // Sent notify
        return rs;
    }

    /*
    * @Overloading
     */
    public static ResultSet getTable(String table, String col, String value) {
        String statement = "SELECT * FROM `" + table + "` WHERE " + col + " = '" + value + "'";
        ResultSet rs = MySQLIO.query(statement);
        // Send notify
        return rs;
    }

    public static ResultSet getTeacherTable() {
        String statement = "SELECT `t`.*, `m`.`Name` FROM `teachers` AS `t` LEFT JOIN `majors` AS `m` ON `t`.`MajorId` = `m`.`MajorId`;";
        return MySQLIO.query(statement);
    }

    public static int deleteBy(String table, String columnName, String value) {
        String statement = "DELETE FROM `" + table + "` WHERE " + columnName + " = '" + value + "'";
        int NumberRowsAffected = MySQLIO.execute(statement);
        return NumberRowsAffected;

    }

    /*
    * @Overloading
     */
    public static int deleteBy(String table, String columnName1, String value1, String columnName2, String value2) {
        String statement = "DELETE FROM `" + table + "` WHERE " + columnName1 + " = '" + value1 + "' AND " + columnName2 + " = '" + value2 + "'";
        int NumberRowsAffected = MySQLIO.execute(statement);
        return NumberRowsAffected;
    }

    public static int addInfo(Teacher object) {
        String statement = "INSERT INTO `" + object.getObjectName() + "s` ( `firstname`, `lastname`, `gender`, `dob`,`address`,`majorid`) "
                + "VALUES ( '" + object.getFirstname() + "', '" + object.getLastname() + "', '" + object.getGender() + "', '"
                + UserIO.dateToString(object.getDob()) + "','" + object.getAddress() + "','" + object.getMajorid()
                + "')";
        int NumberRowsAffected = MySQLIO.execute(statement);
        return NumberRowsAffected;
    }

    /*
    @Overriding
     */
    public static int addInfo(Student object) {
        String statement = "INSERT INTO `" + object.getObjectName() + "s` ( `firstname`, `lastname`, `gender`, `dob`,`address`) "
                + "VALUES ( '" + object.getFirstname() + "', '" + object.getLastname() + "', '" + object.getGender() + "', '"
                + object.getDobString() + "','" + object.getAddress() + "')";
        int NumberRowsAffected = MySQLIO.execute(statement);
        return NumberRowsAffected;
    }

    public static int setInfo(Teacher object, int id) {
        String statement = "UPDATE `" + object.getObjectName() + "s` SET `firstname`='" + object.getFirstname() + "',`lastname`='" + object.getLastname()
                + "',`gender`='" + object.getGender() + "',`dob`='" + object.getDobString() + "', address = '" + object.getAddress() + "', address = '" + object.getMajorid() + "' WHERE `"
                + object.getObjectName() + "id` = '" + id + "'";
        int NumberRowsAffected = MySQLIO.execute(statement);
        return NumberRowsAffected;
    }

    /*
    @Overriding
     */
    public static int setInfo(Student object, int id) {
        String statement = "UPDATE `" + object.getObjectName() + "s` SET `firstname`='" + object.getFirstname() + "',`lastname`='" + object.getLastname()
                + "',`gender`='" + object.getGender() + "',`dob`='" + object.getDobString() + "', address = '" + object.getAddress() + "' WHERE `"
                + object.getObjectName() + "id` = '" + id + "'";
        int NumberRowsAffected = MySQLIO.execute(statement);
        return NumberRowsAffected;
    }

    public static ResultSet getAccountInfo(String usn, String pwd) {
        String statement = "SELECT * FROM `accounts` WHERE username = '" + usn + "' AND password = '" + pwd + "'";
        ResultSet rs = MySQLIO.query(statement);
        // Get row count
        return rs;
    }

    public static ResultSet getStudentInfoByID(String id) {
        String statement = "SELECT * FROM `student` WHERE studentid = '" + id + "'";
        ResultSet rs = MySQLIO.query(statement);
        // Get row count
        return rs;
    }

    public static int getLastID(String object) {
        String statement = "SELECT MAX(" + object + "ID) FROM `" + object + "s`;";
        ResultSet rs = MySQLIO.query(statement);
        try {
            rs.next();
            int countId = rs.getInt(1);
            return countId;
        } catch (SQLException ex) {
            showExceptionDialog(ex);
        }
        return 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static ResultSet getTCTable() { // Teachers_Courses Table
        String statement = "SELECT `tc`.`TCID`, `tc`.`TeacherID`, `t`.`firstname`, `t`.`lastname`, `tc`.`CourseID`, `c`.`name`, `tc`.`DateCreated` "
                + "FROM `teachers_courses` AS `tc` "
                + "LEFT JOIN `teachers` AS `t` ON `tc`.`TeacherId` = `t`.`TeacherID` "
                + "LEFT JOIN `courses` AS `c` ON `tc`.`CourseID` = `c`.`courseID`;";
        return MySQLIO.query(statement);
    }

    public static ResultSet getSCTable() { // Student_Class Table
        String statement = "SELECT `tc`.`TCID`, `sc`.`StudentID`, `s`.`firstname`, `s`.`lastname`, `c`.`courseID`, `c`.`name`, `c`.`fee` "
                + "FROM `teachers_courses` AS `tc` "
                + "LEFT JOIN `students_courses` AS `sc` ON `sc`.`TCID` = `tc`.`TCID` "
                + "LEFT JOIN `students` AS `s` ON `sc`.`StudentID` = `s`.`StudentID` "
                + "LEFT JOIN `courses` AS `c` ON `tc`.`CourseID` = `c`.`courseID` WHERE `sc`.`Studentid` > 0;";
        return MySQLIO.query(statement);
    }

    public static ResultSet getCourseTable() {
        String statement = "SELECT `c`.*, `m`.`Name` FROM `courses` AS `c` LEFT JOIN `majors` AS `m` ON `c`.`MajorId` = `m`.`MajorId`;";
        return MySQLIO.query(statement);
    }

    public static int getNumberStudentInClass(int id) {
        String statement = "SELECT COUNT(StudentID) FROM `students_courses` WHERE TCID = '" + id + "'";
        ResultSet rs = MySQLIO.query(statement);
        try {
            rs.next();
            int countId = rs.getInt(1);
            return countId;
        } catch (SQLException ex) {
            showExceptionDialog(ex);
        }
        return 0;
    }

    public static int getNumberCoursesTaken(String object, int id) {
        String statement = "SELECT COUNT(" + object + "ID) FROM `" + object + "s_courses` WHERE " + object + "ID = '" + id + "'";
        ResultSet rs = MySQLIO.query(statement);
        try {
            rs.next();
            int countId = rs.getInt(1);
            return countId;
        } catch (SQLException ex) {
            showExceptionDialog(ex);
        }
        return 0;
    }

    public static ResultSet getStudentCoursesTaken(int id) {
        String statement = "SELECT `tc`.`CourseID` AS `c`FROM `students_courses` AS `sc`"
                + " LEFT JOIN `teachers_courses` AS `tc` ON `sc`.`TCID` = `tc`.`TCID` WHERE studentid = '" + id + "'";
        return MySQLIO.query(statement);
    }

    public static ResultSet getTeacherCoursesTaken(int id) {
        String statement = "SELECT CourseID FROM `teachers_courses` WHERE teacherid = '" + id + "'";
        return MySQLIO.query(statement);
    }

    public static int addCourse(Course c) {
        String statement = "INSERT INTO `courses`(`courseID`, `name`, `fee`, `majorid`) VALUES ('" + c.getId() + "','" + c.getName() + "','" + c.getFee() + "','" + c.getMajorid() + "')";
        return execute(statement);
    }

    public static int setCourse(Course c, String id) {
        String statement = "UPDATE `courses` SET `courseID` = '" + c.getId() + "', `name` = '" + c.getName() + "', fee = '" + c.getFee() + "', majorid = '" + c.getMajorid() + "' WHERE courseID = '" + id + "'";
        return execute(statement);
    }

    public static int addClass(String courseid, int teacherid, String date) {
        String statement = "INSERT INTO `teachers_courses`(`TeacherId`, `CourseID`, `DateCreated`) VALUES ('" + teacherid + "','" + courseid + "','" + date + "')";
        return MySQLIO.execute(statement);
    }

    public static int addToClass(int classid, int studentid, String courseid) {
        String statement = "INSERT INTO `students_courses` (`StudentID`, `TCID`,`CourseID`) VALUES ('" + studentid + "', '" + classid + "', '" + courseid + "')";
        return execute(statement);
    }

    public static String getMajorName(int majorid) {
        try {
            ResultSet rs = query("SELECT * FROM `majors` WHERE `majorid` = '" + majorid + "'");
            while (rs.next()) {
                return rs.getString("name");
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public static boolean isInClass(int classid, int studentid) {
        String statement = "SELECT * FROM `students_courses` WHERE `studentid` ='" + studentid + "' AND `TCID` = '" + classid + "'";
        try {
            ResultSet rs = MySQLIO.query(statement);
            return rs.next();
        } catch (SQLException ex) {
            showExceptionDialog(ex);
        }
        return true;
    }

}
