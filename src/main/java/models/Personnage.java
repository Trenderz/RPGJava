package main.java.models;

public abstract class Personnage {
    float pv;
    float pm;
    float niv;

    public Personnage(float pv, float pm, float niv) {
        this.pv = pv;
        this.pm = pm;
        this.niv = niv;
    }

    abstract void action(Personnage personnage);

    abstract void recevoirDegats(float degats);

    public void consommerMana(float mana){
        this.pm -= mana;
    }
}