package it.mandeep.client.loader;

import it.mandeep.client.controller.MainController;
import it.mandeep.client.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
        Parent root = loader.load();

        MainController controller = loader.getController();
        controller.setModel(new Model());
        controller.getModel().inizializza();

        stage.setScene(new Scene(root));
        stage.show();
    }
}
