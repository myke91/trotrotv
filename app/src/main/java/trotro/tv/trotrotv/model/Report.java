package trotro.tv.trotrotv.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.db.DatabaseHandler;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class Report extends JSONObject {

    private String id;
    private String vehicle;
    private String question;
    private String answer;
    private String uploaded;
    private String timestamp;
    private String user;
    private String comments;
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

    public Report(String json) throws JSONException {
        super(json);
    }

    public static Report load(Report report) {
        Map map = new LinkedHashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        map.put("id", report.getId());
        map.put("vehicle", report.getVehicle());
        map.put("question", report.getQuestion());
        map.put("answer", report.getAnswer());
        map.put("uploaded", report.getUploaded());
        map.put("timestamp", report.getTimestamp());
        map.put("user", report.getUser());
        map.put("comments", report.getComments());

        try {
            String json = mapper.writeValueAsString(map);
            return new Report(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public void saveReport(Report report) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try {

            ContentValues values = new ContentValues();
            values.put(Constants.REPORT_KEY_VEHICLE_NUMBER, report.getVehicle());
            values.put(Constants.REPORT_KEY_QUESTION, report.getQuestion());
            values.put(Constants.REPORT_KEY_ANSWER, report.getAnswer());
            values.put(Constants.REPORT_KEY_TIMESTAMP, new Date().toGMTString());
            values.put(Constants.REPORT_KEY_USER, report.getUser());
            values.put(Constants.REPORT_KEY_COMMENTS, report.getComments());

            // Inserting Row
            db.insert(Constants.TABLE_REPORT, null, values);

        } catch (Exception ex) {


        } finally {
            db.close(); // Closing database connection
        }
    }

    public void editReport(Report report) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try {

            ContentValues values = new ContentValues();
            values.put(Constants.REPORT_KEY_VEHICLE_NUMBER, report.getVehicle());
            values.put(Constants.REPORT_KEY_QUESTION, report.getQuestion());
            values.put(Constants.REPORT_KEY_ANSWER, report.getAnswer());
            values.put(Constants.REPORT_KEY_UPLOADED, report.getUploaded());
            values.put(Constants.REPORT_KEY_TIMESTAMP, report.getTimestamp());
            values.put(Constants.REPORT_KEY_USER, report.getUser());
            values.put(Constants.REPORT_KEY_COMMENTS, report.getComments());

            // updating row
            db.update(Constants.TABLE_REPORT, values, Constants.REPORT_KEY_ID + " = ?",
                    new String[]{String.valueOf(report.getId())});

        } catch (Exception ex) {

        } finally {
            db.close();
        }
    }

    public void deleteReport(Report report) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try{

        db.delete(Constants.TABLE_REPORT, Constants.REPORT_KEY_ID + " = ?",
                new String[]{String.valueOf(report.getId())});

        }catch (Exception ex){

        }finally {

            db.close();
        }
    }

    public void clearReportData() {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try{

        db.delete(Constants.TABLE_REPORT, Constants.REPORT_KEY_UPLOADED + " = ?",
                new String[]{String.valueOf("true")});

        }catch (Exception ex){

        }finally {

            db.close();
        }
    }

    public void getReport(int id) {
    }

    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_REPORT;

        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try{

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
                report.setUser(cursor.getString(cursor.getColumnIndex(Constants.REPORT_KEY_USER)));
                report.setComments(cursor.getString(cursor.getColumnIndex(Constants.REPORT_KEY_COMMENTS)));

                reports.add(report);
            } while (cursor.moveToNext());
        }

        }catch (Exception ex){

        }finally {

            // close db connection
            db.close();
        }

        // return notes list
        return reports;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("id:").append(getId());
        sb.append("vehicle:").append(getVehicle());
        sb.append("question:").append(getQuestion());
        sb.append("answer:").append(getAnswer());
        sb.append("timestamp:").append(getTimestamp());
        sb.append("uploaded:").append(getUploaded());
        sb.append("user:").append(getUser());
        sb.append("comments:").append(getComments());
        sb.append("}");

        return sb.toString();
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
