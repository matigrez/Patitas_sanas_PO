package cl.POO.patitassanas.controller;

import cl.POO.patitassanas.model.Gato;
import cl.POO.patitassanas.model.Perro;
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
    }

}

