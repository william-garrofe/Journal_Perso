package com.example.journal_perso.ui.esapcesDuJour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.journal_perso.R;

public class EspacesDuJourFragment extends Fragment {

    private EspaceDuJourViewModel espaceDuJourViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        espaceDuJourViewModel =
                ViewModelProviders.of(this).get(EspaceDuJourViewModel.class);
        View root = inflater.inflate(R.layout.fragment_espaces_du_jour, container, false);
        /*final TextView textView = root.findViewById(R.id.espacesDuJour);
        espaceDuJourViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}