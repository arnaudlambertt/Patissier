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
public class SceneAccueil extends javafx.scene.Scene implements Scene
{
    private FlowPane PaneConnection;
    private Button buttonClose;
    
    
    public SceneAccueil(Parent p)
    {
        super(p);
        
        
        
        
        //root = new VBox();
        //root.setBackground(new Background(myBI));
        //root.getChildren().add(buttonClose);
        //root.getChildren().add(PaneConnection);
        //root.setAlignment(Pos.CENTER);
        
        
         
    }
    
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
    }
    
    
    public void add(Node n)
    {
        ((Pane) getRoot()).getChildren().add(n);
        
    }

    @Override
    public void update()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
