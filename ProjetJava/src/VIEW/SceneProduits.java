/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    
    //menu trie
    //bouton revenir en haut
    
    public SceneProduits(Parent p)
    {
        super(p);
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
        VBox pane = new VBox();
        
        BackgroundImage myBI= new BackgroundImage(new javafx.scene.image.Image("http://93.3.238.99/index.jpg"),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
        
        buttonClose=new Button();
        
        pane.setBackground(new Background(myBI));
        pane.getChildren().add(buttonClose);
        //pane.getChildren().add(PaneConnection);
        pane.setAlignment(Pos.CENTER);
        
        setRoot(pane);
        
        
        //scrollPane (pour accueilir les pane de produits)
    }
    
    
    public void add(Node n)
    {
        ((Pane) getRoot()).getChildren().add(n);
    }

    @Override
    public void update(View v)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
