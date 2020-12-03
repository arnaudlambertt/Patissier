/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import CONSTANT.Couleurs;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

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
            controller.getView().getSConnexion().getlEmailOuMdpIncorrect().setVisible(true);
            System.out.println("ERROR : "+e.getMessage());
            return;
        }
        controller.getView().getSConnexion().getlEmailOuMdpIncorrect().setVisible(false);
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
            controller.getView().getCreationCompte().getlEmailOuMdpIncorrect().setVisible(true);
            System.out.println("ERROR : "+e.getMessage());
            return;
        }
        controller.getView().getCreationCompte().getlEmailOuMdpIncorrect().setVisible(false);
        System.out.println(controller.getUtilisateur().toString());
        System.out.println("SUCCES : On a réussi a créer un utilisateur");
    }

    public void btnRedirectionCreerCompte(ActionEvent event)
    {
        System.out.println("Vous avez appuyé sur le bouton de redirection vers Création de compte");
        controller.getView().getCreationCompte().clearTextField();
        controller.changeScene(controller.getView().getCreationCompte());

        System.out.println("SUCCES : On a réussi la redirection");
    }

    //Boutons création compte
    public void btnPasserSurBoutonsCreeCompte(MouseEvent event)
    {
        controller.getView().getCreationCompte().getbCreeMonCompte().setStyle("-fx-background-color : "+Couleurs.ORANGE_BOULANGER_CLAIR+"; -fx-text-fill: "+Couleurs.BLANC);
    }

    public void btnQuiterBoutonsCreeCompte(MouseEvent event)
    {
        controller.getView().getCreationCompte().getbCreeMonCompte().setStyle("-fx-background-color : "+Couleurs.ORANGE_BOULANGER+"; -fx-text-fill: "+Couleurs.BLANC);
    }

    //Boutons redirection creation compte
    public void btnPasserSurBoutonsRedirectionCreeCompte(MouseEvent event)
    {
        controller.getView().getSConnexion().getbCreerCompte().setStyle("-fx-background-color : "+Couleurs.ORANGE_BOULANGER_CLAIR+"; -fx-text-fill: "+Couleurs.BLANC);
    }

    public void btnQuiterBoutonsRedirectionCreeCompte(MouseEvent event)
    {
        controller.getView().getSConnexion().getbCreerCompte().setStyle("-fx-background-color : "+Couleurs.ORANGE_BOULANGER+"; -fx-text-fill: "+Couleurs.BLANC);
    }

    //Boutons connexions
    public void btnPasserSurBoutonsConnexion(MouseEvent event)
    {
        controller.getView().getSConnexion().getbConnection().setStyle("-fx-background-color : "+Couleurs.ORANGE_BOULANGER_CLAIR+"; -fx-text-fill: "+Couleurs.BLANC);
    }

    public void btnQuiterBoutonsConnexion(MouseEvent event)
    {
        controller.getView().getSConnexion().getbConnection().setStyle("-fx-background-color : "+Couleurs.ORANGE_BOULANGER+"; -fx-text-fill: "+Couleurs.BLANC);
    }
}
