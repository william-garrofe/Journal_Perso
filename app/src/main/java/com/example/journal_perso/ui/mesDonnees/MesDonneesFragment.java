package com.example.journal_perso.ui.mesDonnees;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.journal_perso.R;

public class MesDonneesFragment extends Fragment {

    private MesDonneesViewModel mesDonneesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mesDonneesViewModel =
                ViewModelProviders.of(this).get(MesDonneesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mes_donnees, container, false);
       /* final TextView textView = root.findViewById(R.id.text_mes_donnees);
        mesDonneesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}