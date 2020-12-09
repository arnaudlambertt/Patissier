/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import MODEL.Commande;
import MODEL.Produit;
import java.util.ArrayList;
import java.util.Map;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author mathi
 */
public final class PaneCommande extends GridPane
{
    private final int index;
    private final ArrayList<GridPane> paneProduit = new ArrayList<>();
    
    public PaneCommande(int index, Commande c)
    {
        this.index = index;
        setHgap(30);
        
        GridPane produitDeCommande = new GridPane();
        
        
        Label lHorodateur = new Label("Date et heure : "+ c.getHorodateur().toString());
        Label lAdresse = new Label("Adresse : "+ c.getAdresse());
        Label lPrix = new Label("Prix : "+c.getPrix()+"€");
        Label lIsLivre = new Label();
        Label lNumCommande = new Label("Commande numero "+(index+1));
        
        if(c.isLivre())
            lIsLivre.setText("La commande a été livrée");
        else
            lIsLivre.setText("La Commande est en cours de livraison");
        

        add(lNumCommande, 0, 0);
        add(lHorodateur, 0, 1);
        add(lAdresse, 0, 2);
        add(lPrix, 0, 3);
        add(lIsLivre, 0, 4);
        add(new Label("Vous avez commandé les produits suivants : "), 0, 5);
        
        for (Map.Entry<Produit, Integer> entry : c.getProduitsCommande().entrySet())
        {
            ajoutProduitPane(entry.getValue(),entry.getKey() );
        }
        
        for(int i=0; i<paneProduit.size(); ++i)
        {
            add(paneProduit.get(i), 0, i + 6);
        }
    }
    
    public void ajoutProduitPane(int nbrProduit, Produit p)
    {
        GridPane gridProduit = new GridPane();
        
        gridProduit.setHgap(20);
        
        ImageView image = new ImageView(new Image("http://93.3.238.99/uploads/" + p.getLienImage()));
        image.setPreserveRatio(true);
        image.setFitWidth(150);
        
        GridPane.setValignment(image, VPos.TOP);
        gridProduit.add(image,0,0);
        
        Label lNom = new Label(p.getNom() + "\n" + p.getFournisseur());
        lNom.setMinSize(150, 150);
        lNom.setWrapText(true);
        lNom.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(lNom, VPos.TOP);
        gridProduit.add(lNom,1,0);

        GridPane panePrixBouton = new GridPane();
        panePrixBouton.setAlignment(Pos.TOP_CENTER);
        gridProduit.add(panePrixBouton,2,0);
        
        Label prix = new Label(String.valueOf(p.getPrixUnitaire()) + "€");
        prix.setAlignment(Pos.TOP_CENTER);
        prix.setMinHeight(25);
        GridPane.setHalignment(prix, HPos.RIGHT);
        GridPane.setValignment(prix, VPos.CENTER);
        panePrixBouton.add(prix,0,0);
        
        Label nombre = new Label(" x "+nbrProduit);
        
        paneProduit.add(gridProduit);
        
    }
    
}
