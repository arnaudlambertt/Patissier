/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;
import DAO.*;
import java.io.IOException;

/**
 *
 * @author Arnaud
 */
public class Model
{
    private final UtilisateurDAO utilisateurDAO;
    private final ProduitDAO produitDAO;
    private final CommandeDAO commandeDAO;
    private Utilisateur utilisateur;
        
    public Model()
    {
        this.utilisateurDAO = new UtilisateurDAO();
        this.produitDAO = new ProduitDAO();
        this.commandeDAO = new CommandeDAO();
        this.utilisateur = new Utilisateur();
    }

    public Utilisateur getUtilisateur()
    {
        return utilisateur;
    }
    
    public void setEmail(String email)
    {
        utilisateur.setEmail(email);
    }
    
    public void setNom(String nom)
    {
        utilisateur.setNom(nom);
    }
    
    public void setPrenom(String prenom)
    {
        utilisateur.setPrenom(prenom);
    }
    
    // A REFAIRE QUAND ON AURA LA PAGE DE CREATION DE COMPTE
    
    public boolean creerUtilisateur(String motDePasse)
    {
        try
        {
            if(this.utilisateurDAO.emailExistant(utilisateur.getEmail()))
            {
                throw new IOException("Email déjà existant");
            }
        } catch (IOException e)
        {
            System.out.println("ERROR : "+e.getMessage());
            return false;
        }
        this.utilisateur=this.utilisateurDAO.create(utilisateur, motDePasse);
        return true;
    }
    
    public boolean connexionUtilisateur(String email, String motDePasse)
    {
        this.utilisateur=this.utilisateurDAO.connexion(email, motDePasse);
        System.out.println(utilisateur.toString());
        
        try
        {
            if(utilisateur.getEmail().equals(""))
            {
                throw new Exception("Email ou mot de passe incorrect");
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
