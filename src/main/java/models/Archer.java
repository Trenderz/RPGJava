package main.java.models;

import java.util.ArrayList;

public class Archer extends Personnage {

    ArrayList<Arme> listeArmes;
    int nbFleches;
    Arme armeEquipee;

    public Archer(){
        this.pv=350;
        this.pm=75;
        this.niv=1;
        this.listeArmes = new ArrayList<>();
        this.nbFleches = 25;
        this.listeArmes.add(new Arc());
        armeEquipee=listeArmes.get(0);
    }

    void ajouterArme(Arme arme){
        this.listeArmes.add(arme);
    }

    int attaqueCac() {
        this.nbFleches--;
        return armeEquipee.getNbDegats();
    }

    @Override
    void utiliserSort(Sort sort) {

    }
}
