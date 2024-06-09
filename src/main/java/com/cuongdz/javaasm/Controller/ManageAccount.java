package com.cuongdz.javaasm.Controller;

import static com.cuongdz.javaasm.JavaASM.THEME;
import com.cuongdz.javaasm.Model.MySQLUtility;
import com.cuongdz.javaasm.View.HomePage;
import com.cuongdz.javaasm.View.LoginForm;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Cuongdz
 */
public class ManageAccount {

    private short permissionLevel;

    public ManageAccount() {

    }

    // Method
    private void showExceptionDialog(Exception ex) {
        JOptionPane.showMessageDialog(null, ex, "Error", 0);
    }

    private boolean setAccessLevel(String usn, String pwd) {
        try {
            ResultSet rs = MySQLUtility.getAccountInfo(usn, pwd);
            // Check account existance
            if (rs.next()) {
                this.permissionLevel = rs.getShort("permission_level");
                return true;
            }
        } catch (Exception ex) {
            showExceptionDialog(ex);
        }
        return false;
    }

    public boolean isValidLoginButton(String usn, String pwd) {
        try {
            // Verify account before logged in
            if (setAccessLevel(usn, pwd)) {
                // Open homepage
                HomePage hp = new HomePage(usn);
                hp.setLocationRelativeTo(null);
                hp.setVisible(true);
                return true;
            }
        } catch (Exception ex) {
            showExceptionDialog(ex);
        }
        return false;
    }

    public boolean isLoggedOut() {
        // Show login form
        try {
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if (THEME.equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            LoginForm lf = new LoginForm();
            lf.setLocationRelativeTo(null);
            lf.setVisible(true);
            return true;
        } catch (Exception ex) {
            showExceptionDialog(ex);
        }
        return false;
    }

    public boolean addAccount() {
        try {

        } catch (Exception ex) {
            showExceptionDialog(ex);
        }
        return false;
    }

    public boolean setAccount() {
        try {

        } catch (Exception ex) {
            showExceptionDialog(ex);
        }
        return false;
    }

    /**
     * @return the permissionLevel
     */
    public short getPermissionLevel() {
        return permissionLevel;
    }

}
