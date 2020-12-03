/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.*;
import VIEW.SceneCustom;
import VIEW.View;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class Controller
{
    private final Model model;
    private final View view;
    private final ActionBouton actionBouton;

    public Controller(Stage primaryStage)
    {
        this.model = new Model();
        this.view = new View(primaryStage);
        this.actionBouton = new ActionBouton(this);
    }

    public void init()
    {
        view.init();

        //Actions boutons connexion utilisateur
        view.getSConnexion().getbConnection().setOnAction(actionBouton::btnConnexion);
        actionBouton.setHoverButtonOrangeClair(view.getSConnexion().getbConnection());

        //Actions boutons redirection vers cree compte
        view.getSConnexion().getbCreerCompte().setOnAction(actionBouton::btnRedirectionCreerCompte);
        actionBouton.setHoverButtonOrangeClair(view.getSConnexion().getbCreerCompte());
        

        //Actions boutons cree compte utilisateur
        view.getCreationCompte().getbCreeMonCompte().setOnAction(actionBouton::btnCreerCompte);
        actionBouton.setHoverButtonOrangeClair(view.getCreationCompte().getbCreeMonCompte());
    }


    public Utilisateur getUtilisateur()
    {
        return model.getUtilisateur();
    }

    public void setEmail(String email)
    {
        model.setEmail(email);
    }

    public void changeScene(SceneCustom scene)
    {
        scene.update(view);
        view.getPrimaryStage().setScene(scene);
    }

    @Override
    public String toString()
    {
        return "Je suis le controller";
    }

    public Model getModel()
    {
        return model;
    }

    public View getView()
    {
        return view;
    }

    ///////////////TEMPORAIRE/////////////////////////////
    public void setMaximized()
    {
        this.view.getPrimaryStage().setMaximized(false);
        this.view.getPrimaryStage().setMaximized(true);
    }
    //////////////////////////////////////////////////////
}
