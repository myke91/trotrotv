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
 * Created by michael.dugah on 3/16/2018.
 */

public class Question {

    private String id;
    private String question;
    private String type;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updateAt;

    private DatabaseHandler mDbHandler;

    public Question() {
    }

    public Question(Context context) {
        mDbHandler = new DatabaseHandler(context);
    }


    public void saveQuestion(Question question) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.QUESTION_KEY_QUESTION, question.getQuestion());
        values.put(Constants.QUESTION_KEY_TYPE, question.getType());

        // Inserting Row
        db.insert(Constants.TABLE_QUESTION, null, values);
        db.close(); // Closing database connection
    }

    public void editQuestion(int id) {
    }

    public void deleteQuestion(Question question) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        db.delete(Constants.TABLE_QUESTION, Constants.QUESTION_KEY_ID + " = ?",
                new String[]{String.valueOf(question.getId())});
        db.close();
    }

    public void getQuestion(int id) {
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_QUESTION;

        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(cursor.getString(cursor.getColumnIndex(Constants.QUESTION_KEY_ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(Constants.QUESTION_KEY_QUESTION)));
                question.setType(cursor.getString(cursor.getColumnIndex(Constants.QUESTION_KEY_TYPE)));

                questions.add(question);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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