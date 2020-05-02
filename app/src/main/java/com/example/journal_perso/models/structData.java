package com.example.journal_perso.models;

import java.io.Serializable;
import java.util.Vector;

public class structData implements Serializable {

    private Vector<espace> mesEspaces;

    public structData() {
    }

    public structData(Vector<espace> mesEspaces) {
        this.mesEspaces = mesEspaces;
    }

    public Vector<espace> getMesEspaces() {
        return mesEspaces;
    }

    public void setMesEspaces(Vector<espace> mesEspaces) {
        this.mesEspaces = mesEspaces;
    }

    @Override
    public String toString() {
        return "maData{" +
                "mesEspaces=" + mesEspaces +
                '}';
    }


}

