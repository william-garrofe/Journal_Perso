package com.example.journal_perso.models;

import java.util.Vector;

public class ListUser {
    Vector<User> listUser;

    public ListUser(Vector<User> listUser) {
        this.listUser = listUser;
    }

    public Vector<User> getListUser() {
        return listUser;
    }

    public void setListUser(Vector<User> listUser) {
        this.listUser = listUser;
    }

    @Override
    public String toString() {
        return "ListUser{" +
                "listUser=" + listUser +
                '}';
    }
}
