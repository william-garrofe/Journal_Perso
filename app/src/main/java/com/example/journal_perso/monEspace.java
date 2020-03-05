package com.example.journal_perso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class monEspace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_espace);

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
