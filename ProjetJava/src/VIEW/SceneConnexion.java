/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import CONSTANT.Panes;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author mathi
 */
public class SceneConnexion extends SceneCustom
{
    private Button bConnection;
    private Button bCreerCompte;

    private Image iLogo; // à AJOUTER

    
    private Label lEmailOuMdpIncorrect;

    private TextField tEmail;
    private PasswordField pMotDePasse;
    private final GridPane grid;

    
    
    public SceneConnexion()
    {
        this.grid = new GridPane();
    }

    @Override
    public void init()
    {
        ((BorderPane) getRoot()).setStyle("-fx-background-color: " + Couleurs.BLANC + "; "
                + "-fx-border-color: " + Couleurs.BLANC + ";");
        bConnection = new Button("ME CONNECTER");
        bConnection.setStyle("-fx-background-color : "+Couleurs.ORANGE_BOULANGER+"; -fx-text-fill: "+Couleurs.BLANC);
        bCreerCompte = new Button("CRÉER MON COMPTE");
        bCreerCompte.setStyle("-fx-background-color : "+Couleurs.ORANGE_BOULANGER+"; -fx-text-fill: "+Couleurs.BLANC);

        
        
        VBox vBoxEst = new VBox();
        VBox vBoxWest = new VBox();

        Label lEmail = new Label("Email");
        Label lMotDePasse = new Label("Mot de passe");
        Label lNouveauClient = new Label("Nouveau client ?");
        Label lDejaClient = new Label("Déjà client ?");

        lEmailOuMdpIncorrect = new Label("Email ou mot de passe incorrect !");
        lEmailOuMdpIncorrect.setStyle("-fx-text-fill : "+Couleurs.RED);
        lEmailOuMdpIncorrect.setVisible(false);

        tEmail = new TextField();
        tEmail.setMaxWidth(Panes.SIZE_TEXTFIELD_SCENE_CONNEXION);
        pMotDePasse = new PasswordField();
        pMotDePasse.setMaxWidth(Panes.SIZE_TEXTFIELD_SCENE_CONNEXION);

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
        vBoxWest.getChildren().add(lEmailOuMdpIncorrect);
        vBoxWest.setAlignment(Pos.CENTER);

        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);

        grid.add(vBoxWest, 0, 0);
        grid.add(separator2, 1, 0);
        grid.add(vBoxEst, 2, 0);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(Panes.GRID_HGAP_SCENE_CONNEXION);
    }

    @Override
    public void update(View v)
    {
        lEmailOuMdpIncorrect.setVisible(false);
        page.setCenter(grid);
    }

    public Label getlEmailOuMdpIncorrect()
    {
        return lEmailOuMdpIncorrect;
    }

    public Button getbConnexion()
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
