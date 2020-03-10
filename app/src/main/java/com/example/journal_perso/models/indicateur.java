package com.example.journal_perso.models;

import com.example.journal_perso.ConfigurationIndicateur;

import java.io.Serializable;
import java.util.Vector;

public class indicateur implements Serializable {

    private String nom;
    private int TypeIndic;
    private int id;

    public indicateur(String nom, int typeIndic, int id) {
        this.nom = nom;
        this.TypeIndic = typeIndic;
        this.id = id;
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

    @Override
    public String toString() {
        return "indicateur{" +
                "nom='" + nom + '\'' +
                ", TypeIndic=" + TypeIndic +
                ", id=" + id +
                '}';
    }
}
