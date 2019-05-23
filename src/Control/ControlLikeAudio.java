/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.ConnectBD;
import Modelo.LikeAudio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class ControlLikeAudio {

    public boolean DarLike(LikeAudio objL) {
        String idAudio, correo, fecha;

        if ("".equals(objL.getId_Audio())) {
            idAudio = null;
        } else {
            idAudio = "'" + objL.getId_Audio() + "'";
        }

        if ("".equals(objL.getCorreo())) {
            correo = null;
        } else {
            correo = "'" + objL.getCorreo() + "'";
        }

        if ("".equals(objL.getFecha())) {
            fecha = null;
        } else {
            fecha = "'" + objL.getFecha() + "'";
        }

        int likes=0;
        ConnectBD cc = new ConnectBD();
        boolean f = false;
        String sql1 = "SELECT COUNT(id_LikeAudio) FROM likeaudio WHERE correo =" + correo + ";";
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql1);
                rs.first();
                do {
                    likes = rs.getInt(1);
                } while (rs.next());
                
                f = true;
                
            } catch (SQLException ex) {
                System.out.println(ex);
                f = false;
            }
        }
        String sql = "";
        if (likes==0) {
            sql = "INSERT INTO likeaudio(id_Audio, correo, fecha) VALUES(" + idAudio + " , " + correo + " , " + fecha + ");";
        }
        else{
            sql = "DELETE FROM likeaudio WHERE (correo = "+correo+");";
        }
        //System.out.println(date);

        boolean corr = false;

        try {
            corr = objL.insertarLike(sql);
        } catch (Exception e) {
            System.out.println("ERROR CONTROL Comentario imagen");
        }

        return corr;
    }

}
