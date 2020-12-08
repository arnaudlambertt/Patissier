/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Couleurs;
import CONSTANT.Panes;
import static VIEW.SceneCustom.page;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Arnaud
 */
public class sceneAdresseLivraison extends SceneCustom
{
    private final VBox box;
    private final TextField tCodePostal;
    private final TextField tVille;
    private final TextField tRue;
    private final TextField tNumero;
    private final Button bValiderAdresse;
    private final Label lAdresseIncomplete;

    public sceneAdresseLivraison()
    {
        this.box = new VBox(10);
        this.tCodePostal = new TextField();
        this.tVille = new TextField();
        this.tRue = new TextField();
        this.tNumero = new TextField();
        this.bValiderAdresse = new Button();
        this.lAdresseIncomplete = new Label();
    }

    @Override
    public void init()
    {
        FlowPane FlowPaneCodePostal = new FlowPane();
        FlowPane FlowPaneVille = new FlowPane();
        FlowPane FlowPaneRue = new FlowPane();
        FlowPane FlowPaneNumero = new FlowPane();

        ImageView progressionPanier = new ImageView(new Image("http://93.3.238.99/uploads/Panier-3.png"));

        Label lIntitule = new Label("Modifier ou ajouter une adresse de livraison");
        Label lCodePostal = new Label("Code Postal : ");
        lCodePostal.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lCodePostal.setAlignment(Pos.CENTER_RIGHT);
        Label lVille = new Label("Ville : ");
        lVille.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lVille.setAlignment(Pos.CENTER_RIGHT);
        Label lRue = new Label("Rue : ");
        lRue.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lRue.setAlignment(Pos.CENTER_RIGHT);
        Label lNumero = new Label("Numero : ");
        lNumero.setMinWidth(Panes.LABEL_WIDTH_SCENE_CREATION_COMPTE);
        lNumero.setAlignment(Pos.CENTER_RIGHT);

        tCodePostal.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        tVille.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        tRue.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);
        tNumero.setMaxWidth(Panes.TEXTFIELD_WIDTH_SCENE_CREATION_COMPTE);

        bValiderAdresse.setText("VALIDER");
        bValiderAdresse.setStyle("-fx-background-color : " + Couleurs.ORANGE_PATISSIER + "; -fx-text-fill: " + Couleurs.BLANC
                + ";-fx-font-weight: bold;");
        bValiderAdresse.setPrefSize(240, 40);
        
        lAdresseIncomplete.setText("Adresse incomplète.");
        lAdresseIncomplete.setStyle("-fx-text-fill : ff0000");
        lAdresseIncomplete.setVisible(false);
        
        //FlowPaneNom
        FlowPaneCodePostal.getChildren().addAll(lCodePostal, tCodePostal);
        FlowPaneCodePostal.setAlignment(Pos.CENTER);

        //FlowPanePaenom
        FlowPaneVille.getChildren().addAll(lVille, tVille);
        FlowPaneVille.setAlignment(Pos.CENTER);

        //FlowPaneEmail
        FlowPaneRue.getChildren().addAll(lRue, tRue);
        FlowPaneRue.setAlignment(Pos.CENTER);

        //FlowPaneMotDePasse
        FlowPaneNumero.getChildren().addAll(lNumero, tNumero);
        FlowPaneNumero.setAlignment(Pos.CENTER);

        //Box
        box.getChildren().add(progressionPanier);
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(lIntitule);
        box.getChildren().add(FlowPaneCodePostal);
        box.getChildren().add(FlowPaneVille);
        box.getChildren().add(FlowPaneRue);
        box.getChildren().add(FlowPaneNumero);
        box.getChildren().add(bValiderAdresse);
        box.getChildren().add(lAdresseIncomplete);
    }

    @Override
    public void update(View v)
    {
        lAdresseIncomplete.setVisible(false);
        page.setCenter(box);
    }

    public TextField gettCodePostal()
    {
        return tCodePostal;
    }

    public TextField gettVille()
    {
        return tVille;
    }

    public TextField gettRue()
    {
        return tRue;
    }

    public TextField gettNumero()
    {
        return tNumero;
    }
    
    public Button getbValiderAdresse()
    {
        return bValiderAdresse;
    }
    
    public void setAdresseIncompleteVisible()
    {
        lAdresseIncomplete.setVisible(true);
    }
    
}
