package com.example.journal_perso.ui.esapcesDuJour;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.journal_perso.DataEspace;
import com.example.journal_perso.R;
import com.example.journal_perso.models.Espace;
import com.example.journal_perso.models.GsonFic;
import com.example.journal_perso.models.StructData;
import com.example.journal_perso.models.User;
import com.example.journal_perso.services.EspaceService;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Vector;

public class EspacesDuJourFragment extends Fragment {

    private EspaceDuJourViewModel espaceDuJourViewModel;
    final private GsonFic gf = new GsonFic();
    final EspaceService espaceService = new EspaceService();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        espaceDuJourViewModel =
                ViewModelProviders.of(this).get(EspaceDuJourViewModel.class);
        View root = inflater.inflate(R.layout.fragment_espaces_du_jour, container, false);

        final Intent in = getActivity().getIntent();
        final User usr = (User) in.getSerializableExtra("user");

        //test();

        final LinearLayout ll = root.findViewById(R.id.maLayoutEspJ);
        TextView t = root.findViewById(R.id.textV_espJour);
        int annee, mois, jour;

        StructData maDatas = (StructData) gf.LireFichier(getContext(), "monJson.json");
        final StructData finalData = maDatas;

        if (maDatas != null) {

            Calendar calendar = Calendar.getInstance();
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            annee = calendar.get(calendar.YEAR);
            mois = calendar.get(Calendar.MONTH);
            jour = calendar.get(Calendar.DAY_OF_MONTH);
            final String dateDuJour = " Date du jour :" + jour + "/" + mois + "/" + annee;
            t.setText(dateDuJour);

            for (int i = 0; i < finalData.getMesEspaces().size(); i++) {
                if (finalData.getMesEspaces().get(i).getListJour().contains(dayOfWeek)) {
                    LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    Button nB = new Button(getContext());
                    nB.setLayoutParams(p);
                    nB.setId(i + 1);
                    nB.setText(finalData.getMesEspaces().get(i).getNom());
                    final int index = i;
                    nB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DataEspace.class);
                            intent.putExtra("espace", finalData.getMesEspaces().get(index));
                            intent.putExtra("maData", finalData);
                            intent.putExtra("date", dateDuJour);
                            intent.putExtra("user", usr);
                            startActivity(intent);
                        }
                    });
                    ll.addView(nB);
                }
            }
        } else {
            maDatas = new StructData();
            maDatas.setMesEspaces(new Vector<Espace>());
        }
        return root;
    }

    public void test() {
        try {
            espaceService.getListEspace(new EspaceService.OnJSONResponseCallback() {
                @Override
                public void onJSONResponse(boolean success, Object object) {
                    if (object instanceof StructData) {
                        StructData test = (StructData) object;
                        System.out.println(test);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}