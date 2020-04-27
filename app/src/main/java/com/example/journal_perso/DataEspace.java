package com.example.journal_perso;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.journal_perso.models.espace;
import com.example.journal_perso.models.gsonFic;

public class DataEspace extends AppCompatActivity {


    final gsonFic gf = new gsonFic();
    Intent i = getIntent();
    final espace esp = (espace) i.getSerializableExtra("espace");
    //final maDataLocal dataLocal = (maDataLocal) i.getSerializableExtra("maDataLocal");
    final String date = (String) i.getSerializableExtra("date");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_espace);
    }


}
