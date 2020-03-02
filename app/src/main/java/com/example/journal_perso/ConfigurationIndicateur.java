package com.example.journal_perso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class ConfigurationIndicateur extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private CheckBox maCheckBox = findViewById(R.id.checkBoxIndicateur);
    private EditText monEditTextIndicateur =  findViewById(R.id.editTextIndicateur);
    private EditText monIndicateurNom =  findViewById(R.id.nomIndicateur);
    private Switch monSwitchCreneau = findViewById(R.id.switchCreneau);
    private EditText monTempsCreneau = findViewById(R.id.tempsCreneau);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_indicateur);

        Spinner monSpinner = (Spinner) findViewById(R.id.typeIndicateur);
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
        }
        else{
            monEditTextIndicateur.setVisibility(View.VISIBLE);
            monSwitchCreneau.setVisibility(View.VISIBLE);
            monTempsCreneau.setVisibility(View.VISIBLE);
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
