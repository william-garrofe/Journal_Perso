package com.example.journal_perso.ui.mesDonnees;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MesDonneesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MesDonneesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}