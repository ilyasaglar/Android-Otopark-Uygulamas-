package com.example.ilyas.elmar_yazilim_otopark;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ilyas on 6.06.2018.
 */

public class sqliteHelper extends SQLiteOpenHelper {

    private static final int database_Version=1;
    private static final String database_Name="araclarDB";
    private static final String table_Araclar="araclar";

    private static final String arac_plaka="plaka";
    private static final String arac_giris_Saati="giris_saati";
    private static final String arac_Giris_tarihi="giris_tarihi";
    private static final String CREATE_ARAC_TABLE="CREATE TABLE "
            + table_Araclar + " ("
            + arac_plaka +	" INTEGER PRIMARY KEY AUTOINCREMENT, "
	        + arac_giris_Saati + " INTEGER, "
            + arac_Giris_tarihi +	" INTEGER )";



    public sqliteHelper(Context context) {
        super(context, database_Name, null,database_Version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {        //tabloyu oluşturma

        db.execSQL(CREATE_ARAC_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {     //tablo varsa sil yeniden oluşturma

        db.execSQL(" DROP TABLE IF EXISTS "+ table_Araclar);
        this.onCreate(db);


    }

    public void aracEkle(){


    }
}
