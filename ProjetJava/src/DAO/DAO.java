
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Benjamin
 */
import java.sql.Connection;

public abstract class DAO<T,E> {

    public Connection connect = ConnectionMySQL.getInstance();
    
    /**
     * Permet de récupérer un objet via son ID
     * @param id
     * @return
     */
    public abstract T find(int id);
    
    /**
     * Permet de créer une entrée dans la base de données
     * par rapport à un objet
     * @param obj, element
     * @param element
     * @return 
     */
    public abstract T create(T obj, E element);
    
    /**
     * Permet de mettre à jour les données d'une entrée dans la base 
     * @param obj
     * @return 
     */
    public abstract boolean update(T obj);
    
    /**
     * Permet la suppression d'une entrée de la base
     * @param obj
     */
    public abstract void delete(T obj);
}

