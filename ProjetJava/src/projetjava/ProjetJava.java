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
public class ProjetJava extends Application
{

    //private VIEW.View view;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ProduitDAO pdao = new ProduitDAO();

        Produit p = pdao.find(2);
        Produit p2 = pdao.find(3);
        Produit p3;
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
        p3 = pdao.create(new Produit(nom, categorie, nomFournisseur, prixUnitaire, stock, quantiteUnLot, prixUnLot, promotion, promotionActive, lienImage));
        
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(10);

        CommandeDAO dao = new CommandeDAO();
        Commande c = new Commande("10 rue eugene sue rueil");

        c.addProduitCommande(new Pair<>(p, 23));
        c.addProduitCommande(new Pair<>(p2, 18));

        c = dao.create(c, utilisateur);
        System.out.println(c.toString());
        
        dao.getCommandes().forEach((com) ->
        {
            System.out.println(com.toString());
        });
        
        c.setAdresse("45 rue merlin de thionvilles");
        c.addProduitCommande(new Pair<>(p2, 43));
        c.addProduitCommande(new Pair<>(p3, 14));
        c.removeProduitCommande(p);
                
        System.out.println(dao.update(c));

        dao.getCommandes(utilisateur.getId()).forEach((com) ->
        {
            System.out.println(com.toString());
        });
        
        System.out.println(dao.delete(c));
        c = dao.find(c.getId());
        System.out.println(c.toString());
        
        pdao.delete(p3);

        dao.close();

        System.exit(0);
    }
}
