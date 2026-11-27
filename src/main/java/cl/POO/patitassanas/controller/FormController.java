package cl.POO.patitassanas.controller;

import cl.POO.patitassanas.model.Gato;
import cl.POO.patitassanas.model.Mascota;
import cl.POO.patitassanas.model.Perro;
import cl.POO.patitassanas.model.Tutor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class FormController {

    @FXML private VBox boxPerro;
    @FXML private VBox boxGato;
    @FXML private Button btnBuscarTutor;
    @FXML private Button btnCancelar;
    @FXML private Button btnGuardar;
    @FXML private CheckBox chkVacunable;
    @FXML private TextField txtNombreVacuna;
    @FXML private ComboBox<String> cmbEspecie;
    @FXML private ComboBox<String> cmbEstado;
    @FXML private ComboBox<String> cmbEstiloVida;
    @FXML private ComboBox<String> cmbNivelAd;
    @FXML private ComboBox<String> cmbTamanoPerro;
    @FXML private ComboBox<String> cmbTipoPelaje;
    @FXML private Label lblTituloFormulario;
    @FXML private TextField txtCodeficha;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtEdadmeses;
    @FXML private TextField txtNombreTutor;
    @FXML private TextField txtNombremascota;
    @FXML private TextField txtPeso;
    @FXML private TextField txtRutTutor;
    @FXML private TextField txtTelefono;
    @FXML private Label lblError;
    private Mascota mascotaRegistrada;
    private Tutor tutorRegistrado;

    @FXML
    public void initialize(){
        cmbEspecie.setItems(FXCollections.observableArrayList("Perro", "Gato"));
        cmbEstado.setItems(FXCollections.observableArrayList("Ingresado", "En Consulta", "En Observacion", "Alta Medica"));
        cmbTamanoPerro.setItems(FXCollections.observableArrayList("Pequeño","Mediano","Grande"));
        cmbNivelAd.setItems(FXCollections.observableArrayList("Basico","Intermedio","Avanzado"));
        cmbTipoPelaje.setItems(FXCollections.observableArrayList("Corto","Regular","Largo"));
        cmbEstiloVida.setItems(FXCollections.observableArrayList("Outdoor","Indoor"));
        boxPerro.setDisable(true);
        boxGato.setDisable(true);
        txtNombreVacuna.setDisable(true);
        lblError.setText("");
    }

    @FXML
    private void actualizarBox() {
        String tipo = cmbEspecie.getValue();
        boxPerro.setDisable(!"Perro".equals(tipo));
        boxGato.setDisable(!"Gato".equals(tipo));
    }

    @FXML
    private void actualizarTextField(){
        txtNombreVacuna.setDisable(!chkVacunable.isSelected());
    }


    @FXML
    public void registrar(){
        lblError.setText("");
        try {
            String rut = txtRutTutor.getText();
            if (rut == null || rut.isBlank()) {
                lblError.setText("El Rut del tutor es obligatorio");
                return;
            }
            String nombreTutor = txtNombreTutor.getText();
            if (nombreTutor == null || nombreTutor.isBlank()) {
                lblError.setText("El Nombre del tutor es obligatorio");
                return;
            }
            String correo = txtCorreo.getText();
            if (correo == null || correo.isBlank()) {
                lblError.setText("El Correo del tutor es obligatorio");
                return;
            }
            String telefono = txtTelefono.getText();
            if (telefono == null || telefono.isBlank()) {
                lblError.setText("El Telefono del tutor es obligatorio");
                return;
            }
            Tutor tutor = new Tutor(rut,nombreTutor,correo,telefono);
            String codigo = txtCodeficha.getText();
            if (codigo == null || codigo.isBlank()) {
                lblError.setText("El Codigo es obligatorio");
                return;
            }
            String nombreMascota = txtNombremascota.getText();
            if (nombreMascota == null || nombreMascota.isBlank()) {
                lblError.setText("El Nombre de la mascota es obligatorio");
                return;
            }

            if (txtEdadmeses.getText() == null || txtEdadmeses.getText().isBlank()) {
                lblError.setText("La Edad en meses es obligatorio");
                return;
            }
            int meses = calcularMeses();

            if (txtPeso.getText() == null || txtPeso.getText().isBlank()) {
                lblError.setText("El Peso es obligatorio");
                return;
            }
            double peso = calcularPeso();

            String estado = cmbEstado.getValue();
            if  (estado == null || estado.isBlank()) {
                lblError.setText("El estado de la mascota es obligatoria");
                return;
            }
            String especie = cmbEspecie.getValue();
            if (especie == null || especie.isBlank()) {
                lblError.setText("La especie es obligatorio");
                return;
            }
            if(especie.equals("Perro")){
                String tamano = cmbTamanoPerro.getValue();
                if (tamano == null || tamano.isBlank()) {
                    lblError.setText("El tamano es obligatorio");
                    return;
                }
                String adiestramiento = cmbNivelAd.getValue();
                if (adiestramiento == null || adiestramiento.isBlank()) {
                    lblError.setText("La casilla Adiestramiento es obligatoria");
                    return;
                }
                Perro perro = new Perro(codigo, especie, nombreMascota,meses,peso,estado,tutor,tamano,adiestramiento);
                if(chkVacunable.isSelected()){
                    perro.setVacunable(chkVacunable.isSelected());
                    String nombreVacuna = txtNombreVacuna.getText().trim();
                    if(nombreVacuna.isBlank() || nombreVacuna.isEmpty()){
                        lblError.setText("El nombre de la vacuna no puede ser nulo.");
                        return;
                    }
                    perro.aplicarVacuna(txtNombreVacuna.getText());

                }
                tutor.agregarMascotas(perro);
                this.tutorRegistrado = tutor;
                this.mascotaRegistrada = perro;

            } else if (especie.equals("Gato")) {
                String pelaje = cmbTipoPelaje.getValue();
                if (pelaje == null || pelaje.isBlank()) {
                    lblError.setText("El tipo de pelaje es obligatorio");
                    return;
                }
                String estiloVida = cmbEstiloVida.getValue();
                if (estiloVida == null || estiloVida.isBlank()) {
                    lblError.setText("La casilla estilo de vida es obligatoria");
                    return;
                }
                Gato gato = new Gato(codigo, especie, nombreMascota,meses,peso,estado,tutor,pelaje,estiloVida);
                if(chkVacunable.isSelected()){
                    gato.setVacunable(chkVacunable.isSelected());
                    String nombreVacuna = txtNombreVacuna.getText().trim();
                    if(nombreVacuna.isBlank() || nombreVacuna.isEmpty()){
                        lblError.setText("El nombre de la vacuna no puede ser nulo.");
                        return;
                    }
                    gato.aplicarVacuna(nombreVacuna);
                }
                tutor.agregarMascotas(gato);
                this.tutorRegistrado = tutor;
                this.mascotaRegistrada = gato;
            }else{
                throw new IllegalArgumentException("Especie no puede estar nula");
            }

        } catch (IllegalArgumentException e) {
            lblError.setText(e.getMessage());
        }
    }

    public int calcularMeses(){
        try {
            return Integer.parseInt(txtEdadmeses.getText().trim());
        } catch (NumberFormatException e) {
            txtEdadmeses.requestFocus();
            throw new IllegalArgumentException("Los meses deben estar entre 0 y 360.");
        }
    }

    public double calcularPeso(){
        try {
            return Double.parseDouble(txtPeso.getText().trim());
        } catch (NumberFormatException e) {
            txtPeso.requestFocus();
            throw new IllegalArgumentException("El peso debe estar entre 0 y 100 Kg");
        }
    }

    public Tutor getTutorRegistrado(){
        return tutorRegistrado;
    }

    public Mascota getMascotaRegistrada(){
        return mascotaRegistrada;
    }

}

