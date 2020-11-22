/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import MODEL.*;
import DAO.*;
import CONTROLLER.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author Benjamin
 */
public class ProjetJava extends Application{

    //private VIEW.View view;
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ProduitDAO pdao = new ProduitDAO();
        
        Produit p = pdao.find(2);
        Commande c = new Commande();
        c.setId(1);
        
        ProduitCommandeDAO dao = new ProduitCommandeDAO();
        int idCommande = 1;
        int quantite = 25;
        
        Pair<Produit,Integer> pc = new Pair<>(p,quantite);
        pc = dao.create(pc, idCommande);
        System.out.println(pc.getKey().toString() + "\n Quantite = " + pc.getValue());
        
        c.addProduitsCommande(pc);
        System.out.println(c.toString());
        
        quantite = 24;
        System.out.println(dao.update(new Pair<>(p,idCommande), quantite));
        pc = dao.find(p.getId(), idCommande);
        System.out.println(pc.getKey().toString() + "\n Quantite = " + pc.getValue());
        c.setProduitsCommande(dao.getProduitsCommande(idCommande));
        System.out.println(c.toString());
        System.out.println(dao.delete(new Pair<>(p,idCommande)));
        pc = dao.find(p.getId(), idCommande);
        System.out.println(pc.getKey().toString() + "\n Quantite = " + pc.getValue());

        dao.close();
        
        System.exit(0);
    }
}