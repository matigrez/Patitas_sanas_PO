package cl.POO.patitassanas.model;

import java.util.ArrayList;

public class Perro extends Mascota implements Vacunable{
    private static final int costoBase = 20000;
    private String tamano;
    private String adiestramiento;
    private boolean vacuna;

    public Perro(String nombre, int meses, double peso, ArrayList<String> vacunas, String tamano, String adiestramiento, boolean vacuna) {
        super(nombre, meses, peso, vacunas);
        setTamano(tamano);
        setAdiestramiento(adiestramiento);
        this.vacuna = vacuna;
    }

    public Perro(int id, String nombre, int meses, double peso, ArrayList<String> vacunas, String tamano, String adiestramiento, boolean vacuna) {
        super(id, nombre, meses, peso, vacunas);
        setTamano(tamano);
        setAdiestramiento(adiestramiento);
        this.vacuna = vacuna;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getAdiestramiento() {
        return adiestramiento;
    }

    public void setAdiestramiento(String adiestramiento) {
        this.adiestramiento = adiestramiento;
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
