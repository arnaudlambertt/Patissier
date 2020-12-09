/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import MODEL.Produit;
import java.text.DecimalFormat;
import java.util.Collections;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 *
 * @author Arnaud
 */
public final class PaneProduit extends GridPane
{

    private final int index;
    private final Button bAjouterPanier;

    public PaneProduit(int index, Produit p)
    {
        this.index = index;
        this.bAjouterPanier = new Button("AJOUTER AU PANIER");
        setHgap(20);
        setVgap(10);
        StackPane imageStockPane = new StackPane();
        imageStockPane.setAlignment(Pos.TOP_LEFT);
        add(imageStockPane, 0, 0);

        ImageView image = new ImageView(new Image("http://93.3.238.99/uploads/" + p.getLienImage()));
        image.setPreserveRatio(true);
        image.setFitWidth(200);
        
        imageStockPane.getChildren().add(image);

        ImageView imageStock;
        
        if(p.getStock() > 0)
            imageStock = new ImageView(new Image("http://93.3.238.99/uploads/en-stock.png"));
        else
            imageStock = new ImageView(new Image("http://93.3.238.99/uploads/rupture-de-stock.png"));
        
        imageStock.setPreserveRatio(true);
        imageStock.setFitWidth(80);
        imageStockPane.getChildren().add(imageStock);
        
        DecimalFormat df2 = new DecimalFormat("#.00");

        Label llot;
        if (!p.isPromotionActive())
            llot = new Label(p.getQuantiteUnLot() + " pour " + df2.format(Math.round(p.getPrixUnLot() * 100) / 100.0) + "€ !");
        else
            llot = new Label(p.getQuantiteUnLot() + " pour " + df2.format(Math.round(p.getPrixUnLot() *(1.0 - p.getPromotion())* 100) / 100.0) + "€ !");
        llot.setStyle("-fx-border-color: " + Couleurs.ORANGE_PATISSIER
                    + ";-fx-border-width: 1px"
                    + ";-fx-font-weight: bold"
                    + ";-fx-font-family: Arial"
                    + ";-fx-font-size: 16"       
                    + ";-fx-text-fill: " + Couleurs.ORANGE_PATISSIER        
                    + ";-fx-border-radius : 3%"
                    + ";-fx-padding: 3px"
        );
        add(llot, 0, 1);
        GridPane.setHalignment(llot, HPos.CENTER);
        
        Label lNom = new Label( p.getFournisseur() + " " + p.getNom());
        lNom.setStyle("-fx-text-fill: " + Couleurs.GRIS_FONCE
                + ";-fx-font-family: Arial; -fx-font-size: 24"
        );
        lNom.setMinWidth(300);
        lNom.setMaxWidth(300);
        lNom.setWrapText(true);
        lNom.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lNom, VPos.TOP);
        add(lNom, 1, 0);
        
        GridPane panePrixBouton = new GridPane();
        panePrixBouton.setVgap(10);
        add(panePrixBouton, 2, 0);
        GridPane.setHalignment(panePrixBouton, HPos.RIGHT);
        int decalage = 0;
        Label vraiPrix;
        if (!p.isPromotionActive())
            vraiPrix = new Label(df2.format(Math.round(p.getPrixUnitaire() * 100) / 100.0) + "€");
        else
        {
            decalage = 1;
            Label ancienPrix = new Label(df2.format(Math.round(p.getPrixUnitaire() * 100) / 100.0) + "€");
            ancienPrix.setStyle("-fx-font-family: Arial; -fx-font-size: 14; -fx-text-fill: " + Couleurs.GRIS_CLAIR);
            Label lbarre = new Label(String.join("", Collections.nCopies((int) (ancienPrix.getText().length() * 1.75 + 2), "-")));
            lbarre.setStyle("-fx-font-weight: bold; -fx-font-family: Arial; -fx-font-size: 10; -fx-text-fill: " + Couleurs.GRIS_CLAIR);
            StackPane barre = new StackPane();
            HBox promo = new HBox(10);
            promo.getChildren().add(barre);
            barre.getChildren().add(ancienPrix);
            barre.getChildren().add(lbarre);
            panePrixBouton.add(promo, 0, 0);
            GridPane.setHalignment(barre, HPos.RIGHT);
            GridPane.setValignment(barre, VPos.CENTER);
            vraiPrix = new Label(df2.format(Math.round((p.getPrixUnitaire() * (1.0 - p.getPromotion())) * 100) / 100.0) + "€");

            Label lpourcentage = new Label("- " + String.valueOf(Math.round(p.getPromotion() * 100)) + "%");
            lpourcentage.setStyle("-fx-border-color: " + Couleurs.ORANGE_PATISSIER
                    + ";-fx-border-width: 1px"
                    + ";-fx-font-weight: bold"
                    + ";-fx-font-family: Arial"
                    + ";-fx-font-size: 14"       
                    + ";-fx-text-fill: " + Couleurs.ORANGE_PATISSIER        
                    + ";-fx-border-radius : 7%"
                    + ";-fx-padding: 3px"
            );
            promo.getChildren().add(lpourcentage);
            promo.setAlignment(Pos.TOP_RIGHT);
        }
        vraiPrix.setStyle("-fx-text-fill: " + Couleurs.GRIS_FONCE
                + ";-fx-font-family: Arial; ;-fx-font-weight: bold; -fx-font-size: 28"
        );
        vraiPrix.setAlignment(Pos.TOP_CENTER);
        panePrixBouton.add(vraiPrix, 0, 0 + decalage);

        GridPane.setHalignment(vraiPrix, HPos.RIGHT);
        GridPane.setValignment(vraiPrix, VPos.CENTER);

        bAjouterPanier.setMinSize(240, 40);
        bAjouterPanier.setStyle("-fx-background-color: " + Couleurs.ORANGE_PATISSIER + ";"
                + "-fx-text-fill: " + Couleurs.BLANC + ";"
                + "-fx-font-weight: bold;");
        GridPane.setValignment(bAjouterPanier, VPos.CENTER);
        panePrixBouton.add(bAjouterPanier, 0, 1 + decalage);
    }

    public int getIndex()
    {
        return index;
    }

    public Button getbAjouterPanier()
    {
        return bAjouterPanier;
    }
}
