package trotro.tv.trotrotv.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.db.DatabaseHandler;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class Report {

    private String id;
    private String vehicleNumber;
    private String question;
    private String answer;
    private String uploaded;
    private String timestamp;

    private DatabaseHandler mDbHandler;

    public Report(Context context){
        mDbHandler = new DatabaseHandler(context);
    }


    public void saveReport(Report report){
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.REPORT_KEY_VEHICLE_NUMBER, report.getVehicleNumber());
        values.put(Constants.REPORT_KEY_QUESTION, report.getQuestion());
        values.put(Constants.REPORT_KEY_ANSWER, report.getAnswer());
        values.put(Constants.REPORT_KEY_TIMESTAMP, new Date().getTime());

        // Inserting Row
        db.insert(Constants.TABLE_REPORT, null, values);
        db.close(); // Closing database connection
    }
    public void editReport(int id){}
    public void deleteReport(int id){}
    public void getReport(int id){}
    public void getAllReports(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUploaded() {
        return uploaded;
    }

    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
