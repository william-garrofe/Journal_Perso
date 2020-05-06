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

import com.example.journal_perso.models.Espace;
import com.example.journal_perso.models.GsonFic;
import com.example.journal_perso.models.ListMaDataLocal;
import com.example.journal_perso.models.MaDataLocal;

import java.util.Vector;


public class DataEspace extends AppCompatActivity {

    private LinearLayout.LayoutParams p;
    private ListMaDataLocal data;
    private MaDataLocal s;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_espace);

        Button btnSave = findViewById(R.id.saveData);

        final GsonFic gf = new GsonFic();
        Intent i = getIntent();
        //s = new maDataLocal();


        final Espace mEspace = (Espace) i.getSerializableExtra("espace");
        final String date = (String) i.getSerializableExtra("date");

        ll = findViewById(R.id.maLayoutData);
        data = (ListMaDataLocal) gf.LireFichier(getApplicationContext(), "dataJson.json");

        data = test(date, mEspace, data);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gf.ecrireFichier(data, getApplicationContext(), "dataJson.json");
            }
        });
    }

    public ListMaDataLocal test(String date, Espace esp, ListMaDataLocal data) {
        ListMaDataLocal d = data;
        boolean espace_NT = true; //NT = non trouvé
        boolean addDate = true; //Date non présente dans le json
        int index = 0;
        Vector<Espace> n = new Vector<Espace>();
        Vector<MaDataLocal> mDataLoc = new Vector<>();
        MaDataLocal m;

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
                m = new MaDataLocal(date, n);
                d.getDateData().add(m);
            } else if (espace_NT == true) {
                d.getDateData().get(index).getMesEspaces().addElement(esp);
            }
        } else {
            d = new ListMaDataLocal();
            n.add(esp);
            m = new MaDataLocal(date, n);
            mDataLoc.addElement(m);
            d.setDateData(mDataLoc);
        }
        return d;
    }

    public Espace affichageEsp(final Espace mEsp, ListMaDataLocal dataEsp) {

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
