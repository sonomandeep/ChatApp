package it.mandeep.client.controller;

import it.mandeep.libreria.datastructures.Utente;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainController extends Controller {

    @FXML
    private VBox usersBox;

    @FXML
    private Label onlineLabel;

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField messageField;

    @FXML
    private Button sendMessageButton;

    @FXML
    private Label titleLabel;

    private Button button;
    private List<Button> buttons = new ArrayList<>();

    public void inviaMessaggio() {
        // TODO: Invia messaggio
        // this.getModel().inviaMessaggio();
    }

    public void inizializza() throws ExecutionException, InterruptedException {
        for (Utente u : getModel().getUtenti()) {
            button = new Button(u.getUsername());
            // TODO: Aggiungere lo stile in un file css esterno.
            button.setStyle("-fx-pref-width: 500px");
            buttons.add(button);
            usersBox.getChildren().add(button);
        }
        // TODO: Sostituire i pulsanti con un altro node dal quale sia possibile estrarre il testo. Deve essere un qualche
        // tipo di lista.
        for (Button b : buttons) {
            b.setOnAction(event -> {
                try {
                    if (getModel().isOnline(new Utente(button.getText(), "")))
                        onlineLabel.setText(button.getText() + " è online.");
                    else
                        onlineLabel.setText(button.getText() + " è offline.");
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
