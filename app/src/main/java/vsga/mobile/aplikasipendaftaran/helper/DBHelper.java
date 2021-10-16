package vsga.mobile.aplikasipendaftaran.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

//Class ini berfungsi sbg pengelola query SQL CRUD pembuatan db
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "kominfotest.db";

    public static final String TABLE_SQLite = "sqlite";

    public static final String COLUMN_ID = "id";
    public static final String  COLUMN_NAMA = "nama";
    public static final String COLUMN_ALAMAT = "alamat";
    public static final String  COLUMN_NOHP = "nohp";
    public static final String COLUMN_JENISKELAMIN = "jeniskelamin";
    public static final String  COLUMN_LOKASI = "lokasi";
    public static final String COLUMN_FOTO = "foto";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //membuat table kolom dan tipe datanya
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_NAMA + " TEXT NOT NULL, " +
                COLUMN_ALAMAT + " TEXT NOT NULL, " +
                COLUMN_NOHP + " INTEGER NOT NULL, " +
                COLUMN_JENISKELAMIN + " TEXT NOT NULL, " +
                COLUMN_LOKASI + " TEXT NOT NULL, " +
                COLUMN_FOTO + " TEXT NOT NULL " +
                ")";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    //mengambil semua data pada table db
    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAMA, cursor.getString(1));
                map.put(COLUMN_ALAMAT, cursor.getString(2));
                map.put(COLUMN_NOHP, cursor.getString(3));
                map.put(COLUMN_JENISKELAMIN, cursor.getString(4));
                map.put(COLUMN_LOKASI, cursor.getString(5));
                map.put(COLUMN_FOTO, cursor.getString(6));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }

    //membuat fungsi simpan
    public void insert(String nama, String alamat, String nohp, String jeniskelamin, String lokasi, String foto){
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " (nama, alamat, nohp, jeniskelamin, lokasi, foto) " + "VALUES ('" + nama + "', '" + alamat + "', '" + nohp + "', '" + jeniskelamin + "', '" + lokasi + "', '" + foto + "')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    //membuat fungsi update
    public void update(int id, String nama, String alamat,  int nohp, String jeniskelamin, String lokasi, String foto){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_SQLite + " SET "
                + COLUMN_NAMA + "='" + nama + "', "
                + COLUMN_ALAMAT + "='" + alamat + "', "
                + COLUMN_NOHP + "='" + nohp + "', "
                + COLUMN_JENISKELAMIN + "='" + jeniskelamin + "', "
                + COLUMN_LOKASI + "='" + lokasi + "', "
                + COLUMN_FOTO + "='" + foto + "'"
                + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    //membuat fungsi delete digunakan untuk data yang telah diinput
    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_SQLite + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
