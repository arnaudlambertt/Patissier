/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import CONSTANT.Panes;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;

/**
 *
 * @author Benjamin
 */
public class SceneModifierProduit extends SceneCustom
{
    private final VBox box;
    private final Button bValiderModificationProduit;
    private final Label lProduitIncomplete;    

    
    private final TextField tNom;
    private final TextField tFournisseur;
    private final TextField tPrixUnitaire;
    private final TextField tStock;
    private final TextField tQuantiteUnLot;
    private final TextField tPrixUnLot;
    private final TextField tPromotion;
    private final TextField tImage;
    
    
    private final ComboBox<String> listCategorie;
    private final ComboBox<Boolean> listPromotionActive;
                
    public SceneModifierProduit()
    {
        this.box = new VBox(13);
        
        this.tNom = new TextField();
        this.tFournisseur = new TextField();
        this.tPrixUnitaire = new TextField();
        this.tStock = new TextField();
        this.tQuantiteUnLot = new TextField();
        this.tPrixUnLot = new TextField();
        this.tPromotion = new TextField();
        this.tImage = new TextField();
        
        this.bValiderModificationProduit = new Button();
        this.lProduitIncomplete = new Label();
        this.listCategorie = new ComboBox<>();
        this.listPromotionActive = new ComboBox<>();
        
    }
    

    @Override
    public void init()
    {
        FlowPane FlowPaneNom = new FlowPane();
        FlowPane FlowPaneCategorie = new FlowPane();
        FlowPane FlowPaneFournisseur = new FlowPane();
        FlowPane FlowPanePrixUnitaire = new FlowPane();
        FlowPane FlowPaneStock = new FlowPane();
        FlowPane FlowPaneQuantiteUnLot = new FlowPane();
        FlowPane FlowPanePrixUnLot = new FlowPane();
        FlowPane FlowPanePromotion = new FlowPane();
        FlowPane FlowPanePromotionActive = new FlowPane();
        FlowPane FlowPaneImage = new FlowPane();


        Label lIntitule = new Label("Modifier les informations d'un produit");
        
        //Nom
        Label lNom = new Label("Nom du produit : ");
        lNom.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lNom.setAlignment(Pos.CENTER_RIGHT);
        
        // Catégorie
        Label lCatégorie = new Label("Catégorie : "); 
        lCatégorie.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lCatégorie.setAlignment(Pos.CENTER_RIGHT);
        
        //fournisseur
        Label lFournisseur= new Label("Nom du Fournisseur : ");
        lFournisseur.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lFournisseur.setAlignment(Pos.CENTER_RIGHT);

        //Prix Unitaire
        Label lPrixUnitaire = new Label("Prix Unitaire : ");
        lPrixUnitaire.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lPrixUnitaire.setAlignment(Pos.CENTER_RIGHT);
        
        //Stock
        Label lStock = new Label("Quantité en stock : ");
        lStock.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lStock.setAlignment(Pos.CENTER_RIGHT);
        
        //Quantite un lot
        Label lQuantiteUnLot = new Label("Quantité dans un lot : ");
        lQuantiteUnLot.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lQuantiteUnLot.setAlignment(Pos.CENTER_RIGHT);
        
        //Prix un lot
        Label lPrixUnLot = new Label("Prix pour un lot : ");
        lPrixUnLot.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lPrixUnLot.setAlignment(Pos.CENTER_RIGHT);
        
        //Promotion
        Label lPromotion = new Label("Montant de la promotion : ");
        lPromotion.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lPromotion.setAlignment(Pos.CENTER_RIGHT);
        
        //Promotion active
        Label lPromotionActive = new Label("Promotion active : ");
        lPromotionActive.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lPromotionActive.setAlignment(Pos.CENTER_RIGHT);
        
        //Image
        Label lImage = new Label("lien Image : ");
        lImage.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lImage.setAlignment(Pos.CENTER_RIGHT);
        
        this.tNom.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        this.tFournisseur.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        this.tPrixUnitaire.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        this.tStock.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        this.tQuantiteUnLot.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        this.tPrixUnLot.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        this.tPromotion.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        this.tImage.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);

        bValiderModificationProduit.setText("VALIDER");
        bValiderModificationProduit.setStyle("-fx-background-color : " + Couleurs.ORANGE_PATISSIER + "; -fx-text-fill: " + Couleurs.BLANC
                + ";-fx-font-weight: bold;");
        bValiderModificationProduit.setMinSize(240, 40);
        
        lProduitIncomplete.setText("Produit incomplèt.");
        lProduitIncomplete.setStyle("-fx-text-fill : ff0000");
        lProduitIncomplete.setVisible(false);
        
        //FlowPaneNom
        FlowPaneNom.getChildren().addAll(lNom, tNom);
        FlowPaneNom.setAlignment(Pos.CENTER);
        
