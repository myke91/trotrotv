package trotro.tv.trotrotv.constants;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class Constants {
    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "trotrotv.db";

    // Brand table name
    public static final String TABLE_BRAND = "brand";
    // Report table name
    public static final String TABLE_REPORT = "report";
    // Station table name
    public static final String TABLE_STATION = "station";
    // Survey table name
    public static final String TABLE_SURVEY = "survey";
    // Vehicle table name
    public static final String TABLE_VEHICLE = "vehicle";


    public static final String BRAND_KEY_ID = "id";
    public static final String BRAND_KEY_NAME = "name";
    public static final String BRAND_KEY_LOCATION = "location";
    public static final String BRAND_KEY_CONTACT_PERSON = "contact_person";
    public static final String BRAND_KEY_CONTACT_NUMBER = "contact_number";
    public static final String BRAND_KEY_EMAIL = "email";

    public static final String REPORT_KEY_ID = "id";
    public static final String REPORT_KEY_VEHICLE_NUMBER = "name";
    public static final String REPORT_KEY_QUESTION = "question";
    public static final String REPORT_KEY_ANSWER = "answer";
    public static final String REPORT_KEY_UPLOADED = "uploaded";
    public static final String REPORT_KEY_TIMESTAMP = "timestamp";


    public static final String STATION_KEY_ID = "id";
    public static final String STATION_KEY_NAME = "name";
    public static final String STATION_KEY_LOCATION = "location";

    public static final String SURVEY_KEY_ID = "id";
    public static final String SURVEY_KEY_BRAND_NAME = "brand_name";
    public static final String SURVEY_KEY_QUESTION = "question";
    public static final String SURVEY_KEY_ANSWER = "answer";
    public static final String SURVEY_KEY_UPLOADED = "uploaded";
    public static final String SURVEY_KEY_TIMESTAMP = "timestamp";

    public static final String VEHICLE_KEY_ID = "id";
    public static final String VEHICLE_KEY_VEHICLE_NUMBER = "vehicle_number";
    public static final String VEHICLE_KEY_STATION_NAME = "station_name";
}