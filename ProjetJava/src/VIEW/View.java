/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Scenes;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class View
{

    private final Stage primaryStage;
    private final PaneEntete pEntete;
    private final SceneProduits sProduits;
    private final SceneConnexion sConnexion;
    private final SceneCreationCompte sCreationCompte;
    private final SceneProfil sProfil;
    private final ScenePanier sPanier;
    private final sceneAdresse sAdresse;
    private final ScenePaiement sPaiement;
    private final SceneErreur404 sErreur404;

    public View(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.pEntete = new PaneEntete();
        this.sProduits = new SceneProduits();
        this.sConnexion = new SceneConnexion();
        this.sCreationCompte = new SceneCreationCompte();
        this.sProfil = new SceneProfil();
        this.sPanier = new ScenePanier();
        this.sAdresse = new sceneAdresse();
        this.sPaiement = new ScenePaiement();
        this.sErreur404 = new SceneErreur404();
    }

    public void init()
    {
        SceneCustom.setup(this);
        sProduits.init();
        sConnexion.init();
        sCreationCompte.init();
        sProfil.init();
        sPanier.init();
        sAdresse.init();
        sPaiement.init();
        sErreur404.init();
    }

    public void changerScene(int SceneConstant)
    {
        switch (SceneConstant) //appelle changerScene correspondant
        {
            case Scenes.SCENE_PRODUITS:
                changerScene(sProduits);
                break;
            case Scenes.SCENE_CONNEXION:
                changerScene(sConnexion);
                break;
            case Scenes.SCENE_CREATION_COMPTE:
                changerScene(sCreationCompte);
                break;
            case Scenes.SCENE_PROFIL:
                changerScene(sProfil);
                break;
            case Scenes.SCENE_PANIER:
                changerScene(sPanier);
                break;
            case Scenes.SCENE_ADRESSE:
                changerScene(sAdresse);
                break;
            case Scenes.SCENE_PAIEMENT:
                changerScene(sPaiement);
                break;
            default:
                changerScene(sErreur404);
        }
    }

    public void changerScene(SceneCustom scene)
    {
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();
        
        scene.update(this);
        scene.updateFenetre(this);

        primaryStage.setScene(scene);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
    }

    public TextField gettEmail()
    {
        return sConnexion.gettEmail();
    }

    public Button getbConnexion()
    {
        return sConnexion.getbConnexion();
    }

    public PasswordField getMotDePasse()
    {
        return sConnexion.getpMotDePasse();
    }

    public Button getbCreerCompte()
    {
        return sConnexion.getbCreerCompte();
    }

    public Stage getPrimaryStage()
    {
        return primaryStage;
    }

    public SceneConnexion getSConnexion()
    {
        return sConnexion;
    }

    public SceneCreationCompte getCreationCompte()
    {
        return sCreationCompte;
    }

    public SceneProfil getsProfil()
    {
        return sProfil;
    }

    public ArrayList<PaneProduit> getPaneProduits()
    {
        return sProduits.getPaneProduits();
    }

    public SceneProduits getsProduits()
    {
        return sProduits;
    }

    public ArrayList<PaneProduitPanier> getPanesProduitPanier()
    {
        return sPanier.getPaneProduitPanier();
    }

    public ScenePanier getsPanier()
    {
        return sPanier;
    }

    public PaneEntete getpEntete()
    {
        return pEntete;
    }

    public void setPrixPanier(double prixTotal)
    {
        sPanier.setPrixTotal(prixTotal);
        sPaiement.setPrixTotal(prixTotal);
    }

    public Button getbValiderPanier()
    {
        return sPanier.gebtValiderPanier();
    }
    
    public Button getbValiderAdresse()
    {
        return sAdresse.getbValiderAdresse();
    }
    
    public TextField gettCodePostal()
    {
        return sAdresse.gettCodePostal();
    }
    
    public TextField gettVille()
    {
        return sAdresse.gettVille();
    }
    
    public TextField gettRue()
    {
        return sAdresse.gettRue();
    }
    
    public TextField gettNumero()
    {
        return sAdresse.gettNumero();
    }
    
    public void setAdresseIncompleteVisible()
    {
        sAdresse.setAdresseIncompleteVisible();
    }
    
    public Button getbConfirmerCommande()
    {
        return sPaiement.getbConfirmer();
    }

    public void setAdresse(String adresse)
    {
        sPaiement.setAdresse(adresse);
    }
    
    public void setProgressionVisible(boolean visible)
    {
        sConnexion.setProgressionVisible(visible);
        sCreationCompte.setProgressionVisible(visible);
    }
}
