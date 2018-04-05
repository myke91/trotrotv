package trotro.tv.trotrotv.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.db.DatabaseHandler;

/**
 * Created by michael.dugah on 4/2/2018.
 */

public class AccessCode {

    DatabaseHandler mDbHandler;

    private String id;
    private String username;
    private String code;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updateAt;

    public AccessCode() {
    }

    public AccessCode(String username, String code) {
        this.username = username;
        this.code = code;
    }

    public AccessCode(Context context) {
        mDbHandler = new DatabaseHandler(context);
    }


    public void saveAccessCode(AccessCode accessCode) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try {

            ContentValues values = new ContentValues();
            values.put(Constants.ACCESS_CODE_KEY_ID, accessCode.getId());
            values.put(Constants.ACCESS_CODE_KEY_USERNAME, accessCode.getUsername());
            values.put(Constants.ACCESS_CODE_KEY_CODE, accessCode.getCode());

            // Inserting Row
            db.insert(Constants.TABLE_ACCESS_CODE, null, values);

        } catch (Exception ex) {

        } finally {

            db.close(); // Closing database connection
        }
    }

    public boolean doValidate(String username, String code) {
        List<AccessCode> accessCodes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_ACCESS_CODE + " WHERE " + Constants.ACCESS_CODE_KEY_USERNAME + " = '" + username + "' AND " + Constants.ACCESS_CODE_KEY_CODE + " = '" + code + "'";

        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try {


            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    AccessCode accessCode = new AccessCode();
                    accessCode.setId(cursor.getString(cursor.getColumnIndex(Constants.ACCESS_CODE_KEY_ID)));
                    accessCode.setUsername(cursor.getString(cursor.getColumnIndex(Constants.ACCESS_CODE_KEY_USERNAME)));
                    accessCode.setCode(cursor.getString(cursor.getColumnIndex(Constants.ACCESS_CODE_KEY_CODE)));

                    accessCodes.add(accessCode);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {

        } finally {
            // close db connection
            db.close();
        }
        ;

        // return notes list
        return !accessCodes.isEmpty();

    }

    public void editAccessCode(int id) {
    }

    public void deleteAccessCode(AccessCode accessCode) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try {
            db.delete(Constants.TABLE_ACCESS_CODE, Constants.ACCESS_CODE_KEY_ID + " = ?",
                    new String[]{String.valueOf(accessCode.getId())});
        } catch (Exception ex) {

        } finally {

            db.close();
        }

    }

    public void getAccessCode(int id) {
    }

    public void clearData() {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + Constants.TABLE_ACCESS_CODE);
        } catch (Exception ex) {

        } finally {

            db.close();
        }

    }

    public List<AccessCode> getAllAccessCodes() {
        List<AccessCode> accessCodes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_ACCESS_CODE;

        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    AccessCode accessCode = new AccessCode();
                    accessCode.setId(cursor.getString(cursor.getColumnIndex(Constants.ACCESS_CODE_KEY_ID)));
                    accessCode.setUsername(cursor.getString(cursor.getColumnIndex(Constants.ACCESS_CODE_KEY_USERNAME)));
                    accessCode.setCode(cursor.getString(cursor.getColumnIndex(Constants.ACCESS_CODE_KEY_CODE)));

                    accessCodes.add(accessCode);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            Log.e("TrotroTV", ex.getMessage());
        } finally {
            // close db connection
            db.close();
        }


        // return notes list
        return accessCodes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
