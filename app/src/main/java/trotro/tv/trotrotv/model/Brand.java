package trotro.tv.trotrotv.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.db.DatabaseHandler;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class Brand {

    DatabaseHandler mDbHandler;


    private String id;
    private String name;
    private String location;
    private String contactPerson;
    private String contactNumber;
    private String email;

    public Brand(Context context) {
        mDbHandler = new DatabaseHandler(context);
    }

    public void saveBrand(Brand brand) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.BRAND_KEY_NAME, brand.getName());
        values.put(Constants.BRAND_KEY_LOCATION, brand.getLocation());
        values.put(Constants.BRAND_KEY_CONTACT_PERSON, brand.getContactPerson());
        values.put(Constants.BRAND_KEY_CONTACT_NUMBER, brand.getContactNumber());
        values.put(Constants.BRAND_KEY_EMAIL, brand.getEmail());

        // Inserting Row
        db.insert(Constants.TABLE_BRAND, null, values);
        db.close(); // Closing database connection
    }

    public void editBrand(int id) {
    }

    public void deleteBrand(int id) {
    }

    public void getBrand(int id) {
    }

    public void getAllBrands() {
    }


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

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
