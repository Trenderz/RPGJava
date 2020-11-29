package main.java.models;

import java.util.ArrayList;
import java.util.List;

public class Archer extends Personnage {

    List<ArmeDistance> listeArmes;
    List<Fleche> listeFleches;
    ArmeDistance armeEquipee;
    private Sort sortEquipe;

    public Archer(String nom, float pv, float pm,float regenPm, int niv, String urlImage) {
        super(nom, pv, pm,regenPm, niv, urlImage, 2, 0);
        this.listeArmes = new ArrayList<>();
        this.listeFleches = new ArrayList<>();
        for (int i = 0; i < 25; i++)
            listeFleches.add(new Fleche());
        Arc arc = new Arc();
        this.listeArmes.add(arc);
        armeEquipee = arc;
        VoleeFleches volee = new VoleeFleches();
        this.sortEquipe = volee;
        this.ajouterSort(volee);
    }

    public Archer() {
        super("Archer", 350, 75,7, 1, "archer.jpg", 1, 1);
        this.listeArmes = new ArrayList<>();
        this.listeFleches = new ArrayList<>();
        for (int i = 0; i < 25; i++)
            listeFleches.add(new Fleche());
        Arc arc = new Arc();
        this.listeArmes.add(arc);
        armeEquipee = arc;
        VoleeFleches volee = new VoleeFleches();
        this.sortEquipe = volee;
        this.ajouterSort(volee);
    }

    @Override
    public String getUrlImageAction1() {
        return this.armeEquipee.getUrlImage();
    }

    @Override
    public String getUrlImageAction2() {
        return this.sortEquipe.getUrlImage();
    }
    void ajouterArme(ArmeDistance arme) {
        this.listeArmes.add(arme);
    }

    @Override
    public Sort getSortEquipe() {
        return sortEquipe;
    }

    @Override
    public String getNomAction1() {
        return " utilise son " + this.armeEquipee.getNom();
    }

    @Override
    public float getDegatsAction1() {
        return this.armeEquipee.getMultiplicateur() * listeFleches.get(0).getNbDegats();
    }

    @Override
    public void action1(Personnage personnage) {
        attaquerDistance(armeEquipee,this.listeFleches.get(0), personnage);
    }

    @Override
    public void action2(Personnage personnage) {
        attaquerSort(sortEquipe, personnage);
    }

    void attaquerSort(Sort sort, Personnage p) {
        if (this.getPm() >= sort.getCoutMana()){
            p.recevoirDegats(sort.getNbDegats());
            this.consommerMana(sort.getCoutMana());
        }
    }

    public void attaquerDistance(ArmeDistance arme, Fleche fleche, Personnage personnage) {
        personnage.recevoirDegats(fleche.getNbDegats() * arme.getMultiplicateur());
        this.listeFleches.remove(fleche);
    }

    @Override
    public void recevoirDegats(float degats) {
        this.enleverPv(degats);
    }

    @Override
    public boolean aSortEquipe(Sort sort) {
        if (sort == this.sortEquipe)
            return true;
        return false;
    }

    @Override
    public void deEquipeSort(Sort sort) {
        this.sortEquipe = null;
    }

    @Override
    public void equiperSort(Sort sort) {
        this.sortEquipe = sort;
    }

    @Override
    public boolean aArmeEquipe(Arme arme) {
        return arme == this.armeEquipee;
    }

    @Override
    public void deEquipeArme(Arme arme) {
        this.armeEquipee = null;
    }

    @Override
    public void equiperArme(Arme arme) {
        this.armeEquipee = (ArmeDistance) arme;
    }

    @Override
    public boolean peuxEquiperArme(Arme arme) {
        return arme instanceof ArmeDistance && null == this.armeEquipee;
    }

    @Override
    public void regenPm() {
        if (this.getPm() + this.getRegenPm() <= this.getPmMax()){
            this.setPm(this.getPm() + getRegenPm());
        }
        else{
            this.setPm(this.getPmMax());
        }
    }
}
