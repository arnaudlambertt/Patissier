/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
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
public class SceneConnexion extends SceneCustom
{
    private Button bConnection;
    private Button bCreerCompte;

    private Image iLogo; // à AJOUTER

    private Label lEmail;
    private Label lMotDePasse;
    private Label lNouveauClient;
    private Label lDejaClient;
    private Label lEmailOuMdpIncorrect;

    private TextField tEmail;
    private PasswordField pMotDePasse;

    public SceneConnexion()
    {
    }

    @Override
    public void init()
    {
        bConnection = new Button("ME CONNECTER");
        bConnection.setStyle("-fx-background-color : "+Couleurs.ORANGE_BOULANGER+"; -fx-text-fill: "+Couleurs.BLANC);
        bCreerCompte = new Button("CRÉER MON COMPTE");
        bCreerCompte.setStyle("-fx-background-color : "+Couleurs.ORANGE_BOULANGER+"; -fx-text-fill: "+Couleurs.BLANC);

        BorderPane panneau = new BorderPane();
        GridPane grid = new GridPane();
        VBox vBoxEst = new VBox();
        VBox vBoxWest = new VBox();

        lEmail = new Label("Email");
        lMotDePasse = new Label("Mot de passe");
        lNouveauClient = new Label("Nouveau client ?");
        lDejaClient = new Label("Déjà client ?");

        lEmailOuMdpIncorrect = new Label("Email ou mot de passe incorrect !");
        lEmailOuMdpIncorrect.setStyle("-fx-text-fill : ff0000");
        lEmailOuMdpIncorrect.setVisible(false);

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
        vBoxWest.getChildren().add(lEmailOuMdpIncorrect);
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
        //panneau.set
        conteneurPrincipal.getChildren().add(new VBox(panneau));

    }

    @Override
    public void update(View v)
    {
        super.update(v);
        lEmailOuMdpIncorrect.setVisible(false);
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
