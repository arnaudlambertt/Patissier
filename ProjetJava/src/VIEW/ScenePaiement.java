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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Arnaud
 */
public class ScenePaiement extends SceneCustom
{

    private final VBox box;
    private final Label ltotal;
    private final Button bConfirmer;
    private double prixTotal;

    public ScenePaiement()
    {
        this.box = new VBox();
        this.ltotal = new Label();
        this.bConfirmer = new Button();
        this.prixTotal = 0.0;
    }

    @Override
    public void init()
    {
        ImageView progressionPanier = new ImageView(new Image("http://93.3.238.99/uploads/Panier-4.png"));
        
        ToggleGroup group = new ToggleGroup();

        RadioButton[] rbPaiement = new RadioButton[3];
        rbPaiement[0] = new RadioButton("Visa");
        rbPaiement[0].setToggleGroup(group);
        rbPaiement[0].setSelected(true);

        rbPaiement[1] = new RadioButton("MasterCard");
        rbPaiement[1].setToggleGroup(group);

        rbPaiement[2] = new RadioButton("Paypal");
        rbPaiement[2].setToggleGroup(group);

        bConfirmer.setText("CONFIRMER");
        bConfirmer.setStyle("-fx-background-color : " + Couleurs.ORANGE_PATISSIER + "; -fx-text-fill: " + Couleurs.BLANC
                + ";-fx-font-weight: bold;");
        bConfirmer.setMinSize(240, 40);
        
        GridPane grid = new GridPane();
        grid.setHgap(300);
        grid.add(rbPaiement[0], 0, 0);
        grid.add(rbPaiement[1], 0, 1);
        grid.add(rbPaiement[2], 0, 2);
        grid.add(ltotal,1,0);
        grid.add(bConfirmer, 1, 1);
        
        box.setAlignment(Pos.TOP_CENTER);
        grid.setAlignment(Pos.CENTER);
        box.getChildren().add(progressionPanier);
        box.getChildren().add(grid);
    }

    @Override
    public void update(View v)
    {
        ltotal.setText("TOTAL  " + prixTotal + "€");
        page.setCenter(box);
    }

    public Button getbConfirmer()
    {
        return bConfirmer;
    }
    
    public void setPrixTotal(double prixTotal)
    {
        this.prixTotal = prixTotal;
    }

}
