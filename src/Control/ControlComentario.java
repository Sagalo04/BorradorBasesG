/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Comentario;
import java.sql.Date;

/**
 *
 * @author User
 */
public class ControlComentario {
    boolean ComentarImagen(Comentario objC) {

        long t = objC.getFecha().getTime();

        Date date = new Date(t);
        String idImagen, comentarioI, correoI;
       

        if ("".equals(objC.getId_Imagen())) {
            idImagen = null;
        } else {
            idImagen = "'" + objC.getId_Imagen() + "'";
        }
        
        if ("".equals(objC.getTexto_Comentario())) {
            comentarioI = null;
        } else {
            comentarioI = "'" + objC.getTexto_Comentario() + "'";
        }
        
        if ("".equals(objC.getCorreo())) {
            correoI = null;
        } else {
            correoI = "'" + objC.getCorreo() + "'";
        }        

        //System.out.println(date);
        String sql = "INSERT INTO comentario(id_Imagen, correo, Texto_Comentario, fecha) VALUES("+idImagen+" , "+correoI+" , "+ comentarioI+" , '"+ date+"');"; 

        boolean corr = false;

        try {
            corr = objC.insertarComentario(sql);
        } catch (Exception e) {
            System.out.println("ERROR CONTROL Comentario imagen");
        }

        return corr;
    }
    
    boolean ComentarAudio(Comentario objC) {

        long t = objC.getFecha().getTime();

        Date date = new Date(t);
        String idAudio, comentarioA, correoA;       
       

        if ("".equals(objC.getId_Imagen())) {
            idAudio = null;
        } else {
            idAudio = "'" + objC.getId_Imagen() + "'";
        }
        
        if ("".equals(objC.getTexto_Comentario())) {
            comentarioA = null;
        } else {
            comentarioA = "'" + objC.getTexto_Comentario() + "'";
        }
        if ("".equals(objC.getCorreo())) {
            correoA = null;
        } else {
            correoA = "'" + objC.getCorreo() + "'";
        }         

        //System.out.println(date);
        String sql = "INSERT INTO comentario(id_Audio, correo, Texto_Comentario, fecha) VALUES("+idAudio+" , "+correoA+" , "+ comentarioA+" , '"+ date +"');";

        boolean corr = false;

        try {
            corr = objC.insertarComentario(sql);
        } catch (Exception e) {
            System.out.println("ERROR CONTROL Comentario audio");
        }

        return corr;
    }
    
    
}
