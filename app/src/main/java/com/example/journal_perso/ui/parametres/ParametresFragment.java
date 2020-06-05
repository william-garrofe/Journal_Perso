package com.example.journal_perso.ui.parametres;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.journal_perso.MainActivity;
import com.example.journal_perso.R;
import com.example.journal_perso.models.GsonFic;
import com.example.journal_perso.models.User;
import com.example.journal_perso.services.UserService;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;

public class ParametresFragment extends Fragment {

    private ParametresViewModel parametresViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        parametresViewModel =
                ViewModelProviders.of(this).get(ParametresViewModel.class);
        View root = inflater.inflate(R.layout.fragment_parametres, container, false);

        final EditText edtNom = root.findViewById(R.id.editNomParam);
        final EditText edtPrenom = root.findViewById(R.id.editPrenomParam);
        final EditText edtEmail = root.findViewById(R.id.editEmailParam);
        final EditText edtMdp = root.findViewById(R.id.editMdpParam);
        Button btnModifier = root.findViewById(R.id.btnModifParam);
        Button btnDeco = root.findViewById(R.id.btnDeconnexion);

        final UserService usrService = new UserService();
        final GsonFic gf = new GsonFic();
        final User usr = (User) gf.LireFichier(getContext(), "user.json");

        edtNom.setText(usr.getNom());
        edtPrenom.setText(usr.getPrenom());
        edtEmail.setText(usr.getEmail());
        edtMdp.setText(usr.getMotdepasse());

        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    usrService.update(edtNom.getText().toString(), edtPrenom.getText().toString(), edtEmail.getText().toString(), edtMdp.getText().toString(), usr.getIdUser(), getContext());
                    Toast.makeText(getContext(), "Modification enregistrée",
                            Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        btnDeco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}