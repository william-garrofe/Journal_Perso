package com.example.journal_perso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    //private espace e;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_espace);

        Button btnSave = findViewById(R.id.saveData);

        final gsonFic gf = new gsonFic();
        Intent i = getIntent();
        //s = new maDataLocal();


        final espace esp = (espace) i.getSerializableExtra("espace");
        final String date = (String) i.getSerializableExtra("date");

        ll = findViewById(R.id.maLayoutData);
        data = (dateData) gf.LireFichier(getApplicationContext(), "dataJson.json");
        //Vector<maDataLocal> test = new Vector<>();
        System.out.println(date);
        p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        EditText et1 = new EditText(getApplicationContext());
        et1.setLayoutParams(p);
        et1.setText(esp.getNom());
        ll.addView(et1);

        test(date, esp);

        //TODO : Show esp function

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finalDataLoc.setDateData();
                gf.ecrireFichier(data, getApplicationContext(), "dataJson.json");
            }
        });
    }

    public void test(String date, espace e) {
        espace d = null;
        boolean espace_NT = true; //NT = non trouvé
        boolean addDate = true; //Date non présente dans le json
        int index = 0;
        Vector<espace> n = new Vector<espace>();
        Vector<maDataLocal> mDataLoc = new Vector<>();
        maDataLocal m;

        if (data != null) {
            //TODO : function for search espace in data from json
            //TODO : esp = espace found
            for (int j = 0; j < data.getDateData().size(); j++) {
                if (data.getDateData().get(j).getDate().contains(date)) {
                    addDate = false;
                    s = data.getDateData().get(j);
                    index = j;
                    for (int k = 0; k < s.getMesEspaces().size(); k++) {
                        if (s.getMesEspaces().get(k).getNom().contains(e.getNom())) {
                            espace_NT = false;
                            e = s.getMesEspaces().get(k);
                            for (int l = 0; l < e.getcIndic().size(); l++) {
                                System.out.println(s);
                                p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                EditText et1 = new EditText(getApplicationContext());
                                et1.setLayoutParams(p);
                                et1.setText(e.getcIndic().get(l).getText());
                                ll.addView(et1);
                            }
                        }
                    }
                }
            }


            if (addDate == true) {
                n.add(e);
                m = new maDataLocal(date, n);
//                /mDataLoc.addElement(m);
                data.getDateData().add(m);
            } else if (espace_NT == true) {
                data.getDateData().get(index).getMesEspaces().addElement(e);
            }

            /* data.setDateData(test);
             //data.getDateData().addElement(test.firstElement());

             Vector<espace> n = new Vector<espace>();
             n.add(esp);
             e =new maDataLocal(date, n);

             //n.add(esp2);
             e.setMesEspaces(n);

             e = new maDataLocal("Date", new Vector<espace>());
             e.getMesEspaces().add(esp);

             test.add(e);
             //data.setDateData();

             data.setDateData(new Vector<maDataLocal>());
             data.getDateData().add(e);*/
        } else {
            data = new dateData();
            n.add(e);
            m = new maDataLocal(date, n);
            mDataLoc.addElement(m);
            data.setDateData(mDataLoc);
        }
        // return d;
    }
}
