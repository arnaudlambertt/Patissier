/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import CONSTANT.Couleurs;
import CONSTANT.Scenes;
import MODEL.Produit;
import MODEL.Utilisateur;
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
        } else
            controller.getView().getsProfil().getlEmailDejaExistant().setVisible(true);

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
            if (controller.getUtilisateur().getRole().equals("Administrateur"))
            {
                controller.getView().getpEntete().setVisible(false);
                controller.getView().getpAdmin().setVisible(true);
                controller.changerScene(Scenes.SCENE_ADMIN_PRODUIT); // ZONE ADMIN
            } else
            {
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

    public void creerCompteAdmin(ActionEvent event)
    {
        String nom = controller.getView().getSCreationCompteAdmin().gettNom().getText();
        String prenom = controller.getView().getSCreationCompteAdmin().gettPrenom().getText();
        String email = controller.getView().getSCreationCompteAdmin().gettEmail().getText();
        String role = "Administrateur";
        String motDePasse = controller.getView().getSCreationCompteAdmin().getpMotDePasse().getText();

        if (!nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && !motDePasse.isEmpty())
        {
            Utilisateur u = controller.getModel().getUtilisateurSelectionne();
            u.setNom(nom);
            u.setPrenom(prenom);
            u.setEmail(email);
            u.setRole(role);

            if (controller.getModel().creerUtilisateurAdmin(motDePasse))
                controller.changerScene(Scenes.SCENE_ADMIN_UTILISATEUR);
        } else
            controller.getView().getSCreationCompteAdmin().setInformationsIncorrectesVisible();
    }

    public void creerProduit(ActionEvent event)
    {

        String nom = controller.getView().getsCreationProduit().gettNom().getText();
        String categorie = controller.getView().getsCreationProduit().getSelectCategorie();
        String fournisseur = controller.getView().getsCreationProduit().gettFournisseur().getText();
        String prixUnitaire = controller.getView().getsCreationProduit().gettPrixUnitaire().getText();
        String stock = controller.getView().getsCreationProduit().gettStock().getText();
        String quantiteUnLot = controller.getView().getsCreationProduit().gettQuantiteUnLot().getText();
        String prixUnLot = controller.getView().getsCreationProduit().gettPrixUnLot().getText();
        String promotion = controller.getView().getsCreationProduit().gettPromotion().getText();
        boolean promotionActive = controller.getView().getsCreationProduit().getSelectPromotion();
        String image = controller.getView().getsCreationProduit().gettImage().getText();

        if (!nom.isEmpty() && !fournisseur.isEmpty() && !prixUnitaire.isEmpty() && !stock.isEmpty()
                && !quantiteUnLot.isEmpty() && !prixUnLot.isEmpty() && !promotion.isEmpty() && !image.isEmpty())
        {
            Produit p = controller.getModel().getProduitSelectionne();
            try
            {
                p.setNom(nom);
                p.setCategorie(categorie);
                p.setNomFournisseur(fournisseur);
                p.setPrixUnitaire(Double.parseDouble(prixUnitaire));
                p.setStock(Integer.parseInt(stock));
                p.setQuantiteUnLot(Integer.parseInt(quantiteUnLot));
                p.setPrixUnLot(Double.parseDouble(prixUnLot));
                p.setPromotion(Double.parseDouble(promotion));
                p.setPromotionActive(promotionActive);
                p.setLienImage(image);

                if (controller.getModel().validerCreationProduit())
                    controller.changerScene(Scenes.SCENE_ADMIN_PRODUIT);
            } catch (NumberFormatException e)
            {
                System.out.println(e.getMessage());
            }
        } else
            controller.getView().getsCreationProduit().setProduitIncompleteVisible();
    }

    public void redirectionCreerCompte(ActionEvent event)
    {
        controller.getView().getCreationCompte().clearTextField();
        controller.changerScene(Scenes.SCENE_CREATION_COMPTE);
    }

    public void redirectionCreerUtilisateurAdmin(ActionEvent event)
    {
        controller.getView().getSCreationCompteAdmin().clearTextField();
        controller.changerScene(Scenes.SCENE_CREATION_COMPTE_ADMIN);
    }

    public void redirectionCreerProduitAdmin(ActionEvent event)
    {
        controller.getView().getsCreationProduit().clearTextField();
        controller.changerScene(Scenes.SCENE_CREATION_PRODUIT);
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
        controller.changerScene(Scenes.SCENE_ADMIN_PRODUIT); // ZONE ADMIN
    }

    public void GestionUtilisateurAdmin(ActionEvent event)
    {

        controller.getView().getpAdmin().getbAjoutProduit().setVisible(false);
        controller.getView().getpAdmin().getbAjoutUtilisateur().setVisible(true);
        controller.changerScene(Scenes.SCENE_ADMIN_UTILISATEUR); // ZONE ADMIN
    }

    public void afficherCommandesUtilisateur(ActionEvent event)
    {
        controller.changerScene(Scenes.SCENE_COMMANDES);
    }

    public void ajouterProduitPanier(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        if (controller.getModel().ajouterAuPanier(((PaneProduit) source.getParent().getParent()).getIndex()))
            afficherPanier(event);
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

    public void modifierProduitAdminRedirection(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        controller.getModel().setProduitSelectionne(controller.getModel().getTousLesProduits().get(((PaneProduitAdmin) source.getParent().getParent()).getIndex()));
        controller.changerScene(Scenes.SCENE_MODIFIER_PRODUIT);
    }

    public void modifierUtilisateurAdminRedirection(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        controller.getModel().setUtilisateurSelectionne(controller.getModel().getTousLesUtilisateurs().get(((PaneUtilisateurAdmin) source.getParent().getParent()).getIndex()));
        controller.changerScene(Scenes.SCENE_MODIFIER_UTILISATEUR);
    }

    public void modifierUtilisateurSelectionne(ActionEvent event)
    {
        controller.getModel().getUtilisateurSelectionne().setNom(controller.getView().getsModifierUtilisateur().gettNom().getText());
        controller.getModel().getUtilisateurSelectionne().setPrenom(controller.getView().getsModifierUtilisateur().gettPrenom().getText());
        controller.getModel().getUtilisateurSelectionne().setEmail(controller.getView().getsModifierUtilisateur().gettEmail().getText());
        controller.getModel().getUtilisateurSelectionne().setRole(controller.getView().getsModifierUtilisateur().getSelectRole());

        if (!controller.getModel().updateUtilisateurSelectionne())
            controller.getView().getsModifierUtilisateur().setUtiliisateurIncompleteVisible();
        else
        {
            controller.getView().getsModifierUtilisateur().setUtiliisateurIncompleteInvisible();

            controller.changerScene(Scenes.SCENE_ADMIN_UTILISATEUR); //zone admin utilisateur
        }
    }

    public void modifierProduitSelectionne(ActionEvent event)
    {

        String nom = controller.getView().getsModifierProduit().gettNom().getText();
        String categorie = controller.getView().getsModifierProduit().getSelectCategorie();
        String fournisseur = controller.getView().getsModifierProduit().gettFournisseur().getText();
        String prixUnitaire = controller.getView().getsModifierProduit().gettPrixUnitaire().getText();
        String stock = controller.getView().getsModifierProduit().gettStock().getText();
        String quantiteUnLot = controller.getView().getsModifierProduit().gettQuantiteUnLot().getText();
        String prixUnLot = controller.getView().getsModifierProduit().gettPrixUnLot().getText();
        String promotion = controller.getView().getsModifierProduit().gettPromotion().getText();
        boolean promotionActive = controller.getView().getsModifierProduit().getSelectPromotion();
        String image = controller.getView().getsModifierProduit().gettImage().getText();
        
        if (!nom.isEmpty() && !categorie.isEmpty() && !fournisseur.isEmpty() && !prixUnitaire.isEmpty() && !stock.isEmpty()
                && !quantiteUnLot.isEmpty() && !prixUnLot.isEmpty() && !promotion.isEmpty() && !image.isEmpty())
        {
            Produit p = controller.getModel().getProduitSelectionne();

            p.setNom(nom);
            p.setCategorie(categorie);
            p.setNomFournisseur(fournisseur);
            p.setPrixUnitaire(Double.parseDouble(prixUnitaire));
            p.setStock(Integer.parseInt(stock));
            p.setQuantiteUnLot(Integer.parseInt(quantiteUnLot));
            p.setPrixUnLot(Double.parseDouble(prixUnLot));
            p.setPromotion(Double.parseDouble(promotion));
            p.setPromotionActive(promotionActive);
            p.setLienImage(image);

            if (controller.getModel().updateProduitSelectionne())
                controller.changerScene(Scenes.SCENE_ADMIN_PRODUIT);

        } else
            controller.getView().getsModifierProduit().setProduitIncompleteVisible();
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
