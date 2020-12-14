/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import DAO.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
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
    private final HashMap<String, Image> imagesProduit;
    private Utilisateur utilisateurSelectionne;

    private ArrayList<Commande> commandesUtilisateur;

    /**
     *
     */
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
    /**
     * initialise le model (met à jour les produits et les utilisateurs depuis la base de donnée
     */
    public void init()
    {
        updateTousProduits();
        updateTousUtilisateurs();
        Requests.scandir().forEach((i) ->
        {
            imagesProduit.put(i, new Image("http://93.3.238.99/uploads/" + i, 100, 100, true, true));
        });
    }

    /**
     * mets à jour les images depuis le serveur
     */
    public void updateImagesProduit()
    {
        imagesProduit.clear();

        Requests.scandir().forEach((i) ->
        {
            imagesProduit.put(i, new Image("http://93.3.238.99/uploads/" + i, 100, 100, true, true));
        });
    }
    /**
     * upload une imahe en paramètre sur le serveur
     * @param fichier fichier à upload
     * @return true si l'upload a réussi false sinon
     */
    public boolean uploadImageProduit(File fichier)
    {
        if(Requests.upload(fichier))
            imagesProduit.put(fichier.getName(), new Image("http://93.3.238.99/uploads/" + fichier.getName(), 100, 100, true, true));
        else
            return false;
        return true;
    }
    
    /**
     * mets à jour les commandes utilisateurs depuis la base de donnée
     */
    public void updateCommandesUtilisateurs()
    {
        commandesUtilisateur = commandeDAO.getCommandes(utilisateur.getId());
        commandeDAO.close();
    }

    /**
     * Verifie que l'email reçu en paramètre correspond bien à un utilisateur de la base de donnée
     * @param nouvelEmail email à tester
     * @return true si l'email correspond false sinon
     */
    public boolean verifierEmail(String nouvelEmail)
    {
        return ((!(utilisateurDAO.emailExistant(nouvelEmail)) || nouvelEmail.equals(utilisateur.getEmail())));
    }

    /**
     * mets à jour les utilisateurs de la base de donnée à partir de l'utilisateur du code
     * @return true si l'udate a réussi, false sinon
     */
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

    /**
     * mets à jour les utilisateurs selectionnés dans la base de donnée
     * @return true si ça a réussi false sinon
     */
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
    
    
    /**
     * modifie le mdp d'un utilisateur si l'ancien mot de passe correspond bien 
     * @param ancien ancien mot de passe
     * @param nouveau nouveau mot de passe
     * @return true si ça réussi, false sinon
     */
    public boolean modifierMotDePasse(String ancien, String nouveau)
    {
        return utilisateurDAO.modifierMotDePasse(utilisateur, ancien, nouveau);
    }

    /**
     *
     * @return
     */
    public ArrayList<Commande> getCommandesUtilisateur()
    {
        return commandesUtilisateur;
    }

    /**
     * remet l'utilisateur à 0 (le code considère un new utilisateur comme étant
     * un utilisateur déconnecté
     */
    public void deconnecterUtilisateur()
    {
        utilisateur = new Utilisateur();
    }

    /**
     *
     * @return
     */
    public Commande getPanier()
    {
        return panier;
    }

    /**
     *
     * @return
     */
    public Utilisateur getUtilisateur()
    {
        return utilisateur;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email)
    {
        utilisateur.setEmail(email);
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom)
    {
        utilisateur.setNom(nom);
    }

    /**
     *
     * @param prenom
     */
    public void setPrenom(String prenom)
    {
        utilisateur.setPrenom(prenom);
    }

    /**
     *
     * @param role
     */
    public void setRole(String role)
    {
        utilisateur.setRole(role);
    }

    /**
     *
     * @return
     */
    public Utilisateur getUtilisateurSelectionne()
    {
        return utilisateurSelectionne;
    }

    /**
     *
     * @return
     */
    public Produit getProduitSelectionne()
    {
        return produitSelectionne;
    }

    /**
     *
     * @param produitSelectionne
     */
    public void setProduitSelectionne(Produit produitSelectionne)
    {
        this.produitSelectionne = produitSelectionne;
    }

    /**
     * mets à jour les produits séléctionnés dans la base de donnée
     * @return true si ça réussi false sinon
     */
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

    /**
     *
     * @return
     */
    public HashMap<String, Image> getImagesProduit()
    {
        return imagesProduit;
    }

    /**
     *
     * @param utilisateurSelectionne
     */
    public void setUtilisateurSelectionne(Utilisateur utilisateurSelectionne)
    {
        this.utilisateurSelectionne = utilisateurSelectionne;
    }

    /**
     *
     * @return
     */
    public ArrayList<Produit> getTousLesProduits()
    {
        return tousLesProduits;
    }

    /**
     *
     * @return
     */
    public ArrayList<Utilisateur> getTousLesUtilisateurs()
    {
        return tousLesUtilisateurs;
    }

    /**
     * Ajout d'un Produit au panier
     * @param index du produit
     * @return true si ça réussi false sinon
     */
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

    /**
     * Modifie la quantité d'un objet dans le panier
     * @param index id de l'objet dont on veut modifier la quantité
     * @param quantite nouvelle quantité de l'objet
     */
    public void modifierQuantitePanier(int index, int quantite)
    {
        panier.modifierQuantite(index, quantite);
    }

    /**
     * supprime le produit du panier
     * @param index du produit à supprimer
     */
    public void supprimerProduitPanier(int index)
    {
        ArrayList<Produit> keys = new ArrayList<>(panier.getProduitsCommande().keySet());
        Produit p = (Produit) keys.get(index);
        panier.removeProduitCommande(p);
    }

    /**
     * mets à jour tous les produit depuis la base de donnée
     */
    public void updateTousProduits()
    {
        tousLesProduits = produitDAO.getProduits();
        produitDAO.close();

        produitsFiltre.clear();
        produitsFiltre.addAll(tousLesProduits);
    }

    /**
     * mets à jour tous les utilisateurs depuis la base de donnée
     */
    public void updateTousUtilisateurs()
    {
        tousLesUtilisateurs = utilisateurDAO.getUtilisateurs();
        utilisateurDAO.close();
    }
    
    /**
     * reccupère les produits filtrés depuis la base de donnée
     * @param categorie nom de la catégorie selon laquelle on veut filtrer
     */
    public void filtreCategorie(String categorie)
    {
        produitsFiltre.clear();
        produitsFiltre.addAll(produitDAO.getProduits(categorie));
    }

    /**
     * reccupère les produits filtrés depuis la base de donnée
     * @param recherche String à partir de laquelle on va effectuer la recherche
     */
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

    /**
     *
     * @return
     */
    public ArrayList<Produit> getProduitsFiltre()
    {
        return produitsFiltre;
    }

    /**
     * créer un utilisateur
     * @param motDePasse mot de passe de l'utilisateur
     * @return true si ça réussi false sinon
     */
    public boolean creerUtilisateur(String motDePasse)
    {
        if (!utilisateurDAO.emailExistant(utilisateur.getEmail()))
            this.utilisateur = this.utilisateurDAO.create(utilisateur, motDePasse);
        utilisateurDAO.close();
        return utilisateurConnecte();
    }
    
    /**
     * Créer un utilisateur Administrateur
     * @param motDePasse de l'administrateur
     * @return true si ça réussi false sinon
     */
    public boolean creerUtilisateurAdmin(String motDePasse)
    {
        if (!utilisateurDAO.emailExistant(utilisateurSelectionne.getEmail()))
            this.utilisateurSelectionne = this.utilisateurDAO.create(utilisateurSelectionne, motDePasse);
        utilisateurDAO.close();
        return utilisateurAdminConnecte();
    }

    /**
     * tente de connecte l'utilisateur
     * @param email email de l'utilisateur
     * @param motDePasse mdp de l'utilisateur
     * @return true si ça réussi false sinon
     */
    public boolean connecterUtilisateur(String email, String motDePasse)
    {
        this.utilisateur = this.utilisateurDAO.connexion(email, motDePasse);
        utilisateurDAO.close();
        return utilisateurConnecte();
    }

    /**
     * Verifie que l'utilisateur est connecté
     * @return true si il est connécté false sinon
     */
    public boolean utilisateurConnecte()
    {
        return utilisateur.getId() != 0;
    }

    /**
     * Verifie que l'administrateur est connecté
     * @return true si il est connecté false sinon
     */
    public boolean utilisateurAdminConnecte()
    {
        return utilisateurSelectionne.getId() != 0;
    }

    /**
     * verifie que le stock est suffisant dans la base de donnée 
     * @return true s'il est suffisant false sinon
     */
    public boolean stockSuffisantPanier()
    {
        try
        {
            return commandeDAO.verificationStockPanier(panier);
        } finally
        {
            commandeDAO.close();
        }
    }

    /**
     * tente de créer la commande en BDD
     * @return true si la commande est crée false sinon
     */
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

    /**
     * tente de créer un produit en BDD
     * @return true si le produit est crée faux sinon
     */
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
    
    /**
     * Supprime un utilisateur dans la base de donnée
     * @return true si l'utilisateur est supprimé false sinon
     */
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

    /**
     * supprime l'utilisateur selectionné dans la BDD
     * @return true s'il est supprimé false sinon
     */
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

    /**
     * Supprime le produit selectionné dans la BDD
     * @return true si le produit est supprimé false sinon
     */
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

    /**
     * verifie si l'utilisateur est un administrateur
     * @return true si c'est un admin false sinon
     */
    boolean utilisateurAdmin()
    {
        return utilisateur.getRole().equals("Administrateur");
    }

    /**
     * reset le produit selectionné
     */
    public void resetProduitSelectionne()
    {
        produitSelectionne = new Produit();
    }
}
