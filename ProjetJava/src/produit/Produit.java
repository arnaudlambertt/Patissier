/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produit;


/**
 *
 * @author Benjamin
 */
public class Produit {
    private int id;
    private int quantiteUnLot;
    private int quantiteEnStock;
    private String nom;
    private String categorie;
    private String nomFournisseur;
    private String lien;
    private float prixUnitaire;
    private float prixUnLot;
    private float promotion;
    private boolean promotionActive=false;
    
    public Produit()
    {
        this.id=0;
        this.nom = "";
        this.categorie= "";
        this.nomFournisseur= "";
        this.prixUnitaire=0f;
        this.quantiteEnStock=0;
        this.quantiteUnLot=0;
        this.prixUnLot=0f;
        this.promotion=0;
        this.promotionActive=false;
        this.lien="";
    }

    public Produit(int id, String nom, String categorie, String nomFournisseur
        , float prixUnitaire, int quantiteEnStock, int quantiteUnLot, float prixUnLot
        , float promotion, boolean promotionActive, String lien) 
    {
        this.id=id;
        this.nom = nom;
        this.categorie=categorie;
        this.nomFournisseur=nomFournisseur;
        this.prixUnitaire=prixUnitaire;
        this.quantiteEnStock=quantiteEnStock;
        this.quantiteUnLot=quantiteUnLot;
        this.prixUnLot=prixUnLot;
        this.promotion=promotion;
        this.promotionActive=promotionActive;
        this.lien=lien;
    }
    public Produit( String nom, String categorie, String nomFournisseur
        , float prixUnitaire, int quantiteEnStock, int quantiteUnLot, float prixUnLot
        , float promotion, boolean promotionActive, String lien) 
    {
        this.id=0;
        this.nom = nom;
        this.categorie=categorie;
        this.nomFournisseur=nomFournisseur;
        this.prixUnitaire=prixUnitaire;
        this.quantiteEnStock=quantiteEnStock;
        this.quantiteUnLot=quantiteUnLot;
        this.prixUnLot=prixUnLot;
        this.promotion=promotion;
        this.promotionActive=promotionActive;
        this.lien=lien;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantiteUnLot() {
        return quantiteUnLot;
    }

    public void setQuantiteUnLot(int quantiteUnLot) {
        this.quantiteUnLot = quantiteUnLot;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
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

    public void setPrixUnLot(float prixUnLot) {
        this.prixUnLot = prixUnLot;
    }

    public float getPromotion() {
        return promotion;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
    }

    public boolean isPromotionActive() {
        return promotionActive;
    }

    public void setPromotionActive(boolean promotionActive) {
        this.promotionActive = promotionActive;
    }
    
    @Override
    public String toString()
    {
        String str = "ID : " + this.getId()+ "\n";
        str += "NOM : " + this.getNom() + "\n";
        str += "CATEGORIE : " + this.getCategorie()+ "\n";
        str += "NOM FOURNISSEUR : " + this.getNomFournisseur()+ "\n";
        str += "PRIX UNITAIRE : " + this.getPrixUnitaire()+ "\n";
        str += "QUANTITE EN STOCK : " + this.getQuantiteEnStock()+ "\n";
        str += "QUANTITE DANS UN LOT : " + this.getQuantiteUnLot()+ "\n";
        str += "PRIX POUR UN LOT : " + this.getPrixUnLot()+ "\n";
        str += "PROMOTION : " + this.getPromotion()+ "\n";
        str += "PROMOTION ACTIVE : " + this.isPromotionActive()+ "\n";
        str += "LIEN : " + this.getLien()+ "\n";
        str += "\n.....................................\n";
        
        return str;
    }
    
}
