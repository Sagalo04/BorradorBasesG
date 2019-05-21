 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author Usuario
 */


public class LikeImagen {

    private int id_LikeImagen;
    private int id_Imagen;
    private String correo;
    private Timestamp fecha;

    public LikeImagen() {
    }

    public LikeImagen(int id_LikeImagen, int id_Imagen, String correo, Timestamp fecha) {
        this.id_LikeImagen = id_LikeImagen;
        this.id_Imagen = id_Imagen;
        this.correo = correo;
        this.fecha = fecha;
    }
    
    public LikeImagen(int id_Imagen, String correo, Timestamp fecha){
        this.id_Imagen = id_Imagen;
        this.correo = correo;
        this.fecha = fecha;
    }

    public int getId_LikeImagen() {
        return id_LikeImagen;
    }

    public void setId_LikeImagen(int id_LikeImagen) {
        this.id_LikeImagen = id_LikeImagen;
    }

    public int getId_Imagen() {
        return id_Imagen;
    }

    public void setId_Imagen(int id_Imagen) {
        this.id_Imagen = id_Imagen;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "LikeImagen{" + "id_LikeImagen=" + id_LikeImagen + ", id_Imagen=" + id_Imagen + ", correo=" + correo + '}';
    }

    public boolean insertarLike(String sql) {
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
                System.out.println("ERROR EN CLASE LIKE IMAGEN" + ex);
                //ex.printStackTrace();
                f = false;
            }
        }
        return f;
    }

}
