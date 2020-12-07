/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import CONSTANT.Panes;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Benjamin
 */
public class SceneCreationCompte extends SceneCustom
{

    private Label lMesInformationsDeContact;
    private Label lNom;
    private Label lPrenom;
    private Label lEmail;
    private Label lMotDePasse;
    private Label lChampsObligatoire;
    private Label lEmailOuMdpIncorrect;

    private TextField tNom;
    private TextField tPrenom;
    private TextField tEmail;
    private PasswordField pMotDePasse;

    private Button bCreeMonCompte;
    private final ImageView progressionPanier;
    private final VBox box;

    public SceneCreationCompte()
    {
        this.box = new VBox();
        this.progressionPanier = new ImageView();
    }

    @Override
    public void init()
    {
        FlowPane FlowPaneNom = new FlowPane();
        FlowPane FlowPanePrenom = new FlowPane();
        FlowPane FlowPaneEmail = new FlowPane();
        FlowPane FlowPaneMotDePasse = new FlowPane();

        progressionPanier.setImage(new Image("http://93.3.238.99/uploads/Panier-2.png"));
        progressionPanier.setVisible(false);
        
        lMesInformationsDeContact = new Label("Mes informations de contact : ");
        lNom = new Label("Nom* : ");
        lNom.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lNom.setAlignment(Pos.CENTER_RIGHT);
        lPrenom = new Label("Prenom* : ");
        lPrenom.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lPrenom.setAlignment(Pos.CENTER_RIGHT);
        lEmail = new Label("Email* : ");
        lEmail.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lEmail.setAlignment(Pos.CENTER_RIGHT);
        lMotDePasse = new Label("Mot de passe* : ");
        lMotDePasse.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lMotDePasse.setAlignment(Pos.CENTER_RIGHT);
        lChampsObligatoire = new Label("* Champs obligatoires");

        lEmailOuMdpIncorrect = new Label("Email déjà existant !");
        lEmailOuMdpIncorrect.setStyle("-fx-text-fill : ff0000");
        lEmailOuMdpIncorrect.setVisible(false);

        tNom = new TextField();
        tNom.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        tPrenom = new TextField();
        tPrenom.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        tEmail = new TextField();
        tEmail.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        pMotDePasse = new PasswordField();
        pMotDePasse.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);

        bCreeMonCompte = new Button("Cree Mon Compte");
        bCreeMonCompte.setStyle("-fx-background-color : " + Couleurs.ORANGE_PATISSIER + "; -fx-text-fill: " + Couleurs.BLANC
        + ";-fx-font-weight: bold;");

        //FlowPaneNom
        FlowPaneNom.getChildren().addAll(lNom, tNom);
        FlowPaneNom.setAlignment(Pos.CENTER);

        //FlowPanePaenom
        FlowPanePrenom.getChildren().addAll(lPrenom, tPrenom);
        FlowPanePrenom.setAlignment(Pos.CENTER);

        //FlowPaneEmail
        FlowPaneEmail.getChildren().addAll(lEmail, tEmail);
        FlowPaneEmail.setAlignment(Pos.CENTER);

        //FlowPaneMotDePasse
        FlowPaneMotDePasse.getChildren().addAll(lMotDePasse, pMotDePasse);
        FlowPaneMotDePasse.setAlignment(Pos.CENTER);

        //Box
        box.getChildren().add(progressionPanier);
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(lMesInformationsDeContact);
        box.getChildren().add(FlowPaneNom);
        box.getChildren().add(FlowPanePrenom);
        box.getChildren().add(FlowPaneEmail);
        box.getChildren().add(FlowPaneMotDePasse);
        box.getChildren().add(bCreeMonCompte);
        box.getChildren().add(lChampsObligatoire);
        box.getChildren().add(lEmailOuMdpIncorrect);
    }
    

    @Override
    public void update(View v)
    {
        page.setCenter(box);
    }

    public Label getlEmailOuMdpIncorrect()
    {
        return lEmailOuMdpIncorrect;
    }

    public TextField gettNom()
    {
        return tNom;
    }

    public TextField gettPrenom()
    {
        return tPrenom;
    }

    public TextField gettEmail()
    {
        return tEmail;
    }

    public PasswordField getpMotDePasse()
    {
        return pMotDePasse;
    }

    public Button getbCreeMonCompte()
    {
        return bCreeMonCompte;
    }

    public void clearTextField()
    {
        this.tEmail.clear();
        this.tNom.clear();
        this.tPrenom.clear();
        this.pMotDePasse.clear();
    }
    
    public void setProgressionVisible(boolean visible)
    {
        progressionPanier.setVisible(visible);
    }

}
//nom/prenom/email/mdp
