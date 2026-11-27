package cl.POO.patitassanas.controller;

import cl.POO.patitassanas.model.Gato;
import cl.POO.patitassanas.model.Mascota;
import cl.POO.patitassanas.model.Perro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML private TableColumn<Mascota, String> colTutor;
    @FXML private Label lblTotalCosto;
    @FXML private Label lblTotalMascotas;
    @FXML private TableView<Mascota> tblMascotas;
    @FXML private TextField txtBuscar;
    private List<Mascota> listaCompleta = new ArrayList<>();

    @FXML
    private void initialize(){
        cmbFiltroTipo.setItems(FXCollections.observableArrayList("Todas las especies","Perro","Gato"));
        cmbFiltroTipo.setValue("Todas las especies");
        // 2. Conectar columnas con los getters de Mascota (getCodigo, getTipo, getNombre, etc.)
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colMascota.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTutor.setCellValueFactory(new PropertyValueFactory<>("nombreTutor"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoClinico"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costoServicio"));

        // 3. Filtro en tiempo real al escribir o cambiar el ComboBox
        txtBuscar.textProperty().addListener((obs, anterior, actual) -> filtrarTabla());
        cmbFiltroTipo.valueProperty().addListener((obs, anterior, actual) -> filtrarTabla());

        cargarTabla();
    }

    //
    @FXML
    private void onNuevo() {
        abrirFormulario(null);
    }

    //
    @FXML
    private void onEditar() {
        Mascota seleccionada = tblMascotas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarMensaje("Selecciona una mascota para editar.");
            return;
        }
        abrirFormulario(seleccionada);
    }

    // 
    @FXML
    private void onEliminar() {
        Mascota seleccionada = tblMascotas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarMensaje("Selecciona una mascota para eliminar.");
            return;
        }

        try {
            repository.eliminar(seleccionada.getCodigo());
            cargarTabla();
            mostrarMensaje("Mascota eliminada con exito!");
        } catch (PersistenciaException e) {
            mostrarMensaje("Error al eliminar: " + e.getMessage());
        }
    }

    private void cargarTabla() {
        try {
            listaCompleta = repository.obtenerTodos();
            filtrarTabla();
        } catch (PersistenciaException e) {
            mostrarMensaje("Error al cargar datos: " + e.getMessage());
        }
    }

    private void filtrarTabla() {
        String texto = txtBuscar.getText().trim().toLowerCase();
        String tipo = cmbFiltroTipo.getValue();

        ObservableList<Mascota> filtradas = FXCollections.observableArrayList();

        for (Mascota m : listaCompleta) {
            boolean coincideTexto = texto.isEmpty()
                    || m.getCodigo().toLowerCase().contains(texto)
                    || m.getNombre().toLowerCase().contains(texto)
                    || m.getTutorNombre().toLowerCase().contains(texto);

            boolean coincideTipo = tipo.equals("Todas las especies")
                    || m.getEspecie().equalsIgnoreCase(tipo);

            if (coincideTexto && coincideTipo) {
                filtradas.add(m);
            }
        }

        tblMascotas.setItems(filtradas);
        actualizarTotales(filtradas);
    }

    private void actualizarTotales(List<Mascota> lista) {
        double totalCosto = 0;
        for (Mascota m : lista) {
            totalCosto = totalCosto + m.calcularCostoServicio();
        }
        lblTotalMascotas.setText("Total Mascotas: " + lista.size());
        lblTotalCosto.setText("Total Estimado: $" + (int) totalCosto);
    }

    private void abrirFormulario(Mascota mascotaEditar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/form_view.fxml"));
            Scene scene = new Scene(loader.load());

            FormController controller = loader.getController();
            controller.inicializarDatos(repository, mascotaEditar);

            Stage stage = new Stage();
            stage.setTitle("Formulario Mascota");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            cargarTabla();
        } catch (IOException e) {
            mostrarMensaje("Error al abrir formulario: " + e.getMessage());
        }
    }

    private void mostrarMensaje(String texto) {
        if (lblMensaje != null) {
            lblMensaje.setText(texto);
        } else {
            System.out.println(texto);
        }
    }
}


