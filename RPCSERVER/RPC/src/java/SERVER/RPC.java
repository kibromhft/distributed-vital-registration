/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ibrahim
 */
@WebService(serviceName = "RPC")
public class RPC {

    @WebMethod(operationName = "login")
    public boolean login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        boolean login = false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String query = "SELECT * from LOGIN";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (username.equals(rs.getString("username")) && password.equals(rs.getString("password"))) {
                    login = true;
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(RPC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return login;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "marriage")
    public boolean marriage(@WebParam(name = "M_ID") int M_ID, @WebParam(name = "wife_birth_id") String wife_birth_id, @WebParam(name = "WifePic") String WifePic, @WebParam(name = "husband_birth_id") String husband_birth_id, @WebParam(name = "HusbandPic") String HusbandPic, @WebParam(name = "marriage_date") String marriage_date, @WebParam(name = "region") String region, @WebParam(name = "zone") String zone, @WebParam(name = "city") String city, @WebParam(name = "subcity") String subcity, @WebParam(name = "woreda") int woreda, @WebParam(name = "kebele") int kebele, @WebParam(name = "civil_fullname") String civil_fullname) {
        boolean marriage = false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String marquery = "insert into marriage values("+M_ID+",'"+wife_birth_id+"','"+WifePic+"','"+husband_birth_id+"','"+HusbandPic+"','"+marriage_date+"','"+region+"','"+zone+"','"+city+"','"+subcity+"',"+woreda+","+kebele+",'"+civil_fullname+"')";
            stmt.executeUpdate(marquery);
            marriage = true;
        } catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(RPC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marriage;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "birth")
    public boolean birth(@WebParam(name = "birth_id") String birth_id, @WebParam(name = "fullname") String fullname, @WebParam(name = "gender") String gender, @WebParam(name = "dob") String dob, @WebParam(name = "country") String country, @WebParam(name = "region") String region, @WebParam(name = "zone") String zone, @WebParam(name = "woreda") int woreda, @WebParam(name = "nationality") String nationality, @WebParam(name = "mom_fullname") String mom_fullname, @WebParam(name = "mom_nat") String mom_nat, @WebParam(name = "fat_fullname") String fat_fullname, @WebParam(name = "fat_nationality") String fat_nationality, @WebParam(name = "registration_date") String registration_date, @WebParam(name = "issued_date") String issued_date, @WebParam(name = "civil_fullname") String civil_fullname) {
       boolean birth = false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String birthquery = "insert into birth values('"+birth_id+"','"+fullname+"','"+gender+"','"+dob+"','"+country+"','"+region+"','"+zone+"',"+woreda+",'"+nationality+"','"+mom_fullname+"','"+mom_nat+"','"+fat_fullname+"','"+fat_nationality+"','"+registration_date+"','"+issued_date+"','"+civil_fullname+"')";
            stmt.executeUpdate(birthquery);
            birth = true;
        } catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(RPC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return birth;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "death")
    public boolean death(@WebParam(name = "death_id") String death_id, @WebParam(name = "title") String title, @WebParam(name = "deceased_fullname") String deceased_fullname, @WebParam(name = "deceased_gender") String deceased_gender, @WebParam(name = "deceased_dob") String deceased_dob, @WebParam(name = "deceased_nat") String deceased_nat, @WebParam(name = "deceased_place") String deceased_place, @WebParam(name = "deceased_dod") String deceased_dod, @WebParam(name = "deceased_registration") String deceased_registration, @WebParam(name = "deceased_certifcate") String deceased_certifcate, @WebParam(name = "deceased_civil") String deceased_civil) {
       boolean death = false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String deathquery = "insert into death values('"+death_id+"','"+title+"','"+deceased_fullname+"','"+deceased_gender+"','"+deceased_dob+"','"+deceased_nat+"','"+deceased_place+"','"+deceased_dod+"','"+deceased_registration+"','"+deceased_certifcate+"','"+deceased_civil+"')";
            stmt.executeUpdate(deathquery);
            death = true;
        } catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(RPC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return death;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "adoption")
    public boolean adoption(@WebParam(name = "adoption_id") String adoption_id, @WebParam(name = "parent_name") String parent_name, @WebParam(name = "child_name_before") String child_name_before, @WebParam(name = "child_name_after") String child_name_after, @WebParam(name = "child_relationship") String child_relationship, @WebParam(name = "child_dob") String child_dob, @WebParam(name = "child_gender") String child_gender, @WebParam(name = "adoption_date") String adoption_date, @WebParam(name = "adoption_registration") String adoption_registration, @WebParam(name = "civil_fullname") String civil_fullname) {
    boolean adoption = false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String deathquery = "insert into adoption values('"+adoption_id+"','"+parent_name+"','"+child_name_before+"','"+child_name_after+"','"+child_relationship+"','"+child_dob+"','"+child_gender+"','"+adoption_date+"','"+adoption_registration+"','"+civil_fullname+"')";
            stmt.executeUpdate(deathquery);
            adoption = true;
        } catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(RPC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adoption;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "divorce")
    public boolean divorce(@WebParam(name = "divorce_id") String divorce_id, @WebParam(name = "family_name") String family_name, @WebParam(name = "husband_fullname") String husband_fullname, @WebParam(name = "wife_fullname") String wife_fullname, @WebParam(name = "divorce_type") String divorce_type, @WebParam(name = "divorce_reason") String divorce_reason, @WebParam(name = "divorce_date") String divorce_date, @WebParam(name = "divorce_certificate") String divorce_certificate, @WebParam(name = "civil_fullname") String civil_fullname) {
       boolean divorce = false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:RMIASSIGNMEN", "sys as sysdba", "ibro456");
            Statement stmt = con.createStatement();
            String marquery = "insert into divorce values('"+divorce_id+"','"+family_name+"','"+husband_fullname+"','"+wife_fullname+"','"+divorce_type+"','"+divorce_reason+"','"+divorce_date+"','"+divorce_certificate+"','"+civil_fullname+"')";
            stmt.executeUpdate(marquery);
            divorce = true;
        } catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(RPC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return divorce;
 }

}
