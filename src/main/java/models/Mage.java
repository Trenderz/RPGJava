package main.java.models;

import java.util.ArrayList;
import java.util.List;

public class Mage extends Personnage{
    private List<Sort> listeSorts;
    private Sort sort1;
    private Sort sort2;

    public Mage(String nom, float pv, float pm, int niv) {
        super(nom, pv, pm, niv,"mage.jpg");
        listeSorts = new ArrayList<>();
        Sort sortDEOUF = new BouleDeFeu();
        Sort sortDEMERDE = new ExplosionDeFeu();
        listeSorts.add(sortDEOUF);
        listeSorts.add(sortDEMERDE);
        this.sort1 = sortDEOUF;
        this.sort2 = sortDEMERDE;
    }

    public Mage() {
        super("Mage",250,1000,1,"mage.jpg");
        listeSorts = new ArrayList<>();
        Sort sortDEOUF = new BouleDeFeu();
        Sort sortDEMERDE = new ExplosionDeFeu();
        listeSorts.add(sortDEOUF);
        listeSorts.add(sortDEMERDE);
        this.sort1 = sortDEOUF;
        this.sort2 = sortDEMERDE;
    }

    @Override
    public Sort getSort() {
        return sort1;
    }

    @Override
    public void lancerSort(Personnage personnage) {
        attaquerSort(sort1, personnage);
    }

    void attaquerSort(Sort sort,Personnage p){
        p.recevoirDegats(sort.getNbDegats());
        this.consommerMana(sort.getCoutMana());
    }

    @Override
    public void recevoirDegats(float degats) {
        this.enleverPv(degats);
    }
}
