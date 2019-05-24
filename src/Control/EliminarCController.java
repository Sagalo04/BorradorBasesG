/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Audio;
import Modelo.ConnectBD;
import Modelo.Imagen;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class EliminarCController implements Initializable {

    @FXML
    TableView TbI;

    @FXML
    TableView TbA;

    TableColumn<String, Imagen> fechaPublic;
    TableColumn<String, Imagen> nombrecat;
    TableColumn<String, Imagen> DelI;

    TableColumn<String, Audio> fechaPublicA;
    TableColumn<String, Audio> DelA;
    TableColumn<String, Audio> nombreCatA;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//---------------------------IMAGEN---------------------------------//
        fechaPublic = new TableColumn<>("Fecha Publicacion");
        nombrecat = new TableColumn<>("Categoria");
        DelI = new TableColumn<>("img");

        fechaPublic.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        nombrecat.setCellValueFactory(new PropertyValueFactory<>("nombrecat"));
        DelI.setCellValueFactory(new PropertyValueFactory<>("Delete"));

        TbI.getColumns().addAll(fechaPublic, nombrecat, DelI);

        ConnectBD cc = new ConnectBD();
        String sql = "";
        sql = ("select * from Imagen Where correo = " + "'" + LoginController.getUsuario() + "';");
        boolean f = false;
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                TbI.getItems().clear();
                do {

                    String sql2 = "select nombre_CategoriaImagen from categoriaimagen where id_CategoriaImagen = " + rs.getInt(5) + ";";
                    Statement pst2 = cc.getConexion().createStatement();
                    ResultSet rs2 = pst2.executeQuery(sql2);
                    rs2.first();

                    Imagen ip = new Imagen(rs.getInt(1), rs.getTimestamp(4), rs.getInt(5), rs2.getString(1));

                    TbI.getItems().add(ip);

                } while (rs.next());

                f = true;

            } catch (SQLException ex) {
                System.out.println(ex);

                f = false;
            }
        }

        //---------------------------AUDIO---------------------------------//
        fechaPublicA = new TableColumn<>("Fecha Publicacion");
        nombreCatA = new TableColumn<>("Categoria");
        DelA = new TableColumn<>("Audio");

        fechaPublicA.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        nombreCatA.setCellValueFactory(new PropertyValueFactory<>("nombrecat"));
        DelA.setCellValueFactory(new PropertyValueFactory<>("Delete"));

        TbA.getColumns().addAll(fechaPublicA,nombreCatA, DelA);

        sql = ("select * from audio Where correo = " + "'" + LoginController.getUsuario() + "';");
        boolean y = false;
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                TbA.getItems().clear();
                do {
                    String sql2 = "select nombre_CategoriaAudio from categoriaaudio where id_CategoriaAudio = " + rs.getInt(5) + ";";
                    Statement pst2 = cc.getConexion().createStatement();
                    ResultSet rs2 = pst2.executeQuery(sql2);
                    rs2.first();

                    int a = rs.getInt(1);
                    Audio ad = new Audio(a,rs.getTimestamp(4), rs.getInt(5), rs2.getString(1));

                    TbA.getItems().add(ad);

                } while (rs.next());

                y = true;

            } catch (SQLException ex) {
                System.out.println(ex);
                y = false;
            }
        }
    }

}
