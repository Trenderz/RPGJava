package main.java.models.armes;

import main.java.models.ArmeCAC;

public class Hache extends ArmeCAC {
    public Hache(String nom, String urlImage, float nbDegats, float prix) {
        super(nom, urlImage, nbDegats, prix);
    }

    public Hache() {
        super("Hache", "hache.jpg", 35, 80);
    }
}
