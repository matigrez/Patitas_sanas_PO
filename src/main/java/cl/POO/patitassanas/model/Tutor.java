package cl.POO.patitassanas.model;

import java.util.ArrayList;
import java.util.List;

public class Tutor {
    private String rut;
    private String nombre;
    private String correo;
    private String numero;
    private List<Mascota> mascotas;

    public Tutor(String rut, String nombre, String correo, String numero) {
        setRut(rut);
        setNombre(nombre);
        setCorreo(correo);
        setNumero(numero);
        this.mascotas = new ArrayList<>();
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        if(rut == null || rut.isEmpty()){
            throw new IllegalArgumentException("El rut no debe ser nulo.");
        }
        this.rut = rut.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre == null || nombre.isEmpty()){
            throw new IllegalArgumentException("El nombre no debe ser nulo.");
        }
        this.nombre = nombre.trim();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if(correo == null || correo.isEmpty()){
            throw new IllegalArgumentException("El correo no debe ser nulo.");
        }
        this.correo = correo.trim();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if(numero == null || numero.isEmpty()){
            throw new IllegalArgumentException("El numero no debe ser nulo.");
        }
        this.numero = numero.trim();
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
