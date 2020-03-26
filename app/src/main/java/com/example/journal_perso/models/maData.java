package com.example.journal_perso.models;

import java.util.Vector;

public class maData {
    public Vector<indicateur> cIndic;
    public int id;
    public String nomEspace;

    public maData(Vector<indicateur> indic, int id, String nomEsp) {
        this.cIndic = indic;
        this.id = id;
        this.nomEspace = nomEsp;
    }

    //region getter
    public Vector<indicateur> getIndic() {
        return cIndic;
    }

    public void setIndic(Vector<indicateur> indic) {
        this.cIndic = indic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEsp() {
        return nomEspace;
    }

    public void setNomEsp(String nomEsp) {
        this.nomEspace = nomEsp;
    }
    //endregion

    @Override
    public String toString() {
        return "maData{" +
                "indic=" + cIndic +
                ", id=" + id +
                ", nomEsp='" + nomEspace + '\'' +
                '}';
    }
}
