package com.example.journal_perso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.journal_perso.models.espace;
import com.example.journal_perso.models.gsonFic;
import com.example.journal_perso.models.indicateur;
import com.example.journal_perso.models.maData;

import java.util.Vector;

public class ConfigurationIndicateur extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private EditText monIndicateurNom;
    private Switch monSwitchCreneau;
    private EditText monTempsCreneau;
    private Button monButton;
    private Spinner monSpinner;
    private espace esp;
    private maData data;
    private LinearLayout ll;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_indicateur);

        monIndicateurNom =  findViewById(R.id.nomIndicateur);
        monSwitchCreneau = findViewById(R.id.switchCreneau);
        monTempsCreneau = findViewById(R.id.tempsCreneau);
        monButton = findViewById(R.id.buttonAjoutIndicateur);
        monSpinner = findViewById(R.id.typeIndicateur);
        ll = findViewById(R.id.maLayout);

        Intent i = getIntent();
        esp = (espace) i.getSerializableExtra("espace");
        data = (maData) i.getSerializableExtra("maData");
        final gsonFic gf = new gsonFic();


        ArrayAdapter<CharSequence> monAdaptater = ArrayAdapter.createFromResource(this, R.array.typeIndicateurNom,android.R.layout.simple_spinner_item);
        monAdaptater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(monAdaptater);
        monSpinner.setOnItemSelectedListener(this);

        monButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicateur i = new indicateur(monIndicateurNom.getText().toString(), pos, esp.getcIndic().size() + 1, "");
                esp.getcIndic().addElement(i);
                data = UpdateData(data, esp);
                gf.ecrireFichier(data, getApplicationContext());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String monText = parent.getItemAtPosition(position).toString();
        pos = position;
        switch (position)
        {
            case 0:
                EditText et = new EditText(getApplicationContext());
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                et.setLayoutParams(p);
                et.setHint("texte");
                ll.addView(et);
                break;

            case 1:
                CheckBox cb = new CheckBox(getApplicationContext());
                LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                cb.setLayoutParams(l);
                cb.setText(monIndicateurNom.getText());
                ll.addView(cb);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        monTempsCreneau.setVisibility(View.INVISIBLE);
        monSwitchCreneau.setVisibility(View.INVISIBLE);
    }

    private maData UpdateData(maData data, espace espace) {
        Vector<espace> vect = data.getMesEspaces();
        for (int i = 0; i < vect.size(); i++) {
            if (vect.get(i).getId() == espace.getId()) {
                vect.get(i).setNom(espace.getNom());
                vect.get(i).setcIndic(espace.getcIndic());
            }
        }
        return data;
    }


}
