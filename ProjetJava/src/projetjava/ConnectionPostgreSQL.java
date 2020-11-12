/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Benjamin
 */


public class ConnectionPostgreSQL{

    /**
     * URL de connexion
     */
    private static final String url = "jdbc:mysql://93.3.238.99:3307/patissier";
    /**
     * Nom du user
     */
    private static final String utilisateur = "PROJET";
    /**
     * Mot de passe du user
     */
    private static final String motDePass = "Azertyu12!";
    /**
     * Objet Connexion
     */
    private static Connection connect;
    
    /**
     * Méthode qui va nous retourner notre instance
     * et la créer si elle n'existe pas...
     * @return
     */
    public static Connection getInstance(){
        if(connect == null){
            try {
                connect = DriverManager.getConnection(url, utilisateur, motDePass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }        
        return connect;    
    }    
}