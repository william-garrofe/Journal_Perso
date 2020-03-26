package com.example.journal_perso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.journal_perso.models.espace;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfigurationIndicateur extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private CheckBox maCheckBox;
    private EditText monEditTextIndicateur;
    private EditText monIndicateurNom;
    private Switch monSwitchCreneau;
    private EditText monTempsCreneau;
    private Button monButton;
    private Spinner monSpinner;
    private espace dene;

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
        Intent i = getIntent();
        dene = (espace)i.getSerializableExtra("MonObj");


        ArrayAdapter<CharSequence> monAdaptater = ArrayAdapter.createFromResource(this, R.array.typeIndicateurNom,android.R.layout.simple_spinner_item);
        monAdaptater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(monAdaptater);
        monSpinner.setOnItemSelectedListener(this);

        monButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ecrireFichier();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String monText = parent.getItemAtPosition(position).toString();
       // numIndic = position;
        switch (position)
        {
            case 0:
                maCheckBox.setVisibility(View.VISIBLE);
                monSwitchCreneau.setVisibility(View.VISIBLE);
                monTempsCreneau.setVisibility(View.VISIBLE);
                monButton.setVisibility(View.VISIBLE);
                break;

            case 1:
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

    public void ecrireFichier()
    {
        final GsonBuilder builder = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting();
        final Gson gson = builder.create();

        String filename = "monJson.json";

        String fileContents = gson.toJson(dene);  //Ne pas oublier
        FileOutputStream monFichier;

        try
        {
            monFichier = openFileOutput(filename, Context.MODE_PRIVATE);
            monFichier.write(fileContents.getBytes());
            monFichier.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
