package com.example.journal_perso.ui.calendrier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.journal_perso.R;
import com.example.journal_perso.models.espace;
import com.example.journal_perso.models.gsonFic;
import com.example.journal_perso.models.maData;

import java.util.ArrayList;
import java.util.Vector;

public class CalendrierFragment extends Fragment {

    private CalendrierViewModel calendrierViewModel;
    final private gsonFic gf = new gsonFic();
    private ListView listCal;
    private String nomEspace;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendrierViewModel =
                ViewModelProviders.of(this).get(CalendrierViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendrier, container, false);

        final CalendarView cal = root.findViewById(R.id.calendarView);
        listCal = root.findViewById(R.id.listCalendrier);
        final ArrayList<String> list = new ArrayList<>();

        maData maDatas = gf.LireFichier(getContext(), "monJson.json");

        if (maDatas == null) {
            maDatas = new maData();
            maDatas.setMesEspaces(new Vector<espace>());
        }

        for (int i = 0; i < maDatas.getMesEspaces().size(); i++) {
            nomEspace = maDatas.getMesEspaces().get(i).nomEsp();
            list.add(nomEspace);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list); //items maDatas.getMesEspaces()
        listCal.setAdapter(adapter);

        long test = cal.getDate();

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast patate = Toast.makeText(getContext(), "test", Toast.LENGTH_LONG);
                patate.show();
            }
        });

        return root;
    }
}