/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Comentario;
import Modelo.ConnectBD;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author User
 */
public class ConsultaComController implements Initializable {
    @FXML
    TextField UsuarioText;
    
    @FXML
    TableView TableView;
    
    TableColumn<String, Comentario> fechaPublic;
    TableColumn<String, Comentario> idImagen;
    TableColumn<String, Comentario> idAudio;
    TableColumn<String, Comentario> Texto;
    
    @FXML
    public void OnBuscarComen(ActionEvent event) {       
        
        TableView.getColumns().clear();
        
        fechaPublic = new TableColumn<>("Fecha Publicacion");
        idImagen = new TableColumn<>("Id_Imagen");
        idAudio = new TableColumn<>("Id_Audio");
        Texto = new TableColumn<>("Comentario");
        

        fechaPublic.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        idImagen.setCellValueFactory(new PropertyValueFactory<>("id_Imagen"));       
        idAudio.setCellValueFactory(new PropertyValueFactory<>("id_Audio"));
        Texto.setCellValueFactory(new PropertyValueFactory<>("Texto_Comentario"));
        
        TableView.getColumns().addAll(fechaPublic, idImagen, idAudio, Texto);
        
        String usuario = UsuarioText.getText();
        ConnectBD cc = new ConnectBD();
        String sql = "CALL BuscarComentario ('"+usuario+"') ; ";
        boolean f = false;
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                TableView.getItems().clear();
                do {

                    Comentario ip = new Comentario(rs.getTimestamp(1), rs.getString(2), rs.getString(3), rs.getString(4));

                    TableView.getItems().add(ip);

                } while (rs.next());

                f = true;

            } catch (SQLException ex) {
                System.out.println(ex);

                f = false;
            }
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
