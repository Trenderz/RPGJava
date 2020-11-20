package main.java.models;

import java.util.ArrayList;

public class Guerrier extends Personnage {

    ArrayList<Arme> listeArmes;
    Arme armeEquipee;

    public Guerrier(){
        this.pv=500;
        this.pm=50;
        this.niv=1;
        this.listeArmes = new ArrayList<>();
        this.listeArmes.add(new Epee());
        armeEquipee=listeArmes.get(0);
    }

    void ajouterArme(Arme arme){
        this.listeArmes.add(arme);
    }

    int attaqueCac() {
        return armeEquipee.getNbDegats();
    }

    @Override
    void utiliserSort(Sort sort) {

    }
}
