/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsprojectwithfx;

import MYSERVER.RPC;
import MYSERVER.RPC_Service;
import com.jfoenix.controls.*;

import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javax.swing.*;


public class menuController implements Initializable {

    RPC rpc;
    RPC_Service rpc_service;
    String wifePic;
    String husbandPic;
    @FXML
    private AnchorPane main_display;

    @FXML
    private AnchorPane menu_display;

    @FXML
    private JFXButton birthbtn;

    @FXML
    private JFXButton deathbtn;

    @FXML
    private JFXButton marriagebtn;

    @FXML
    private JFXButton divorcebtn;

    @FXML
    private JFXButton adoptionbtn;

    @FXML
    private AnchorPane birthanchor;

    @FXML
    private JFXTextField birth_fullname;

    @FXML
    private JFXTextField region_birth;

    @FXML
    private JFXTextField zone_birth;

    @FXML
    private JFXTextField woreda_birth;

    @FXML
    private JFXTextField fullname_mom;

    @FXML
    private JFXComboBox<String> gender_combo;

    @FXML
    private JFXDatePicker dob;

    @FXML
    private JFXTextField fullname_father;

    @FXML
    private JFXDatePicker registration_date;

    @FXML
    private JFXDatePicker issued_date;

    @FXML
    private JFXComboBox<String> country_combo;

    @FXML
    private JFXComboBox<String> nationality_combo;

    @FXML
    private JFXComboBox<String> mom_nat_combo;

    @FXML
    private JFXComboBox<String> fat_nat_combo;

    @FXML
    private AnchorPane marr_display;

    @FXML
    private JFXTextField husband_birth_id;

    @FXML
    private JFXTextField wife_birth_id;

    @FXML
    private ImageView imghusband;

    @FXML
    private JFXDialog file_picker;

    @FXML
    private JFXButton male_btn;

    @FXML
    private JFXButton female_btn;

    @FXML
    private ImageView imgwife;

    @FXML
    private JFXTextField husband_fullname;

    @FXML
    private JFXTextField wife_fullname;

    @FXML
    private JFXTextField husband_dob;

    @FXML
    private JFXTextField wife_dob;

    @FXML
    private JFXTextField husband_nat;

    @FXML
    private JFXTextField wife_nat;

    @FXML
    private JFXTextField region;

    @FXML
    private JFXTextField zone;

    @FXML
    private JFXTextField city;

    @FXML
    private JFXTextField subcity;

    @FXML
    private JFXTextField woreda;

    @FXML
    private JFXDatePicker marriageDate;

    @FXML
    private JFXTextField kebele;

    @FXML
    private JFXTextField civilFullname;

    @FXML
    private JFXButton register;

    @FXML
    private AnchorPane death_display;

    @FXML
    private JFXTextField deceased_fullname;

    @FXML
    private JFXComboBox<String> gender_death_combo;

    @FXML
    private JFXDatePicker dob_deceased;

    @FXML
    private JFXComboBox<String> deceased_nat_combo;

    @FXML
    private JFXComboBox<String> deceased_place_combo;

    @FXML
    private JFXDatePicker deceased_date;

    @FXML
    private JFXDatePicker deceased_registration;

    @FXML
    private JFXDatePicker deceased_certificate;

    @FXML
    private JFXTextField deceased_civil;

    @FXML
    private JFXComboBox<String> title_combo;

    @FXML
    private AnchorPane divorce_display;

    @FXML
    private AnchorPane adoption_display;

    @FXML
    private JFXTextField parent_name;

    @FXML
    private JFXComboBox<String> child_gender;

    @FXML
    private JFXTextField child_before;

    @FXML
    private JFXTextField child_after;

    @FXML
    private JFXTextField child_relation;

    @FXML
    private JFXDatePicker child_dob;

    @FXML
    private JFXDatePicker adoption_date;

    @FXML
    private JFXTextField adoption_civil;

    @FXML
    private JFXDatePicker adoption_registration;
    @FXML
    private JFXCheckBox bith_selected;

    @FXML
    private JFXCheckBox death_selected;

    @FXML
    private JFXCheckBox marriage_selected;

    @FXML
    private JFXCheckBox divorce_selected;

    @FXML
    private JFXCheckBox adoption_selected;
    @FXML
    private AnchorPane search_display;
    @FXML
    private TextField search_string;
    transient ResultSet rs;

    @FXML
    private JFXTextField civil_fullname_birth;
    @FXML
    private JFXButton birth_registration;
    @FXML
    private JFXButton death_registration;
    @FXML
    private JFXButton divorce_registration;
    @FXML
    private JFXButton adoption_registration_btn;

    @FXML
    private ProgressIndicator progress;

    @FXML
    private JFXComboBox<String> divorce_type_combo;

    @FXML
    private JFXComboBox<String> divorce_reason_combo;

    @FXML
    private JFXDatePicker divorce_date;

    @FXML
    private JFXDatePicker divorce_certificate;

    @FXML
    private JFXTextField divorce_civil;

    @FXML
    private JFXTextField family_name;
    @FXML
    private JFXTextField divorce_husband_fullname;

    @FXML
    private JFXTextField divorce_wife_fullname;
    @FXML
    private JFXButton mysearch;

    public void handleButtonAction(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Hello to the menu");

        //  label.setText("Hello World!");
    }

    @FXML
    void death_btn(ActionEvent event) {
        marr_display.setVisible(false);
        birthanchor.setVisible(false);
        death_display.setVisible(true);
        adoption_display.setVisible(false);
        search_display.setVisible(false);
        death_registration.setVisible(true);
        divorce_display.setVisible(false);
    }

    @FXML
    void birthbtn(ActionEvent event) {
        birthanchor.setVisible(true);
        marr_display.setVisible(false);
        death_display.setVisible(false);
        adoption_display.setVisible(false);
        search_display.setVisible(false);
        birth_registration.setVisible(true);
        divorce_display.setVisible(false);
        //birthbtn.setStyle("-fx-background-color: grey;");
    }

