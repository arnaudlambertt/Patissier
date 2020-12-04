/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Scenes;
import CONTROLLER.Controller;
import MODEL.Utilisateur;
import DAO.UtilisateurDAO;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Benjamin
 */
public class View
{

    Button buttonClose;
    Button buttonClose2;
    Button buttonConnection;
    Button submitNouveauCompte;
    
    private PaneEntete pEntete;
    private Stage primaryStage;
    private SceneConnexion sConnexion;
    private SceneCreationCompte sCreationCompte;
    private SceneProduits sProduits;

    public View(Stage primaryStage)
    {
        this.buttonClose = new Button();
        this.buttonClose2 = new Button();
        this.buttonConnection = new Button();
        this.sConnexion = new SceneConnexion();
        this.sCreationCompte = new SceneCreationCompte();
        this.primaryStage = primaryStage;
        this.sProduits = new SceneProduits();
        this.pEntete = new PaneEntete();
    }

    public void init()
    {
        sProduits.init();
        sConnexion.init();
        sCreationCompte.init();
    }

    public void changementScene(int SceneConstant)
    {
        switch (SceneConstant) //appelle changementScene correspondant
        {
            case Scenes.SCENE_PRODUITS:
                changementScene(sProduits);
                break;
            case Scenes.SCENE_CONNEXION:
                changementScene(sConnexion);
                break;
            case Scenes.SCENE_CREATION_COMPTE:
                changementScene(sCreationCompte);
                break;
            default:
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("ERREUR 404 TEMPORAIRE");
                alert.setHeaderText(null);
                alert.setContentText("Cette scene n'existe pas."
                + "\nMATHIAS TAS PAS ENCORE FAIT LERREUR 404");

                alert.showAndWait();
        }
    }

    public void changementScene(SceneCustom scene)
    {
        scene.update(this);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setMaximized(true);
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

    public Button getSubmitNouveauCompte()
    {
        return submitNouveauCompte;
    }

    public SceneConnexion getSConnexion()
    {
        return sConnexion;
    }

    public SceneCreationCompte getCreationCompte()
    {
        return sCreationCompte;
    }

    public ArrayList<PaneProduit> getPaneProduits()
    {
        return sProduits.getPaneProduits();
    }

    public SceneProduits getsProduits()
    {
        return sProduits;
    }

    public PaneEntete getpEntete()
    {
        return pEntete;
    }
}
