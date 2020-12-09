/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import CONSTANT.Couleurs;
import CONSTANT.Scenes;
import VIEW.PaneProduit;
import VIEW.PaneProduitAdmin;
import VIEW.PaneProduitPanier;
import VIEW.PaneUtilisateurAdmin;
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
        if (controller.getModel().supprimerUtilisateur())
            controller.changerScene(Scenes.SCENE_CONNEXION);
    }

    public void deconnecterUtilisateur(ActionEvent event)
    {
        controller.getModel().deconnecterUtilisateur();
        controller.getView().getpAdmin().setVisible(false);
        controller.getView().getpEntete().setVisible(true);
        afficherAccueil(event);
    }

    public void mettreAJourUtilisateur(ActionEvent event)
    {
        if (controller.getModel().verifierEmail(controller.getView().getsProfil().gettEmail().getText()))
        {
            controller.getView().getsProfil().getlEmailDejaExistant().setVisible(false);
            if (controller.getView().getsProfil().gettNouveauMotDePasse().getText().isEmpty())
            {

                controller.mettreAJourUtilisateur();
                controller.getModel().updateUtilisateur();
            } else if (controller.getModel().modifierMotDePasse(
                    controller.getView().getsProfil().gettAncienMotDePasse().getText(),
                    controller.getView().getsProfil().gettNouveauMotDePasse().getText()))
            {
                controller.mettreAJourUtilisateur();
                controller.getModel().updateUtilisateur();
            } 
        }
        else
            {
                controller.getView().getsProfil().getlEmailDejaExistant().setVisible(true);
            }
        
        controller.changerScene(Scenes.SCENE_PROFIL);

    }

    public void connexion(ActionEvent event)
    {
        //Si identifiant incorrecte ou mot de passe indorecte alors on affiche un message sur la page
        if (!controller.getModel().connecterUtilisateur(controller.getView().getSConnexion().gettEmail().getText(), controller.getView().getSConnexion().getpMotDePasse().getText()))
            controller.getView().getSConnexion().getlEmailOuMdpIncorrect().setVisible(true);
        else
        {
            controller.getView().getSConnexion().getlEmailOuMdpIncorrect().setVisible(false);
            if(controller.getUtilisateur().getRole().equals("Administrateur"))
            {
                controller.getView().getpEntete().setVisible(false);
                controller.getView().getpAdmin().setVisible(true);
                controller.changerScene(Scenes.SCENE_ADMIN_Produit); // ZONE ADMIN
            }else{
                controller.getView().getpEntete().setVisible(true);
                controller.getView().getpAdmin().setVisible(false);
                if (controller.redirectionCommande())
                    controller.changerScene(Scenes.SCENE_ADRESSE); //adresse livraison
                else
                {
                    controller.changerScene(Scenes.SCENE_PROFIL); //profil
                    controller.setRedirectionCommande(false);
                }
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
            if (controller.redirectionCommande())
                controller.changerScene(Scenes.SCENE_ADRESSE); //adresse livraison
            else
            {
                controller.changerScene(Scenes.SCENE_PROFIL); //profil
                controller.setRedirectionCommande(false);
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
        controller.setRedirectionCommande(false);
        controller.getModel().updateTousProduits();
        controller.changerScene(Scenes.SCENE_PRODUITS);
    }

    public void afficherCategorie(ActionEvent event)
    {
        controller.setRedirectionCommande(false);
        switch (((Button) event.getSource()).getText())
        {
            case "Gros\nélectroménager":
                controller.getModel().filtreCategorie("Gros électroménager");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Cuisine\nCuisson":
                controller.getModel().filtreCategorie("Cuisine Cuisson");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Maison\nEntretien":
                controller.getModel().filtreCategorie("Maison Entretien");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Beauté\nSanté":
                controller.getModel().filtreCategorie("Beauté Santé");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Objets\nconnectés":
                controller.getModel().filtreCategorie("Objets connectés");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Smartphone\nTéléphonie":
                controller.getModel().filtreCategorie("Smartphone Téléphonie");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Informatique\nTablette":
                controller.getModel().filtreCategorie("Informatique Tablette");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "TV Image\nSon":
                controller.getModel().filtreCategorie("TV, Image Son");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                break;
            case "Console\nGaming":
                controller.getModel().filtreCategorie("Console Gaming");
                controller.changerScene(Scenes.SCENE_PRODUITS);
        }
    }

    public void afficherRecherche(ActionEvent event)
    {
        String recherche = controller.getView().getpEntete().gettBarreRecherche().getText();
        if (!recherche.equals("Rechercher"))
        {
            controller.setRedirectionCommande(false);
            controller.getModel().filtreRecherche(recherche);
            controller.changerScene(Scenes.SCENE_PRODUITS);
            controller.getView().getpEntete().requestFocus();
        }
    }

    public void afficherPanier(ActionEvent event)
    {
        controller.setRedirectionCommande(false);
        controller.getModel().stockSuffisantPanier();
        controller.changerScene(Scenes.SCENE_PANIER);
    }

    public void validerPanier(ActionEvent event)
    {
        if (!controller.getModel().getPanier().getProduitsCommande().isEmpty())
            if (!controller.utilisateurConnecte())
            {
                controller.setRedirectionCommande(true);
                controller.changerScene(Scenes.SCENE_CONNEXION);
            } else
                controller.changerScene(Scenes.SCENE_ADRESSE);
    }
    
    public void GestionProduitAdmin(ActionEvent event)
    {
        controller.getView().getpAdmin().getbAjoutProduit().setVisible(true);
        controller.getView().getpAdmin().getbAjoutUtilisateur().setVisible(false);
        controller.changerScene(Scenes.SCENE_ADMIN_Produit); // ZONE ADMIN
    }
    
    public void GestionUtilisateurAdmin(ActionEvent event)
    {
        
        controller.getView().getpAdmin().getbAjoutProduit().setVisible(false);
        controller.getView().getpAdmin().getbAjoutUtilisateur().setVisible(true);
        controller.changerScene(Scenes.SCENE_ADMIN_Utilisateur); // ZONE ADMIN
    }

    public void afficherCommandesUtilisateur(ActionEvent event)
    {
        controller.changerScene(Scenes.SCENE_COMMANDES);
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

    public void validerAdresse(ActionEvent event)
    {
        String codePostal = controller.getView().gettCodePostal().getText();
        String ville = controller.getView().gettVille().getText();
        String rue = controller.getView().gettRue().getText();
        String numero = controller.getView().gettNumero().getText();

        if (!codePostal.isEmpty() && !ville.isEmpty() && !rue.isEmpty() && !numero.isEmpty())
        {
            controller.getModel().getPanier().setAdresse(numero + " " + rue + " " + codePostal + " " + ville);
            controller.changerScene(Scenes.SCENE_PAIEMENT);
        } else
            controller.getView().setAdresseIncompleteVisible();
    }

    public void commander(ActionEvent event)
    {
        if (!controller.getModel().stockSuffisantPanier())
            controller.changerScene(Scenes.SCENE_PANIER);

        else if (controller.getModel().validerCommande())
        {
            controller.setRedirectionCommande(false);
            controller.changerScene(Scenes.SCENE_COMMANDES);
        }
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
        controller.setRedirectionCommande(false);
        if (!controller.utilisateurConnecte())
            controller.changerScene(Scenes.SCENE_CONNEXION);
        else
            controller.changerScene(Scenes.SCENE_PROFIL);
    }
    
    public void modifierProduitAdmin(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        controller.getModel().setProduitSelectionne(controller.getModel().getTousLesProduits().get(((PaneProduitAdmin) source.getParent().getParent()).getIndex()));
        controller.changerScene(Scenes.SCENE_MODIFIER_PRODUIT);
    }
    public void modifierUtilisateurAdmin(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        controller.getModel().setUtilisateurSelectionne(controller.getModel().getTousLesUtilisateurs().get(((PaneUtilisateurAdmin) source.getParent().getParent()).getIndex()));
        controller.changerScene(Scenes.SCENE_MODIFIER_UTILISATEUR);
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
