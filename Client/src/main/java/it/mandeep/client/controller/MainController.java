package it.mandeep.client.controller;

import it.mandeep.libreria.datastructures.Utente;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.concurrent.ExecutionException;

public class MainController extends Controller {

    @FXML
    private TableView<Utente> utentiOnlineTable;

    @FXML
    private TableColumn<Utente, String> utentiColumn;

    @FXML
    private Label onlineLabel;

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField messageField;

    private Utente utente;

    @FXML
    public void inviaMessaggio() throws ExecutionException, InterruptedException {
        // TODO: Invia messaggio
        if (messageField.getText().isEmpty()) {
            onlineLabel.setText("Il messaggio non può essere vuoto.");
            return;
        }

        chatArea.appendText(messageField.getText() + '\n');

        if (this.getModel().inviaMessaggio(messageField.getText()) == 0)
            onlineLabel.setText("Messaggio inviato.");
        else
            onlineLabel.setText("Errore durante l'invio del messaggio.");
    }

    public void inizializza() throws ExecutionException, InterruptedException {

        utentiColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        utentiOnlineTable.getItems().addAll(getModel().getUtenti());

        utentiOnlineTable.setOnMouseClicked(event -> {
            utente = utentiOnlineTable.getSelectionModel().getSelectedItem();
            try {
                if (getModel().isOnline(utente))
                    onlineLabel.setText(utente.getUsername() + " è online.");
                else
                    onlineLabel.setText(utente.getUsername() + " è offline.");
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
