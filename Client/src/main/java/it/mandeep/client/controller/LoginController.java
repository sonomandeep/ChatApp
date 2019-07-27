package it.mandeep.client.controller;

import it.mandeep.client.model.Model;
import it.mandeep.libreria.datastructures.Utente;
import it.mandeep.libreria.network.risposta.Risposta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class LoginController extends Controller {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label loginResultLabel;

    private Utente utente;

    @FXML
    public void login() throws IOException, ExecutionException, InterruptedException {
        if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            loginResultLabel.setText("Compila tutti i campi.");
            return;
        }

        utente = new Utente(usernameTextField.getText(), passwordTextField.getText());
        Risposta risposta = this.getModel().login(utente);

        if (risposta.getRisultatoRisposta() == 1) {
            loginResultLabel.setText("La password non corrisponde allo username.");
            return;
        }

        if (risposta.getRisultatoRisposta() == 2) {
            loginResultLabel.setText("Credenziali errate.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        MainController controller = loader.getController();
        controller.setModel(new Model());

        // FixMe: Trovare un metodo migliore per inizializzare il controller ed il model.
        controller.getModel().inizializza();
        controller.inizializza();

        stage.show();
        closeApp();
    }

    public void closeApp() {
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.close();
    }

}