    @FXML
    void deathbtn(ActionEvent event) {
        birthanchor.setVisible(false);
        death_display.setVisible(true);
    }

    @FXML
    void search_btn(ActionEvent event) {
        birthanchor.setVisible(false);
        marr_display.setVisible(true);
        death_display.setVisible(false);
        adoption_display.setVisible(false);
        search_display.setVisible(true);
        divorce_display.setVisible(false);
        register.setVisible(true);

    }

    @FXML
    void marriage_btn(ActionEvent event) {
        birthanchor.setVisible(false);
        marr_display.setVisible(true);
        death_display.setVisible(false);
        adoption_display.setVisible(false);
        search_display.setVisible(false);
        divorce_display.setVisible(false);
        register.setVisible(true);
    }

    @FXML
    void adoption_btn(ActionEvent event) {
        birthanchor.setVisible(false);
        marr_display.setVisible(false);
        death_display.setVisible(false);
        adoption_display.setVisible(true);
        search_display.setVisible(false);
        divorce_display.setVisible(false);
        adoption_registration_btn.setVisible(true);
    }

    @FXML
    void divorce_btn(ActionEvent event) {
        birthanchor.setVisible(false);
        marr_display.setVisible(false);
        death_display.setVisible(false);
        adoption_display.setVisible(false);
        search_display.setVisible(false);
        divorce_display.setVisible(true);
        divorce_registration.setVisible(true);
    }

    @FXML
    void mouseMovedbirthagain(MouseEvent event) {
        birthbtn.setStyle("-fx-background-color: grey;");
    }

    @FXML
    void mouseMoveddeath(MouseEvent event) {
        deathbtn.setStyle("-fx-background-color: grey;");
//birthbtn.setStyle("-fx-cursor: hand;");
    }

    public void mouseMovedmarr(MouseEvent event) {
        marriagebtn.setStyle("-fx-background-color: grey;");
//birthbtn.setStyle("-fx-cursor: hand;");
    }

    public void mouseMoveddivorce(MouseEvent event) {
        divorcebtn.setStyle("-fx-background-color: grey;");
//birthbtn.setStyle("-fx-cursor: hand;");
    }

    public void mouseMovedadop(MouseEvent event) {
        adoptionbtn.setStyle("-fx-background-color: grey;");
        //birthbtn.setStyle("-fx-cursor: hand;");
    }

    @FXML
    void mouseMovedsearch(MouseEvent event) {
        mysearch.setStyle("-fx-background-color: grey;");
    }

    public void achormousemoved(MouseEvent event) {
        birthbtn.setStyle("-fx-background-color:  #C94059;");
        deathbtn.setStyle("-fx-background-color:  #C94059;");
        marriagebtn.setStyle("-fx-background-color:  #C94059;");
        divorcebtn.setStyle("-fx-background-color:  #C94059;");
        adoptionbtn.setStyle("-fx-background-color:  #C94059;");
        mysearch.setStyle("-fx-background-color:  #C94059;");
    }

    public void exitthesytem(ActionEvent event) {
        System.exit(0);
        JOptionPane.showMessageDialog(null, "You Clikced Exit");
    }

    @FXML
    void choose_male(ActionEvent event) {
        JFileChooser fgile = new JFileChooser();
        fgile.showOpenDialog(null);
        husbandPic = fgile.getSelectedFile().toString();
        if (!husbandPic.equals("")) {
            String img = "file:///" + fgile.getSelectedFile().toString();
            Image image = new Image(img);
            imghusband.setImage(image);
        }
    }

    @FXML
    void choose_female(ActionEvent event) {
        JFileChooser fgile = new JFileChooser();
        fgile.showOpenDialog(null);
        wifePic = fgile.getSelectedFile().toString();
        if (!wifePic.equals("")) {
            String img = "file:///" + fgile.getSelectedFile().toString();
            Image image = new Image(img);
            imgwife.setImage(image);
        }

    }

