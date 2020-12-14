/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

/**
 *
 * @author Benjamin
 */
public abstract class SceneCustom extends Scene
{

    private static final BorderPane navigateur = new BorderPane(); //url nord + scroll centre 
    protected static final BorderPane page = new BorderPane(); //entete nord + contenu centre

    public SceneCustom()
    {
        super(new BorderPane(), Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        ((BorderPane) getRoot()).setStyle("-fx-background-color: " + Couleurs.BLANC + "; "
                + "-fx-border-color: " + Couleurs.BLANC + ";");
    }
    /**
     * Permet de normaliser toutes les Scenes
     * @param v nous donne accès aux méthodes de view
     */
    public static void setup(View v)
    {
        StackPane urlPane = new StackPane();
        ImageView chrome = new ImageView(new Image("http://93.3.238.99/uploads/barre-URL.png"));
        chrome.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
        urlPane.getChildren().add(chrome);
        v.gettUrl().setStyle("-fx-background-color: #202124;"
                + "-fx-text-fill: #FFFFFF;"
        );
        v.gettUrl().setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth() - 140);
        urlPane.getChildren().add(v.gettUrl());
        StackPane.setAlignment(v.gettUrl(), Pos.CENTER);
        navigateur.setTop(urlPane);
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

        StackPane stack = new StackPane();
        stack.getChildren().add(v.getpEntete());
        v.getpAdmin().setVisible(false);
        stack.getChildren().add(v.getpAdmin());

        page.setTop(stack);
    }

    /**
     * initialisation de la Scene
     */
    public abstract void init();

    /**
     * met à jour la Scene en fonction des données de View
     * @param v nous donne accès aux données et methodes de view
     */
    public abstract void update(View v);

    /**
     * Centre la Scene
     */
    public void updateFenetre()
    {
        ((BorderPane) getRoot()).setCenter(navigateur);
    }
}
