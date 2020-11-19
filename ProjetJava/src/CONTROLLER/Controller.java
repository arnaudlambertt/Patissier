/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import DAO.UtilisateurDAO;
import MODEL.Utilisateur;
import VIEW.View;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class Controller
{
    private UtilisateurDAO DAOUtilisateur;
    private Utilisateur utilisateur;
    private VIEW.View view;

    public Controller(Stage primaryStage)
    {
        this.view = new View(primaryStage);
        this.utilisateur = new Utilisateur();
        this.DAOUtilisateur = new UtilisateurDAO();
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
        return utilisateur;
    }

    public View getView()
    {
        return view;
    }
    
    public void setEmail(String email)
    {
        this.utilisateur.setEmail(email);
    }

    public void creationUtilisateur()
    {
        this.utilisateur = this.DAOUtilisateur.create(this.utilisateur, this.view.getMotDePasse().getText());
    }
    
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
