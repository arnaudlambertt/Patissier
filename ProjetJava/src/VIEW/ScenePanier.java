/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import CONSTANT.Panes;
import java.util.ArrayList;
import javafx.scene.control.Separator;

/**
 *
 * @author Benjamin
 */
public class ScenePanier extends SceneCustom
{
    private final ArrayList<PaneProduitPanier> paneProduitPanier;
    private final Button bValiderMonPanier =  new Button("VALIDER MON PANIER");
    private double prixTotal;

    public ScenePanier()
    {
        prixTotal=0;
        this.paneProduitPanier = new ArrayList<>();
    }
    
    @Override
    public void init()
    {
        ((BorderPane) getRoot()).setStyle("-fx-background-color: " + Couleurs.BLANC + "; "
                + "-fx-border-color: " + Couleurs.BLANC + ";");
    }

    @Override
    public void update(View v)
    {
        GridPane gridPaneCollectionEtAchat = new GridPane();
        
        
        GridPane collectionGridPane = new GridPane();
        
        collectionGridPane.setVgap(Panes.VGAP_SCENE_PRODUITS);
        collectionGridPane.setAlignment(Pos.CENTER);
        
        for (int i = 0; i < paneProduitPanier.size(); ++i)
        {
            collectionGridPane.add(paneProduitPanier.get(i), 0, 2 * i + 1);
            collectionGridPane.add(new Separator(), 0, 2 * (i + 1));
        }
        
        GridPane achatGridPane = new GridPane();
        achatGridPane.setHgap(20);
        
        Label nbrProduit = new Label();
        if(this.paneProduitPanier.size()>1)
        {
            nbrProduit.setText("Mon Panier (" + this.paneProduitPanier.size() + " produits) ");
        }
        else
        {
            nbrProduit.setText("Mon Panier (" + this.paneProduitPanier.size() + " produit) ");
        }
        achatGridPane.add(nbrProduit, 0, 0);
        
        Label total = new Label("TOTAL  " + prixTotal );
        
        achatGridPane.add(total, 0, 1);
        
        achatGridPane.add(bValiderMonPanier, 0, 3);
        
       
        
        
        gridPaneCollectionEtAchat.add(collectionGridPane, 0, 0);
        gridPaneCollectionEtAchat.add(achatGridPane, 1, 0);
        page.setCenter(gridPaneCollectionEtAchat);
    }
    
    
    public ArrayList<PaneProduitPanier> getPaneProduitPanier()
    {
        return paneProduitPanier;
    }

    public Button getValiderMonPanier()
    {
        return bValiderMonPanier;
    }
    
    public void setPrixTotal(double prixTotal)
    {
        this.prixTotal = prixTotal;
    }
    
    /*public double calculerTotal()
    {
        double total = 0;
        
        for (PaneProduitPanier e : paneProduitPanier)
        {
            System.out.println("Total "+ total);
            System.out.println("Prix " + e.getPrix());
            System.out.println("Value "+e.getCbNombreProduit().getValue());
            total+=(e.getPrix()*e.getCbNombreProduit().getValue());
        }
        return total;
    }*/

    //private final ArrayList<PaneProduit>
    
    /*private Label lMonPanier;
    private Label lTotal;
    private Label lHorsFraisLivraison;
    private Label lRemise;
    private Label lPrix;
    private Label lValeurRemise;

    private Button bValider;

    private Pane pArticles;

    public ScenePanier()
    {
        ((BorderPane) getRoot()).setStyle("-fx-background-color: " + Couleurs.BLANC + "; "
                + "-fx-border-color: " + Couleurs.BLANC + ";");
    }

    @Override
    public void init()
    {
        BorderPane panneau = new BorderPane();
        GridPane grid = new GridPane();
        GridPane gridInterne = new GridPane();
        GridPane gridTotal = new GridPane();
        GridPane gridRemise = new GridPane();

        //initialisation valeurs 
        lMonPanier = new Label("MON PANIER");
        lTotal = new Label("TOTAL");
        lHorsFraisLivraison = new Label("Hors frais de livraison");
        lRemise = new Label("Remise : ");
        lPrix = new Label("Prix");
        lValeurRemise = new Label("Valeur");

        bValider = new Button("VALIDER MON PANIER");

        pArticles = new FlowPane();
        //Grid Remise
        gridRemise.add(lRemise, 0, 0);
        gridRemise.add(lValeurRemise, 1, 0);
        gridRemise.setAlignment(Pos.CENTER);
        //Grid total
        gridTotal.add(lTotal, 0, 0);
        gridTotal.add(lPrix, 1, 0);
        gridTotal.add(lHorsFraisLivraison, 0, 1);

        // GridPane Interne
        gridInterne.add(gridRemise, 1, 0);
        gridInterne.add(gridTotal, 1, 1);
        gridInterne.add(bValider, 1, 2);
        gridInterne.setAlignment(Pos.CENTER);
        gridInterne.setGridLinesVisible(true);

        // GridPane
        grid.add(lMonPanier, 1, 1);
        grid.add(gridInterne, 1, 2);
        grid.add(bValider, 1, 3);
        grid.setVgap(Panes.VGAP_SCENE_PANIER);

        //Panneau principal
        panneau.setRight(grid);
        panneau.setCenter(this.pArticles);

        setRoot(panneau);
    }

    @Override
    public void update(View v)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/




}
