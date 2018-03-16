package trotro.tv.trotrotv.model;

import android.content.Context;

import trotro.tv.trotrotv.db.DatabaseHandler;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class Vehicle {


    private String id;
    private String vehicleNumber;
    private String stationName;

    private DatabaseHandler mDbHandler;

    public Vehicle(Context context){
        mDbHandler = new DatabaseHandler(context);
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

    public void saveVehicle() {
    }

    public void editVehicle(int id) {
    }

    public void deleteVehicle(int id) {
    }

    public void getVehicle(int id) {
    }

    public void getAllVehicles() {
    }
}
