package main.java.models;

import main.java.models.armes.Arc;
import main.java.models.armes.Fleche;
import main.java.models.exceptions.ManaNegatifException;
import main.java.models.exceptions.PersonnageMortException;
import main.java.models.exceptions.PlusDeFlecheException;
import main.java.models.sorts.VoleeFleches;

import java.util.ArrayList;
import java.util.List;

public class Archer extends Personnage {

    private final List<Fleche> listeFleches;
    private ArmeDistance armeEquipee;
    private Sort sortEquipe;

    public Archer(String nom, float pv, float pm, float regenPm, int niv, String urlImage) {
        super(nom, pv, pm, regenPm, niv, urlImage, 1, 1);
        this.listeFleches = new ArrayList<>();
        for (int i = 0; i < 40; i++)
            listeFleches.add(new Fleche());
        Arc arc = new Arc();
        armeEquipee = arc;
        VoleeFleches volee = new VoleeFleches();
        this.sortEquipe = volee;
        this.ajouterSort(volee);
        this.ajouterArme(arc);
    }

    public Archer() {
        super("Archer", 350, 75, 7, 1, "archer.jpg", 1, 1);
        this.listeFleches = new ArrayList<>();
        for (int i = 0; i < 40; i++)
            listeFleches.add(new Fleche());
        Arc arc = new Arc();
        armeEquipee = arc;
        VoleeFleches volee = new VoleeFleches();
        this.sortEquipe = volee;
        this.ajouterSort(volee);
        this.ajouterArme(arc);

    }

    @Override
    public String getUrlImageAction1() {
        return this.armeEquipee.getUrlImage();
    }

    @Override
    public String getUrlImageAction2() {
        return this.sortEquipe.getUrlImage();
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
    public void action1(Personnage personnage) throws PersonnageMortException, PlusDeFlecheException {
        attaquerDistance(armeEquipee, this.listeFleches.get(0), personnage);
    }

    @Override
    public void action2(Personnage personnage) throws PersonnageMortException, ManaNegatifException {
        attaquerSort(sortEquipe, personnage);
    }

    void attaquerSort(Sort sort, Personnage p) throws PersonnageMortException, ManaNegatifException {
        if (this.getPm() >= sort.getCoutMana()) {
            p.recevoirDegats(sort.getNbDegats());
            this.consommerMana(sort.getCoutMana());
        }
    }

    public void attaquerDistance(ArmeDistance arme, Fleche fleche, Personnage personnage) throws PersonnageMortException, PlusDeFlecheException {
        if (this.listeFleches.isEmpty()) throw new PlusDeFlecheException("fleche a zero");
        personnage.recevoirDegats(fleche.getNbDegats() * arme.getMultiplicateur());
        this.listeFleches.remove(fleche);
    }

    @Override
    public void recevoirDegats(float degats) throws PersonnageMortException {
        this.enleverPv(degats);
    }

    @Override
    public boolean aSortEquipe(Sort sort) {
        return sort.equals(this.sortEquipe);
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
        return arme.equals(this.armeEquipee);
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
    public String toString() {
        String info;
        info = "Niveau : " + this.getNiv();
        info += "\nArme : " + this.armeEquipee.getNom();
        info += "\nSort : " + this.sortEquipe.getNom();
        info += "\nNombre de Flèches : " + this.listeFleches.size();
        return info;
    }

    @Override
    public String infoArmesEquipables() {
        return "Votre classe ne peux équiper que des Armes à distance";
    }

    public void remplirFleche() {
        for (int i = this.listeFleches.size(); i < 40; i++)
            listeFleches.add(new Fleche());
    }
}
