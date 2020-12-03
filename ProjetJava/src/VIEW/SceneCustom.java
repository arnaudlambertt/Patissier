/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
/**
 *
 * @author Benjamin
 */
public abstract class SceneCustom extends Scene
{

    public SceneCustom()
    {
        super(new Region());
    }
    public abstract void init();
    public abstract void update(View v);
}
