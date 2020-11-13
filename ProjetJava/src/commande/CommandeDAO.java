/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commande;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import projetjava.DAO;
import commande.Commande;

/**
 *
 * @author Benjamin
 */
public class CommandeDAO extends DAO<Commande,String> {

    @Override
    public Commande create(Commande obj, String motDePasse) 
    {        
        try 
        {
            PreparedStatement prepare = this    .connect
                                                    .prepareStatement(
                                                        "INSERT INTO historique_commande ( prix, horodateur, id_user, id_produit)"+
                                                        "VALUES( ?, ?, ?, ?)",    Statement.RETURN_GENERATED_KEYS
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

            
            prepare.setString(4, motDePasse);
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
        } catch (SQLException e) 
        {
                e.printStackTrace();
        }
        return obj;
    }//end create
    
    @Override
    public Commande find(int id) 
    {
        Commande dev = new Commande();
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                        ResultSet.CONCUR_READ_ONLY
                                     ).executeQuery(
                                        "SELECT * FROM historique_commande WHERE id = " + id 
                                     );
            if(result.first())
            {
                dev = new Commande(id, 
                                    result.getString("nom"), 
                                    result.getString("prenom"), 
                                    result.getString("email"), 
                                    result.getString("role")
                                        );
            }
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return dev;
    }//end find
    
    @Override
    public Commande update(Commande obj) 
    {
        try
        {    
            this.connect    
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE historique_commande SET nom = '" + obj.getNom() + "',"+
                    " prenom = '" + obj.getPrenom() + "'"+
                    " WHERE id = " + obj.getId()
                 );

            obj = this.find(obj.getId());
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
        return obj;
    }//end update
    

    @Override
    public void delete(Commande obj) 
    {
        try
        {
            
            this.connect    
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "DELETE FROM historique_commande WHERE id = " + obj.getId()
                 );

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }//end delete
}//end classe
    