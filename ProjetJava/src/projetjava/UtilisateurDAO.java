/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Benjamin
 */

public class UtilisateurDAO extends DAO<Utilisateur> {

    public Utilisateur create(Utilisateur obj) {
        
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
                                                        "INSERT INTO utilisateurs (id, nom, prenom, email, mot_de_passe, role)"+
                                                        "VALUES(?, ?, ?, ?, ?, ?)"
                                                    );
                prepare.setNull(1, 92);
                prepare.setNull(2, 92);
                prepare.setNull(3, 92);
                prepare.setString(4, obj.getEmail());
                prepare.setString(5, obj.getMotDePasse());
                prepare.setString(6, obj.getRole());
                
                prepare.executeUpdate();
                //obj = this.find(id);    
                
            //}
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return obj;
    }
    
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
                                            result.getString("mot_de_passe"), 
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
                    " prenom = '" + obj.getPrenom() + "',"+
                    " email = '" + obj.getEmail() + "'"+
                    " mot_de_passe = '" + obj.getMotDePasse()+ "'"+
                    " role = '" + obj.getRole()+ "'"+
                    " WHERE id = " + obj.getId()
                 );

            obj = this.find(obj.getId());
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
        return obj;
    }
    

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
    