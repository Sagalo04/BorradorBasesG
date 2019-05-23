/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Audio;
import Modelo.Cuenta;
import Modelo.Imagen;
import Modelo.Plantilla;
import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class ControlCuenta {

    //Sql para insertar cuenta
    boolean insertarCuenta(Cuenta objC) {

        long t = objC.getFecha().getTime();

        Date date = new Date(t);

        String Correo, Nombre2, Apellido2, NombreCuenta, Nombre, Apellido, Contraseña;

        if ("".equals(objC.getCorreo()) || objC.getCorreo()== null) {
            Correo = null;
        } else {
            Correo = "'" + objC.getCorreo() + "'";
        }

        if ("".equals(objC.getNombre2()) || objC.getNombre2() == null) {
            Nombre2 = null;
        } else {
            Nombre2 = "'" + objC.getNombre2() + "'";
        }

        if ("".equals(objC.getApellido2()) || objC.getApellido2() == null) {
            Apellido2 = null;
        } else {
            Apellido2 = "'" + objC.getApellido2() + "'";
        }

        if ("".equals(objC.getNombreCuenta()) || objC.getNombreCuenta() == null) {
            NombreCuenta = null;
        } else {
            NombreCuenta = "'" + objC.getNombreCuenta() + "'";
        }

        if ("".equals(objC.getNombre()) || objC.getNombre() == null) {
            Nombre = null;
        } else {
            Nombre = "'" + objC.getNombre() + "'";
        }

        if ("".equals(objC.getApellido()) || objC.getApellido() == null) {
            Apellido = null;
        } else {
            Apellido = "'" + objC.getApellido() + "'";
        }

        if ("".equals(objC.getContraseña()) || objC.getContraseña() == null) {
            Contraseña = null;
        } else {
            Contraseña = "'" + objC.getContraseña() + "'";
        }

        //System.out.println(date);
        String sql = "Insert into Cuenta(correo, nombrecuenta, nombre, nombre2, apellido, apellido2, contraseña,fechaCreacion) "
                + "values(" + Correo + ", " + NombreCuenta + ", " + Nombre + ", "
                + Nombre2 + ", " + Apellido + ", " + Apellido2 + ", " + Contraseña + " , '" + date + "');";

        boolean corr = false;

        try {
            corr = objC.insertarCuenta(sql);
        } catch (Exception e) {
            System.out.println("ERROR CONTROL");
        }

        return corr;
    }

    //Sql para actualizar la cuenta segun el correo
    boolean UpdateCuenta(Cuenta objc) {

        String Nombre2, Apellido2, NombreCuenta, Nombre, Apellido, Contraseña;

        if ("".equals(objc.getNombre2()) || objc.getNombre2() == null) {
            Nombre2 = null;
        } else {
            Nombre2 = "'" + objc.getNombre2() + "'";
        }

        if ("".equals(objc.getApellido2()) || objc.getApellido2() == null) {
            Apellido2 = null;
        } else {
            Apellido2 = "'" + objc.getApellido2() + "'";
        }

        if ("".equals(objc.getNombreCuenta()) || objc.getNombreCuenta() == null) {
            NombreCuenta = null;
        } else {
            NombreCuenta = "'" + objc.getNombreCuenta() + "'";
        }

        if ("".equals(objc.getNombre()) || objc.getNombre() == null) {
            Nombre = null;
        } else {
            Nombre = "'" + objc.getNombre() + "'";
        }

        if ("".equals(objc.getApellido()) || objc.getApellido() == null) {
            Apellido = null;
        } else {
            Apellido = "'" + objc.getApellido() + "'";
        }

        if ("".equals(objc.getContraseña()) || objc.getContraseña() == null) {
            Contraseña = null;
        } else {
            Contraseña = "'" + objc.getContraseña() + "'";
        }

        String sql = "Update Cuenta SET nombreCuenta = " + objc.getNombreCuenta() + ", nombre = " + objc.getNombre() + ", nombre2 = " + Nombre2 + ""
                + ", apellido = " + objc.getApellido() + ", apellido2 = " + Apellido2 + ", contraseña = " + Contraseña + " "
                + "WHERE correo = '" + objc.getCorreo() + "';";

        boolean corr = false;

        corr = objc.UpdateCuenta(sql);

        return corr;

    }

    //Sql para actualizar la cuenta segun el correo
    boolean IniciarSesion(Cuenta objc) {
        String sql = "Update Cuenta SET nombreCuenta = '" + objc.getNombreCuenta() + "', nombre = '" + objc.getNombre() + "', nombre2 = '" + objc.getNombre2() + "'"
                + ", apellido = '" + objc.getApellido() + "', apellido2 = '" + objc.getApellido2() + "', contraseña = '" + objc.getContraseña() + "' "
                + "WHERE correo = '" + objc.getCorreo() + "';";

        boolean corr = false;

        corr = objc.UpdateCuenta(sql);

        return corr;

    }

    boolean insertarImagen(Imagen objI) {
        boolean t = false;
        String sql = "insert into Imagen(Imagen,correo,fecha)" + " values(?,?,?)";

        t = objI.insertarImagen(objI, sql);

        return t;
    }

    boolean insertarAudio(Audio objA) {
        boolean t = false;
        String sql = "insert into Audio(Audio,correo,fecha)" + " values(?,?,?)";

        t = objA.insertarAudio(objA, sql);

        return t;
    }

    boolean insertarPlantilla(Plantilla objP) {
        boolean t = false;
        String sql = "insert into Plantilla(plantilla,correo,fecha)" + " values(?,?,?)";

        t = objP.insertarPlantilla(objP, sql);

        return t;
    }

}
