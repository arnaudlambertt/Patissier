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
        this.className = "UtilsateurDAO";
    }

    @Override
    public Utilisateur create(Utilisateur obj, String motDePasse)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;
        Utilisateur utilisateur = new Utilisateur();

        try
        {
            if (obj.getNom().isEmpty())
                throw new NullPointerException("ERROR : Nom vide");
            if (obj.getPrenom().isEmpty())
                throw new NullPointerException("ERROR : Prenom vide");
            if (obj.getEmail().isEmpty())
                throw new NullPointerException("ERROR : Email vide");
            if (motDePasse.isEmpty())
                throw new NullPointerException("ERROR : Mot de passe vide");

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
                throw new SQLException("SQL ERROR : ID autoIncrement nulle");

            int id = result.getInt(1);

            utilisateur = this.find(id);
        } catch (SQLException | NullPointerException e)
        {
            System.err.println(className + " create() " + e.getMessage());
        } finally
        {
            close(result);
            close(prepare);
        }
        return utilisateur;
    }

    @Override
    public Utilisateur find(int id)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;
        Utilisateur utilisateur = new Utilisateur();

        try
        {
            if (id == 0)
                throw new NullPointerException("ERROR : ID NULLE");

            prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM utilisateur WHERE id = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            prepare.setInt(1, id);
            result = prepare.executeQuery();

            if (result.next())
                utilisateur = new Utilisateur(id,
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("role")
                );

        } catch (SQLException | NullPointerException e)
        {
            System.err.println(className + " find() " + e.getMessage());
        } finally
        {
            close(result);
            close(prepare);
        }
        return utilisateur;
    }

    @Override
    public boolean update(Utilisateur obj)
    {
        PreparedStatement prepare = null;
        boolean bool = false;
        try
        {
            if (obj.getId() == 0)
                throw new NullPointerException("ERROR : ID nulle");
            if (obj.getNom().isEmpty())
                throw new NullPointerException("ERROR : Nom vide");
            if (obj.getPrenom().isEmpty())
                throw new NullPointerException("ERROR : Prenom vide");
            if (obj.getEmail().isEmpty())
                throw new NullPointerException("ERROR : Mail vide");

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

            bool = true;
        } catch (SQLException | NullPointerException e)
        {
            System.err.println(className + " update() " + e.getMessage());
        } finally
        {
            close(prepare);
        }
        return bool;
    }

    @Override
    public boolean delete(Utilisateur obj)
    {
        PreparedStatement prepare = null;
        boolean bool = false;
        try
        {
            if (obj.getId() == 0)
                throw new NullPointerException("ERROR : ID nulle");

            prepare = this.connect
                    .prepareStatement(
                            "DELETE FROM utilisateur WHERE id = ? "
                    );
            prepare.setInt(1, obj.getId());
            prepare.executeUpdate();

            bool = this.find(obj.getId()).getId() == 0;//true / false
        } catch (SQLException | NullPointerException e)
        {
            System.err.println(className + " delete() " + e.getMessage());
        }
        finally
        {
            close(prepare);
        }
        return bool;
    }

    public Utilisateur connexion(String email, String motDePasse)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;
        Utilisateur utilisateur = new Utilisateur();
        try
        {
            if (email.isEmpty())
                throw new NullPointerException("ERROR : Email vide");
            if (motDePasse.isEmpty())
                throw new NullPointerException("ERROR : Mot de passe vide");

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
                utilisateur = this.find(result.getInt(1));

        } catch (SQLException | NullPointerException | NoSuchAlgorithmException e)
        {
            System.err.println(className + " connexion() " + e.getMessage());
        }
        finally
        {
            close(result);
            close(prepare);
        }
        return utilisateur;
    }

    public boolean emailExistant(String email)
    {
        PreparedStatement prepare = null;
        ResultSet result = null;
        boolean bool = true;
        try
        {
            if (email.isEmpty())
                throw new NullPointerException("ERROR : Email vide");

            prepare = this.connect
                    .prepareStatement(
                            "SELECT email FROM utilisateur WHERE email = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );

            prepare.setString(1, email);

            result = prepare.executeQuery();

            bool = result.next();//true / false
        } catch (SQLException | NullPointerException e)
        {
            System.err.println(className + " mailExiste() " + e.getMessage());
        }
        finally
        {
            close(result);
            close(prepare);
        }
        return bool;
    }

    public boolean modifierMotDePasse(Utilisateur obj, String ancienMotDePasse, String nouveauMotDePasse)
    {
        PreparedStatement prepare = null;
        boolean bool = false;
        
        if (connexion(obj.getEmail(), ancienMotDePasse).getId() == 0)
            return bool;

        try
        {
            if (obj.getId() == 0)
                throw new NullPointerException("ERROR : ID nulle");
            if (nouveauMotDePasse.isEmpty())
                throw new NullPointerException("ERROR : Nouveau mot de passe vide");

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

            bool = true;
        } catch (SQLException | NullPointerException | NoSuchAlgorithmException e)
        {
            System.err.println(className + " modifierMotDePasse() " + e.getMessage());
        }
        finally
        {
            close(prepare);
        }
        return bool;
    }

}
