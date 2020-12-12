package main.java.models;

import main.java.models.armes.Bouclier;
import main.java.models.armes.Epee;
import main.java.models.exceptions.ManaNegatifException;
import main.java.models.exceptions.PersonnageMortException;
import main.java.models.sorts.CriDeGuerre;

public class Guerrier extends Personnage {

    private ArmeCAC armeCACEquipee;
    private EquipementDefensif equipementDefensifEquipee;
    private Sort sortEquipe;

    public Guerrier(String nom, float pv, float pm, float regenPm, int niv, String urlImage) {
        super(nom, pv, pm, regenPm, niv, urlImage, 1, 2);
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

    public Guerrier(String nom, float pv, float pm, float regenPm, int niv, String urlImage,
                    ArmeCAC arme, EquipementDefensif equipementDefensif, Sort sort) {
        super(nom, pv, pm, regenPm, niv, urlImage, 1, 2);
        this.armeCACEquipee = arme;
        this.equipementDefensifEquipee = equipementDefensif;
        this.sortEquipe = sort;
    }

    public Guerrier() {
        super("Guerrier", 500, 35, 6, 1, "guerrier.jpg", 1, 2);
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

    @Override
    public void action1(Personnage personnage) throws PersonnageMortException {
        attaquerCAC(armeCACEquipee, personnage);
    }

    @Override
    public void action2(Personnage personnage) throws ManaNegatifException, PersonnageMortException {
        attaquerSort(sortEquipe, personnage);
    }

    void attaquerSort(Sort sort, Personnage p) throws ManaNegatifException, PersonnageMortException {
        if (this.getPm() >= sort.getCoutMana()) {
            this.consommerMana(sort.getCoutMana());
            p.recevoirDegats(sort.getNbDegats());
        }
    }

    public void attaquerCAC(ArmeCAC arme, Personnage p) throws PersonnageMortException {
        p.recevoirDegats(arme.getNbDegats());
    }

    @Override
    public void recevoirDegats(float degats) throws PersonnageMortException {
        if (degats - equipementDefensifEquipee.getReductionDegats() > 0){
            this.enleverPv(degats - equipementDefensifEquipee.getReductionDegats());
        }
    }

    @Override
    public boolean aSortEquipe(Sort sort) {
        return sort.equals(sortEquipe);
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
        return arme.equals(this.armeCACEquipee) || arme.equals(this.equipementDefensifEquipee);
    }

    @Override
    public void deEquipeArme(Arme arme) {
        if (arme.equals(this.armeCACEquipee)) {
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
        return arme instanceof EquipementDefensif && null == this.equipementDefensifEquipee;

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
        return "Votre classe ne peux équiper que des Armes CAC et des boucliers";
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
