/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Audio;
import Modelo.ConnectBD;
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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ElegircategoriaAController implements Initializable {

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
        a.add("*.wav");
        a.add("*.mp3");
        a.add("*.ogg");
        a.add("*.mp4");
        a.add("*.avi");

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Audios", a)
        );

        //Show save file dialog
        File file = fileChooser.showOpenDialog(stage);

        Date date = new Date();
        long x = date.getTime();

        Timestamp fecha = new Timestamp(x);

        int id_cat = 0;

        ConnectBD cc1 = new ConnectBD();
        String sql2 = "";
        sql2 = ("select id_CategoriaAudio from categoriaaudio where nombre_CategoriaAudio=" + "'" + CB1.getValue() + "'" + ";");
        System.out.println(CB1.getValue());
        if (cc1.crearConexion()) {
            try {
                Statement pst = cc1.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql2);
                rs.first();
                do {
                    id_cat = rs.getInt(1);
                    System.out.println("LA IDCAT ES " + id_cat);
                } while (rs.next());

            } catch (SQLException ex) {
                System.out.println("LAREPUTA " + ex);
                ex.printStackTrace();
            }
        }

        Audio objA = new Audio(Integer.numberOfLeadingZeros(0), file.toString(), LoginController.getUsuario(), fecha, id_cat);

        boolean ins = false;

        ins = objCC.insertarAudio(objA);

        // si todo sale bien se habra creado la cuenta y volveremos a la pantalla de inicio de sesion
        ((Node) (event.getSource())).getScene().getWindow().hide();

        ConnectBD cc = new ConnectBD();
        String sql = "";
        sql = ("select * from audio ORDER BY fecha DESC");
        boolean y = false;
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                MainController.tableview2.getItems().clear();
                do {
                    String sql3 = "select nombre_CategoriaAudio from categoriaaudio where id_CategoriaAudio = " + rs.getInt(5) + ";";
                    Statement pst2 = cc.getConexion().createStatement();
                    ResultSet rs2 = pst2.executeQuery(sql3);
                    rs2.first();

                    int n = rs.getInt(1);
                    Audio ad = new Audio(n, "", rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs2.getString(1));

                    MainController.tableview2.getItems().add(ad);

                } while (rs.next());

                y = true;

            } catch (SQLException ex) {
                System.out.println(ex);
                y = false;
            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO.
        stage = new Stage();
        ConnectBD cc = new ConnectBD();
        String sql = "";
        sql = ("select * from categoriaaudio");
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
                    if (cont == 0) {
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
    }

}
