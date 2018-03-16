package trotro.tv.trotrotv.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import trotro.tv.trotrotv.constants.Constants;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DatabaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BRAND_TABLE = "CREATE TABLE " + Constants.TABLE_BRAND + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";

        String CREATE_REPORT_TABLE = "CREATE TABLE " + Constants.TABLE_REPORT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";

        String CREATE_STATION_TABLE = "CREATE TABLE " + Constants.TABLE_STATION + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";

        String CREATE_SURVEY_TABLE = "CREATE TABLE " + Constants.TABLE_SURVEY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";

        String CREATE_VEHICLE_TABLE = "CREATE TABLE " + Constants.TABLE_VEHICLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";

        db.execSQL(CREATE_BRAND_TABLE);
        db.execSQL(CREATE_REPORT_TABLE);
        db.execSQL(CREATE_STATION_TABLE);
        db.execSQL(CREATE_SURVEY_TABLE);
        db.execSQL(CREATE_VEHICLE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_BRAND);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_REPORT);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_STATION);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_SURVEY);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_VEHICLE);

        // Create tables again
        onCreate(db);
    }
}
