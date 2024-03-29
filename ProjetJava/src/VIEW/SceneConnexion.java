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
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author mathi
 */
public class SceneConnexion extends SceneCustom
{
    private Button bConnexion;
    private Button bCreerCompte;
    private Label lEmailOuMdpIncorrect;
    private TextField tEmail;
    private PasswordField pMotDePasse;
    private final ImageView progressionPanier;
    private final VBox box;
    
    public SceneConnexion()
    {
        this.progressionPanier = new ImageView();
        this.box = new VBox();
    }

    @Override
    public void init()
    {
        progressionPanier.setImage(new Image("http://93.3.238.99/uploads/Panier-2.png"));
        progressionPanier.setVisible(false);
        
        bConnexion = new Button("ME CONNECTER");
        bConnexion.setStyle("-fx-background-color : "+Couleurs.ORANGE_PATISSIER+"; -fx-text-fill: "+Couleurs.BLANC
        + ";-fx-font-weight: bold;");
        bConnexion.setMinSize(240, 40);
        bCreerCompte = new Button("CRÉER MON COMPTE");
        bCreerCompte.setStyle("-fx-background-color : "+Couleurs.ORANGE_PATISSIER+"; -fx-text-fill: "+Couleurs.BLANC
        + ";-fx-font-weight: bold;");
        bCreerCompte.setMinSize(240, 40);
        
        VBox vBoxEst = new VBox(5);
        VBox vBoxWest = new VBox(5);

        Label lEmail = new Label("Email");
        Label lMotDePasse = new Label("Mot de passe");
        Label lNouveauClient = new Label("Nouveau client ?");
        Label lDejaClient = new Label("Déjà client ?");

        lEmailOuMdpIncorrect = new Label("Email ou mot de passe incorrect !");
        lEmailOuMdpIncorrect.setStyle("-fx-text-fill : "+Couleurs.ROUGE);
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
        vBoxWest.getChildren().add(bConnexion);
        vBoxWest.getChildren().add(lEmailOuMdpIncorrect);
        vBoxWest.setAlignment(Pos.CENTER);

        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);
        
        GridPane grid = new GridPane();
        grid.add(vBoxWest, 0, 0);
        grid.add(separator2, 1, 0);
        grid.add(vBoxEst, 2, 0);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(Panes.GRID_HGAP_SCENE_CONNEXION);
        box.getChildren().add(progressionPanier);
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(grid);
    }

    public void clear()
    {
        this.tEmail.clear();
        this.pMotDePasse.clear();
    }
    
    @Override
    public void update(View v)
    {
        lEmailOuMdpIncorrect.setVisible(false);
        page.setCenter(box);
    }

    public Label getlEmailOuMdpIncorrect()
    {
        return lEmailOuMdpIncorrect;
    }

    public Button getbConnexion()
    {
        return bConnexion;
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
    
    public void setProgressionVisible(boolean visible)
    {
        progressionPanier.setVisible(visible);
    }
}
