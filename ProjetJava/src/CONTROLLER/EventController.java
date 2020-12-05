/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import CONSTANT.Couleurs;
import CONSTANT.Scenes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author mathi
 */
public class EventController
{

    private final Controller controller;

    public EventController(Controller c)
    {
        controller = c;
    }

    public void btnConnexion(ActionEvent event)
    {
        System.out.println("Vous avez appuyé sur le bouton de connexion");
        try
        {
            if (!controller.getModel().connexionUtilisateur(controller.getView().getSConnexion().gettEmail().getText(), controller.getView().getSConnexion().getpMotDePasse().getText()))
            {
                throw new Exception("Nous n'avons pas réussi à connecter l'utilisateur");
            }
        } catch (Exception e)
        {
            controller.getView().getSConnexion().getlEmailOuMdpIncorrect().setVisible(true);
            System.out.println("ERROR : " + e.getMessage());
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
            if (!controller.getModel().creerUtilisateur(controller.getView().getCreationCompte().getpMotDePasse().getText()))
            {
                throw new Exception("Nous n'avons pas réussi à créer un utilisateur");
            }
        } catch (Exception e)
        {
            controller.getView().getCreationCompte().getlEmailOuMdpIncorrect().setVisible(true);
            System.out.println("ERROR : " + e.getMessage());
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
        controller.changerScene(Scenes.SCENE_CREATION_COMPTE);

        System.out.println("SUCCES : On a réussi la redirection");
    }

    public void afficherAccueil(ActionEvent event)
    {
        controller.getModel().updateTousProduits();
        controller.changerScene(Scenes.SCENE_PRODUITS);
    }

    public void btnBonjour(ActionEvent event)
    {
        if(controller.getUtilisateur().getId() == 0)
            controller.changerScene(Scenes.SCENE_CONNEXION);
        else
            controller.changerScene(Scenes.SCENE_PROFIL);
    }

    //Boutons création compte
    public void btnPasserSurBoutonsCreeCompte(MouseEvent event)
    {
        controller.getView().getCreationCompte().getbCreeMonCompte().setStyle("-fx-background-color : " + Couleurs.ORANGE_BOULANGER_CLAIR + "; -fx-text-fill: " + Couleurs.BLANC);
    }

    public void btnQuiterBoutonsCreeCompte(MouseEvent event)
    {
        controller.getView().getCreationCompte().getbCreeMonCompte().setStyle("-fx-background-color : " + Couleurs.ORANGE_BOULANGER + "; -fx-text-fill: " + Couleurs.BLANC);
    }

    //Boutons redirection creation compte
    public void btnPasserSurBoutonsRedirectionCreeCompte(MouseEvent event)
    {
        controller.getView().getSConnexion().getbCreerCompte().setStyle("-fx-background-color : " + Couleurs.ORANGE_BOULANGER_CLAIR + "; -fx-text-fill: " + Couleurs.BLANC);
    }

    public void btnQuiterBoutonsRedirectionCreeCompte(MouseEvent event)
    {
        controller.getView().getSConnexion().getbCreerCompte().setStyle("-fx-background-color : " + Couleurs.ORANGE_BOULANGER + "; -fx-text-fill: " + Couleurs.BLANC);
    }

    //Boutons connexions
    public void btnPasserSurBoutonsConnexion(MouseEvent event)
    {
        controller.getView().getSConnexion().getbConnexion().setStyle("-fx-background-color : " + Couleurs.ORANGE_BOULANGER_CLAIR + "; -fx-text-fill: " + Couleurs.BLANC);
    }

    public void btnQuiterBoutonsConnexion(MouseEvent event)
    {
        controller.getView().getSConnexion().getbConnexion().setStyle("-fx-background-color : " + Couleurs.ORANGE_BOULANGER + "; -fx-text-fill: " + Couleurs.BLANC);
    }

    public void setHoverButtonOrange(Button ceButton)
    {
        ceButton.setOnMouseEntered((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.HAND);
            ceButton.setStyle("-fx-background-color: " + Couleurs.ORANGE_BOULANGER + "; -fx-text-fill: " + Couleurs.BLANC + "; -fx-font-weight: bold");
        });

        ceButton.setOnMouseExited((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
            ceButton.setStyle("-fx-background-color: " + Couleurs.BLANC + "; -fx-font-weight: bold");
        });
    }

    public void setHoverButtonOrangeClair(Button ceButton)
    {
        ceButton.setOnMouseEntered((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.HAND);
            ceButton.setStyle("-fx-background-color: " + Couleurs.ORANGE_BOULANGER_CLAIR + "; -fx-text-fill: " + Couleurs.BLANC);
        });

        ceButton.setOnMouseExited((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
            ceButton.setStyle("-fx-background-color: " + Couleurs.ORANGE_BOULANGER + "; -fx-text-fill: " + Couleurs.BLANC);
        });
    }

    public void setHover(Button ceButton)
    {
        ceButton.setOnMouseEntered((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.HAND);
        });

        ceButton.setOnMouseExited((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
        });
    }

    public void setActionBarreRecherche(TextField element)
    {
        element.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if (element.getText().equals("Rechercher"))
                {
                    element.clear();
                }
            }
        });

        element.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if (element.getText().equals(""))
                {
                    element.setText("Rechercher");
                }
            }
        });
    }

}