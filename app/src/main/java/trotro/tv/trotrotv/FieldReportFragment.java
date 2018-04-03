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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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
    TextView mPageTitle;
    EditText mAdditionalComments;

    Station mStation;
    Question mQuestion;
    Vehicle mVehicle;

    public FieldReportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStation = new Station(getContext());
        mQuestion = new Question(getContext());
        mVehicle = new Vehicle(getContext());
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

        mAdditionalComments = layout.findViewById(R.id.additionalCommentText);

        formQuestion = layout.findViewById(R.id.form_question);
        mPageTitle = layout.findViewById(R.id.page_title);

        getStations();
        return layout;
    }

    private void getStations() {
        List<Station> list = mStation.getAllStations();
        StationListAdapter adapter = new StationListAdapter(getActivity(), list);
        listStations.setAdapter(adapter);
    }

    private List<Vehicle> getVehicles(Station station) {
        return mVehicle.getVehiclesForStation(station);
    }

    private List<Question> getQuestions() {
        return mQuestion.getQuestionsForReport();
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

            mPageTitle.setText("VEHICLES");

        }
    };

    LinearLayout mFormItemLayout;
    EditText mQuestionText;
    Spinner mAnswerSpinner;
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
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mFormItemLayout.setLayoutParams(lp);
                mFormItemLayout.setWeightSum(2f);


                mQuestionText = new EditText(getContext());
                mQuestionText.setText(question.getQuestion());
                mQuestionText.setEnabled(false);
                mQuestionText.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams lpQuestionText = (LinearLayout.LayoutParams) mFormItemLayout.getLayoutParams();
                lpQuestionText.weight = 1.7f;
                mQuestionText.setLayoutParams(lpQuestionText);
                mFormItemLayout.addView(mQuestionText);

                mAnswerSpinner = new Spinner(getContext());
                List<String> answers = new ArrayList<String>();
                answers.add("NO");
                answers.add("YES");
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, answers);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mAnswerSpinner.setAdapter(dataAdapter);
                LinearLayout.LayoutParams lpAnswerAnswer = (LinearLayout.LayoutParams) mFormItemLayout.getLayoutParams();
                lpAnswerAnswer.weight = 0.3f;
                mAnswerSpinner.setLayoutParams(lpAnswerAnswer);
                mFormItemLayout.addView(mAnswerSpinner);

                formQuestion.addView(mFormItemLayout);
            }
            
            mVehicleNumber.setText(getContext().getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE).getString("currentVehicle", ""));
            mVehicleNumber.setVisibility(View.VISIBLE);

            mButtonSave.setOnClickListener(saveClickListener);
            mButtonSave.setVisibility(View.VISIBLE);

            mAdditionalComments.setVisibility(View.VISIBLE);

            listVehicles.setVisibility(View.GONE);
            formQuestion.setVisibility(View.VISIBLE);

            mPageTitle.setText("QUESTIONS");
        }
    };

    View.OnClickListener saveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < formQuestion.getChildCount(); i++) {

                Report report = new Report(getContext());
                LinearLayout child = (LinearLayout) formQuestion.getChildAt(i);
                report.setQuestion(((EditText) child.getChildAt(0)).getText().toString());
                report.setAnswer(((Spinner) child.getChildAt(1)).getSelectedItem().toString());
                report.setVehicle(getContext().getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE).getString("currentVehicle", ""));
                report.setUser(getContext().getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE).getString("user", ""));
                report.setComments(mAdditionalComments.getText().toString());
                report.saveReport(report);

                formQuestion.setVisibility(View.GONE);
                mButtonSave.setVisibility(View.GONE);
                mAdditionalComments.setVisibility(View.GONE);
                mVehicleNumber.setVisibility(View.GONE);

                listStations.setVisibility(View.VISIBLE);

            }
        }
    };

}
