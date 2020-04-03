package com.example.journal_perso.ui.mesEspaces;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.journal_perso.R;
import com.example.journal_perso.models.espace;
import com.example.journal_perso.models.gsonFic;
import com.example.journal_perso.models.indicateur;
import com.example.journal_perso.models.maData;
import com.example.journal_perso.monEspace;

import java.util.Vector;


public class MesEspacesFragment extends Fragment {

    private MesEsapcesViewModel mesEsapcesViewModel;
    private ScrollView mScrollView;
    private ListView mListeView;
    private AlertDialog.Builder builder;

    final private gsonFic gf = new gsonFic();
    private int pos = -1;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mesEsapcesViewModel =
                ViewModelProviders.of(this).get(MesEsapcesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mes_espaces, container, false);
        Button monBouton = root.findViewById(R.id.btnCreerEspace);
        Button modifBouton = root.findViewById(R.id.btnModifierEspace);
        builder = new AlertDialog.Builder(getActivity());

        mScrollView = root.findViewById(R.id.scrollView2);
        mListeView = root.findViewById(R.id.list);

        maData maDatas = gf.LireFichier(getContext(), "monJson.json");

        if (maDatas == null) {
            maDatas = new maData();
            maDatas.setMesEspaces(new Vector<espace>());
        }
        final maData finalDatas = maDatas;

        ArrayAdapter<espace> adapter = new ArrayAdapter<espace>(getActivity(), android.R.layout.simple_list_item_1, maDatas.getMesEspaces()); //items
        mListeView.setAdapter(adapter);

        monBouton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                builder.setTitle("Nom de votre Esapce :");
                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Vector<indicateur> i = new Vector<>();
                        espace esp = new espace(i, input.getText().toString(), finalDatas.getMesEspaces().size() + 1);

                        finalDatas.getMesEspaces().addElement(esp);
                        gf.ecrireFichier(finalDatas, getContext());

                    }
                });
                builder.setNegativeButton("Retour", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        modifBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), monEspace.class);
                intent.putExtra("maData", finalDatas);
                intent.putExtra("espace", finalDatas.getMesEspaces().get(pos));
                startActivity(intent);
            }
        });

        mListeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                pos = position;
                view.setBackgroundColor(Color.CYAN);
            }
        });


        return root;
    }


}