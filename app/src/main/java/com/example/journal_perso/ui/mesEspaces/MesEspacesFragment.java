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
import android.widget.Toast;

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
    private Vector<indicateur> i;
    private AlertDialog.Builder builder;
    private espace mEsp;
    private Vector<espace> mListEspace;
    private gsonFic gf;
    private maData data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mesEsapcesViewModel =
                ViewModelProviders.of(this).get(MesEsapcesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mes_espaces, container, false);
        Button monBouton = root.findViewById(R.id.btnCreerEspace);
        Button modifBouton = root.findViewById(R.id.btnModifierEspace);
        builder = new AlertDialog.Builder(getActivity());
        gf = new gsonFic();
        data = gf.LireFichier(getContext(), "monJson.json");

        System.out.println(data);

        i = new Vector<>();
        mListEspace = new Vector<>();

        /*indicateur indic = new indicateur("ABV",1,0);
        i.addElement(indic);

        final espace[] items = {
            new espace(i,"A",1),
            new espace(i, "Patate",2
        };*/

        mScrollView = root.findViewById(R.id.scrollView2);
        mListeView = root.findViewById(R.id.list);

        ArrayAdapter<espace> adapter = new ArrayAdapter<espace>(getActivity(), android.R.layout.simple_list_item_1, mListEspace); //items
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
                        indicateur indic = new indicateur("", 1, 0);
                        i.addElement(indic);
                        mEsp = new espace(i, input.getText().toString(), 1);
                        mListEspace.addElement(mEsp);
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
                //intent.putParcelableArrayListExtra("monObj",mListEspace);
                intent.putExtra("Monobj", items[1]); //Problème
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
                mListeView.setBackgroundColor(Color.BLUE);
            }
        });

        return root;

    }
}