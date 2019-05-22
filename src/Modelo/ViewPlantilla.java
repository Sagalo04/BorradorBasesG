/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Timestamp;

/**
 *
 * @author User
 */
public class ViewPlantilla {
    private int Id_Plantilla;
    private Timestamp Fecha;
    private String NombreCuenta;
    private String Nombre;
    private String Apellido;

    public ViewPlantilla(int Id_Plantilla, Timestamp Fecha, String NombreCuenta, String Nombre, String Apellido) {
        this.Id_Plantilla = Id_Plantilla;
        this.Fecha = Fecha;
        this.NombreCuenta = NombreCuenta;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
    }
    

    public int getId_Plantilla() {
        return Id_Plantilla;
    }

    public void setId_Plantilla(int Id_Plantilla) {
        this.Id_Plantilla = Id_Plantilla;
    }

    public Timestamp getFecha() {
        return Fecha;
    }

    public void setFecha(Timestamp Fecha) {
        this.Fecha = Fecha;
    }

    public String getNombreCuenta() {
        return NombreCuenta;
    }

    public void setNombreCuenta(String NombreCuenta) {
        this.NombreCuenta = NombreCuenta;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    @Override
    public String toString() {
        return "ViewPlantilla{" + "Id_Plantilla=" + Id_Plantilla + ", Fecha=" + Fecha + ", NombreCuenta=" + NombreCuenta + ", Nombre=" + Nombre + ", Apellido=" + Apellido + '}';
    }
    
    
    
}
