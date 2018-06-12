package com.example.ilyas.elmar_yazilim_otopark;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ilyas on 6.06.2018.
 */

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="plakalar_vt21";
    public static final int DATABASE_VERSION=1;
    public static final String PLAKA_TABLE="plakalar21";

    public static final String ROW_ID="id";
    public static final String plaka="plaka";
    public static final String giris_saati="saat";
    public static final String giris_tarihi="tarih";
    public static final String yikama_ucret="yikama";
    public static final String denme_ucret="denme";
    public static final String toplam_tutar="ucret";


    public Database(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {                                 //tabloyu oluşturma

        db.execSQL("CREATE TABLE " + PLAKA_TABLE +"("+ROW_ID+" INTEGER PRIMARY KEY, " + plaka+" TEXT NOT NULL, "+giris_saati+" TEXT NOT NULL, "+giris_tarihi+" TEXT NOT NULL, "+toplam_tutar+" TEXT NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ PLAKA_TABLE);                      //tablo varsa sil yeniden oluşturma
        onCreate(db);
    }

    public void veriEkle(String ingilizce,String turkce,String tarih,String tutar){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(plaka,ingilizce.trim());
        cv.put(giris_saati,turkce.trim());
        cv.put(giris_tarihi,tarih.trim());
        cv.put(toplam_tutar,tutar.trim());



        db.insert(PLAKA_TABLE, null,cv);
        db.close();
    }

    public List<String> veriListele(){
        List<String> veriler=new ArrayList<String>();
        SQLiteDatabase db=this.getWritableDatabase();
        String[] sutunlar={ROW_ID, plaka,giris_saati,giris_tarihi,toplam_tutar};
        Cursor cursor=db.query(PLAKA_TABLE,sutunlar,null,null,null,null,null);
        while (cursor.moveToNext()){
            veriler.add(cursor.getInt(0)+ "  -  " + cursor.getString(1)+"  -  "+cursor.getString(2)+ "  -  "+ cursor.getString(3)+ "  -  "+ cursor.getString(4));
        }
        return veriler;
    }


    public List<Bilgiler> Arama(String plaka) {
        List<Bilgiler> contactList = new ArrayList<Bilgiler>();
        // Select All Query ：SELECT * FROM tableName WHERE criteria
        String selectQuery = "SELECT * FROM "+ PLAKA_TABLE +" WHERE plaka=plaka";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bilgiler contact = new Bilgiler();
                contact.setId(cursor.getString(0));
                contact.setPlaka(cursor.getString(1));
                contact.setGiris_saati(cursor.getString(2));
                contact.setGiris_tarihi(cursor.getString(3));
                contact.setToplam_tutar(cursor.getString(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;
    }




}
