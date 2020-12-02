/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.COULEUR;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Benjamin
 */
public class PaneEntete extends BorderPane
{
    private FlowPane ligneHaut;
    private FlowPane ligneBas;
    
    private TextField tBarreRecherche;
    private Button bRecherche;
    private Button bPanier;
    private Button bBonjour;
    private Button bLogo;
    
    private Button[] bCategories;
    
    private Menu[] menuItems; 
    
    public PaneEntete()
    {
        super();
        tBarreRecherche = new TextField("Rechercher");
        
        //Boutons
        bLogo = new Button();
        bRecherche = new Button();
        bRecherche.setStyle("-fx-background-color: #fd5300");
        bPanier = new Button();
        bBonjour = new Button();
        bCategories = new Button[9];
        
        bCategories[0] = new Button("Gros\nélectroménager");
        bCategories[1] = new Button("Cuisine\nCuisson");
        bCategories[2] = new Button("Maison\nEntretien");
        bCategories[3] = new Button("Beauté\nSanté");
        bCategories[4] = new Button("Objets\nconnecté");
        bCategories[5] = new Button("Smartphone\ntéléphonie");
        bCategories[6] = new Button("Informatique\nTablette");
        bCategories[7] = new Button("Tv Image\nSon");
        bCategories[8] = new Button("Console\nGaming");
        
        for(Button i : bCategories)
        {
            i.setStyle("-fx-background-color: " + COULEUR.BLANC);
        }
        
        
    }
    
    public void init()
    {
        // Button LOGO
        Image imgLogo = new Image("http://93.3.238.99/uploads/logo-entier-patissier.png");
        ImageView viewLogo = new ImageView(imgLogo);
        bLogo.setStyle("-fx-background-color: " + COULEUR.BLANC);
        bLogo.setGraphic(viewLogo);
        
        // Button Panier
        Image imgPanier = new Image("http://93.3.238.99/uploads/icon-panier.jpg");
        ImageView viewPanier = new ImageView(imgPanier);
        viewPanier.setPreserveRatio(true);
        bPanier.setStyle("-fx-background-color: " + COULEUR.BLANC);
        bPanier.setGraphic(viewPanier);
        
        // Button Bonjour
        Image imgBonjour = new Image("http://93.3.238.99/uploads/icon-bonjour.jpg");
        ImageView viewBonjour = new ImageView(imgBonjour);
        viewBonjour.setPreserveRatio(true);
        bBonjour.setStyle("-fx-background-color: " + COULEUR.BLANC);
        bBonjour.setGraphic(viewBonjour);
        
        // Button Rechercher
        Image imgRechercher = new Image("http://93.3.238.99/uploads/icon-loupe.png");
        ImageView viewRechercher = new ImageView(imgRechercher);
        viewRechercher.setPreserveRatio(true);
        bRecherche.setGraphic(viewRechercher);
        
        
        ligneHaut = new FlowPane();
        ligneBas = new FlowPane();
        for(Button i: bCategories)
        {
            ligneBas.getChildren().add(i);
        }
        
        
        ligneHaut.getChildren().addAll(bLogo, tBarreRecherche, bRecherche, bBonjour, bPanier);
        ligneHaut.setHgap(25);
        ligneHaut.setAlignment(Pos.TOP_CENTER);
        ligneBas.setAlignment(Pos.TOP_CENTER);
        
        setTop(ligneHaut);
        setCenter(ligneBas);
        
        
    }

    public TextField gettBarreRecherche()
    {
        return tBarreRecherche;
    }

    public Button getbRecherche()
    {
        return bRecherche;
    }

    public Button getbPanier()
    {
        return bPanier;
    }

    public Button getbBonjour()
    {
        return bBonjour;
    }

    public Button getbLogo()
    {
        return bLogo;
    }

    public Button[] getbCategories()
    {
        return bCategories;
    }

    public Menu[] getMenuItems()
    {
        return menuItems;
    }
}
