/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;
import javafx.scene.Parent;
import javafx.scene.Scene;
/**
 *
 * @author Benjamin
 */
public abstract class SceneCustom extends Scene
{

    public SceneCustom(Parent root)
    {
        super(root);
    }
    public abstract void init();
    public abstract void update(View v);
}
