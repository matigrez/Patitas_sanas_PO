package cl.POO.patitassanas.model;

import java.util.ArrayList;

public class Perro extends Mascota implements Vacunable{ ;
    private static final int costoBase = 20000;
    private String tamano;
    private String adiestramiento;
    private boolean vacuna;

    public Perro(String codigo, String especie, String nombre, int meses, double peso, String estadoClinico,Tutor tutor, String tamano, String adiestramiento) {
        super(codigo, especie, nombre, meses, peso, estadoClinico);
        setTamano(tamano);
        setAdiestramiento(adiestramiento);
        setVacuna(vacuna);
    }



    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        if(tamano == null || tamano.isEmpty()){
            throw new IllegalArgumentException("El tamaño no debe ser nulo.");
        }
        this.tamano = tamano.trim();
    }

    public String getAdiestramiento() {

        return adiestramiento;
    }

    public void setAdiestramiento(String adiestramiento) {
        if(adiestramiento == null || adiestramiento.isEmpty()){
            throw new IllegalArgumentException("El adiestramiento no debe ser nulo.");
        }
        this.adiestramiento = adiestramiento.trim();
    }

    public boolean isVacuna() {
        return vacuna;
    }

    public void setVacuna(boolean vacuna) {
        this.vacuna = vacuna;
    }

    @Override
    public int calcularPrecio(String tamano) {
        if (tamano.equals("Grande")){
            return costoBase + 5000;
        } else if (tamano.equals("Mediano")) {
            return costoBase + 2500;
        } else if (tamano.equals("Pequeño")) {
            return costoBase;
        }else {
            throw new IllegalArgumentException("Tamaño nulo.");
        }
    }

    @Override
    public boolean isVacunable() {
        return vacuna;
    }

    @Override
    public void setVacunable(boolean vacuna) {
        this.vacuna = vacuna;
    }

    @Override
    public void aplicarVacuna(String nombreVacuna) {
        getVacunas().add(nombreVacuna);

    }

}
