/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONTROLLER.ActionCreationCompte;
import CONTROLLER.ActionSubmit;
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
    private Scene connectionScene;
    private SceneConnexion sConnexion;


    public View(Stage primaryStage)
    {
        this.buttonClose = new Button();
        this.buttonClose2 = new Button();
        this.buttonConnection = new Button();
        this.primaryStage = primaryStage;
    }

    public void init()
    {
//        buttonClose.setText("Close");
//        buttonClose.setOnAction((ActionEvent) -> Platform.exit());
//        buttonClose2.setText("Close");
//        buttonClose2.setOnAction((ActionEvent) -> Platform.exit());
//
//        buttonConnection.setText("Cree compte");
//        buttonConnection.setOnAction(new ActionCreationCompte(this));
//
//        primaryStage.setTitle("Patissier");


        //root.setBackground(new Background(myBI));

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        //primaryStage.setHeight((int)dimension.getHeight());
        //primaryStage.setWidth((int)dimension.getWidth());
        //primaryStage.setScene(sConnexion);
        
//        primaryStage.show();
//        primaryStage.centerOnScreen();

        VBox root = new VBox();
        //root.setBackground(new Background(myBI));
//        root.getChildren().add(buttonClose);
//        root.getChildren().add(buttonConnection);
//        root.setAlignment(Pos.CENTER);
        //this.sConnexion = new Scene(root);

        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////

        sConnexion = new SceneConnexion(new Region());
        sConnexion.init();

        primaryStage.setMaximized(true);
        primaryStage.setScene(sConnexion);
        primaryStage.show();
        primaryStage.centerOnScreen();

        submitNouveauCompte = new Button("Cree Compte");


//        //Labels
//        Label secondLabel = new Label("Page de creation de compte");
//        Label utilisateur = new Label("Identifiant");
//        Label motDePasse = new Label("Mot de Passe");
//
//        //Text Fields
//        emailTextField = new TextField();
//        motDePasseTextField  = new PasswordField();
//
//        VBox secondaryLayout = new VBox(10);
//        //GridPane grid = new GridPane();
//
//        //JPanel panel3 = new JPanel();
//        //panel3.setLayout(new FlowLayout());
//
//        secondaryLayout.getChildren().add(secondLabel);
//        secondaryLayout.getChildren().add(utilisateur);
//        secondaryLayout.getChildren().add(emailTextField);
//        secondaryLayout.getChildren().add(motDePasse);
//        secondaryLayout.getChildren().add(motDePasseTextField);
//        secondaryLayout.getChildren().add(submitNouveauCompte);
//        secondaryLayout.getChildren().add(buttonClose2);
//
//        //secondaryLayout.getChildren().add(grid);
//
//
//        this.connectionScene = new Scene(secondaryLayout, 250, 300);
//
//        this.connectionScene = new SceneAccueil(new Pane());
        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////

        /*Stage other = new Stage();
        other.setTitle("TEST");
        other.show();
        */

        //this.controller.setActionButon();
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

    public Scene getConnectionScene() {
        return connectionScene;
    }

    public Button getSubmitNouveauCompte() {
        return submitNouveauCompte;
    }

    public SceneConnexion getSConnexion()
    {
        return sConnexion;
    }
}
