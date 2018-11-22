package com.example.ilyas.otopark_elmar_sonhali;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyas on 6.06.2018.
 */

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "otopark_db";
    public static final int DATABASE_VERSION = 10;


    public static final String GIRIS_TABLE = "table_giris_cikis";          //giris cıkış tablosu satırları
    public static final String ROW_ID_GIRIS = "id_giris";
    public static final String PLAKA_GIRIS = "plaka_giris";
    public static final String GIRIS_SAATI_GIRIS = "saat_giris";
    public static final String GIRIS_TARIHI_GIRIS = "tarih_giris";
    public static final String TOPLAM_TUTAR_GIRIS = "ucret_giris";
    public static final String ABONELİK_SORGULAMA = "abonelik_sorgula";
    public static final String ICERIDEMI = "iceridemi";

    public static final String ABONE_TABLE = "table_aboneler";               //abone ekle tablosu satırları
    public static final String ROW_ID_ABONE = "id_abone";
    public static final String PLAKA_ABONE = "plaka_abone";
    public static final String ADI_ABONE = "adi_abone";
    public static final String TELEFON_ABONE = "telefon_abone";
    public static final String ACIKLAMA_ABONE = "aciklama_abone";
    public static final String KAYIT_TARIHI_ABONE = "kayit_tarihi_abone";


    public static final String SAAT_TANIM_TABLE = "table_saat_tanim";               //saat tanımları satırlaro
    public static final String ROW_ID_SAAT_TANIM = "id_saat_tanim";
    public static final String BASLANGIC_SAATI = "baslangic_saati";
    public static final String BITIS_SAATI = "bitis_saati";
    public static final String FIYAT = "fiyat";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {                                 //GİRİS CIKIŞ VE ABONELER tabloSu oluşturma

        db.execSQL("CREATE TABLE " + GIRIS_TABLE + "(" + ROW_ID_GIRIS + " INTEGER PRIMARY KEY, " + PLAKA_GIRIS + " TEXT NOT NULL, " + GIRIS_SAATI_GIRIS + " TEXT NOT NULL, " + GIRIS_TARIHI_GIRIS + " TEXT NOT NULL, " + TOPLAM_TUTAR_GIRIS + " TEXT NOT NULL, " + ABONELİK_SORGULAMA + " TEXT NOT NULL, " + ICERIDEMI + " TEXT NOT NULL   )");
        db.execSQL("CREATE TABLE " + ABONE_TABLE + "(" + ROW_ID_ABONE + " INTEGER PRIMARY KEY, " + PLAKA_ABONE + " TEXT NOT NULL, " + ADI_ABONE + " TEXT NOT NULL, " + TELEFON_ABONE + " TEXT NOT NULL, " + ACIKLAMA_ABONE + " TEXT NOT NULL, " + KAYIT_TARIHI_ABONE + " TEXT NOT NULL )");
        db.execSQL("CREATE TABLE " + SAAT_TANIM_TABLE + "(" + ROW_ID_SAAT_TANIM + " INTEGER PRIMARY KEY, " + BASLANGIC_SAATI + " TEXT NOT NULL, " + BITIS_SAATI + " TEXT NOT NULL, " + FIYAT + " TEXT NOT NULL )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {                   //tablo varsa sil yeniden oluşturma
        db.execSQL("DROP TABLE IF EXISTS " + GIRIS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ABONE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SAAT_TANIM_TABLE);
        onCreate(db);
    }


    public void girisCikis(String plaka_giris, String giris_saati, String giris_tarihi, String tutar, String abonelik_sorgulama, String iceridemi) {     //GİRİS CİKİS KAYIT

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLAKA_GIRIS, plaka_giris.trim());
        cv.put(GIRIS_SAATI_GIRIS, giris_saati.trim());
        cv.put(GIRIS_TARIHI_GIRIS, giris_tarihi.trim());
        cv.put(TOPLAM_TUTAR_GIRIS, tutar.trim());
        cv.put(ABONELİK_SORGULAMA, abonelik_sorgulama.trim());
        cv.put(ICERIDEMI, iceridemi.trim());
        db.insert(GIRIS_TABLE, null, cv);
        db.close();
    }

    public void aboneEkle(String plaka, String adi_soyadi, String telefon, String aciklama, String kayıt_tarihi) {          //abone ekle
               SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLAKA_ABONE, plaka.trim());
        cv.put(ADI_ABONE, adi_soyadi.trim());
        cv.put(TELEFON_ABONE, telefon.trim());
        cv.put(ACIKLAMA_ABONE, aciklama.trim());
        cv.put(KAYIT_TARIHI_ABONE, kayıt_tarihi);


        db.insert(ABONE_TABLE, null, cv);
        db.close();
    }

    public void saat_Tanimi_ekle(String baslangic_saati, String bitis_saati, String fiyat) {     //Saat tanimi ekleme
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BASLANGIC_SAATI, baslangic_saati.trim());
        cv.put(BITIS_SAATI, bitis_saati.trim());
        cv.put(FIYAT, fiyat.trim());
        db.insert(SAAT_TANIM_TABLE, null, cv);
        db.close();
    }


    public void cikis_yap(String plaka_guncelle, String giris_saati, String giris_Tarihi, String toplam_tutar, String abonelik_sorgulama, String iceridemi) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GIRIS_SAATI_GIRIS, giris_saati);
        cv.put(GIRIS_TARIHI_GIRIS, giris_Tarihi);
        cv.put(TOPLAM_TUTAR_GIRIS, toplam_tutar);
        cv.put(ABONELİK_SORGULAMA, abonelik_sorgulama);
        cv.put(ICERIDEMI, iceridemi);
        String sql = "plaka_giris='" + plaka_guncelle + "'";

        db.update(GIRIS_TABLE, cv, sql, null);

    }

    public void abone_Guncelle(String plaka_guncelle, String adi_soyadi_guncelle, String telefon_guncelle, String aciklama_guncelle, String kayıt_tarihi_guncelle) {//kayit güncelle metodu
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ADI_ABONE, adi_soyadi_guncelle);
        cv.put(TELEFON_ABONE, telefon_guncelle);
        cv.put(ACIKLAMA_ABONE, aciklama_guncelle);
        cv.put(KAYIT_TARIHI_ABONE, kayıt_tarihi_guncelle);


        db.update(ABONE_TABLE, cv, PLAKA_ABONE + "="
                + plaka_guncelle, null);

    }


    public List<String> giris_cikis_Listele(String sql) {                                      //giris cıkışları listele

        SQLiteDatabase db = this.getWritableDatabase();
        List<String> veriler = new ArrayList<String>();
        String[] sutunlar = {ROW_ID_GIRIS, PLAKA_GIRIS, GIRIS_SAATI_GIRIS, GIRIS_TARIHI_GIRIS, TOPLAM_TUTAR_GIRIS, ABONELİK_SORGULAMA};
        String gelen_plaka = "select * from table_giris_cikis where iceridemi=" + sql;
        Cursor cursor = db.rawQuery(gelen_plaka, null);
        while (cursor.moveToNext()) {
            veriler.add(cursor.getInt(0) + "  -  " + cursor.getString(1) + "  -  " + cursor.getString(2) + "  -  " + cursor.getString(3) + "  -  " + cursor.getString(4) + "  -  " + cursor.getString(5));
        }
        return veriler;

    }


    public List<String> saat_tanimi_listele() {
        List<String> veriler = new ArrayList<String>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String[] sutunlar = {ROW_ID_SAAT_TANIM, BASLANGIC_SAATI, BITIS_SAATI, FIYAT};
            Cursor cursor = db.query(SAAT_TANIM_TABLE, sutunlar, null, null, null, null, null);
            while (cursor.moveToNext()) {
                veriler.add(cursor.getString(1) + "  -  " + cursor.getString(2) + "  -  " + cursor.getString(3));
            }
            return veriler;

        } catch (Exception e) {

        }//giris cıkışları listele
        return veriler;
    }

    public List<String> aboneListele() {                                               //aboneler listele

        SQLiteDatabase db2 = this.getWritableDatabase();
        List<String> veriler = new ArrayList<String>();
        String[] sutunlar = {ROW_ID_ABONE, PLAKA_ABONE, ADI_ABONE, TELEFON_ABONE, ACIKLAMA_ABONE};
        Cursor cursor = db2.query(ABONE_TABLE, sutunlar, null, null, null, null, null);
        while (cursor.moveToNext()) {
            veriler.add(cursor.getInt(0) + "  -  " + cursor.getString(1) + "  -  " + cursor.getString(2) + "  -  " + cursor.getString(3) + "  -  " + cursor.getString(4));
        }
        return veriler;

    }

    public String arama_abone_kaydi(String aracin_aboneligi) {

        String bulunanVeri = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar = {ROW_ID_ABONE, PLAKA_ABONE, ADI_ABONE, TELEFON_ABONE, ACIKLAMA_ABONE};
        Cursor cursor = db.query(ABONE_TABLE, sutunlar, null, null, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.getString(1).equals(aracin_aboneligi)) {

                bulunanVeri = cursor.getString(1) + " - " + cursor.getString(2) + " - " + cursor.getString(3) + " - " + cursor.getString(4);


                System.out.println("bulundu2222");
            } else {
                bulunanVeri = "bulunamadi";
            }

        }
        return bulunanVeri;
    }

    public List<Veriler> arama_plaka_giris_cikis(String aracin_plakasi, String gelen) {                //aboneler listesinde plakaya göre arama yapma

        SQLiteDatabase db = this.getWritableDatabase();
        List<Veriler> veriler = new ArrayList<Veriler>();
        Veriler.listVeriler.clear();
        String[] sutunlar = {ROW_ID_GIRIS, PLAKA_GIRIS, GIRIS_SAATI_GIRIS, GIRIS_TARIHI_GIRIS, TOPLAM_TUTAR_GIRIS, ABONELİK_SORGULAMA};
        String plaka_arama = "select * from table_giris_cikis where plaka_giris='" + aracin_plakasi + "' and iceridemi=" + gelen;
        Cursor cursor = db.rawQuery(plaka_arama, null);
        while (cursor.moveToNext()) {
            Veriler v = new Veriler();
            v.setId((cursor.getInt(0)));
            v.setPlaka(cursor.getString(1));
            v.setGiris_saati(cursor.getString(2));
            v.setGiris_tarihi(cursor.getString(3));
            v.setToplam_tutar(cursor.getString(4));
            v.setAbonelik_sorgulama(cursor.getString(5));
            Veriler.listVeriler.add(v);
        }
        return Veriler.listVeriler;

    }

    public String plaka_Arama(String aracin_plakasi) {                   //giris çıkış listesinde plakaya göre arama yapma
        String bulunanVeri = "";
        SQLiteDatabase db = this.getWritableDatabase();

        String[] sutunlar = {ROW_ID_GIRIS, PLAKA_GIRIS, GIRIS_SAATI_GIRIS, GIRIS_TARIHI_GIRIS, TOPLAM_TUTAR_GIRIS, ABONELİK_SORGULAMA};
        Cursor cursor = db.query(GIRIS_TABLE, sutunlar, null, null, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.getString(1).equals(aracin_plakasi)) {
                bulunanVeri = cursor.getString(1) + " - " + cursor.getString(2) + " - " + cursor.getString(3) + " - " + cursor.getString(4) + " - " + cursor.getString(5);


                System.out.println("bulundu");
            } else {
                bulunanVeri = "bulunamadi";
            }

        }

        return bulunanVeri;


    }

    public String saatUcretiGetir(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        String fiyat="0";
        String plaka_arama = "select fiyat from table_saat_tanim where baslangic_saati <= " + i + " and bitis_saati >= " + i;
        Cursor cursor = db.rawQuery(plaka_arama, null);
        while (cursor.moveToNext()) {
            fiyat= (cursor.getString(0));
        }
        return fiyat;
    }
}