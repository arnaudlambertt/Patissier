/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;


/**
 *
 * @author Benjamin
 */
public class Produit {
    
    private int id;
    private String nom;
    private String categorie;
    private String nomFournisseur;
    private double prixUnitaire;
    private int stock;
    private int quantiteUnLot;
    private double prixUnLot;
    private double promotion;
    private boolean promotionActive;
    private String lienImage;
    
    public Produit()
    {
        this.id = 0;
        this.nom = "";
        this.categorie = "";
        this.nomFournisseur = "";
        this.prixUnitaire = 0.0;
        this.stock = 0;
        this.quantiteUnLot = 0;
        this.prixUnLot = 0.0;
        this.promotion = 0;
        this.promotionActive = false;
        this.lienImage = "";
    }

    public Produit(int id, String nom, String categorie, String nomFournisseur
        , double prixUnitaire, int stock, int quantiteUnLot, double prixUnLot
        , double promotion, boolean promotionActive, String lienImage) 
    {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.nomFournisseur = nomFournisseur;
        this.prixUnitaire = prixUnitaire;
        this.stock = stock;
        this.quantiteUnLot = quantiteUnLot;
        this.prixUnLot = prixUnLot;
        this.promotion = promotion;
        this.promotionActive = promotionActive;
        this.lienImage = lienImage;
    }
    
    public Produit(String nom, String categorie, String nomFournisseur
        , double prixUnitaire, int stock, int quantiteUnLot, double prixUnLot
        , double promotion, boolean promotionActive, String lienImage) 
    {
        this.id = 0;
        this.nom = nom;
        this.categorie = categorie;
        this.nomFournisseur = nomFournisseur;
        this.prixUnitaire = prixUnitaire;
        this.stock = stock;
        this.quantiteUnLot = quantiteUnLot;
        this.prixUnLot = prixUnLot;
        this.promotion = promotion;
        this.promotionActive = promotionActive;
        this.lienImage = lienImage;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public String getLienImage() {
        return lienImage;
    }

    public void setLienImage(String lienImage) {
        this.lienImage = lienImage;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public double getPrixUnLot() {
        return prixUnLot;
    }

    public void setPrixUnLot(double prixUnLot) {
        this.prixUnLot = prixUnLot;
    }

    public double getPromotion() {
        return promotion;
    }

    public void setPromotion(double promotion) {
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
        str += "STOCK : " + this.getStock()+ "\n";
        str += "QUANTITE DANS UN LOT : " + this.getQuantiteUnLot()+ "\n";
        str += "PRIX POUR UN LOT : " + this.getPrixUnLot()+ "\n";
        str += "PROMOTION : " + this.getPromotion()+ "\n";
        str += "PROMOTION ACTIVE : " + this.isPromotionActive()+ "\n";
        str += "LIEN : " + this.getLienImage()+ "\n";
        str += "\n.....................................\n";
        
        return str;
    }
    
}
