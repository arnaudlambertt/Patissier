/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import CONSTANT.Panes;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author Benjamin
 */
public class ScenePanier extends SceneCustom
{

    private final Button bValiderMonPanier;
    private final ImageView progressionPanier;
    private final ArrayList<PaneProduitPanier> paneProduitPanier;
    private double prixTotal;

    public ScenePanier()
    {
        this.bValiderMonPanier = new Button();
        this.progressionPanier = new ImageView();
        this.paneProduitPanier = new ArrayList<>();
        prixTotal = 0.0;
    }

    @Override
    public void init()
    {
        bValiderMonPanier.setText("VALIDER MON PANIER");
        bValiderMonPanier.setStyle(
                "-fx-background-color : " + Couleurs.ORANGE_PATISSIER + ";"
                + "-fx-text-fill: " + Couleurs.BLANC + ";"
                + "-fx-font-weight: bold;"
        );
        bValiderMonPanier.setMinSize(240, 40);
        progressionPanier.setImage(new Image("http://93.3.238.99/uploads/Panier-1.png"));
    }

    @Override
    public void update(View v)
    {
        VBox box = new VBox();
        box.setAlignment(Pos.TOP_CENTER);
                
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

        if (paneProduitPanier.size() > 1)
            nbrProduit.setText("Mon Panier (" + this.paneProduitPanier.size() + " produits) ");
        else
            nbrProduit.setText("Mon Panier (" + this.paneProduitPanier.size() + " produit) ");

        achatGridPane.add(nbrProduit, 0, 0);

        Label total = new Label("TOTAL  " + prixTotal + "€");

        achatGridPane.add(total, 0, 1);

        achatGridPane.add(bValiderMonPanier, 0, 3);
        gridPaneCollectionEtAchat.setAlignment(Pos.TOP_RIGHT);
        gridPaneCollectionEtAchat.setHgap(75);
        gridPaneCollectionEtAchat.add(collectionGridPane, 0, 1);
        gridPaneCollectionEtAchat.add(achatGridPane, 1, 1);
        Region r = new Region();
        r.setMinWidth(225);
        gridPaneCollectionEtAchat.add(r, 2, 0);
        box.getChildren().add(progressionPanier);
        box.getChildren().add(gridPaneCollectionEtAchat);
        page.setCenter(box);
    }

    public ArrayList<PaneProduitPanier> getPaneProduitPanier()
    {
        return paneProduitPanier;
    }

    public Button gebtValiderPanier()
    {
        return bValiderMonPanier;
    }

    public void setPrixTotal(double prixTotal)
    {
        this.prixTotal = prixTotal;
    }
    
}
