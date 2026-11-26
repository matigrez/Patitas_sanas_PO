package cl.POO.patitassanas.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController{

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnNuevo;

    @FXML
    private ComboBox<String> cmbFiltroTipo;

    @FXML
    private TableColumn<?, ?> colCodigo;

    @FXML
    private TableColumn<?, ?> colCosto;

    @FXML
    private TableColumn<?, ?> colEspecie;

    @FXML
    private TableColumn<?, ?> colEstado;

    @FXML
    private TableColumn<?, ?> colMascota;

    @FXML
    private TableColumn<?, ?> colTutor;

    @FXML
    private Label lblTotalCosto;

    @FXML
    private Label lblTotalMascotas;

    @FXML
    private TableView<?> tblMascotas;

    @FXML
    private TextField txtBuscar;

    @FXML
    private void initialize(){


    }

}
