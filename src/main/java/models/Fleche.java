package main.java.models;

public class Fleche extends ArmeCAC {
    public Fleche(String urlImage,float nbDegats) {
        super("Fleche",urlImage, nbDegats);
    }

    public Fleche(){
        super("Fleche","fleche.jpg",10);
    }
}
