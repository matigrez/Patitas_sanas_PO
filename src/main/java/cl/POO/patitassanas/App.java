package cl.POO.patitassanas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/main_view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Patitas Sanas");
        stage.setScene(scene);
        stage.show();
    }
}
