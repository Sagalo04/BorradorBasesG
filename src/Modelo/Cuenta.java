/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class Cuenta {

    private String correo;
    private String nombreCuenta;
    private String nombre;
    private String nombre2;
    private String apellido;
    private String apellido2;
    private String contraseña;
    private Date fecha;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    

    public Cuenta() {
    }

    public Cuenta(String correo, String nombreCuenta, String nombre, String apellido, String contraseña) {
        this.correo = correo;
        this.nombreCuenta = nombreCuenta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
    }

    public Cuenta(String correo, String nombreCuenta, String nombre, String nombre2, String apellido, String apellido2, String contraseña,Date fecha) {
        this.correo = correo;
        this.nombreCuenta = nombreCuenta;
        this.nombre = nombre;
        this.nombre2 = nombre2;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.contraseña = contraseña;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "correo=" + correo + ", nombreCuenta=" + nombreCuenta + ", nombre=" + nombre + ", nombre2=" + nombre2 + ", apellido=" + apellido + ", apellido2=" + apellido2 + ", contrase\u00f1a=" + contraseña + '}';
    }

    //metodo para ingresar una cuenta nueva
    public boolean insertarCuenta(String sql) {

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
                System.out.println("ERROR EN CLASE CUENTA" + ex);
                //ex.printStackTrace();
                f = false;
            }
        }
        return f;
    }

    //Pendiente
    public boolean UpdateCuenta(String sql) {

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
                ex.printStackTrace();
                f = false;
            }
        }
        return f;
        
    }

}