    @FXML
    void husband_search(MouseEvent event) {
        List list = new ArrayList();
        try {
            Registry reg = LocateRegistry.getRegistry("10.4.40.227", 1099);
            rmiserver.VRSinterface inter = (rmiserver.VRSinterface) reg.lookup("RMISERVER");
            list = inter.husband_search(husband_birth_id.getText());
            husband_fullname.setText(list.get(0).toString());
            husband_dob.setText(list.get(1).toString());
            husband_nat.setText(list.get(2).toString());
        } catch (RemoteException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void wife_search(MouseEvent event) {
        List list = new ArrayList();
        try {
            Registry reg = LocateRegistry.getRegistry("10.4.40.227", 1099);
            rmiserver.VRSinterface inter = (rmiserver.VRSinterface) reg.lookup("RMISERVER");
            list = inter.wife_search(wife_birth_id.getText());
            wife_fullname.setText(list.get(0).toString());
            wife_dob.setText(list.get(1).toString());
            wife_nat.setText(list.get(2).toString());

        } catch (RemoteException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void search(ActionEvent event) throws RemoteException, NotBoundException, SQLException {
        rs = null;
        List list = new ArrayList();
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        Registry reg = LocateRegistry.getRegistry("10.4.40.227", 1099);
        rmiserver.VRSinterface inter = (rmiserver.VRSinterface) reg.lookup("RMISERVER");
        if (bith_selected.isSelected()) {
            birth_registration.setVisible(false);
            list = inter.birth_search(search_string.getText());
            if (list.size() > 0) {
                search_display.setVisible(false);
                birthanchor.setVisible(true);
                death_display.setVisible(false);
                marr_display.setVisible(false);
                adoption_display.setVisible(false);
                divorce_display.setVisible(false);
                birth_fullname.setText(list.get(0).toString());
                gender_combo.setValue(list.get(1).toString());
                LocalDate lc = LocalDate.parse((CharSequence) list.get(2));
                dob.setValue(lc);
                country_combo.setValue(list.get(3).toString());
                region_birth.setText(list.get(4).toString());
                zone_birth.setText(list.get(5).toString());
                woreda_birth.setText(list.get(6).toString());
                nationality_combo.setValue(list.get(7).toString());
                fullname_mom.setText(list.get(8).toString());
                mom_nat_combo.setValue(list.get(9).toString());
                fullname_father.setText(list.get(10).toString());
                fat_nat_combo.setValue(list.get(11).toString());
                registration_date.setValue(LocalDate.parse((CharSequence) list.get(12)));
                issued_date.setValue(LocalDate.parse((CharSequence) list.get(13)));
                civil_fullname_birth.setText(list.get(14).toString());
            } else {
                JOptionPane.showMessageDialog(null, "NO Birth Records Found for '" + search_string.getText());
            }
        }
        if (death_selected.isSelected()) {
            list = inter.death_search(search_string.getText());
            if (list.size() > 0) {

                search_display.setVisible(false);
                birthanchor.setVisible(false);
                death_display.setVisible(true);
                marr_display.setVisible(false);
                adoption_display.setVisible(false);
                divorce_display.setVisible(false);
                title_combo.setValue(list.get(0).toString());
                deceased_fullname.setText(list.get(1).toString());
                gender_death_combo.setValue(list.get(2).toString());
                dob_deceased.setValue(LocalDate.parse((CharSequence) list.get(3)));
                deceased_nat_combo.setValue(list.get(4).toString());
                deceased_place_combo.setValue(list.get(5).toString());
                deceased_date.setValue(LocalDate.parse((CharSequence) list.get(6)));
                deceased_registration.setValue(LocalDate.parse((CharSequence) list.get(7)));
                deceased_certificate.setValue(LocalDate.parse((CharSequence) list.get(8)));
                deceased_civil.setText(list.get(9).toString());
            } else {
                JOptionPane.showMessageDialog(null, "NO death Records Found for '" + search_string.getText());
            }

        }
        if (marriage_selected.isSelected()) {
            list = inter.marriage_search(search_string.getText());
            if (list.size() > 0) {

                search_display.setVisible(false);
                birthanchor.setVisible(false);
                death_display.setVisible(false);
                marr_display.setVisible(true);
                adoption_display.setVisible(false);
                register.setVisible(false);

                String img = "file:///" + list.get(0).toString();
                Image image = new Image(img);
                imgwife.setImage(image);
                String imghus = "file:///" + list.get(1).toString();
                Image imagehus = new Image(imghus);
                imghusband.setImage(imagehus);
                marriageDate.setValue(LocalDate.parse((CharSequence) list.get(2)));
                region.setText(list.get(3).toString());
                zone.setText(list.get(4).toString());
                city.setText(list.get(5).toString());
                subcity.setText(list.get(6).toString());
                woreda.setText(list.get(7).toString());
                kebele.setText(list.get(8).toString());
                civilFullname.setText(list.get(9).toString());
                wife_fullname.setText(list.get(13).toString());
                wife_dob.setText(list.get(14).toString());
                wife_nat.setText(list.get(15).toString());
                husband_fullname.setText(list.get(10).toString());
                husband_dob.setText(list.get(11).toString());
                husband_nat.setText(list.get(12).toString());
            } else {
                JOptionPane.showMessageDialog(null, "NO marriage Records Found for '" + search_string.getText());
            }
        }

        if (divorce_selected.isSelected()) {
            list = inter.adoption_search(search_string.getText());
            if (list.size() > 0) {
                birthanchor.setVisible(false);
                marr_display.setVisible(false);
                death_display.setVisible(false);
                adoption_display.setVisible(false);
                search_display.setVisible(false);
                divorce_display.setVisible(true);
                divorce_registration.setVisible(false);
                family_name.setText(list.get(0).toString());
                divorce_husband_fullname.setText(list.get(1).toString());
                divorce_wife_fullname.setText(list.get(2).toString());
                divorce_type_combo.setValue(list.get(3).toString());
                divorce_reason_combo.setValue(list.get(4).toString());
                divorce_date.setValue(LocalDate.parse((CharSequence) list.get(5)));
                divorce_certificate.setValue(LocalDate.parse((CharSequence) list.get(6)));
                divorce_civil.setText(list.get(7).toString());
            } else {
                JOptionPane.showMessageDialog(null, "NO Divorce Records Found for '" + search_string.getText());
            }
        }

        if (adoption_selected.isSelected()) {
            list = inter.adoption_search(search_string.getText());
            if (list.size() > 0) {
                birthanchor.setVisible(false);
                marr_display.setVisible(false);
                death_display.setVisible(false);
                adoption_display.setVisible(true);
                search_display.setVisible(false);
                divorce_display.setVisible(false);
                adoption_registration_btn.setVisible(false);
                parent_name.setText(list.get(0).toString());
                child_before.setText(list.get(1).toString());
                child_after.setText(list.get(2).toString());
                child_gender.setValue(list.get(3).toString());
                child_relation.setText(list.get(4).toString());
                child_dob.setValue(LocalDate.parse((CharSequence) list.get(5)));
                adoption_date.setValue(LocalDate.parse((CharSequence) list.get(6)));
                adoption_registration.setValue(LocalDate.parse((CharSequence) list.get(7)));
                adoption_civil.setText(list.get(8).toString());
            } else {
                JOptionPane.showMessageDialog(null, "NO Adoption Records Found for '" + search_string.getText());
            }
        }

    }

    @FXML
    void divorceregistration(ActionEvent event) {
      try {
            rpc_service = new RPC_Service();
            rpc = rpc_service.getRPCPort();
            Registry reg = LocateRegistry.getRegistry("10.4.40.227", 1099);
            rmiserver.VRSinterface inter = (rmiserver.VRSinterface) reg.lookup("RMISERVER");
            String divorce_id = inter.divorce_id();
            boolean divorce = rpc.divorce(divorce_id, family_name.getText(), divorce_husband_fullname.getText(), divorce_wife_fullname.getText(), divorce_type_combo.getValue(), divorce_reason_combo.getValue(), divorce_date.getValue().toString(), divorce_certificate.getValue().toString(), divorce_civil.getText());
            if (divorce) {
                JOptionPane.showMessageDialog(null, "Divorce Successfully recorded");
            } else {
                JOptionPane.showMessageDialog(null, "Something went wrong with divorce registration");
            }
        } catch (Exception Ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, Ex);
        }
    }

    @FXML
    void register(ActionEvent event) {

        try {

            Registry reg = LocateRegistry.getRegistry("10.4.40.227", 1099);
            rmiserver.VRSinterface inter = (rmiserver.VRSinterface) reg.lookup("RMISERVER");
            int M_ID = inter.marriage_id();
            rpc_service = new RPC_Service();
            rpc = rpc_service.getRPCPort();
            boolean bool = rpc.marriage(M_ID, wife_birth_id.getText(), wifePic, husband_birth_id.getText(), husbandPic, marriageDate.getValue().toString(), region.getText(), zone.getText(), city.getText(), subcity.getText(), Integer.parseInt(woreda.getText()), Integer.parseInt(kebele.getText()), civilFullname.getText());
            if (bool) {
                JOptionPane.showMessageDialog(null, "Congratulations");
            } else {
                JOptionPane.showMessageDialog(null, "Something Went Wrong");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Vital Registrion System", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void birthregistration(ActionEvent event) {
        try {
            rpc_service = new RPC_Service();
            rpc = rpc_service.getRPCPort();
            Registry reg = LocateRegistry.getRegistry("10.4.40.227", 1099);
            rmiserver.VRSinterface inter = (rmiserver.VRSinterface) reg.lookup("RMISERVER");
            String birth_id = inter.birth_id();
            boolean birth = rpc.birth(birth_id, birth_fullname.getText(), gender_combo.getValue(), dob.getValue().toString(), country_combo.getValue(), region_birth.getText(), zone_birth.getText(), Integer.parseInt(woreda_birth.getText()), nationality_combo.getValue(), fullname_mom.getText(), mom_nat_combo.getValue(), fullname_father.getText(), fat_nat_combo.getValue(), registration_date.getValue().toString(), issued_date.getValue().toString(), civil_fullname_birth.getText());
            if (birth) {
                JOptionPane.showMessageDialog(null, "Birth successfully registred");
            } else {
                JOptionPane.showMessageDialog(null, "Something went wrong!!!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Vital Registrion System", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void exitthesytem(MouseEvent event) {
        System.exit(0);
    }

    //Death REGISTRATION 
    @FXML
    void deathregistration(ActionEvent event) {
        //progress.setProgress(30);
        int num = 0;
        try {
            rpc_service = new RPC_Service();
            rpc = rpc_service.getRPCPort();
            Registry reg = LocateRegistry.getRegistry("10.4.40.227", 1099);
            rmiserver.VRSinterface inter = (rmiserver.VRSinterface) reg.lookup("RMISERVER");
            String death_id = inter.death_id();
            boolean death = rpc.death(death_id, title_combo.getValue(), deceased_fullname.getText(), gender_death_combo.getValue(), dob_deceased.getValue().toString(), deceased_nat_combo.getValue(), deceased_place_combo.getValue(), deceased_date.getValue().toString(), deceased_registration.getValue().toString(), deceased_certificate.getValue().toString(), deceased_civil.getText());
         if (death) {
                JOptionPane.showMessageDialog(null, "Death successfully registred");
            } else {
                JOptionPane.showMessageDialog(null, "Something went wrong With Death Registration!!!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Vital Registrion System", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void adoption_registration(ActionEvent event) {
        try {
            rpc_service = new RPC_Service();
            rpc = rpc_service.getRPCPort();
            Registry reg = LocateRegistry.getRegistry("10.4.40.227", 1099);
            rmiserver.VRSinterface inter = (rmiserver.VRSinterface) reg.lookup("RMISERVER");
            String adoption_id = inter.adoption_id();
             boolean death = rpc.adoption(adoption_id, parent_name.getText(), child_before.getText(), child_after.getText(), child_gender.getValue(), child_relation.getText(), child_dob.getValue().toString(), adoption_date.getValue().toString(), adoption_registration.getValue().toString(), adoption_civil.getText());

            if (death) {
                JOptionPane.showMessageDialog(null, "Adoption successfully registred");
            } else {
                JOptionPane.showMessageDialog(null, "Something went wrong!!!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Vital Registrion System", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void changecombo(ActionEvent event) {
        if (divorce_type_combo.getValue() == "No Fault Divorce") {
            //divorce_reason_combo.getItems().clear();
            divorce_reason_combo.getItems().addAll("Irreconcilalble differences", "Incompatibility", "Irretrievable breakdown");

        } else {
            //divorce_reason_combo.getItems().clear();
            divorce_reason_combo.getItems().addAll("Adultry", "Bigamy", "Desertion", "Mental incapacity at time of marriage", "Marriage between close relatives", "Impotence at time of marriage", "Force or fraud in obtaining the marriage", "Criminal conviction and/or imprisonment", "Drug or alcohol addiction", "Mental illness");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //String[] values = {"Male","Female"};
        //ArrayList<String> al = new ArrayList<String>();

        // ObservableList<String> list = new observableArrayList<String>();
        //list.
//       list.add("Female");
//gender_combo.setValue(url);
        //gender_combo.setVa 
        /*Collection cl = null;
       cl.add("male");
       gender_combo.getItems().addAll(cl);*/
        gender_combo.getItems().addAll("Male", "Female");
        country_combo.getItems().addAll("Choose Country", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antigua & Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "The Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia & Herzegovina", "Botswana", "Brazil", "British Virgin Is.", "Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi ", "Cambodia ", "Cameroon", "Canada ", "Cape Verde ", "Cayman Islands ", "Central African Rep.", "Chad ", "Chile ", "China ", "Colombia ", "Comoros ", "Congo, Dem. Rep. ", "Congo, Repub. of the", "Cook Islands", "Costa Rica ", "Cote d'Ivoire", "Croatia", "Cuba ", "Cyprus ", "Czech Republic", "Denmark ", "Djibouti ", "Dominica ", "Dominican Republic", "East Timor", "Ecuador", "Egypt ", "El Salvador ", "Equatorial Guinea", "Eritrea ", "Estonia ", "Ethiopia ", "Faroe Islands", "Fiji ", "Finland ", "France ", "French Guiana ", "French Polynesia ", "Gabon ", "Gambia, The", "Gaza Strip", "Georgia ", "Germany", "Ghana ", "Gibraltar", "Greece ", "Greenland", "Grenada ", "Guadeloupe", "Guam ", "Guatemala", "Guernsey", "Guinea ", "Guinea-Bissau", "Guyana ", "Haiti ", "Honduras ", "Hong Kong ", "Hungary ", "Iceland ", "India ", "Indonesia", "Iran ", "Iraq ", "Ireland ", "Isle of Man", "Israel ", "Italy ", "Jamaica", "Japan ", "Jersey ", "Jordan ", "Kazakhstan", "Kenya ", "Kiribati ", "Korea, North ", "Korea, South", "Kuwait ", "Kyrgyzstan", "Laos ", "Latvia ", "Lebanon ", "Lesotho ", "Liberia", "Libya ", "Liechtenstein ", "Lithuania ", "Luxembourg", "Macau ", "Macedonia ", "Madagascar", "Malawi ", "Malaysia ", "Maldives", "Mali ", "Malta ", "Marshall Islands", "Martinique", "Mauritania ", "Mauritius", "Mayotte ", "Mexico ", "Micronesia, Fed. St.", "Moldova", "Monaco ", "Mongolia ", "Montserrat", "Morocco ", "Mozambique ", "Namibia", "Nauru ", "Nepal ", "Netherlands ", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger ", "Nigeria ", "N. Mariana Islands", "Norway", "Oman ", "Pakistan ", "Palau ", "Panama ", "Papua New Guinea", "Paraguay", "Peru ", "Philippines", "Poland ", "Portugal ", "Puerto Rico", "Qatar ", "Reunion ", "Romania ", "Russia ", "Rwanda ", "Saint Helena ", "Saint Kitts & Nevis", "Saint Lucia ", "St Pierre & Miquelon ", "Saint Vincent and the Grenadines", "Samoa ", "San Marino ", "Sao Tome & Principe", "Saudi Arabia", "Senegal ", "Serbia ", "Seychelles ", "Sierra Leone", "Singapore ", "Slovakia ", "Slovenia ", "Solomon Islands ", "Somalia ", "South Africa", "Spain ", "Sri Lanka", "Sudan ", "Suriname ", "Swaziland", "Sweden ", "Switzerland", "Syria ", "Taiwan ", "Tajikistan ", "Tanzania ", "Thailand", "Togo ", "Tonga ", "Trinidad & Tobago", "Tunisia ", "Turkey ", "Turkmenistan ", "Turks & Caicos Is", "Tuvalu ", "Uganda ", "Ukraine ", "United Arab Emirates", "United Kingdom", "United States", "Uruguay ", "Uzbekistan ", "Vanuatu ", "Venezuela", "Vietnam", "Virgin Islands", "Wallis and Futuna", "West Bank", "Western Sahara", "Yemen", "Zambia", "Zimbabwe");
        nationality_combo.getItems().addAll("Choose Country", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antigua & Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "The Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia & Herzegovina", "Botswana", "Brazil", "British Virgin Is.", "Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi ", "Cambodia ", "Cameroon", "Canada ", "Cape Verde ", "Cayman Islands ", "Central African Rep.", "Chad ", "Chile ", "China ", "Colombia ", "Comoros ", "Congo, Dem. Rep. ", "Congo, Repub. of the", "Cook Islands", "Costa Rica ", "Cote d'Ivoire", "Croatia", "Cuba ", "Cyprus ", "Czech Republic", "Denmark ", "Djibouti ", "Dominica ", "Dominican Republic", "East Timor", "Ecuador", "Egypt ", "El Salvador ", "Equatorial Guinea", "Eritrea ", "Estonia ", "Ethiopia ", "Faroe Islands", "Fiji ", "Finland ", "France ", "French Guiana ", "French Polynesia ", "Gabon ", "Gambia, The", "Gaza Strip", "Georgia ", "Germany", "Ghana ", "Gibraltar", "Greece ", "Greenland", "Grenada ", "Guadeloupe", "Guam ", "Guatemala", "Guernsey", "Guinea ", "Guinea-Bissau", "Guyana ", "Haiti ", "Honduras ", "Hong Kong ", "Hungary ", "Iceland ", "India ", "Indonesia", "Iran ", "Iraq ", "Ireland ", "Isle of Man", "Israel ", "Italy ", "Jamaica", "Japan ", "Jersey ", "Jordan ", "Kazakhstan", "Kenya ", "Kiribati ", "Korea, North ", "Korea, South", "Kuwait ", "Kyrgyzstan", "Laos ", "Latvia ", "Lebanon ", "Lesotho ", "Liberia", "Libya ", "Liechtenstein ", "Lithuania ", "Luxembourg", "Macau ", "Macedonia ", "Madagascar", "Malawi ", "Malaysia ", "Maldives", "Mali ", "Malta ", "Marshall Islands", "Martinique", "Mauritania ", "Mauritius", "Mayotte ", "Mexico ", "Micronesia, Fed. St.", "Moldova", "Monaco ", "Mongolia ", "Montserrat", "Morocco ", "Mozambique ", "Namibia", "Nauru ", "Nepal ", "Netherlands ", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger ", "Nigeria ", "N. Mariana Islands", "Norway", "Oman ", "Pakistan ", "Palau ", "Panama ", "Papua New Guinea", "Paraguay", "Peru ", "Philippines", "Poland ", "Portugal ", "Puerto Rico", "Qatar ", "Reunion ", "Romania ", "Russia ", "Rwanda ", "Saint Helena ", "Saint Kitts & Nevis", "Saint Lucia ", "St Pierre & Miquelon ", "Saint Vincent and the Grenadines", "Samoa ", "San Marino ", "Sao Tome & Principe", "Saudi Arabia", "Senegal ", "Serbia ", "Seychelles ", "Sierra Leone", "Singapore ", "Slovakia ", "Slovenia ", "Solomon Islands ", "Somalia ", "South Africa", "Spain ", "Sri Lanka", "Sudan ", "Suriname ", "Swaziland", "Sweden ", "Switzerland", "Syria ", "Taiwan ", "Tajikistan ", "Tanzania ", "Thailand", "Togo ", "Tonga ", "Trinidad & Tobago", "Tunisia ", "Turkey ", "Turkmenistan ", "Turks & Caicos Is", "Tuvalu ", "Uganda ", "Ukraine ", "United Arab Emirates", "United Kingdom", "United States", "Uruguay ", "Uzbekistan ", "Vanuatu ", "Venezuela", "Vietnam", "Virgin Islands", "Wallis and Futuna", "West Bank", "Western Sahara", "Yemen", "Zambia", "Zimbabwe");
        mom_nat_combo.getItems().addAll("Choose Country", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antigua & Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "The Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia & Herzegovina", "Botswana", "Brazil", "British Virgin Is.", "Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi ", "Cambodia ", "Cameroon", "Canada ", "Cape Verde ", "Cayman Islands ", "Central African Rep.", "Chad ", "Chile ", "China ", "Colombia ", "Comoros ", "Congo, Dem. Rep. ", "Congo, Repub. of the", "Cook Islands", "Costa Rica ", "Cote d'Ivoire", "Croatia", "Cuba ", "Cyprus ", "Czech Republic", "Denmark ", "Djibouti ", "Dominica ", "Dominican Republic", "East Timor", "Ecuador", "Egypt ", "El Salvador ", "Equatorial Guinea", "Eritrea ", "Estonia ", "Ethiopia ", "Faroe Islands", "Fiji ", "Finland ", "France ", "French Guiana ", "French Polynesia ", "Gabon ", "Gambia, The", "Gaza Strip", "Georgia ", "Germany", "Ghana ", "Gibraltar", "Greece ", "Greenland", "Grenada ", "Guadeloupe", "Guam ", "Guatemala", "Guernsey", "Guinea ", "Guinea-Bissau", "Guyana ", "Haiti ", "Honduras ", "Hong Kong ", "Hungary ", "Iceland ", "India ", "Indonesia", "Iran ", "Iraq ", "Ireland ", "Isle of Man", "Israel ", "Italy ", "Jamaica", "Japan ", "Jersey ", "Jordan ", "Kazakhstan", "Kenya ", "Kiribati ", "Korea, North ", "Korea, South", "Kuwait ", "Kyrgyzstan", "Laos ", "Latvia ", "Lebanon ", "Lesotho ", "Liberia", "Libya ", "Liechtenstein ", "Lithuania ", "Luxembourg", "Macau ", "Macedonia ", "Madagascar", "Malawi ", "Malaysia ", "Maldives", "Mali ", "Malta ", "Marshall Islands", "Martinique", "Mauritania ", "Mauritius", "Mayotte ", "Mexico ", "Micronesia, Fed. St.", "Moldova", "Monaco ", "Mongolia ", "Montserrat", "Morocco ", "Mozambique ", "Namibia", "Nauru ", "Nepal ", "Netherlands ", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger ", "Nigeria ", "N. Mariana Islands", "Norway", "Oman ", "Pakistan ", "Palau ", "Panama ", "Papua New Guinea", "Paraguay", "Peru ", "Philippines", "Poland ", "Portugal ", "Puerto Rico", "Qatar ", "Reunion ", "Romania ", "Russia ", "Rwanda ", "Saint Helena ", "Saint Kitts & Nevis", "Saint Lucia ", "St Pierre & Miquelon ", "Saint Vincent and the Grenadines", "Samoa ", "San Marino ", "Sao Tome & Principe", "Saudi Arabia", "Senegal ", "Serbia ", "Seychelles ", "Sierra Leone", "Singapore ", "Slovakia ", "Slovenia ", "Solomon Islands ", "Somalia ", "South Africa", "Spain ", "Sri Lanka", "Sudan ", "Suriname ", "Swaziland", "Sweden ", "Switzerland", "Syria ", "Taiwan ", "Tajikistan ", "Tanzania ", "Thailand", "Togo ", "Tonga ", "Trinidad & Tobago", "Tunisia ", "Turkey ", "Turkmenistan ", "Turks & Caicos Is", "Tuvalu ", "Uganda ", "Ukraine ", "United Arab Emirates", "United Kingdom", "United States", "Uruguay ", "Uzbekistan ", "Vanuatu ", "Venezuela", "Vietnam", "Virgin Islands", "Wallis and Futuna", "West Bank", "Western Sahara", "Yemen", "Zambia", "Zimbabwe");
        fat_nat_combo.getItems().addAll("Choose Country", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antigua & Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "The Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia & Herzegovina", "Botswana", "Brazil", "British Virgin Is.", "Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi ", "Cambodia ", "Cameroon", "Canada ", "Cape Verde ", "Cayman Islands ", "Central African Rep.", "Chad ", "Chile ", "China ", "Colombia ", "Comoros ", "Congo, Dem. Rep. ", "Congo, Repub. of the", "Cook Islands", "Costa Rica ", "Cote d'Ivoire", "Croatia", "Cuba ", "Cyprus ", "Czech Republic", "Denmark ", "Djibouti ", "Dominica ", "Dominican Republic", "East Timor", "Ecuador", "Egypt ", "El Salvador ", "Equatorial Guinea", "Eritrea ", "Estonia ", "Ethiopia ", "Faroe Islands", "Fiji ", "Finland ", "France ", "French Guiana ", "French Polynesia ", "Gabon ", "Gambia, The", "Gaza Strip", "Georgia ", "Germany", "Ghana ", "Gibraltar", "Greece ", "Greenland", "Grenada ", "Guadeloupe", "Guam ", "Guatemala", "Guernsey", "Guinea ", "Guinea-Bissau", "Guyana ", "Haiti ", "Honduras ", "Hong Kong ", "Hungary ", "Iceland ", "India ", "Indonesia", "Iran ", "Iraq ", "Ireland ", "Isle of Man", "Israel ", "Italy ", "Jamaica", "Japan ", "Jersey ", "Jordan ", "Kazakhstan", "Kenya ", "Kiribati ", "Korea, North ", "Korea, South", "Kuwait ", "Kyrgyzstan", "Laos ", "Latvia ", "Lebanon ", "Lesotho ", "Liberia", "Libya ", "Liechtenstein ", "Lithuania ", "Luxembourg", "Macau ", "Macedonia ", "Madagascar", "Malawi ", "Malaysia ", "Maldives", "Mali ", "Malta ", "Marshall Islands", "Martinique", "Mauritania ", "Mauritius", "Mayotte ", "Mexico ", "Micronesia, Fed. St.", "Moldova", "Monaco ", "Mongolia ", "Montserrat", "Morocco ", "Mozambique ", "Namibia", "Nauru ", "Nepal ", "Netherlands ", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger ", "Nigeria ", "N. Mariana Islands", "Norway", "Oman ", "Pakistan ", "Palau ", "Panama ", "Papua New Guinea", "Paraguay", "Peru ", "Philippines", "Poland ", "Portugal ", "Puerto Rico", "Qatar ", "Reunion ", "Romania ", "Russia ", "Rwanda ", "Saint Helena ", "Saint Kitts & Nevis", "Saint Lucia ", "St Pierre & Miquelon ", "Saint Vincent and the Grenadines", "Samoa ", "San Marino ", "Sao Tome & Principe", "Saudi Arabia", "Senegal ", "Serbia ", "Seychelles ", "Sierra Leone", "Singapore ", "Slovakia ", "Slovenia ", "Solomon Islands ", "Somalia ", "South Africa", "Spain ", "Sri Lanka", "Sudan ", "Suriname ", "Swaziland", "Sweden ", "Switzerland", "Syria ", "Taiwan ", "Tajikistan ", "Tanzania ", "Thailand", "Togo ", "Tonga ", "Trinidad & Tobago", "Tunisia ", "Turkey ", "Turkmenistan ", "Turks & Caicos Is", "Tuvalu ", "Uganda ", "Ukraine ", "United Arab Emirates", "United Kingdom", "United States", "Uruguay ", "Uzbekistan ", "Vanuatu ", "Venezuela", "Vietnam", "Virgin Islands", "Wallis and Futuna", "West Bank", "Western Sahara", "Yemen", "Zambia", "Zimbabwe");
        title_combo.getItems().addAll("Mr.", "Mrs.", "Miss.", "Dr.");
        gender_death_combo.getItems().addAll("Male", "Female");
        deceased_nat_combo.getItems().addAll("Choose Country", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antigua & Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "The Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia & Herzegovina", "Botswana", "Brazil", "British Virgin Is.", "Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi ", "Cambodia ", "Cameroon", "Canada ", "Cape Verde ", "Cayman Islands ", "Central African Rep.", "Chad ", "Chile ", "China ", "Colombia ", "Comoros ", "Congo, Dem. Rep. ", "Congo, Repub. of the", "Cook Islands", "Costa Rica ", "Cote d'Ivoire", "Croatia", "Cuba ", "Cyprus ", "Czech Republic", "Denmark ", "Djibouti ", "Dominica ", "Dominican Republic", "East Timor", "Ecuador", "Egypt ", "El Salvador ", "Equatorial Guinea", "Eritrea ", "Estonia ", "Ethiopia ", "Faroe Islands", "Fiji ", "Finland ", "France ", "French Guiana ", "French Polynesia ", "Gabon ", "Gambia, The", "Gaza Strip", "Georgia ", "Germany", "Ghana ", "Gibraltar", "Greece ", "Greenland", "Grenada ", "Guadeloupe", "Guam ", "Guatemala", "Guernsey", "Guinea ", "Guinea-Bissau", "Guyana ", "Haiti ", "Honduras ", "Hong Kong ", "Hungary ", "Iceland ", "India ", "Indonesia", "Iran ", "Iraq ", "Ireland ", "Isle of Man", "Israel ", "Italy ", "Jamaica", "Japan ", "Jersey ", "Jordan ", "Kazakhstan", "Kenya ", "Kiribati ", "Korea, North ", "Korea, South", "Kuwait ", "Kyrgyzstan", "Laos ", "Latvia ", "Lebanon ", "Lesotho ", "Liberia", "Libya ", "Liechtenstein ", "Lithuania ", "Luxembourg", "Macau ", "Macedonia ", "Madagascar", "Malawi ", "Malaysia ", "Maldives", "Mali ", "Malta ", "Marshall Islands", "Martinique", "Mauritania ", "Mauritius", "Mayotte ", "Mexico ", "Micronesia, Fed. St.", "Moldova", "Monaco ", "Mongolia ", "Montserrat", "Morocco ", "Mozambique ", "Namibia", "Nauru ", "Nepal ", "Netherlands ", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger ", "Nigeria ", "N. Mariana Islands", "Norway", "Oman ", "Pakistan ", "Palau ", "Panama ", "Papua New Guinea", "Paraguay", "Peru ", "Philippines", "Poland ", "Portugal ", "Puerto Rico", "Qatar ", "Reunion ", "Romania ", "Russia ", "Rwanda ", "Saint Helena ", "Saint Kitts & Nevis", "Saint Lucia ", "St Pierre & Miquelon ", "Saint Vincent and the Grenadines", "Samoa ", "San Marino ", "Sao Tome & Principe", "Saudi Arabia", "Senegal ", "Serbia ", "Seychelles ", "Sierra Leone", "Singapore ", "Slovakia ", "Slovenia ", "Solomon Islands ", "Somalia ", "South Africa", "Spain ", "Sri Lanka", "Sudan ", "Suriname ", "Swaziland", "Sweden ", "Switzerland", "Syria ", "Taiwan ", "Tajikistan ", "Tanzania ", "Thailand", "Togo ", "Tonga ", "Trinidad & Tobago", "Tunisia ", "Turkey ", "Turkmenistan ", "Turks & Caicos Is", "Tuvalu ", "Uganda ", "Ukraine ", "United Arab Emirates", "United Kingdom", "United States", "Uruguay ", "Uzbekistan ", "Vanuatu ", "Venezuela", "Vietnam", "Virgin Islands", "Wallis and Futuna", "West Bank", "Western Sahara", "Yemen", "Zambia", "Zimbabwe");
        deceased_place_combo.getItems().addAll("Choose Country", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antigua & Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "The Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia & Herzegovina", "Botswana", "Brazil", "British Virgin Is.", "Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi ", "Cambodia ", "Cameroon", "Canada ", "Cape Verde ", "Cayman Islands ", "Central African Rep.", "Chad ", "Chile ", "China ", "Colombia ", "Comoros ", "Congo, Dem. Rep. ", "Congo, Repub. of the", "Cook Islands", "Costa Rica ", "Cote d'Ivoire", "Croatia", "Cuba ", "Cyprus ", "Czech Republic", "Denmark ", "Djibouti ", "Dominica ", "Dominican Republic", "East Timor", "Ecuador", "Egypt ", "El Salvador ", "Equatorial Guinea", "Eritrea ", "Estonia ", "Ethiopia ", "Faroe Islands", "Fiji ", "Finland ", "France ", "French Guiana ", "French Polynesia ", "Gabon ", "Gambia, The", "Gaza Strip", "Georgia ", "Germany", "Ghana ", "Gibraltar", "Greece ", "Greenland", "Grenada ", "Guadeloupe", "Guam ", "Guatemala", "Guernsey", "Guinea ", "Guinea-Bissau", "Guyana ", "Haiti ", "Honduras ", "Hong Kong ", "Hungary ", "Iceland ", "India ", "Indonesia", "Iran ", "Iraq ", "Ireland ", "Isle of Man", "Israel ", "Italy ", "Jamaica", "Japan ", "Jersey ", "Jordan ", "Kazakhstan", "Kenya ", "Kiribati ", "Korea, North ", "Korea, South", "Kuwait ", "Kyrgyzstan", "Laos ", "Latvia ", "Lebanon ", "Lesotho ", "Liberia", "Libya ", "Liechtenstein ", "Lithuania ", "Luxembourg", "Macau ", "Macedonia ", "Madagascar", "Malawi ", "Malaysia ", "Maldives", "Mali ", "Malta ", "Marshall Islands", "Martinique", "Mauritania ", "Mauritius", "Mayotte ", "Mexico ", "Micronesia, Fed. St.", "Moldova", "Monaco ", "Mongolia ", "Montserrat", "Morocco ", "Mozambique ", "Namibia", "Nauru ", "Nepal ", "Netherlands ", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger ", "Nigeria ", "N. Mariana Islands", "Norway", "Oman ", "Pakistan ", "Palau ", "Panama ", "Papua New Guinea", "Paraguay", "Peru ", "Philippines", "Poland ", "Portugal ", "Puerto Rico", "Qatar ", "Reunion ", "Romania ", "Russia ", "Rwanda ", "Saint Helena ", "Saint Kitts & Nevis", "Saint Lucia ", "St Pierre & Miquelon ", "Saint Vincent and the Grenadines", "Samoa ", "San Marino ", "Sao Tome & Principe", "Saudi Arabia", "Senegal ", "Serbia ", "Seychelles ", "Sierra Leone", "Singapore ", "Slovakia ", "Slovenia ", "Solomon Islands ", "Somalia ", "South Africa", "Spain ", "Sri Lanka", "Sudan ", "Suriname ", "Swaziland", "Sweden ", "Switzerland", "Syria ", "Taiwan ", "Tajikistan ", "Tanzania ", "Thailand", "Togo ", "Tonga ", "Trinidad & Tobago", "Tunisia ", "Turkey ", "Turkmenistan ", "Turks & Caicos Is", "Tuvalu ", "Uganda ", "Ukraine ", "United Arab Emirates", "United Kingdom", "United States", "Uruguay ", "Uzbekistan ", "Vanuatu ", "Venezuela", "Vietnam", "Virgin Islands", "Wallis and Futuna", "West Bank", "Western Sahara", "Yemen", "Zambia", "Zimbabwe");
        child_gender.getItems().addAll("Male", "Female");

        divorce_type_combo.getItems().addAll("No Fault Divorce", "At Fault Divorce");
        //no_fault_divorce_combo.getItems().addAll("Adultry", "Bigamy", "Desertion", "Mental incapacity at time of marriage", "Marriage between close relatives", "Impotence at time of marriage", "Force or fraud in obtaining the marriage", "Criminal conviction and/or imprisonment", "Drug or alcohol addiction", "Mental illness");
        //at_fault_divorce_combo.getItems().addAll("Irreconcilalble differences","Incompatibility","Irretrievable breakdown");
    }
}
