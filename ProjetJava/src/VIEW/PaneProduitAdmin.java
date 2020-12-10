/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import MODEL.Produit;
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
 * @author Arnaud
 */
public final class PaneProduitAdmin extends GridPane
{
    private final int index;
    private final Button bModifierProduit;
    private final Button bSupprimerProduit;

    public PaneProduitAdmin(int index, Produit p)
    {
        this.index = index;
        this.bModifierProduit = new Button("MODIFIER PRODUIT");
        this.bSupprimerProduit = new Button("SUPPRIMER PRODUIT");
        
        this.setHgap(20);
        
        // IMAGE
        ImageView image = new ImageView(new Image("http://93.3.238.99/uploads/" + p.getLienImage()));
        image.setPreserveRatio(true);
        image.setFitWidth(200);
        GridPane.setValignment(image, VPos.TOP);
        add(image,0,0);
        
        // INFO PRODUIT VBOX
        VBox box = new VBox();
        //nom
        Label lNom = new Label("Nom : " + p.getNom());
        lNom.setWrapText(true);
        lNom.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lNom, VPos.TOP);
        box.getChildren().add(lNom);
        //categorie
        Label lCategorie = new Label("Catégorie : " + p.getCategorie());
        lCategorie.setWrapText(true);
        lCategorie.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lCategorie, VPos.TOP);
        box.getChildren().add(lCategorie);
        //fournisseur
        Label lFournisseur = new Label("Vendeur : " + p.getFournisseur());
        lFournisseur.setWrapText(true);
        lFournisseur.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lFournisseur, VPos.TOP);
        box.getChildren().add(lFournisseur);
        //prix unitaire
        Label lPrixUnitaire = new Label("Prix unitaire : " + p.getPrixUnitaire());
        lPrixUnitaire.setWrapText(true);
        lPrixUnitaire.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lPrixUnitaire, VPos.TOP);
        box.getChildren().add(lPrixUnitaire);
        //stock
        Label lStock = new Label("Stock : " + p.getStock());
        lStock.setWrapText(true);
        lStock.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lStock, VPos.TOP);
        box.getChildren().add(lStock);
        //quantite un lot
        Label lQuantiteUnLot = new Label("Quantité dans un lot : " + p.getQuantiteUnLot());
        lQuantiteUnLot.setWrapText(true);
        lQuantiteUnLot.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lQuantiteUnLot, VPos.TOP);
        box.getChildren().add(lQuantiteUnLot);
        //prix un lot
        Label lPrixUnLot = new Label("Prix un lot : " + p.getPrixUnLot());
        lPrixUnLot.setWrapText(true);
        lPrixUnLot.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lPrixUnLot, VPos.TOP);
        box.getChildren().add(lPrixUnLot);
        //promotion
        Label lPromotion = new Label("Promotion : " + p.getPromotion());
        lPromotion.setWrapText(true);
        lPromotion.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lPromotion, VPos.TOP);
        box.getChildren().add(lPromotion);
        //promotion active
        Label lPromotionActive = new Label("Promotion en cours : " + p.isPromotionActive());
        lPromotionActive.setWrapText(true);
        lPromotionActive.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lPromotionActive, VPos.TOP);
        box.getChildren().add(lPromotionActive);
        //lien image
        Label lImage = new Label("Image : " + p.getLienImage());
        lImage.setWrapText(true);
        lImage.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lImage, VPos.TOP);
        box.getChildren().add(lImage);
        
        //box.setSpacing(10);
        
        box.setMinWidth(360);
        add(box,1,0);

        // BOUTONS
        GridPane paneBouton = new GridPane();
        paneBouton.setAlignment(Pos.TOP_CENTER);
        add(paneBouton,2,0);
        
        bModifierProduit.setMinSize(240, 40);
        bModifierProduit.setStyle("-fx-background-color: " + Couleurs.ORANGE_PATISSIER + ";"
        + "-fx-text-fill: " + Couleurs.BLANC + ";"
                + "-fx-font-weight: bold;");
        GridPane.setValignment(bModifierProduit, VPos.CENTER);
        paneBouton.add(bModifierProduit,0,2);
        paneBouton.setVgap(20);
        
        bSupprimerProduit.setMinSize(240, 40);
        bSupprimerProduit.setStyle("-fx-background-color: " + Couleurs.ORANGE_PATISSIER + ";"
        + "-fx-text-fill: " + Couleurs.BLANC + ";"
                + "-fx-font-weight: bold;");
        GridPane.setValignment(bSupprimerProduit, VPos.CENTER);
        paneBouton.add(bSupprimerProduit,0,3);
    }

    public int getIndex()
    {
        return index;
    }

    public Button getbModifierProduit()
    {
        return bModifierProduit;
    }

    public Button getbSupprimerProduit()
    {
        return bSupprimerProduit;
    }
    
    
}
