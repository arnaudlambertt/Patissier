/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

/**
 *
 * @author Benjamin
 */
public abstract class SceneCustom extends Scene
{

    private final ScrollPane rootPane;
    protected final VBox conteneurPrincipal;

    public SceneCustom()
    {
        super(new VBox(),Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());
        this.conteneurPrincipal = new VBox(15);
        this.rootPane = new ScrollPane();
        this.rootPane.setFitToWidth(true);
        this.rootPane.setFitToHeight(true);
        this.rootPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.rootPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.rootPane.setContent(conteneurPrincipal);
        this.conteneurPrincipal.setStyle("-fx-background-color: #FFFFFF;"
                + "-fx-border-color: #FFFFFF;");
        this.rootPane.setStyle("-fx-background-color: #FFFFFF;"
                + "-fx-border-color: #FFFFFF;");
        ((VBox) getRoot()).setStyle("-fx-background-color: #FFFFFF;"
                + "-fx-border-color: #FFFFFF;");
        this.conteneurPrincipal.setAlignment(Pos.TOP_CENTER);

        double SPEED = 0.0025;
        this.rootPane.getContent().setOnScroll(scrollEvent ->
        {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            this.rootPane.setVvalue(this.rootPane.getVvalue() - deltaY);
        });
    }

    //methode qui met la barre de recherche
    public abstract void init();

    public void update(View v)
    {
        VBox pageBox = (VBox) getRoot();
        pageBox.getChildren().clear();
        //add URL
        pageBox.getChildren().add(rootPane);
        conteneurPrincipal.getChildren().clear();
        conteneurPrincipal.getChildren().add(v.getpEntete());
    }
}
