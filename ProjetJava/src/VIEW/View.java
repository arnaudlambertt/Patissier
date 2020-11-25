/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONTROLLER.Controller;
import MODEL.Utilisateur;
import DAO.UtilisateurDAO;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Benjamin
 */
public class View{
    Button buttonClose;
    Button buttonClose2;
    Button buttonConnection;
    Button submitNouveauCompte;

    TextField emailTextField;
    PasswordField motDePasseTextField;

    private Stage primaryStage;
    private SceneConnexion sConnexion;
    private SceneCreationCompte sCreationCompte;

    public View(Stage primaryStage)
    {
        this.buttonClose = new Button();
        this.buttonClose2 = new Button();
        this.buttonConnection = new Button();
        this.primaryStage = primaryStage;
    }

    public void init()
    {
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();



        sConnexion = new SceneConnexion(new Region());
        sConnexion.init();

        sCreationCompte = new SceneCreationCompte(new Region());
        sCreationCompte.init();

        primaryStage.setMaximized(true);
        primaryStage.setScene(sConnexion);
        primaryStage.show();
        primaryStage.centerOnScreen();

        submitNouveauCompte = new Button("Cree Compte");

    }


    public String getEmail()
    {
        return emailTextField.getText();
    }

    public PasswordField getMotDePasse()
    {
        return motDePasseTextField;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Button getSubmitNouveauCompte() {
        return submitNouveauCompte;
    }

    public SceneConnexion getSConnexion()
    {
        return sConnexion;
    }

    public SceneCreationCompte getCreationCompte()
    {
        return sCreationCompte;
    }
}
