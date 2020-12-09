/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import MODEL.Utilisateur;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Benjamin
 */
public class PaneUtilisateurAdmin extends GridPane
{
    private final int index;
    private final Button bModifierUtilisateur;
    private final Button bSupprimerUtilisateur;

    public PaneUtilisateurAdmin(int index, Utilisateur u)
    {
        this.index = index;
        this.bModifierUtilisateur = new Button("MODIFIER PRODUIT");
        this.bSupprimerUtilisateur = new Button("SUPPRIMER PRODUIT");
        
        this.setHgap(20);
        
        // INFO Utilisateur VBOX
        VBox box = new VBox();
        //nom
        Label lNom = new Label("Nom : " + u.getNom());
        lNom.setWrapText(true);
        lNom.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lNom, VPos.TOP);
        box.getChildren().add(lNom);
        //prenom
        Label lPrenom = new Label("Vendeur : " + u.getPrenom());
        lPrenom.setWrapText(true);
        lPrenom.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lPrenom, VPos.TOP);
        box.getChildren().add(lPrenom);
        //email
        Label lEmail = new Label("Prix unitaire : " + u.getEmail());
        lEmail.setWrapText(true);
        lEmail.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lEmail, VPos.TOP);
        box.getChildren().add(lEmail);
        //role
        Label lRole = new Label("Quantité dans un lot : " + u.getRole());
        lRole.setWrapText(true);
        lRole.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lRole, VPos.TOP);
        box.getChildren().add(lRole);
        
        //box.setSpacing(10);
        
        box.setMinWidth(360);
        add(box,0,0);

        // BOUTONS
        GridPane paneBouton = new GridPane();
        paneBouton.setAlignment(Pos.TOP_CENTER);
        add(paneBouton,1,0);
        
        bModifierUtilisateur.setMinSize(240, 40);
        bModifierUtilisateur.setStyle("-fx-background-color: " + Couleurs.ORANGE_PATISSIER + ";"
        + "-fx-text-fill: " + Couleurs.BLANC + ";"
                + "-fx-font-weight: bold;");
        GridPane.setValignment(bModifierUtilisateur, VPos.CENTER);
        paneBouton.add(bModifierUtilisateur,0,2);
        paneBouton.setVgap(20);
        
        bSupprimerUtilisateur.setMinSize(240, 40);
        bSupprimerUtilisateur.setStyle("-fx-background-color: " + Couleurs.ORANGE_PATISSIER + ";"
        + "-fx-text-fill: " + Couleurs.BLANC + ";"
                + "-fx-font-weight: bold;");
        GridPane.setValignment(bSupprimerUtilisateur, VPos.CENTER);
        paneBouton.add(bSupprimerUtilisateur,0,3);
    }

    public int getIndex()
    {
        return index;
    }

    public Button getbModifierProduit()
    {
        return bModifierUtilisateur;
    }

    public Button getbSupprimerProduit()
    {
        return bSupprimerUtilisateur;
    }
    
    
}