        // Catégorie
        ObservableList<String> Cat = FXCollections.observableArrayList("Gros électroménager", "Cuisine Cuisson", "Maison Entretien",
                "Beauté Santé", "Objets connectés", "Smartphone Téléphonie", "Informatique Tablette", "TV Image Son", "Console Gaming");
        
        listCategorie.setItems(Cat);
        
        FlowPaneCategorie.getChildren().addAll(lCatégorie, listCategorie);
        FlowPaneCategorie.setAlignment(Pos.CENTER);
        
        //fournisseur
        FlowPaneFournisseur.getChildren().addAll(lFournisseur, tFournisseur);
        FlowPaneFournisseur.setAlignment(Pos.CENTER);

        //Prix Unitaire
        FlowPanePrixUnitaire.getChildren().addAll(lPrixUnitaire, tPrixUnitaire);
        FlowPanePrixUnitaire.setAlignment(Pos.CENTER);
        
        //Stock
        FlowPaneStock.getChildren().addAll(lStock, tStock);
        FlowPaneStock.setAlignment(Pos.CENTER);
        
        //Quantite un lot
        FlowPaneQuantiteUnLot.getChildren().addAll(lQuantiteUnLot, tQuantiteUnLot);
        FlowPaneQuantiteUnLot.setAlignment(Pos.CENTER);
        
        //Prix un lot
        FlowPanePrixUnLot.getChildren().addAll(lPrixUnLot, tPrixUnLot);
        FlowPanePrixUnLot.setAlignment(Pos.CENTER);
        
        //Promotion
        Label pourcent = new Label("%");
        FlowPanePromotion.getChildren().addAll(lPromotion, tPromotion, pourcent);
        FlowPanePromotion.setAlignment(Pos.CENTER);
        
        //Promotion active
        ObservableList<Boolean> listPromo = FXCollections.observableArrayList(true, false);
        listPromotionActive.setItems(listPromo);
        listPromotionActive.getSelectionModel().select(1);
        FlowPanePromotionActive.getChildren().addAll(lPromotionActive, listPromotionActive);
        FlowPanePromotionActive.setAlignment(Pos.CENTER);
        
        //Image
        FlowPaneImage.getChildren().addAll(lImage, tImage);
        FlowPaneImage.setAlignment(Pos.CENTER);

        //Box
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(lIntitule);
        box.getChildren().add(FlowPaneNom);
        box.getChildren().add(FlowPaneCategorie);
        box.getChildren().add(FlowPaneFournisseur);
        box.getChildren().add(FlowPanePrixUnitaire);
        box.getChildren().add(FlowPaneStock);
        box.getChildren().add(FlowPaneQuantiteUnLot);
        box.getChildren().add(FlowPanePrixUnLot);
        box.getChildren().add(FlowPanePromotion);
        box.getChildren().add(FlowPanePromotionActive);
        box.getChildren().add(FlowPaneImage);
        box.getChildren().add(bValiderModificationProduit);
        box.getChildren().add(lProduitIncomplete);
    }

    @Override
    public void update(View v)
    {
        lProduitIncomplete.setVisible(false);
        page.setCenter(box);
    }

    public TextField gettNom()
    {
        return tNom;
    }

    public TextField gettFournisseur()
    {
        return tFournisseur;
    }

    public TextField gettPrixUnitaire()
    {
        return tPrixUnitaire;
    }

    public TextField gettStock()
    {
        return tStock;
    }

    public TextField gettQuantiteUnLot()
    {
        return tQuantiteUnLot;
    }

    public TextField gettPrixUnLot()
    {
        return tPrixUnLot;
    }

    public TextField gettPromotion()
    {
        return tPromotion;
    }

    public TextField gettImage()
    {
        return tImage;
    }
    
    public void setProduitIncompleteVisible()
    {
        lProduitIncomplete.setVisible(true);
    }
    
    public Button getbValiderModificationProduit()
    {
        return bValiderModificationProduit;
    }

    public void setSelectCategorie(String categorie)
    {
        listCategorie.getSelectionModel().select(categorie);
    }
    
    public String getSelectCategorie()
    {
        return listCategorie.getSelectionModel().getSelectedItem();
    }
    
    public boolean getSelectPromotion()
    {
        return listPromotionActive.getSelectionModel().getSelectedItem();
    }
    
    public void setSelectPromotion(boolean promotion)
    {
        listPromotionActive.getSelectionModel().select(promotion);
    }
    
    public void clearTextField()
    {
        this.tNom.clear();
        this.tFournisseur.clear();
        this.tPrixUnitaire.clear();
        this.tStock.clear();
        this.tPrixUnLot.clear();
        this.tQuantiteUnLot.clear();
        this.tPromotion.clear();
        this.tImage.clear();
        //this.listCategorie.getSelectionModel().clearSelection();
        //this.listPromotionActive.getSelectionModel().clearSelection();
    }
}
