package com.example.journal_perso.models;

import java.util.Objects;
import java.util.Vector;

public class ListMaDataLocal {
    Vector<MaDataLocal> data;

    public ListMaDataLocal() {
    }

    public ListMaDataLocal(Vector<MaDataLocal> dateData) {
        this.data = dateData;
    }

    //region getter
    public Vector<MaDataLocal> getDateData() {
        return data;
    }

    public void setDateData(Vector<MaDataLocal> dateData) {
        this.data = dateData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListMaDataLocal listMaDataLocal1 = (ListMaDataLocal) o;
        return Objects.equals(data, listMaDataLocal1.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
    //endregion

    @Override
    public String toString() {
        return "dateData{" +
                "dateData=" + data +
                '}';
    }
}
