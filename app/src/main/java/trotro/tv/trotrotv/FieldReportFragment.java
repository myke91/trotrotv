package trotro.tv.trotrotv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import trotro.tv.trotrotv.adapter.StationListAdapter;
import trotro.tv.trotrotv.model.Station;


/**
 * A simple {@link Fragment} subclass.
 */
public class FieldReportFragment extends Fragment {
    ListView lv;

    public FieldReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout layout = (FrameLayout) inflater.inflate(R.layout.fragment_field_report, container, false);
        lv = (ListView) layout.findViewById(R.id.list_stations);

        getStations();

        return layout;
    }

    private List<Station> getStations() {
        List<Station> list = new ArrayList();
        Station station = new Station(getContext());
        station.setLocation("ACCRA");
        station.setName("ACCRA");
        list.add(station);

        station = new Station(getContext());
        station.setLocation("37");
        station.setName("37");
        list.add(station);

        station = new Station(getContext());
        station.setLocation("CIRCLE");
        station.setName("CIRCLE");
        list.add(station);

        StationListAdapter adapter = new StationListAdapter(getActivity(), list);
        lv.setAdapter(adapter);

        return list;

    }

}
