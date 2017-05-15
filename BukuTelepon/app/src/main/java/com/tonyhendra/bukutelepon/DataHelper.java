package com.tonyhendra.bukutelepon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tony on 5/15/17.
 */

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_bukutelepon.db";
    private static final int DATABSE_VERSION = 1;
    public DataHelper(Context context){
        super(context,DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table bukutelepon(ROWID INTEGER PRIMARY KEY AUTOINCREMENT, nama text null, telepon text null, jk text null, alamat text null)";
        Log.d("Data", "onCreate: "+sql);
        db.execSQL(sql);
        sql = "Insert INTO bukutelepon(nama, telepon, jk, alamat) VALUES ('Tony Hendra', '085728576040', 'Laki-Laki', 'Jatisumo, Sragen')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }
}
