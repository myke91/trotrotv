package trotro.tv.trotrotv.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.db.DatabaseHandler;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class Vehicle {


    private String id;
    private String vehicle;
    private String station;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updateAt;

    private DatabaseHandler mDbHandler;

    public Vehicle() {
    }

    public Vehicle(Context context) {
        mDbHandler = new DatabaseHandler(context);
    }

    public void saveVehicle(Vehicle vehicle) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.VEHICLE_KEY_STATION_NAME, vehicle.getStation());
        values.put(Constants.VEHICLE_KEY_VEHICLE_NUMBER, vehicle.getVehicle());

        // Inserting Row
        db.insert(Constants.TABLE_VEHICLE, null, values);
        db.close(); // Closing database connection
    }

    public void editVehicle(int id) {
    }

    public void deleteVehicle(Vehicle vehicle) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        db.delete(Constants.TABLE_VEHICLE, Constants.VEHICLE_KEY_ID + " = ?",
                new String[]{String.valueOf(vehicle.getId())});
        db.close();
    }

    public void getVehicle(int id) {
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_VEHICLE;

        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(cursor.getString(cursor.getColumnIndex(Constants.BRAND_KEY_ID)));
                vehicle.setVehicle(cursor.getString(cursor.getColumnIndex(Constants.VEHICLE_KEY_VEHICLE_NUMBER)));
                vehicle.setStation(cursor.getString(cursor.getColumnIndex(Constants.VEHICLE_KEY_STATION_NAME)));

                vehicles.add(vehicle);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return vehicles;
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

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
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
