/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import CONSTANT.Panes;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Benjamin
 */

public class PaneAdmin extends VBox
{
    private Button bGestionProduit;
    private Button bGestionAdministrateur;
    private Button bLogo;
    
    private Button bAjoutUtilisateur;
    private Button bAjoutProduit;

    private final Button bDeconnexion;

    private final Separator[] separationHorizontale;

    public PaneAdmin()
    {
        super();


        //BUTTON
        bLogo = new Button();
        bDeconnexion = new Button();
        bAjoutUtilisateur = new Button();;
        bAjoutProduit = new Button();;
        
        this.separationHorizontale = new Separator[2];
        init();
    }

    public final void init()
    {
        //BUTTON
        bGestionProduit = new Button();
        bGestionAdministrateur = new Button();
        bGestionProduit.setText("Gestion Produit");
        bGestionProduit.setStyle("-fx-font: 22 arial; -fx-background-color: #ffffff;-fx-text-fill: #000000");
        bAjoutProduit.setText("AJOUT PRODUIT");
        bAjoutProduit.setStyle("-fx-font: 22 arial; -fx-background-color: #ffffff;-fx-text-fill: #000000");
        
        bGestionAdministrateur.setText("Gestion Utilisateurs");
        bGestionAdministrateur.setStyle("-fx-font: 22 arial; -fx-background-color: #ffffff;-fx-text-fill: #000000");
        bAjoutUtilisateur.setText("AJOUT UTILISATEUR");
        bAjoutUtilisateur.setStyle("-fx-font: 22 arial; -fx-background-color: #ffffff;-fx-text-fill: #000000");
        
        //Separator Horizontale
        for (int i = 0; i < separationHorizontale.length; i++)
        {
            separationHorizontale[i] = new Separator(Orientation.HORIZONTAL);
            separationHorizontale[i].setMaxWidth(Panes.SEPARATOR_HORIZONTAL_WIDTH_PANE_ENTETE);
        }

        // Button LOGO
        Image imgLogo = new Image("http://93.3.238.99/uploads/logo-entier-patissier.png");
        ImageView viewLogo = new ImageView(imgLogo);
        bLogo.setStyle("-fx-background-color: " + Couleurs.BLANC + ";");
        bLogo.setGraphic(viewLogo);

        // Button deconnexion
        Image imageDeconnexion = new Image("http://93.3.238.99/uploads/icon-deconnexion.png");
        ImageView viexDeconnexion = new ImageView(imageDeconnexion);
        viexDeconnexion.setPreserveRatio(true);
        bDeconnexion.setStyle("-fx-background-color: " + Couleurs.BLANC + ";");
        bDeconnexion.setGraphic(viexDeconnexion);

        // STACKPANE
        StackPane stack = new StackPane();
        stack.getChildren().addAll(bAjoutProduit, bAjoutUtilisateur);
        bAjoutProduit.setVisible(false);
        bAjoutUtilisateur.setVisible(false);
        
        //FLOW PANE BAS
        FlowPane flowPaneLigneBas = new FlowPane();
        flowPaneLigneBas.getChildren().add(bGestionAdministrateur);
        flowPaneLigneBas.getChildren().add(bGestionProduit);
        flowPaneLigneBas.getChildren().add(stack);
        flowPaneLigneBas.setAlignment(Pos.TOP_CENTER);

        //GRID PANE 
        GridPane gridPaneButton = new GridPane();
        GridPane gridPaneRechercher = new GridPane();

        gridPaneButton.add(bDeconnexion, 0, 0);
        gridPaneButton.setHgap(Panes.GRID_PANE_BUTTON_HGAP_PANE_ENTETE);

        gridPaneRechercher.setHgap(Panes.GRID_PANE_RECHERCHER_HGAP_PANE_ENTETE);
        gridPaneRechercher.setAlignment(Pos.CENTER);

        //FLOW PANE HAUT
        FlowPane flowPaneLigneHaut = new FlowPane();
        flowPaneLigneHaut.getChildren().addAll(bLogo, gridPaneRechercher, gridPaneButton);
        flowPaneLigneHaut.setHgap(Panes.HGAP_LIGNE_HAUT_PANE_ENTETE);
        flowPaneLigneHaut.setAlignment(Pos.CENTER);

        //espace entre panes
        Region re = new Region();
        re.setPrefHeight(Panes.SPACING_VBOX_PANE_ENTETE);

        //PANE ENTETE
        this.getChildren().addAll(flowPaneLigneHaut, re, separationHorizontale[0], flowPaneLigneBas, separationHorizontale[1]);
        this.setAlignment(Pos.CENTER);

    }

    public Button getbGestionProduit()
    {
        return bGestionProduit;
    }

    public Button getbGestionUtilisateur()
    {
        return bGestionAdministrateur;
    }

    public Button getbAjoutUtilisateur()
    {
        return bAjoutUtilisateur;
    }

    public Button getbAjoutProduit()
    {
        return bAjoutProduit;
    }

    public Button getbDeconnexion()
    {
        return bDeconnexion;
    }
    
    
}