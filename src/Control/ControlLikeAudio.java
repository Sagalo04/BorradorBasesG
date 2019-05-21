/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.LikeAudio;

/**
 *
 * @author User
 */
public class ControlLikeAudio {

    boolean DarLike(LikeAudio objL) {
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

        //System.out.println(date);
        String sql = "INSERT INTO likeaudio(id_Audio, correo, fecha) VALUES("+idAudio+" , "+correo+" , "+ fecha +");"; 

        boolean corr = false;

        try {
            corr = objL.insertarLike(sql);
        } catch (Exception e) {
            System.out.println("ERROR CONTROL Comentario imagen");
        }

        return corr;
    }
    
}
