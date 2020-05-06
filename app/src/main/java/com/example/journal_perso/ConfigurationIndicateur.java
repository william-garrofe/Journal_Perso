package com.example.journal_perso;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.journal_perso.models.Espace;
import com.example.journal_perso.models.GsonFic;
import com.example.journal_perso.models.Indicateur;
import com.example.journal_perso.models.StructData;

import java.util.Vector;

public class ConfigurationIndicateur extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private EditText monIndicateurNom;
    private Switch monSwitchCreneau = null;
    private EditText monTempsCreneau;
    private Button monButton;
    private Spinner monSpinner;
    private Espace esp;
    private StructData data;
    private LinearLayout ll;
    private int pos = -1;
    private EditText et, et1;
    private LinearLayout.LayoutParams p;
    private String mTemps;

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
        esp = (Espace) i.getSerializableExtra("espace");
        data = (StructData) i.getSerializableExtra("maData");
        final GsonFic gf = new GsonFic();


        ArrayAdapter<CharSequence> monAdaptater = ArrayAdapter.createFromResource(this, R.array.typeIndicateurNom,android.R.layout.simple_spinner_item);
        monAdaptater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(monAdaptater);
        monSpinner.setOnItemSelectedListener(this);
        p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        monSwitchCreneau.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(ConfigurationIndicateur.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    et1 = new EditText(getApplicationContext());
                                    et1.setLayoutParams(p);
                                    et1.setText("Heure : " + hourOfDay + " min : " + minute);
                                    mTemps = hourOfDay + ":" + minute;
                                    ll.addView(et1);
                                }
                            }, 0, 0, true);
                    timePickerDialog.show();
                } else {
                    ll.removeView(et1);
                }
            }
        });
        monButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Indicateur i = new Indicateur(monIndicateurNom.getText().toString(), pos, esp.getcIndic().size() + 1, et.getText().toString(), mTemps);
                esp.getcIndic().addElement(i);
                data = UpdateData(data, esp);
                gf.ecrireFichier(data, getApplicationContext(), "monJson.json");

                Intent intent = new Intent(ConfigurationIndicateur.this, monEspace.class);
                intent.putExtra("espace", esp);
                intent.putExtra("maData", data);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String monText = parent.getItemAtPosition(position).toString();
        pos = position;
        ll.removeAllViews();

        switch (position)
        {
            case 0:
                et = new EditText(getApplicationContext());
                et.setLayoutParams(p);
                et.setHint("Texte");
                ll.addView(et);
                break;

            case 1:
                CheckBox cb = new CheckBox(getApplicationContext());
                cb.setLayoutParams(p);
                cb.setText(monIndicateurNom.getText().toString());
                ll.addView(cb);
                break;
        }
        monSwitchCreneau.setVisibility(view.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        monTempsCreneau.setVisibility(View.INVISIBLE);
        monSwitchCreneau.setVisibility(View.INVISIBLE);
    }

    private StructData UpdateData(StructData data, Espace espace) {
        Vector<Espace> vect = data.getMesEspaces();
        for (int i = 0; i < vect.size(); i++) {
            if (vect.get(i).getId() == espace.getId()) {
                vect.get(i).setNom(espace.getNom());
                vect.get(i).setcIndic(espace.getcIndic());
            }
        }
        return data;
    }
}
