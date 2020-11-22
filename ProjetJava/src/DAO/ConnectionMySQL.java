/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Benjamin
 */
public class ConnectionMySQL
{

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
     * Méthode qui va nous retourner notre instance et la créer si elle n'existe pas...
     *
     * @return
     * @throws java.sql.SQLException
     */
    public static Connection getInstance() throws SQLException
    {

        connect = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);

        return connect;
    }
}
