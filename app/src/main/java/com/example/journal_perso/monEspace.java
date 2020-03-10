package com.example.journal_perso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.journal_perso.models.espace;
import com.example.journal_perso.models.indicateur;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Vector;

import static java.security.AccessController.getContext;

public class monEspace extends AppCompatActivity {
    private espace esp = new espace();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_espace);

        Intent i = getIntent();
        espace dene = (espace)i.getSerializableExtra("Monobj");

        Toast.makeText(getApplicationContext(),
                "Click ListItem Number " + dene.getNom(), Toast.LENGTH_LONG)
                .show();
        FloatingActionButton btnAjout = (FloatingActionButton) findViewById(R.id.btnAjout);
        btnAjout.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(monEspace.this, ConfigurationIndicateur.class);
                startActivity(intent);
            }
        });
    }

}
