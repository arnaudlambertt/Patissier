/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava.produit;

import projetjava.produit.Produit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import projetjava.DAO;

/**
 *
 * @author Benjamin
 */
public class ProduitDAO extends DAO<Produit,String> {

    @Override
    public Produit create(Produit obj, String test) {
        
        try 
        {
            PreparedStatement prepare = this    .connect
                                                    .prepareStatement(
                                                        "INSERT INTO produit ( nom, categorie, nom_fournisseur, prix_unitaire, "
                                                                + "quantite_en_stock, quantite_un_lot, prix_un_lot, promotion, "
                                                                + "promotion_active, lien )"+
                                                        "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",    Statement.RETURN_GENERATED_KEYS
                                                    );
            
            
            //Verification des valeurs avant ajout en base de donnée
            try
            {
                if(obj.getNom().isEmpty())
                {
                    throw new IllegalAccessException("ERROR : Nom vide");
                }
                else if(obj.getCategorie().isEmpty())
                {
                    throw new IllegalAccessException("ERROR : Categorie vide");
                }
                else if(obj.getNomFournisseur().isEmpty())
                {
                    throw new IllegalAccessException("ERROR : Nom Fournisseur vide");
                }
                else if(obj.getPrixUnitaire()==0)
                {
                    throw new IllegalAccessException("ERROR : Prix Unitaire vide");
                }
                else if(obj.getQuantiteEnStock()==0)
                {
                    throw new IllegalAccessException("ERROR : Quantite En Stock vide");
                }
                else if(obj.getQuantiteUnLot() == 0 && obj.getPrixUnLot() != 0)
                {
                    throw new IllegalAccessException("ERROR : Si un lot à un prix alors il faut avoir la quantité de produit par lot");
                }
                else if(obj.getQuantiteUnLot() != 0 && obj.getPrixUnLot() == 0)
                {
                    throw new IllegalAccessException("ERROR : Si un lot à une quantité alors il faut avoir un prix de produit par lot");
                }
                else if(obj.getLien().isEmpty())
                {
                    throw new IllegalAccessException("ERROR : Lien vide");
                }
                
            }
            catch (IllegalAccessException ex) {
                Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //On preparer les valeur pour l'instruction INSERT
            prepare.setString(1, obj.getNom());
            prepare.setString(2, obj.getCategorie());
            prepare.setString(3, obj.getNomFournisseur());
            prepare.setFloat(4, obj.getPrixUnitaire());
            prepare.setInt(5, obj.getQuantiteEnStock());
            prepare.setInt(6, obj.getQuantiteUnLot());
            prepare.setFloat(7, obj.getPrixUnLot());
            prepare.setFloat(8, obj.getPromotion());
            prepare.setBoolean(9, obj.isPromotionActive());
            prepare.setString(10, obj.getLien());

            //On fait l'instruction INSERT
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
    public Produit find(int id) 
    {
        Produit dev = new Produit();
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                        ResultSet.CONCUR_READ_ONLY
                                     ).executeQuery(
                                        "SELECT * FROM produit WHERE id = " + id 
                                     );
            if(result.first())
            {
                dev = new Produit(id, 
                                result.getString("nom"), 
                                result.getString("categorie"), 
                                result.getString("nom_fournisseur"), 
                                result.getFloat("prix_unitaire"),
                                result.getInt("quantite_en_stock"),
                                result.getInt("quantite_un_lot"),
                                result.getFloat("prix_un_lot"),
                                result.getFloat("promotion"),
                                result.getBoolean("promotion_active"),
                                result.getString("lien")
                                    );
            }
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return dev;
    }//end find
    
    @Override
    public Produit update(Produit obj) 
    {
        try
        {    
            this.connect    
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE produit SET nom = '" + obj.getNom() + "',"+
                    " categorie = '" + obj.getCategorie()+ "'"+
                    " nom_fournisseur = '" + obj.getNomFournisseur()+ "'"+
                    " prix_unitaire = '" + obj.getPrixUnitaire()+ "'"+
                    " quantite_en_stock = '" + obj.getQuantiteEnStock()+ "'"+
                    " quantite_un_lot = '" + obj.getQuantiteUnLot()+ "'"+
                    " promotion = '" + obj.getPromotion()+ "'"+                      
                    " promotion_active = '" + obj.isPromotionActive()+ "'"+
                    " lien = '" + obj.getLien()+ "'"+
                    " WHERE id = " + obj.getId()
                 );

            obj = this.find(obj.getId());
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
        return obj;
    }//end update
    

    @Override
    public void delete(Produit obj) 
    {
        try
        {
            
            this.connect    
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "DELETE FROM produit WHERE id = " + obj.getId()
                 );

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }//end delete
}//end classe
    