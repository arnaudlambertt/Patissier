/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author mathi
 */
public class SceneConnexion extends javafx.scene.Scene implements Scene
{
    private Button bConnection;
    private Button bCreerCompte;
    
    private Image iLogo; // à AJOUTER
    
    private Label lEmail;
    private Label lMotDePasse;
    private Label lNouveauClient;
    private Label lDejaClient;
    
    private TextField tEmail;
    private PasswordField pMotDePasse;

    public SceneConnexion(Parent root)
    {
        super(root);
    }
    
    public void init()
    {
        bConnection = new Button("ME CONNECTER");
        bCreerCompte = new Button("CRÉER MON COMPTE");
        
//        bConnection.setMaxWidth(Double.MAX_VALUE);
//        bConnection.setMaxWidth(Double.MAX_VALUE);
        
        
        BorderPane panneau = new BorderPane();
        VBox flowPaneEst = new VBox();
        VBox flowPaneWest = new VBox();
        
        lEmail = new Label("Email");
        lMotDePasse = new Label("Mot de passe");
        lNouveauClient = new Label("Nouveau client ?");
        lDejaClient = new Label("Déjà client ?");
    
        tEmail = new TextField();
        tEmail.setMaxWidth(300);
        pMotDePasse = new PasswordField();
        pMotDePasse.setMaxWidth(300);
        
        // FLOW PANE EST
        flowPaneEst.getChildren().add(lNouveauClient);
        flowPaneEst.getChildren().add(bCreerCompte);
        flowPaneEst.setAlignment(Pos.CENTER);
        
        // FLOW PANE WEST
        flowPaneWest.getChildren().add(lDejaClient);
        flowPaneWest.getChildren().add(lEmail);
        flowPaneWest.getChildren().add(tEmail);
        flowPaneWest.getChildren().add(lMotDePasse);
        flowPaneWest.getChildren().add(pMotDePasse);
        flowPaneWest.getChildren().add(bConnection);
        flowPaneWest.setAlignment(Pos.CENTER);
        
        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);
        
        
        // BORDER PANE
        //panneau.setPadding(new Insets(150, 200, 150, 200)); MARGES TOP/RIGHT/BOTTON/LEFT
        panneau.setRight(flowPaneEst);
        panneau.setCenter(separator2);
        panneau.setLeft(flowPaneWest);
        
        setRoot(panneau);
        
    }
    
    @Override
    public void update()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}