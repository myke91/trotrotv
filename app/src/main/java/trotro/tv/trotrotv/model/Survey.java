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

public class Survey extends JSONObject {

    private String id;
    private String brand;
    private String question;
    private String answer;
    private String uploaded;
    private String timestamp;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updateAt;

    private DatabaseHandler mDbHandler;

    public Survey() {
    }

    public Survey(Context context) {
        mDbHandler = new DatabaseHandler(context);
    }

    public Survey(String json) throws JSONException {
        super(json);
    }

    public static Survey load(Survey survey) {
        Map map = new LinkedHashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        map.put("id", survey.getId());
        map.put("brand", survey.getBrand());
        map.put("question", survey.getQuestion());
        map.put("answer", survey.getAnswer());
        map.put("uploaded", survey.getUploaded());
        map.put("timestamp", survey.getTimestamp());

        try {
            String json = mapper.writeValueAsString(map);
            return new Survey(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public void saveSurvey(Survey survey) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.SURVEY_KEY_BRAND_NAME, survey.getBrand());
        values.put(Constants.SURVEY_KEY_QUESTION, survey.getQuestion());
        values.put(Constants.SURVEY_KEY_TIMESTAMP, new Date().toGMTString());
        values.put(Constants.SURVEY_KEY_ANSWER, survey.getAnswer());

        // Inserting Row
        db.insert(Constants.TABLE_SURVEY, null, values);
        db.close(); // Closing database connection
    }

    public void editSurvey(Survey survey) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.SURVEY_KEY_BRAND_NAME, survey.getBrand());
        values.put(Constants.SURVEY_KEY_QUESTION, survey.getQuestion());
        values.put(Constants.SURVEY_KEY_ANSWER, survey.getAnswer());
        values.put(Constants.SURVEY_KEY_TIMESTAMP, survey.getTimestamp());
        values.put(Constants.SURVEY_KEY_UPLOADED, survey.getUploaded());

        // updating row
        db.update(Constants.TABLE_SURVEY, values, Constants.SURVEY_KEY_ID + " = ?",
                new String[]{String.valueOf(survey.getId())});
    }

    public void deleteSurvey(Survey survey) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        db.delete(Constants.TABLE_SURVEY, Constants.SURVEY_KEY_ID + " = ?",
                new String[]{String.valueOf(survey.getId())});
        db.close();
    }

    public void clearSurveyData() {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        db.delete(Constants.TABLE_SURVEY, Constants.SURVEY_KEY_UPLOADED + " = ?",
                new String[]{String.valueOf("true")});
        db.close();
    }

    public void getSurvey(int id) {
    }

    public List<Survey> getAllSurveys() {
        List<Survey> surveys = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_SURVEY;

        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Survey survey = new Survey();
                survey.setId(cursor.getString(cursor.getColumnIndex(Constants.SURVEY_KEY_ID)));
                survey.setBrand(cursor.getString(cursor.getColumnIndex(Constants.SURVEY_KEY_BRAND_NAME)));
                survey.setQuestion(cursor.getString(cursor.getColumnIndex(Constants.SURVEY_KEY_QUESTION)));
                survey.setAnswer(cursor.getString(cursor.getColumnIndex(Constants.SURVEY_KEY_ANSWER)));
                survey.setTimestamp(cursor.getString(cursor.getColumnIndex(Constants.SURVEY_KEY_TIMESTAMP)));
                survey.setUploaded(cursor.getString(cursor.getColumnIndex(Constants.SURVEY_KEY_UPLOADED)));

                surveys.add(survey);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return surveys;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
