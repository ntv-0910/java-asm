package com.cuongdz.javaasm.View;

import com.cuongdz.javaasm.Controller.ManageAccount;
import com.cuongdz.javaasm.Controller.TableDisplay;
import com.cuongdz.javaasm.Model.UserIO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cuongdz
 */
public class HomePage extends javax.swing.JFrame {

    private String currUsername;
    private ManageAccount ma;
    private TableDisplay td;

    /**
     * Creates new form HomePage
     *
     * @param usn
     */
    public HomePage(String usn) {
        initComponents();
        currUsername = usn;
        ma = new ManageAccount();
        td = new TableDisplay();

        td.generateTeacherTable(tbTeacher);
        td.generateStudentTable(tbStudent);
        td.getMajorItem(cboMajor);

        btnCancel.setVisible(false);
        btnConfirm.setVisible(false);
        btnReset.setVisible(false);
    }

    public HomePage() {
        initComponents();

    }

    private void add() {
        // Input info
        byte gender = -1;
        if (rbFemale.isSelected()) {
            gender = 1;
        }
        if (rbMale.isSelected()) {
            gender = 0;
        }
        int mid = UserIO.getSelectedID(cboMajor);
        if (td.inputInfo(getSelectedObject(), getSelectedTable(), getSelectedDfTable(),
                txtFirstName.getText(), txtLastName.getText(), gender,
                dcDoB.getDate(), txtAddress.getText(), mid)) {
            lbNotify.setText("Added");
            clearTextBox();
        } else {
            lbNotify.setText("Error");
        }
    }

    private int getMajorID() {
        String st = cboMajor.getSelectedItem() + "";
        return Integer.parseInt(st.substring(0, st.indexOf('-')));
    }

    private void filter() {
        // TODO: get filter type
        switch (cboFilterType.getSelectedIndex()) {
            case 0:
                // Search all
                td.searchKey(txtSearchBox.getText());
                break;
            case 1:
                // by id
                td.searchKey(txtSearchBox.getText(), 0);
                break;
            case 2:
                // by first name
                td.searchKey(txtSearchBox.getText(), 1);
                break;
            case 3:
                // by last name
                td.searchKey(txtSearchBox.getText(), 2);
                break;
            case 4:
                // by address
                td.searchKey(txtSearchBox.getText(), 5);
                break;

        }
    }

    private void cancel() {
        setVisibleCRUDButton(true);
        setVisibleInputButton(false);
        if (getSelectedTable().getSelectedRow() > -1) {
            fillData(getSelectedTable().getSelectedRow(), getSelectedTable());
        } else {
            clearTextBox();
        }
    }

    /*
    * @param idx is the row index
     */
    private javax.swing.JTable getSelectedTable() {
        if (tabTable.getSelectedIndex() == 0) {
            return tbTeacher;
        }
        if (tabTable.getSelectedIndex() == 1) {
            return tbStudent;
        }
        return null;
    }

    private String getSelectedObject() {
        if (tabTable.getSelectedIndex() == 0) {
            return "Teacher";
        }
        if (tabTable.getSelectedIndex() == 1) {
            return "Student";
        }
        return null;
    }

    private DefaultTableModel getSelectedDfTable() {
        if (tabTable.getSelectedIndex() == 0) {
            return TableDisplay.dfTeacherTableModel;
        }
        if (tabTable.getSelectedIndex() == 1) {
            return TableDisplay.dfStudentTableModel;
        }
        return null;
    }

    private void fillData(int idx, javax.swing.JTable jtb) {
        // Get value 
        txtID.setText(jtb.getValueAt(idx, 0) + "");
        txtFirstName.setText(jtb.getValueAt(idx, 1) + "");
        txtLastName.setText(jtb.getValueAt(idx, 2) + "");
        if ((jtb.getValueAt(idx, 3) + "").equals("Male")) {
            rbMale.setSelected(true);
        } else {
            rbFemale.setSelected(true);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // Convert date format
        try {
            dcDoB.setDate(format.parse(jtb.getValueAt(idx, 4) + ""));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        txtAddress.setText(jtb.getValueAt(idx, 5) + "");
    }

    private void clearTextBox() {
        // Set text box to default
        txtID.setText("");
        txtAddress.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        rbMale.setSelected(true);
        try {
            dcDoB.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01"));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void setVisibleInputButton(boolean b) {
        btnCancel.setVisible(b);
        btnConfirm.setVisible(b);
        btnReset.setVisible(b);
    }

    private void setVisibleCRUDButton(boolean b) {
        btnAdd.setVisible(b);
        btnDelete.setVisible(b);
        btnEdit.setVisible(b);
        btnShowInfo.setVisible(b);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgGender = new javax.swing.ButtonGroup();
        tabTable = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTeacher = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbStudent = new javax.swing.JTable();
        txtSearchBox = new javax.swing.JTextField();
        bntClearSearch = new javax.swing.JButton();
        cboFilterType = new javax.swing.JComboBox<>();
        panel = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnShowInfo = new javax.swing.JButton();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        rbMale = new javax.swing.JRadioButton();
        rbFemale = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtType = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        btnConfirm = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        dcDoB = new com.toedter.calendar.JDateChooser();
        lbNotify = new javax.swing.JLabel();
        cboMajor = new javax.swing.JComboBox<>();
        lbMajor = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        btnAddToCourses = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        addCourses = new javax.swing.JMenuItem();
        CourseManage = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home Page (CuongNDGCH211353)");

        tabTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabTable.setName(""); // NOI18N
        tabTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabTableMouseClicked(evt);
            }
        });

        tbTeacher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbTeacher.getTableHeader().setReorderingAllowed(false);
        tbTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTeacherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbTeacher);

