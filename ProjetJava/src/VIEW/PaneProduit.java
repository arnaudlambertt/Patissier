/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import MODEL.Produit;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
        ImageView image = new ImageView(new Image("http://93.3.238.99/uploads/" + p.getLienImage()));
        image.setPreserveRatio(true);
        image.setFitWidth(300);
        GridPane.setValignment(image, VPos.TOP);
        add(image,0,0);
        
        Label lNom = new Label(p.getNom() + "\n" + p.getNomFournisseur());
        lNom.setPrefSize(300, 300);
        lNom.setWrapText(true);
        lNom.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lNom, VPos.TOP);
        add(lNom,1,0);

        GridPane panePrixBouton = new GridPane();
        panePrixBouton.setAlignment(Pos.TOP_CENTER);
        add(panePrixBouton,2,0);
        
        Label prix = new Label(String.valueOf(p.getPrixUnitaire()) + "€");
        prix.setAlignment(Pos.TOP_CENTER);
        prix.setPrefHeight(25);
        GridPane.setHalignment(prix, HPos.RIGHT);
        GridPane.setValignment(prix, VPos.CENTER);
        panePrixBouton.add(prix,0,0);
        
        bAjouterPanier.setPrefSize(240, 40);
        bAjouterPanier.setStyle("-fx-background-color: " + Couleurs.ORANGE_PATISSIER + ";"
        + "-fx-text-fill: " + Couleurs.BLANC + ";");
        GridPane.setValignment(bAjouterPanier, VPos.CENTER);
        panePrixBouton.add(bAjouterPanier,0,1);
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
