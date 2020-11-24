package main.java.models;

public class Partie {
    private Personnage personnage;
    private Personnage ennemi;

    public Partie(Personnage personnage, Personnage ennemi){
        this.personnage= personnage;
        this.ennemi= ennemi;
    }

    public void combat(){
        if (this.personnage.getPv()>0 && this.ennemi.getPv()>0){
            System.out.println("combat en cours");
        }
    }
}
