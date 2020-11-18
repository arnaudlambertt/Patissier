/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import VIEW.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class ActionCreationCompte implements EventHandler<ActionEvent>{

    private View fenetre;
    public ActionCreationCompte(View fenetre)
    {
        super();
        this.fenetre=fenetre;
    }

    @Override
    public void handle(ActionEvent event) {
        this.fenetre.getPrimaryStage().setScene(fenetre.getConnectionScene());
    }
    
    
}
