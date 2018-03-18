package trotro.tv.trotrotv.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

import trotro.tv.trotrotv.constants.Constants;
import trotro.tv.trotrotv.db.DatabaseHandler;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class Brand {

    DatabaseHandler mDbHandler;


    private String id;
    @JsonProperty("brand_name")
    private String brandName;
    private String location;
    @JsonProperty("contact_person")
    private String contactPerson;
    @JsonProperty("contact_number")
    private String contactNumber;
    private String email;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updateAt;

    public Brand() {
    }

    public Brand(Context context) {
        mDbHandler = new DatabaseHandler(context);
    }

    public void saveBrand(Brand brand) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.BRAND_KEY_NAME, brand.getBrandName());
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

    public void deleteBrand(Brand brand) {
        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        db.delete(Constants.TABLE_BRAND, Constants.BRAND_KEY_ID + " = ?",
                new String[]{String.valueOf(brand.getId())});
        db.close();
    }

    public void getBrand(int id) {
    }

    public List<Brand> getAllBrands() {
        List<Brand> brands = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_BRAND;

        SQLiteDatabase db = mDbHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Brand brand = new Brand();
                brand.setId(cursor.getString(cursor.getColumnIndex(Constants.BRAND_KEY_ID)));
                brand.setBrandName(cursor.getString(cursor.getColumnIndex(Constants.BRAND_KEY_NAME)));
                brand.setLocation(cursor.getString(cursor.getColumnIndex(Constants.BRAND_KEY_LOCATION)));
                brand.setContactPerson(cursor.getString(cursor.getColumnIndex(Constants.BRAND_KEY_CONTACT_PERSON)));
                brand.setContactNumber(cursor.getString(cursor.getColumnIndex(Constants.BRAND_KEY_CONTACT_NUMBER)));

                brands.add(brand);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return brands;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
