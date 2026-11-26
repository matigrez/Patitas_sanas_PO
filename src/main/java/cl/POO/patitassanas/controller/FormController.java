package cl.POO.patitassanas.controller;

import cl.POO.patitassanas.model.Gato;
import cl.POO.patitassanas.model.Perro;
import cl.POO.patitassanas.model.Tutor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    public void registrar(){
        String rut = txtRutTutor.getText();
        String nombre = txtNombreTutor.getText();
        String correo = txtCorreo.getText();
        String telefono = txtTelefono.getText();
        Tutor tutor = new Tutor(rut,nombre,correo,telefono);
        String especie = cmbEspecie.getValue();
        if(especie.equals("Perro")){
            
        } else if (especie.equals("Gato")) {
            
        }else{
            throw new IllegalArgumentException("Especie no puede estar nula");
        }

    }

}

