package it.mandeep.client.controller;

import it.mandeep.client.model.Model;

public class Controller {

    private Model model;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        model.inizializza();
    }

}
