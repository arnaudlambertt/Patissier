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
        String email = "abc.def@ghi.jk5";
        String mdp = "abcdef";
        Utilisateur test = utilisateurDAO.connexion(email, mdp);
        System.out.println(test.toString());

        String nouveauMdp = "123456";
        System.out.println(utilisateurDAO.modifierMotDePasse(test,mdp,nouveauMdp));
        test = utilisateurDAO.connexion(email, nouveauMdp);
        System.out.println(test.toString());

        //this.view.start(primaryStage);
        //this.controller.setActionButon();
        System.exit(0);
    }
}