package com.example.journal_perso.ui.calendrier;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.journal_perso.DataEspace;
import com.example.journal_perso.R;
import com.example.journal_perso.models.espace;
import com.example.journal_perso.models.gsonFic;
import com.example.journal_perso.models.maData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class CalendrierFragment extends Fragment {

    private CalendrierViewModel calendrierViewModel;
    private espace mesEsp;
    final private gsonFic gf = new gsonFic();
    private ListView listCal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendrierViewModel =
                ViewModelProviders.of(this).get(CalendrierViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendrier, container, false);

        final CalendarView cal = root.findViewById(R.id.calendarView);
        //listCal = root.findViewById(R.id.listCalendrier);

        final ArrayList<String> list = new ArrayList<>();
        final LinearLayout ll = root.findViewById(R.id.maLayoutCalendrier);

        maData maDatas = gf.LireFichier(getContext(), "monJson.json");
        final maData finalData = maDatas;

        if (maDatas == null) {
            maDatas = new maData();
            maDatas.setMesEspaces(new Vector<espace>());
        }

        /*for (int i = 0; i < maDatas.getMesEspaces().size(); i++) {
            nomEspace = maDatas.getMesEspaces().get(i).nomEsp();
            list.add(nomEspace);
        }*/

        long test = cal.getDate();

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                final String date = dayOfMonth + "/" + month + "/" + year;
                ll.removeAllViews();
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
                                //intent.putExtra("maData", finalData);
                                intent.putExtra("date", date);
                                startActivity(intent);
                            }
                        });
                        ll.addView(nB);
                    }
                }
            }
        });

        return root;
    }
}