/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 *
 * @author Usuario
 */
public class Plantilla {

    private int id_Plantilla;
    private String Plantilla;
    private String correo;
    private Timestamp fecha;

    public Plantilla() {
    }

    public Plantilla(int id_Plantilla, String Plantilla,Timestamp fecha) {
        this.id_Plantilla = id_Plantilla;
        this.Plantilla = Plantilla;
        this.fecha = fecha;
    }

    public Plantilla(int id_Plantilla, String Plantilla, String correo, Timestamp fecha) {
        this.id_Plantilla = id_Plantilla;
        this.Plantilla = Plantilla;
        this.correo = correo;
        this.fecha = fecha;
    }

    public int getId_Plantilla() {
        return id_Plantilla;
    }

    public void setId_Plantilla(int id_Plantilla) {
        this.id_Plantilla = id_Plantilla;
    }

    public String getPlantilla() {
        return Plantilla;
    }

    public void setPlantilla(String Plantilla) {
        this.Plantilla = Plantilla;
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
        return "Plantilla{" + "id_Plantilla=" + id_Plantilla + ", Plantilla=" + Plantilla + ", correo=" + correo + '}';
    }

    public boolean insertarPlantilla(Plantilla objP, String sql) {
        System.out.println(objP.getPlantilla());
        System.out.println(objP.getCorreo());
        System.err.println(objP.getFecha());
        boolean t = false;
        FileInputStream fis = null;
        PreparedStatement ps = null;
        ConnectBD obj = new ConnectBD();
        try {
            if (obj.crearConexion()) {
                obj.getConexion().setAutoCommit(false);
                File file = new File(objP.getPlantilla());
                fis = new FileInputStream(file);
                ps = obj.getConexion().prepareStatement(sql);
                ps.setBinaryStream(1, fis, (int) file.length());
                ps.setString(2, objP.getCorreo());
                ps.setTimestamp(3, objP.getFecha());
                ps.executeUpdate();
                obj.getConexion().commit();
                t = true;
            }

        } catch (Exception ex) {
            t = false;
            System.out.println("Error " + ex.toString());
        } finally {
            try {
                ps.close();
                fis.close();
            } catch (Exception ex) {
                t = false;
                System.out.println("Errro " + ex);
            }
        }
        return t;
    }

}
