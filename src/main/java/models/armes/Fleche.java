package main.java.models.armes;

import main.java.models.ArmeCAC;

public class Fleche extends ArmeCAC {
    public Fleche(String urlImage,float nbDegats, float prix) {
        super("Fleche",urlImage, nbDegats, prix);
    }

    public Fleche(){
        super("Fleche","fleche.jpg",20,5);
    }
}
