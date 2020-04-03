package com.example.journal_perso.models;

import java.io.Serializable;

public class indicateur implements Serializable {

    private String nom;
    private int TypeIndic;
    private int id;
    private String text;

    public indicateur(String nom, int typeIndic, int id, String text) {
        this.nom = nom;
        TypeIndic = typeIndic;
        this.id = id;
        this.text = text;
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

    @Override
    public String toString() {
        return "indicateur{" +
                "nom='" + nom + '\'' +
                ", TypeIndic=" + TypeIndic +
                ", id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
