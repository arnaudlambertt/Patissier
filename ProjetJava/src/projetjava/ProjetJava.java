/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;


import MODEL.*;
import DAO.*;
import CONTROLLER.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class ProjetJava extends Application{

    //private VIEW.View view;
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Controller controller = new Controller(primaryStage);
        //controller.init();
        //System.out.println("Je suis dans projetJava");
        //System.out.println(controller.toString());
        
        
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateur test;
        String nom = "LAMBERT";
        String prenom = "Arnaud";
        String email = "abc.def@ghi.wtf";
        String mdp = "123456";
        test = utilisateurDAO.create(new Utilisateur(nom, prenom, email), mdp);
        System.out.println(test.toString());
        test = utilisateurDAO.connexion(email, mdp);
        System.out.println(test.toString());
        email = "abc.def@ghi.wtf2";
        test.setEmail(email);
        System.out.println(utilisateurDAO.update(test));
        System.out.println(utilisateurDAO.emailExistant(email));
        test = utilisateurDAO.connexion(email, mdp);
        System.out.println(test.toString());
        String nouveauMdp = "abcdef";
        System.out.println(utilisateurDAO.modifierMotDePasse(test, mdp, nouveauMdp));
        test = utilisateurDAO.connexion(email, nouveauMdp);
        System.out.println(test.toString());
        System.out.println(utilisateurDAO.delete(test));
        test = utilisateurDAO.connexion(email, nouveauMdp);
        System.out.println(test.toString());

        //this.view.start(primaryStage);
        //this.controller.setActionButon();
        System.exit(0);
    }
}