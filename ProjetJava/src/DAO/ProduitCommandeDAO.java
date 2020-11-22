/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Produit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.util.Pair;

/**
 *
 * @author Arnaud
 */
public class ProduitCommandeDAO extends DAO<Pair<Produit, Integer>, Integer>
{

    //////////////NORMALEMENT PROTECTED///////////////////
    public ProduitCommandeDAO()
    {
        this.className = "ProduitCommandeDAO";
    }

    /**
     * Permet de créer une entrée dans la base de donnée par rapport à un Produit, une quantité et une id de commmande
     *
     * @param obj une paire (produit,quantité)
     * @param idCommande id de commande
     * @return
     */
    @Override
    public Pair<Produit, Integer> create(Pair<Produit, Integer> obj, Integer idCommande)
    {
        PreparedStatement prepare = null;

        try
        {
            if (obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (idCommande == null)
                throw new NullPointerException("ERREUR: Parametre 2 nul");
            if (obj.getValue() == null)
                throw new NullPointerException("ERREUR: Quantite nulle");

            Produit produit = obj.getKey();
            int quantite = obj.getValue();

            if (produit == null)
                throw new NullPointerException("ERREUR: Produit nul");
            if (quantite <= 0)
                throw new NullPointerException("ERREUR: Quantite incorrecte");
            if (produit.getId() == 0)
                throw new NullPointerException("ERREUR: id produit nulle");
            if (idCommande == 0)
                throw new NullPointerException("ERREUR: ID commande nulle");
            if (produit.getPrixUnitaire() <= 0.0)
                throw new NullPointerException("ERREUR: Prix Unitaire incorrect");
            if (produit.getQuantiteUnLot() <= 0 && produit.getPrixUnLot() > 0.0)
                throw new NullPointerException("ERREUR: Si un lot a un prix alors il faut avoir la quantité de produit par lot");
            if (produit.getQuantiteUnLot() > 0 && produit.getPrixUnLot() <= 0.0)
                throw new NullPointerException("ERREUR: Si un lot a une quantité alors il faut avoir un prix de produit par lot");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO produit_commande "
                            + "( id_commande, id_produit, quantite, prix_unitaire, "
                            + "quantite_un_lot, prix_un_lot) "
                            + "VALUES( ?, ?, ?, ?, ?, ?)"
                    );

            prepare.setInt(1, idCommande);
            prepare.setInt(2, produit.getId());
            prepare.setInt(3, quantite);
            prepare.setDouble(4, produit.getPrixUnitaire()
                    * (1 - (produit.isPromotionActive() ? produit.getPromotion() : 0)));
            prepare.setInt(5, produit.getQuantiteUnLot());
            prepare.setDouble(6, produit.getPrixUnLot()
                    * (1 - (produit.isPromotionActive() ? produit.getPromotion() : 0)));

            prepare.executeUpdate();

            return this.find(produit.getId(), idCommande);

        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " create() " + e.getMessage());
            return new Pair<>(new Produit(), 0);
        } finally
        {
            close(prepare);
        }
    }

    @Deprecated
    @Override
    public Pair<Produit, Integer> find(int id)
    {
        throw new NoSuchMethodError("Undefined method.");
    }

    /**
     * Permet de récupérer une paire (produit,quantité) via sa clé primaire (idCommande,idProduit)
     *
     * @param idCommande
     * @param idProduit
     * @return
     */
    public Pair<Produit, Integer> find(int idProduit, int idCommande)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;

        try
        {
            if (idProduit == 0)
                throw new NullPointerException("ERREUR: Parametre 1 ID nulle");
            if (idCommande == 0)            
                throw new NullPointerException("ERREUR: Parametre 2 ID nulle");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM produit_commande "
                            + "WHERE id_commande = ? AND id_produit = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            prepare.setInt(1, idCommande);
            prepare.setInt(2, idProduit);

            result = prepare.executeQuery();

            if (result.next())
            {
                ProduitDAO dao = new ProduitDAO(); //Surtout ne pas close
                Produit produit = dao.find(idProduit);
                produit.setId(idProduit);
                produit.setPrixUnitaire(result.getDouble("prix_unitaire"));
                produit.setQuantiteUnLot(result.getInt("quantite_un_lot"));
                produit.setPrixUnLot(result.getDouble("prix_un_lot"));
                produit.setPromotion(0.0);
                produit.setPromotionActive(false);

                return new Pair<>(produit, result.getInt("quantite"));
            } else
                return new Pair<>(new Produit(), 0);

        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " find() " + e.getMessage());
            return new Pair<>(new Produit(), 0);
        } finally
        {
            close(result);
            close(prepare);
        }
    }

    @Deprecated
    @Override
    public boolean update(Pair<Produit, Integer> obj)
    {
        throw new NoSuchMethodError("Undefined method.");
    }

    /**
     * Permet de mettre à jour les données d'un produit_commande
     *
     * @param obj une pair (produit,idCommande)
     * @param quantite
     * @return
     */
    public boolean update(Pair<Produit, Integer> obj, int quantite)
    {
        PreparedStatement prepare = null;

        try
        {
            if (obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (obj.getValue() == null)
                throw new NullPointerException("ERREUR: ID commande nulle");

            Produit produit = obj.getKey();
            int idCommande = obj.getValue();

            if (produit == null)
                throw new NullPointerException("ERREUR: Produit nul");
            if (produit.getId() == 0)
                throw new NullPointerException("ERREUR: id produit nulle");
            if (quantite <= 0)
                throw new NullPointerException("ERREUR: Quantite incorrecte");
            if (produit.getPrixUnitaire() <= 0.0)
                throw new NullPointerException("ERREUR: Prix Unitaire incorrect");
            if (produit.getQuantiteUnLot() <= 0 && produit.getPrixUnLot() > 0.0)
                throw new NullPointerException("ERREUR: Si un lot a un prix alors il faut avoir la quantité de produit par lot");
            if (produit.getQuantiteUnLot() > 0 && produit.getPrixUnLot() <= 0.0)
                throw new NullPointerException("ERREUR: Si un lot a une quantité alors il faut avoir un prix de produit par lot");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "UPDATE produit_commande "
                            + "SET quantite = ? ,"
                            + "prix_unitaire = ? , "
                            + "quantite_un_lot = ? , "
                            + "prix_un_lot = ? "
                            + "WHERE id_commande = ? AND id_produit = ?"
                    );

            prepare.setInt(1, quantite);
            prepare.setDouble(2, produit.getPrixUnitaire());
            prepare.setInt(3, produit.getQuantiteUnLot());
            prepare.setDouble(4, produit.getPrixUnLot());
            prepare.setInt(5, idCommande);
            prepare.setInt(6, produit.getId());

            prepare.executeUpdate();

            return this.find(produit.getId(), idCommande).getValue() != 0;

        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " update() " + e.getMessage());
            return false;
        } finally
        {
            close(prepare);
        }
    }

    /**
     * Permet une suppression d'une entrée de produit_commande dans la base
     *
     * @param obj une pair (produit,idCommande)
     * @return
     */
    @Override
    public boolean delete(Pair<Produit, Integer> obj)
    {
        PreparedStatement prepare = null;

        try
        {
            if (obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (obj.getKey() == null)
                throw new NullPointerException("ERREUR: Produit nul");
            if (obj.getValue() == null || obj.getValue() == 0)
                throw new NullPointerException("ERREUR: ID commande nulle");
            if (obj.getKey().getId() == 0)
                throw new NullPointerException("ERREUR: ID produit nulle");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "DELETE FROM produit_commande "
                            + "WHERE id_commande = ? AND id_produit = ?"
                    );
            prepare.setInt(1, obj.getValue());
            prepare.setInt(2, obj.getKey().getId());

            prepare.executeUpdate();

            return this.find(obj.getKey().getId(), obj.getValue()).getValue() == 0;

        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " delete() " + e.getMessage());
            return false;
        } finally
        {
            close(prepare);
        }
    }

    public HashMap<Produit, Integer> getProduitsCommande(int idCommande)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;

        try
        {
            if (idCommande == 0)
                throw new NullPointerException("ERREUR: Parametre 1 nul");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM produit_commande WHERE id_commande = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            prepare.setInt(1, idCommande);

            result = prepare.executeQuery();

            HashMap<Produit, Integer> produitsCommande = new HashMap<>();

            while (result.next())
            {
                ProduitDAO dao = new ProduitDAO(); //
                Produit produit = dao.find(result.getInt("id_produit"));
                produit.setId(result.getInt("id_produit"));
                produit.setPrixUnitaire(result.getDouble("prix_unitaire"));
                produit.setQuantiteUnLot(result.getInt("quantite_un_lot"));
                produit.setPrixUnLot(result.getDouble("prix_un_lot"));
                produit.setPromotion(0.0);
                produit.setPromotionActive(false);
                
                produitsCommande.put(produit, result.getInt("quantite"));
            }

            return produitsCommande;

        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " getProduitsCommande() " + e.getMessage());
            return new HashMap<>();
        } finally
        {
            close(result);
            close(prepare);
        }
    }

}
