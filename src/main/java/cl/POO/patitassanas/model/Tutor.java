package cl.POO.patitassanas.model;

import java.util.ArrayList;
import java.util.List;

public class Tutor {
    private String rut;
    private String nombre;
    private String correo;
    private String numero;
    private List<Mascota> mascotas = new ArrayList<>();

    public Tutor(String rut, String nombre, String correo, String numero, List<Mascota> mascotas) {
        this.rut = rut;
        this.nombre = nombre;
        this.correo = correo;
        this.numero = numero;
        this.mascotas = mascotas;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public void agregarMascotas(Mascota mascota){
        mascotas.add(mascota);
    }
}
