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
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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
        try
        {
            if (!controller.getModel().supprimerUtilisateur(controller.getUtilisateur().getEmail()))
                throw new Exception("Echec de suppression utilisateur");
            {
                controller.getModel().deconnecterUtilisateur();
                controller.changerScene(Scenes.SCENE_CONNEXION);
            }
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    public void deconnecterUtilisateur(ActionEvent event)
    {
        controller.getModel().deconnecterUtilisateur();
        afficherAccueil(event);
    }

    public void connexion(ActionEvent event)
    {
        if (!controller.getModel().connecterUtilisateur(controller.getView().getSConnexion().gettEmail().getText(), controller.getView().getSConnexion().getpMotDePasse().getText()))
            controller.getView().getSConnexion().getlEmailOuMdpIncorrect().setVisible(true);
        else
        {
            controller.getView().getSConnexion().getlEmailOuMdpIncorrect().setVisible(false);
            if (controller.panierValide())
                controller.changerScene(Scenes.SCENE_ERREUR_404); //adresse livraison
            else
            {
                controller.changerScene(Scenes.SCENE_PROFIL); //profil
                controller.setPanierValide(false);
            }
        }
    }

    public void creerCompte(ActionEvent event)
    {
        controller.getModel().setNom(controller.getView().getCreationCompte().gettNom().getText());
        controller.getModel().setPrenom(controller.getView().getCreationCompte().gettPrenom().getText());
        controller.getModel().setEmail(controller.getView().getCreationCompte().gettEmail().getText());

        if (!controller.getModel().creerUtilisateur(controller.getView().getCreationCompte().getpMotDePasse().getText()))
            controller.getView().getCreationCompte().getlEmailOuMdpIncorrect().setVisible(true);
        else
        {
            controller.getView().getCreationCompte().getlEmailOuMdpIncorrect().setVisible(false);
            if (controller.panierValide())
                controller.changerScene(Scenes.SCENE_ERREUR_404); //adresse livraison
            else
            {
                controller.changerScene(Scenes.SCENE_PROFIL); //profil
                controller.setPanierValide(false);
            }
        }
    }

    public void redirectionCreerCompte(ActionEvent event)
    {
        controller.getView().getCreationCompte().clearTextField();
        controller.changerScene(Scenes.SCENE_CREATION_COMPTE);
    }

    public void afficherAccueil(ActionEvent event)
    {
        controller.setPanierValide(false);
        //set text accueil
        controller.getModel().updateTousProduits();
        controller.changerScene(Scenes.SCENE_PRODUITS);
    }

    public void afficherCategorie(ActionEvent event)
    {
        controller.setPanierValide(false);
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
            controller.setPanierValide(false);
            controller.getModel().filtreRecherche(recherche);
            controller.changerScene(Scenes.SCENE_PRODUITS);
            controller.getView().getpEntete().requestFocus();
        }
    }

    public void afficherPanier(ActionEvent event)
    {
        controller.setPanierValide(false);
        controller.changerScene(Scenes.SCENE_PANIER);
    }

    public void validerPanier(ActionEvent event)
    {
        if (!controller.getModel().getPanier().getProduitsCommande().isEmpty())
            if (!controller.utilisateurConnecte())
            {
                controller.setPanierValide(true);
                controller.changerScene(Scenes.SCENE_CONNEXION);
            } else
                controller.changerScene(Scenes.SCENE_ERREUR_404);
    }

    public void ajouterProduitPanier(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        controller.getModel().ajouterAuPanier(((PaneProduit) source.getParent().getParent()).getIndex());
    }

    public void changementQuantitePanier(ActionEvent event)
    {
        ComboBox source = ((ComboBox) event.getSource());
        controller.getModel().modifierQuantitePanier(((PaneProduitPanier) source.getParent()).getIndex(), (int) source.getValue());
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
        controller.setPanierValide(false);
        if (!controller.utilisateurConnecte())
            controller.changerScene(Scenes.SCENE_CONNEXION);
        else
            controller.changerScene(Scenes.SCENE_PROFIL);
    }

    public void hoverButtonOrange(Button ceButton)
    {
        ceButton.setOnMouseEntered((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.HAND);
            ceButton.setStyle("-fx-background-color: " + Couleurs.ORANGE_PATISSIER + "; -fx-text-fill: " + Couleurs.BLANC + "; -fx-font-weight: bold;" + "-fx-background-radius: 0;");
        });

        ceButton.setOnMouseExited((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
            ceButton.setStyle("-fx-background-color: " + Couleurs.BLANC + "; -fx-font-weight: bold;" + "-fx-background-radius: 0;");
        });
    }

    public void hoverButtonOrangeClair(Button ceButton)
    {
        ceButton.setOnMouseEntered((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.HAND);
            ceButton.setStyle("-fx-background-color: " + Couleurs.ORANGE_PATISSIER_CLAIR + "; -fx-text-fill: " + Couleurs.BLANC
                    + ";-fx-font-weight: bold;");
        });

        ceButton.setOnMouseExited((MouseEvent event) ->
        {
            controller.getView().getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
            ceButton.setStyle("-fx-background-color: " + Couleurs.ORANGE_PATISSIER + "; -fx-text-fill: " + Couleurs.BLANC
                    + ";-fx-font-weight: bold;");
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
