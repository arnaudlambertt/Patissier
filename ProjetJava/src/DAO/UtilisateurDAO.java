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
import MODEL.Utilisateur;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author Benjamin
 */
public class UtilisateurDAO extends DAO<Utilisateur, String>
{

    public UtilisateurDAO()
    {
        this.className = "UtilisateurDAO";
    }

    @Override
    public Utilisateur create(Utilisateur obj, String motDePasse)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;

        try
        {
            if (obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (motDePasse == null)
                throw new NullPointerException("ERREUR: Parametre 2 nul");
            if (obj.getNom().isEmpty())
                throw new NullPointerException("ERREUR: Nom vide");
            if (obj.getPrenom().isEmpty())
                throw new NullPointerException("ERREUR: Prenom vide");
            if (obj.getEmail().isEmpty())
                throw new NullPointerException("ERREUR: Email vide");
            if (motDePasse.isEmpty())
                throw new NullPointerException("ERREUR: Mot de passe vide");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe) "
                            + "VALUES( ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                    );

            prepare.setString(1, obj.getNom());
            prepare.setString(2, obj.getPrenom());
            prepare.setString(3, obj.getEmail());
            prepare.setString(4, motDePasse);

            prepare.executeUpdate();

            result = prepare.getGeneratedKeys();
            if (!result.next())
                throw new SQLException("SQL ERREUR: ID autoIncrement nulle");

            int id = result.getInt(1);

            return this.find(id);

        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " create() " + e.getMessage());
            return new Utilisateur();
        } finally
        {
            close(result);
            close(prepare);
        }
    }

    @Override
    public Utilisateur find(int id)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;

        try
        {
            if (id == 0)
                throw new NullPointerException("ERREUR: Param 1 nul");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM utilisateur WHERE id = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            prepare.setInt(1, id);

            result = prepare.executeQuery();

            if (result.next())
                return new Utilisateur(id,
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("role")
                );
            else
                return new Utilisateur();

        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " find() " + e.getMessage());
            return new Utilisateur();
        } finally
        {
            close(result);
            close(prepare);
        }
    }

    @Override
    public boolean update(Utilisateur obj)
    {
        PreparedStatement prepare = null;

        try
        {
            if (obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (obj.getId() == 0)
                throw new NullPointerException("ERREUR: ID nulle");
            if (obj.getNom().isEmpty())
                throw new NullPointerException("ERREUR: Nom vide");
            if (obj.getPrenom().isEmpty())
                throw new NullPointerException("ERREUR: Prenom vide");
            if (obj.getEmail().isEmpty())
                throw new NullPointerException("ERREUR: Mail vide");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "UPDATE utilisateur "
                            + "SET nom = ? , "
                            + "prenom = ? , "
                            + "email = ? "
                            + "WHERE id = ?"
                    );

            prepare.setString(1, obj.getNom());
            prepare.setString(2, obj.getPrenom());
            prepare.setString(3, obj.getEmail());
            prepare.setInt(4, obj.getId());

            prepare.executeUpdate();

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
    public boolean delete(Utilisateur obj)
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
                            "DELETE FROM utilisateur WHERE id = ? "
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

    public Utilisateur connexion(String email, String motDePasse)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;

        try
        {
            if (email == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (motDePasse == null)
                throw new NullPointerException("ERREUR: Parametre 2 nul");
            if (email.isEmpty())
                throw new NullPointerException("ERREUR: Email vide");
            if (motDePasse.isEmpty())
                throw new NullPointerException("ERREUR: Mot de passe vide");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );

            prepare.setString(1, email);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(motDePasse.getBytes(StandardCharsets.UTF_8));
            motDePasse = new String(Hex.encode(hash));

            prepare.setString(2, motDePasse);

            result = prepare.executeQuery();

            if (result.next())
                return this.find(result.getInt(1));
            else
                return new Utilisateur();

        } catch (NullPointerException | SQLException | NoSuchAlgorithmException e)
        {
            System.err.println(className + " connexion() " + e.getMessage());
            return new Utilisateur();
        } finally
        {
            close(result);
            close(prepare);
        }
    }

    public boolean emailExistant(String email)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;

        try
        {
            if (email == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (email.isEmpty())
                throw new NullPointerException("ERREUR: Email vide");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "SELECT email FROM utilisateur WHERE email = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );

            prepare.setString(1, email);

            result = prepare.executeQuery();

            return result.next();//true / false

        } catch (NullPointerException | SQLException e)
        {
            System.err.println(className + " mailExiste() " + e.getMessage());
            return true;
        } finally
        {
            close(result);
            close(prepare);
        }
    }

    public boolean modifierMotDePasse(Utilisateur obj, String ancienMotDePasse, String nouveauMotDePasse)
    {
        PreparedStatement prepare = null;

        try
        {
            if (obj == null)
                throw new NullPointerException("ERREUR: Parametre 1 nul");
            if (ancienMotDePasse == null)
                throw new NullPointerException("ERREUR: Parametre 2 nul");
            if (nouveauMotDePasse == null)
                throw new NullPointerException("ERREUR: Parametre 3 nul");

            if (connexion(obj.getEmail(), ancienMotDePasse).getId() == 0)
                return false;

            if (obj.getId() == 0)
                throw new NullPointerException("ERREUR: ID nulle");
            if (nouveauMotDePasse.isEmpty())
                throw new NullPointerException("ERREUR: Nouveau mot de passe vide");

            this.open();

            prepare = this.connect
                    .prepareStatement(
                            "UPDATE utilisateur "
                            + "SET mot_de_passe = ? "
                            + "WHERE id = ?"
                    );

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(nouveauMotDePasse.getBytes(StandardCharsets.UTF_8));
            nouveauMotDePasse = new String(Hex.encode(hash));

            prepare.setString(1, nouveauMotDePasse);
            prepare.setInt(2, obj.getId());

            prepare.executeUpdate();

            return true;

        } catch (NullPointerException | SQLException | NoSuchAlgorithmException e)
        {
            System.err.println(className + " modifierMotDePasse() " + e.getMessage());
            return false;
        } finally
        {
            close(prepare);
        }
    }

}
