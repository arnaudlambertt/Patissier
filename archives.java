pour voir les grilles gridpane
//grid.setGridLinesVisible (true);


public static void main(String[] args) {
        //DBBConnexion();

        //UtilisateurDAO DAO = new UtilisateurDAO();
        /*Utilisateur test = new Utilisateur("benji@test7.com");

        System.out.println(test.toString());
        test=DAO.create(test,"Truc");
        System.out.println(test.toString());
        */
        //test.setNom("Arnaud");
        //test.setPrenom("Mathias");
        //test = DAO.update(test);

        //System.out.println(test.toString());


        //


        //String motDePasse = "Truc";
        //String email = "benji@test7.com";



        //Utilisateur test2 = DAO.connexionUtilisateur(email, motDePasse);
        //System.out.println("\n\nTEST 2");
        //System.out.println(test2.toString());

        /*
        Produit nextProduit = new Produit("shuriken2", "arme", "Mathias", 100, 1000, 20, 1500, 0.05f, true, "http://patissier.com/arme/Mathias/shuriken");
        ProduitDAO DAO = new ProduitDAO();
        System.out.println(nextProduit.toString());
        nextProduit=DAO.create(nextProduit, "");
        System.out.println("\n\nAFTER");
        System.out.println(nextProduit.toString());
        //DAO.delete(nextProduit);


        launch(args);
        */
    }

