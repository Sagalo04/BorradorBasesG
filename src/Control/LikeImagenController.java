/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;


import Modelo.Comentario;
import Modelo.ConnectBD;
import Modelo.Imagen;
import Modelo.LikeImagen;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author admin-sala3
 */
public class LikeImagenController implements Initializable {
    
    @FXML
    TextField idImag;
    
    @FXML
    ImageView ImagenView;
    
    public String Usuario;
    public String Image;
    public int id;
    
    
    @FXML
    public void OnBuscarImagen(ActionEvent event) {
        ConnectBD cc = new ConnectBD();
        boolean f = false;
        String idImagen = idImag.getText();
        
        
        String sql = "SELECT i.imagen FROM imagen i WHERE i.id_Imagen = '"+idImagen+"' ; ";

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
                    ImagenView.setImage(img);
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

    @FXML
    private void OnLikeImagen(ActionEvent event) {       
        ControlLikeImagen objCL = new ControlLikeImagen();
        LikeImagen objL = null;
        
        Usuario = LoginController.getUsuario();       
        Date date = new Date();
        long x = date.getTime();
        Timestamp fecha = new Timestamp(x);
        int idImagen = Integer.parseInt(idImag.getText());

       boolean ins = false;
       
       try {
           objL = new LikeImagen(idImagen, Usuario, fecha);
           
            //Se llama al metodo de controlcuenta para insertar
            ins = objCL.DarLike(objL);

        } catch (Exception e) {
            System.out.println("ERROR " + e.toString());
        }
    }
    
}
