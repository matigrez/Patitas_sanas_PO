package cl.POO.patitassanas.model;

import java.util.ArrayList;

public abstract class Mascota implements Vacunable{
    private String codigo;
    private String especie;
    private String nombre;
    private int meses;
    private double peso;
    private String estadoClinico;
    private ArrayList<String> vacunas;

    public Mascota(String codigo,String especie, String nombre, int meses, double peso, String estadoClinico) {
        setCodigo(codigo);
        setEspecie(especie);
        setNombre(nombre);
        setMeses(meses);
        setPeso(peso);
        setEstadoClinico(estadoClinico);
        this.vacunas = new ArrayList<>();
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        if(especie == null || especie.isEmpty()){
            throw new IllegalArgumentException("La especie no debe ser nulo.");
        }
        this.especie = especie;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if(codigo == null || codigo.isEmpty()){
            throw new IllegalArgumentException("El codigo no puede ser nulo.");
        }
        this.codigo = codigo;
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

    public abstract int calcularPrecio(String condicional);
}
