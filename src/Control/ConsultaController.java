/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Audio;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import Modelo.ConnectBD;
import Modelo.Imagen;

import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author pecky
 */
public class ConsultaController implements Initializable {

    @FXML
    DatePicker datepicker1;
    @FXML
    DatePicker datepicker2;
    @FXML
    TableView ConsultasID;

    int cont;

    @FXML
    private void BtBuscarImg(ActionEvent event) {

        TableColumn<String, Imagen> fechaPublic;

        TableColumn<String, Imagen> ncuenta;

        TableColumn<String, Imagen> idImg;

        TableColumn<String, Imagen> Img;

        ConsultasID.getColumns().clear();
        String fechinicio = datepicker1.getValue().toString();
        String fechfinal = datepicker2.getValue().toString();
        String sql1;
        // System.out.println(fechinicio);
        //System.out.println(fechfinal);

        ConnectBD con = new ConnectBD();
        /* sql1 = ("SELECT * "
                    + "FROM imagen" + " "
                    + "WHERE fecha BETWEEN" 
                    + " '" + fechinicio + "' " + "AND" + " '" +fechfinal + "'");
         */

        // System.out.println(sql1);
        sql1 = "CALL DevolverImgFecha ('" + fechinicio + "','" + fechfinal + "');";
        fechaPublic = new TableColumn<>("Fecha Publicacion");
        ncuenta = new TableColumn<>("Correo");
        idImg = new TableColumn<>("idImagen");
        Img = new TableColumn<>("img");
        System.out.println(sql1);

        fechaPublic.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ncuenta.setCellValueFactory(new PropertyValueFactory<>("correo"));
        idImg.setCellValueFactory(new PropertyValueFactory<>("id_imagen"));
        Img.setCellValueFactory(new PropertyValueFactory<>("button"));

        ConsultasID.getColumns().addAll(fechaPublic, ncuenta, Img);

        if (con.crearConexion()) {
            try {

                Statement s = con.getConexion().createStatement();
                ResultSet rs = s.executeQuery(sql1);
                ConsultasID.getItems().clear();
                if (!rs.next()) {
                    System.out.println("no data");
                }

                do {
                    String sql2 = "select nombre_CategoriaImagen from categoriaimagen where id_CategoriaImagen = " + rs.getInt(5) + ";";
                    Statement pst2 = con.getConexion().createStatement();
                    ResultSet rs2 = pst2.executeQuery(sql2);
                    rs2.first();

                    Imagen ip = new Imagen(rs.getInt(1), "", rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs2.getString(1));

                    ConsultasID.getItems().add(ip);

                } while (rs.next());
                con.getConexion().close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }

        }
    }

    @FXML
    private void BtBuscarAudio(ActionEvent event) {

        TableColumn<String, Audio> fechaPublic;

        TableColumn<String, Audio> ncuenta;

        TableColumn<String, Audio> idImg;

        TableColumn<String, Audio> Img;

        ConsultasID.getColumns().clear();
        String fechinicio = datepicker1.getValue().toString();
        String fechfinal = datepicker2.getValue().toString();
        String sql2;
        // System.out.println(fechinicio);
        //System.out.println(fechfinal);

        ConnectBD con = new ConnectBD();
        /* sql1 = ("SELECT * "
                    + "FROM imagen" + " "
                    + "WHERE fecha BETWEEN" 
                    + " '" + fechinicio + "' " + "AND" + " '" +fechfinal + "'");
         */

        // System.out.println(sql1);
        sql2 = "CALL DevolverAudioFecha ('" + fechinicio + "','" + fechfinal + "');";
        fechaPublic = new TableColumn<>("Fecha Publicacion");
        ncuenta = new TableColumn<>("Correo");
        idImg = new TableColumn<>("idImagen");
        Img = new TableColumn<>("Audio");
        System.out.println(sql2);

        fechaPublic.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ncuenta.setCellValueFactory(new PropertyValueFactory<>("correo"));
        idImg.setCellValueFactory(new PropertyValueFactory<>("id_Audio"));
        Img.setCellValueFactory(new PropertyValueFactory<>("buttonA"));

        ConsultasID.getColumns().addAll(fechaPublic, ncuenta, Img);

        if (con.crearConexion()) {
            try {

                Statement s = con.getConexion().createStatement();
                ResultSet rs = s.executeQuery(sql2);
                ConsultasID.getItems().clear();
                if (!rs.next()) {
                    System.out.println("no data");
                }

                do {
                    String sql3 ="select nombre_CategoriaAudio from categoriaaudio where id_CategoriaAudio = " + rs.getInt(5) + ";";
                    Statement pst2 = con.getConexion().createStatement();
                    ResultSet rs2 = pst2.executeQuery(sql3);
                    rs2.first();

                    Audio Ai = new Audio(rs.getInt(1), "", rs.getString(3), rs.getTimestamp(4), rs.getInt(5),rs2.getString(1));

                    ConsultasID.getItems().add(Ai);

                } while (rs.next());
                con.getConexion().close();

            } catch (SQLException e) {

            }

        }
    }

    @FXML
    private void OnCancel1(ActionEvent event) {

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

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
