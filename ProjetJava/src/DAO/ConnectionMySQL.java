/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Benjamin
 */


public class ConnectionMySQL{

    /**
     * URL de connexion
     */
    private static final String URL = "jdbc:mysql://93.3.238.99:3307/patissier";
    /**
     * Nom du user
     */
    private static final String UTILISATEUR = "PROJET";
    /**
     * Mot de passe du user
     */
    private static final String MOT_DE_PASSE = "Azertyu12!";
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
                connect = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
            } catch (SQLException e) {
                System.err.println("ConnectionMYSQL " + e.getMessage());
            }
        }        
        return connect;    
    }    
}