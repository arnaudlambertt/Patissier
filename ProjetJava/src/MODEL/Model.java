/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;
import DAO.*;
import java.io.IOException;
import java.util.ArrayList;

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
    private ArrayList<Produit> tousLesProduits;
    private ArrayList<Produit> produitsFiltre;
        
    public Model()
    {
        this.utilisateurDAO = new UtilisateurDAO();
        this.produitDAO = new ProduitDAO();
        this.commandeDAO = new CommandeDAO();
        this.utilisateur = new Utilisateur();
        this.tousLesProduits = new ArrayList<>();
        this.produitsFiltre = new ArrayList<>();
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
    
    public void init()
    {
        updateTousProduits();
    }
    
    public void updateTousProduits()
    {        
        tousLesProduits = produitDAO.getProduits();
        produitDAO.close();

        produitsFiltre.clear();
        produitsFiltre.addAll(tousLesProduits);
    }
  
    public void filtreCategorie(String categorie)
    {
        produitsFiltre.clear();
        produitsFiltre.addAll(produitDAO.getProduits(categorie));
    }
    
    public void filtreRecherche(String recherche)
    {
        updateTousProduits();
        produitsFiltre.clear();
        tousLesProduits.stream().filter((p) -> (p.getNom().contains(recherche)
                || p.getCategorie().contains(recherche)
                || p.getNomFournisseur().contains(recherche))).forEachOrdered((p) ->
        {
            produitsFiltre.add(p);
        });
    }
    
    public ArrayList<Produit> getProduitsFiltre()
    {
        return produitsFiltre;
    }
    
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
        finally
        {
            utilisateurDAO.close();
        }
        this.utilisateur = this.utilisateurDAO.create(utilisateur, motDePasse);
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
        finally
        {
            utilisateurDAO.close();
        }
        return true;
    }
}
