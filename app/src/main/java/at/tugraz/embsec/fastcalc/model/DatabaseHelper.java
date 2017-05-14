package at.tugraz.embsec.fastcalc.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

@Deprecated
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_TAG = "DATABASE_HELPER";
    private static final int DB_VERSION = 1;

    private static final String STR_CREATE = "CREATE TABLE sensordata(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, btn INTEGER NOT NULL, " +
            "acc_x FLOAT, acc_y FLOAT, acc_z FLOAT, gyr_x FLOAT, gyr_y FLOAT, gyr_z FLOAT, lgt_a FLOAT, lgt_b FLOAT);";

    public DatabaseHelper(Context context) {
        super(context, "sensordata", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STR_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(DB_TAG, "onUpgrade called ...");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public long storeSensorData(SensorData sdata) {

        long id = -1;
        ContentValues values = sdata.getContentValues();

        SQLiteDatabase db = this.getWritableDatabase();

        id = db.insert("sensordata", null, values);
        db.close();

        return id;
    }

    public int getRowCount() {
        String sql = "SELECT * FROM sensordata;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        int count = c.getCount();
        c.close();
        db.close();

        return count;

    }

    public String getLastEntry() {
        String entry = "[empty]";
        String sql = "SELECT * FROM sensordata WHERE id = (SELECT MAX(id) FROM sensordata);";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        if (c != null) {
            StringBuilder sb = new StringBuilder();
            c.moveToFirst();
            sb.append("id: " + c.getInt(0) + "; ");
            sb.append("btn: " + c.getInt(1) + ";\n");
            sb.append("acc_x: " + c.getFloat(2) + "; ");
            sb.append("acc_y: " + c.getFloat(3) + "; ");
            sb.append("acc_z: " + c.getFloat(4) + ";\n");
            sb.append("gyr_x: " + c.getFloat(5) + "; ");
            sb.append("gyr_y: " + c.getFloat(6) + "; ");
            sb.append("gyr_z: " + c.getFloat(7) + ";\n");
            sb.append("lgt_a: " + c.getFloat(8) + "; ");
            sb.append("lgt_b: " + c.getFloat(9) + ";");
            entry = sb.toString();
        }
        c.close();
        db.close();

        return entry;
    }

    public void resetDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE sensordata;");
        this.onCreate(db);
        db.close();
    }
}
