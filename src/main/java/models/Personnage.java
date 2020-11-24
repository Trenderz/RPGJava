package main.java.models;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {
    private String nom;
    private FloatProperty pv;
    private FloatProperty pvMax;
    private FloatProperty pm;
    private FloatProperty pmMax;
    private IntegerProperty niv;
    private String urlImage;


    public Personnage(String nom, float pv, float pm, int niv, String urlImage) {
        this.nom = nom;
        this.pv = new SimpleFloatProperty(pv);
        this.pvMax = new SimpleFloatProperty(pv);
        this.pm = new SimpleFloatProperty(pm);
        this.pmMax = new SimpleFloatProperty(pm);
        this.niv = new SimpleIntegerProperty(niv);
        this.urlImage = urlImage;
    }

    abstract public Sort getSort();

    abstract public void lancerSort(Personnage personnage);

    abstract public void recevoirDegats(float degats);

    public void enleverPv(float pv){
        this.pv.setValue(this.pv.get()-pv);
    }

    public void consommerMana(float mana) {
        this.pm.setValue(pm.get()-mana);
    }

    public float getPv() {
        return this.pv.get();
    }

    public FloatProperty getPvProperty(){
        return this.pv;
    }

    public void setPv(float pv) {
        this.pv.setValue(pv);
    }

    public FloatProperty getPvMaxProperty(){
        return this.pvMax;
    }

    public String getUrlImage() {
        return this.urlImage;
    }

    public float getPm() {
        return this.pm.get();
    }

    public FloatProperty getPmProperty(){
        return this.pm;
    }

    public void setPm(float pm) {
        this.pm.setValue(pm);
    }

    public FloatProperty getPmMaxProperty(){
        return this.pmMax;
    }

    public int getNiv() {
        return this.niv.get();
    }

    public IntegerProperty getNivProperty(){
        return this.niv;
    }

    public String getNom(){
        return this.nom;
    }
}