public static void DBBConnexion()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://93.3.238.99:3307/patissier","PROJET","Azertyu12!");
            System.out.println("SUCESS");
            con.close();
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
            Logger.getLogger(ProjetJava.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fail class not found ");
        } catch (SQLException ex) {
            Logger.getLogger(ProjetJava.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fail SQL");
        }
    }

    public static void getBDDNom()
    {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://93.3.238.99:3307/patissier","PROJET","Azertyu12!");
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM utilisateur");
            while (res.next())
            {
                String name = res.getString("nom");
                System.out.println(name);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProjetJava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getBDDPrenom()
    {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://93.3.238.99:3307/patissier","PROJET","Azertyu12!");
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT nom,prenom FROM utilisateur");
            while (res.next())
            {
                String name = res.getString("prenom");
                System.out.println(name);


            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProjetJava.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    ///TEST UtilisateurDAO
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateur test;
        String nom = "LAMBERT";
        String prenom = "Arnaud";
        String email = "abc.def@ghi.wtf";
        String mdp = "123456";
        test = utilisateurDAO.create(new Utilisateur(nom, prenom, email), mdp);
        System.out.println(test.toString());
        test = utilisateurDAO.connexion(email, mdp);
        System.out.println(test.toString());
        email = "abc.def@ghi.wtf2";
        test.setEmail(email);
        System.out.println(utilisateurDAO.update(test));
        System.out.println(utilisateurDAO.emailExistant(email));
        test = utilisateurDAO.connexion(email, mdp);
        System.out.println(test.toString());
        String nouveauMdp = "abcdef";
        System.out.println(utilisateurDAO.modifierMotDePasse(test, mdp, nouveauMdp));
        test = utilisateurDAO.connexion(email, nouveauMdp);
        System.out.println(test.toString());
        System.out.println(utilisateurDAO.delete(test));
        test = utilisateurDAO.connexion(email, nouveauMdp);
        System.out.println(test.toString());

        utilisateurDAO.close();

        System.exit(0);
    }
    //TEST ProduitDAO
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ProduitDAO dao = new ProduitDAO();
        Produit test;
        String nom = "Clavier";
        String categorie = "Peripheriques";
        String nomFournisseur = "Corsair";
        double prixUnitaire = 3.50;
        int stock = 30;
        int quantiteUnLot = 10;
        double prixUnLot = 25.0;
        double promotion = 0.1;
        boolean promotionActive = false;
        String lienImage = "corsair/clavier.png";
        test = dao.create(new Produit(nom, categorie, nomFournisseur, prixUnitaire, stock, quantiteUnLot, prixUnLot, promotion, promotionActive, lienImage));
        System.out.println(test.toString());
        prixUnitaire = 3.0;
        test.setPrixUnitaire(prixUnitaire);
        stock = 29;
        test.setStock(stock);
        System.out.println(dao.stockSuffisant(test,30));
        System.out.println(dao.update(test));
        test = dao.find(test.getId());
        System.out.println(test.toString());
        System.out.println(dao.stockSuffisant(test,30));

        dao.getProduits().forEach((p) ->
        {
            System.out.println(p.toString());
        });

        dao.getProduits(categorie).forEach((p) ->
        {
            System.out.println(p.toString());
        });

        System.out.println(dao.delete(test));

        dao.getProduits(categorie).forEach((p) ->
        {
            System.out.println(p.toString());
        });

        dao.getProduits().forEach((p) ->
        {
            System.out.println(p.toString());
        });

        dao.close();

        System.exit(0);
    }

    //TEST ProduitCommandeDAO NECESSITE DE REMETTRE PUBLIC ProduitCommandeDAO()
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ProduitDAO pdao = new ProduitDAO();

        Produit p = pdao.find(2);
        Commande c = new Commande();
        c.setId(1);

        ProduitCommandeDAO dao = new ProduitCommandeDAO();
        int idCommande = 1;
        int quantite = 25;

        Pair<Produit,Integer> pc = new Pair<>(p,quantite);
        pc = dao.create(pc, idCommande);
        System.out.println(pc.getKey().toString() + "\n Quantite = " + pc.getValue());

        c.addProduitsCommande(pc);
        System.out.println(c.toString());

        quantite = 24;
        System.out.println(dao.update(new Pair<>(p,idCommande), quantite));
        pc = dao.find(p.getId(), idCommande);
        System.out.println(pc.getKey().toString() + "\n Quantite = " + pc.getValue());
        c.setProduitsCommande(dao.getProduitsCommande(idCommande));
        System.out.println(c.toString());
        System.out.println(dao.delete(new Pair<>(p,idCommande)));
        pc = dao.find(p.getId(), idCommande);
        System.out.println(pc.getKey().toString() + "\n Quantite = " + pc.getValue());

        dao.close();

        System.exit(0);
    }

    //TEST CommandeDAO
    @Override
        public void start(Stage primaryStage) throws Exception
        {
            ProduitDAO pdao = new ProduitDAO();

            Produit p = pdao.find(2);
            Produit p2 = pdao.find(3);
            Produit p3;
            String nom = "Clavier";
            String categorie = "Peripheriques";
            String nomFournisseur = "Corsair";
            double prixUnitaire = 3.50;
            int stock = 30;
            int quantiteUnLot = 10;
            double prixUnLot = 25.0;
            double promotion = 0.1;
            boolean promotionActive = false;
            String lienImage = "corsair/clavier.png";
            p3 = pdao.create(new Produit(nom, categorie, nomFournisseur, prixUnitaire, stock, quantiteUnLot, prixUnLot, promotion, promotionActive, lienImage));

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(10);

            CommandeDAO dao = new CommandeDAO();
            Commande c = new Commande("10 rue eugene sue rueil");

            c.addProduitCommande(new Pair<>(p, 23));
            c.addProduitCommande(new Pair<>(p2, 18));

            c = dao.create(c, utilisateur);
            System.out.println(c.toString());

            dao.getCommandes().forEach((com) ->
            {
                System.out.println(com.toString());
            });

            c.setAdresse("45 rue merlin de thionvilles");
            c.addProduitCommande(new Pair<>(p2, 43));
            c.addProduitCommande(new Pair<>(p3, 14));
            c.removeProduitCommande(p);

            System.out.println(dao.update(c));

            dao.getCommandes(utilisateur.getId()).forEach((com) ->
            {
                System.out.println(com.toString());
            });

            System.out.println(dao.delete(c));
            c = dao.find(c.getId());
            System.out.println(c.toString());

            pdao.delete(p3);

            dao.close();

            System.exit(0);
        }
