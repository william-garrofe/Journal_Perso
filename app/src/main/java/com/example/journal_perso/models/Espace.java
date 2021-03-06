package com.example.journal_perso.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Espace implements Serializable {
    private Vector<Indicateur> cIndic;
    private String nomEspace;
    private int idEspace;
    private ArrayList<Integer> ListJour;

    public Espace() {

    }

    public Espace(Vector<Indicateur> cIndic, String nom, int idEspace, ArrayList<Integer> ListJour) {
        this.cIndic = cIndic;
        this.nomEspace = nom;
        this.idEspace = idEspace;
        this.ListJour = ListJour;
    }

    public Vector<Indicateur> getcIndic() {
        return cIndic;
    }

    public void setcIndic(Vector<Indicateur> cIndic) {
        this.cIndic = cIndic;
    }

    public String getNom() {
        return nomEspace;
    }

    public void setNom(String nom) {
        this.nomEspace = nom;
    }

    public int getId() {
        return idEspace;
    }

    public void setId(int id) {
        this.idEspace = id;
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
                ", id=" + idEspace +
                ", ListJour=" + ListJour +
                '}';
    }
}
