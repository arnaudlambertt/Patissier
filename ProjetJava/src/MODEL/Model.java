/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import DAO.*;
import java.util.ArrayList;
import java.util.Map;
import javafx.util.Pair;

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
    private final ArrayList<Produit> produitsFiltre;
    private Commande panier;
    private boolean panierValide;

    public Model()
    {
        this.utilisateurDAO = new UtilisateurDAO();
        this.produitDAO = new ProduitDAO();
        this.commandeDAO = new CommandeDAO();
        this.utilisateur = new Utilisateur();
        this.tousLesProduits = new ArrayList<>();
        this.produitsFiltre = new ArrayList<>();
        this.panier = new Commande();
        this.panierValide = false;
    }

    public void deconnecterUtilisateur()
    {
        utilisateur = new Utilisateur();
    }

    public Commande getPanier()
    {
        return panier;
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

    public void ajouterAuPanier(int index)
    {
        Produit p = produitsFiltre.get(index);
        Map<Produit, Integer> pc = panier.getProduitsCommande();

        if (!pc.containsKey(p) && p.getStock() > 0)
            panier.addProduitCommande(new Pair<>(p, 1)); //creer
        else if (p.getStock() > 0 && pc.get(p) < Integer.min(p.getStock(), 10))
            panier.addProduitCommande(new Pair<>(p, pc.get(p) + 1)); //incrementer 
    }

    public void modifierQuantitePanier(int index, int quantite)
    {
        panier.modifierQuantite(index, quantite);
    }

    public void supprimerProduitPanier(int index)
    {
        ArrayList<Produit> keys = new ArrayList<>(panier.getProduitsCommande().keySet());
        Produit p = (Produit) keys.get(index);
        panier.removeProduitCommande(p);
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
        if (!utilisateurDAO.emailExistant(utilisateur.getEmail()))
            this.utilisateur = this.utilisateurDAO.create(utilisateur, motDePasse);
        utilisateurDAO.close();
        return utilisateurConnecte();
    }

    public boolean connecterUtilisateur(String email, String motDePasse)
    {
        this.utilisateur = this.utilisateurDAO.connexion(email, motDePasse);
        utilisateurDAO.close();
        return utilisateurConnecte();
    }

    public boolean utilisateurConnecte()
    {
        return utilisateur.getId() != 0;
    }

    public void setPanierValide(boolean panierValide)
    {
        this.panierValide = panierValide;
    }

    public boolean panierValide()
    {
        return panierValide;
    }

    public boolean stockSuffisantPanier()
    {
        try
        {
            return commandeDAO.verificationStockPanier(panier);
        } 
        finally
        {
            commandeDAO.close();
        }
    }
    
    public boolean validerCommande()
    {
        try
        {
            if (commandeDAO.create(panier, utilisateur).getId() == 0)
                throw new Exception("Echec validation commande");
            panier = new Commande();
            return true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        } finally
        {
            commandeDAO.close();
        }
    }

    public boolean supprimerUtilisateur()
    {
        try
        {
            if (!utilisateurDAO.delete(this.utilisateur))
                throw new Exception("Echec suppression utilisateur");
            utilisateur = new Utilisateur();
            return true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        } finally
        {
            utilisateurDAO.close();
        }
    }
}
