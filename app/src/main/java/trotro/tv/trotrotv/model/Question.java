package trotro.tv.trotrotv.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.db.DatabaseHandler;

/**
 * Created by michael.dugah on 3/16/2018.
 */

public class Question {

    private String id;
    private String question;
    private String type;

    private DatabaseHandler mDbHandler;

    public Question(Context context){
        mDbHandler = new DatabaseHandler(context);
    }


    public void saveQuestion(Question question){
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.QUESTION_KEY_QUESTION, question.getQuestion());
        values.put(Constants.QUESTION_KEY_TYPE, question.getType());

        // Inserting Row
        db.insert(Constants.TABLE_QUESTION, null, values);
        db.close(); // Closing database connection
    }
    public void editQuestion(int id){}
    public void deleteQuestion(int id){}
    public void getQuestion(int id){}
    public void getAllQuestions(){}
    
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
}
