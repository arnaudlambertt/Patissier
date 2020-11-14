/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL.DAO.commande;

import DAO.produit.Produit;
import DAO.utilisateur.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DAO.DAO;
import javafx.util.Pair;

/**
 *
 * @author Benjamin
 */
public class CommandeDAO extends DAO<Commande,Utilisateur> {

    @Override
    public Commande create(Commande obj, Utilisateur utilisateur) 
    {        
        try 
        {
            PreparedStatement prepare = this    .connect
                                                    .prepareStatement(
                                                        "INSERT INTO produit_commande ( id_utilisateur, id_produit, quantite, prix_unitaire, quantite_un_lot, prix_un_lot"+
                                                        "VALUES( ?, ?, ?, ?, ?, ?)",    Statement.RETURN_GENERATED_KEYS
                                                    );
            for (Pair<Produit, Integer> produitActuel : obj.getProduitsCommande()) 
            {
                
                if(utilisateur.getId()==0)
                {
                    prepare.setNull(1, 92);
                }else prepare.setInt(2, utilisateur.getId());
                if(produitActuel.getKey().getId()==0)
                {
                    prepare.setNull(2, 92);
                }else prepare.setInt(2, produitActuel.getKey().getId());
                if(produitActuel.getValue()==0)
                {
                    prepare.setNull(3, 92);
                }else prepare.setInt(3, produitActuel.getValue());
                if(produitActuel.getKey().getPrixUnitaire()==0f)
                {
                   prepare.setNull(4, 92);
                }else prepare.setFloat(4, produitActuel.getKey().getPrixUnitaire());
                if(produitActuel.getKey().getQuantiteUnLot()==0f)
                {
                    prepare.setNull(5, 92);
                }else prepare.setFloat(5, produitActuel.getKey().getQuantiteUnLot());
                if(produitActuel.getKey().getPrixUnLot()==0f)
                {
                    prepare.setNull(6, 92);
                }else prepare.setFloat(6, produitActuel.getKey().getPrixUnLot());
                
                prepare.executeUpdate();
                ResultSet result = prepare.getGeneratedKeys();
                int idUtilisateur=0;
                int idProduit=0;
                if(result.next())
                {
                    idUtilisateur = result .getInt("id_utilisateur");
                    idProduit =result.getInt("id_produit");
                    
                }
                obj = this.find(idUtilisateur, idProduit);
                
                prepare.clearParameters();
                
            }
        } catch (SQLException e) 
        {
                e.printStackTrace();
        }
        return obj;
    }//end create
    
    @Override
    public Commande find(int idUtilisateur, int idProduit) 
    {
        Commande dev = new Commande();
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                        ResultSet.CONCUR_READ_ONLY
                                     ).executeQuery(
                                        "SELECT * FROM produit_commande WHERE id_utilisateur = " + idUtilisateur
                                             +" AND id_produit = "+idProduit
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
    