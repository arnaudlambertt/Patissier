/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Benjamin
 */
public class Fenetre extends Application{
    Button buttonClose;
    Button buttonConnection;
    
    TextField utilisateurTextField;
    PasswordField motDePasseTextField;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        buttonClose = new Button();
        buttonClose.setText("Close");
        buttonClose.setOnAction((ActionEvent) -> Platform.exit());
        
        buttonConnection = new Button();
        buttonConnection.setText("Connection");
        buttonConnection.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
                //Buton
                Button submit = new Button("SUBMIT");
                submit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println(getUtilisateur().getText());
                        System.out.println(getMotDePasse().getText());
                    }
                });
                
                //Labels
                Label secondLabel = new Label("Page de connection");
                Label utilisateur = new Label("Identifiant");
                Label motDePasse = new Label("Mot de Passe");
                
                //Text Fields
                utilisateurTextField = new TextField();
                motDePasseTextField  = new PasswordField();
 
                FlowPane secondaryLayout = new FlowPane();
                GridPane grid = new GridPane();
                
                JPanel panel3 = new JPanel();
                panel3.setLayout(new FlowLayout());
                
                grid.add(secondLabel, 0, 0);
                grid.add(utilisateur, 0, 1);
                grid.add(utilisateurTextField, 0, 2);
                grid.add(motDePasse, 0, 3);
                grid.add(motDePasseTextField, 0, 4);
                grid.add(submit, 0, 6);
                
                secondaryLayout.getChildren().add(grid);
                
 
                Scene secondScene = new Scene(secondaryLayout, 250, 300);
 
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("CONNECTION");
                newWindow.setScene(secondScene);
 
                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX() + 200);
                newWindow.setY(primaryStage.getY() + 100);
 
                newWindow.show();
            }
            
        });
        
        
        
        primaryStage.setTitle("Hello World!");
        BackgroundImage myBI= new BackgroundImage(new javafx.scene.image.Image("http://93.3.238.99/index.jpg"),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
        
        FlowPane root = new FlowPane();
        root.setBackground(new Background(myBI));
        root.getChildren().add(buttonClose);
        root.getChildren().add(buttonConnection);
        Scene scene = new Scene(root);
        primaryStage.setHeight(500);
        primaryStage.setWidth(500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
        
        /*Stage other = new Stage();
        other.setTitle("TEST");
        other.show();
        */
        
    }
    public TextField getUtilisateur()
    {
        return utilisateurTextField;
    }

    public PasswordField getMotDePasse()
    {
        return motDePasseTextField;
    }
    
}
