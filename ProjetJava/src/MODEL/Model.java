/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import DAO.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ArrayList<Utilisateur> tousLesUtilisateurs;
    private final ArrayList<Produit> produitsFiltre;
    private Commande panier;

    private Produit produitSelectionne;
    private final HashMap<String,ImageView> imagesProduit;
    private Utilisateur utilisateurSelectionne;

    private ArrayList<Commande> commandesUtilisateur;

    public Model()
    {
        this.utilisateurDAO = new UtilisateurDAO();
        this.produitDAO = new ProduitDAO();
        this.commandeDAO = new CommandeDAO();
        this.utilisateur = new Utilisateur();
        this.tousLesProduits = new ArrayList<>();
        this.tousLesUtilisateurs = new ArrayList<>();
        this.produitsFiltre = new ArrayList<>();
        this.panier = new Commande();
        
        this.produitSelectionne = new Produit();
        this.imagesProduit = new HashMap<>();
        this.utilisateurSelectionne = new Utilisateur();
        this.commandesUtilisateur = new ArrayList<>();
    }

    public void init()
    {
        updateTousProduits();
        updateTousUtilisateurs();
        Requests.scandir().forEach((i) ->
        {
            ImageView image = new ImageView(new Image("http://93.3.238.99/uploads/" + i));
            image.setPreserveRatio(true);
            image.setFitWidth(100);
            imagesProduit.put(i,image);
        });
    }
    
    public void updateCommandesUtilisateurs()
    {
        commandesUtilisateur = commandeDAO.getCommandes(utilisateur.getId());
        commandeDAO.close();
    }

    public boolean verifierEmail(String nouvelEmail)
    {
        return ((!(utilisateurDAO.emailExistant(nouvelEmail))||nouvelEmail.equals(utilisateur.getEmail())));
    }

    public boolean updateUtilisateur()
    {
        try
        {
            if (utilisateurDAO.update(utilisateur))
                throw new Exception("Echec modification utilisateur");
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
    
    public boolean updateUtilisateurSelectionne()
    {
        try
        {
            if (!utilisateurDAO.update(utilisateurSelectionne))
                throw new Exception("Echec modification utilisateur selectionné");
            utilisateurSelectionne = new Utilisateur();
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

    public boolean modifierMotDePasse(String ancien, String nouveau)
    {
        return utilisateurDAO.modifierMotDePasse(utilisateur,ancien,nouveau);
    }

    public ArrayList<Commande> getCommandesUtilisateur()
    {
        return commandesUtilisateur;
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
    
    public void setRole(String role)
    {
        utilisateur.setRole(role);
    }
    
    public Utilisateur getUtilisateurSelectionne()
    {
        return utilisateurSelectionne;
    }

    public Produit getProduitSelectionne()
    {
        return produitSelectionne;
    }

    public void setProduitSelectionne(Produit produitSelectionne)
    {
        this.produitSelectionne = produitSelectionne;
    }
    
    public boolean updateProduitSelectionne()
    {
        try
        {
            if (!produitDAO.update(produitSelectionne))
                throw new Exception("Echec modification produit");
            produitSelectionne = new Produit();
            return true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        } finally
        {
            produitDAO.close();
        }
    }
    
    public HashMap<String,ImageView> getImagesProduit()
    {
        return imagesProduit;
    }

    public void setUtilisateurSelectionne(Utilisateur utilisateurSelectionne)
    {
        this.utilisateurSelectionne = utilisateurSelectionne;
    }

    public ArrayList<Produit> getTousLesProduits()
    {
        return tousLesProduits;
    }
    
    public ArrayList<Utilisateur> getTousLesUtilisateurs()
    {
        return tousLesUtilisateurs;
    }


    public boolean ajouterAuPanier(int index)
    {
        Produit p = produitsFiltre.get(index);
        Map<Produit, Integer> pc = panier.getProduitsCommande();

        if (!pc.containsKey(p) && p.getStock() > 0)
            panier.addProduitCommande(new Pair<>(p, 1)); //creer
        else if (p.getStock() > 0 && pc.get(p) < Integer.min(p.getStock(), 10))
            panier.addProduitCommande(new Pair<>(p, pc.get(p) + 1)); //incrementer
        else
            return false;
        
        return true;
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
    
    public void updateTousUtilisateurs()
    {
        tousLesUtilisateurs = utilisateurDAO.getUtilisateurs();
        utilisateurDAO.close();
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
                || p.getFournisseur().contains(recherche))).forEachOrdered((p) ->
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
    
    public boolean creerUtilisateurAdmin(String motDePasse)
    {
        if (!utilisateurDAO.emailExistant(utilisateurSelectionne.getEmail()))
            this.utilisateurSelectionne = this.utilisateurDAO.create(utilisateurSelectionne, motDePasse);
        utilisateurDAO.close();
        return utilisateurAdminConnecte();
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
    
    public boolean utilisateurAdminConnecte()
    {
        return utilisateurSelectionne.getId() != 0;
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
    
    public boolean validerCreationProduit()
    {
        try
        {
            if (produitDAO.create(produitSelectionne).getId() == 0)
                throw new Exception("Echec création produit");
            produitSelectionne = new Produit();
            return true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        } finally
        {
            produitDAO.close();
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
    
    public boolean supprimerUtilisateurSelectionnee()
    {
        try
        {
            if (!utilisateurDAO.delete(this.utilisateurSelectionne))
                throw new Exception("Echec suppression utilisateur");
            utilisateurSelectionne = new Utilisateur();
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
    
    public boolean supprimerProduitSelectionnee()
    {
        try
        {
            if (!produitDAO.delete(this.produitSelectionne))
                throw new Exception("Echec suppression utilisateur");
            produitSelectionne = new Produit();
            return true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        } finally
        {
            produitDAO.close();
        }
    }

    boolean utilisateurAdmin()
    {
        return utilisateur.getRole().equals("Administrateur");
    }

    public void resetProduitSelectionne()
    {
        produitSelectionne = new Produit();
    }
}
