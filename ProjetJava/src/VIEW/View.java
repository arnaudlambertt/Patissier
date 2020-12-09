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
    private final PaneAdmin pAdmin;
    private final SceneProduits sProduits;
    private final SceneConnexion sConnexion;
    private final SceneCreationCompte sCreationCompte;
    private final SceneProfil sProfil;
    private final ScenePanier sPanier;
    private final sceneAdresse sAdresse;
    private final ScenePaiement sPaiement;
    private final SceneErreur404 sErreur404;
    private final SceneProduitsAdmin sProduitAdmin;
    private final SceneModifierProduit sModifierProduit;

    public View(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.pEntete = new PaneEntete();
        this.pAdmin = new PaneAdmin();
        this.sProduits = new SceneProduits();
        this.sConnexion = new SceneConnexion();
        this.sCreationCompte = new SceneCreationCompte();
        this.sProfil = new SceneProfil();
        this.sPanier = new ScenePanier();
        this.sAdresse = new sceneAdresse();
        this.sPaiement = new ScenePaiement();
        this.sErreur404 = new SceneErreur404();
        this.sProduitAdmin = new SceneProduitsAdmin();
        this.sModifierProduit = new SceneModifierProduit();
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
        sProduitAdmin.init();
        sModifierProduit.init();
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
            case Scenes.SCENE_ADMIN:
                changerScene(sProduitAdmin);
                break;
            case Scenes.SCENE_MODIFIER_PRODUIT:
                changerScene(sModifierProduit);
                break;
            default:
                changerScene(sErreur404);
        }
    }

    public void changerScene(SceneCustom scene)
    {
        //double width = primaryStage.getWidth();
        //double height = primaryStage.getHeight();
        
        scene.update(this);
        scene.updateFenetre(this);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        //primaryStage.setWidth(width);
        //primaryStage.setHeight(height);
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
    
    /// GETTER SCENES
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
    
    public SceneProduits getsProduits()
    {
        return sProduits;
    }
    
    public ScenePanier getsPanier()
    {
        return sPanier;
    }

    public SceneModifierProduit getsModifierProduit()
    {
        return sModifierProduit;
    }

    public ArrayList<PaneProduit> getPanesProduit()
    {
        return sProduits.getPanesProduit();
    }
    
     public ArrayList<PaneProduitAdmin> getPanesProduitAdmin()
    {
        return sProduitAdmin.getPanesProduitAdmin();
    }

    public ArrayList<PaneProduitPanier> getPanesProduitPanier()
    {
        return sPanier.getPanesProduitPanier();
    }

    

    public PaneEntete getpEntete()
    {
        return pEntete;
    }

    public PaneAdmin getpAdmin()
    {
        return pAdmin;
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
