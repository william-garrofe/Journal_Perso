package com.example.journal_perso.models;

import java.io.Serializable;

public class indicateur implements Serializable {

    private String nom;
    private int TypeIndic;
    private int id;
    private String text;
    private int Heure;
    private int min;

    public indicateur(String nom, int typeIndic, int id, String text, int Heure, int min) {
        this.nom = nom;
        TypeIndic = typeIndic;
        this.id = id;
        this.text = text;
        this.Heure = Heure;
        this.min = min;
    }

    public int getTypeIndic() {
        return TypeIndic;
    }

    public void setTypeIndic(int typeIndic) {
        TypeIndic = typeIndic;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getHeure() {
        return Heure;
    }

    public void setHeure(int heure) {
        Heure = heure;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }


    @Override
    public String toString() {
        return "indicateur{" +
                "nom='" + nom + '\'' +
                ", TypeIndic=" + TypeIndic +
                ", id=" + id +
                ", text='" + text + '\'' +
                ", Heure='" + Heure + '\'' +
                ", min='" + min + '\'' +
                '}';
    }
}
