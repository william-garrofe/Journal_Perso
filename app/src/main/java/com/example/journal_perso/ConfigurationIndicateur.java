package com.example.journal_perso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class ConfigurationIndicateur extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private CheckBox maCheckBox;
    private EditText monEditTextIndicateur;
    private EditText monIndicateurNom;
    private Switch monSwitchCreneau;
    private EditText monTempsCreneau;
    private Button monButon;
    private Spinner monSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_indicateur);

        maCheckBox = findViewById(R.id.checkBoxIndicateur);
        monEditTextIndicateur =  findViewById(R.id.editTextIndicateur);
        monIndicateurNom =  findViewById(R.id.nomIndicateur);
        monSwitchCreneau = findViewById(R.id.switchCreneau);
        monTempsCreneau = findViewById(R.id.tempsCreneau);
        monButon = findViewById(R.id.buttonAjoutIndicateur);
        monSpinner = findViewById(R.id.typeIndicateur);

        ArrayAdapter<CharSequence> monAdaptater = ArrayAdapter.createFromResource(this, R.array.typeIndicateurNom,android.R.layout.simple_spinner_item);
        monAdaptater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(monAdaptater);
        monSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String monText = parent.getItemAtPosition(position).toString();
        if(position == 1) {
            maCheckBox.setVisibility(View.VISIBLE);
            monSwitchCreneau.setVisibility(View.VISIBLE);
            monTempsCreneau.setVisibility(View.VISIBLE);
            monButon.setVisibility(View.VISIBLE);
        }
        else{
            monEditTextIndicateur.setVisibility(View.VISIBLE);
            monSwitchCreneau.setVisibility(View.VISIBLE);
            monTempsCreneau.setVisibility(View.VISIBLE);
            monButon.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        maCheckBox.setVisibility(View.INVISIBLE);
        monTempsCreneau.setVisibility(View.INVISIBLE);
        monSwitchCreneau.setVisibility(View.INVISIBLE);
        monEditTextIndicateur.setVisibility(View.INVISIBLE);
    }


}
