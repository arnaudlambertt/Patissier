/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import CONSTANT.Scenes;
import MODEL.*;
import VIEW.PaneProduit;
import VIEW.PaneProduitPanier;
import VIEW.View;
import java.util.ArrayList;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class Controller
{

    private final Model model;
    private final View view;
    private final EventController eventController;
    private boolean redirectionCommande;

    public Controller(Stage primaryStage)
    {
        this.model = new Model();
        this.view = new View(primaryStage);
        this.eventController = new EventController(this);
        this.redirectionCommande = false;
    }

    public void init()
    {
        model.init();
        view.init();

        eventController.hover(view.getpEntete().getbLogo());
        eventController.hover(view.getpEntete().getbRecherche());
        eventController.hover(view.getpEntete().getbBonjour());
        eventController.hover(view.getpEntete().getbPanier());

        for (int i = 0; i < 9; ++i)
        {
            view.getpEntete().getbCategories(i).setOnAction(eventController::afficherCategorie);
            eventController.hoverButtonOrange(view.getpEntete().getbCategories(i));
        }

        //Actions boutons connexion utilisateur
        view.getbConnexion().setOnAction(eventController::connexion);
        eventController.hoverButtonOrangeClair(view.getSConnexion().getbConnexion());

        //Actions boutons redirection vers cree compte
        view.getbCreerCompte().setOnAction(eventController::redirectionCreerCompte);
        eventController.hoverButtonOrangeClair(view.getSConnexion().getbCreerCompte());

        //Actions boutons cree compte utilisateur
        view.getCreationCompte().getbCreeMonCompte().setOnAction(eventController::creerCompte);
        eventController.hoverButtonOrangeClair(view.getCreationCompte().getbCreeMonCompte());

        //Actions barre de recherche
        view.getpEntete().gettBarreRecherche().setOnKeyPressed(eventController::entrerRercherche);
        eventController.focusBarreRecherche(view.getpEntete().gettBarreRecherche());

        //Actions bouton rechercher
        view.getpEntete().getbRecherche().setOnAction(eventController::afficherRecherche);
        eventController.hoverButtonOrangeClair(view.getpEntete().getbRecherche());

        //Actions boutons entete
        view.getpEntete().getbLogo().setOnAction(eventController::afficherAccueil);
        view.getpEntete().getbBonjour().setOnAction(eventController::bonjour);
        view.getpEntete().getbPanier().setOnAction(eventController::afficherPanier);

        view.getsProfil().getbDeconnectionUtilisateur().setOnAction(eventController::deconnecterUtilisateur);
        view.getsProfil().getbSupprimerCompte().setOnAction(eventController::supprimerUtilisateur);

        //Actions bouton valider Panier
        view.getbValiderPanier().setOnAction(eventController::validerPanier);
        eventController.hover(view.getbValiderPanier());

        //Actions bouton validerLivraison
        view.getbValiderAdresse().setOnAction(eventController::validerAdresse);//
        eventController.hover(view.getbValiderAdresse());
       
        //Actions bouton confirmerCommande
        view.getbConfirmerCommande().setOnAction(eventController::commander);
        eventController.hover(view.getbConfirmerCommande());        
        
        changerScene(Scenes.SCENE_PRODUITS);
        view.getPrimaryStage().show();
        //view.getPrimaryStage().setMaximized(true);

        //APRES LE SHOW
        view.getpEntete().format();
    }

    public void changerScene(int SceneConstant)
    {
        switch (SceneConstant) //prepare scenes si besoin
        {
            case Scenes.SCENE_PRODUITS:
                preparerSceneProduits();
                break;
            case Scenes.SCENE_PANIER:
                preparerScenePanier();
                break;
            case Scenes.SCENE_PROFIL:
                preparerSceneUtilisateur();
                break;
            case Scenes.SCENE_CONNEXION:
                preparerSceneConnexion();
                break;
            case Scenes.SCENE_PAIEMENT:
                view.setAdresse(model.getPanier().getAdresse());
                break;
            default:;
        }

        view.changerScene(SceneConstant);
    }

    public void preparerSceneConnexion()
    {
        view.getSConnexion().clear();
    }

    public void preparerSceneCreationCompte()
    {
        view.getCreationCompte().clearTextField();
    }


    public void preparerSceneProduits()
    {
        ArrayList<Produit> produitsFiltre = model.getProduitsFiltre();
        ArrayList<PaneProduit> paneProduits = view.getPaneProduits();
        paneProduits.clear();
        for (int i = 0; i < produitsFiltre.size(); ++i)
        {
            PaneProduit pp = new PaneProduit(i, produitsFiltre.get(i));
            eventController.hoverButtonOrangeClair(pp.getbAjouterPanier());
            pp.getbAjouterPanier().setOnAction(eventController::ajouterProduitPanier);
            paneProduits.add(pp);
        }
    }

    public void preparerScenePanier()
    {
        Commande panier = model.getPanier();
        ArrayList<PaneProduitPanier> panesProduitPanier = view.getPanesProduitPanier();
        panesProduitPanier.clear();

        ArrayList<Produit> keys = new ArrayList<>(panier.getProduitsCommande().keySet());

        for (int i = 0; i < keys.size(); ++i)
        {
            PaneProduitPanier pp = new PaneProduitPanier(i,(Produit)keys.get(i),panier.getProduitsCommande().get((Produit)keys.get(i)));
            pp.getCbNombreProduit().setOnAction(eventController::changementQuantitePanier);
            pp.getbSupprimer().setOnAction(eventController::supprimerProduitPanier);
            eventController.hover(pp.getbSupprimer());
            panesProduitPanier.add(pp);
        }
        view.setPrixPanier(panier.getPrix());
    }

    public void preparerSceneUtilisateur()
    {
        Utilisateur utilisateurActif = model.getUtilisateur();

        view.getsProfil().gettNom().setText(utilisateurActif.getNom());
        view.getsProfil().gettPrenom().setText(utilisateurActif.getPrenom());
        view.getsProfil().gettEmail().setText(utilisateurActif.getEmail());
        view.getsProfil().update(view);
    }

    public Utilisateur getUtilisateur()
    {
        return model.getUtilisateur();
    }

    public void setEmail(String email)
    {
        model.setEmail(email);
    }

    @Override
    public String toString()
    {
        return "Je suis le controller";
    }

    public Model getModel()
    {
        return model;
    }

    public View getView()
    {
        return view;
    }

    public boolean utilisateurConnecte()
    {
        return model.utilisateurConnecte();
    }

    public void setRedirectionCommande(boolean redirige)
    {
        this.redirectionCommande = redirige;
        view.setProgressionVisible(redirige);
    }
 
    public boolean redirectionCommande()
    {
        return redirectionCommande;
    }
}
