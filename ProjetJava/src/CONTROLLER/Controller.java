/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import CONSTANT.Scenes;
import MODEL.*;
import VIEW.PaneProduit;
import VIEW.SceneCustom;
import VIEW.View;
import java.util.ArrayList;
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

        eventController.setHover(view.getpEntete().getbLogo());
        eventController.setHover(view.getpEntete().getbRecherche());
        eventController.setHover(view.getpEntete().getbBonjour());
        eventController.setHover(view.getpEntete().getbPanier());
        
        for (int i = 0; i < 9; ++i)
            eventController.setHoverButtonOrange(view.getpEntete().getbCategories(i));

        //Actions boutons connexion utilisateur
        view.getbConnexion().setOnAction(eventController::btnConnexion);
        eventController.setHoverButtonOrangeClair(view.getSConnexion().getbConnexion());

        //Actions boutons redirection vers cree compte
        view.getbCreerCompte().setOnAction(eventController::btnRedirectionCreerCompte);
        eventController.setHoverButtonOrangeClair(view.getSConnexion().getbCreerCompte());

        //Actions boutons cree compte utilisateur
        view.getCreationCompte().getbCreeMonCompte().setOnAction(eventController::btnCreerCompte);
        eventController.setHoverButtonOrangeClair(view.getCreationCompte().getbCreeMonCompte());

        //Actions barre de recherche
        eventController.setActionBarreRecherche(this.view.getpEntete().gettBarreRecherche());
        
        //Actions bouton rechercher
        eventController.setHoverButtonOrangeClair(this.view.getpEntete().getbRecherche());
        
        view.getpEntete().getbLogo().setOnAction(eventController::afficherAccueil);
        view.getpEntete().getbBonjour().setOnAction(eventController::btnBonjour);
        
        changementScene(Scenes.SCENE_PRODUITS);
        view.getPrimaryStage().show();
    }

    public void changementScene(int SceneConstant)
    {
        switch (SceneConstant) //prepare scenes si besoin
        {
            case Scenes.SCENE_PRODUITS:
                preparationSceneProduits();
                break;
            default:;
        }
        view.changementScene(SceneConstant);
    }

    public void preparationSceneProduits()
    {
        ArrayList<Produit> produitsFiltre = model.getProduitsFiltre();
        ArrayList<PaneProduit> paneProduits = view.getPaneProduits();
        paneProduits.clear();
        for (int i = 0; i < produitsFiltre.size(); ++i)
        {
            PaneProduit pp = new PaneProduit(i, produitsFiltre.get(i));
            paneProduits.add(pp);
        }
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
