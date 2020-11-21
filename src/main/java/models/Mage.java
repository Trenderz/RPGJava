package main.java.models;

import java.util.ArrayList;
import java.util.List;

public class Mage extends Personnage{
    private List<Sort> listeSorts;
    private Sort sort1;
    private Sort sort2;

    public Mage(float pv, float pm, float niv) {
        super(pv, pm, niv);
        listeSorts = new ArrayList<>();
        Sort sortDEOUF = new BouleDeFeu();
        Sort sortDEMERDE = new ExplosionDeFeu();
        listeSorts.add(sortDEOUF);
        listeSorts.add(sortDEMERDE);
        this.sort1 = sortDEOUF;
        this.sort2 = sortDEMERDE;
    }

    @Override
    void action(Personnage personnage) {
        attaquerSort(this.sort1,personnage);
    }

    void attaquerSort(Sort sort,Personnage p){
        p.recevoirDegats(sort.getNbDegats());
        this.consommerMana(sort.getCoutMana());
    }

    @Override
    void recevoirDegats(float degats) {

    }
}
