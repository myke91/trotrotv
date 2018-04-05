package trotro.tv.trotrotv.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import trotro.tv.trotrotv.constants.Constants;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BRAND_TABLE = "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_BRAND + "("
                + Constants.BRAND_KEY_ID + " INTEGER, "
                + Constants.BRAND_KEY_NAME + " TEXT, "
                + Constants.BRAND_KEY_LOCATION + " TEXT, "
                + Constants.BRAND_KEY_CONTACT_PERSON + " TEXT, "
                + Constants.BRAND_KEY_CONTACT_NUMBER + " TEXT, "
                + Constants.BRAND_KEY_EMAIL + " TEXT" + ")";

        String CREATE_REPORT_TABLE = "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_REPORT + "("
                + Constants.REPORT_KEY_ID + " INTEGER PRIMARY KEY, "
                + Constants.REPORT_KEY_QUESTION + " TEXT, "
                + Constants.REPORT_KEY_ANSWER + " TEXT, "
                + Constants.REPORT_KEY_TIMESTAMP + " TEXT, "
                + Constants.REPORT_KEY_UPLOADED + " TEXT, "
                + Constants.REPORT_KEY_VEHICLE_NUMBER + " TEXT, "
                + Constants.REPORT_KEY_USER + " TEX, "
                + Constants.REPORT_KEY_COMMENTS + " TEXT)";

        String CREATE_STATION_TABLE = "CREATE TABLE IF NOT EXISTS  " + Constants.TABLE_STATION + "("
                + Constants.STATION_KEY_ID + " INTEGER, "
                + Constants.STATION_KEY_NAME + " TEXT, "
                + Constants.STATION_KEY_LOCATION + " TEXT )";

        String CREATE_SURVEY_TABLE = "CREATE TABLE IF NOT EXISTS  " + Constants.TABLE_SURVEY + "("
                + Constants.SURVEY_KEY_ID + " INTEGER PRIMARY KEY, "
                + Constants.SURVEY_KEY_BRAND_NAME + " TEXT, "
                + Constants.SURVEY_KEY_QUESTION + " TEXT, "
                + Constants.SURVEY_KEY_ANSWER + " TEXT, "
                + Constants.SURVEY_KEY_TIMESTAMP + " TEXT, "
                + Constants.SURVEY_KEY_UPLOADED + " TEXT, "
                + Constants.SURVEY_KEY_USER + " TEXT, "
                + Constants.SURVEY_KEY_RESPONDENT_NAME + " TEXT, "
                + Constants.SURVEY_KEY_RESPONDENT_TEL_NUMBER + " TEXT, "
                + Constants.SURVEY_KEY_RESPONDENT_EMAIL + " TEXT )";

        String CREATE_VEHICLE_TABLE = "CREATE TABLE IF NOT EXISTS  " + Constants.TABLE_VEHICLE + "("
                + Constants.VEHICLE_KEY_ID + " INTEGER, "
                + Constants.VEHICLE_KEY_STATION_NAME + " TEXT, "
                + Constants.VEHICLE_KEY_VEHICLE_NUMBER + " TEXT)";

        String CREATE_QUESTION_TABLE = "CREATE TABLE IF NOT EXISTS  " + Constants.TABLE_QUESTION + "("
                + Constants.QUESTION_KEY_ID + " INTEGER, "
                + Constants.QUESTION_KEY_QUESTION + " TEXT, "
                + Constants.QUESTION_KEY_TYPE + " TEXT, "
                + Constants.QUESTION_KEY_BRAND_NAME + " TEXT)";

        String CREATE_ACCESS_CODE_TABLE = "CREATE TABLE IF NOT EXISTS  " + Constants.TABLE_ACCESS_CODE + "("
                + Constants.ACCESS_CODE_KEY_ID + " INTEGER, "
                + Constants.ACCESS_CODE_KEY_USERNAME + " TEXT, "
                + Constants.ACCESS_CODE_KEY_CODE + " TEXT )";

        String CREATE_ANSWER_TABLE = "CREATE TABLE IF NOT EXISTS  " + Constants.TABLE_ANSWER + "("
                + Constants.ANSWER_KEY_ID + " INTEGER, "
                + Constants.ANSWER_KEY_ANSWER + " TEXT, "
                + Constants.ANSWER_KEY_QUESTION_ID + " TEXT )";

        db.execSQL(CREATE_BRAND_TABLE);
        db.execSQL(CREATE_REPORT_TABLE);
        db.execSQL(CREATE_STATION_TABLE);
        db.execSQL(CREATE_SURVEY_TABLE);
        db.execSQL(CREATE_VEHICLE_TABLE);
        db.execSQL(CREATE_QUESTION_TABLE);
        db.execSQL(CREATE_ACCESS_CODE_TABLE);
        db.execSQL(CREATE_ANSWER_TABLE);
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
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_QUESTION);

        // Create tables again
        onCreate(db);
    }
}
