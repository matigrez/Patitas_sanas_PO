package cl.POO.patitassanas.model;

import java.util.ArrayList;

public class Gato extends Mascota implements Vacunable{
    private static final int costoBase = 18000;
    private String estiloVida;
    private boolean vacuna;

    public Gato(String nombre, int meses, double peso, ArrayList<String> vacunas, String estiloVida, boolean vacuna) {
        super(nombre, meses, peso, vacunas);
        this.estiloVida = estiloVida;
        this.vacuna = vacuna;
    }

    public String getEstiloVida() {
        return estiloVida;
    }

    public void setEstiloVida(String estiloVida) {
        this.estiloVida = estiloVida;
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
