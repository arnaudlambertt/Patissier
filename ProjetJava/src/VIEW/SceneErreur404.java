/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author mathi
 */
public class SceneErreur404 extends SceneCustom
{

    public SceneErreur404()
    {

    }

    @Override
    public void init()
    {
        ((BorderPane) getRoot()).setStyle("-fx-background-color: " + Couleurs.BLANC + "; "
                + "-fx-border-color: " + Couleurs.BLANC + ";");
    }

    @Override
    public void update(View v)
    {
        Label lMessageErreur404 = new Label();
        lMessageErreur404.setText("Error 404 : Page Not Found");
        lMessageErreur404.setScaleX(5);
        lMessageErreur404.setScaleY(5);
        page.setCenter(lMessageErreur404);
    }

}
