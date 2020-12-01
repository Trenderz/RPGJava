package main.java.models;

import main.java.models.armes.Arc;
import main.java.models.armes.Bouclier;
import main.java.models.armes.Epee;
import main.java.models.sorts.CriDeGuerre;
import main.java.models.sorts.VoleeFleches;

public class Guerrier extends Personnage {

    private ArmeCAC armeCACEquipee;
    private EquipementDefensif equipementDefensifEquipee;
    private Sort sortEquipe;

    public Guerrier(String nom, float pv, float pm, float regenPm, int niv, String urlImage) {
        super(nom, pv, pm,regenPm, niv, urlImage, 1, 2);
        Epee epee = new Epee();
        this.ajouterArme(epee);
        this.armeCACEquipee = epee;
        Bouclier bouclier = new Bouclier();
        this.equipementDefensifEquipee = bouclier;
        this.ajouterArme(bouclier);
        CriDeGuerre criDeGuerre = new CriDeGuerre();
        this.sortEquipe = criDeGuerre;
        this.ajouterSort(criDeGuerre);
    }

    public Guerrier() {
        super("Guerrier", 500, 35,4, 1, "guerrier.jpg", 1, 2);
        Epee epee = new Epee();
        this.ajouterArme(epee);
        this.armeCACEquipee = epee;
        Bouclier bouclier = new Bouclier();
        this.equipementDefensifEquipee = bouclier;
        this.ajouterArme(bouclier);
        this.ajouterArme(new Arc());
        this.ajouterSort(new VoleeFleches());
        this.ajouterArme(new Bouclier());
        CriDeGuerre criDeGuerre = new CriDeGuerre();
        this.sortEquipe = criDeGuerre;
        this.ajouterSort(criDeGuerre);
    }

    @Override
    public void action1(Personnage personnage) {
        attaquerCAC(armeCACEquipee, personnage);
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

    public void attaquerCAC(ArmeCAC arme, Personnage p) {
        p.recevoirDegats(arme.getNbDegats());
    }

    @Override
    public void recevoirDegats(float degats) {
        this.enleverPv(degats - equipementDefensifEquipee.getReductionDegats());
    }

    public ArmeCAC getArmeCACEquipee() {
        return armeCACEquipee;
    }

    public void setArmeCACEquipee(ArmeCAC armeCACEquipee) {
        this.armeCACEquipee = armeCACEquipee;
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
        return this.armeCACEquipee == arme || this.equipementDefensifEquipee == arme;
    }

    @Override
    public void deEquipeArme(Arme arme) {
        if (this.armeCACEquipee == arme) {
            this.armeCACEquipee = null;
        } else {
            this.equipementDefensifEquipee = null;
        }
    }

    @Override
    public void equiperArme(Arme arme) {
        if (arme instanceof ArmeCAC) {
            this.armeCACEquipee = (ArmeCAC) arme;
        } else {
            this.equipementDefensifEquipee = (EquipementDefensif) arme;
        }
    }

    @Override
    public boolean peuxEquiperArme(Arme arme) {
        if (arme instanceof ArmeCAC && null == this.armeCACEquipee)
            return true;
        if (arme instanceof EquipementDefensif && null == this.equipementDefensifEquipee)
            return true;
        return false;

    }

    @Override
    public void regenPm() {
        if (this.getPm() + this.getRegenPm() <= this.getPmMax()){
            this.setPm(this.getPm() + this.getRegenPm());
        }
        else{
            this.setPm(this.getPmMax());
        }
    }

    @Override
    public String toString() {
        String info;
        info = "Niveau : " + this.getNiv();
        info += "\nArme : " + this.armeCACEquipee.getNom();
        info += "\nSort : " + this.sortEquipe.getNom();
        info += "\nRéduction dégats : " + this.equipementDefensifEquipee.getReductionDegats();
        return info;
    }

    @Override
    public String infoArmesEquipables() {
        return "votre classe ne peux équiper que des Armes CAC et des boucliers";
    }


    @Override
    public String getUrlImageAction1() {
        return this.armeCACEquipee.getUrlImage();
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
        return " utilise " + this.armeCACEquipee.getNom();
    }

    @Override
    public float getDegatsAction1() {
        return this.armeCACEquipee.getNbDegats();
    }

}
