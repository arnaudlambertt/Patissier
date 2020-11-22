/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.sql.Timestamp;
import java.util.HashMap;
import javafx.util.Pair;

/**
 *
 * @author Benjamin
 */
public class Commande
{

    private int id;
    private Timestamp horodateur;
    private String adresse;
    private double prix;
    private boolean isLivre;
    private HashMap<Produit, Integer> produitsCommande;

    public Commande()
    {
        this.id = 0;
        this.horodateur = new Timestamp(0);
        this.adresse = "";
        this.prix = 0.0;
        this.isLivre = false;
        this.produitsCommande = new HashMap<>();
    }

    public Commande(int id, Timestamp horodateur, String adresse, double prix, boolean isLivre)
    {
        this.id = id;
        this.horodateur = horodateur;
        this.adresse = adresse;
        this.prix = prix;
        this.isLivre = isLivre;
        this.produitsCommande = new HashMap<>();
    }

    public Commande(String adresse)
    {
        this.id = 0;
        this.horodateur = new Timestamp(0);
        this.adresse = adresse;
        this.prix = 0.0;
        this.isLivre = false;
        this.produitsCommande = new HashMap<>();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Timestamp getHorodateur()
    {
        return horodateur;
    }

    public void setHorodateur(Timestamp date)
    {
        this.horodateur = date;
    }

    public String getAdresse()
    {
        return adresse;
    }

    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    public double getPrix()
    {
        return prix;
    }

    public void setIsLivre(boolean isLivre)
    {
        this.isLivre = isLivre;
    }

    public boolean getIsLivre()
    {
        return isLivre;
    }

    public HashMap<Produit, Integer> getProduitsCommande()
    {
        return produitsCommande;
    }

    public void setProduitsCommande(HashMap<Produit, Integer> produitsCommande)
    {
        this.produitsCommande = produitsCommande;
        calculerPrix();
    }

    public void addProduitsCommande(Pair<Produit,Integer> produitCommande)
    {
        this.produitsCommande.put(produitCommande.getKey(),produitCommande.getValue());
        calculerPrix();
    }

    public void calculerPrix()
    {
        this.prix = 0.0;
        produitsCommande.entrySet().stream().map((entry) ->
        {
            double temp;

            if (entry.getKey().getQuantiteUnLot() > 0)
                temp = (entry.getValue() / entry.getKey().getQuantiteUnLot()) * entry.getKey().getPrixUnLot()
                        + (entry.getValue() % entry.getKey().getQuantiteUnLot()) * entry.getKey().getPrixUnitaire();
            else
                temp = entry.getKey().getPrixUnitaire() * entry.getValue();

            if (entry.getKey().isPromotionActive())
                temp *= (1 - entry.getKey().getPromotion());

            return temp;
        }).forEachOrdered((temp) ->
        {
            this.prix += temp;
        });

        //Math.round( this.getPrix() * 100.0 ) / 100.0 pour arrondir à 2 décimales
    }

    @Override
    public String toString()
    {
        String str = "ID : " + this.getId() + "\n";
        str = produitsCommande.entrySet().stream().map((entry) -> entry.getKey().getNom() + " " + entry.getValue() + "\n").reduce(str, String::concat);
        str += "PRIX : " + this.getPrix() + "\n";
        str += "\n.....................................\n";

        return str;
    }

}
