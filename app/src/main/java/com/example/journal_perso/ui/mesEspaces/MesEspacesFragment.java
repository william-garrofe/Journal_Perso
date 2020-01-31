package com.example.journal_perso.ui.mesEspaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.journal_perso.R;

public class MesEspacesFragment extends Fragment {

    private MesEsapcesViewModel mesEsapcesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mesEsapcesViewModel =
                ViewModelProviders.of(this).get(MesEsapcesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mes_espaces, container, false);
       /* final TextView textView = root.findViewById(R.id.text_mes_esapces);
        mesEsapcesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}