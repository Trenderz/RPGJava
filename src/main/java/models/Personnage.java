package main.java.models;

public abstract class Personnage {
    private String nom;
    private float pv;
    private float pm;
    private float niv;
    private String urlImage;


    public Personnage(String nom, float pv, float pm, float niv, String urlImage) {
        this.nom = nom;
        this.pv = pv;
        this.pm = pm;
        this.niv = niv;
        this.urlImage = urlImage;
    }

    abstract void action(Personnage personnage);

    abstract void recevoirDegats(float degats);

    public void consommerMana(float mana) {
        this.pm -= mana;
    }

    public float getPv() {
        return this.pv;
    }

    public void setPv(float pv) {
        this.pv = pv;
    }

    public String getImage(){
        return this.urlImage;
    }
}