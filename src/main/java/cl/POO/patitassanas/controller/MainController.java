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
        //Se llena el combobox con las opciones presentes
        cmbFiltroTipo.setItems(FXCollections.observableArrayList("Todas las especies","Perro","Gato"));
        cmbFiltroTipo.setValue("Todas las especies");
        //Se conectan las columnas con la clase mascota
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colMascota.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTutor.setCellValueFactory(new PropertyValueFactory<>("nombreTutor"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
    }

    @FXML private void onNuevo(){
            abrirFormulario(null);
    }
    @FXML private void onEditar(){
        Mascota seleccionada =  tblMascotas.getSelectionModel().getSelectedItem();
        if(seleccionada == null){
            mostrarMensaje("Seleccionada una mascota para editar");
            return;
        }
        abrirFormulario(seleccionada);
    }
    @FXML private void onEliminar(){
        Mascota seleccionada =  tblMascotas.getSelectionModel().getSelectedItem();
        if (seleccionada == null){
            mostrarMensaje("Selecciona una mascota para eliminar");
            return;
        }
        try{
            repository.eliminar(seleccionada.getId());
            cargarTabla();
            mostrarMensaje("Mascota eliminada con exito!");
        } catch (PersistenciaException e) {
            mostrarMensaje("Error al eliminar: "+ e.getMessage());
        }
    }
    private void cargarTabla(){
        try{
            listaCompleta = repository.obtenerTodos();
            filtrarTabla();
        } catch (PersistenciaException e) {
            mostrarMensaje("Error al cargar datos:"+e.getMessage());
        }
    }
    private void filtrarTabla(){
        String texto = txtBuscar.getText().trim().toLowerCase();
        String tipo = cmbFiltroTipo.getValue();
        ObservableList<Mascota> filtradas = FXCollections.observableArrayList();

        for (Mascota mascota : listaCompleta){
            boolean coincideTexto = texto.isEmpty()
                    ||mascota.getCodigo().toLowerCase().contains(texto)
                    ||mascota.getNombre().toLowerCase().contains(texto)
                    ||mascota.getTutor().toLowerCase().contains(texto);
            boolean coincideTipo = tipo.equals("Todas las especies")
                    ||mascota.getTipo().equalsIgnoreCase(tipo);
            if(coincideTexto && coincideTipo){
                filtradas.add(mascota);
            }
        }
        tblMascotas.setItems(filtradas);
        actualizarTotales(filtradas);
    }
    private  void actualizarTotales(ObservableList<Mascota> lista{
        double totalCosto = 0;
        for (Mascota mascota : lista){
            totalCosto = totalCosto + mascota.calcularPrecio();
        }
        lblTotalMascotas.setText("Total Mascotas:"+lista.size());
        lblTotalCosto.setText("Total Costo:"+(int)totalCosto);
    }
    private void abrirFormulario(Mascota mascotaEditar){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/form_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            FormController controller = fxmlLoader.getController();
            controller.inicializarDatos(repository,mascotaEditar);

            Stage stage = new Stage();
            stage.setTitle("Formulario de Mascota");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            cargarTabla();
        } catch (IOException e) {
            mostrarMensaje("Error al abrir el formulario:"+e.getMessage());
        }
    }
    private void mostrarMensaje(String texto){
        if(lblMensaje != null){
            lblMensaje.setText(texto);
        }else{
            System.out.println(texto);
        }

    }


}
