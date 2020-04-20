package com.example.journal_perso.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class espace implements Serializable {
    private Vector<indicateur> cIndic;
    private String nomEspace;
    private int id;
    private ArrayList<Integer> ListJour;
    public  espace(){

    }

    public espace(Vector<indicateur> cIndic, String nom, int id, ArrayList<Integer> ListJour) {
        this.cIndic = cIndic;
        this.nomEspace = nom;
        this.id = id;
        this.ListJour = ListJour;
    }

    public Vector<indicateur> getcIndic() {
        return cIndic;
    }

    public void setcIndic(Vector<indicateur> cIndic) {
        this.cIndic = cIndic;
    }

    public String getNom() {
        return nomEspace;
    }

    public void setNom(String nom) {
        this.nomEspace = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String nomEsp() {
        return nomEspace;
    }

    public ArrayList<Integer> getListJour() {
        return ListJour;
    }

    public void setListJour(ArrayList<Integer> listJour) {
        ListJour = listJour;
    }

    @Override
    public String toString() {
        return "espace{" +
                "cIndic=" + cIndic +
                ", nomEspace='" + nomEspace + '\'' +
                ", id=" + id +
                ", ListJour=" + ListJour +
                '}';
    }
}
