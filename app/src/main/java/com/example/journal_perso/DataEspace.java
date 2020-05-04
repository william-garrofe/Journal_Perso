package com.example.journal_perso;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.journal_perso.models.dateData;
import com.example.journal_perso.models.espace;
import com.example.journal_perso.models.gsonFic;
import com.example.journal_perso.models.maDataLocal;

import java.util.Vector;


public class DataEspace extends AppCompatActivity {

    private LinearLayout.LayoutParams p;
    private dateData data;
    private maDataLocal s;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_espace);

        Button btnSave = findViewById(R.id.saveData);

        final gsonFic gf = new gsonFic();
        Intent i = getIntent();
        //s = new maDataLocal();


        final espace mEspace = (espace) i.getSerializableExtra("espace");
        final String date = (String) i.getSerializableExtra("date");

        ll = findViewById(R.id.maLayoutData);
        data = (dateData) gf.LireFichier(getApplicationContext(), "dataJson.json");

        data = test(date, mEspace, data);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gf.ecrireFichier(data, getApplicationContext(), "dataJson.json");
            }
        });
    }

    public dateData test(String date, espace esp, dateData data) {
        dateData d = data;
        boolean espace_NT = true; //NT = non trouvé
        boolean addDate = true; //Date non présente dans le json
        int index = 0;
        Vector<espace> n = new Vector<espace>();
        Vector<maDataLocal> mDataLoc = new Vector<>();
        maDataLocal m;

        if (d != null) {
            for (int j = 0; j < d.getDateData().size(); j++) {
                if (d.getDateData().get(j).getDate().contains(date)) {
                    addDate = false;
                    s = d.getDateData().get(j);
                    index = j;
                    for (int k = 0; k < s.getMesEspaces().size(); k++) {
                        if (s.getMesEspaces().get(k).getNom().contains(esp.getNom())) {
                            espace_NT = false;
                            esp = affichageEsp(s.getMesEspaces().get(k), d);
                            s.getMesEspaces().set(k, esp);
                            d.getDateData().set(index, s);
                        }
                    }
                }
            }


            if (addDate == true) {
                n.add(esp);
                m = new maDataLocal(date, n);
                d.getDateData().add(m);
            } else if (espace_NT == true) {
                d.getDateData().get(index).getMesEspaces().addElement(esp);
            }
        } else {
            d = new dateData();
            n.add(esp);
            m = new maDataLocal(date, n);
            mDataLoc.addElement(m);
            d.setDateData(mDataLoc);
        }
        return d;
    }

    public espace affichageEsp(final espace mEsp, dateData dataEsp) {

        p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int l = 0; l < mEsp.getcIndic().size(); l++) {
            final int paatate = l;
            switch (mEsp.getcIndic().get(l).getTypeIndic()) {
                case 0:
                    final EditText et = new EditText(getApplicationContext());
                    et.setLayoutParams(p);
                    et.setText(mEsp.getcIndic().get(l).getText());
                    et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mEsp.getcIndic().get(paatate).setText(et.getText().toString());
                        }
                    });
                    ll.addView(et);
                    break;
                case 1:
                    CheckBox cb = new CheckBox(getApplicationContext());
                    cb.setLayoutParams(p);
                    cb.setText(mEsp.getcIndic().get(l).getNom());
                    ll.addView(cb);
                    break;
            }

        }
        return mEsp;
    }
}
