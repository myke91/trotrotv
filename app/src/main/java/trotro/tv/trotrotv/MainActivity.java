package trotro.tv.trotrotv;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.db.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            FragmentTransaction ft;
            switch (item.getItemId()) {
                case R.id.navigation_field_report:
                    fragment = new FieldReportFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainFrame, fragment);
                    ft.commit();
                    return true;
                case R.id.navigation_survey:
                    fragment = new BrandSurveyFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainFrame, fragment);
                    ft.commit();
                    return true;
                case R.id.navigation_sync:
                    fragment = new SynchronizationFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainFrame, fragment);
                    ft.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String message = getIntent().getStringExtra("message");
        if (message != null && message.equals("GO_TO_SYNC")) {
            Fragment fragment = new SynchronizationFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();
        } else {
            Fragment fragment = new FieldReportFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();
        }

        DatabaseHandler mDbHandler = new DatabaseHandler(this);
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        mDbHandler.onCreate(db);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
