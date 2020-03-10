package com.example.journal_perso.ui.mesEspaces;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.journal_perso.ConfigurationIndicateur;
import com.example.journal_perso.R;
import com.example.journal_perso.models.espace;
import com.example.journal_perso.models.indicateur;
import com.example.journal_perso.monEspace;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class MesEspacesFragment extends Fragment {

    private MesEsapcesViewModel mesEsapcesViewModel;
    private ScrollView mScrollView;
    private ListView mListeView;
    private Vector<indicateur> i;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mesEsapcesViewModel =
                ViewModelProviders.of(this).get(MesEsapcesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mes_espaces, container, false);
        Button monBouton = root.findViewById(R.id.btnCreerEspace);

        i = new Vector<>();
        indicateur indic = new indicateur("test",1,0);

        i.addElement(indic);

        final espace[] items = {
            new espace(i,"A",1),
            new espace(i, "Patate",2)
        };

        mScrollView = root.findViewById(R.id.scrollView2);
        mListeView = root.findViewById(R.id.list);

        ArrayAdapter<espace> adapter = new ArrayAdapter<espace>(getActivity(), android.R.layout.simple_list_item_1,items);
        mListeView.setAdapter(adapter);

        monBouton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), monEspace.class);
                intent.putExtra("Monobj", items[1]);
                startActivity(intent);
            }
        });

        mListeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });

        return root;

    }
}