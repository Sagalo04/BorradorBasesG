/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
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
public class ConsultaController {
    @FXML
    DatePicker datepicker1;
    @FXML
    DatePicker datepicker2;
    @FXML
    TableView ConsultasID;
    
    int cont;
    
    TableColumn<String, Imagen> fechaPublic;

    TableColumn<String, Imagen> ncuenta;

    TableColumn<String, Imagen> idImg;

    TableColumn<String, Imagen> Img;
    
    @FXML
    private void BtBuscarImg (ActionEvent event) {
       
       ConsultasID.getColumns().clear();
            String fechinicio = datepicker1.getValue().toString();
            String fechfinal = datepicker2.getValue().toString();
            String sql1;
            System.out.println(fechinicio);
            System.out.println(fechfinal);
            
        
            
            ConnectBD con = new ConnectBD();
            sql1 = ("SELECT * "
                    + "FROM imagen" + " "
                    + "WHERE fecha BETWEEN" 
                    + " '" + fechinicio + "' " + "AND" + " '" +fechfinal + "'");
            
            System.out.println(sql1);
            
            fechaPublic = new TableColumn<>("Fecha Publicacion");
            ncuenta = new TableColumn<>("Correo");
            idImg = new TableColumn<>("idImagen");
            Img = new TableColumn<>("img");

            fechaPublic.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            ncuenta.setCellValueFactory(new PropertyValueFactory<>("correo"));
            idImg.setCellValueFactory(new PropertyValueFactory<>("id_imagen"));
            Img.setCellValueFactory(new PropertyValueFactory<>("button"));

        ConsultasID.getColumns().addAll(fechaPublic, ncuenta, idImg, Img);
        
        if (con.crearConexion()) {
                try {

                    Statement s = con.getConexion().createStatement();
                    ResultSet rs = s.executeQuery(sql1);
                    ConsultasID.getItems().clear();
                   if (!rs.next() ) {
                   System.out.println("no data");
                   } 
                    
                   do {
                        
                   

                    Imagen ip = new Imagen(rs.getInt(1), "", rs.getString(3), rs.getTimestamp(4));
                    
                    ConsultasID.getItems().add(ip);

                    } while (rs.next());
                   con.getConexion().close();
            
            }catch (SQLException e){
                        
             }
             
           } 
     }
        

 }
    
