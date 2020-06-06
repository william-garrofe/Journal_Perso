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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.journal_perso.models.Espace;
import com.example.journal_perso.models.StructData;
import com.example.journal_perso.ui.mesEspaces.MesEspacesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class monEspace extends AppCompatActivity {
    private Espace esp = new Espace();
    private TextView monTView;
    private LinearLayout lay;
    private LinearLayout.LayoutParams p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_espace);

        ImageButton imageRetour = findViewById(R.id.imageRetour);

        Intent i = getIntent();

        final StructData data = (StructData) i.getSerializableExtra("maData");
        final Espace esp = (Espace) i.getSerializableExtra("espace");

        lay = findViewById(R.id.LayoutMonEsp);
        p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int j = 0; j < esp.getcIndic().size(); j++) {
            int type = esp.getcIndic().get(j).getTypeIndic();
            String nom = esp.getcIndic().get(j).getNom();
            CardView cardView = new CardView(getApplicationContext());
            cardView.setLayoutParams(p);

            switch (type) {
                case 0:

                    final CardView cv = newCardView();

                    final TextView tv = new TextView(getApplicationContext());
                    final TextView text = new TextView(getApplicationContext());
                    text.setLayoutParams(p);
                    text.setText(nom);

                    LinearLayout cl = new LinearLayout(getApplicationContext());
                    cl.setOrientation(LinearLayout.VERTICAL);
                    cl.setId(View.generateViewId());

                    tv.setLayoutParams(p);
                    tv.setText("Indicateur n°" + (j + 1) + " (EditText)");
                    tv.setId(View.generateViewId());

                    cl.addView(tv);
                    cl.addView(text);

                    cv.addView(cl);
                    lay.addView(cv);
                    break;

                case 1:

                    final CardView cv1 = newCardView();

                    final TextView tv1 = new TextView(getApplicationContext());
                    final CheckBox cb = new CheckBox(getApplicationContext());

                    LinearLayout cl1 = new LinearLayout(getApplicationContext());
                    cl1.setOrientation(LinearLayout.VERTICAL);
                    cl1.setId(View.generateViewId());
                    //p= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    cb.setText(nom);
                    cb.setLayoutParams(p);

                    tv1.setLayoutParams(p);
                    tv1.setText("Indicateur n°" + (j + 1) + " (CheckBox)");
                    tv1.setId(View.generateViewId());

                    cl1.addView(tv1);
                    cl1.addView(cb);
                    cv1.addView(cl1);
                    lay.addView(cv1);
                    break;
            }
        }

        imageRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MesEspacesFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_mes_esapces, fragment);
                transaction.addToBackStack(null);
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

    public CardView newCardView() {
        CardView cardView = new CardView(getApplicationContext());
        cardView.setId(View.generateViewId());
        cardView.setPadding(16, 16, 16, 16);
        cardView.setElevation(5);
        cardView.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT + 150
        ));

        return cardView;
    }
}
