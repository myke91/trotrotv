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

public class Station {

    private String id;
    private String name;
    private String location;

    private DatabaseHandler mDbHandler;

    public Station(Context context){
        mDbHandler = new DatabaseHandler(context);
    }



    public void saveStation(Station station){
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.STATION_KEY_NAME, station.getName());
        values.put(Constants.STATION_KEY_LOCATION, station.getLocation());

        // Inserting Row
        db.insert(Constants.TABLE_STATION, null, values);
        db.close(); // Closing database connection
    }
    public void editStation(int id){}
    public void deleteStation(int id){}
    public void getStation(int id){}
    public void getAllStations(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
