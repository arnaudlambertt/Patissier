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

    public Controller(Stage primaryStage)
    {
        this.model = new Model();
        this.view = new View(primaryStage);
    }
    
    public void init()
    {
        view.init();
        setActionButon();
    }
    
    public void setActionButon()
    {
        this.view.getSubmitNouveauCompte().setOnAction(new ActionSubmit(this));
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
    }
    
    @Override
    public String toString()
    {
        return "Je suis le controller";
    }
}
