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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 *
 * @author mathi
 */
public class SceneProfil extends SceneCustom
{
    private final Button bEnregisterModifs= new Button("ENREGISTER LES MODIFICATIONS");
    private final Button bDeconnectionUtilisateur= new Button("ME DECONNECTER");
    private final Button bSupprimerCompte= new Button("SUPPRIMER MON COMPTE");
    private final Button bMesAchats= new Button("MES COMMANDES");
    private final TextField tNom = new TextField();
    private final TextField tPrenom = new TextField();
    private final TextField tEmail = new TextField();
    private final PasswordField tAncienMotDePasse = new PasswordField();
    private final TextField tNouveauMotDePasse = new TextField();
    private final Label lEmailDejaExistant = new Label("L'adresse email saisie est déjà utilisée dans notre système");
    //mesCommandes

    @Override
    public void init()
    {
       ((BorderPane) getRoot()).setStyle("-fx-background-color: " + Couleurs.BLANC + "; "
                + "-fx-border-color: " + Couleurs.BLANC + ";");
    }

    @Override
    public void update(View v)
    {
        bEnregisterModifs.setStyle("-fx-background-color : "+Couleurs.ORANGE_PATISSIER+"; -fx-text-fill: "+Couleurs.BLANC
        + ";-fx-font-weight: bold;");
        bEnregisterModifs.setMinSize(200, 40);
        
        bDeconnectionUtilisateur.setStyle("-fx-background-color : "+Couleurs.ORANGE_PATISSIER+"; -fx-text-fill: "+Couleurs.BLANC
        + ";-fx-font-weight: bold;");
        bDeconnectionUtilisateur.setMinSize(200, 40);
        
        bSupprimerCompte.setStyle("-fx-background-color : "+Couleurs.ORANGE_PATISSIER+"; -fx-text-fill: "+Couleurs.BLANC
        + ";-fx-font-weight: bold;");
        bSupprimerCompte.setMinSize(200, 40);
        
        bMesAchats.setStyle("-fx-background-color : "+Couleurs.ORANGE_PATISSIER+"; -fx-text-fill: "+Couleurs.BLANC
        + ";-fx-font-weight: bold;");
        bMesAchats.setMinSize(200, 40);
        
        
        
        
        
        GridPane donneeProfil = new GridPane();
        GridPane gNom = new GridPane();
        GridPane gPrenom = new GridPane();
        GridPane gEmail = new GridPane();
        GridPane gMdp = new GridPane();
        
        donneeProfil.setVgap(15);
        donneeProfil.setHgap(15);
        donneeProfil.setAlignment(Pos.TOP_CENTER);
        
        Label lTitre = new Label("Mes informations personelles");
        lTitre.setStyle("-fx-font-weight: bold");
        
        Label lProfil = new Label("MON PROFIL");
        lProfil.setStyle("-fx-font-weight : bold");

        Label lNom = new Label("Nom");
        Label lPrenom = new Label("Prenom");
        Label lEmail = new Label("Email");
        Label lAncienMotDePasse= new Label("Ancien Mot de passe");
        Label lNouveauMotDePasse= new Label("Nouveau Mot de passe");
        
        Region r = new Region();
        r.setMinHeight(100);
        
        donneeProfil.add(r,1,0);
        donneeProfil.add(lTitre, 1, 2);
        gNom.add(lNom, 1, 0);
        gNom.add(tNom, 1, 1);
        gPrenom.add(lPrenom, 1, 0);
        gPrenom.add(tPrenom, 1, 1);
        gEmail.add(lEmail, 1, 0);
        gEmail.add(tEmail, 1, 1);
        gMdp.add(lAncienMotDePasse, 1, 0);
        gMdp.add(tAncienMotDePasse, 1, 1);
        gMdp.add(lNouveauMotDePasse, 1, 2);
        gMdp.add(tNouveauMotDePasse, 1, 3);
        
        donneeProfil.add(gNom, 1, 3);
        donneeProfil.add(gPrenom, 1, 4);
        donneeProfil.add(gEmail, 1, 5);
        donneeProfil.add(gMdp, 1, 6);
        
        lEmailDejaExistant.setVisible(false);
        lEmailDejaExistant.setStyle("-fx-text-fill : "+Couleurs.ROUGE);
        donneeProfil.add(lEmailDejaExistant, 1, 7);
        
        donneeProfil.add(bEnregisterModifs, 1, 9);
        donneeProfil.add(bDeconnectionUtilisateur, 0, 3);
        donneeProfil.add(bSupprimerCompte, 0, 4);
        donneeProfil.add(bMesAchats, 0, 5);
        donneeProfil.add(lProfil, 1, 1);
        
        page.setCenter(donneeProfil);
    }

    public Label getlEmailDejaExistant()
    {
        return lEmailDejaExistant;
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

    public PasswordField gettAncienMotDePasse()
    {
        return tAncienMotDePasse;
    }

    public TextField gettNouveauMotDePasse()
    {
        return tNouveauMotDePasse;
    }

    public Button getbDeconnectionUtilisateur()
    {
        return bDeconnectionUtilisateur;
    }

    public Button getbEnregisterModifs()
    {
        return bEnregisterModifs;
    }

    public Button getbMesAchats()
    {
        return bMesAchats;
    }

    public Button getbSupprimerCompte()
    {
        return bSupprimerCompte;
    }
    
    
        
}
