/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Comentario {

    private int id_Comentario;
    private String id_Imagen;
    private String id_Audio;
    private String correo;
    private String Texto_Comentario;
    private Date fecha;

    public Comentario() {
    }

    public Comentario(int id_Comentario, String id_Imagen, String correo, String Texto_Comentario, Date fecha) {
        this.id_Comentario = id_Comentario;
        this.id_Imagen = id_Imagen;
        this.correo = correo;
        this.Texto_Comentario = Texto_Comentario;
        this.fecha = fecha;
    }

    public Comentario(int id_Comentario, String id_Imagen, String id_Audio, String correo, String Texto_Comentario, Date fecha) {
        this.id_Comentario = id_Comentario;
        this.id_Imagen = id_Imagen;
        this.id_Audio = id_Audio;
        this.correo = correo;
        this.Texto_Comentario = Texto_Comentario;
        this.fecha = fecha;
    }
    
    public Comentario(String id_Imagen, String correo, String Texto_Comentario, Date fecha){
        this.id_Imagen = id_Imagen;
        this.correo = correo;
        this.Texto_Comentario = Texto_Comentario;
        this.fecha = fecha;        
    }
    
    public Comentario(Date fecha, String id_Imagen, String id_Audio, String Texto_Comentario){             
        this.fecha = fecha;
        this.id_Imagen = id_Imagen;
        this.id_Audio = id_Audio;
        this.Texto_Comentario = Texto_Comentario;        
    }
     

    public int getId_Comentario() {
        return id_Comentario;
    }

    public void setId_Comentario(int id_Comentario) {
        this.id_Comentario = id_Comentario;
    }

    public String getId_Imagen() {
        return id_Imagen;
    }

    public void setId_Imagen(String id_Imagen) {
        this.id_Imagen = id_Imagen;
    }

    public String getId_Audio() {
        return id_Audio;
    }

    public void setId_Audio(String id_Audio) {
        this.id_Audio = id_Audio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTexto_Comentario() {
        return Texto_Comentario;
    }

    public void setTexto_Comentario(String Texto_Comentario) {
        this.Texto_Comentario = Texto_Comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
        

    @Override
    public String toString() {
        return "Comentario{" + "id_Comentario=" + id_Comentario + ", id_Imagen=" + id_Imagen + ", id_Audio=" + id_Audio + ", correo=" + correo + ", Texto_Comentario=" + Texto_Comentario + '}';
    }
    
    public boolean insertarComentario(String sql) {

        boolean f = false;

        //se establece la coneccion
        ConnectBD objCon = new ConnectBD();

        //Si hay coneccion se crea la sentencia y se ejecuta
        if (objCon.crearConexion()) {
            try {
                Statement sentencia = objCon.getConexion().createStatement();
                sentencia.executeUpdate(sql);
                f = true;
            } catch (SQLException ex) {
                System.out.println("ERROR EN CLASE COMENTARIO" + ex);
                //ex.printStackTrace();
                f = false;
            }
        }
        return f;
    }

}
