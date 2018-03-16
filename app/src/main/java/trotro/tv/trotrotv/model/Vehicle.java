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

public class Vehicle {


    private String id;
    private String vehicleNumber;
    private String stationName;

    private DatabaseHandler mDbHandler;

    public Vehicle(Context context) {
        mDbHandler = new DatabaseHandler(context);
    }

    public void saveVehicle(Vehicle vehicle) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.VEHICLE_KEY_STATION_NAME, vehicle.getStationName());
        values.put(Constants.VEHICLE_KEY_VEHICLE_NUMBER, vehicle.getVehicleNumber());

        // Inserting Row
        db.insert(Constants.TABLE_VEHICLE, null, values);
        db.close(); // Closing database connection
    }

    public void editVehicle(int id) {
    }

    public void deleteVehicle(int id) {
    }

    public void getVehicle(int id) {
    }

    public void getAllVehicles() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

}
