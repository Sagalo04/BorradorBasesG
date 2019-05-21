/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Usuario
 */
public class CategoriaImagen {

    private int id_CategoriaImagen;
    private String nombre_CategoriaImagen;

    public CategoriaImagen() {
    }

    public CategoriaImagen(int id_CategoriaImagen, String nombre_CategoriaImagen) {
        this.id_CategoriaImagen = id_CategoriaImagen;
        this.nombre_CategoriaImagen = nombre_CategoriaImagen;
    }

    public int getId_CategoriaImagen() {
        return id_CategoriaImagen;
    }

    public void setId_CategoriaImagen(int id_CategoriaImagen) {
        this.id_CategoriaImagen = id_CategoriaImagen;
    }

    public String getNombre_CategoriaImagen() {
        return nombre_CategoriaImagen;
    }

    public void setNombre_CategoriaImagen(String nombre_CategoriaImagen) {
        this.nombre_CategoriaImagen = nombre_CategoriaImagen;
    }

    @Override
    public String toString() {
        return "CategoriaImagen{" + "id_CategoriaImagen=" + id_CategoriaImagen + ", nombre_CategoriaImagen=" + nombre_CategoriaImagen + '}';
    }
    
    
    
}
