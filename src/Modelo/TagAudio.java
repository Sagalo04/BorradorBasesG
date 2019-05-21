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
public class TagAudio {

    private int id_TagAudio;
    private int id_Audio;
    private int id_CategoriaAudio;
    private String nombre_TagAudio;

    public TagAudio() {
    }

    public TagAudio(int id_TagAudio, int id_Audio, int id_CategoriaAudio, String nombre_TagAudio) {
        this.id_TagAudio = id_TagAudio;
        this.id_Audio = id_Audio;
        this.id_CategoriaAudio = id_CategoriaAudio;
        this.nombre_TagAudio = nombre_TagAudio;
    }

    public int getId_TagAudio() {
        return id_TagAudio;
    }

    public void setId_TagAudio(int id_TagAudio) {
        this.id_TagAudio = id_TagAudio;
    }

    public int getId_Audio() {
        return id_Audio;
    }

    public void setId_Audio(int id_Audio) {
        this.id_Audio = id_Audio;
    }

    public int getId_CategoriaAudio() {
        return id_CategoriaAudio;
    }

    public void setId_CategoriaAudio(int id_CategoriaAudio) {
        this.id_CategoriaAudio = id_CategoriaAudio;
    }

    public String getNombre_TagAudio() {
        return nombre_TagAudio;
    }

    public void setNombre_TagAudio(String nombre_TagAudio) {
        this.nombre_TagAudio = nombre_TagAudio;
    }

    @Override
    public String toString() {
        return "TagAudio{" + "id_TagAudio=" + id_TagAudio + ", id_Audio=" + id_Audio + ", id_CategoriaAudio=" + id_CategoriaAudio + ", nombre_TagAudio=" + nombre_TagAudio + '}';
    }

}
