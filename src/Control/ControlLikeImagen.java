/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.LikeImagen;
import java.sql.Date;

/**
 *
 * @author User
 */
public class ControlLikeImagen {

    public boolean DarLike(LikeImagen objL) {

        String idImagen, correo, fecha;
       

        if ("".equals(objL.getId_Imagen())) {
            idImagen = null;
        } else {
            idImagen = "'" + objL.getId_Imagen() + "'";
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
        String sql = "INSERT INTO likeimagen(id_Imagen, correo, fecha) VALUES("+idImagen+" , "+correo+" , "+ fecha +");"; 

        boolean corr = false;

        try {
            corr = objL.insertarLike(sql);
        } catch (Exception e) {
            System.out.println("ERROR CONTROL Comentario imagen");
        }

        return corr;
    }
    
}
