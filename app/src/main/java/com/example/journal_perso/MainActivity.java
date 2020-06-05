package com.example.journal_perso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.journal_perso.models.GsonFic;
import com.example.journal_perso.models.User;
import com.example.journal_perso.services.UserService;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    Button btnOk;
    Button btnInscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOk = (Button) findViewById(R.id.btnConnexion);
        btnInscription = (Button) findViewById(R.id.btnFenInscription);
        final EditText edtLogin = findViewById(R.id.edtLogin);
        final EditText edtPasse = findViewById(R.id.editPasse);

        final UserService userService = new UserService();
        final GsonFic gf = new GsonFic();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    userService.getUser(new UserService.OnJSONResponseCallback() {
                        @Override
                        public void onJSONResponse(boolean success, Object object) {
                            if (object instanceof User) {
                                User usr = (User) object;
                                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                                intent.putExtra("user", usr);
                                gf.ecrireFichier(usr, getApplicationContext(), "user.json");
                                startActivity(intent);
                            }
                        }
                    }, edtLogin.getText().toString(), edtPasse.getText().toString(), getApplicationContext());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Inscription.class);
                startActivity(intent);
            }
        });
    }

    public void callBack(View v){
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
