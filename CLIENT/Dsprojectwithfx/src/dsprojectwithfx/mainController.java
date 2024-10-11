package dsprojectwithfx;

import MYSERVER.RPC;
import MYSERVER.RPC_Service;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;
import com.jfoenix.controls.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTabPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

/**
 *
 */
public class mainController implements Initializable {

    RPC rpcserver;
    RPC_Service rpcservice;
    public Stage windows;

    @FXML // fx:id="myanchor"
    private AnchorPane myanchor; // Value injected by FXMLLoader

    @FXML // fx:id="usernamelbl"
    private Label usernamelbl; // Value injected by FXMLLoader

    @FXML // fx:id="passwordtext"
    private JFXTextField passwordtext; // Value injected by FXMLLoader

    @FXML // fx:id="mybtn"
    private Button mybtn; // Value injected by FXMLLoader

    @FXML // fx:id="Usernametext"
    private JFXTextField Usernametext; // Value injected by FXMLLoader

    @FXML
  
    private void loginbutton(ActionEvent event){
        rpcservice = new RPC_Service();
        rpcserver = rpcservice.getRPCPort();
        boolean bool = rpcserver.login(Usernametext.getText(), passwordtext.getText());
        if (bool == true) {
            try {
                usernamelbl.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Wrong User name And/Or Password Please try again");
        }
    }
    public void forgetpasswordbtn(ActionEvent event) throws IOException
    {
        Usernametext.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("forget.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
      public void exitlogin(MouseEvent event) {
        System.exit(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //todo    
    }

}
