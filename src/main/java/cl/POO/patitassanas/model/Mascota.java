package cl.POO.patitassanas.model;

import java.util.ArrayList;

public abstract class Mascota implements Vacunable{
    private int id;
    private String nombre;
    private int meses;
    private double peso;
    private String estadoClinico;
    private ArrayList<String> vacunas;

    public Mascota(String nombre, int meses, double peso, String estadoClinico) {
        setNombre(nombre);
        setMeses(meses);
        setPeso(peso);
        setEstadoClinico(estadoClinico);
        this.vacunas = new ArrayList<>();
    }

    public Mascota(int id, String nombre, int meses, double peso, String estadoClinico) {
        setId(id);
        setNombre(nombre);
        setMeses(meses);
        setPeso(peso);
        setEstadoClinico(estadoClinico);
        this.vacunas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id < 0){
            throw new IllegalArgumentException("El id no puede ser menor a 0");
        }
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre == null || nombre.isEmpty()){
            throw new IllegalArgumentException("El nombre no debe estar vacio.");
        }
        this.nombre = nombre.trim();
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        if(meses < 0 || meses > 360){
            throw new IllegalArgumentException("Meses deben estar entre 0 y 360");
        }
        this.meses = meses;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if(peso < 0 || peso > 100){
            throw new IllegalArgumentException("El peso debe estar entre 0 y 100");
        }
        this.peso = peso;
    }

    public String getEstadoClinico() {
        return estadoClinico;
    }

    public void setEstadoClinico(String estadoClinico) {
        if(estadoClinico == null || estadoClinico.isEmpty()){
            throw new IllegalArgumentException("El estado clinico no debe estar vacio.");
        }
        this.estadoClinico = estadoClinico.trim();
    }

    public ArrayList<String> getVacunas() {
        return vacunas;
    }

    public void setVacunas(ArrayList<String> vacunas) {
        this.vacunas = vacunas;
    }

    public abstract int calcularPrecio(String condicional);
}
