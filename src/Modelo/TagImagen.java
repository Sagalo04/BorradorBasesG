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
public class TagImagen {
    private int id_TagImagen;
    private int id_Imagen;
    private int id_CategoriaImagen;
    private String nombre_TagImagen;

    public TagImagen() {
    }

    public TagImagen(int id_TagImagen, int id_Imagen, int id_CategoriaImagen, String nombre_TagImagen) {
        this.id_TagImagen = id_TagImagen;
        this.id_Imagen = id_Imagen;
        this.id_CategoriaImagen = id_CategoriaImagen;
        this.nombre_TagImagen = nombre_TagImagen;
    }

    public int getId_TagImagen() {
        return id_TagImagen;
    }

    public void setId_TagImagen(int id_TagImagen) {
        this.id_TagImagen = id_TagImagen;
    }

    public int getId_Imagen() {
        return id_Imagen;
    }

    public void setId_Imagen(int id_Imagen) {
        this.id_Imagen = id_Imagen;
    }

    public int getId_CategoriaImagen() {
        return id_CategoriaImagen;
    }

    public void setId_CategoriaImagen(int id_CategoriaImagen) {
        this.id_CategoriaImagen = id_CategoriaImagen;
    }

    public String getNombre_TagImagen() {
        return nombre_TagImagen;
    }

    public void setNombre_TagImagen(String nombre_TagImagen) {
        this.nombre_TagImagen = nombre_TagImagen;
    }

    @Override
    public String toString() {
        return "TagImagen{" + "id_TagImagen=" + id_TagImagen + ", id_Imagen=" + id_Imagen + ", id_CategoriaImagen=" + id_CategoriaImagen + ", nombre_TagImagen=" + nombre_TagImagen + '}';
    }
    
    
    
}
