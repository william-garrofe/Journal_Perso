package com.example.journal_perso.ui.mesEspaces;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MesEsapcesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MesEsapcesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}