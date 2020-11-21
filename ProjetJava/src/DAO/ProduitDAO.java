/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import MODEL.Produit;

/**
 *
 * @author Benjamin
 */
public class ProduitDAO extends DAO<Produit, String>
{

    @Override
    public Produit create(Produit obj, String test)
    {

        try
        {
            if (obj.getNom().isEmpty())
                throw new NullPointerException("ERROR : Nom vide");
            if (obj.getCategorie().isEmpty())
                throw new NullPointerException("ERROR : Categorie vide");
            if (obj.getNomFournisseur().isEmpty())
                throw new NullPointerException("ERROR : Nom Fournisseur vide");
            if (obj.getPrixUnitaire() <= 0)
                throw new NullPointerException("ERROR : Prix Unitaire vide");
            if (obj.getStock() <= 0)
                throw new NullPointerException("ERROR : Stock vide");
            if (obj.getQuantiteUnLot() <= 0 && obj.getPrixUnLot() > 0)
                throw new NullPointerException("ERROR : Si un lot à un prix alors il faut avoir la quantité de produit par lot");
            if (obj.getQuantiteUnLot() > 0 && obj.getPrixUnLot() <= 0)
                throw new NullPointerException("ERROR : Si un lot à une quantité alors il faut avoir un prix de produit par lot");
            if (obj.getLien().isEmpty())
                throw new NullPointerException("ERROR : Lien vide");

            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO produit ( nom, categorie, nom_fournisseur, prix_unitaire, "
                            + "stock, quantite_un_lot, prix_un_lot, promotion, "
                            + "promotion_active, lien ) "
                            + "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                    );

            //Verification des valeurs avant ajout en base de donnée
            //On preparer les valeur pour l'instruction INSERT
            prepare.setString(1, obj.getNom());
            prepare.setString(2, obj.getCategorie());
            prepare.setString(3, obj.getNomFournisseur());
            prepare.setFloat(4, obj.getPrixUnitaire());
            prepare.setInt(5, obj.getStock());
            prepare.setInt(6, obj.getQuantiteUnLot());
            prepare.setFloat(7, obj.getPrixUnLot());
            prepare.setFloat(8, obj.getPromotion());
            prepare.setBoolean(9, obj.isPromotionActive());
            prepare.setString(10, obj.getLien());

            //On fait l'instruction INSERT
            prepare.executeUpdate();

            ResultSet result = prepare.getGeneratedKeys();
     
            if (!result.next())
                throw new SQLException("SQL ERROR : ID autoIncrement nulle");
                        
            int id = result.getInt(1);
            obj = this.find(id);

            //}
        } catch (NullPointerException | SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return obj;
    }//end create

    @Override
    public Produit find(int id)
    {
        Produit dev = new Produit();
        try
        {
            ResultSet result = this.connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY
                    ).executeQuery(
                            "SELECT * FROM produit WHERE id = " + id
                    );
            if (result.first())
                dev = new Produit(id,
                        result.getString("nom"),
                        result.getString("categorie"),
                        result.getString("nom_fournisseur"),
                        result.getFloat("prix_unitaire"),
                        result.getInt("quantite_en_stock"),
                        result.getInt("quantite_un_lot"),
                        result.getFloat("prix_un_lot"),
                        result.getFloat("promotion"),
                        result.getBoolean("promotion_active"),
                        result.getString("lien")
                );

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return dev;
    }//end find

    @Override
    public boolean update(Produit obj)
    {
        obj = new Produit();
        try
        {
            this.connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "UPDATE produit SET nom = '" + obj.getNom() + "',"
                            + " categorie = '" + obj.getCategorie() + "'"
                            + " nom_fournisseur = '" + obj.getNomFournisseur() + "'"
                            + " prix_unitaire = '" + obj.getPrixUnitaire() + "'"
                            + " quantite_en_stock = '" + obj.getStock() + "'"
                            + " quantite_un_lot = '" + obj.getQuantiteUnLot() + "'"
                            + " promotion = '" + obj.getPromotion() + "'"
                            + " promotion_active = '" + obj.isPromotionActive() + "'"
                            + " lien = '" + obj.getLien() + "'"
                            + " WHERE id = " + obj.getId()
                    );

            obj = this.find(obj.getId());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return obj.getId() != 0;
    }//end update

    @Override
    public void delete(Produit obj)
    {
        try
        {

            this.connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "DELETE FROM produit WHERE id = " + obj.getId()
                    );

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }//end delete
}//end classe

