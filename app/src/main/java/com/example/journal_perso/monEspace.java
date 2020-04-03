package com.example.journal_perso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.journal_perso.models.espace;
import com.example.journal_perso.models.maData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class monEspace extends AppCompatActivity {
    private espace esp = new espace();
    private TextView monTView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_espace);

        Intent i = getIntent();

        final maData data = (maData) i.getSerializableExtra("maData");
        final espace esp = (espace) i.getSerializableExtra("espace");

        LinearLayout lay = findViewById(R.id.LayoutMonEsp);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //TODO : List indicateur
        for (int j = 0; j < esp.getcIndic().size(); j++) {
            int type = esp.getcIndic().get(j).getTypeIndic();
            String nom = esp.getcIndic().get(j).getNom();
            switch (type) {
                case 1:
                    EditText text = new EditText(getApplicationContext());
                    text.setLayoutParams(p);
                    text.setHint("texte");
                    lay.addView(text);
                    break;
            }
        }

        monTView = findViewById(R.id.textView5);
        monTView.setText(esp.getNom());
        FloatingActionButton btnAjout = (FloatingActionButton) findViewById(R.id.btnAjout);
        btnAjout.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(monEspace.this, ConfigurationIndicateur.class);
                intent.putExtra("espace", esp);
                intent.putExtra("maData", data);
                startActivity(intent);
            }
        });
    }

}
