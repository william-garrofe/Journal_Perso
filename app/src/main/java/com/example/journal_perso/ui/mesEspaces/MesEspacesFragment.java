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
import com.example.journal_perso.models.Espace;
import com.example.journal_perso.models.GsonFic;
import com.example.journal_perso.models.Indicateur;
import com.example.journal_perso.models.StructData;
import com.example.journal_perso.models.User;
import com.example.journal_perso.monEspace;
import com.example.journal_perso.services.EspaceService;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Vector;


public class MesEspacesFragment extends Fragment {

    private MesEsapcesViewModel mesEsapcesViewModel;
    private ScrollView mScrollView;
    private ListView mListeView;
    private AlertDialog.Builder builder;

    final private GsonFic gf = new GsonFic();
    private int pos = -1;
    private String nomEspace;
    private ArrayList<Integer> ListJour;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mesEsapcesViewModel =
                ViewModelProviders.of(this).get(MesEsapcesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mes_espaces, container, false);
        Button monBouton = root.findViewById(R.id.btnCreerEspace);
        Button modifBouton = root.findViewById(R.id.btnModifierEspace);
        Button btnSuppEsp = root.findViewById(R.id.btnSuppEspace);
        Button btnJours = root.findViewById(R.id.btnJours);

        builder = new AlertDialog.Builder(getActivity());

        mScrollView = root.findViewById(R.id.scrollView2);
        mListeView = root.findViewById(R.id.list);
        final ArrayList<String> list = new ArrayList<>();
        final Intent in = getActivity().getIntent();
        final User usr = (User) in.getSerializableExtra("user");

        ListJour = new ArrayList<>();
        final EspaceService espaceService = new EspaceService();

        StructData maDatas = (StructData) gf.LireFichier(getContext(), "monJson.json");

        if (maDatas == null) {
            maDatas = new StructData();
            maDatas.setMesEspaces(new Vector<Espace>());
        }
        final StructData finalDatas = maDatas;

        for (int i = 0; i < maDatas.getMesEspaces().size(); i++) {
            nomEspace = maDatas.getMesEspaces().get(i).nomEsp();
            list.add(nomEspace);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list); //items maDatas.getMesEspaces()
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
                        Vector<Indicateur> i = new Vector<>();
                        Espace esp = new Espace(i, input.getText().toString(), finalDatas.getMesEspaces().size() + 1, ListJour); //A faire
                        finalDatas.getMesEspaces().addElement(esp);
                        try {
                            espaceService.createEsapce(new EspaceService.OnJSONResponseCallback() {
                                @Override
                                public void onJSONResponse(boolean success, Object object) {
                                    System.out.println(object);
                                }
                            }, input.getText().toString(), usr.getIdUser(), getContext());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        gf.ecrireFichier(finalDatas, getContext(), "monJson.json");
                        list.add(esp.getNom());
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

        btnJours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos != -1) {
                    AlertDialog.Builder builderJours = new AlertDialog.Builder(getActivity());
                    final ArrayList joursSelect = new ArrayList();
                    builderJours.setTitle("Choisir les jours pour l'espace : ");
                    String[] jour = {"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
                    final StructData data = (StructData) gf.LireFichier(getContext(), "monJson.json");
                    builderJours.setMultiChoiceItems(jour, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int jour, boolean isChecked) {
                            if (isChecked) {
                                joursSelect.add(jour + 1);
                            } else if (joursSelect.contains(jour + 1)) {
                                joursSelect.remove(Integer.valueOf(jour + 1));
                            }
                        }
                    });

                    builderJours.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            data.getMesEspaces().get(pos).setListJour(joursSelect);
                            gf.ecrireFichier(data, getContext(), "monJson.json");
                        }
                    });
                    builderJours.setNegativeButton("Retour", null);
                    AlertDialog dialog = builderJours.create();
                    dialog.show();
                } else {
                    Toast.makeText(getContext(), "Sélectionnez un espace",
                            Toast.LENGTH_LONG).show();
                }
            }

        });

        btnSuppEsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos != -1) {
                    finalDatas.getMesEspaces().remove(pos);
                    list.remove(pos);
                    adapter.notifyDataSetChanged();
                    gf.ecrireFichier(finalDatas, getContext(), "monJson.json");
                }
            }
        });

        modifBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos != -1) {
                    Intent intent = new Intent(getActivity(), monEspace.class);
                    intent.putExtra("maData", finalDatas);
                    intent.putExtra("espace", finalDatas.getMesEspaces().get(pos));
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Sélectionnez un espace",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        mListeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                view.setBackgroundColor(Color.WHITE);
                pos = position;
                view.setBackgroundColor(Color.CYAN);
            }
        });


        return root;
    }


}