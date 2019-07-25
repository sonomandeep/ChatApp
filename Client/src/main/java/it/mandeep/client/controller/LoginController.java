package it.mandeep.client.controller;

import it.mandeep.client.model.Model;
import it.mandeep.libreria.datastructures.Utente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends Controller {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label loginResultLabel;

    private Utente utente;

    @FXML
    public void login() throws IOException {
        if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            loginResultLabel.setText("Compila tutti i campi.");
            return;
        }

        utente = new Utente(usernameTextField.getText(), passwordTextField.getText());
        this.getModel().login(utente);

        // TODO: in caso di login avvenuto con successo aprire la finestra principale della chat
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        MainController controller = loader.getController();
        controller.setModel(new Model());

        stage.show();
        closeApp();*/

    }

    public void closeApp() {
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.close();
    }

}
