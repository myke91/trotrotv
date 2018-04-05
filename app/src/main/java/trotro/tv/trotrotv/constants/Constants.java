package trotro.tv.trotrotv.constants;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class Constants {
    public static final String TAG = "trotro.tv.trotrotv";

//    public static final String BACKEND_BASE_URL = "http://10.0.2.2:8000";
    public static final String BACKEND_BASE_URL = "http://trotrotv.herokuapp.com";

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
    // Question table name
    public static final String TABLE_QUESTION = "question";
    // Question table name
    public static final String TABLE_ANSWER = "answer";
    // Question table name
    public static final String TABLE_ACCESS_CODE = "access_code";


    public static final String BRAND_KEY_ID = "id";
    public static final String BRAND_KEY_NAME = "brand_name";
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
    public static final String REPORT_KEY_USER = "user";
    public static final String REPORT_KEY_COMMENTS = "comments";

    public static final String STATION_KEY_ID = "id";
    public static final String STATION_KEY_NAME = "name";
    public static final String STATION_KEY_LOCATION = "location";

    public static final String SURVEY_KEY_ID = "id";
    public static final String SURVEY_KEY_BRAND_NAME = "brand";
    public static final String SURVEY_KEY_QUESTION = "question";
    public static final String SURVEY_KEY_ANSWER = "answer";
    public static final String SURVEY_KEY_UPLOADED = "uploaded";
    public static final String SURVEY_KEY_TIMESTAMP = "timestamp";
    public static final String SURVEY_KEY_USER = "user";
    public static final String SURVEY_KEY_RESPONDENT_NAME = "respondent_name";
    public static final String SURVEY_KEY_RESPONDENT_TEL_NUMBER = "respondent_tel_number";
    public static final String SURVEY_KEY_RESPONDENT_EMAIL = "respondent_email";

    public static final String VEHICLE_KEY_ID = "id";
    public static final String VEHICLE_KEY_VEHICLE_NUMBER = "vehicle";
    public static final String VEHICLE_KEY_STATION_NAME = "station";

    public static final String QUESTION_KEY_ID = "id";
    public static final String QUESTION_KEY_QUESTION = "question";
    public static final String QUESTION_KEY_TYPE = "type";
    public static final String QUESTION_KEY_BRAND_NAME = "brand_name";

    public static final String ANSWER_KEY_ID = "id";
    public static final String ANSWER_KEY_ANSWER = "answer";
    public static final String ANSWER_KEY_QUESTION_ID = "question_id";

    public static final String ACCESS_CODE_KEY_ID = "id";
    public static final String ACCESS_CODE_KEY_USERNAME = "username";
    public static final String ACCESS_CODE_KEY_CODE = "code";
}
