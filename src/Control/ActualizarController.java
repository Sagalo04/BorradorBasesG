/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import static Control.LoginController.usuario;
import Modelo.ConnectBD;
import Modelo.Cuenta;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class ActualizarController implements Initializable {

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

    String usuario = LoginController.getUsuario();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        stage = new Stage();

        String contraseña="", nameUser="", name="", name2="", Apellido="", Apellido2="";
        ConnectBD cc = new ConnectBD();
        String correo;
        String sql = "";
        Statement st;
        correo = usuario;
        sql = ("SELECT * FROM Cuenta WHERE correo='" + correo + "'");
        if (cc.crearConexion()) {
            try {

                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);

                if (rs.next()) {
                    contraseña = rs.getString("contraseña");
                    nameUser = rs.getString("nombrecuenta");
                    name = rs.getString("nombre");
                    name2 = rs.getString("nombre2");
                    Apellido = rs.getString("apellido");
                    Apellido2 = rs.getString("apellido2");
                }
                //JOptionPane.showMessageDialog(null, contraseña);
                //  t_pre.setText(("$ "+rs));

                TextFieldNameCuenta.setText(nameUser);
                TextFieldName.setText(name);
                TextFieldName2.setText(name2);
                TextFieldAp.setText(Apellido);
                TextFieldAp2.setText(Apellido2);
                TextFieldPass.setText(contraseña);
                
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    @FXML
    private void OnCancel(ActionEvent event) {

        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
//            Parent parent = FXMLLoader.load(getClass().getResource("/Vista/Main.fxml"));
//
//            Scene scene = new Scene(parent);
//            stage.setTitle("Login");
//
//            stage.setScene(scene);
//
//            stage.show();

        } catch (Exception e) {
            System.out.println("ERROR " + e.toString());
        }

    }

    @FXML
    private void OnConfirm(ActionEvent event) throws IOException {

        //se crea un objeto de controlcuenta con constructor vacio
        ControlCuenta objCC = new ControlCuenta();
        //Se crea una cuenta nula
        Cuenta objC = null;

        boolean ins = false;

        //Se llena el objeto de cuetna con todos sus atributos
        try {
            objC = new Cuenta(usuario, TextFieldNameCuenta.getText(), TextFieldName.getText(), TextFieldName2.getText(),
                    TextFieldAp.getText(), TextFieldAp2.getText(), TextFieldPass.getText(), null);
            
            System.out.println(TextFieldName2.getText());

            //Se llama al metodo de controlcuenta para insertar
            ins = objCC.UpdateCuenta(objC);

        } catch (Exception e) {
            System.out.println("ERROR " + e.toString());
        }

        // si todo sale bien se habra creado la cuenta y volveremos a la pantalla de inicio de sesion
        if (ins) {

            ((Node) (event.getSource())).getScene().getWindow().hide();
//            Parent parent = FXMLLoader.load(getClass().getResource("/Vista/Login.fxml"));
//
//            Scene scene = new Scene(parent);
//            stage.setTitle("Login");
//
//            stage.setScene(scene);
//
//            stage.setHeight(400);
//            stage.setWidth(500);
//
//            stage.setMaxHeight(400);
//            stage.setMaxWidth(500);
//
//            stage.setMinHeight(400);
//            stage.setMinWidth(500);
//
//            stage.show();

        } else {
            JOptionPane.showMessageDialog(null, "Datos Erroneos Por Favor Verifique o Correo ya registrado", "Error al crear Cuenta", JOptionPane.ERROR_MESSAGE, null);
        }

        System.out.println(usuario);

    }

}
