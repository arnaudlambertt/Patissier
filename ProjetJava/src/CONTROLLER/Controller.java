/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import CONSTANT.Scenes;
import MODEL.*;
import VIEW.PaneCommande;
import VIEW.PaneProduit;
import VIEW.PaneProduitAdmin;
import VIEW.PaneProduitPanier;
import VIEW.PaneUtilisateurAdmin;
import VIEW.View;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Benjamin
 */
public class Controller
{

    private final Model model;
    private final View view;
    private final EventController eventController;
    private boolean redirectionCommande;
    private final FileChooser fileChooser;

    public Controller(Stage primaryStage)
    {
        this.model = new Model();
        this.view = new View(primaryStage);
        this.eventController = new EventController(this);
        this.redirectionCommande = false;
        this.fileChooser = new FileChooser();
    }

    /**
     *  initialise le model et le view, et ajoute les différentes actions
     */
    public void init()
    {
        model.init();
        view.init();

        eventController.hover(view.getpEntete().getbLogo());
        eventController.hover(view.getpEntete().getbRecherche());
        eventController.hover(view.getpEntete().getbBonjour());
        eventController.hover(view.getpEntete().getbPanier());

        //Actions boutons de l'entete
        for (int i = 0; i < 9; ++i)
        {
            view.getpEntete().getbCategories(i).setOnAction(eventController::afficherCategorie);
            eventController.hoverButtonOrange(view.getpEntete().getbCategories(i));
        }

        //Actions boutons connexion utilisateur
        view.getbConnexion().setOnAction(eventController::connexion);
        eventController.hoverButtonOrangeClair(view.getSConnexion().getbConnexion());

        //Actions boutons redirection vers cree compte
        view.getbCreerCompte().setOnAction(eventController::redirectionCreerCompte);
        eventController.hoverButtonOrangeClair(view.getSConnexion().getbCreerCompte());

        //Actions boutons cree compte utilisateur
        view.getCreationCompte().getbCreeMonCompte().setOnAction(eventController::creerCompte);
        eventController.hoverButtonOrangeClair(view.getCreationCompte().getbCreeMonCompte());

        //Actions barre de recherche
        view.getpEntete().gettBarreRecherche().setOnKeyPressed(eventController::entrerRecherche);
        eventController.focusBarreRecherche(view.getpEntete().gettBarreRecherche());

        //Actions bouton rechercher
        view.getpEntete().getbRecherche().setOnAction(eventController::afficherRecherche);
        eventController.hoverButtonOrangeClair(view.getpEntete().getbRecherche());
        
        eventController.hoverButtonOrangeClair(view.getsProfil().getbDeconnectionUtilisateur());
        eventController.hoverButtonOrangeClair(view.getsProfil().getbEnregisterModifs());
        eventController.hoverButtonOrangeClair(view.getsProfil().getbMesAchats());
        eventController.hoverButtonOrangeClair(view.getsProfil().getbSupprimerCompte());

        //Actions boutons entete
        initUrl();
        view.gettUrl().setOnKeyPressed(eventController::entrerUrl);
        view.getpEntete().getbLogo().setOnAction(eventController::afficherAccueil);
        view.getpEntete().getbBonjour().setOnAction(eventController::bonjour);
        view.getpEntete().getbPanier().setOnAction(eventController::afficherPanier);

        view.getsProfil().getbDeconnectionUtilisateur().setOnAction(eventController::deconnecterUtilisateur);
        view.getsProfil().getbSupprimerCompte().setOnAction(eventController::supprimerUtilisateur);

        //Actions boutons zone admin
        view.getpAdmin().getbGestionProduit().setOnAction(eventController::GestionProduitAdmin);
        view.getpAdmin().getbGestionUtilisateur().setOnAction(eventController::GestionUtilisateurAdmin);
        view.getpAdmin().getbDeconnexion().setOnAction(eventController::deconnecterUtilisateur);
        view.getpAdmin().getbAjoutUtilisateur().setOnAction(eventController::redirectionCreerUtilisateurAdmin);
        view.getpAdmin().getbAjoutProduit().setOnAction(eventController::redirectionCreerProduitAdmin);
        eventController.hover(view.getpAdmin().getbDeconnexion());
        eventController.hover(view.getpAdmin().getbGestionProduit());
        eventController.hover(view.getpAdmin().getbAjoutProduit());
        eventController.hover(view.getpAdmin().getbGestionUtilisateur());
        eventController.hover(view.getpAdmin().getbAjoutUtilisateur());

        //Actions boutons cree compte administrateur
        view.getSCreationCompteAdmin().getbCreeMonCompte().setOnAction(eventController::creerCompteAdmin);
        eventController.hoverButtonOrangeClair(view.getSCreationCompteAdmin().getbCreeMonCompte());

        //Actions boutons modifier compte utilisateur (zone administrateur)
        view.getsModifierUtilisateur().getbValiderModificationProduit().setOnAction(eventController::modifierUtilisateurSelectionne);
        eventController.hoverButtonOrangeClair(view.getsModifierUtilisateur().getbValiderModificationProduit());

        //Actions boutons modifier / crer produit (zone administrateur)
        view.getsModifierProduit().getbValiderModificationProduit().setOnAction(eventController::modifierProduitSelectionne);
        eventController.hoverButtonOrangeClair(view.getsModifierProduit().getbValiderModificationProduit());
        view.getsModifierProduit().getbUpload().setOnAction(eventController::uploadImage);
        eventController.hover(view.getsModifierProduit().getbUpload());
        initFileChooser();
        initListImages();

        //Profils
        view.getsProfil().getbMesAchats().setOnAction(eventController::afficherCommandesUtilisateur);
        view.getsProfil().getbEnregisterModifs().setOnAction(eventController::mettreAJourUtilisateur);        
        
        //Actions bouton valider Panier
        view.getbValiderPanier().setOnAction(eventController::validerPanier);
        eventController.hover(view.getbValiderPanier());

        //Actions bouton validerLivraison
        view.getbValiderAdresse().setOnAction(eventController::validerAdresse);//
        eventController.hover(view.getbValiderAdresse());

        //Actions bouton confirmerCommande
        view.getbConfirmerCommande().setOnAction(eventController::commander);
        eventController.hover(view.getbConfirmerCommande());

        changerScene(Scenes.SCENE_PRODUITS);
        view.getPrimaryStage().show();
        //view.getPrimaryStage().setMaximized(true);
        //APRES LE SHOW
        view.getpEntete().format();
    }

