/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import javafx.event.ActionEvent;

/**
 *
 * @author mathi
 */
public class ActionBouton
{
    private final Controller controller;

    public ActionBouton(Controller c)
    {
        controller=c;
    }
    
    public void btnConnexion(ActionEvent event)
    {
        System.out.println("Vous avez appuyé sur le bouton de connexion");    
        try
        {
            if(!controller.getModel().connexionUtilisateur(controller.getView().getSConnexion().gettEmail().getText(),controller.getView().getSConnexion().getpMotDePasse().getText()))
            {
                throw new Exception("Nous n'avons pas réussi à connecter l'utilisateur");
            }
        } catch (Exception e)
        {
            System.out.println("ERROR : "+e.getMessage());
            return;
        }
        System.out.println("SUCCES : On a réussi à connecter l'utilisateur");
        
    }
    
    public void btnCreerCompte(ActionEvent event)
    {
        System.out.println("Vous avez appuyé sur le bouton de Création de compte");
        controller.getModel().setNom(controller.getView().getCreationCompte().gettNom().getText());
        controller.getModel().setPrenom(controller.getView().getCreationCompte().gettPrenom().getText());
        controller.getModel().setEmail(controller.getView().getCreationCompte().gettEmail().getText());
        
        try
        {
            if(!controller.getModel().creerUtilisateur(controller.getView().getCreationCompte().getpMotDePasse().getText()))
            {
                throw new Exception("Nous n'avons pas réussi à créer un utilisateur");
            }
        } catch (Exception e)
        {
            System.out.println("ERROR : "+e.getMessage());
        }
        System.out.println(controller.getUtilisateur().toString());
        System.out.println("SUCCES : On a réussi a créer un utilisateur");       
    }
}
