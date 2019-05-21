/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.ConnectBD;
import Modelo.LikeAudio;
import Modelo.LikeImagen;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LikeAudioController implements Initializable {

    @FXML
    TextField FieldID;

    @FXML
    Label idAudioT;
    @FXML
    Label AudioT;
    @FXML
    Label usuarioT;
    @FXML
    Label fechaT;

    public String Usuario;

    @FXML
    public void OnBuscarAudio(ActionEvent event) {
        ConnectBD cc = new ConnectBD();
        boolean f = false;
        String idA = FieldID.getText();

        String sql = "SELECT * FROM audio a WHERE a.id_Audio = '" + idA + "' ; ";

        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                do {
                    byte byteImage[] = null;
                    Blob blob = rs.getBlob(2);
                    String bob = rs.getString(2);
                    byteImage = blob.getBytes(1, (int) blob.length());
                    //img = new Image(new ByteArrayInputStream(byteImage));
                    
                    File someFile = File.createTempFile("something-", ".mp3");
                    InputStream in = blob.getBinaryStream();
                    OutputStream out = new FileOutputStream(someFile);
                    byte[] buff = blob.getBytes(1, (int) blob.length());
                    System.out.println(someFile.toString() );
                    out.write(buff);
                    out.close();

                    String ab="";
                    String p = "\\";
                    String split[];
                    split = someFile.toString().split("\\\\");
                    for (String split1 : split) {
                        ab += split1 + "/";
                    }
                    System.out.println(ab);
                    System.out.println(p);
                    AudioClip audio = new AudioClip("file:///"+ab/*"file:///c:/developer/temp/audio.mp3"*/);
                    audio.play();
                    audio.setVolume(0.85);
                    
                    idAudioT.setText(rs.getString(1));
                    usuarioT.setText(rs.getString(3));
                    fechaT.setText(rs.getString(4));
                } while (rs.next());

                f = true;

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println(ex);
                f = false;
            }
        }
    }

    @FXML
    public void OnLikeAudio(ActionEvent event) {
        ControlLikeAudio objCA = new ControlLikeAudio();
        LikeAudio objL = null;
        Usuario = LoginController.getUsuario();
        Date date = new Date();
        long x = date.getTime();
        Timestamp fecha = new Timestamp(x);

        int idAudio = Integer.parseInt(FieldID.getText());

        boolean ins = false;

        try {
            objL = new LikeAudio(idAudio, Usuario, fecha);

            ins = objCA.DarLike(objL);

        } catch (Exception e) {
            System.out.println("ERROR " + e.toString());
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
