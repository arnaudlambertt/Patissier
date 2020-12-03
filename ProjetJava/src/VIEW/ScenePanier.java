/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

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

/**
 *
 * @author Benjamin
 */
public class ScenePanier extends SceneCustom
{

    private Label lMonPanier;
    private Label lTotal;
    private Label lHorsFraisLivraison;
    private Label lRemise;
    private Label lPrix;
    private Label lValeurRemise;
    
    private Button bValider;
    
    private Pane pArticles;
    
    public ScenePanier(Parent root)
    {
        super(root);
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
        lPrix=new Label("Prix");
        lValeurRemise = new Label("Valeur");
    
        bValider = new Button("VALIDER MON PANIER");
    
        pArticles = new FlowPane();
        //Grid Remise
        gridRemise.add(lRemise,0,0);
        gridRemise.add(lValeurRemise,1,0);
        gridRemise.setAlignment(Pos.CENTER);
        //Grid total
        gridTotal.add(lTotal,0,0);
        gridTotal.add(lPrix,1,0);
        gridTotal.add(lHorsFraisLivraison,0,1);
        
        // GridPane Interne
        gridInterne.add(gridRemise, 1, 0);
        gridInterne.add(gridTotal, 1, 1);
        gridInterne.add(bValider, 1, 2);
        gridInterne.setAlignment(Pos.CENTER);
        gridInterne.setGridLinesVisible (true);
        
        // GridPane
        grid.add(lMonPanier, 1, 1);
        grid.add(gridInterne, 1, 2);
        grid.add(bValider, 1, 3);
        grid.setVgap(10);
        
        //Panneau principal
        panneau.setRight(grid);
        panneau.setCenter(this.pArticles);
        
        setRoot(panneau);
    }

    @Override
    public void update(View v)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
