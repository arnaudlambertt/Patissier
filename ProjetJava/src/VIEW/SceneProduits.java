/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import java.util.ArrayList;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Benjamin
 */
public class SceneProduits extends SceneCustom
{

    ///////////////////////////
    private FlowPane PaneConnection;
    private Button buttonClose;
    //////////////////////////
    //label accueil > rercherche

    private final ArrayList<PaneProduit> paneProduits;

    //menu trie
    //bouton revenir en haut
    public SceneProduits()
    {
        this.paneProduits = new ArrayList<>();
    }

    @Override
    public void init()
    {
       
    }

    @Override
    public void update(View v)
    {
        super.update(v);
        conteneurPrincipal.getChildren().clear(); //clear page
        conteneurPrincipal.getChildren().add(v.getpEntete());
        //ajouter label accueil > recherche / accueil > catégorie...

        GridPane collectionGridPane = new GridPane();
        collectionGridPane.setVgap(25);
        collectionGridPane.setAlignment(Pos.CENTER);
        conteneurPrincipal.getChildren().add(collectionGridPane);
        for (int i = 0; i < paneProduits.size(); ++i)
        {
            collectionGridPane.add(paneProduits.get(i), 0, 2 * i + 1);
            collectionGridPane.add(new Separator(), 0, 2 * (i + 1));
        }
    }

    public ArrayList<PaneProduit> getPaneProduits()
    {
        return paneProduits;
    }
}