        tabTable.addTab("Teacher", jScrollPane1);

        tbStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbStudent.getTableHeader().setReorderingAllowed(false);
        tbStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbStudentMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbStudent);

        tabTable.addTab("Student", jScrollPane2);

        txtSearchBox.setToolTipText("Enter keywords...");
        txtSearchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchBoxActionPerformed(evt);
            }
        });
        txtSearchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchBoxKeyReleased(evt);
            }
        });

        bntClearSearch.setText("Clear");
        bntClearSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntClearSearchActionPerformed(evt);
            }
        });

        cboFilterType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "ID", "First Name", "Last Name", "Address" }));
        cboFilterType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFilterTypeItemStateChanged(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnShowInfo.setText("Info");

        txtFirstName.setToolTipText("");

        bgGender.add(rbMale);
        rbMale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbMale.setSelected(true);
        rbMale.setText("Male");

        bgGender.add(rbFemale);
        rbFemale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbFemale.setText("Female");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Last Name:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("First name:");

        txtID.setEnabled(false);

        jLabel3.setText("ID:");

        txtType.setEnabled(false);

        jLabel4.setText("Type:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("DoB:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Address:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Gender:");

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane3.setViewportView(txtAddress);

        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.setOpaque(true);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        dcDoB.setDateFormatString("dd-MM-yyyy");
        dcDoB.setMaxSelectableDate(new java.util.Date(253370743317000L));
        dcDoB.setMinSelectableDate(new java.util.Date(-62135791083000L));

        lbNotify.setBackground(new java.awt.Color(255, 51, 51));
        lbNotify.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        cboMajor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMajorActionPerformed(evt);
            }
        });

        lbMajor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbMajor.setText("Major");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(lbNotify, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 427, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(25, 25, 25))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel7)
                                    .addComponent(lbMajor))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(txtID))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtType)
                                    .addComponent(txtLastName)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addGap(0, 124, Short.MAX_VALUE)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnShowInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                                .addGap(7, 7, 7))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(rbMale)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbFemale))
                                    .addComponent(dcDoB, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(cboMajor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNotify, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcDoB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMale)
                    .addComponent(rbFemale)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMajor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMajor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirm)
                    .addComponent(btnCancel)
                    .addComponent(btnReset))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnShowInfo))
                .addGap(15, 15, 15))
        );

        btnLogout.setText("Log out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnReload.setText("Reload");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        btnAddToCourses.setText("Add To Course");
        btnAddToCourses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCoursesActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("jMenuItem2");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Courses");

        addCourses.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        addCourses.setText("Add Courses");
        addCourses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCoursesActionPerformed(evt);
            }
        });
        jMenu2.add(addCourses);

        CourseManage.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        CourseManage.setText("Manage");
        CourseManage.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
                CourseManageMenuKeyTyped(evt);
            }
        });
        CourseManage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CourseManageActionPerformed(evt);
            }
        });
        jMenu2.add(CourseManage);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddToCourses, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cboFilterType, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bntClearSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLogout))
                    .addComponent(tabTable, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bntClearSearch)
                        .addComponent(txtSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboFilterType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLogout)
                        .addComponent(btnReload)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabTable, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnAddToCourses, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchBoxKeyReleased
        filter();
    }//GEN-LAST:event_txtSearchBoxKeyReleased

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO open login form before close
        if (JOptionPane.showConfirmDialog(null, "Are you sure?") == JOptionPane.OK_OPTION) {

            if (ma.isLoggedOut()) {
                this.dispose();
            }
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int idx = getSelectedTable().getSelectedRow();
        if (idx < 0) {
            JOptionPane.showMessageDialog(null, "Please Select Row!");
            return;
        }
        // TODO Update
        if (JOptionPane.showConfirmDialog(null, "Are you sure?") == JOptionPane.OK_OPTION) {

            td.updateInfo(getSelectedObject(), getSelectedTable(), txtFirstName.getText(), txtLastName.getText(), (rbMale.isSelected()) ? (byte) 0 : (byte) 1,
                    dcDoB.getDate(), txtAddress.getText(),getMajorID());
        } else {
            lbNotify.setText("Error!");
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int idx = getSelectedTable().getSelectedRow();
        if (idx < 0) {
            JOptionPane.showMessageDialog(null, "Please Select Row!");

            return;
        }
        if (JOptionPane.showConfirmDialog(null, "Are you sure?") == JOptionPane.OK_OPTION) {
            td.deleteInfo(getSelectedObject(), getSelectedTable(), getSelectedDfTable());

        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        setVisibleInputButton(true);
        setVisibleCRUDButton(false);
        clearTextBox();
    }//GEN-LAST:event_btnAddActionPerformed

    private void tbStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStudentMouseClicked
        // TODO add your handling code here:
        setVisibleCRUDButton(true);
        setVisibleInputButton(false);
        if (tbStudent.getSelectedRow() > -1) {
            fillData(tbStudent.getSelectedRow(), tbStudent);
        } else {
            clearTextBox();
        }
    }//GEN-LAST:event_tbStudentMouseClicked

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        add();

    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        cancel();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearTextBox();
    }//GEN-LAST:event_btnResetActionPerformed

    private void cboFilterTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFilterTypeItemStateChanged
        filter();
    }//GEN-LAST:event_cboFilterTypeItemStateChanged

    private void tbTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTeacherMouseClicked
        // TODO add your handling code here:
        setVisibleCRUDButton(true);
        setVisibleInputButton(false);
        if (tbTeacher.getSelectedRow() > -1) {
            fillData(tbTeacher.getSelectedRow(), tbTeacher);
        } else {
            clearTextBox();
        }
    }//GEN-LAST:event_tbTeacherMouseClicked

    private void tabTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabTableMouseClicked
        // TODO add your handling code here:
        if (getSelectedObject().equals("Teacher")) {
            cboMajor.setEnabled(true);
        } else {
            cboMajor.setEnabled(false);

        }
        filter();
        cancel();
    }//GEN-LAST:event_tabTableMouseClicked

    private void bntClearSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntClearSearchActionPerformed
        // TODO add your handling code here:
        txtSearchBox.setText("");
        txtSearchBox.requestFocus();
    }//GEN-LAST:event_bntClearSearchActionPerformed

    private void CourseManageMenuKeyTyped(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_CourseManageMenuKeyTyped
        // TODO add your handling code here:
        CoursesPage cp = new CoursesPage(HomePage.this, true);
        cp.setLocationRelativeTo(null);
        cp.setVisible(true);
    }//GEN-LAST:event_CourseManageMenuKeyTyped

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        // TODO add your handling code here:
        td.generateStudentTable(tbStudent);
        td.generateTeacherTable(tbTeacher);
    }//GEN-LAST:event_btnReloadActionPerformed

    private void CourseManageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CourseManageActionPerformed
        // TODO add your handling code here:
        CoursesPage cp = new CoursesPage(HomePage.this, true);
        cp.setLocationRelativeTo(null);
        cp.setVisible(true);
    }//GEN-LAST:event_CourseManageActionPerformed

    private void addCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCoursesActionPerformed
        CoursesManagement cm = new CoursesManagement(HomePage.this, true);
        cm.setLocationRelativeTo(null);
        cm.setVisible(true);
    }//GEN-LAST:event_addCoursesActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Coming Soon!");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnAddToCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCoursesActionPerformed
        // TODO add your handling code here:
        int idx = tbStudent.getSelectedRow();
        if (idx < 0) {
            JOptionPane.showMessageDialog(null, "Please Select Row!");

            return;
        }
        if ("Student".equals(getSelectedObject())) {
            int id = Integer.parseInt(tbStudent.getValueAt(idx, 0) + "");
            AddToCourse atc = new AddToCourse(this, id);
            atc.setLocationRelativeTo(null);
            atc.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Cannot enrol teacher!");
        }
    }//GEN-LAST:event_btnAddToCoursesActionPerformed

    private void txtSearchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchBoxActionPerformed

    private void cboMajorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMajorActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboMajorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CourseManage;
    private javax.swing.JMenuItem addCourses;
    private javax.swing.ButtonGroup bgGender;
    private javax.swing.JButton bntClearSearch;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddToCourses;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnShowInfo;
    private javax.swing.JComboBox<String> cboFilterType;
    private javax.swing.JComboBox<String> cboMajor;
    private com.toedter.calendar.JDateChooser dcDoB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbMajor;
    private javax.swing.JLabel lbNotify;
    private javax.swing.JPanel panel;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JTabbedPane tabTable;
    private javax.swing.JTable tbStudent;
    private javax.swing.JTable tbTeacher;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtSearchBox;
    private javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables
}
