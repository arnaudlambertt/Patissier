/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL.DAO.utilisateur;

/**
 *
 * @author Benjamin
 */
public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String role;
    
    public Utilisateur(){}
    
    //Constructeur avec tout les paramètres
    
    public Utilisateur(int id, String nom, String prenom, String email, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email=email;
        this.role=role;
    }
    
    //Constructeur avec tout les paramètres sauf identifiant parce qu'il est auto incrémenté dans la base de donnée
    public Utilisateur(String nom, String prenom, String email, String role) {
        this.id = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.email=email;
        this.role=role;
    }
    
    public Utilisateur( String email) {
        this.id = 0;
        this.nom = "";
        this.prenom = "";
        this.email=email;
        this.role="Utilisateur";
        
    }
    
   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString(){
        String str = "ID : " + this.getId()+ "\n";
        str += "NOM : " + this.getNom() + "\n";
        str += "PRENOM : " + this.getPrenom() + "\n";
        str += "EMAIL : " + this.getEmail()+ "\n";
        str += "ROLE : " + this.getRole()+ "\n";
        str += "\n.....................................\n";
        
        return str;
    }
}
