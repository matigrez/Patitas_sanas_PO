package cl.POO.patitassanas.model;

import java.util.ArrayList;

public class Gato extends Mascota implements Vacunable{
    public static final String especie = "Gato";
    private static final int costoBase = 18000;
    private String estiloVida;
    private String pelaje;
    private boolean vacuna;

    public Gato(String codigo, String especie,String nombre, int meses, double peso, String estadoClinico, Tutor tutor, String estiloVida, String pelaje) {
        super(codigo,especie, nombre, meses, peso, estadoClinico,tutor);
        setEstiloVida(estiloVida);
        setPelaje(pelaje);
        setVacuna(vacuna);
    }

    public String getEstiloVida() {
        return estiloVida;
    }

    public void setEstiloVida(String estiloVida) {
        if(estiloVida == null || estiloVida.isEmpty()){
            throw new IllegalArgumentException("El estilo de vida no debe ser nulo.");
        }
        this.estiloVida = estiloVida.trim();
    }

    public String getPelaje() {
        return pelaje;
    }

    public void setPelaje(String pelaje) {
        if(pelaje == null || pelaje.isEmpty()){
            throw new IllegalArgumentException("El pelaje no debe ser nulo.");
        }
        this.pelaje = pelaje.trim();
    }

    public boolean isVacuna() {
        return vacuna;
    }

    public void setVacuna(boolean vacuna) {
        this.vacuna = vacuna;
    }

    @Override
    public int calcularPrecio(String estiloVida) {
        if (estiloVida.equals("Outdoor")){
            return costoBase + 4000;
        } else if (estiloVida.equals("Indoor")) {
            return costoBase;
        }else {
            throw new IllegalArgumentException("Estilo de vida nulo.");
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
