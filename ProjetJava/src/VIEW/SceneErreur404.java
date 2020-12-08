/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import javafx.scene.control.Label;

/**
 *
 * @author mathi
 */
public class SceneErreur404 extends SceneCustom
{

    @Override
    public void init()
    {
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
