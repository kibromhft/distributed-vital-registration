/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

import java.io.Serializable;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author ibrahim
 */
public class VRSimplementation extends UnicastRemoteObject implements VRSinterface, Serializable {

    transient ResultSet rs;
    List birthlist = new ArrayList();
    List marriagelist = new ArrayList();
    List divorcelist = new ArrayList();
    List deathlist = new ArrayList();
    List adoptionlist = new ArrayList();

    public VRSimplementation() throws RemoteException {

    }

    //Search Function
    @Override
    public boolean changepassword(String username, String newpassword) throws RemoteException {
        boolean bool = false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String sql = "update login set password = '" + newpassword + "' where username = '" + username + "'";
            stmt.executeUpdate(sql);
            bool = true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Vital Registration System", 0);
        }
        return bool;
    }

    @Override
    public boolean new_user(String username, String newpassword) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Birth Search Function
    @Override
    public List birth_search(String search_string) {
        rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String search_birth = "select * from birth where fullname = '" + search_string + "'";
            rs = stmt.executeQuery(search_birth);
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                birthlist.add(0, rs.getString("FULLNAME"));
                birthlist.add(1, rs.getString("GENDER"));
                birthlist.add(2, rs.getString("DOB"));
                birthlist.add(3, rs.getString("COUNTRY"));
                birthlist.add(4, rs.getString("REGION"));
                birthlist.add(5, rs.getString("ZONE"));
                birthlist.add(6, rs.getInt("WOREDA"));
                birthlist.add(7, rs.getString("NAT"));
                birthlist.add(8, rs.getString("MOTHER_FULLNAME"));
                birthlist.add(9, rs.getString("MOTHER_NAT"));
                birthlist.add(10, rs.getString("FATHER_FULLNAME"));
                birthlist.add(11, rs.getString("FATHER_NAT"));

                birthlist.add(12, rs.getString("DATE_OF_REGISTRATION"));
                birthlist.add(13, rs.getString("CERTIFCATE_DATE"));
                birthlist.add(14, rs.getString("CIVIL_FULLNAME"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return birthlist;
    }

    @Override
    public List death_search(String search_string) throws RemoteException {
        rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String search_birth = "select * from death where deceased_fullname = '" + search_string + "'";
            rs = stmt.executeQuery(search_birth);
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                deathlist.add(0, rs.getString("title"));
                deathlist.add(1, rs.getString("Deceased_fullname"));
                deathlist.add(2, rs.getString("Deceased_gender"));
                deathlist.add(3, rs.getString("Deceased_DOB"));
                deathlist.add(4, rs.getString("Deceased_nat"));
                deathlist.add(5, rs.getString("Deceased_place"));
                deathlist.add(6, rs.getString("Deceased_DOD"));
                deathlist.add(7, rs.getString("Deceased_registration"));
                deathlist.add(8, rs.getString("Deceased_certificate"));
                deathlist.add(9, rs.getString("Deceased_civil"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deathlist;
    }

    @Override
    public List marriage_search(String search_string) throws RemoteException {
        rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String search_birth_wife = "";
            String search_birth_husband = "";

            String marriage_id = "select * from marriage inner join birth b on birth_id = husbands_birth_id where  b.fullname = '" + search_string + "'";
            rs = stmt.executeQuery(marriage_id);
            while (rs.next()) {
                marriagelist.add(0, rs.getString("wife_Pic"));
                marriagelist.add(1, rs.getString("husband_Pic"));
                marriagelist.add(2, rs.getString("marriage_Date"));
                marriagelist.add(3, rs.getString("region"));
                marriagelist.add(4, rs.getString("zone"));
                marriagelist.add(5, rs.getString("city"));
                marriagelist.add(6, rs.getString("subcity"));
                marriagelist.add(7, rs.getInt("woreda"));
                marriagelist.add(8, rs.getInt("kebele"));
                marriagelist.add(9, rs.getString("civil_fullname"));
                marriagelist.add(10, rs.getString("FULLNAME"));
                marriagelist.add(11, rs.getString("DOB"));
                marriagelist.add(12, rs.getString("NAT"));

                search_birth_wife = rs.getString("wife_birth_id");
            }
            String wife_info = "select * from birth where birth_id = '" + search_birth_wife + "'";
            ResultSet wife_info_rs = stmt.executeQuery(wife_info);
            while (wife_info_rs.next()) {
                marriagelist.add(13, wife_info_rs.getString("FULLNAME"));
                marriagelist.add(14, wife_info_rs.getString("DOB"));
                marriagelist.add(15, wife_info_rs.getString("NAT"));
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marriagelist;
    }

    @Override
    public List divorce_search(String search_string) throws RemoteException {
        rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String search_divorce = "select * from divorce where husband_fullname = '" + search_string + "'";
            rs = stmt.executeQuery(search_divorce);
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                adoptionlist.add(0, rs.getString("family_name"));
                adoptionlist.add(1, rs.getString("husband_fullname"));
                adoptionlist.add(2, rs.getString("wife_fullname"));
                adoptionlist.add(3, rs.getString("divorce_type"));
                adoptionlist.add(4, rs.getString("divorce_reason"));
                adoptionlist.add(5, rs.getString("divorce_date"));
                adoptionlist.add(6, rs.getString("divorce_certificate"));
                adoptionlist.add(7, rs.getString("civil_fullname"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return divorcelist;
    }

    @Override
    public List adoption_search(String search_string) throws RemoteException {
        rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String search_adoption = "select * from adoption where parent_name = '" + search_string + "'";
            rs = stmt.executeQuery(search_adoption);
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                adoptionlist.add(0, rs.getString("parent_name"));
                adoptionlist.add(1, rs.getString("child_name_after"));
                adoptionlist.add(2, rs.getString("child_name_before"));
                adoptionlist.add(3, rs.getString("child_relationship"));
                adoptionlist.add(4, rs.getString("child_dob"));
                adoptionlist.add(5, rs.getString("child_gender"));
                adoptionlist.add(6, rs.getString("adoption_date"));
                adoptionlist.add(7, rs.getString("adoption_registration"));
                adoptionlist.add(8, rs.getString("civil_fullname"));

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adoptionlist;
    }

    @Override
    public List wife_search(String search_string) throws RemoteException {
        List wife_list = new ArrayList();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM birth where birth_id = '" + search_string + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                wife_list.add(0, rs.getString("fullname"));
                wife_list.add(1, rs.getString("dob"));
                wife_list.add(2, rs.getString("nat"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wife_list;
    }

    @Override
    public List husband_search(String search_string) throws RemoteException {
        List husband_list = new ArrayList();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String husband_search = "SELECT * FROM birth where birth_id = '" + search_string + "'";
            ResultSet rs = stmt.executeQuery(husband_search);
            while (rs.next()) {
                husband_list.add(0, rs.getString("fullname"));
                husband_list.add(1, rs.getString("dob"));
                husband_list.add(2, rs.getString("nat"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Vital Registration System", 0);
        }
        return husband_list;
    }

    @Override
    public String birth_id() throws RemoteException {
        int num = 0;
        String birth_id = "bi";
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String sql = "Select * from birth";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                birth_id = rs.getString("birth_id");
            }
            String[] parts = birth_id.split("(?<=\\D)(?=\\d)");
            if (parts.length > 1) {
                num = Integer.parseInt(parts[1]);
            }
            String string = parts[0];
            num++;
            birth_id = string + num;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return birth_id;

    }

    @Override
    public String death_id() throws RemoteException {
        String death_id = "de";
        int num = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String sql = "Select * from death";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                death_id = rs.getString("death_id");
            }
            String[] parts = death_id.split("(?<=\\D)(?=\\d)");
            if (parts.length > 1) {
                num = Integer.parseInt(parts[1]);
            }
            String string = parts[0];
            num++;
            death_id = string + num;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return death_id;
    }

    @Override
    public String divorce_id() throws RemoteException {
        String divorce_id = "di";
        try {
            int num = 0;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String divorce_sql = "Select * from divorce";
            ResultSet rs = stmt.executeQuery(divorce_sql);
            while (rs.next()) {
                divorce_id = rs.getString("divorce_id");
            }
            String[] parts = divorce_id.split("(?<=\\D)(?=\\d)");
            if (parts.length > 1) {
                num = Integer.parseInt(parts[1]);
            }
            String string = parts[0];
            num++;
            divorce_id = string + num;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return divorce_id;
    }

    @Override
    public int marriage_id() throws RemoteException {
        int M_ID = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String sql = "Select * from marriage";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                M_ID = rs.getInt(1);
            }
            M_ID++;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return M_ID;
    }

    @Override
    public String adoption_id() throws RemoteException {
        String adoption_ID = "ad";
        int num = 0;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String sql = "Select * from adoption";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                adoption_ID = rs.getString("adoption_id");
            }
            String[] parts = adoption_ID.split("(?<=\\D)(?=\\d)");
            if (parts.length > 1) {
                num = Integer.parseInt(parts[1]);
            }
            String string = parts[0];
            num++;
            adoption_ID = string + num;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VRSimplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adoption_ID;
    }
}
