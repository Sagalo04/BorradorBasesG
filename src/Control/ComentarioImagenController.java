/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Comentario;
import Modelo.Cuenta;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ruben.chalapud
 */
public class ComentarioImagenController implements Initializable {
    @FXML
    TextField idImag;
    
    @FXML
    TextArea comenText;
    public String Usuario;
    
    
    
    @FXML
    public void OnEnviarComentario(ActionEvent event) {
       ControlComentario objCI = new ControlComentario();
       Comentario objC = null;
       boolean ins = false;
       Date dat = new Date();
       Usuario = LoginController.getUsuario();
       
       try {
           objC = new Comentario(idImag.getText(), Usuario, comenText.getText(), dat);
           
            //Se llama al metodo de controlcuenta para insertar
            ins = objCI.ComentarImagen(objC);

        } catch (Exception e) {
            System.out.println("ERROR " + e.toString());
        }
                             
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
