package cl.POO.patitassanas.controller;

import cl.POO.patitassanas.model.Gato;
import cl.POO.patitassanas.model.Mascota;
import cl.POO.patitassanas.model.Perro;
import cl.POO.patitassanas.repository.MascotaRepository;
import cl.POO.patitassanas.repository.PersistenciaException;
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
    private MascotaRepository repository = new MascotaRepository();

    @FXML
    private void initialize(){
        cmbFiltroTipo.setItems(FXCollections.observableArrayList("Todas las especies","Perro","Gato"));
        cmbFiltroTipo.setValue("Todas las especies");
        // 2. Columnas de la tabla
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colMascota.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTutor.setCellValueFactory(new PropertyValueFactory<>("nombreTutor"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoClinico"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costoServicio"));

        // 3. Filtro de la tabla para buscar
        txtBuscar.textProperty().addListener((obs, anterior, actual) -> filtrarTabla());
        cmbFiltroTipo.valueProperty().addListener((obs, anterior, actual) -> filtrarTabla());

        cargarTabla();
    }

    //Abre el formulario para registrar una mascota
    @FXML
    private void onNuevo() {
        abrirFormulario(null);
    }

    // Editar un registro
    @FXML
    private void onEditar() {
        Mascota seleccionada = tblMascotas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarMensaje("Selecciona una mascota para editar.");
            return;
        }
        abrirFormulario(seleccionada);
    }

    // Elimina del repositorio una mascota
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
    // actualiza la tabla
    private void cargarTabla() {
        try {
            listaCompleta = repository.obtenerTodos();
            filtrarTabla();
        } catch (PersistenciaException e) {
            mostrarMensaje("Error al cargar datos: " + e.getMessage());
        }
    }
    // Es un filtro que deja en vista unicamente al texto buscado (codigo, nombre o tutor)
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
    //Metodo para actualizar la tabla con la suma total de los servicios
    private void actualizarTotales(List<Mascota> lista) {
        double totalCosto = 0;
        for (Mascota m : lista) {
            if(m instanceof Perro){
                totalCosto = totalCosto + m.calcularPrecio(((Perro) m).getTamano());
            } else if (m instanceof Gato) {
                totalCosto = totalCosto + m.calcularPrecio(((Gato) m).getEstiloVida());
            }

        }
        lblTotalMascotas.setText("Total Mascotas: " + lista.size());
        lblTotalCosto.setText("Total Estimado: $" + (int) totalCosto);
    }
    //Metodo para Abrir el formulario de la vista secundaria,
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
    //Metodo para mostrar mensajes
    private void mostrarMensaje(String texto) {
        if (lblMensaje != null) {
            lblMensaje.setText(texto);
        } else {
            System.out.println(texto);
        }
    }
}


