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
    
    @Override
    public void init()
    {
        bConnection = new Button("ME CONNECTER");
        bCreerCompte = new Button("CRÉER MON COMPTE");
        
        
        
        BorderPane panneau = new BorderPane();
        GridPane grid = new GridPane();
        VBox vBoxEst = new VBox();
        VBox vBoxWest = new VBox();
        
        lEmail = new Label("Email");
        lMotDePasse = new Label("Mot de passe");
        lNouveauClient = new Label("Nouveau client ?");
        lDejaClient = new Label("Déjà client ?");
    
        tEmail = new TextField();
        tEmail.setMaxWidth(300);
        pMotDePasse = new PasswordField();
        pMotDePasse.setMaxWidth(300);
        
        // FLOW PANE EST
        vBoxEst.getChildren().add(lNouveauClient);
        vBoxEst.getChildren().add(bCreerCompte);
        vBoxEst.setAlignment(Pos.CENTER);
        
        // FLOW PANE WEST
        vBoxWest.getChildren().add(lDejaClient);
        vBoxWest.getChildren().add(lEmail);
        vBoxWest.getChildren().add(tEmail);
        vBoxWest.getChildren().add(lMotDePasse);
        vBoxWest.getChildren().add(pMotDePasse);
        vBoxWest.getChildren().add(bConnection);
        vBoxWest.setAlignment(Pos.CENTER);
        
        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);
        
        grid.add(vBoxWest, 0, 0);
        grid.add(separator2, 1, 0);
        grid.add(vBoxEst, 2, 0);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(100);
        
        
        // BORDER PANE
        //panneau.setPadding(new Insets(150, 200, 150, 200)); MARGES TOP/RIGHT/BOTTON/LEFT
        panneau.setCenter(grid);
        
        setRoot(panneau);
        
    }
    
    @Override
    public void update()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Button getbConnection()
    {
        return bConnection;
    }

    public Button getbCreerCompte()
    {
        return bCreerCompte;
    }

    public TextField gettEmail()
    {
        return tEmail;
    }

    public PasswordField getpMotDePasse()
    {
        return pMotDePasse;
    }
}