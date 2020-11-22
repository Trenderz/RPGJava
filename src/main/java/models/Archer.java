package main.java.models;

import java.util.ArrayList;
import java.util.List;

public class Archer extends Personnage {

    List<ArmeDistance> listeArmes;
    List<Fleche> listeFleches;
    ArmeDistance armeEquipee;

    public Archer() {
        super("Archer",350, 75, 1,"file:src/main/resources/archer.jpg");
        this.listeArmes = new ArrayList<>();
        this.listeFleches = new ArrayList<>();
        for (int i = 0; i < 25; i++)
            listeFleches.add(new Fleche());
        Arc arc = new Arc();
        this.listeArmes.add(arc);
        armeEquipee = arc;
    }

    void ajouterArme(ArmeDistance arme) {
        this.listeArmes.add(arme);
    }

    @Override
    void action(Personnage personnage) {
        attaquerDistance(armeEquipee, listeFleches.get(listeFleches.size() - 1), personnage);
    }

    void attaquerSort(Sort sort,Personnage p){
        p.recevoirDegats(sort.getNbDegats());
        this.consommerMana(sort.getCoutMana());
    }

    public void attaquerDistance(ArmeDistance arme, Fleche fleche, Personnage personnage) {
        personnage.recevoirDegats(fleche.getNbDegats() * arme.getMultiplicateur());
        this.listeFleches.remove(fleche);
    }

    @Override
    void recevoirDegats(float degats) {
        this.setPv(this.getPv()-degats);
    }
}
