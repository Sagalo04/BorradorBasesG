/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.ConnectBD;
import Modelo.Cuenta;
import Modelo.Imagen;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;
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

        ControlCuenta objCC = new ControlCuenta();

        //Lista de extensiones a usar
        LinkedList<String> a = new LinkedList<>();
        a.add("*.jpg");
        a.add("*.jpeg");
        a.add("*.png");
        a.add("*.gif");

        FileChooser fileChooser = new FileChooser();

        //Se da la extencion de la imagen
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", a)
        );

        //Se muestra la escena para abrir la imagen
        File file = fileChooser.showOpenDialog(stage);

        Date date = new Date();
        long x = date.getTime();

        Timestamp fecha = new Timestamp(x);

        int id_cat=0;

        ConnectBD cc1 = new ConnectBD();
        String sql2 = "";
        sql2 = ("select id_CategoriaImagen from categoriaimagen where nombre_CategoriaImagen=" +"'" +CB1.getValue() +"'"+ ";");
        System.out.println(CB1.getValue());
        if (cc1.crearConexion()) {
            try {
                Statement pst = cc1.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql2);
                rs.first();
                do {
                    id_cat = rs.getInt(1);
                } while (rs.next());

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        Imagen obji = new Imagen(Integer.numberOfLeadingZeros(0), file.toString(), LoginController.getUsuario(), fecha, id_cat);

        boolean ins = false;

        ins = objCC.insertarImagen(obji);

        // si todo sale bien se habra creado la cuenta y volveremos a la pantalla de inicio de sesion
        ((Node) (event.getSource())).getScene().getWindow().hide();

        ConnectBD cc = new ConnectBD();
        String sql = "";
        sql = ("select * from Imagen ORDER BY fecha DESC");
        boolean f = false;
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                int cont = 0;
                MainController.tableview.getItems().clear();
                do {

                    Imagen ip = new Imagen(rs.getInt(1), "", rs.getString(3), rs.getTimestamp(4), rs.getInt(5));
                    MainController.tableview.getItems().add(ip);

                } while (rs.next());

                f = true;

            } catch (SQLException ex) {
                System.out.println(ex);

                f = false;
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ConnectBD cc = new ConnectBD();
        String sql = "";
        sql = ("select * from categoriaimagen");
        boolean f = false;
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                int cont = 0;
                do {

                    String item = rs.getString(2);
                    CB1.getItems().add(item);
                    if (cont==0) {
                        CB1.setValue(item);
                    }
                    cont++;

                } while (rs.next());

                f = true;

            } catch (SQLException ex) {
                System.out.println(ex);

                f = false;
            }
        }
        
        
        stage = new Stage();

    }

}
