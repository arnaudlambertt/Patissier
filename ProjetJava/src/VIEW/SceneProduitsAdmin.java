/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CONSTANT.Panes;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Benjamin
 */
public class SceneProduitsAdmin extends SceneCustom
{

    private final ArrayList<PaneProduitAdmin> paneProduits;

    //menu trie
    //bouton revenir en haut
    public SceneProduitsAdmin()
    {
        this.paneProduits = new ArrayList<>();
    }

    @Override
    public void init()
    {
    }

    @Override
    public void update(View v)
    {
        
        //ajouter label accueil > recherche / accueil > catégorie...
        GridPane collectionGridPane = new GridPane();
        collectionGridPane.setVgap(Panes.VGAP_SCENE_PRODUITS);
        collectionGridPane.setAlignment(Pos.TOP_CENTER);
        for (int i = 0; i < paneProduits.size(); ++i)
        {
            collectionGridPane.add(paneProduits.get(i), 0, 2 * i + 1);
            collectionGridPane.add(new Separator(), 0, 2 * (i + 1));
        }
        
        page.setCenter(collectionGridPane);
    }

    public ArrayList<PaneProduitAdmin> getPanesProduitAdmin()
    {
        return paneProduits;
    }
    
}
