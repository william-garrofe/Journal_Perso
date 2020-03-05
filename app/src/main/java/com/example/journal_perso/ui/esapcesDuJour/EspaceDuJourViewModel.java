package com.example.journal_perso.ui.esapcesDuJour;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EspaceDuJourViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EspaceDuJourViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}