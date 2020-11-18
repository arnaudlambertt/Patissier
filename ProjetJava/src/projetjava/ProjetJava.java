/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;



import CONTROLLER.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class ProjetJava extends Application{

    private VIEW.View view;
    private CONTROLLER.Controller controller;
    @Override
    public void init()
    {
        this.view = new VIEW.View();
        this.controller=new Controller();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        System.out.println("Je suis dans projetJava");
        System.out.println(this.controller.toString());
        this.view.start(primaryStage);
        
        
    }
    
    
    
}