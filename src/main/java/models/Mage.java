package main.java.models;

public class Mage extends Personnage {
    private Sort sortEquipe1;
    private Sort sortEquipe2;

    public Mage(String nom, float pv, float pm, int niv) {
        super(nom, pv, pm, niv, "mage.jpg", 2, 0);
        Sort sortDEOUF = new BouleDeFeu();
        Sort sortDEMERDE = new ExplosionDeFeu();
        this.ajouterSort(sortDEOUF);
        this.ajouterSort(sortDEMERDE);
        this.sortEquipe1 = sortDEOUF;
        this.sortEquipe2 = sortDEMERDE;
    }

    public Mage() {
        super("Mage", 250, 1000, 1, "mage.jpg", 2, 0);
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
    public Sort getSortEquipe() {
        return sortEquipe1;
    }

    @Override
    public void lancerSort(Personnage personnage) {
        attaquerSort(sortEquipe1, personnage);
    }

    void attaquerSort(Sort sort, Personnage p) {
        p.recevoirDegats(sort.getNbDegats());
        this.consommerMana(sort.getCoutMana());
    }

    @Override
    public void recevoirDegats(float degats) {
        this.enleverPv(degats);
    }

    @Override
    public boolean aSortEquipe(Sort sort) {
        if (sort == sortEquipe1 || sort == sortEquipe2)
            return true;
        return false;
    }

    @Override
    public void deEquipeSort(Sort sort) {
        if (sort == this.sortEquipe1)
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
}
