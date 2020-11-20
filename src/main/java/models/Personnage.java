package main.java.models;

import java.util.ArrayList;

public abstract class Personnage {
    int pv;
    int pm;
    int niv;

    abstract void utiliserSort(Sort sort);
}
