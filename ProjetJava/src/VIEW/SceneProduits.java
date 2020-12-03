/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import java.util.ArrayList;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

    private ArrayList<PaneProduit> paneProduits = new ArrayList<>();
    private StackPane collectionPane = new StackPane();
    private GridPane collectionGridPane = new GridPane();

    //menu trie
    //bouton revenir en haut
    public SceneProduits()
    {

        // menu trie = new menu
        //root = new VBox();
        //root.setBackground(new Background(myBI));
        //root.getChildren().add(buttonClose);
        //root.getChildren().add(PaneConnection);
        //root.setAlignment(Pos.CENTER);
    }

    @Override
    public void init()
    {
        ScrollPane pageScrollPane = new ScrollPane();
        pageScrollPane.setContent(collectionPane);
        pageScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        pageScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        pageScrollPane.setFitToWidth(true);
        setRoot(pageScrollPane);

        ////////////////////////////////////////
        /*VBox pane = new VBox();
        
        BackgroundImage myBI= new BackgroundImage(new javafx.scene.image.Image("http://93.3.238.99/index.jpg"),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
        
        buttonClose=new Button();
        
        pane.setBackground(new Background(myBI));
        pane.getChildren().add(buttonClose);
        //pane.getChildren().add(PaneConnection);
        pane.setAlignment(Pos.CENTER);
        
        setRoot(pane);*/
        /////////////////////////////////////////:

        //GridPane 
        // Création des separators
        //scrollPane (pour accueilir les pane de produits)


    }

    public void add(Node n)
    {
        ((Pane) getRoot()).getChildren().add(n);
    }

    @Override
    public void update(View v)
    {
        collectionGridPane = new GridPane();
        collectionPane.getChildren().add(collectionGridPane);
        for (int i = 0; i < paneProduits.size() * 2; i += 2)
        {
            Separator newSeparator = new Separator();
            newSeparator.setMinWidth(300);
            collectionGridPane.add(paneProduits.get(i / 2), 0, i);
            collectionGridPane.add(newSeparator, 0, i + 1);
        }

    }

    public ArrayList<PaneProduit> getPaneProduits()
    {
        return paneProduits;
    }
}
