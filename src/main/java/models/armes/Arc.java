package main.java.models.armes;

import main.java.models.ArmeDistance;

public class Arc extends ArmeDistance {

    public Arc() {
        super("Arc", "arc.jpg", 1, 80);
    }

    public Arc(String nom, String urlImage, float multiplicateur, float prix) {
        super(nom, urlImage, multiplicateur, prix);
    }

}
