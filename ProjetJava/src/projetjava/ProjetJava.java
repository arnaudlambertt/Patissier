/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;


import MODEL.*;
import DAO.*;
import CONTROLLER.Controller;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class ProjetJava extends Application{

    //private VIEW.View view;
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Controller controller = new Controller(primaryStage);
        //controller.init();
        //System.out.println("Je suis dans projetJava");
        //System.out.println(controller.toString());
        
        ProduitDAO dao = new ProduitDAO();
        dao.open();
        
        Produit test;
        String nom = "Clavier";
        String categorie = "Peripheriques";
        String nomFournisseur = "Corsair";
        double prixUnitaire = 3.50;
        int stock = 30;
        int quantiteUnLot = 10;
        double prixUnLot = 25.0;
        double promotion = 0.1;
        boolean promotionActive = false;
        String lienImage = "corsair/clavier.png";
        test = dao.create(new Produit(nom, categorie, nomFournisseur, prixUnitaire, stock, quantiteUnLot, prixUnLot, promotion, promotionActive, lienImage),null);
        System.out.println(test.toString());
        prixUnitaire = 3.0;
        test.setPrixUnitaire(prixUnitaire);
        stock = 29;
        test.setStock(stock);
        System.out.println(dao.stockSuffisant(test,30));
        System.out.println(dao.update(test));
        test = dao.find(test.getId());
        System.out.println(test.toString());
        System.out.println(dao.stockSuffisant(test,30));
    
        dao.getProduits().forEach((p) ->
        {
            System.out.println(p.toString());
        });
        
        dao.getProduits(categorie).forEach((p) ->
        {
            System.out.println(p.toString());
        });
        
        System.out.println(dao.delete(test));

        dao.getProduits(categorie).forEach((p) ->
        {
            System.out.println(p.toString());
        });
        
        dao.getProduits().forEach((p) ->
        {
            System.out.println(p.toString());
        });
        
        dao.close();

        //this.view.start(primaryStage);
        //this.controller.setActionButon();
        System.exit(0);
    }
}