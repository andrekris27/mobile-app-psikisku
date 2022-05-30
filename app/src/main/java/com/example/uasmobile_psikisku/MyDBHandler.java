package com.example.uasmobile_psikisku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "datatransaksi.db";
    private static final String TABLE_NAME = "barang";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAMABARANG = "namabarang";
    private static final String COLUMN_HARGABARANG = "hargabarang";
    private static final String COLUMN_QTYOBAT = "qtyobat";

    //Constructor untuk Class MyDBHandler
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BARANG = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAMABARANG + " VARCHAR(50) NOT NULL, " +
                COLUMN_QTYOBAT + " VARCHAR(50) NOT NULL, " +
                COLUMN_HARGABARANG + " LONG NOT NULL)";
        db.execSQL(CREATE_TABLE_BARANG);
    }
    //Method yang dipakai untuk upgrade tabel
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    private SQLiteDatabase database;
    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }

    //Inisialisasi semua kolom di tabel database
    private String[] allColumns =
            {COLUMN_ID, COLUMN_NAMABARANG, COLUMN_HARGABARANG, COLUMN_QTYOBAT};

    public void createBarang(String nama, String qty, long harga) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMABARANG, nama);
        values.put(COLUMN_HARGABARANG, harga);
        values.put(COLUMN_QTYOBAT, qty);
        database.insert(TABLE_NAME, null, values);
    }

    private Obat cursorToObat(Cursor cursor) {
        Obat obat = new Obat();
        obat.setID(cursor.getLong(0));
        obat.setNamaBarang(cursor.getString(1));
        obat.setHargaBarang(cursor.getLong(2));
        obat.setQTYBarang(cursor.getString(3));
        return obat;
    }

    public ArrayList<Obat> getAllBarang() {
        ArrayList<Obat> daftarBarang = new ArrayList<Obat>();
        Cursor cursor = database.query(TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Obat obat = cursorToObat(cursor);
            daftarBarang.add(obat);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarBarang;
    }

    public boolean deleteBarang(long id) {
        try
        {
            int result=database.delete(TABLE_NAME, SyncStateContract.Constants._ID+" =?",new String[]{String.valueOf(id)});
            if(result>0)
            {
                return true;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }
}
