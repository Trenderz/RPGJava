package main.java.models;

import java.util.ArrayList;

public class Guerrier extends Personnage {

    private ArrayList<Arme> listeArmes;
    private ArmeCAC armeCACEquipee;
    private EquipementDefensif equipementDefensifEquipee;

    public Guerrier() {
        super("Guerrier", 500, 50, 1, "guerrier.jpg");
        this.listeArmes = new ArrayList<>();
        Epee epee = new Epee();
        this.listeArmes.add(epee);
        this.armeCACEquipee = epee;
        Bouclier bouclier = new Bouclier();
        this.equipementDefensifEquipee = bouclier;
        this.listeArmes.add(bouclier);
    }

    public void ajouterArme(Arme arme) {
        this.listeArmes.add(arme);
    }

    @Override
    public void action(Personnage personnage) {
        personnage.recevoirDegats(this.armeCACEquipee.getNbDegats());
    }

    void attaquerSort(Sort sort, Personnage p) {
        p.recevoirDegats(sort.getNbDegats());
        this.consommerMana(sort.getCoutMana());
    }

    public void attaquerCAC(ArmeCAC arme, Personnage personnage) {

    }

    @Override
    public void recevoirDegats(float degats) {
        this.enleverPv(degats - equipementDefensifEquipee.getReductionDegats());
    }
}
