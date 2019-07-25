package it.mandeep.client.controller;

import it.mandeep.libreria.datastructures.Utente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController extends Controller {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label loginResultLabel;

    private Utente utente;

    @FXML
    public void login() {
        if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            loginResultLabel.setText("Compila tutti i campi.");
            return;
        }

        utente = new Utente(usernameTextField.getText(), passwordTextField.getText());
        this.getModel().login(utente);
    }

}
