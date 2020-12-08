/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

/**
 *
 * @author Benjamin
 */
public abstract class SceneCustom extends Scene
{

    protected static final BorderPane navigateur = new BorderPane(); //url nord + scroll centre 
    protected static final BorderPane page = new BorderPane(); //entete nord + contenu centre

    public SceneCustom()
    {
        super(new BorderPane(), Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        ((BorderPane) getRoot()).setStyle("-fx-background-color: " + Couleurs.BLANC + "; "
                + "-fx-border-color: " + Couleurs.BLANC + ";");
    }

    public static void setup(View v)
    {
        //Top URL       

        ScrollPane scrollPrincipal = new ScrollPane();
        navigateur.setCenter(scrollPrincipal);
        scrollPrincipal.setFitToWidth(true);
        scrollPrincipal.setFitToHeight(true);
        scrollPrincipal.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPrincipal.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPrincipal.setContent(page);
        page.setStyle("-fx-background-color: #FFFFFF;"
                + "-fx-border-color: #FFFFFF;");
        scrollPrincipal.setStyle("-fx-background-color: #FFFFFF;"
                + "-fx-border-color: #FFFFFF;");

        double SPEED = 0.0025;
        scrollPrincipal.getContent().setOnScroll(scrollEvent ->
        {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            scrollPrincipal.setVvalue(scrollPrincipal.getVvalue() - deltaY);
        });
        page.setTop(v.getpEntete());
    }

    //methode qui met la barre de recherche
    public abstract void init();

    public abstract void update(View v);

    public void updateFenetre(View v)
    {
        ((BorderPane) getRoot()).setCenter(navigateur);
    }
}
