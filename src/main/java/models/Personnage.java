package main.java.models;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import main.java.models.exceptions.ManaNegatifException;
import main.java.models.exceptions.ManaOutofBoundException;
import main.java.models.exceptions.PersonnageMortException;
import main.java.models.exceptions.PlusDeFlecheException;

import java.util.ArrayList;
import java.util.List;

public abstract class Personnage {
    private String nom;

    //on utilise des objets "Property" pour que l'affichage soit toujours au meme stade que les attributs de personnage
    private final FloatProperty pv;
    private final FloatProperty pvMax;

    private final FloatProperty pm;
    private final FloatProperty pmMax;
    private final float regenPm;

    private final IntegerProperty niv;

    private float pieces = 0;

    private final String urlImage;

    private final int nombreSortsEquipable;
    private final List<Arme> listeArmes;

    private final int nombreEquipementsEquipable;
    private final List<Sort> listeSorts;

    // constructeur
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

    abstract public Sort getSortEquipe();

    abstract public String getNomAction1();

    abstract public float getDegatsAction1();

    public float getCoutManaAction1() {
        return 0;
    }

    public float getCoutManaAction2() {
        return getSortEquipe().getCoutMana();
    }

    abstract public void action1(Personnage personnage) throws PersonnageMortException, ManaNegatifException, PlusDeFlecheException;

    abstract public void action2(Personnage personnage) throws PersonnageMortException, ManaNegatifException;

    abstract public void recevoirDegats(float degats) throws PersonnageMortException;

    public void enleverPv(float pv) throws PersonnageMortException {
        if (this.getPv() - pv <= 0) throw new PersonnageMortException("pv < 0");
        this.pv.setValue(this.getPv() - pv);
    }

    public void consommerMana(float mana) throws ManaNegatifException {
        if (this.getPm() - mana < 0) throw new ManaNegatifException("mana < 0");
        this.pm.setValue(this.getPm() - mana);
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

    public void setPm(float pm) throws ManaNegatifException, ManaOutofBoundException {
        if (pm < 0) throw new ManaNegatifException("mana < 0");
        if (pm > this.getPmMax()) throw new ManaOutofBoundException("mana > manaMax");
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

    public abstract String toString();

    public abstract String infoArmesEquipables();

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPmAZero() {
        pm.setValue(0);
    }

    public void setPmAMax() {
        this.pm.setValue(this.getPmMax());
    }

    public void setPvAMax() {
        this.pv.setValue(this.getPvMax());
    }

    public void regenPm() {
        try {
            this.setPm(this.getPm() + getRegenPm());
        } catch (ManaNegatifException e) {
            this.setPmAZero();
        } catch (ManaOutofBoundException e) {
            this.setPmAMax();
        }
    }

    public void setPvAZero() {
        this.pv.setValue(0);
    }
}