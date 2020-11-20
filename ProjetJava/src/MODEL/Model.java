/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;
import DAO.*;

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
   
}
