package main.java.models;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public abstract class Personnage {
    private String nom;

    private FloatProperty pv;
    private FloatProperty pvMax;

    private FloatProperty pm;
    private FloatProperty pmMax;

    private IntegerProperty niv;

    private String urlImage;

    private int nombreSortsEquipable;
    private List<Arme> listeArmes;

    private int nombreEquipementsEquipable;
    private List<Sort> listeSorts;


    public Personnage(String nom, float pv, float pm, int niv, String urlImage, int nombreSortsEquipable, int nombreEquipementEquipable) {
        this.nom = nom;
        this.pv = new SimpleFloatProperty(pv);
        this.pvMax = new SimpleFloatProperty(pv);
        this.pm = new SimpleFloatProperty(pm);
        this.pmMax = new SimpleFloatProperty(pm);
        this.niv = new SimpleIntegerProperty(niv);
        this.urlImage = urlImage;
        this.nombreSortsEquipable = nombreSortsEquipable;
        this.listeSorts = new ArrayList<>();
        this.nombreEquipementsEquipable = nombreEquipementEquipable;
        this.listeArmes = new ArrayList<>();
    }

    abstract public Sort getSortEquipe();

    abstract public void lancerSort(Personnage personnage);

    abstract public void recevoirDegats(float degats);

    public void enleverPv(float pv) {
        this.pv.setValue(this.pv.get() - pv);
    }

    public void consommerMana(float mana) {
        this.pm.setValue(pm.get() - mana);
    }

    public float getPv() {
        return this.pv.get();
    }

    public FloatProperty getPvProperty() {
        return this.pv;
    }

    public void setPv(float pv) {
        this.pv.setValue(pv);
    }

    public FloatProperty getPvMaxProperty() {
        return this.pvMax;
    }

    public String getUrlImage() {
        return this.urlImage;
    }

    public float getPm() {
        return this.pm.get();
    }

    public FloatProperty getPmProperty() {
        return this.pm;
    }

    public void setPm(float pm) {
        this.pm.setValue(pm);
    }

    public FloatProperty getPmMaxProperty() {
        return this.pmMax;
    }

    public int getNiv() {
        return this.niv.get();
    }

    public IntegerProperty getNivProperty() {
        return this.niv;
    }

    public String getNom() {
        return this.nom;
    }

    public int getNombreSortsEquipable() {
        return this.nombreSortsEquipable;
    }

    public List<Sort> getListeSorts() {
        return listeSorts;
    }

    public int getNombreEquipementsEquipable() {
        return this.nombreEquipementsEquipable;
    }

    public List<Arme> getListeArmes() {
        return listeArmes;
    }

    public void ajouterArme(Arme arme) {
        this.listeArmes.add(arme);
    }

    public void ajouterSort(Sort criDeGuerre) {
        this.listeSorts.add(criDeGuerre);
    }

    public abstract boolean aSortEquipe(Sort sort);

    public abstract void deEquipeSort(Sort sort);

    public abstract void equiperSort(Sort sort);

    public abstract boolean aArmeEquipe(Arme arme);

    public abstract void deEquipeArme(Arme arme);

    public abstract void equiperArme(Arme arme);

    public abstract boolean peuxEquiperArme(Arme arme);
}