/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.ConnectBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
 *
 * @author Usuario
 */
public class LoginController implements Initializable {

    @FXML
    TextField TextFieldUser;

    @FXML
    PasswordField TextFieldPass;

    //El Usuario (correo) que se enviara al Main
    public static String usuario;

    //Metodo donde se carga la vista principal(Main) falta validar el usuario contraseña por la base de datos
    @FXML
    private void OnLogin(ActionEvent event) {

        String contraseña = "";
        //Se llena al usuario por el TextField
        usuario = TextFieldUser.getText();

        ConnectBD cc = new ConnectBD();
        String correo;
        String sql = "";
        Statement st;
        correo = usuario;
        sql = ("SELECT contraseña FROM Cuenta WHERE correo='" + correo + "'");
        if (cc.crearConexion()) {
            try {

                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);

                if (rs.next()) {
                    contraseña = rs.getString("contraseña");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

        System.out.println(TextFieldPass.getText());
        if (!"".equals(TextFieldPass.getText())) {
            if (contraseña.equals(TextFieldPass.getText())) {
                try {

                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    Parent parent = FXMLLoader.load(getClass().getResource("/Vista/Main.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(parent);
                    //stage.setTitle("Login");

                    stage.setHeight(800);
                    stage.setWidth(1300);

                    stage.setMaxHeight(800);
                    stage.setMaxWidth(1300);

                    stage.setMinHeight(800);
                    stage.setMinWidth(1300);

                    stage.setScene(scene);

                    stage.show();
                } catch (Exception e) {
                    System.out.println("Error " + e.toString());
                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectas",
                        "Error iniciar Sesion", JOptionPane.ERROR_MESSAGE, null);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe Proporcionar una contraseña",
                    "Error iniciar Sesion", JOptionPane.ERROR_MESSAGE, null);
        }

    }

    //Metodo para abrir la vista donde se crea un usuario nuevo
    @FXML
    private void OnSign(ActionEvent event) throws IOException {

        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/Vista/SignIn.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setTitle("Crea una Cuenta");

        stage.setHeight(504);
        stage.setWidth(626);

        stage.setMaxHeight(504);
        stage.setMaxWidth(626);

        stage.setMinHeight(504);
        stage.setMinWidth(626);

        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    /**
     *
     * @return
     */
    public static String getUsuario() {
        return usuario;
    }

}
