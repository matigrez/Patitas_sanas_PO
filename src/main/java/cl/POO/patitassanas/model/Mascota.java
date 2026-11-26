package cl.POO.patitassanas.model;

import java.util.ArrayList;

public abstract class Mascota implements Vacunable{
    private String nombre;
    private int meses;
    private double peso;
    private ArrayList<String> vacunas = new ArrayList<>();

    public Mascota() {
    }

    public Mascota(String nombre, int meses, double peso, ArrayList<String> vacunas) {
        this.nombre = nombre;
        this.meses = meses;
        this.peso = peso;
        this.vacunas = vacunas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre == null || nombre.isEmpty()){
            throw new IllegalArgumentException("El nombre no debe estar vacio.");
        }
        this.nombre = nombre;
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

    public ArrayList<String> getVacunas() {
        return vacunas;
    }

    public void setVacunas(ArrayList<String> vacunas) {
        this.vacunas = vacunas;
    }

    public abstract int calcularPrecio(String condicional);
}
