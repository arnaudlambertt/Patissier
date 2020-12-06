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
import VIEW.SceneCustom;
import VIEW.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.stage.Stage;
import org.bouncycastle.crypto.prng.RandomGenerator;

/**
 *
 * @author Benjamin
 */
public class Controller
{

    private final Model model;
    private final View view;
    private final EventController eventController;

    public Controller(Stage primaryStage)
    {
        this.model = new Model();
        this.view = new View(primaryStage);
        this.eventController = new EventController(this);
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
        
        view.getpEntete().getbLogo().setOnAction(eventController::afficherAccueil);
        view.getpEntete().getbBonjour().setOnAction(eventController::bonjour);
        
        view.getpEntete().getbPanier().setOnAction(eventController::afficherPanier);
        

        
        changerScene(Scenes.SCENE_PRODUITS);
        view.getPrimaryStage().show();
        
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
            default:;
        }
        view.changerScene(SceneConstant);
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
        ArrayList<PaneProduitPanier> paneProduitPanier = view.getPaneProduitPanier();
        paneProduitPanier.clear();
        
        ArrayList<Produit> keys = new ArrayList<>(panier.getProduitsCommande().keySet());
        
        for (int i = 0; i < keys.size(); ++i)
        {
            PaneProduitPanier pp = new PaneProduitPanier(i,(Produit)keys.get(i),panier.getProduitsCommande().get((Produit)keys.get(i)));
            pp.getCbNombreProduit().setOnAction(eventController::changementQuantiteProduit);
            paneProduitPanier.add(pp);
        }
        view.setPrixPanier(panier.getPrix());
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

}
