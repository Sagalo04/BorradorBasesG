/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Cuenta;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ElegirCategoriaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    ChoiceBox CB1;
    Stage stage;
    
    
    @FXML
    private void OnAceptar(ActionEvent event) throws IOException {


        // si todo sale bien se habra creado la cuenta y volveremos a la pantalla de inicio de sesion
            ((Node) (event.getSource())).getScene().getWindow().hide();


    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        stage = new Stage();
        CB1.getItems().addAll("opcion1","opcion2","opcion3");
    }    
    
}
