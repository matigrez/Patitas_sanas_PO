package cl.POO.patitassanas.controller;

import cl.POO.patitassanas.model.Gato;
import cl.POO.patitassanas.model.Mascota;
import cl.POO.patitassanas.model.Perro;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class MainController{

    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnNuevo;
    @FXML private ComboBox<String> cmbFiltroTipo;
    @FXML private TableColumn<Mascota, String> colCodigo;
    @FXML private TableColumn<Mascota, Number> colCosto;
    @FXML private TableColumn<Mascota, String> colEspecie;
    @FXML private TableColumn<Mascota, String> colEstado;
    @FXML private TableColumn<Mascota, String> colMascota;
    @FXML private TableColumn<Mascota, String> colTutor;
    @FXML private Label lblTotalCosto;
    @FXML private Label lblTotalMascotas;
    @FXML private TableView<Mascota> tblMascotas;
    @FXML private TextField txtBuscar;
    private List<Mascota> todaslasMascotas = new ArrayList<>();

    @FXML
    private void initialize(){
        cmbFiltroTipo.setEditable(FXCollections.observableArrayList("Todas las especies","Perro","Gato"));
        cmbFiltroTipo.setValue("Todas las especies");


    }

}
