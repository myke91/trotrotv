package trotro.tv.trotrotv;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.model.Brand;
import trotro.tv.trotrotv.model.Question;
import trotro.tv.trotrotv.model.Report;
import trotro.tv.trotrotv.model.Station;
import trotro.tv.trotrotv.model.Survey;
import trotro.tv.trotrotv.model.Vehicle;


/**
 * A simple {@link Fragment} subclass.
 */
public class SynchronizationFragment extends Fragment {

    RequestQueue mRequestQueue;
    ProgressDialog mProgressDialog;
    Station mStation;
    Question mQuestion;
    Brand mBrand;
    Report mReport;
    Survey mSurvey;
    Vehicle mVehicle;

    TextView mTextProcessOutput;

    final ObjectMapper mapper = new ObjectMapper();
    StringBuffer mProcessOutput;
    JSONArray reportData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mStation = new Station(getContext());
        mQuestion = new Question(getContext());
        mBrand = new Brand(getContext());
        mReport = new Report(getContext());
        mSurvey = new Survey(getContext());
        mVehicle = new Vehicle(getContext());

        ArrayList<Report> reports = (ArrayList<Report>) mReport.getAllReports();

        try {
            Gson gson = new Gson();
            String listString = gson.toJson(
                    reports,
                    new TypeToken<ArrayList<Report>>() {
                    }.getType());
            reportData = new JSONArray(listString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_upload, container, false);

        //init sync button
        Button btnSync = layout.findViewById(R.id.button_sync);
        btnSync.setOnClickListener(syncClickListener);

        //init output area
        mTextProcessOutput = layout.findViewById(R.id.output_area);

        //init process output
        mProcessOutput = new StringBuffer();

        return layout;
    }

    View.OnClickListener syncClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mProgressDialog = ProgressDialog.show(getContext(), "", "Processing ...");

            //clear output string buffer
            mProcessOutput.setLength(0);

            //clear output text box
            mTextProcessOutput.setText("");

//            mRequestQueue.add(brandsRequest);
//            mRequestQueue.add(vehiclesRequest);
//            mRequestQueue.add(stationsRequest);
//            mRequestQueue.add(questionsRequest);
            mRequestQueue.add(reportsRequest);
//            mRequestQueue.add(surveysRequest);
            mProgressDialog.dismiss();

        }
    };

    JsonArrayRequest stationsRequest = new JsonArrayRequest(Request.Method.GET, Constants.BACKEND_BASE_URL + "/api/stations", null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            mStation.clearData();
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject json = response.getJSONObject(i);
                    Station station = mapper.readValue(json.toString(), Station.class);
                    mStation.saveStation(station);
                    mProcessOutput.append("Downloaded and saved station ---> ").append(station.getStationName()).append("\n");
                    mTextProcessOutput.setText(mProcessOutput.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mProcessOutput.append("Error downloading stations ").append(error.getMessage());
            mTextProcessOutput.setText(mProcessOutput.toString());
        }
    });

    JsonArrayRequest questionsRequest = new JsonArrayRequest(Request.Method.GET, Constants.BACKEND_BASE_URL + "/api/questions", null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            mQuestion.clearData();
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject json = response.getJSONObject(i);
                    Question question = mapper.readValue(json.toString(), Question.class);
                    mQuestion.saveQuestion(question);
                    mProcessOutput.append("Downloaded and saved question ---> ").append(question.getQuestion()).append("\n");
                    mTextProcessOutput.setText(mProcessOutput.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mProcessOutput.append("Error downloading questions ").append(error.getMessage()).append("\n");
            mTextProcessOutput.setText(mProcessOutput.toString());
        }
    });

    JsonArrayRequest vehiclesRequest = new JsonArrayRequest(Request.Method.GET, Constants.BACKEND_BASE_URL + "/api/vehicles", null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            mVehicle.clearData();
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject json = response.getJSONObject(i);
                    Vehicle vehicle = mapper.readValue(json.toString(), Vehicle.class);
                    mVehicle.saveVehicle(vehicle);
                    mProcessOutput.append("Downloaded and saved vehicle ---> ").append(vehicle.getVehicle()).append("\n");
                    mTextProcessOutput.setText(mProcessOutput.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mProcessOutput.append("Error downloading vehicles ").append(error.getMessage()).append("\n");
            mTextProcessOutput.setText(mProcessOutput.toString());
        }
    });

    JsonArrayRequest brandsRequest = new JsonArrayRequest(Request.Method.GET, Constants.BACKEND_BASE_URL + "/api/brands", null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            mBrand.clearData();
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject json = response.getJSONObject(i);
                    Brand brand = mapper.readValue(json.toString(), Brand.class);
                    mBrand.saveBrand(brand);
                    mProcessOutput.append("Downloaded and saved brand ---> ").append(brand.getBrandName()).append("\n");
                    mTextProcessOutput.setText(mProcessOutput.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mProcessOutput.append("Error downloading brand ").append(error.getMessage()).append("\n");
            mTextProcessOutput.setText(mProcessOutput.toString());
        }
    });

    JsonArrayRequest reportsRequest = new JsonArrayRequest(Request.Method.POST, Constants.BACKEND_BASE_URL + "/api/report", reportData, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject json = response.getJSONObject(i);
                    Report report = mapper.readValue(json.toString(), Report.class);
                    mReport.editReport(report);
                    mProcessOutput.append("Uploaded report for ---> ").append(report.getVehicle()).append("\n");
                    mTextProcessOutput.setText(mProcessOutput.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            mReport.clearReportData();
        }
    }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            mProcessOutput.append("Error uploading reports ").append(error.getMessage()).append("\n");
            mTextProcessOutput.setText(mProcessOutput.toString());
        }
    });

    JsonArrayRequest surveysRequest = new JsonArrayRequest(Request.Method.POST, Constants.BACKEND_BASE_URL + "/api/survey", null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject json = response.getJSONObject(i);
                    Survey survey = mapper.readValue(json.toString(), Survey.class);
                    mSurvey.editSurvey(survey);
                    mProcessOutput.append("Uploaded survey for ---> ").append(survey.getBrand()).append("\n");
                    mTextProcessOutput.setText(mProcessOutput.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            mSurvey.clearSurveyData();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mProcessOutput.append("Error uploading surveys ").append(error.getMessage()).append("\n");
            mTextProcessOutput.setText(mProcessOutput.toString());
        }
    });
}
