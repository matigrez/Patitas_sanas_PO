package cl.POO.patitassanas.controller;

import cl.POO.patitassanas.model.Gato;
import cl.POO.patitassanas.model.Mascota;
import cl.POO.patitassanas.model.Perro;
import cl.POO.patitassanas.model.Tutor;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
    @FXML private TableColumn<Tutor, String> colTutor;
    @FXML private Label lblTotalCosto;
    @FXML private Label lblTotalMascotas;
    @FXML private TableView<Mascota> tblMascotas;
    @FXML private TextField txtBuscar;
    private List<Mascota> listaMascotas = FXCollections.observableArrayList();
    private List<Tutor> listaTutor = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        cmbFiltroTipo.setItems(FXCollections.observableArrayList("Todas las especies","Perro","Gato"));
        colCodigo.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getCodigo()));
        colEspecie.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue()));
        colEstado.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getEstadoClinico()));
        colMascota.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getNombre()));
        colTutor.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getNombre()));

        btnNuevo.setOnAction(event -> abrirVistaRegistro());

    }

    private void abrirVistaRegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/form_view.fxml"));
            Parent root = loader.load();
            FormController formController = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Registro de Ficha Clínica");
            stage.setScene(new Scene(root));

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            stage.showAndWait();
            Tutor nuevoTutor = formController.getTutorRegistrado();
            if (nuevoTutor != null) {
                listaTutor.add(nuevoTutor);
            }
            Mascota nuevaMascota = formController.getMascotaRegistrada();
            if (nuevaMascota != null) {
                listaMascotas.add(nuevaMascota);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
