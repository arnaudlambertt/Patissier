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
import java.io.File;
import javafx.application.Platform;
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
    
    /**
     * Change de scene si on arrive à supprimer l'utilisateur.
     * @param event 
     */
    public void supprimerUtilisateur(ActionEvent event)
    {
        if (controller.getModel().supprimerUtilisateur())
            controller.changerScene(Scenes.SCENE_CONNEXION);
    }

    /**
     * Déconnecte l'utilisateur
     * Place l'entete visible et l'entete Admin invisible
     * Change de scene
     * @param event 
     */
    public void deconnecterUtilisateur(ActionEvent event)
    {
        controller.getModel().deconnecterUtilisateur();
        controller.getView().getpAdmin().setVisible(false);
        controller.getView().getpEntete().setVisible(true);
        afficherAccueil(event);
    }
    
    /**
     * Met a jour les informations de l'utilisateur
     * Puis change de scene
     * @param event 
     */
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

    /**
     * Connecte un utilisateur
     * Puis change de scene
     * @param event 
     */
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
                GestionProduitAdmin(event);
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

    
    /**
     * Crée un compte
     * Puis change de scene
     * @param event 
     */
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

    /**
     * Crée un compte admin
     * Puis change de scene
     * @param event 
     */
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

    /**
     * clear les textfields de la scene création compte
     * Puis change de scene
     * @param event 
     */
    public void redirectionCreerCompte(ActionEvent event)
    {
        controller.getView().getCreationCompte().clearTextField();
        controller.changerScene(Scenes.SCENE_CREATION_COMPTE);
    }
    
    /**
     * clear les textfields de la scene création compte Admin
     * Puis change de scene
     * @param event 
     */
    public void redirectionCreerUtilisateurAdmin(ActionEvent event)
    {
        controller.getView().getSCreationCompteAdmin().clearTextField();
        controller.changerScene(Scenes.SCENE_CREATION_COMPTE_ADMIN);
    }

    /**
     * clear les textfields de la scene création produit
     * Puis change de scene
     * @param event 
     */
    public void redirectionCreerProduitAdmin(ActionEvent event)
    {
        controller.getView().getsModifierProduit().clearTextField();
        controller.getModel().resetProduitSelectionne();
        controller.changerScene(Scenes.SCENE_CREATION_PRODUIT);
        Platform.runLater(() ->
        {
            controller.getModel().updateImagesProduit();
        });
    }

    /**
     * Met a jour la liste des produits
     * puis change de scène
     * @param event 
     */
    public void afficherAccueil(ActionEvent event)
    {
        controller.setRedirectionCommande(false);
        controller.getModel().updateTousProduits();
        controller.changerScene(Scenes.SCENE_PRODUITS);
    }

    /**
     * Prepare la scene produit avec les bon produits
     * @param event 
     */
    public void afficherCategorie(ActionEvent event)
    {
        controller.setRedirectionCommande(false);
        switch (((Button) event.getSource()).getText())
        {
            case "Gros\nélectroménager":
                controller.getModel().filtreCategorie("Gros électroménager");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                controller.setUrl("produits/categorie/gros-electromenager");
                break;
            case "Cuisine\nCuisson":
                controller.getModel().filtreCategorie("Cuisine Cuisson");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                controller.setUrl("produits/categorie/cuisine-cuisson");
                break;
            case "Maison\nEntretien":
                controller.getModel().filtreCategorie("Maison Entretien");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                controller.setUrl("produits/categorie/maison-entretien");
                break;
            case "Beauté\nSanté":
                controller.getModel().filtreCategorie("Beauté Santé");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                controller.setUrl("produits/categorie/beaute-sante");
                break;
            case "Objets\nconnectés":
                controller.getModel().filtreCategorie("Objets connectés");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                controller.setUrl("produits/categorie/objets-connectes");
                break;
            case "Smartphone\nTéléphonie":
                controller.getModel().filtreCategorie("Smartphone Téléphonie");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                controller.setUrl("produits/produits/categorie/smartphone-telephonie");
                break;
            case "Informatique\nTablette":
                controller.getModel().filtreCategorie("Informatique Tablette");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                controller.setUrl("produits/categorie/informatique-tablette");
                break;
            case "TV Image\nSon":
                controller.getModel().filtreCategorie("TV, Image Son");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                controller.setUrl("produits/categorie/tv-image-son");
                break;
            case "Console\nGaming":
                controller.getModel().filtreCategorie("Console Gaming");
                controller.changerScene(Scenes.SCENE_PRODUITS);
                controller.setUrl("produits/categorie/console-gaming");
        }

    }

    /**
       appelle l'affiche de scene lié à l'url lorsque la touche entrée est appuyée 
     * @param event 
     */
    public void entrerUrl(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER && (!controller.getUrl().isEmpty()))
            afficherUrl(new ActionEvent());
    }

    /**
     * affiche la scene en fonction de l'url rentré par l'utilisateur
     * @param event 
     */
    public void afficherUrl(ActionEvent event)
    {
        String url = controller.getUrl();
        if (url.startsWith("patissier.com/"))
            if (url.length() == 14) //patissier.com(/)

                if (controller.getModel().getUtilisateur().getRole().equals("Utilisateur"))
                    afficherAccueil(event);
                else
                    GestionProduitAdmin(event);
            else
            {
                url = url.substring(14);
                if (controller.getModel().getUtilisateur().getRole().equals("Utilisateur"))
                    if (url.startsWith("produits/"))//recherche ou categorie
                    {
                        url = url.substring(9);
                        if (url.startsWith("categorie/"))
                        {
                            url = url.substring(10);
                            switch (url)
                            {
                                case "gros-electromenager":
                                    controller.getView().getpEntete().getbCategories(0).fire();
                                    break;
                                case "cuisine-cuisson":
                                    controller.getView().getpEntete().getbCategories(1).fire();
                                    break;
                                case "maison-entretien":
                                    controller.getView().getpEntete().getbCategories(2).fire();
                                    break;
                                case "beaute-sante":
                                    controller.getView().getpEntete().getbCategories(3).fire();
                                    break;
                                case "objets-connectes":
                                    controller.getView().getpEntete().getbCategories(4).fire();
                                    break;
                                case "smartphone-telephonie":
                                    controller.getView().getpEntete().getbCategories(5).fire();
                                    break;
                                case "informatique-tablette":
                                    controller.getView().getpEntete().getbCategories(6).fire();
                                    break;
                                case "tv-image-son":
                                    controller.getView().getpEntete().getbCategories(7).fire();
                                    break;
                                case "console-gaming":
                                    controller.getView().getpEntete().getbCategories(8).fire();
                                    break;
                                default:
                                    controller.changerScene(Scenes.SCENE_ERREUR_404);
                            }
                        } else if (url.startsWith("recherche=") && url.length() > 10)
                        {
                            url = url.substring(10);
                            controller.getView().getpEntete().gettBarreRecherche().setText(url);
                            afficherRecherche(event);
                        } else
                            controller.changerScene(Scenes.SCENE_ERREUR_404);
                    } else if (url.equals("connexion"))
                        bonjour(event);
                    else if (url.equals("creer-mon-compte"))
                        if (controller.utilisateurConnecte())
                            bonjour(event);
                        else
                            redirectionCreerCompte(event);
                    else if (url.startsWith("panier"))
                        afficherPanier(event);
                    else if (url.startsWith("profil"))
                        if (url.equals("profil") || !controller.utilisateurConnecte())
                            bonjour(event);
                        else if (url.startsWith("profil/"))
                        {
                            url = url.substring(7);
                            switch (url)
                            {
                                case "":
                                case "mes-informations":
                                    bonjour(event);
                                    break;
                                case "mes-commandes":
                                    afficherCommandesUtilisateur(event);
                                    break;
                                default:
                                    controller.changerScene(Scenes.SCENE_ERREUR_404);
                            }
                        } else
                            controller.changerScene(Scenes.SCENE_ERREUR_404);
                    else
                        controller.changerScene(Scenes.SCENE_ERREUR_404);
                else if (url.startsWith("admin"))
                    if (url.equals("admin"))
                        GestionProduitAdmin(event);
                    else if (url.startsWith("admin/"))
                    {
                        url = url.substring(6);
                        switch (url)
                        {
                            case "produits/":
                            case "produits":
                                GestionProduitAdmin(event);
                                break;
                            case "produits/modifier-produit":
                            case "produits/creation-produit":
                                redirectionCreerProduitAdmin(event);
                                break;
                            case "utilisateurs/":
                            case "utilisateurs":
                                GestionUtilisateurAdmin(event);
                                break;
                            case "utilisateurs/modifier-utilisateur":
                            case "utilisateurs/creation-compte-admin":
                                redirectionCreerUtilisateurAdmin(event);
                                break;
                            default:
                                controller.changerScene(Scenes.SCENE_ERREUR_404);
                        }
                    } else
                        controller.changerScene(Scenes.SCENE_ERREUR_404);
                else
                    controller.changerScene(Scenes.SCENE_ERREUR_404);
            }
        else if (url.equals("patissier.com"))
            if (controller.getModel().getUtilisateur().getRole().equals("Utilisateur"))
                afficherAccueil(event);
            else
                GestionProduitAdmin(event);
        else
            controller.changerScene(Scenes.SCENE_ERREUR_404);
    }

    /**
     * affiche la scene produit basée sur la recherche lorsque la touche entrée est appuyée
     * @param event 
     */
    public void entrerRecherche(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER && !controller.getView().getpEntete().gettBarreRecherche().getText().isEmpty())
            afficherRecherche(new ActionEvent());
    }

    /**
     * affiche la scene produit basée sur la recherche
     * @param event 
     */
    public void afficherRecherche(ActionEvent event)
    {
        String recherche = controller.getView().getpEntete().gettBarreRecherche().getText();
        if (!recherche.equals("Rechercher"))
        {
            controller.setRedirectionCommande(false);
            controller.getModel().filtreRecherche(recherche);
            controller.changerScene(Scenes.SCENE_PRODUITS);
            controller.setUrl("produits/recherche=" + recherche);
        }
    }

    /**
     * change de scene après avoir vérifié les stocks
     * @param event 
     */
    public void afficherPanier(ActionEvent event)
    {
        controller.setRedirectionCommande(false);
        controller.getModel().stockSuffisantPanier();
        controller.changerScene(Scenes.SCENE_PANIER);
    }

    /**
     * change de scene pour valider la commande
     * @param event 
     */
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

    /**
     * bouton d'ajout de produit devient visible et utilisable
     * puis change de scene
     * @param event 
     */
    public void GestionProduitAdmin(ActionEvent event)
    {
        controller.getView().getpAdmin().getbAjoutProduit().setVisible(true);
        controller.getView().getpAdmin().getbAjoutUtilisateur().setVisible(false);
        controller.changerScene(Scenes.SCENE_ADMIN_PRODUIT); // ZONE ADMIN
    }

    /**
     * bouton d'ajout d'utilisateur devient visible et utilisable
     * puis change de scene
     * @param event 
     */
    public void GestionUtilisateurAdmin(ActionEvent event)
    {

        controller.getView().getpAdmin().getbAjoutProduit().setVisible(false);
        controller.getView().getpAdmin().getbAjoutUtilisateur().setVisible(true);
        controller.changerScene(Scenes.SCENE_ADMIN_UTILISATEUR); // ZONE ADMIN
    }

    /**
     * change de scene
     * @param event 
     */
    public void afficherCommandesUtilisateur(ActionEvent event)
    {
        controller.changerScene(Scenes.SCENE_COMMANDES);
    }

    /**
     * ajoute un produit au panier
     * @param event 
     */
    public void ajouterProduitPanier(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        if (controller.getModel().ajouterAuPanier(((PaneProduit) source.getParent().getParent()).getIndex()))
            afficherPanier(event);
    }

    /**
     * met a jour la quantité de produits différents dans le panier et
     * le prix total du panier
     * @param event 
     */
    public void changementQuantitePanier(ActionEvent event)
    {
        ComboBox source = ((ComboBox) event.getSource());
        controller.getModel().modifierQuantitePanier(((PaneProduitPanier) source.getParent()).getIndex(), (int) source.getValue());
        controller.getView().setPrixPanier(controller.getModel().getPanier().getPrix());
        controller.getView().getsPanier().update(controller.getView());
        controller.getView().getpEntete().requestFocus();
    }

    /**
     * supprime un produit du panier
     * @param event 
     */
    public void supprimerProduitPanier(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        int index = ((PaneProduitPanier) source.getParent()).getIndex();
        controller.getModel().supprimerProduitPanier(index);
        controller.getView().getPanesProduitPanier().remove(index);
        controller.changerScene(Scenes.SCENE_PANIER);
    }

    /**
     * vérifie le blindage de l'adresse saisie 
     * puis change de scène
     * @param event 
     */
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

    /**
     * change de scene
     * @param event 
     */
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

    /**
     * action quand on clic sur le textField en paramètre
     * @param element 
     */
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

    /**
     * change de scene
     * @param event 
     */
    public void bonjour(ActionEvent event)
    {
        controller.setRedirectionCommande(false);
        if (!controller.utilisateurConnecte())
            controller.changerScene(Scenes.SCENE_CONNEXION);
        else
            controller.changerScene(Scenes.SCENE_PROFIL);
    }

    /**
     * change de scene
     * @param event 
     */
    public void modifierProduitAdminRedirection(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        controller.getModel().setProduitSelectionne(controller.getModel().getTousLesProduits().get(((PaneProduitAdmin) source.getParent().getParent()).getIndex()));
        controller.changerScene(Scenes.SCENE_MODIFIER_PRODUIT);
        Platform.runLater(() ->
        {
            controller.getModel().updateImagesProduit();
        });
    }

    /**
     * change de scene
     * @param event 
     */
    public void modifierUtilisateurAdminRedirection(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        controller.getModel().setUtilisateurSelectionne(controller.getModel().getTousLesUtilisateurs().get(((PaneUtilisateurAdmin) source.getParent().getParent()).getIndex()));
        controller.changerScene(Scenes.SCENE_MODIFIER_UTILISATEUR);
    }

    /**
     * vérifie le blindage des informations lors de la modifications d'un utilisateur
     * puis met a jour les information de l'utilisateur choisit
     * @param event 
     */
    public void modifierUtilisateurSelectionne(ActionEvent event)
    {
        String nom = controller.getView().getsModifierUtilisateur().gettNom().getText();
        String prenom = controller.getView().getsModifierUtilisateur().gettPrenom().getText();
        String email = controller.getView().getsModifierUtilisateur().gettEmail().getText();
        String role = controller.getView().getsModifierUtilisateur().getSelectRole();

        if (!nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && !role.isEmpty())
        {
            Utilisateur u = controller.getModel().getUtilisateurSelectionne();
            u.setNom(nom);
            u.setPrenom(prenom);
            u.setEmail(email);
            u.setRole(role);

            if (controller.getModel().updateUtilisateurSelectionne())
                controller.changerScene(Scenes.SCENE_ADMIN_UTILISATEUR); //zone admin utilisateur
        } else
            controller.getView().getsModifierUtilisateur().setUtiliisateurIncompleteVisible();
    }

    /**
     * supprime l'utilisateur choisit
     * @param event 
     */
    public void supprimerUtilisateurAdministrateur(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        controller.getModel().setUtilisateurSelectionne(controller.getModel().getTousLesUtilisateurs().get(((PaneUtilisateurAdmin) source.getParent().getParent()).getIndex()));

        if (controller.getModel().supprimerUtilisateurSelectionnee())
            controller.changerScene(Scenes.SCENE_ADMIN_UTILISATEUR);
    }

    /**
     * supprime le produit choisit
     * @param event 
     */
    public void supprimerProduitAdministrateur(ActionEvent event)
    {
        Button source = ((Button) event.getSource());
        controller.getModel().setProduitSelectionne(controller.getModel().getTousLesProduits().get(((PaneProduitAdmin) source.getParent().getParent()).getIndex()));
        System.out.println(controller.getModel().getProduitSelectionne().toString());
        if (controller.getModel().supprimerProduitSelectionnee())
            controller.changerScene(Scenes.SCENE_ADMIN_PRODUIT);
    }

    /**
     * vérifie le blindage des informations lors de la modifications d'un produit
     * puis met a jour les information du produit choisit
     * @param event 
     */
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
        String image = controller.getView().getsModifierProduit().getcbListImages().getSelectionModel().getSelectedItem();

        if (!nom.isEmpty() && !fournisseur.isEmpty() && !prixUnitaire.isEmpty() && !stock.isEmpty()
                && !quantiteUnLot.isEmpty() && !prixUnLot.isEmpty() && !promotion.isEmpty())
        {
            Produit p = controller.getModel().getProduitSelectionne();

            p.setNom(nom);
            p.setCategorie(categorie);
            p.setNomFournisseur(fournisseur);
            p.setPrixUnitaire(Double.parseDouble(prixUnitaire));
            p.setStock(Integer.parseInt(stock));
            p.setQuantiteUnLot(Integer.parseInt(quantiteUnLot));
            p.setPrixUnLot(Double.parseDouble(prixUnLot));
            p.setPromotion(Double.parseDouble(promotion) / 100);
            p.setPromotionActive(promotionActive);
            p.setLienImage(image);
            if ((p.getId() == 0 && controller.getModel().validerCreationProduit())
                    || (p.getId() != 0 && controller.getModel().updateProduitSelectionne()))
                controller.changerScene(Scenes.SCENE_ADMIN_PRODUIT);

        } else
            controller.getView().getsModifierProduit().setProduitIncompleteVisible();
    }

    /**
     * permet d'upload les images sur le server
     * @param event 
     */
    public void uploadImage(ActionEvent event)
    {
        File temp = controller.getFileChooser().showOpenDialog(null);
        if (temp != null)
            if (controller.getModel().uploadImageProduit(temp))
            {
                controller.getView().getsModifierProduit().getcbListImages().getItems().add(temp.getName());
                controller.getView().getsModifierProduit().getcbListImages().getSelectionModel().select(temp.getName());
            }
    }

    /**
     * change le style quand on passe sur un bouton
     * @param ceButton 
     */
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

    /**
     * change le style quand on passe sur un bouton
     * @param ceButton 
     */
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

    /**
     * change le style du curseur quand on passe sur le bouton en paramètre
     * @param ceButton 
     */
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
