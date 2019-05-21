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
public class CategoriaAudio {
    private int id_CategoriaAudio;
    private String nombre_CategoriaAudio;

    public CategoriaAudio() {
    }

    public CategoriaAudio(int id_CategoriaAudio, String nombre_CategoriaAudio) {
        this.id_CategoriaAudio = id_CategoriaAudio;
        this.nombre_CategoriaAudio = nombre_CategoriaAudio;
    }

    public int getId_CategoriaAudio() {
        return id_CategoriaAudio;
    }

    public void setId_CategoriaAudio(int id_CategoriaAudio) {
        this.id_CategoriaAudio = id_CategoriaAudio;
    }

    public String getNombre_CategoriaAudio() {
        return nombre_CategoriaAudio;
    }

    public void setNombre_CategoriaAudio(String nombre_CategoriaAudio) {
        this.nombre_CategoriaAudio = nombre_CategoriaAudio;
    }

    @Override
    public String toString() {
        return "CategoriaAudio{" + "id_CategoriaAudio=" + id_CategoriaAudio + ", nombre_CategoriaAudio=" + nombre_CategoriaAudio + '}';
    }
    
    
    
}
