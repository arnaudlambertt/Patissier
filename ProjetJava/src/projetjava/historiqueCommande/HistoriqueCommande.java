/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava.historiqueCommande;

import java.sql.Timestamp;

/**
 *
 * @author Benjamin
 */
public class HistoriqueCommande {
    private int id=0, idUtilisateur=0, idProduit=0;
    private float prix=0f;
    private Timestamp date;
    
    public HistoriqueCommande(int id, float prix, Timestamp date,
            int idUtilisateur, int idProduit)
    {
        this.id=id;
        this.prix=prix;
        this.date=date;
        this.idUtilisateur=idUtilisateur;
        this.idProduit=idProduit;
    }
    public HistoriqueCommande( float prix, Timestamp date,
            int idUtilisateur, int idProduit)
    {
        this.prix=prix;
        this.date=date;
        this.idUtilisateur=idUtilisateur;
        this.idProduit=idProduit;
    }
    public HistoriqueCommande(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    @Override
    public String toString()
    {
        String str = "ID : " + this.getId()+ "\n";
        str += "PRIX : " + this.getPrix()+ "\n";
        str += "DATE : " + this.getDate()+ "\n";
        str += "ID UTILISATEUR : " + this.getIdUtilisateur()+ "\n";
        str += "ID PRODUIT : " + this.getIdProduit()+ "\n";
        str += "\n.....................................\n";
        
        return str;
    }
    
}
