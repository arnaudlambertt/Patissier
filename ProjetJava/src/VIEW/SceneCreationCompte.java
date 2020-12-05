/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import CONSTANT.PaneC;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private final BorderPane panneau;

    public SceneCreationCompte()
    {
        this.panneau = new BorderPane();
    }
    

    @Override
    public void init()
    {
        VBox box = new VBox();
        FlowPane FlowPaneNom = new FlowPane();
        FlowPane FlowPanePrenom = new FlowPane();
        FlowPane FlowPaneEmail = new FlowPane();
        FlowPane FlowPaneMotDePasse = new FlowPane();

        lMesInformationsDeContact = new Label("Mes informations de contact : ");
        lNom = new Label("Nom* : ");
        lNom.setMinWidth(PaneC.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lNom.setAlignment(Pos.CENTER_RIGHT);
        lPrenom = new Label("Prenom* : ");
        lPrenom.setMinWidth(PaneC.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lPrenom.setAlignment(Pos.CENTER_RIGHT);
        lEmail = new Label("Email* : ");
        lEmail.setMinWidth(PaneC.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lEmail.setAlignment(Pos.CENTER_RIGHT);
        lMotDePasse = new Label("Mot de passe* : ");
        lMotDePasse.setMinWidth(PaneC.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lMotDePasse.setAlignment(Pos.CENTER_RIGHT);
        lChampsObligatoire = new Label("* Champs obligatoires");

        lEmailOuMdpIncorrect = new Label("Email ou mot de passe incorrect !");
        lEmailOuMdpIncorrect.setStyle("-fx-text-fill : ff0000");
        lEmailOuMdpIncorrect.setVisible(false);

        tNom = new TextField();
        tNom.setMaxWidth(PaneC.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        tPrenom = new TextField();
        tPrenom.setMaxWidth(PaneC.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        tEmail = new TextField();
        tEmail.setMaxWidth(PaneC.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        pMotDePasse = new PasswordField();
        pMotDePasse.setMaxWidth(PaneC.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);

        bCreeMonCompte = new Button("Cree Mon Compte");
        bCreeMonCompte.setStyle("-fx-background-color : "+Couleurs.ORANGE_BOULANGER+"; -fx-text-fill: "+Couleurs.BLANC);

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
        box.getChildren().add(lMesInformationsDeContact);
        box.getChildren().add(FlowPaneNom);
        box.getChildren().add(FlowPanePrenom);
        box.getChildren().add(FlowPaneEmail);
        box.getChildren().add(FlowPaneMotDePasse);
        box.getChildren().add(bCreeMonCompte);
        box.getChildren().add(lChampsObligatoire);
        box.getChildren().add(lEmailOuMdpIncorrect);
        box.setAlignment(Pos.CENTER);

        panneau.setCenter(box);
    }

    @Override
    public void update(View v)
    {
        super.update(v);
        conteneurPrincipal.getChildren().add(panneau);
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
        this.tNom.clear();
        this.pMotDePasse.clear();
    }


}
//nom/prenom/email/mdp
