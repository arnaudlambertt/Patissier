/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.*;
import VIEW.View;
import javafx.scene.Scene;
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
        view.getSConnexion().getbConnection().setOnMouseEntered(actionBouton::btnPasserSurBoutonsConnexion);
        view.getSConnexion().getbConnection().setOnMouseExited(actionBouton::btnQuiterBoutonsConnexion);
        
        //Actions boutons redirection vers cree compte
        view.getSConnexion().getbCreerCompte().setOnAction(actionBouton::btnRedirectionCreerCompte);
        view.getSConnexion().getbCreerCompte().setOnMouseEntered(actionBouton::btnPasserSurBoutonsRedirectionCreeCompte);
        view.getSConnexion().getbCreerCompte().setOnMouseExited(actionBouton::btnQuiterBoutonsRedirectionCreeCompte);        
        
        //Actions boutons cree compte utilisateur
        view.getCreationCompte().getbCreeMonCompte().setOnAction(actionBouton::btnCreerCompte);
        view.getCreationCompte().getbCreeMonCompte().setOnMouseEntered(actionBouton::btnPasserSurBoutonsCreeCompte);
        view.getCreationCompte().getbCreeMonCompte().setOnMouseExited(actionBouton::btnQuiterBoutonsCreeCompte);
    }
    

    public Utilisateur getUtilisateur()
    {
        return model.getUtilisateur();
    }

    public void setEmail(String email)
    {
        model.setEmail(email);
    }

//    public void creationUtilisateur()
//    {
//        model.creationUtilisateur(view.getEmail(),view.getMotDePasse());
//    }

    public void changeScene(Scene scene)
    {
        this.view.getPrimaryStage().setScene(scene);
        this.setMaximized();
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
    
    public void setMaximized()
    {
        this.view.getPrimaryStage().setMaximized(false);
        this.view.getPrimaryStage().setMaximized(true);
    }
}
