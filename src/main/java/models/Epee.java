package main.java.models;

public class Epee extends ArmeCAC {

    public Epee() {
        super("Epee", "epee.jpg", 10);
    }

    public Epee(String nom, String urlImage, float nbDegats) {
        super(nom, urlImage, nbDegats);
    }
}
