/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import MODEL.*;
import DAO.*;
import CONTROLLER.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author Benjamin
 */
public class ProjetJava extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Controller controller=new Controller(primaryStage);
        controller.init();
    }
}
