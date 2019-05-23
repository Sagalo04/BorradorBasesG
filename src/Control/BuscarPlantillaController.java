/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.ConnectBD;
import Modelo.Plantilla;
import Modelo.ViewPlantilla;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author User
 */
public class BuscarPlantillaController implements Initializable {
    
    @FXML
    TableView PublicacionesPlan;
    @FXML
    TextField BuscarText;
    @FXML
    ImageView ImageView;
    
    TableColumn<String, Plantilla> fechaPublic;
    TableColumn<String, Plantilla> ncuenta;
    TableColumn<String, Plantilla> idPlantilla;
    TableColumn<String, Plantilla> nombre;
    TableColumn<String, Plantilla> apellido;
    
    @FXML
    public void OnMirarExistentes(ActionEvent event) {
        
        PublicacionesPlan.getColumns().clear();
        
        idPlantilla = new TableColumn<>("Id Plantilla");        
        fechaPublic = new TableColumn<>("Fecha Publicacion");
        ncuenta = new TableColumn<>("Correo");
        nombre = new TableColumn<>("Nombre Usuario");
        apellido = new TableColumn<>("Apellido Usuario");
        
        
        idPlantilla.setCellValueFactory(new PropertyValueFactory<>("Id_Plantilla"));
        fechaPublic.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        ncuenta.setCellValueFactory(new PropertyValueFactory<>("NombreCuenta"));       
        nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("Apellido"));

        
        PublicacionesPlan.getColumns().addAll(idPlantilla, fechaPublic, ncuenta, nombre, apellido);
        
        ConnectBD cc = new ConnectBD();
        String sql = "";
        sql = ("select * from vw_plantillas ORDER BY Fecha ASC");
        boolean f = false;
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                PublicacionesPlan.getItems().clear();
                do {

                    ViewPlantilla ip = new ViewPlantilla(rs.getInt(1), rs.getTimestamp(2), rs.getString(3), rs.getString(4), rs.getString(5));

                    PublicacionesPlan.getItems().add(ip);

                } while (rs.next());

                f = true;

            } catch (SQLException ex) {
                System.out.println(ex);

                f = false;
            }
        }       
    }
    
    @FXML
    public void OnBusquedaId(ActionEvent event) {
        ConnectBD cc = new ConnectBD();
        boolean f = false;
        String idPlantilla = BuscarText.getText();  
        
        String sql = "SELECT p.plantilla FROM plantilla p WHERE p.id_Plantilla = '"+idPlantilla+"' ; ";

        if (cc.crearConexion()) {
            try {              
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                do {                                       
                    byte byteImage[] = null;
                    Blob blob = rs.getBlob(1);
                    byteImage = blob.getBytes(1, (int) blob.length());
                    Image img = new Image(new ByteArrayInputStream(byteImage));                   
                    ImageView.setImage(img);
                } while (rs.next());               
                f = true;            
            } catch (SQLException ex) {
                System.out.println(ex);
                f = false;
            }
        }
  
    }
    
     @FXML
    public void OnDescargar(ActionEvent event) {
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO    
    }    
    
}
