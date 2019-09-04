package id.io.barcodescanner.main.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import id.io.barcodescanner.main.response.UserDetails;

/**
 * Dibuat oleh petersam
 */
public class SqliteDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "repository";
    private static final String TABLE_NAME = "user_details";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_ALIAS = "alias";
    private static final String COLUMN_ROLE = "role";
    private static final String COLUMN_MEMBERCODE = "memberCode";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_LEVEL = "level";
    private static final String COLUMN_DEPARTMENT = "department";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_ALIAS + " TEXT, "
                + COLUMN_ROLE + " TEXT, "
                + COLUMN_MEMBERCODE + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_IMAGE + " TEXT, "
                + COLUMN_LEVEL + " TEXT, "
                + COLUMN_DEPARTMENT + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertData(UserDetails userDetails){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "INSERT INTO " + TABLE_NAME
                + " (username, alias, role, memberCode, email, image, level, department)"
                + " VALUES ("
                + "'" + userDetails.getUsername() + "', "
                + "'" + userDetails.getAlias() + "', "
                + "'" + userDetails.getRole() + "', "
                + "'" + userDetails.getMemberCode() + "', "
                + "'" + userDetails.getEmail() + "', "
                + "'" + userDetails.getImage() + "', "
                + "'" + userDetails.getLevel() + "', "
                + "'" + userDetails.getDepartment() + "'"
                + ")";
        Log.e("insert sqlite ", "" + query);
        database.execSQL(query);
        database.close();
    }

    public void deleteData(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_NAME);
        database.close();
    }
}
