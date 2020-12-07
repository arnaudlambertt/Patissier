/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import CONSTANT.Couleurs;
import CONSTANT.Scenes;
import VIEW.PaneProduit;
import VIEW.PaneProduitPanier;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    
    public void supprimerUtilisateur(ActionEvent event)
    {
        //if(controller.getModel().supprimerUtilisateur(controller.getUtilisateur().getEmail()))
            System.out.println("On a bien supprimé l'utilisateur");
    }
    
    public void deconnecterUtilisateur(ActionEvent event)
    {
        System.out.println("deconnecter utilisateur");
        controller.getModel().deconnecterUtilisateur();
        controller.changerScene(Scenes.SCENE_CONNEXION);
    }
    
    public void connexion(ActionEvent event)
    {
        //System.out.println("Vous avez appuyé sur le bouton de connexion");
        try
        {
            if (!controller.getModel().connexionUtilisateur(controller.getView().getSConnexion().gettEmail().getText(), controller.getView().getSConnexion().getpMotDePasse().getText()))
                throw new Exception("Nous n'avons pas réussi à connecter l'utilisateur");
        } catch (Exception e)
        {
            controller.getView().getSConnexion().getlEmailOuMdpIncorrect().setVisible(true);
            System.out.println("ERROR : " + e.getMessage());
            return;
        }
        controller.getView().getSConnexion().getlEmailOuMdpIncorrect().setVisible(false);
        controller.changerScene(Scenes.SCENE_PROFIL);
        System.out.println("SUCCES : On a réussi à connecter l'utilisateur");
    }

    public void creerCompte(ActionEvent event)
    {
        //System.out.println("Vous avez appuyé sur le bouton de Création de compte");
        controller.getModel().setNom(controller.getView().getCreationCompte().gettNom().getText());
        controller.getModel().setPrenom(controller.getView().getCreationCompte().gettPrenom().getText());
        controller.getModel().setEmail(controller.getView().getCreationCompte().gettEmail().getText());

        try
        {
            if (!controller.getModel().creerUtilisateur(controller.getView().getCreationCompte().getpMotDePasse().getText()))
                throw new Exception("Nous n'avons pas réussi à créer un utilisateur");
        } catch (Exception e)
        {
            controller.getView().getCreationCompte().getlEmailOuMdpIncorrect().setVisible(true);
            System.out.println("ERROR : " + e.getMessage());
            return;
        }
        controller.getView().getCreationCompte().getlEmailOuMdpIncorrect().setVisible(false);
        //System.out.println(controller.getUtilisateur().toString());
        System.out.println("SUCCES : On a réussi a créer un utilisateur");
    }

    public void redirectionCreerCompte(ActionEvent event)
    {
        System.out.println("Vous avez appuyé sur le bouton de redirection vers Création de compte");
        controller.getView().getCreationCompte().clearTextField();
        controller.changerScene(Scenes.SCENE_CREATION_COMPTE);

        System.out.println("SUCCES : On a réussi la redirection");
    }

    public void afficherAccueil(ActionEvent event)
    {
        //set text accueil
        controller.getModel().updateTousProduits();
        controller.changerScene(Scenes.SCENE_PRODUITS);
    }

    public void afficherCategorie(ActionEvent event)
    {
        switch (((Button) event.getSource()).getText())
        {
            case "Gros\nélectroménager":
                //set text categorie ....
                controller.getModel().filtreCategorie("Gros électroménager");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Cuisine\nCuisson":
                //set text categorie ....
                controller.getModel().filtreCategorie("Cuisine Cuisson");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Maison\nEntretien":
                //set text categorie ....
                controller.getModel().filtreCategorie("Maison Entretien");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Beauté\nSanté":
                //set text categorie ....
                controller.getModel().filtreCategorie("Beauté Santé");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Objets\nconnectés":
                //set text categorie ....
                controller.getModel().filtreCategorie("Objets connectés");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Smartphone\nTéléphonie":
                //set text categorie ....
                controller.getModel().filtreCategorie("Smartphone Téléphonie");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Informatique\nTablette":
                //set text categorie ....
                controller.getModel().filtreCategorie("Informatique Tablette");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "TV Image\nSon":
                //set text categorie ....
                controller.getModel().filtreCategorie("TV, Image Son");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Console\nGaming":
                //set text categorie ....
                controller.getModel().filtreCategorie("Console Gaming");
                controller.changerScene(Scenes.SCENE_PRODUITS);
        }
    }

    public void afficherRecherche(ActionEvent event)
    {
        String recherche = controller.getView().getpEntete().gettBarreRecherche().getText();
        if (!recherche.equals("Rechercher"))
        {
            controller.getModel().filtreRecherche(recherche);
            controller.changerScene(Scenes.SCENE_PRODUITS);
            controller.getView().getpEntete().requestFocus();
        }
    }

    public void afficherPanier(ActionEvent event)
    {
        controller.changerScene(Scenes.SCENE_PANIER);

    }
    
    public void ajouterProduitPanier(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        controller.getModel().ajouterAuPanier(((PaneProduit) source.getParent().getParent()).getIndex());
    }
    
    public void changementQuantitePanier(ActionEvent event)
    {
        ComboBox source = ((ComboBox)event.getSource());
        controller.getModel().modifierQuantitePanier(((PaneProduitPanier) source.getParent()).getIndex(),(int)source.getValue());
        controller.getView().setPrixPanier(controller.getModel().getPanier().getPrix());
        controller.getView().getsPanier().update(controller.getView());
        controller.getView().getpEntete().requestFocus();
    }
    
    public void supprimerProduitPanier(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        int index = ((PaneProduitPanier) source.getParent()).getIndex();
        controller.getModel().supprimerProduitPanier(index);
        controller.getView().getPanesProduitPanier().remove(index);
        controller.changerScene(Scenes.SCENE_PANIER);
    }
    
    public void focusBarreRecherche(TextField element)
    {
        element.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) ->
        {
            if (newPropertyValue)
            {
                if (element.getText().equals("Rechercher"))
                    element.clear();
            } else if (element.getText().equals(""))
                element.setText("Rechercher");
        });
    }

    public void entrerRercherche(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER && !controller.getView().getpEntete().gettBarreRecherche().getText().isEmpty())
            afficherRecherche(new ActionEvent());
    }

    public void bonjour(ActionEvent event)
    {
        if (controller.getUtilisateur().getId() == 0)
            controller.changerScene(Scenes.SCENE_CONNEXION);
        else
            controller.changerScene(Scenes.SCENE_PROFIL);
    }

    public void hoverButtonOrange(Button ceButton)
    {
        ceButton.setOnMouseEntered((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.HAND);
            ceButton.setStyle("-fx-background-color: " + Couleurs.ORANGE_PATISSIER + "; -fx-text-fill: " + Couleurs.BLANC + "; -fx-font-weight: bold");
        });

        ceButton.setOnMouseExited((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
            ceButton.setStyle("-fx-background-color: " + Couleurs.BLANC + "; -fx-font-weight: bold");
        });
    }

    public void hoverButtonOrangeClair(Button ceButton)
    {
        ceButton.setOnMouseEntered((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.HAND);
            ceButton.setStyle("-fx-background-color: " + Couleurs.ORANGE_PATISSIER_CLAIR + "; -fx-text-fill: " + Couleurs.BLANC);
        });

        ceButton.setOnMouseExited((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
            ceButton.setStyle("-fx-background-color: " + Couleurs.ORANGE_PATISSIER + "; -fx-text-fill: " + Couleurs.BLANC);
        });
    }

    public void hover(Button ceButton)
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

}
