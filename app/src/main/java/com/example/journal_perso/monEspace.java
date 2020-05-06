package com.example.journal_perso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.journal_perso.models.Espace;
import com.example.journal_perso.models.StructData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class monEspace extends AppCompatActivity {
    private Espace esp = new Espace();
    private TextView monTView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_espace);

        ImageButton imageRetour = findViewById(R.id.imageRetour);

        Intent i = getIntent();

        final StructData data = (StructData) i.getSerializableExtra("maData");
        final Espace esp = (Espace) i.getSerializableExtra("espace");

        LinearLayout lay = findViewById(R.id.LayoutMonEsp);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        for (int j = 0; j < esp.getcIndic().size(); j++) {
            int type = esp.getcIndic().get(j).getTypeIndic();
            String nom = esp.getcIndic().get(j).getNom();
            String txt = esp.getcIndic().get(j).getText();
            String temps = esp.getcIndic().get(j).getTemps();
            CardView cardView = new CardView(getApplicationContext());
            cardView.setLayoutParams(p);

            switch (type) {
                case 0:


                    TextView text = new TextView(getApplicationContext());
                    text.setLayoutParams(p);
                    text.setText(txt);

                    TextView textView = new TextView(getApplicationContext());
                    textView.setLayoutParams(p);
                    textView.setText(temps);

                    LinearLayout ll2 = new LinearLayout(getApplicationContext());
                    ll2.setOrientation(LinearLayout.VERTICAL);
                    ll2.addView(text);
                    ll2.addView(textView);

                    cardView.addView(ll2);
                    lay.addView(cardView);
                    break;

                case 1:
                    CheckBox cb = new CheckBox(getApplicationContext());
                    cb.setText(nom);
                    lay.addView(cb);
                    break;
            }
        }

        imageRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Fragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_mes_esapces, fragment);
                transaction.commit();
            }
        });

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
