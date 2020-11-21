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
import java.util.ArrayList;

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
            if (obj.getPrixUnitaire() <= 0.0)
                throw new NullPointerException("ERROR : Prix Unitaire incorrect");
            if (obj.getStock() < 0)
                throw new NullPointerException("ERROR : Stock negatif");
            if (obj.getQuantiteUnLot() <= 0 && obj.getPrixUnLot() > 0.0)
                throw new NullPointerException("ERROR : Si un lot à un prix alors il faut avoir la quantité de produit par lot");
            if (obj.getQuantiteUnLot() > 0 && obj.getPrixUnLot() <= 0.0)
                throw new NullPointerException("ERROR : Si un lot à une quantité alors il faut avoir un prix de produit par lot");
            if (obj.getLienImage().isEmpty())
                throw new NullPointerException("ERROR : Lien image vide");

            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO produit ( nom, categorie, nom_fournisseur, prix_unitaire, "
                            + "stock, quantite_un_lot, prix_un_lot, promotion, "
                            + "promotion_active, lien_image ) "
                            + "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                    );

            prepare.setString(1, obj.getNom());
            prepare.setString(2, obj.getCategorie());
            prepare.setString(3, obj.getNomFournisseur());
            prepare.setDouble(4, obj.getPrixUnitaire());
            prepare.setInt(5, obj.getStock());
            prepare.setInt(6, obj.getQuantiteUnLot());
            prepare.setDouble(7, obj.getPrixUnLot());
            prepare.setDouble(8, obj.getPromotion());
            prepare.setBoolean(9, obj.isPromotionActive());
            prepare.setString(10, obj.getLienImage());

            prepare.executeUpdate();

            ResultSet result = prepare.getGeneratedKeys();
            if (!result.next())
                throw new SQLException("SQL ERROR : ID autoIncrement nulle");

            int id = result.getInt(1);

            return this.find(id);
        } catch (NullPointerException | SQLException e)
        {
            System.err.println("ProduitDAO create() " + e.getMessage());
            return new Produit();
        }
    }

    @Override
    public Produit find(int id)
    {
        Produit obj = new Produit();
        try
        {
            if (id == 0)
                throw new NullPointerException("ERROR : ID NULLE");

            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM utilisateur WHERE id = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            prepare.setInt(1, id);
            ResultSet result = prepare.executeQuery();

            if (result.next())
                obj = new Produit(id,
                        result.getString("nom"),
                        result.getString("categorie"),
                        result.getString("nom_fournisseur"),
                        result.getDouble("prix_unitaire"),
                        result.getInt("stock"),
                        result.getInt("quantite_un_lot"),
                        result.getDouble("prix_un_lot"),
                        result.getDouble("promotion"),
                        result.getBoolean("promotion_active"),
                        result.getString("lien_image")
                );

        } catch (SQLException | NullPointerException e)
        {
            System.err.println("ProduitDAO find() " + e.getMessage());
        }
        return obj;
    }

    @Override
    public boolean update(Produit obj)
    {
        try
        {
            if (obj.getId() == 0)
                throw new NullPointerException("ERROR : ID vide");
            if (obj.getNom().isEmpty())
                throw new NullPointerException("ERROR : Nom vide");
            if (obj.getCategorie().isEmpty())
                throw new NullPointerException("ERROR : Categorie vide");
            if (obj.getNomFournisseur().isEmpty())
                throw new NullPointerException("ERROR : Nom Fournisseur vide");
            if (obj.getPrixUnitaire() <= 0.0)
                throw new NullPointerException("ERROR : Prix Unitaire incorrect");
            if (obj.getStock() < 0)
                throw new NullPointerException("ERROR : Stock negatif");
            if (obj.getQuantiteUnLot() <= 0 && obj.getPrixUnLot() > 0.0)
                throw new NullPointerException("ERROR : Si un lot à un prix alors il faut avoir la quantité de produit par lot");
            if (obj.getQuantiteUnLot() > 0 && obj.getPrixUnLot() <= 0.0)
                throw new NullPointerException("ERROR : Si un lot à une quantité alors il faut avoir un prix de produit par lot");
            if (obj.getLienImage().isEmpty())
                throw new NullPointerException("ERROR : Lien image vide");

            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "UPDATE produit "
                            + "SET nom = ? ,"
                            + "categorie = ? , "
                            + "nom_fournisseur = ? , "
                            + "prix_unitaire = ? , "
                            + "stock = ? , "
                            + "quantite_un_lot = ? , "
                            + "prix_un_lot = ? , "
                            + "promotion = ? , "
                            + "promotion_active = ? , "
                            + "lien_image = ? "
                            + "WHERE id = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );

            prepare.setString(1, obj.getNom());
            prepare.setString(2, obj.getCategorie());
            prepare.setString(3, obj.getNomFournisseur());
            prepare.setDouble(4, obj.getPrixUnitaire());
            prepare.setInt(5, obj.getStock());
            prepare.setInt(6, obj.getQuantiteUnLot());
            prepare.setDouble(7, obj.getPrixUnLot());
            prepare.setDouble(8, obj.getPromotion());
            prepare.setBoolean(9, obj.isPromotionActive());
            prepare.setString(10, obj.getLienImage());
            prepare.setInt(11, obj.getId());

            prepare.executeUpdate();

            return this.find(obj.getId()).getId() != 0;// false / true
        } catch (SQLException | NullPointerException e)
        {
            System.err.println("ProduitDAO update() " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Produit obj)
    {
        try
        {
            if (obj.getId() == 0)
                throw new NullPointerException("ERROR : ID nulle");

            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "DELETE FROM produit WHERE id = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            prepare.setInt(1, obj.getId());
            prepare.executeUpdate();

            return this.find(obj.getId()).getId() == 0;//true / false
        } catch (SQLException | NullPointerException e)
        {
            System.err.println("ProduitDAO delete() " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Produit> getProduits()
    {
        ArrayList<Produit> produits = new ArrayList<>();
        try
        {

            ResultSet result = this.connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE)
                    .executeQuery("SELECT * FROM produit"
                    );

            while (result.next())
            {
                Produit obj = new Produit(
                        result.getInt("id"),
                        result.getString("nom"),
                        result.getString("categorie"),
                        result.getString("nom_fournisseur"),
                        result.getDouble("prix_unitaire"),
                        result.getInt("stock"),
                        result.getInt("quantite_un_lot"),
                        result.getDouble("prix_un_lot"),
                        result.getDouble("promotion"),
                        result.getBoolean("promotion_active"),
                        result.getString("lien_image")
                );
                produits.add(obj);
            }

        } catch (SQLException e)
        {
            System.err.println("ProduitDAO get() " + e.getMessage());
        }
        return produits;
    }

    public ArrayList<Produit> getProduits(String categorie)
    {
        ArrayList<Produit> produits = new ArrayList<>();
        try
        {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM produit WHERE categorie = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            prepare.setString(1, categorie);
            ResultSet result = prepare.executeQuery();

            while (result.next())
            {
                Produit obj = new Produit(
                        result.getInt("id"),
                        result.getString("nom"),
                        categorie,
                        result.getString("nom_fournisseur"),
                        result.getDouble("prix_unitaire"),
                        result.getInt("stock"),
                        result.getInt("quantite_un_lot"),
                        result.getDouble("prix_un_lot"),
                        result.getDouble("promotion"),
                        result.getBoolean("promotion_active"),
                        result.getString("lien_image")
                );
                produits.add(obj);
            }

        } catch (SQLException | NullPointerException e)
        {
            System.err.println("ProduitDAO find() " + e.getMessage());
        }
        return produits;
    }
    
    public boolean stockSuffisant(Produit obj, int quantite)
    {
        try
        {
            if (obj.getId() == 0)
                throw new NullPointerException("ERROR : ID nulle");
            if (quantite <= 0)
                throw new NullPointerException("ERROR : Quantite incorrecte");

            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "SELECT stock FROM produit WHERE id = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );

            prepare.setInt(1, obj.getId());

            ResultSet result = prepare.executeQuery();
            if (!result.next())
                throw new SQLException("SQL ERROR : PAS DE RESULTAT");

            int stock = result.getInt(1);

            return quantite >= stock;
        } catch (SQLException | NullPointerException e)
        {
            System.err.println("ProduitDAO stockSuffisant() " + e.getMessage());
            return false;
        }
    }

}
