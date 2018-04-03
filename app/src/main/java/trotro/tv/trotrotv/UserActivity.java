package trotro.tv.trotrotv;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import trotro.tv.trotrotv.adapter.AccessCodeListAdapter;
import trotro.tv.trotrotv.adapter.StationListAdapter;
import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.db.DatabaseHandler;
import trotro.tv.trotrotv.model.AccessCode;
import trotro.tv.trotrotv.model.Brand;
import trotro.tv.trotrotv.model.Question;
import trotro.tv.trotrotv.model.Station;
import trotro.tv.trotrotv.model.Vehicle;

public class UserActivity extends AppCompatActivity {
    ListView listAccessCodes;
    Button mSyncButton;
    AccessCode mAccessCode;
    RequestQueue mRequestQueue;
    ProgressDialog mProgressDialog;
    final ObjectMapper mapper = new ObjectMapper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_user);

        mSyncButton = findViewById(R.id.sync_button);
        mSyncButton.setOnClickListener(syncButtonClickListener);
        mAccessCode = new AccessCode(getApplicationContext());

        listAccessCodes = findViewById(R.id.list_users);
        listAccessCodes.setOnItemClickListener(itemClickListener);

        DatabaseHandler mDbHandler = new DatabaseHandler(this);
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        mDbHandler.onCreate(db);

        getAccessCodes();
    }

    private void getAccessCodes() {
        List<AccessCode> list = new ArrayList<>();

        AccessCode accessCode = new AccessCode("KMensah", "123");
        mAccessCode.saveAccessCode(accessCode);
        list.add(accessCode);

        accessCode = new AccessCode("KMensah", "123");
        mAccessCode.saveAccessCode(accessCode);
        list.add(accessCode);

        accessCode = new AccessCode("AmaBee", "123");
        mAccessCode.saveAccessCode(accessCode);
        list.add(accessCode);

        accessCode = new AccessCode("Nana", "123");
        mAccessCode.saveAccessCode(accessCode);
        list.add(accessCode);

        accessCode = new AccessCode("Kwesi", "123");
        mAccessCode.saveAccessCode(accessCode);
        list.add(accessCode);

        accessCode = new AccessCode("MaameAwuku", "123");
        mAccessCode.saveAccessCode(accessCode);
        list.add(accessCode);

        accessCode = new AccessCode("Joojo", "123");
        mAccessCode.saveAccessCode(accessCode);
        list.add(accessCode);


//        List<AccessCode> list = mAccessCode.getAllAccessCodes();
        AccessCodeListAdapter adapter = new AccessCodeListAdapter(getApplicationContext(), list);
        listAccessCodes.setAdapter(adapter);
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            AccessCode accessCode = (AccessCode) listAccessCodes.getItemAtPosition(position);
            getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE).edit().putString("user", accessCode.getUsername()).apply();

            showValidateUserCodeDialog();
        }
    };

    View.OnClickListener syncButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mProgressDialog = ProgressDialog.show(getApplicationContext(), "", "Downloading access codes ...");
            mProgressDialog.show();

            JsonArrayRequest accessCodesRequest = new JsonArrayRequest(Request.Method.GET, Constants.BACKEND_BASE_URL + "/api/codes", null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    mAccessCode.clearData();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject json = response.getJSONObject(i);
                            AccessCode accessCode = mapper.readValue(json.toString(), AccessCode.class);
                            mAccessCode.saveAccessCode(accessCode);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    mProgressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mProgressDialog.dismiss();
                }
            });

            mRequestQueue.add(accessCodesRequest);
        }
    };

    public void showValidateUserCodeDialog() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_validate_user_code, (ViewGroup) findViewById(R.id.validate_dialog_activity));

        final TextInputEditText txtAccessCode = layout.findViewById(R.id.code);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(layout);
        builder.setPositiveButton("Validate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String user = getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE).getString("user", "");
                String code = txtAccessCode.getText().toString();
                boolean valid = mAccessCode.doValidate(user, code);
                if (valid) {
                    Intent intent = new Intent(UserActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    dialog.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setMessage("Invalid Code");
                    builder.setTitle(R.string.app_name);
                    AlertDialog errorDialog = builder.create();
                    errorDialog.show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
    }

}
