package trotro.tv.trotrotv.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.util.List;

import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.db.DatabaseHandler;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class Survey {

    private String id;
    private String brandName;
    private String question;
    private String answer;
    private String uploaded;
    private String timestamp;

    private DatabaseHandler mDbHandler;

    public Survey(Context context) {
        mDbHandler = new DatabaseHandler(context);
    }

    public void saveSurvey(Survey survey) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.SURVEY_KEY_BRAND_NAME, survey.getBrandName());
        values.put(Constants.SURVEY_KEY_QUESTION, survey.getQuestion());
        values.put(Constants.SURVEY_KEY_TIMESTAMP, new Date().getTime());
        values.put(Constants.SURVEY_KEY_ANSWER, survey.getAnswer());

        // Inserting Row
        db.insert(Constants.TABLE_SURVEY, null, values);
        db.close(); // Closing database connection
    }

    public void saveSurveys(List<Survey> survey) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

//        ContentValues values = new ContentValues();
//        values.put(Constants.SURVEY_KEY_BRAND_NAME, survey.getBrandName());
//        values.put(Constants.SURVEY_KEY_QUESTION, survey.getQuestion());
//        values.put(Constants.SURVEY_KEY_ANSWER, survey.getAnswer());
//        values.put(Constants.SURVEY_KEY_TIMESTAMP, new Date().getTime());
//
//        // Inserting Row
//        db.insert(Constants.TABLE_STATION, null, values);
//        db.close(); // Closing database connection
    }

    public void editSurvey(int id) {
    }

    public void deleteSurvey(int id) {
    }

    public void getSurvey(int id) {
    }

    public void getAllSurveys() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
