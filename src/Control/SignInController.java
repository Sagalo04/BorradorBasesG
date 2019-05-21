/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Cuenta;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class SignInController implements Initializable {

    @FXML
    TextField TextFieldEmail;

    @FXML
    TextField TextFieldName;

    @FXML
    TextField TextFieldName2;

    @FXML
    TextField TextFieldAp;

    @FXML
    TextField TextFieldAp2;

    @FXML
    PasswordField TextFieldPass;

    @FXML
    TextField TextFieldNameCuenta;

    Stage stage;

    //Metodo Crear cuenta
    @FXML
    private void OnConfirm(ActionEvent event) throws IOException {

        //se crea un objeto de controlcuenta con constructor vacio
        ControlCuenta objCC = new ControlCuenta();
        //Se crea una cuenta nula
        Cuenta objC = null;

        boolean ins = false;
        
        Date dat = new Date();

        //Se llena el objeto de cuetna con todos sus atributos
        try {
            objC = new Cuenta(TextFieldEmail.getText(), TextFieldNameCuenta.getText(), TextFieldName.getText(), TextFieldName2.getText(),
                    TextFieldAp.getText(), TextFieldAp2.getText(), TextFieldPass.getText(),dat);

            //Se llama al metodo de controlcuenta para insertar
            ins = objCC.insertarCuenta(objC);

        } catch (Exception e) {
            System.out.println("ERROR " + e.toString());
        }

        // si todo sale bien se habra creado la cuenta y volveremos a la pantalla de inicio de sesion
        if (ins) {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/Vista/Login.fxml"));

            Scene scene = new Scene(parent);
            stage.setTitle("Login");

            stage.setScene(scene);

            stage.setHeight(400);
            stage.setWidth(500);

            stage.setMaxHeight(400);
            stage.setMaxWidth(500);

            stage.setMinHeight(400);
            stage.setMinWidth(500);

            stage.show();

        } else {
            JOptionPane.showMessageDialog(null, "Datos Erroneos Por Favor Verifique o Correo ya registrado", "Error al crear Cuenta", JOptionPane.ERROR_MESSAGE, null);
        }

    }

    //metodo solo para volver a la pantalla anterior
    @FXML
    private void OnAtras(ActionEvent event) throws IOException {

        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/Vista/Login.fxml"));

        Scene scene = new Scene(parent);
        stage.setTitle("Login");

        stage.setScene(scene);

        stage.setHeight(400);
        stage.setWidth(500);

        stage.setMaxHeight(400);
        stage.setMaxWidth(500);

        stage.setMinHeight(400);
        stage.setMinWidth(500);

        stage.show();

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        stage = new Stage();
    }

}
