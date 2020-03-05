package com.example.journal_perso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Map;
import java.util.Set;

public class ConfigurationIndicateur extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private CheckBox maCheckBox;
    private EditText monEditTextIndicateur;
    private EditText monIndicateurNom;
    private Switch monSwitchCreneau;
    private EditText monTempsCreneau;
    private Button monButton;
    private Spinner monSpinner;
    private SharedPreferences mesPreferences;
    private int numIndic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_indicateur);

        maCheckBox = findViewById(R.id.checkBoxIndicateur);
        monEditTextIndicateur =  findViewById(R.id.editTextIndicateur);
        monIndicateurNom =  findViewById(R.id.nomIndicateur);
        monSwitchCreneau = findViewById(R.id.switchCreneau);
        monTempsCreneau = findViewById(R.id.tempsCreneau);
        monButton = findViewById(R.id.buttonAjoutIndicateur);
        monSpinner = findViewById(R.id.typeIndicateur);

        ArrayAdapter<CharSequence> monAdaptater = ArrayAdapter.createFromResource(this, R.array.typeIndicateurNom,android.R.layout.simple_spinner_item);
        monAdaptater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(monAdaptater);
        monSpinner.setOnItemSelectedListener(this);

        SharedPreferences mesPreferences = getSharedPreferences("mesPreferences", Context.MODE_PRIVATE);

        monButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save(v,numIndic);
                Save(v);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String monText = parent.getItemAtPosition(position).toString();
        numIndic = position;
        switch (position)
        {
            case 1:
                maCheckBox.setVisibility(View.VISIBLE);
                monSwitchCreneau.setVisibility(View.VISIBLE);
                monTempsCreneau.setVisibility(View.VISIBLE);
                monButton.setVisibility(View.VISIBLE);
                break;

            case 2:
                monIndicateurNom.setVisibility(View.INVISIBLE);
                monEditTextIndicateur.setVisibility(View.VISIBLE);
                monSwitchCreneau.setVisibility(View.VISIBLE);
                monTempsCreneau.setVisibility(View.VISIBLE);
                monButton.setVisibility(View.VISIBLE);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        maCheckBox.setVisibility(View.INVISIBLE);
        monTempsCreneau.setVisibility(View.INVISIBLE);
        monSwitchCreneau.setVisibility(View.INVISIBLE);
        monEditTextIndicateur.setVisibility(View.INVISIBLE);
    }

    public void Save(View view) {
        int typeIndic=0;
        String n;
        if (typeIndic == 1)
        {
            n = monIndicateurNom.getText().toString();
        }else{
            n = monEditTextIndicateur.getText().toString();
        }

        //String e = email.getText().toString();
        SharedPreferences.Editor editor = mesPreferences.edit();
        editor.putString("NameAppli", n);
        //editor.putString(Email, e);
        editor.commit();
    }

}
