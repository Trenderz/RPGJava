package main.java.models.armes;

import main.java.models.ArmeCAC;

public class Epee extends ArmeCAC {

    public Epee() {
        super("Epee", "epee.jpg", 100, 40);
    }

    public Epee(String nom, String urlImage, float nbDegats, float prix) {
        super(nom, urlImage, nbDegats, prix);
    }
}
