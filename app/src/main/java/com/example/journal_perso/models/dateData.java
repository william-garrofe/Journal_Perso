package com.example.journal_perso.models;

import java.util.Objects;
import java.util.Vector;

public class dateData {
    Vector<maDataLocal> data;

    public dateData() {
    }

    public dateData(Vector<maDataLocal> dateData) {
        this.data = dateData;
    }

    //region getter
    public Vector<maDataLocal> getDateData() {
        return data;
    }

    public void setDateData(Vector<maDataLocal> dateData) {
        this.data = dateData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dateData dateData1 = (dateData) o;
        return Objects.equals(data, dateData1.data);
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
