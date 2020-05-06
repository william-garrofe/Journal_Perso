package com.example.journal_perso.models;

import java.io.Serializable;
import java.util.Vector;

public class StructData implements Serializable {

    private Vector<Espace> mesEspaces;

    public StructData() {
    }

    public StructData(Vector<Espace> mesEspaces) {
        this.mesEspaces = mesEspaces;
    }

    public Vector<Espace> getMesEspaces() {
        return mesEspaces;
    }

    public void setMesEspaces(Vector<Espace> mesEspaces) {
        this.mesEspaces = mesEspaces;
    }

    @Override
    public String toString() {
        return "maData{" +
                "mesEspaces=" + mesEspaces +
                '}';
    }


}

