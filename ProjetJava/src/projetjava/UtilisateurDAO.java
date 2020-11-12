/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author Benjamin
 */

public class UtilisateurDAO extends DAO<Utilisateur,String> {

    @Override
    public Utilisateur create(Utilisateur obj, String motDePasse) {
        
        try {
             
            
            //Vu que nous sommes sous postgres, nous allons chercher manuellement
            //la prochaine valeur de la séquence correspondant à l'id de notre table
           /* ResultSet result = this    .connect
                                    .createStatement(
                                            ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                            ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                            "SELECT NEXTVAL(utilisateurs_id) as id"
                                    );*/
            //if(result.first()){
                //int id = result.getInt("id");
                PreparedStatement prepare = this    .connect
                                                    .prepareStatement(
                                                        "INSERT INTO utilisateurs ( nom, prenom, email, mot_de_passe, role)"+
                                                        "VALUES( ?, ?, ?, ?, ?)",    Statement.RETURN_GENERATED_KEYS
                                                    );
                
                
                if(obj.getNom().isEmpty())
                {
                    prepare.setNull(1, 92);
                }else prepare.setString(2, obj.getNom());
                
                if(obj.getPrenom().isEmpty())
                {
                    prepare.setNull(2, 92);
                }else prepare.setString(2, obj.getPrenom());
                
                
                prepare.setString(3, obj.getEmail());
                
                MessageDigest digest;
                String messageEncode ="ERREUR";
            try {
                digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(motDePasse.getBytes((StandardCharsets.UTF_8)));
                messageEncode = new String (Hex.encode(hash));
                
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
               
                
                prepare.setString(4, messageEncode);
                
                prepare.setString(5, obj.getRole());
                
                prepare.executeUpdate();
                
                ResultSet result = prepare.getGeneratedKeys();
                int id=0;
                if(result.next())
                {
                    id = result .getInt(1);
                }
                obj = this.find(id);
                
            //}
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return obj;
    }
    
    @Override
    public Utilisateur find(int id) {
        
        Utilisateur dev = new Utilisateur();
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                        ResultSet.CONCUR_READ_ONLY
                                     ).executeQuery(
                                        "SELECT * FROM utilisateurs WHERE id = " + id 
                                     );
            if(result.first())
                    dev = new Utilisateur(
                                            id, 
                                            result.getString("nom"), 
                                            result.getString("prenom"), 
                                            result.getString("email"), 
                                            result.getString("role")
                                        );
            
            } catch (SQLException e) {
                    e.printStackTrace();
            }
           return dev;

    }
    @Override
    public Utilisateur update(Utilisateur obj) {
        
        try{    
            this.connect    
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE utilisateurs SET nom = '" + obj.getNom() + "',"+
                    " prenom = '" + obj.getPrenom() + "'"+
                    " WHERE id = " + obj.getId()
                 );

            obj = this.find(obj.getId());
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
        return obj;
    }
    

    @Override
    public void delete(Utilisateur obj) {
        try {
            
            this.connect    
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "DELETE FROM developpeur WHERE dev_id = " + obj.getId()
                 );

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}
    