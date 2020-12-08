/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.util.Pair;
import MODEL.Commande;
import MODEL.Produit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Benjamin
 */
public class CommandeDAO extends DAO<Commande, Utilisateur>
{

    public CommandeDAO()
    {
        this.className = "CommandeDAO";
    }

    @Override
    public Commande create(Commande obj, Utilisateur utilisateur)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;

        try
        {
            if (obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (utilisateur == null)
                throw new NullPointerException("ERREUR: Parametre 2 nul");
            if (utilisateur.getId() == 0)
                throw new NullPointerException("ERREUR: ID utilisateur nulle");
            if (obj.getAdresse().isEmpty())
                throw new NullPointerException("ERREUR: Adresse vide");
            if (obj.getPrix() <= 0.0)
                throw new NullPointerException("ERREUR: Prix incorrect");
            if (obj.getProduitsCommande().isEmpty())
                throw new NullPointerException("ERREUR: Pas de produits");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO commande ( id_utilisateur, "
                            + "adresse, prix) "
                            + "VALUES( ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                    );

            prepare.setInt(1, utilisateur.getId());
            prepare.setString(2, obj.getAdresse());
            prepare.setDouble(3, obj.getPrix());

            prepare.executeUpdate();

            result = prepare.getGeneratedKeys();
            if (!result.next())
                throw new SQLException("SQL ERREUR: ID autoIncrement nulle");

            int id = result.getInt(1);

            ProduitCommandeDAO dao = new ProduitCommandeDAO();
            obj.getProduitsCommande().forEach((k, v) -> dao.create(new Pair<>(k, v), id));

            return this.find(id);

        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " create() " + e.getMessage());
            return new Commande();
        } finally
        {
            close(result);
            close(prepare);
        }
    }

    @Override
    public Commande find(int id)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;

        try
        {
            if (id == 0)
                throw new NullPointerException("ERREUR: Parametre 1 ID nulle");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM commande WHERE id = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            prepare.setInt(1, id);

            result = prepare.executeQuery();

            if (result.next())
            {
                ProduitCommandeDAO dao = new ProduitCommandeDAO();
                Commande commande = new Commande(id,
                        result.getTimestamp("horodateur"),
                        result.getString("adresse"),
                        result.getDouble("prix"),
                        result.getBoolean("livre"),
                        dao.getProduitsCommande(id)
                );
                if (!commande.getProduitsCommande().isEmpty())
                    return commande;
                else
                    return new Commande();
            } else
                return new Commande();

        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " find() " + e.getMessage());
            return new Commande();
        } finally
        {
            close(result);
            close(prepare);
        }
    }

    @Override
    public boolean update(Commande obj)
    {
        PreparedStatement prepare = null;

        try
        {
            if (obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (obj.getId() == 0)
                throw new NullPointerException("ERREUR: ID vide");
            if (obj.getAdresse().isEmpty())
                throw new NullPointerException("ERREUR: Adresse vide");
            if (obj.getPrix() <= 0.0)
                throw new NullPointerException("ERREUR: Prix incorrect");
            if (obj.getProduitsCommande().isEmpty())
                throw new NullPointerException("ERREUR: Pas de produits");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "UPDATE commande "
                            + "SET adresse = ? , "
                            + "prix = ? , "
                            + "livre = ? "
                            + "WHERE id = ?"
                    );

            prepare.setString(1, obj.getAdresse());
            prepare.setDouble(2, obj.getPrix());
            prepare.setBoolean(3, obj.isLivre());
            prepare.setInt(4, obj.getId());

            prepare.executeUpdate();

            ProduitCommandeDAO dao = new ProduitCommandeDAO();
            HashMap<Produit, Integer> bbdProduitsCommande = dao.getProduitsCommande(obj.getId());
            for (Map.Entry<Produit, Integer> entry : bbdProduitsCommande.entrySet())
                if (obj.getProduitsCommande().containsKey(entry.getKey()))
                {
                    if (!dao.update(new Pair<>(entry.getKey(), obj.getId()), obj.getProduitsCommande().get(entry.getKey())))
                        throw new SQLException("ERREUR: Echec mise a jour produit_commande");
                } else if (!dao.delete(new Pair<>(entry.getKey(), obj.getId())))
                    throw new SQLException("ERREUR: Echec suppression produit_commande");
            obj.getProduitsCommande().entrySet().stream().filter((entry) -> (!bbdProduitsCommande.containsKey(entry.getKey()))).forEachOrdered((entry) ->
            {
                dao.create(new Pair<>(entry.getKey(), entry.getValue()), obj.getId());
            });

            return this.find(obj.getId()).getId() != 0;

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
    public boolean delete(Commande obj)
    {
        PreparedStatement prepare = null;

        try
        {
            if (obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (obj.getId() == 0)
                throw new NullPointerException("ERREUR: ID nulle");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "DELETE FROM commande WHERE id = ? "
                    );
            prepare.setInt(1, obj.getId());

            prepare.executeUpdate();

            ProduitCommandeDAO dao = new ProduitCommandeDAO();
            for (Map.Entry<Produit, Integer> entry : dao.getProduitsCommande(obj.getId()).entrySet())
                if (!dao.delete(new Pair<>(entry.getKey(), obj.getId())))
                    throw new SQLException("ERREUR: Echec suppression produit_commande");

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

    public ArrayList<Commande> getCommandes(int idUtilisateur)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;

        try
        {
            if (idUtilisateur == 0)
                throw new NullPointerException("ERREUR: Parametre 1 ID nulle");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM commande WHERE id_utilisateur = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            prepare.setInt(1, idUtilisateur);

            result = prepare.executeQuery();

            ArrayList<Commande> commandes = new ArrayList<>();

            while (result.next())
            {
                commandes.add(this.find(result.getInt("id")));
            }

            return commandes;

        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " getCommandes(idUtilisateur) " + e.getMessage());
            return new ArrayList<>();
        } finally
        {
            close(result);
            close(prepare);
        }
    }

    public ArrayList<Commande> getCommandes()
    {
        ResultSet result = null;

        try
        {
            this.open();

            result = this.connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE)
                    .executeQuery("SELECT * FROM commande"
                    );

            ArrayList<Commande> commandes = new ArrayList<>();

            while (result.next())
            {
                commandes.add(this.find(result.getInt("id")));
            }

            return commandes;

        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " getCommandes() " + e.getMessage());
            return new ArrayList<>();
        } finally
        {
            close(result);
        }
    }

    public boolean stockSuffisantCommande(Commande obj)
    {
        try
        {
            if(obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (obj.getProduitsCommande().isEmpty())
                throw new NullPointerException("ERREUR: Pas de produits");
            
            ProduitDAO dao = new ProduitDAO();
            return obj.getProduitsCommande().entrySet().stream().noneMatch((entry) -> (!dao.stockSuffisant(entry.getKey(), entry.getValue())));
        } catch (NullPointerException e)
        {
            System.err.println(className + " stockSuffisantCommande() " + e.getMessage());
            return false;
        }
    }

}
