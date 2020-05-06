package com.example.journal_perso.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class MaDataLocal implements Serializable {

    private String date;
    private Vector<Espace> mesEspaces;

    public MaDataLocal() {
    }

    public MaDataLocal(String date, Vector<Espace> mesEspaces) {
        this.date = date;
        this.mesEspaces = mesEspaces;
    }

    //region getter
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Vector<Espace> getMesEspaces() {
        return mesEspaces;
    }

    public void setMesEspaces(Vector<Espace> mesEspaces) {
        this.mesEspaces = mesEspaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaDataLocal that = (MaDataLocal) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(mesEspaces, that.mesEspaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, mesEspaces);
    }
    //endregion

    @Override
    public String toString() {
        return "maDataLocal{" +
                "date='" + date + '\'' +
                ", mesEspaces=" + mesEspaces +
                '}';
    }
}
