
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO<T, E>
{

    protected Connection connect = null;
    protected String className = "DAO";

    /**
     * Permet de créer une entrée dans la base de données par rapport à un objet
     *
     * @param obj, element
     * @param element
     * @return
     */
    public abstract T create(T obj, E element);

    /**
     * Permet de récupérer un objet via son ID
     *
     * @param id
     * @return
     */
    public abstract T find(int id);

    /**
     * Permet de mettre à jour les données d'une entrée dans la base
     *
     * @param obj
     * @return
     */
    public abstract boolean update(T obj);

    /**
     * Permet la suppression d'une entrée de la base
     *
     * @param obj
     * @return
     */
    public abstract boolean delete(T obj);

    public void open() throws SQLException
    {
        connect = ConnectionMySQL.getInstance();
    }

    public void close()
    {
        try
        {
            if (connect != null && !connect.isClosed())
                connect.close();
        } catch (SQLException e)
        {
            System.err.println(className + " " + e.getMessage());
        }
    }

    public void close(PreparedStatement prepare)
    {
        try
        {
            if (prepare != null)
                prepare.close();
        } catch (SQLException e)
        {
            System.err.println(className + " " + e.getMessage());
        }
    }

    public void close(ResultSet result)
    {
        try
        {
            if (result != null)
                result.close();
        } catch (SQLException e)
        {
            System.err.println(className + " " + e.getMessage());
        }
    }
}
