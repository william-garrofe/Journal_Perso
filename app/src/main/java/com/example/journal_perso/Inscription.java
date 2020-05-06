package com.example.journal_perso;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.journal_perso.services.UserService;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;


public class Inscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);

        Button btnFenInscription = findViewById(R.id.btnFenInscription);
        final EditText edtNom = findViewById(R.id.edtNom);
        final EditText edtPrenom = findViewById(R.id.edtPrenom);
        final EditText edtEmail = findViewById(R.id.edtEmail);
        final EditText edtPassword = findViewById(R.id.edtPassword);

        btnFenInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserService userService = new UserService();
                try {
                    userService.createUser(new UserService.OnJSONResponseCallback() {
                        @Override
                        public void onJSONResponse(boolean success, Object object) {
                            System.out.println(object);
                        }
                    }, edtNom.getText().toString(), edtPrenom.getText().toString(), edtEmail.getText().toString(), edtPassword.getText().toString(), getApplicationContext());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
