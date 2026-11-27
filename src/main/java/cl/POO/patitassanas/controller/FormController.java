package cl.POO.patitassanas.controller;

import cl.POO.patitassanas.model.Gato;
import cl.POO.patitassanas.model.Perro;
import cl.POO.patitassanas.model.Tutor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class FormController {

    @FXML
    private VBox boxPerro;

    @FXML
    private VBox boxGato;

    @FXML
    private Button btnBuscarTutor;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private CheckBox chkvacunable;

    @FXML
    private TextField txtNombreVacuna;

    @FXML
    private ComboBox<String> cmbEspecie;

    @FXML
    private ComboBox<String> cmbEstado;

    @FXML
    private ComboBox<String> cmbEstiloVida;

    @FXML
    private ComboBox<String> cmbNivelAd;

    @FXML
    private ComboBox<String> cmbTamanoPerro;

    @FXML
    private ComboBox<String> cmbTipoPelaje;

    @FXML
    private Label lblTituloFormulario;

    @FXML
    private TextField txtCodeficha;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtEdadmeses;

    @FXML
    private TextField txtNombreTutor;

    @FXML
    private TextField txtNombremascota;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtRutTutor;

    @FXML
    private TextField txtTelefono;

    @FXML
    public void initialize(){
        cmbEspecie.setItems(FXCollections.observableArrayList(Perro.especie, Gato.especie));
        cmbEstado.setItems(FXCollections.observableArrayList("Ingresado", "En Consulta", "En Observacion", "Alta Medica"));
        cmbTamanoPerro.setItems(FXCollections.observableArrayList("Pequeño","Mediano","Grande"));
        cmbNivelAd.setItems(FXCollections.observableArrayList("Basico","Intermedio","Avanzado"));
        cmbTipoPelaje.setItems(FXCollections.observableArrayList("Corto","Regular","Largo"));
        cmbEstiloVida.setItems(FXCollections.observableArrayList("Outdoor","Indoor"));
    }

    @FXML
    private void actualizarBox() {
        String tipo = cmbEspecie.getValue();
        // Deshabilitar el contenedor deshabilita a sus controles hijos.
        // Los dos grupos permanecen visibles: no usamos setVisible ni setManaged.
        boxPerro.setDisable(!"Perro".equals(tipo));
        boxGato.setDisable(!"Gato".equals(tipo));
    }

    @FXML
    private void actualizarTextField(){
        txtNombreVacuna.setDisable(!chkvacunable.isSelected());
    }


    @FXML
    public void registrar(){
        String rut = txtRutTutor.getText();
        String nombreTutor = txtNombreTutor.getText();
        String correo = txtCorreo.getText();
        String telefono = txtTelefono.getText();
        Tutor tutor = new Tutor(rut,nombreTutor,correo,telefono);
        String nombreMascota = txtNombremascota.getText();
        int meses = calcularMeses();
        double peso = calcularPeso();
        String estado = cmbEstado.getValue();
        String especie = cmbEspecie.getValue();
        if(especie.equals("Perro")){
            String tamano = cmbTamanoPerro.getValue();
            String adiestramiento = cmbNivelAd.getValue();
            Perro perro = new Perro(nombreMascota,meses,peso,estado,tamano,adiestramiento);
            if(chkvacunable.isSelected()){
                perro.setVacunable(chkvacunable.isSelected());
                perro.aplicarVacuna(txtNombreVacuna.getText());
            }
            tutor.agregarMascotas(perro);

        } else if (especie.equals("Gato")) {
            String pelaje = cmbTipoPelaje.getValue();
            String estiloVida = cmbEstiloVida.getValue();
            Gato gato = new Gato(nombreMascota,meses,peso,estado,pelaje,estiloVida);
            if(chkvacunable.isSelected()){
                gato.setVacunable(chkvacunable.isSelected());
                String nombreVacuna = txtNombreVacuna.getText().trim();
                if(nombreVacuna == null || nombreVacuna.isEmpty()){
                    throw new IllegalArgumentException("El nombre de la vacuna no puede ser nulo.");
                }
                gato.aplicarVacuna(nombreVacuna);
            }
            tutor.agregarMascotas(gato);
        }else{
            throw new IllegalArgumentException("Especie no puede estar nula");
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

}

