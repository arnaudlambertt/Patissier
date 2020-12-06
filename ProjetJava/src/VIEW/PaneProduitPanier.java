/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import MODEL.Produit;
import static java.lang.Math.min;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author mathi
 */
public final class PaneProduitPanier extends GridPane
{
    private final int index;
    private final Button bSupprimer;
    private final ComboBox<Integer> cbNombreProduit;
    
    public PaneProduitPanier(int index, Produit p, int quantite)
    {
        
        
        this.index = index;
        this.bSupprimer = new Button("Supprimer");
        
        this.cbNombreProduit = new ComboBox<>();
        this.cbNombreProduit.setValue(quantite);
        for (int i = 1; i < min(p.getStock()+1,11); i++)
        {
           this.cbNombreProduit.getItems().add(i);
        }
        add(cbNombreProduit, 4, 0);
        
        setHgap(20);
        ImageView image = new ImageView(new Image("http://93.3.238.99/uploads/" + p.getLienImage()));
        image.setPreserveRatio(true);
        image.setFitWidth(200);
        GridPane.setValignment(image, VPos.TOP);
        add(image,0,0);
        
        Label lNom = new Label(p.getNom() + "\n" + p.getNomFournisseur());
        lNom.setPrefSize(200, 200);
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
        panePrixBouton.add(prix, 0, 0);
        
        bSupprimer.setStyle("-fx-background-color: " + Couleurs.BLANC + ";"
        + "-fx-text-fill: " + Couleurs.GRIS_FONCE + ";"
        + "-fx-font-weight: bold;"
        + "-fx-underline: true;");
        GridPane.setValignment(bSupprimer, VPos.CENTER);
        add(bSupprimer,5,0);
        
    }
    
    public int getIndex()
    {
        return index;
    }

    public Button getbSupprimer()
    {
        return bSupprimer;
    }

    public ComboBox<Integer> getCbNombreProduit()
    {
        return cbNombreProduit;
    }
}
