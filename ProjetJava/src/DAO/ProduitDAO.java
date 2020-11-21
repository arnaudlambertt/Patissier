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
public class ProduitDAO extends DAO<Produit, Object>
{

    public ProduitDAO()
    {
        this.className = "ProduitDAO";
    }

    @Deprecated
    @Override
    public Produit create(Produit obj, Object nullObject)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;

        try
        {
            if(obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (obj.getNom().isEmpty())
                throw new NullPointerException("ERREUR: Nom vide");
            if (obj.getCategorie().isEmpty())
                throw new NullPointerException("ERREUR: Categorie vide");
            if (obj.getNomFournisseur().isEmpty())
                throw new NullPointerException("ERREUR: Nom Fournisseur vide");
            if (obj.getPrixUnitaire() <= 0.0)
                throw new NullPointerException("ERREUR: Prix Unitaire incorrect");
            if (obj.getStock() < 0)
                throw new NullPointerException("ERREUR: Stock negatif");
            if (obj.getQuantiteUnLot() <= 0 && obj.getPrixUnLot() > 0.0)
                throw new NullPointerException("ERREUR: Si un lot à un prix alors il faut avoir la quantité de produit par lot");
            if (obj.getQuantiteUnLot() > 0 && obj.getPrixUnLot() <= 0.0)
                throw new NullPointerException("ERREUR: Si un lot à une quantité alors il faut avoir un prix de produit par lot");
            if (obj.getPromotion() < 0.0)
                throw new NullPointerException("ERREUR: Promotion negative");
            if (obj.getLienImage().isEmpty())
                throw new NullPointerException("ERREUR: Lien image vide");

            if (connect == null)
                throw new NullPointerException("ERREUR: Pas de connexion à la BDD");

            prepare = this.connect
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

            result = prepare.getGeneratedKeys();
            if (!result.next())
                throw new SQLException("SQL ERREUR: ID autoIncrement nulle");

            int id = result.getInt(1);
            
            return this.find(id);
            
        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " create() " + e.getMessage());
            return new Produit();
        } finally
        {
            close(result);
            close(prepare);
        }
    }
    
    public Produit create(Produit obj)
    {
        return this.create(obj, null);
    }

    @Override
    public Produit find(int id)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;
        
        try
        {
            if (id == 0)
                throw new NullPointerException("ERREUR: Parametre 1 ID nulle");

            if (connect == null)
                throw new NullPointerException("ERREUR: Pas de connexion à la BDD");

            prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM produit WHERE id = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            prepare.setInt(1, id);
            result = prepare.executeQuery();

            if (result.next())
                return new Produit(id,
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
            else
                return new Produit();
            
        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " find() " + e.getMessage());
            return new Produit();
        } finally
        {
            close(result);
            close(prepare);
        }
    }

    @Override
    public boolean update(Produit obj)
    {
        PreparedStatement prepare = null;

        try
        {
            if(obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (obj.getId() == 0)
                throw new NullPointerException("ERREUR: ID vide");
            if (obj.getNom().isEmpty())
                throw new NullPointerException("ERREUR: Nom vide");
            if (obj.getCategorie().isEmpty())
                throw new NullPointerException("ERREUR: Categorie vide");
            if (obj.getNomFournisseur().isEmpty())
                throw new NullPointerException("ERREUR: Nom Fournisseur vide");
            if (obj.getPrixUnitaire() <= 0.0)
                throw new NullPointerException("ERREUR: Prix Unitaire incorrect");
            if (obj.getStock() < 0)
                throw new NullPointerException("ERREUR: Stock negatif");
            if (obj.getQuantiteUnLot() <= 0 && obj.getPrixUnLot() > 0.0)
                throw new NullPointerException("ERREUR: Si un lot à un prix alors il faut avoir la quantité de produit par lot");
            if (obj.getQuantiteUnLot() > 0 && obj.getPrixUnLot() <= 0.0)
                throw new NullPointerException("ERREUR: Si un lot à une quantité alors il faut avoir un prix de produit par lot");
            if (obj.getPromotion() < 0.0)
                throw new NullPointerException("ERREUR: Promotion negative");
            if (obj.getLienImage().isEmpty())
                throw new NullPointerException("ERREUR: Lien image vide");

            if (connect == null)
                throw new NullPointerException("ERREUR: Pas de connexion à la BDD");

            prepare = this.connect
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
                            + "WHERE id = ?"
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

            return true;
            
        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " update() " + e.getMessage());
            return false;
        } finally
        {
            close(prepare);
        }
    }

    @Override
    public boolean delete(Produit obj)
    {
        PreparedStatement prepare = null;
        
        try
        {
            if(obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (obj.getId() == 0)
                throw new NullPointerException("ERREUR: ID nulle");

            if (connect == null)
                throw new NullPointerException("ERREUR: Pas de connexion à la BDD");

            prepare = this.connect
                    .prepareStatement(
                            "DELETE FROM produit WHERE id = ? "
                    );
            prepare.setInt(1, obj.getId());
            prepare.executeUpdate();

            return this.find(obj.getId()).getId() == 0;//true / false
            
        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " delete() " + e.getMessage());
            return false;
        } finally
        {
            close(prepare);
        }
    }

    public ArrayList<Produit> getProduits()
    {
        ResultSet result = null;

        try
        {

            if (connect == null)
                throw new NullPointerException("ERREUR: Pas de connexion à la BDD");

            result = this.connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE)
                    .executeQuery("SELECT * FROM produit"
                    );

            ArrayList<Produit> produits = new ArrayList<>();
            
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

            return produits;
            
        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " getProduits() " + e.getMessage());
            return new ArrayList<>();
        } finally
        {
            close(result);
        }
    }

    public ArrayList<Produit> getProduits(String categorie)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;
        
        try
        {
            if(categorie == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (categorie.isEmpty())
                throw new NullPointerException("ERREUR: Categorie vide");

            if (connect == null)
                throw new NullPointerException("ERREUR: Pas de connexion à la BDD");

            prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM produit WHERE categorie = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            prepare.setString(1, categorie);
            result = prepare.executeQuery();
            
            ArrayList<Produit> produits = new ArrayList<>();
            
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
            
            return produits;
            
        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " getProduits(categorie) " + e.getMessage());
            return new ArrayList<>();
        } finally
        {
            close(result);
            close(prepare);
        }
    }

    public boolean stockSuffisant(Produit obj, int quantiteVoulue)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;

        try
        {
            if(obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (obj.getId() == 0)
                throw new NullPointerException("ERREUR: ID nulle");
            if (quantiteVoulue <= 0)
                throw new NullPointerException("ERREUR: Parametre 2 quantite incorrecte");

            if (connect == null)
                throw new NullPointerException("ERREUR: Pas de connexion à la BDD");

            prepare = this.connect
                    .prepareStatement(
                            "SELECT stock FROM produit WHERE id = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );

            prepare.setInt(1, obj.getId());

            result = prepare.executeQuery();
            if (!result.next())
                throw new SQLException("SQL ERREUR: PAS DE RESULTAT");

            int stock = result.getInt(1);

            return stock >= quantiteVoulue;
            
        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " stockSuffisant() " + e.getMessage());
            return true;
        } finally
        {
            close(result);
            close(prepare);
        }
    }
}
