package main.java.models;

import main.java.models.sorts.BouleDeFeu;
import main.java.models.sorts.ExplosionDeFeu;
import main.java.models.sorts.VoleeFleches;

public class Mage extends Personnage {
    private Sort sortEquipe1;
    private Sort sortEquipe2;

    public Mage(String nom, float pv, float pm, float regenPm, int niv, String urlImage) {
        super(nom, pv, pm, regenPm, niv, urlImage, 2, 0);
        Sort sortDEOUF = new BouleDeFeu();
        Sort sortDEMERDE = new ExplosionDeFeu();
        this.ajouterSort(sortDEOUF);
        this.ajouterSort(sortDEMERDE);
        this.sortEquipe1 = sortDEOUF;
        this.sortEquipe2 = sortDEMERDE;
    }

    public Mage() {
        super("Mage", 250, 1000, 30, 1, "mage.jpg", 2, 0);
        Sort sortDEOUF = new BouleDeFeu();
        Sort sortDEMERDE = new ExplosionDeFeu();
        this.ajouterSort(sortDEOUF);
        this.ajouterSort(sortDEMERDE);
        this.ajouterSort(new VoleeFleches());
        this.ajouterSort(new VoleeFleches());
        this.sortEquipe1 = sortDEOUF;
        this.sortEquipe2 = sortDEMERDE;
    }


    @Override
    public String getUrlImageAction1() {
        return this.sortEquipe1.getUrlImage();
    }

    @Override
    public String getUrlImageAction2() {
        return this.sortEquipe2.getUrlImage();
    }

    @Override
    public Sort getSortEquipe() {
        return sortEquipe2;
    }

    @Override
    public String getNomAction1() {
        return " lance " + this.sortEquipe1.getNom();
    }

    @Override
    public float getDegatsAction1() {
        return this.sortEquipe1.getNbDegats();
    }


    @Override
    public void action1(Personnage personnage) {
        attaquerSort(sortEquipe1, personnage);
    }

    @Override
    public void action2(Personnage personnage) {
        attaquerSort(sortEquipe2, personnage);
    }

    void attaquerSort(Sort sort, Personnage p) {
        if (this.getPm() >= sort.getCoutMana()) {
            p.recevoirDegats(sort.getNbDegats());
            this.consommerMana(sort.getCoutMana());
        }
    }

    @Override
    public void recevoirDegats(float degats) {
        this.enleverPv(degats);
    }

    @Override
    public boolean aSortEquipe(Sort sort) {
        if (sort.equals(sortEquipe1) || sort.equals(sortEquipe2))
            return true;
        return false;
    }

    @Override
    public void deEquipeSort(Sort sort) {
        if (sort.equals(this.sortEquipe1))
            this.sortEquipe1 = null;
        else
            this.sortEquipe2 = null;
    }

    @Override
    public void equiperSort(Sort sort) {
        if (null == this.sortEquipe1)
            this.sortEquipe1 = sort;
        else
            this.sortEquipe2 = sort;
    }

    @Override
    public boolean aArmeEquipe(Arme arme) {
        return false;
    }

    @Override
    public void deEquipeArme(Arme arme) {
    }

    @Override
    public void equiperArme(Arme arme) {
    }

    @Override
    public boolean peuxEquiperArme(Arme arme) {
        return false;
    }

    @Override
    public void regenPm() {
        if (this.getPm() + this.getRegenPm() <= this.getPmMax()) {
            this.setPm(this.getPm() + this.getRegenPm());
        } else {
            this.setPm(this.getPmMax());
        }
    }

    @Override
    public String toString() {
        String info;
        info = "Niveau : " + this.getNiv();
        info += "\nSort 1 : " + this.sortEquipe1.getNom();
        info += "\nSort 2 : " + this.sortEquipe2.getNom();
        return info;
    }

    @Override
    public float getCoutManaAction1() {
        return this.sortEquipe1.getCoutMana();
    }

    @Override
    public String infoArmesEquipables() {
        return "votre classe ne peux pas équiper d'armes";
    }
}
