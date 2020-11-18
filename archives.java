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
