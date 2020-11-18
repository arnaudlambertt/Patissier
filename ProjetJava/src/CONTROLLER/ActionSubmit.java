/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Utilisateur;
import DAO.UtilisateurDAO;
import VIEW.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Benjamin
 */
public class ActionSubmit  implements EventHandler<ActionEvent>
{

    private Controller controller;
    public ActionSubmit(Controller controller)
    {
        super();
        this.controller=controller;
    }

    @Override
    public void handle(ActionEvent event) 
    {
        
        //if(event.getSource()== this.controller.getView().getSubmitNouveauCompte())
        //{
            System.out.println(this.controller.getView().getIdentifiantUtilisateur());
            System.out.println(this.controller.getView().getMotDePasse().getText());

            this.controller.setEmail(this.controller.getView().getIdentifiantUtilisateur());
            this.controller.creationUtilisateur();
            this.controller.changeScene(this.controller.getView().getInitStage());
            
            System.out.println("NEW User");
            System.out.println(controller.getUtilisateur().toString());
        //}
    }
}
