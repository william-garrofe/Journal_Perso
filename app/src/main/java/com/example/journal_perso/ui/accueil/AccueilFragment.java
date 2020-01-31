package com.example.journal_perso.ui.accueil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.journal_perso.R;

public class AccueilFragment extends Fragment {

    private AccueilViewModel accueilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accueilViewModel =
                ViewModelProviders.of(this).get(AccueilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_accueil, container, false);
        return root;
    }
}