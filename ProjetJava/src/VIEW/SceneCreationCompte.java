/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.COULEUR;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 *
 * @author Benjamin
 */
public class SceneCreationCompte extends javafx.scene.Scene implements Scene
{
    private Label lMesInformationsDeContact;
    private Label lNom;
    private Label lPrenom;
    private Label lEmail;
    private Label lMotDePasse;
    private Label lChampsObligatoire;
    
    private TextField tNom;
    private TextField tPrenom;
    private TextField tEmail;
    private PasswordField pMotDePasse;
    
    private Button bCreeMonCompte;

    public SceneCreationCompte(Parent root)
    {
        super(root);
    }

    @Override
    public void init()
    {
        BorderPane panneau = new BorderPane();
        VBox box = new VBox();
        FlowPane FlowPaneNom = new FlowPane();
        FlowPane FlowPanePrenom = new FlowPane();
        FlowPane FlowPaneEmail = new FlowPane();
        FlowPane FlowPaneMotDePasse = new FlowPane();
        
        lMesInformationsDeContact = new Label("Mes informations de contact : ");
        lNom = new Label("Nom* : ");
        lNom.setMinWidth(90);
        lNom.setAlignment(Pos.CENTER_RIGHT);
        lPrenom = new Label("Prenom* : ");
        lPrenom.setMinWidth(90);
        lPrenom.setAlignment(Pos.CENTER_RIGHT);
        lEmail = new Label("Email* : ");
        lEmail.setMinWidth(90);
        lEmail.setAlignment(Pos.CENTER_RIGHT);
        lMotDePasse = new Label("Mot de passe* : ");
        lMotDePasse.setMinWidth(90);
        lMotDePasse.setAlignment(Pos.CENTER_RIGHT);
        lChampsObligatoire = new Label("* Champs obligatoires");

        tNom = new TextField();
        tNom.setMaxWidth(300);
        tPrenom = new TextField();
        tPrenom.setMaxWidth(300);
        tEmail = new TextField();
        tEmail.setMaxWidth(300);
        pMotDePasse = new PasswordField();
        pMotDePasse.setMaxWidth(300);
        
        bCreeMonCompte = new Button("Cree Mon Compte");
        bCreeMonCompte.setStyle("-fx-background-color : "+COULEUR.orangeBoulanger+"; -fx-text-fill: "+COULEUR.BLANC);
        
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
        box.setAlignment(Pos.CENTER);
        
        
        panneau.setCenter(box);
        
        
        setRoot(panneau);
    }

    @Override
    public void update()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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