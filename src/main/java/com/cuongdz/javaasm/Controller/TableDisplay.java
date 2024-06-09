package com.cuongdz.javaasm.Controller;

import com.cuongdz.javaasm.Model.*;

import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class TableDisplay {

    public static DefaultTableModel dfStudentTableModel;
    public static DefaultTableModel dfTeacherTableModel;
    public static DefaultTableModel dfTeachersCoursesTableModel;    // Classes table
    public static DefaultTableModel dfStudentsCoursesTableModel;    // Student in class table
    public static DefaultTableModel dfCourseTableModel;    // Student in class table

    public TableRowSorter<DefaultTableModel> sorter;
    public TableRowSorter<DefaultTableModel> sorter1;

    private final ManageStudent ms = new ManageStudent();
    private final ManageTeacher mt = new ManageTeacher();
    private final ManageCourse mc = new ManageCourse();

    public TableDisplay() {

    }

    private void showExceptionDialog(Exception ex) {
        JOptionPane.showMessageDialog(null, ex.toString(), "Error", 0);
    }

    public boolean generateStudentTable(javax.swing.JTable tbStudent) {
        // Set Collumns titles
        String[] titles = {"ID", "First Name", "Last Name", "Gender", "DoB", "Address"};
        // Set table uneditable
        dfStudentTableModel = new DefaultTableModel(titles, 0) {
            @Override
            public boolean isCellEditable(int i, int j) {
                return false;
            }
        };
        // Get students data
        ResultSet rs = MySQLUtility.getTable("students");
        try {
            // Generate rows data
            while (rs.next()) {
                Object[] row = {rs.getInt("StudentID"), rs.getString("firstname"), rs.getString("lastname"),
                    (rs.getByte("gender") == 0) ? "Male" : "Female", rs.getDate("dob"),
                    rs.getString("address")
                };
                dfStudentTableModel.addRow(row);

            }
            tbStudent.setModel(dfStudentTableModel);

            sorter = new TableRowSorter<>(dfStudentTableModel);
            tbStudent.setRowSorter(sorter);
            return true;
        } catch (Exception ex) {
            showExceptionDialog(ex);
        }

        return false;
    }

    public boolean generateTeacherTable(javax.swing.JTable tbTeacher) {
        // Set Collumns titles
        String[] titles = {"ID", "First Name", "Last Name", "Gender", "DoB", "Address", "MajorID", "Name"};
        // Set table uneditable
        dfTeacherTableModel = new DefaultTableModel(titles, 0) {
            @Override
            public boolean isCellEditable(int i, int j) {
                return false;
            }
        };
        // Get students data
        ResultSet rs = MySQLUtility.getTeacherTable();
        try {
            // Generate rows data
            while (rs.next()) {
                Object[] row = {rs.getInt("TeacherID"), rs.getString("firstname"), rs.getString("lastname"),
                    (rs.getByte("Gender") == 0) ? "Male" : "Female", rs.getDate("dob"),
                    rs.getString("address"), rs.getInt("MajorID"), rs.getString("Name")
                };
                dfTeacherTableModel.addRow(row);

            }
            tbTeacher.setModel(dfTeacherTableModel);

            sorter1 = new TableRowSorter<>(dfTeacherTableModel);
            tbTeacher.setRowSorter(sorter1);
            return true;
        } catch (Exception ex) {
            showExceptionDialog(ex);
        }
        return false;
    }

    public boolean inputInfo(String object, JTable tbl, DefaultTableModel dft, String fname, String lname, byte gender, Date dob, String address, int majorid) {
        // Update to server
        try {
            // get selected target
            if ("Student".equals(object)) {
                // Check if it add to server or not
                if (!ms.addStudent(new Student(fname, lname, gender, dob, address))) {
                    return false;
                }
            } else if ("Teacher".equals(object)) {
                // Check if it add to server or not
                if (!mt.addTeacher(new Teacher(fname, lname, gender, dob, address, majorid))) {
                    return false;
                }
            }

        } catch (Exception ex) {
            return false;
        }

        int id = MySQLUtility.getLastID(object);
        // Update on client side
        if ("Student".equals(object)) {
            // Check if it add to server or not
            Object[] row = {id, fname, lname, (gender == 0) ? "Male" : "Female", UserIO.dateToString(dob), address};
            dft.addRow(row);

        } else {
            Object[] row = {id, fname, lname, (gender == 0) ? "Male" : "Female", UserIO.dateToString(dob), address, majorid, MySQLUtility.getMajorName(majorid)};
            dft.addRow(row);
        }
        tbl.setModel(dft);

        return true;
    }

    public boolean deleteInfo(String object, JTable jtb, DefaultTableModel dft) {
        int idx = jtb.getSelectedRow();
        int id = Integer.parseInt(jtb.getValueAt(idx, 0) + "");
        // Update to server        
        if (idx > 0) {
            try {
                // get selected target
                if ("student".equals(object.toLowerCase())) {
                    // Check if it delete on server or not
                    if (!ms.deleteStudent(id)) {
                        return false;
                    }
                } else if ("teacher".equals(object.toLowerCase())) {
                    // Check if it delete on server or not
                    if (!mt.deleteTeacher(id)) {
                        return false;
                    }
                }
            } catch (Exception ex) {
                return false;
            }
            // Update to client
            dft.removeRow(idx);
            jtb.setModel(dft);
            return true;
        }
        return false;
    }

    public boolean updateInfo(String object, javax.swing.JTable jtb, String fname, String lname, byte gender, Date dob, String address, int majorid) {
        int idx = jtb.getSelectedRow();
        int id = Integer.parseInt(jtb.getValueAt(idx, 0) + "");
        String sgender;
        if (gender == 0) {
            sgender = "Male";
        } else {
            sgender = "Female";
        }
        // Update to server
        if (idx > -1) {
            try {
                // get selected target
                if ("Student".equals(object)) {
                    // Check if it delete on server or not
                    if (!ms.setStudent(new Student(fname, lname, gender, dob, address), id)) {
                        return false;
                    }
                }
                if ("Teacher".equals(object)) {
                    // Check if it delete on server or not
                    if (!mt.setTeacher(new Teacher(fname, lname, gender, dob, address, majorid), id)) {
                        return false;
                    }
                }

            } catch (Exception ex) {
                showExceptionDialog(ex);
                return false;
            }
            // Update to client
            jtb.setValueAt(fname, idx, 1);
            jtb.setValueAt(lname, idx, 2);
            jtb.setValueAt(sgender, idx, 3);
            jtb.setValueAt(UserIO.dateToString(dob), idx, 4);
            jtb.setValueAt(address, idx, 5);
            return true;
        }
        return false;
    }

    public void searchKey(String key) {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + key.trim()));
        sorter1.setRowFilter(RowFilter.regexFilter("(?i)" + key.trim()));
    }

    /*
    * @Overloading
     */
    public void searchKey(String key, int ColumnIdx) {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + key.trim(), ColumnIdx));
        sorter1.setRowFilter(RowFilter.regexFilter("(?i)" + key.trim(), ColumnIdx));
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    public boolean generateTeachersCoursesTable(JTable tbl) {
        // Set Collumns titles
        String[] titles = {"ClassID", "TeacherID", "First Name", "Last Name", "CourseID", "Name", "DateCreated"};
        // Set table uneditable
        dfTeachersCoursesTableModel = new DefaultTableModel(titles, 0) {
            @Override
            public boolean isCellEditable(int i, int j) {
                return false;
            }
        };
        // Get students data
        ResultSet rs = MySQLUtility.getTCTable();
        try {
            // Generate rows data
            while (rs.next()) {
                Object[] row = {rs.getInt(1), rs.getInt(2), rs.getString(3),
                    rs.getString(4), rs.getInt(5),
                    rs.getString(6), rs.getDate(7)
                };
                dfTeachersCoursesTableModel.addRow(row);

            }
            tbl.setModel(dfTeachersCoursesTableModel);

            sorter1 = new TableRowSorter<>(dfTeachersCoursesTableModel);
            tbl.setRowSorter(sorter1);
            return true;
        } catch (Exception ex) {
            showExceptionDialog(ex);
        }
        return false;
    }

    public boolean generateStudentsCoursesTable(JTable tbl) {
        // Set Collumns titles
        String[] titles = {"ClassID", "StudentID", "First Name", "Last Name", "CourseID", "Name", "Fee"};
        // Set table uneditable
        dfStudentsCoursesTableModel = new DefaultTableModel(titles, 0) {
            @Override
            public boolean isCellEditable(int i, int j) {
                return false;
            }
        };
        // Get students data
        ResultSet rs = MySQLUtility.getSCTable();
        try {
            // Generate rows data
            while (rs.next()) {
                Object[] row = {rs.getInt(1), rs.getInt(2), rs.getString(3),
                    rs.getString(4), rs.getInt(5),
                    rs.getString(6), rs.getDouble(7)
                };
                dfStudentsCoursesTableModel.addRow(row);

            }
            tbl.setModel(dfStudentsCoursesTableModel);

            sorter = new TableRowSorter<>(dfStudentsCoursesTableModel);
            tbl.setRowSorter(sorter);
            return true;
        } catch (Exception ex) {
            showExceptionDialog(ex);
        }
        return false;
    }

    public boolean generateCoursesTable(JTable tbl) {
        // Set Columns titles
        String[] titles = {"CourseId", "Name", "Fee", "Major Name"};
        // Set table uneditable
        dfCourseTableModel = new DefaultTableModel(titles, 0) {
            @Override
            public boolean isCellEditable(int i, int j) {
                return false;
            }
        };
        // Get students data
        ResultSet rs = MySQLUtility.getCourseTable();
        try {
            // Generate rows data
            while (rs.next()) {
                Object[] row = {rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4)};
                dfCourseTableModel.addRow(row);

            }
            tbl.setModel(dfCourseTableModel);

            sorter = new TableRowSorter<>(dfCourseTableModel);
            tbl.setRowSorter(sorter);
            return true;
        } catch (Exception ex) {
            showExceptionDialog(ex);
        }
        return false;
    }

    public void getMajorItem(JComboBox cbo) {
        ResultSet rs = MySQLUtility.getTable("majors");
        try {
            while (rs.next()) {
                cbo.addItem(rs.getInt("MajorID") + "- " + rs.getString("Name"));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot load Majors list!");
        }
    }

    // Fix
    public boolean inputCourse(JTable jtb, DefaultTableModel dft, String id, String name, double fee, int majorid) {
        // Update to server
        if (majorid < 1) {
            return false;
        }
        try {
            // Check if it add to server or not
            if (!mc.addCourse(new Course(id, name, fee, majorid))) {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
        // Update on client side
        Object[] row = {id, name, fee, majorid};
        dft.addRow(row);
        jtb.setModel(dft);
        return true;
    }

    public boolean setCourse(JTable tbl, String id, String name, double fee, int majorid) {
        int idx = tbl.getSelectedRow();
        if (majorid < 1) {
            return false;
        }
        // Update to server
        try {
            // Check if it add to server or not
            if (!mc.setCourse(new Course(id, name, fee, majorid), id)) {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
        tbl.setValueAt(name, idx, 2);
        return true;
    }

    public boolean deleteCourse(JTable tbl, DefaultTableModel dft) {
        int idx = tbl.getSelectedRow();

        if (idx > -1) {
            String id = tbl.getValueAt(idx, 0) + "";
            mc.deleteCourse(id);
            // Update to client
            dft.removeRow(idx);
            return true;
        }
        return false;
    }

}
