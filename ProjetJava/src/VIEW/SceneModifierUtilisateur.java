/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import CONSTANT.Panes;
import static VIEW.SceneCustom.page;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Benjamin
 */
public class SceneModifierUtilisateur extends SceneCustom
{
    private final VBox box;
    private final Button bValiderModificationProduit;
    private final Label lUtilisateurIncomplet;    

    
    private final TextField tNom;
    private final TextField tPrenom;
    private final TextField tEmail;
    
    
    private final ComboBox<String> listRole;
                
    public SceneModifierUtilisateur()
    {
        this.box = new VBox(7);
        
        this.tNom = new TextField();
        this.tPrenom = new TextField();
        this.tEmail = new TextField();
        
        this.bValiderModificationProduit = new Button();
        this.lUtilisateurIncomplet = new Label();
        this.listRole = new ComboBox<>();
        
    }
    

    @Override
    public void init()
    {
        FlowPane FlowPaneNom = new FlowPane();
        FlowPane FlowPanePrenom = new FlowPane();
        FlowPane FlowPaneEmail = new FlowPane(); 
        FlowPane FlowPaneRole = new FlowPane();


        Label lIntitule = new Label("Modifier les informations d'un Utilisateur");
        
        //Nom
        Label lNom = new Label("Nom : ");
        lNom.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lNom.setAlignment(Pos.CENTER_RIGHT);
        
        //prenom
        Label lPrenom= new Label("Prenom : ");
        lPrenom.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lPrenom.setAlignment(Pos.CENTER_RIGHT);

        //Email
        Label lEmail = new Label("Email : ");
        lEmail.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lEmail.setAlignment(Pos.CENTER_RIGHT);
        
        //Role
        Label lRole = new Label("Role : ");
        lRole.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lRole.setAlignment(Pos.CENTER_RIGHT);
        
        this.tNom.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        this.tPrenom.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        this.tEmail.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);

        bValiderModificationProduit.setText("Mettre à jour");
        bValiderModificationProduit.setStyle("-fx-background-color : " + Couleurs.ORANGE_PATISSIER + "; -fx-text-fill: " + Couleurs.BLANC
                + ";-fx-font-weight: bold;");
        bValiderModificationProduit.setMinSize(240, 40);
        
        lUtilisateurIncomplet.setText("Information utilisateur incomplètes.");
        lUtilisateurIncomplet.setStyle("-fx-text-fill : ff0000");
        lUtilisateurIncomplet.setVisible(false);
        
        //FlowPaneNom
        FlowPaneNom.getChildren().addAll(lNom, tNom);
        FlowPaneNom.setAlignment(Pos.CENTER);
        
        //FlowPanePrenom
        FlowPanePrenom.getChildren().addAll(lPrenom, tPrenom);
        FlowPanePrenom.setAlignment(Pos.CENTER);

        //FlowPaneEmail
        FlowPaneEmail.getChildren().addAll(lEmail, tEmail);
        FlowPaneEmail.setAlignment(Pos.CENTER);
        
        //FlowPaneRole
        ObservableList<String> listR = FXCollections.observableArrayList("Administrateur", "Utilisateur");
        listRole.setItems(listR);
        FlowPaneRole.getChildren().addAll(lRole, listRole);
        FlowPaneRole.setAlignment(Pos.CENTER);
        
        //Box
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(lIntitule);
        box.getChildren().add(FlowPaneNom);
        box.getChildren().add(FlowPanePrenom);
        box.getChildren().add(FlowPaneEmail);
        box.getChildren().add(FlowPaneRole);
        box.getChildren().add(bValiderModificationProduit);
        box.getChildren().add(lUtilisateurIncomplet);
    }

    @Override
    public void update(View v)
    {
         lUtilisateurIncomplet.setVisible(false);
        page.setCenter(box);
    }

    public Button getbValiderModificationProduit()
    {
        return bValiderModificationProduit;
    }

    public TextField gettNom()
    {
        return tNom;
    }

    public TextField gettPrenom()
    {
        return tPrenom;
    }

    public TextField gettEmail()
    {
        return tEmail;
    }

    public void setUtiliisateurIncompleteVisible()
    {
        lUtilisateurIncomplet.setVisible(true);
    }
    

    public void setSelectRole(String role)
    {
        listRole.getSelectionModel().select(role);
    } 
    
    public String getSelectRole()
    {
        return listRole.getSelectionModel().getSelectedItem();
    } 
}
