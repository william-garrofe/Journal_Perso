package com.example.journal_perso.ui.parametres;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.journal_perso.R;

public class ParametresFragment extends Fragment {

    private ParametresViewModel parametresViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        parametresViewModel =
                ViewModelProviders.of(this).get(ParametresViewModel.class);
        View root = inflater.inflate(R.layout.fragment_parametres, container, false);
        /*final TextView textView = root.findViewById(R.id.text_parametres);
        parametresViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}