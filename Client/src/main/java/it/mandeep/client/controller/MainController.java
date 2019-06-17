package it.mandeep.client.controller;

import it.mandeep.client.model.Model;

public class MainController {

    private Model model;

    public void inviaMessaggio() {
        model.inviaMessaggio();
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        model.inizializza();
    }
}
