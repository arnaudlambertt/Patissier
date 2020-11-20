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
import java.sql.Types;
import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author Benjamin
 */
public class UtilisateurDAO extends DAO<Utilisateur, String>
{

    @Override
    public Utilisateur create(Utilisateur obj, String motDePasse)
    {
        try
        {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe) "
                            + "VALUES( ? , ? , ? , ? )", Statement.RETURN_GENERATED_KEYS
                    );
            if (obj.getNom().isEmpty())
                prepare.setNull(1, Types.VARCHAR);
            else
                prepare.setString(1, obj.getNom());

            if (obj.getPrenom().isEmpty())
                prepare.setNull(2, Types.VARCHAR);
            else
                prepare.setString(2, obj.getPrenom());

            if (obj.getEmail().isEmpty())
                prepare.setNull(3, Types.VARCHAR);
            else
                prepare.setString(3, obj.getEmail());

            prepare.setString(4, motDePasse);
            prepare.executeUpdate();

            ResultSet result = prepare.getGeneratedKeys();
            int id = 0;
            if (result.next())
                id = result.getInt(1);
            obj = this.find(id);

            //}
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
    }//end create

    @Override
    public Utilisateur find(int id)
    {
        Utilisateur obj = new Utilisateur();
        try
        {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM utilisateur WHERE id = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            prepare.setInt(1, id);
            ResultSet result = prepare.executeQuery();

            if (result.first())
                obj = new Utilisateur(id,
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("role")
                );
            

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
    }//end find

    @Override
    public boolean update(Utilisateur obj)
    {
        try
        {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "UPDATE utilisateur "
                            + "SET nom = ? , prenom = ? , email = ? "
                            + "WHERE id = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            if (obj.getNom().isEmpty())
                prepare.setNull(1, Types.VARCHAR);
            else
                prepare.setString(1, obj.getNom());

            if (obj.getPrenom().isEmpty())
                prepare.setNull(2, Types.VARCHAR);
            else
                prepare.setString(2, obj.getPrenom());

            if (obj.getEmail().isEmpty())
                prepare.setNull(3, Types.VARCHAR);
            else
                prepare.setString(3, obj.getEmail());

            prepare.setInt(4, obj.getId());

            prepare.executeUpdate();

            obj = this.find(obj.getId());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return obj.getId() != 0;
    }//end update
    
    public boolean modifierMotDePasse(Utilisateur obj, String ancienMotDePasse, String nouveauMotDePasse)
    {
        if(connexion(obj.getEmail(), ancienMotDePasse).getId() == 0)
            return false;
        
        try
        {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "UPDATE utilisateur "
                            + "SET mot_de_passe = ? "
                            + "WHERE id = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
          
            if (nouveauMotDePasse.isEmpty())
                prepare.setNull(1, Types.CHAR);
            else
            {                
                try
                {
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] hash = digest.digest(nouveauMotDePasse.getBytes(StandardCharsets.UTF_8));
                    nouveauMotDePasse = new String(Hex.encode(hash));
                } catch (NoSuchAlgorithmException ex)
                {
                }
                prepare.setString(1, nouveauMotDePasse);
            }
            
            prepare.setInt(2, obj.getId());

            prepare.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return true;
    }//end update

    @Override
    public void delete(Utilisateur obj)
    {
        try
        {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "DELETE FROM utilisateur WHERE id = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            prepare.setInt(1, obj.getId());
            prepare.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }//end delete

    public Utilisateur connexion(String email, String motDePasse)
    {
        Utilisateur obj = new Utilisateur();
        try
        {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            if (email.isEmpty())
                prepare.setNull(1, Types.VARCHAR);
            else
                prepare.setString(1, email);

            if (motDePasse.isEmpty())
                prepare.setNull(2, Types.CHAR);
            else
            {                
                try
                {
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] hash = digest.digest(motDePasse.getBytes(StandardCharsets.UTF_8));
                    motDePasse = new String(Hex.encode(hash));
                } catch (NoSuchAlgorithmException ex)
                {
                }
                prepare.setString(2, motDePasse);
            }

            ResultSet result = prepare.executeQuery();

            int id = 0; // 
            
            if (result.next())
                id = result.getInt(1);
            
            obj = this.find(id);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
    }
    
    public boolean mailExiste(String email)
    {
        try
        {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "SELECT * FROM utilisateur WHERE email = ? ",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    );
            if (email.isEmpty())
                prepare.setNull(1, Types.VARCHAR);
            else
                prepare.setString(1, email);
            
            ResultSet result = prepare.executeQuery();
            
            return result.first();//true / false
            
        } catch (SQLException ex)
        {
            
        }
        
        return true;
    }
}//end classe

