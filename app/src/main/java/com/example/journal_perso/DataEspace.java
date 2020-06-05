package com.example.journal_perso;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.journal_perso.models.Espace;
import com.example.journal_perso.models.GsonFic;
import com.example.journal_perso.models.Indicateur;
import com.example.journal_perso.models.ListMaDataLocal;
import com.example.journal_perso.models.MaDataLocal;

import java.util.Vector;


public class DataEspace extends AppCompatActivity {

    private LinearLayout.LayoutParams p;
    private ListMaDataLocal data;
    private MaDataLocal s;
    private LinearLayout ll;
    private final GsonFic gf = new GsonFic();
    private Espace mEspace;
    private boolean flag = false;
    private Espace espaceData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_espace);

        Button btnSave = findViewById(R.id.saveData);
        Intent i = getIntent();

        mEspace = (Espace) i.getSerializableExtra("espace");
        final String date = (String) i.getSerializableExtra("date");

        ll = findViewById(R.id.maLayoutData);
        data = (ListMaDataLocal) gf.LireFichier(getApplicationContext(), "dataJson.json");

        /*for (MaDataLocal currentData: data.getDateData()
             ) {
            for (Espace currentEspace: currentData.getMesEspaces()
                 ) {
                if(mEspace.getId()==currentEspace.getId()){
                   espaceData = currentEspace;
                }
            }
        }*/

        if (data != null) {
            for (int j = 0; j < data.getDateData().size(); j++) {
                if (data.getDateData().get(j).getDate().contains(date)) {
                    MaDataLocal currentData = data.getDateData().get(j);
                    for (int k = 0; k < currentData.getMesEspaces().size(); k++) {
                        espaceData = currentData.getMesEspaces().get(k);
                    }

                }
            }
        }

        affichageEsp(mEspace);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = VerificationDate(date, mEspace, data);
                gf.ecrireFichier(data, getApplicationContext(), "dataJson.json");
            }
        });
    }

    public Espace affichageEsp(final Espace mEsp) {

        if (espaceData != null) {
            for (int i = 0; i < espaceData.getcIndic().size(); i++)
                if (espaceData.getcIndic().get(i).getId() == mEspace.getcIndic().get(i).getId()) {
                    mEspace.getcIndic().get(i).setText(espaceData.getcIndic().get(i).getText());
                }
        }

        for (int l = 0; l < mEsp.getcIndic().size(); l++) {
            final int index = l;
            Indicateur currentInd = mEsp.getcIndic().get(l);
            switch (currentInd.getTypeIndic()) {
                case 0:
                    p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    final CardView cv = newCardView();

                    LinearLayout cl = new LinearLayout(getApplicationContext());
                    cl.setOrientation(LinearLayout.VERTICAL);
                    cl.setId(View.generateViewId());

                    final EditText et = new EditText(getApplicationContext());
                    final TextView tv = new TextView(getApplicationContext());

                    tv.setLayoutParams(p);
                    tv.setText(currentInd.getNom());
                    tv.setId(View.generateViewId());

                    et.setLayoutParams(p);

                    if ((espaceData != null) && l < espaceData.getcIndic().size()) {
                        et.setText(espaceData.getcIndic().get(index).getText());
                    }

                    et.setId(View.generateViewId());

                    cl.addView(tv);
                    cl.addView(et);

                    cv.addView(cl);

                    et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            mEsp.getcIndic().get(index).setText(et.getText().toString());
                        }
                    });
                    ll.addView(cv);
                    break;

                case 1:
                    p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    CheckBox cb = new CheckBox(getApplicationContext());
                    cb.setLayoutParams(p);
                    cb.setText(mEsp.getcIndic().get(index).getNom());
                    if (mEsp.getcIndic().get(index).getText().equals("true")) {
                        cb.setChecked(true);
                    }

                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                mEsp.getcIndic().get(index).setText("true");
                            } else {
                                mEsp.getcIndic().get(index).setText("false");
                            }
                        }
                    });
                    cb.isChecked();
                    ll.addView(cb);
                    break;
            }

        }
        return mEsp;
    }

    public CardView newCardView() {
        CardView cardView = new CardView(getApplicationContext());
        cardView.setId(View.generateViewId());
        cardView.setPadding(25, 25, 25, 25);
        cardView.setElevation(10);
        cardView.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT + 200
        ));

        return cardView;
    }

    public ListMaDataLocal VerificationDate(String date, Espace esp, ListMaDataLocal data) {
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
                    s = d.getDateData().get(j);
                    for (int k = 0; k < s.getMesEspaces().size(); k++) {
                        if (s.getMesEspaces().get(k).getId() == mEspace.getId()) {
                            espace_NT = false;
                            s.getMesEspaces().set(k, mEspace);
                            d.getDateData().set(index, s);
                        }
                    }
                } else {
                    n.add(esp);
                    m = new MaDataLocal(date, n);
                    d.getDateData().add(m);
                }
            }

            if (espace_NT == true) {
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
}
