/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsprojectwithfx;

import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.SocketPermission;
import java.net.URL;
import java.rmi.*;
import java.rmi.registry.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.*;
import rmiserver.VRSinterface;

/**
 *
 */
public class forgetController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private AnchorPane chnagepassanchor;
    @FXML
    private AnchorPane mainanchor;
    @FXML
    private JFXTextField newpassword;

    @FXML
    private JFXTextField newpasswordagain;

    String user;

    @FXML
    void searchusername(ActionEvent event) {

        boolean bool = false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM login";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                bool = rs.getString("username").equals(username.getText());
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Vital Registration System", 0);
        }

        if (bool) {
            user = username.getText();
            mainanchor.setVisible(false);
            chnagepassanchor.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Sorry But the username is not found", "Vital Registration System", 0);
        }

    }

    @FXML
    public void changepassword(ActionEvent event) throws RemoteException, NotBoundException, IOException {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        String newpass = newpassword.getText();
        String newpassagain = newpasswordagain.getText();
        boolean bool = false;
        if (!newpass.equals(newpassagain)) {
            JOptionPane.showMessageDialog(null, "The password don't match");
            // 
        } else {
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            VRSinterface inter = (VRSinterface) reg.lookup("RMISERVER");
            bool = inter.changepassword(user, newpassword.getText());
        }
        if (bool) {
            JOptionPane.showMessageDialog(null, "The passwords successfully changed!!");
            newpassword.getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //to do 
    }

}
