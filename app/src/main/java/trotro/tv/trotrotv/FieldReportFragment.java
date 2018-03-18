package trotro.tv.trotrotv;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import trotro.tv.trotrotv.adapter.StationListAdapter;
import trotro.tv.trotrotv.adapter.VehicleListAdapter;
import trotro.tv.trotrotv.model.Question;
import trotro.tv.trotrotv.model.Report;
import trotro.tv.trotrotv.model.Station;
import trotro.tv.trotrotv.model.Vehicle;


/**
 * A simple {@link Fragment} subclass.
 */
public class FieldReportFragment extends Fragment {
    ListView listStations;
    ListView listVehicles;
    LinearLayout formQuestion;
    TextView mVehicleNumber;

    Station mStation;
    Question mQuestion;

    public FieldReportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStation = new Station(getContext());
        mQuestion = new Question(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_field_report, container, false);
        listStations = layout.findViewById(R.id.list_stations);
        listStations.setOnItemClickListener(stationsItemClickListener);

        listVehicles = layout.findViewById(R.id.list_vehicles);
        listVehicles.setOnItemClickListener(vehiclesItemClickListener);

        mVehicleNumber = layout.findViewById(R.id.vehicle_number);
        mButtonSave = layout.findViewById(R.id.button_save);

        formQuestion = layout.findViewById(R.id.form_question);

        getStations();
        return layout;
    }

    private List<Station> getStations() {
//        List<Station> list = new ArrayList();
//        Station station = new Station(getContext());
//        station.setLocation("ACCRA");
//        station.setBrandName("ACCRA");
//        list.add(station);
//
//        station = new Station(getContext());
//        station.setLocation("37");
//        station.setBrandName("37");
//        list.add(station);
//
//        station = new Station(getContext());
//        station.setLocation("CIRCLE");
//        station.setBrandName("CIRCLE");
//        list.add(station);

        List<Station> list = mStation.getAllStations();
        StationListAdapter adapter = new StationListAdapter(getActivity(), list);
        listStations.setAdapter(adapter);

        return list;
    }

    private List<Vehicle> getVehicles(Station station) {
        List<Vehicle> list = new ArrayList();
        Vehicle vehicle = new Vehicle(getContext());
        vehicle.setStation(station.getStationName());
        vehicle.setVehicle("GT 984 Y");
        list.add(vehicle);

        vehicle = new Vehicle(getContext());
        vehicle.setStation(station.getStationName());
        vehicle.setVehicle("GW 84 10");
        list.add(vehicle);

        vehicle = new Vehicle(getContext());
        vehicle.setStation(station.getStationName());
        vehicle.setVehicle("GT 487 12");
        list.add(vehicle);


        return list;

    }

    private List<Question> getQuestions() {
        List<Question> list = new ArrayList();
        Question que = new Question(getContext());
        que.setQuestion("Is the screen working?");
        list.add(que);

        que = new Question(getContext());
        que.setQuestion("Does it start with key?");
        list.add(que);

        que = new Question(getContext());
        que.setQuestion("Is driver happy?");
        list.add(que);


        return list;

    }

    AdapterView.OnItemClickListener stationsItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Station station = (Station) listStations.getItemAtPosition(position);
            List list = getVehicles(station);
            VehicleListAdapter adapter = new VehicleListAdapter(getActivity(), list);
            listVehicles.setAdapter(adapter);

            listStations.setVisibility(View.GONE);
            listVehicles.setVisibility(View.VISIBLE);

        }
    };

    LinearLayout mFormItemLayout;
    EditText mQuestionText;
    ToggleButton mAnswerButton;
    Button mButtonSave;

    AdapterView.OnItemClickListener vehiclesItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Vehicle vehicle = (Vehicle) listVehicles.getItemAtPosition(position);
            List<Question> list = getQuestions();
            getContext().getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE).edit().putString("currentVehicle", vehicle.getVehicle()).apply();
            formQuestion.removeAllViews();
            for (Question question : list) {
                mFormItemLayout = new LinearLayout(getContext());
                mFormItemLayout.setOrientation(LinearLayout.HORIZONTAL);
                mFormItemLayout.setWeightSum(2);

                mQuestionText = new EditText(getContext());
                mQuestionText.setText(question.getQuestion());
                mQuestionText.setEnabled(false);
                mQuestionText.setTextColor(Color.BLACK);
                mFormItemLayout.addView(mQuestionText);

                mAnswerButton = new ToggleButton(getContext());
                mAnswerButton.setTextOff("NO");
                mAnswerButton.setTextOn("YES");
                mAnswerButton.setText("NO");
                mFormItemLayout.addView(mAnswerButton);

                formQuestion.addView(mFormItemLayout);
            }

            mVehicleNumber.setText(getContext().getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE).getString("currentVehicle", ""));
            mVehicleNumber.setVisibility(View.VISIBLE);

            mButtonSave.setOnClickListener(saveClickListener);
            mButtonSave.setVisibility(View.VISIBLE);

            listVehicles.setVisibility(View.GONE);
            formQuestion.setVisibility(View.VISIBLE);
        }
    };

    View.OnClickListener saveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < formQuestion.getChildCount(); i++) {
//                try {
                    Report report = new Report(getContext());
                    LinearLayout child = (LinearLayout) formQuestion.getChildAt(i);
                    report.setQuestion(((EditText) child.getChildAt(0)).getText().toString());
                    report.setAnswer(((ToggleButton) child.getChildAt(1)).getText().toString());
                    report.setVehicle(getContext().getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE).getString("currentVehicle", ""));
                    report.saveReport(report);

                    formQuestion.setVisibility(View.GONE);
                    mButtonSave.setVisibility(View.GONE);
                    mVehicleNumber.setVisibility(View.GONE);

                    listStations.setVisibility(View.VISIBLE);

//                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                    builder.setMessage("SAVE SUCCESSFUL");
//                    builder.setTitle(R.string.app_name);
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                } catch (Exception ex) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                    builder.setMessage("ERROR OCCURED WHILST SAVING REPORT");
//                    builder.setTitle(R.string.app_name);
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                }
            }
        }
    };

}
