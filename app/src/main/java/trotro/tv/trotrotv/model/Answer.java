package trotro.tv.trotrotv.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.db.DatabaseHandler;

/**
 * Created by michael.dugah on 4/2/2018.
 */

public class Answer {
    private String id;
    private String answer;
    @JsonProperty("question_id")
    private String questionId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updateAt;

    private DatabaseHandler mDbHandler;

    public Answer() {
    }

    public Answer(Context context) {
        mDbHandler = new DatabaseHandler(context);
    }


    public void saveAnswer(Answer answer) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.ANSWER_KEY_ANSWER, answer.getAnswer());
        values.put(Constants.ANSWER_KEY_QUESTION_ID, answer.getQuestionId());

        // Inserting Row
        db.insert(Constants.TABLE_ANSWER, null, values);
        db.close(); // Closing database connection
    }

    public void editAnswer(int id) {
    }

    public void deleteAnswer(Answer answer) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        db.delete(Constants.TABLE_ANSWER, Constants.ANSWER_KEY_ID + " = ?",
                new String[]{String.valueOf(answer.getId())});
        db.close();
    }

    public void getQuestion(int id) {
    }

    public void clearData() {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        db.execSQL("DELETE FROM " + Constants.TABLE_ANSWER);
        db.close();
    }

    public List<String> getAnswersForQuestion(String questionId) {
        List<String> answers = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_ANSWER + " WHERE question_id = '" + questionId + "'";

        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                answers.add(cursor.getString(cursor.getColumnIndex(Constants.ANSWER_KEY_ANSWER)));
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return answers;
    }


    public List<Answer> getAllAnswers() {
        List<Answer> answers = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_ANSWER;

        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Answer answer = new Answer();
                answer.setId(cursor.getString(cursor.getColumnIndex(Constants.ANSWER_KEY_ID)));
                answer.setAnswer(cursor.getString(cursor.getColumnIndex(Constants.ANSWER_KEY_ANSWER)));
                answer.setQuestionId(cursor.getString(cursor.getColumnIndex(Constants.ANSWER_KEY_QUESTION_ID)));

                answers.add(answer);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return answers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

}
