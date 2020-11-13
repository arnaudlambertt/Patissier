/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commande;

import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.util.Pair;
import produit.Produit;

/**
 *
 * @author Benjamin
 */
public class Commande {
    private int idCommande; 
    private int idProduit;
    private int quantiteUnitaire;
    private int quantiteUnLot;
    private float prixUnitaire;
    private float prixUnLot ;
    private Timestamp horodateur;
    private ArrayList<Pair<Produit,Integer>> produitsCommande;
    
    public Commande(int idCommande, int idProduit, int quantiteUnitaire, int quantiteUnLot, float prixUnitaire, float prixUnLot, Timestamp horodateur) {
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantiteUnitaire = quantiteUnitaire;
        this.quantiteUnLot = quantiteUnLot;
        this.prixUnitaire = prixUnitaire;
        this.prixUnLot = prixUnLot;
        this.horodateur = horodateur;
        this.produitsCommande = new ArrayList<>();
    }
    
    public Commande(int idProduit, int quantiteUnitaire, int quantiteUnLot, float prixUnitaire, float prixUnLot, Timestamp horodateur) {
        this.idCommande = 0;
        this.idProduit = idProduit;
        this.quantiteUnitaire = quantiteUnitaire;
        this.quantiteUnLot = quantiteUnLot;
        this.prixUnitaire = prixUnitaire;
        this.prixUnLot = prixUnLot;
        this.horodateur = horodateur;
        this.produitsCommande = new ArrayList<>();
    }
    
    public Commande() {
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantiteUnitaire = quantiteUnitaire;
        this.quantiteUnLot = quantiteUnLot;
        this.prixUnitaire = prixUnitaire;
        this.prixUnLot = prixUnLot;
        this.horodateur = horodateur;
        this.produitsCommande = new ArrayList<>();
    }

    public int getQuantiteUnitaire() {
        return quantiteUnitaire;
    }

    public void setQuantiteUnitaire(int quantiteUnitaire) {
        this.quantiteUnitaire = quantiteUnitaire;
    }

    public int getQuantiteUnLot() {
        return quantiteUnLot;
    }

    public void setQuantiteUnLot(int quantiteUnLot) {
        this.quantiteUnLot = quantiteUnLot;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public float getPrixUnLot() {
        return prixUnLot;
    }

    public void setPrixUnLot(float prisUnLot) {
        this.prixUnLot = prisUnLot;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int id) {
        this.idCommande = idCommande;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public Timestamp getHorodateur() {
        return horodateur;
    }

    public void setHorodateur(Timestamp date) {
        this.horodateur = date;
    }
    
    @Override
    public String toString()
    {
        String str = "ID COMMANDE : " + this.getIdCommande()+ "\n";
        str += "ID PRODUIT : " + this.getIdProduit()+ "\n";
        str += "QUANDITE UNITAIRE : " + this.getQuantiteUnitaire()+ "\n";
        str += "PRIX UNITAIRE : " + this.getPrixUnitaire()+ "\n";
        str += "QUANTITE DANS UN LOT : " + this.getQuantiteUnLot()+ "\n";
        str += "PRIX D'UN SEUL LOT : " + this.getPrixUnitaire()+ "\n";
        str += "\n.....................................\n";
        
        return str;
    }
    
}
