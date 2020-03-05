package com.example.journal_perso.ui.esapcesDuJour;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.journal_perso.ConfigurationIndicateur;
import com.example.journal_perso.R;
import com.example.journal_perso.ui.mesEspaces.MesEspacesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EspacesDuJourFragment extends Fragment {

    private EspaceDuJourViewModel espaceDuJourViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        espaceDuJourViewModel =
                ViewModelProviders.of(this).get(EspaceDuJourViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendrier, container, false);

        FloatingActionButton mAjoutEsapce = (FloatingActionButton) root.findViewById(R.id.AjouterEspace);

       /* mAjoutEsapce.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //androidx.fragment.app.Fragment fragment = getFragmentManager().findFragmentById(R.id.nav_espaces_du_jour);
                Intent intent = new Intent(getActivity(), R);
                startActivity(intent);
            }
        });*/
        return root;
    }
}