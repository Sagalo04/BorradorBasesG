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
public class LikeAudio {

    private int id_LikeAudio;
    private int id_Audio;
    private String correo;
    private Timestamp fecha;

    public LikeAudio() {
    }

    public LikeAudio(int id_LikeAudio, int id_Audio, String correo, Timestamp fecha) {
        this.id_LikeAudio = id_LikeAudio;
        this.id_Audio = id_Audio;
        this.correo = correo;
        this.fecha = fecha;
    }

    public LikeAudio(int id_Audio, String correo, Timestamp fecha) {
        this.id_Audio = id_Audio;
        this.correo = correo;
        this.fecha = fecha;
    }

    public int getId_LikeAudio() {
        return id_LikeAudio;
    }

    public void setId_LikeAudio(int id_LikeAudio) {
        this.id_LikeAudio = id_LikeAudio;
    }

    public int getId_Audio() {
        return id_Audio;
    }

    public void setId_Audio(int id_Audio) {
        this.id_Audio = id_Audio;
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
        return "LikeAudio{" + "id_LikeAudio=" + id_LikeAudio + ", id_Audio=" + id_Audio + ", correo=" + correo + '}';
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
                System.out.println("ERROR EN CLASE LIKE AUDIO" + ex);
                //ex.printStackTrace();
                f = false;
            }
        }
        return f;
    }

}