    /** 
     * initialiser le selectionneur de fichier pour l'upload
     */
    public void initFileChooser()
    {
        fileChooser.setTitle("Choisir une image");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\pictures"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    }

    /**
     *  initialise le combobox d'images pour la scene modifier produit
     */
    public void initListImages()
    {
        ComboBox<String> cb = view.getsModifierProduit().getcbListImages();
        HashMap<String, Image> listItems = model.getImagesProduit();
        cb.getItems().addAll(listItems.keySet());
        Callback<ListView<String>, ListCell<String>> cellFactory
                = param -> new ListCell<String>()
        {
            @Override
            protected void updateItem(String item, boolean empty)
            {
                super.updateItem(item, empty);
                if (listItems.containsKey(item))
                    setGraphic(new ImageView(listItems.get(item)));
            }
        };
        cb.setButtonCell(cellFactory.call(null));
        cb.setCellFactory(cellFactory);
    }

    /**
     * corrige en partie une contrainte de javafx avec windows quand on set un textfield
     */
    private void initUrl()
    {
        view.gettUrl().focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldValue, Boolean newValue) ->
        {
            if (newValue)
                Platform.runLater(() ->
                {
                    int carretPosition = view.gettUrl().getCaretPosition();
                    if (view.gettUrl().getAnchor() != carretPosition)
                    {
                        view.gettUrl().selectRange(carretPosition, carretPosition);
                        view.getpEntete().requestFocus();
                    }
                });
        });
    }

    /**
     * prépare si besoin la scène avant de la changer via le view, set l'url
     * @param SceneConstant numero de la scène
     */
    public void changerScene(int SceneConstant)
    {
        String urlTemporaire = "";
        switch (SceneConstant) //prepare scenes si besoin
        {
            case Scenes.SCENE_PRODUITS:
                preparerSceneProduits();
                break;
            case Scenes.SCENE_CONNEXION:
                urlTemporaire = "connexion";
                preparerSceneConnexion();
                break;
            case Scenes.SCENE_CREATION_COMPTE:
                urlTemporaire = "creer-mon-compte";
                break;
            case Scenes.SCENE_PROFIL:
                urlTemporaire = "profil/mes-informations";
                preparerSceneUtilisateur();
                break;
            case Scenes.SCENE_PANIER:
                urlTemporaire = "panier";
                preparerScenePanier();
                break;
            case Scenes.SCENE_ADRESSE:
                urlTemporaire = "panier/livraison";
                break;
            case Scenes.SCENE_PAIEMENT:
                urlTemporaire = "panier/paiement";
                view.setAdresse(model.getPanier().getAdresse());
                break;
            case Scenes.SCENE_COMMANDES:
                urlTemporaire = "profil/mes-commandes";
                preparerSceneCommande();
                break;
            case Scenes.SCENE_ADMIN_PRODUIT:
                urlTemporaire = "admin/produits";
                preparerSceneProduitsAdmin();
                break;
            case Scenes.SCENE_MODIFIER_PRODUIT:
                urlTemporaire = "admin/produits/modifier-produit";
                preparerSceneModifierCreerProduit();
                break;
            case Scenes.SCENE_CREATION_PRODUIT:
                urlTemporaire = "admin/produits/creation-produit";
                model.getProduitSelectionne().setCategorie("Gros électroménager");
                model.getProduitSelectionne().setLienImage("segado.jpg");
                model.getProduitSelectionne().setPromotionActive(true);
                preparerSceneModifierCreerProduit();
                break;
            case Scenes.SCENE_ADMIN_UTILISATEUR:
                urlTemporaire = "admin/utilisateurs";
                preparerSceneUtilisateursAdmin();
                break;
            case Scenes.SCENE_MODIFIER_UTILISATEUR:
                urlTemporaire = "admin/utilisateurs/modifier-utilisateur";
                preparerSceneModifierUtilisateur();
                break;
            case Scenes.SCENE_CREATION_COMPTE_ADMIN:
                urlTemporaire = "admin/utilisateurs/creation-compte-admin";
                break;
            default:;
                urlTemporaire = "page-introuvable";
        }
        view.changerScene(SceneConstant);
        setUrl(urlTemporaire);
    }

    /**
     *  met à jour les données de l'utilisateur en base de donnée avec les textField de la scene profil
     */
    public void mettreAJourUtilisateur()
    {
        if (!view.getsProfil().gettEmail().getText().isEmpty())
            model.getUtilisateur().setEmail(view.getsProfil().gettEmail().getText());
        if (!view.getsProfil().gettPrenom().getText().isEmpty())
            model.getUtilisateur().setPrenom(view.getsProfil().gettPrenom().getText());
        if(!view.getsProfil().gettNom().getText().isEmpty())
            model.getUtilisateur().setNom(view.getsProfil().gettNom().getText());
    }

    /**
     *  prépare les panes des différentes commandes de l'utilisateur pour la scene historique de commande
     */
    public void preparerSceneCommande()
    {
        model.updateCommandesUtilisateurs();
        ArrayList<Commande> commandeUtilisateur = model.getCommandesUtilisateur();
        ArrayList<PaneCommande> paneCommande = view.getPanesCommandes();

        paneCommande.clear();

        for (int i = 0; i < commandeUtilisateur.size(); ++i)
        {
            PaneCommande pp = new PaneCommande(i, commandeUtilisateur.get(i));
            paneCommande.add(pp);
        }
    }

    /**
     *  clear les TextField de la scene connexion
     */
    public void preparerSceneConnexion()
    {
        view.getSConnexion().clear();
    }

    /**
     *  clear les TextField de la scene creation compte
     */
    public void preparerSceneCreationCompte()
    {
        view.getCreationCompte().clearTextField();
    }

    /**
     *  prépare les panes des différents produits de la scene produits
     */
    public void preparerSceneProduits()
    {
        ArrayList<Produit> produitsFiltre = model.getProduitsFiltre();
        ArrayList<PaneProduit> paneProduits = view.getPanesProduit();
        paneProduits.clear();
        for (int i = 0; i < produitsFiltre.size(); ++i)
        {
            PaneProduit pp = new PaneProduit(i, produitsFiltre.get(i));
            eventController.hoverButtonOrangeClair(pp.getbAjouterPanier());
            pp.getbAjouterPanier().setOnAction(eventController::ajouterProduitPanier);
            paneProduits.add(pp);
        }
    }

    /**
     *  set les différents éléments visuels de la scene modifier produit avec les données du produit selectionné
     */
    public void preparerSceneModifierCreerProduit()
    {
        view.getsModifierProduit().gettNom().setText(model.getProduitSelectionne().getNom());
        view.getsModifierProduit().setSelectCategorie(model.getProduitSelectionne().getCategorie());
        view.getsModifierProduit().gettFournisseur().setText(model.getProduitSelectionne().getFournisseur());
        view.getsModifierProduit().gettPrixUnitaire().setText(Double.toString(model.getProduitSelectionne().getPrixUnitaire()));
        view.getsModifierProduit().gettStock().setText(Integer.toString(model.getProduitSelectionne().getStock()));
        view.getsModifierProduit().gettQuantiteUnLot().setText(Integer.toString(model.getProduitSelectionne().getQuantiteUnLot()));
        view.getsModifierProduit().gettPrixUnLot().setText(Double.toString(model.getProduitSelectionne().getPrixUnLot()));
        view.getsModifierProduit().gettPromotion().setText(Double.toString(model.getProduitSelectionne().getPromotion() * 100));
        view.getsModifierProduit().getcbListImages().getSelectionModel().select(model.getProduitSelectionne().getLienImage());
        view.getsModifierProduit().setSelectPromotion(model.getProduitSelectionne().isPromotionActive());
    }
    
    /**
     *  set les différents TextField des la scene modifier utilisateur avec les données de l'utilisateur selectionné
     */
    public void preparerSceneModifierUtilisateur()
    {
        view.getsModifierUtilisateur().gettNom().setText(model.getUtilisateurSelectionne().getNom());
        view.getsModifierUtilisateur().gettPrenom().setText(model.getUtilisateurSelectionne().getPrenom());
        view.getsModifierUtilisateur().gettEmail().setText(model.getUtilisateurSelectionne().getEmail());
        view.getsModifierUtilisateur().setSelectRole(model.getUtilisateurSelectionne().getRole());
    }
    
    /**
     *  prépare les panes des différents produits de la scene admin produits
     */
    public void preparerSceneProduitsAdmin()
    {
        model.updateTousProduits();
        ArrayList<Produit> produitsFiltre = model.getProduitsFiltre();
        ArrayList<PaneProduitAdmin> panesProduitAdmin = view.getPanesProduitAdmin();
        panesProduitAdmin.clear();
        for (int i = 0; i < produitsFiltre.size(); ++i)
        {
            PaneProduitAdmin pp = new PaneProduitAdmin(i, produitsFiltre.get(i));
            //Boutons modifier
            eventController.hoverButtonOrangeClair(pp.getbModifierProduit());
            pp.getbModifierProduit().setOnAction(eventController::modifierProduitAdminRedirection);
            //Boutons supprimer
            eventController.hoverButtonOrangeClair(pp.getbSupprimerProduit());
            pp.getbSupprimerProduit().setOnAction(eventController::supprimerProduitAdministrateur);
            panesProduitAdmin.add(pp);
        }
    }

    /**
     *  prépare les panes des différents utilisateurs de la scene admin utilisateurs
     */
    public void preparerSceneUtilisateursAdmin()
    {
        model.updateTousUtilisateurs();
        ArrayList<Utilisateur> utilisateurs = model.getTousLesUtilisateurs();
        ArrayList<PaneUtilisateurAdmin> panesUtilisateursAdmin = view.getPanesUtilisateurAdmin();
        panesUtilisateursAdmin.clear();
        for (int i = 0; i < utilisateurs.size(); ++i)
        {
            PaneUtilisateurAdmin uu = new PaneUtilisateurAdmin(i, utilisateurs.get(i));
            //Boutons modifier
            eventController.hoverButtonOrangeClair(uu.getbModifierUtilisateur());
            uu.getbModifierUtilisateur().setOnAction(eventController::modifierUtilisateurAdminRedirection);
            //Boutons supprimer
            eventController.hoverButtonOrangeClair(uu.getbSupprimerUtilisateur());
            uu.getbSupprimerUtilisateur().setOnAction(eventController::supprimerUtilisateurAdministrateur);

            panesUtilisateursAdmin.add(uu);
        }
    }

    /**
     *  prépare les panes des différents produits dans le panier
     */
    public void preparerScenePanier()
    {
        Commande panier = model.getPanier();
        ArrayList<PaneProduitPanier> panesProduitPanier = view.getPanesProduitPanier();
        panesProduitPanier.clear();

        ArrayList<Produit> keys = new ArrayList<>(panier.getProduitsCommande().keySet());

        for (int i = 0; i < keys.size(); ++i)
        {
            PaneProduitPanier pp = new PaneProduitPanier(i, (Produit) keys.get(i), panier.getProduitsCommande().get((Produit) keys.get(i)));
            pp.getCbNombreProduit().setOnAction(eventController::changementQuantitePanier);
            pp.getbSupprimer().setOnAction(eventController::supprimerProduitPanier);
            eventController.hover(pp.getbSupprimer());
            panesProduitPanier.add(pp);
        }
        view.setPrixPanier(panier.getPrix());
    }

    /**
     *  prépare les élements de la scene profil
     */
    public void preparerSceneUtilisateur()
    {
        Utilisateur utilisateurActif = model.getUtilisateur();

        view.getsProfil().gettNom().setText(utilisateurActif.getNom());
        view.getsProfil().gettPrenom().setText(utilisateurActif.getPrenom());
        view.getsProfil().gettEmail().setText(utilisateurActif.getEmail());
        view.getsProfil().gettNouveauMotDePasse().setText("");
        view.getsProfil().gettAncienMotDePasse().setText("");
        view.getsProfil().update(view);
    }

    public Utilisateur getUtilisateur()
    {
        return model.getUtilisateur();
    }

    public void setEmail(String email)
    {
        model.setEmail(email);
    }

    @Override
    public String toString()
    {
        return "Je suis le controller";
    }

    public Model getModel()
    {
        return model;
    }

    public View getView()
    {
        return view;
    }

    public boolean utilisateurConnecte()
    {
        return model.utilisateurConnecte();
    }

    public void setRedirectionCommande(boolean redirige)
    {
        this.redirectionCommande = redirige;
        view.setProgressionVisible(redirige);
    }

    public boolean redirectionCommande()
    {
        return redirectionCommande;
    }

    public FileChooser getFileChooser()
    {
        return fileChooser;
    }

    public void setUrl(String url)
    {
        view.setUrl(url);
    }

    public String getUrl()
    {
        return view.getUrl();
    }

}
