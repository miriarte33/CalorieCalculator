package manriqueiriarte.caloriecalculator;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "fitness.db";
    public static final String TABLE_NAME = "fitness";
    public static final String COL1 = "NAME";
    public static final String COL2 = "WEIGHT";
    public static final String COL3 = "DAILY_CALORIC_INTAKE";
    public static final String COL4 = "BMI";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(NAME TEXT, " +
                " WEIGHT INT, DAILY_CALORIC_INTAKE INT, BMI REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void deleteProduct(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COL1 + "='" + name + "';");
    }

    public boolean addData(String name, int weight, int dailyCaloricIntake, double bmi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, weight);
        contentValues.put(COL3, dailyCaloricIntake);
        contentValues.put(COL4, bmi);

        long result = db.insert(TABLE_NAME, null, contentValues);
        Log.d("MI", result + "");
        //if data as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //query for outputting the whole table
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public Cursor getHealthyBmiContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME + " a WHERE a.BMI >= 18.5 AND a.BMI <= 24.9", null);
        return data;
    }
}