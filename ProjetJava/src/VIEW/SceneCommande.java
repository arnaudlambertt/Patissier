/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author mathi
 */
public class SceneCommande extends SceneCustom
{
    private final ArrayList<PaneCommande> paneProduit;
    
    public SceneCommande()
    {
        this.paneProduit = new ArrayList<>();
    }
    
    @Override
    public void init()
    {}

    @Override
    public void update(View v)
    {
        VBox box = new VBox();
        box.setAlignment(Pos.TOP_CENTER);
        GridPane gridPaneCollection = new GridPane();
        gridPaneCollection.setAlignment(Pos.TOP_CENTER);
        Label titre = new Label("Liste de vos commandes :");
        titre.setStyle("-fx-font-weight: bold");
        for (int i = 0; i < paneProduit.size(); ++i)
        {
            gridPaneCollection.add(paneProduit.get(i), 0, 2 * i + 1);
            gridPaneCollection.add(new Separator(), 0, 2 * (i + 1));
        }
        box.getChildren().add(titre);
        box.getChildren().add(gridPaneCollection);
        page.setCenter(box);
    }

    public ArrayList<PaneCommande> getPaneProduit()
    {
        return paneProduit;
    }
    
    
}
