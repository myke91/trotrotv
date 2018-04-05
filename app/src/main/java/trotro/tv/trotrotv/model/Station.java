package trotro.tv.trotrotv.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.db.DatabaseHandler;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class Station extends JSONObject {

    private String id;
    @JsonProperty("station_name")
    private String stationName;
    private String location;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updateAt;

    private DatabaseHandler mDbHandler;

    public Station(Context context) {
        mDbHandler = new DatabaseHandler(context);
    }


    public Station() {
    }


    public void saveStation(Station station) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try {

            ContentValues values = new ContentValues();
            values.put(Constants.STATION_KEY_ID, station.getId());
            values.put(Constants.STATION_KEY_NAME, station.getStationName());
            values.put(Constants.STATION_KEY_LOCATION, station.getLocation());

            // Inserting Row
            db.insert(Constants.TABLE_STATION, null, values);

        } catch (Exception ex) {

        } finally {

            db.close(); // Closing database connection
        }
    }

    public void editStation(int id) {
    }

    public void deleteStation(Station station) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try {

            db.delete(Constants.TABLE_STATION, Constants.STATION_KEY_ID + " = ?",
                    new String[]{String.valueOf(station.getId())});

        } catch (Exception ex) {

        } finally {

            db.close();
        }
    }

    public void clearData() {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try {

            db.execSQL("DELETE FROM " + Constants.TABLE_STATION);

        } catch (Exception ex) {

        } finally {

            db.close();
        }
    }

    public void getStation(int id) {
    }

    public List<Station> getAllStations() {
        List<Station> stations = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_STATION;

        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Station station = new Station();
                    station.setId(cursor.getString(cursor.getColumnIndex(Constants.STATION_KEY_ID)));
                    station.setStationName(cursor.getString(cursor.getColumnIndex(Constants.STATION_KEY_NAME)));
                    station.setLocation(cursor.getString(cursor.getColumnIndex(Constants.STATION_KEY_LOCATION)));

                    stations.add(station);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {

        } finally {

            // close db connection
            db.close();
        }

        // return notes list
        return stations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
