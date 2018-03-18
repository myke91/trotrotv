package trotro.tv.trotrotv.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.db.DatabaseHandler;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class Report {

    private String id;
    private String vehicle;
    private String question;
    private String answer;
    private String uploaded;
    private String timestamp;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updateAt;

    private DatabaseHandler mDbHandler;

    public Report(Context context) {
        mDbHandler = new DatabaseHandler(context);
    }

    public Report() {
    }

    public void saveReport(Report report) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.REPORT_KEY_VEHICLE_NUMBER, report.getVehicle());
        values.put(Constants.REPORT_KEY_QUESTION, report.getQuestion());
        values.put(Constants.REPORT_KEY_ANSWER, report.getAnswer());
        values.put(Constants.REPORT_KEY_TIMESTAMP, new Date().getTime());

        // Inserting Row
        db.insert(Constants.TABLE_REPORT, null, values);
        db.close(); // Closing database connection
    }

    public void editReport(Report report) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.REPORT_KEY_VEHICLE_NUMBER, report.getVehicle());
        values.put(Constants.REPORT_KEY_QUESTION, report.getQuestion());
        values.put(Constants.REPORT_KEY_ANSWER, report.getAnswer());
        values.put(Constants.REPORT_KEY_UPLOADED, report.getUploaded());
        values.put(Constants.REPORT_KEY_TIMESTAMP, report.getTimestamp());

        // updating row
        db.update(Constants.TABLE_REPORT, values, Constants.REPORT_KEY_ID + " = ?",
                new String[]{String.valueOf(report.getId())});
    }

    public void deleteReport(Report report) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        db.delete(Constants.TABLE_REPORT, Constants.REPORT_KEY_ID + " = ?",
                new String[]{String.valueOf(report.getId())});
        db.close();
    }

    public void clearReportData() {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        db.delete(Constants.TABLE_REPORT, Constants.REPORT_KEY_UPLOADED + " = ?",
                new String[]{String.valueOf("true")});
        db.close();
    }

    public void getReport(int id) {
    }

    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_REPORT;

        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Report report = new Report();
                report.setId(cursor.getString(cursor.getColumnIndex(Constants.REPORT_KEY_ID)));
                report.setVehicle(cursor.getString(cursor.getColumnIndex(Constants.REPORT_KEY_VEHICLE_NUMBER)));
                report.setQuestion(cursor.getString(cursor.getColumnIndex(Constants.REPORT_KEY_QUESTION)));
                report.setAnswer(cursor.getString(cursor.getColumnIndex(Constants.REPORT_KEY_ANSWER)));
                report.setTimestamp(cursor.getString(cursor.getColumnIndex(Constants.REPORT_KEY_TIMESTAMP)));
                report.setUploaded(cursor.getString(cursor.getColumnIndex(Constants.REPORT_KEY_UPLOADED)));

                reports.add(report);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return reports;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
