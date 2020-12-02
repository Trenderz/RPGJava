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
    private float regenPm;

    private IntegerProperty niv;

    private float pieces = 0;

    private String urlImage;

    private int nombreSortsEquipable;
    private List<Arme> listeArmes;

    private int nombreEquipementsEquipable;
    private List<Sort> listeSorts;

    private String urlImageAction1;
    private String urlImageAction2;

    public Personnage(String nom, float pv, float pm, float regenPm, int niv, String urlImage, int nombreSortsEquipable, int nombreEquipementEquipable) {
        this.nom = nom;
        this.pv = new SimpleFloatProperty(pv);
        this.pvMax = new SimpleFloatProperty(pv);
        this.pm = new SimpleFloatProperty(pm);
        this.pmMax = new SimpleFloatProperty(pm);
        this.regenPm = regenPm;
        this.niv = new SimpleIntegerProperty(niv);
        this.urlImage = urlImage;
        this.nombreSortsEquipable = nombreSortsEquipable;
        this.listeSorts = new ArrayList<>();
        this.nombreEquipementsEquipable = nombreEquipementEquipable;
        this.listeArmes = new ArrayList<>();
    }


    abstract public String getUrlImageAction1();

    abstract public String getUrlImageAction2();

    public void setUrlImageAction1(String urlImageAction1) {
        this.urlImageAction1 = urlImageAction1;
    }

    public void setUrlImageAction2(String urlImageAction2) {
        this.urlImageAction2 = urlImageAction2;
    }

    abstract public Sort getSortEquipe();

    abstract public String getNomAction1();

    abstract public float getDegatsAction1();

    public float getCoutManaAction1() {
        return 0;
    }

    public float getCoutManaAction2() {
        return getSortEquipe().getCoutMana();
    }

    abstract public void action1(Personnage personnage);

    abstract public void action2(Personnage personnage);

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

    public float getPvMax() {
        return this.pvMax.get();
    }

    public void setPvMax(float pvMax) {
        this.pvMax.setValue(pvMax);
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

    public float getPmMax() {
        return this.pmMax.get();
    }

    public void setPmMax(float pvMax) {
        this.pmMax.setValue(pvMax);
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

    public void setNiv(int niv) {
        this.niv.setValue(niv);
    }

    public IntegerProperty getNivProperty() {
        return this.niv;
    }

    public float getPieces() {
        return this.pieces;
    }

    public void setPieces(float pieces) {
        this.pieces = pieces;
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

    public void ajouterSort(Sort sort) {
        this.listeSorts.add(sort);
    }

    public abstract boolean aSortEquipe(Sort sort);

    public abstract void deEquipeSort(Sort sort);

    public abstract void equiperSort(Sort sort);

    public abstract boolean aArmeEquipe(Arme arme);

    public abstract void deEquipeArme(Arme arme);

    public abstract void equiperArme(Arme arme);

    public abstract boolean peuxEquiperArme(Arme arme);

    public float getRegenPm() {
        return this.regenPm;
    }

    public abstract void regenPm();

    public abstract String toString();

    public abstract String infoArmesEquipables();

    public void setNom(String nom) {
        this.nom = nom;
    }
}