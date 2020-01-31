package com.example.journal_perso.ui.calendrier;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalendrierViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CalendrierViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}