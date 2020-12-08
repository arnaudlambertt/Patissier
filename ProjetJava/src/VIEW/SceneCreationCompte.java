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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Benjamin
 */
public class SceneCreationCompte extends SceneCustom
{
    private final VBox box;
    private final TextField tNom;
    private final TextField tPrenom;
    private final TextField tEmail;
    private final PasswordField pMotDePasse;
    private final Label lEmailOuMdpIncorrect;
    private final Button bCreerMonCompte;
    private final ImageView progressionPanier;

    public SceneCreationCompte()
    {
        this.box = new VBox(10);
        this.tNom = new TextField();
        this.tPrenom = new TextField();
        this.tEmail = new TextField();
        this.pMotDePasse = new PasswordField();
        this.lEmailOuMdpIncorrect = new Label();
        this.bCreerMonCompte = new Button();
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
        
        Label lMesInformationsDeContact = new Label("Mes informations de contact : ");
        Label lNom = new Label("Nom* : ");
        lNom.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lNom.setAlignment(Pos.CENTER_RIGHT);
        Label lPrenom = new Label("Prenom* : ");
        lPrenom.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lPrenom.setAlignment(Pos.CENTER_RIGHT);
        Label lEmail = new Label("Email* : ");
        lEmail.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lEmail.setAlignment(Pos.CENTER_RIGHT);
        Label lMotDePasse = new Label("Mot de passe* : ");
        lMotDePasse.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lMotDePasse.setAlignment(Pos.CENTER_RIGHT);
        Label lChampsObligatoire = new Label("* Champs obligatoires");

        lEmailOuMdpIncorrect.setText("Email déjà existant !");
        lEmailOuMdpIncorrect.setStyle("-fx-text-fill : ff0000");
        lEmailOuMdpIncorrect.setVisible(false);

        tNom.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        tPrenom.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        tEmail.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        pMotDePasse.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);

        bCreerMonCompte.setText("CRÉER MON COMPTE");
        bCreerMonCompte.setStyle("-fx-background-color : " + Couleurs.ORANGE_PATISSIER + "; -fx-text-fill: " + Couleurs.BLANC
        + ";-fx-font-weight: bold;");
        bCreerMonCompte.setPrefSize(240, 40);

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
        box.getChildren().add(bCreerMonCompte);
        box.getChildren().add(lChampsObligatoire);
        box.getChildren().add(lEmailOuMdpIncorrect);
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
        return bCreerMonCompte;
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
