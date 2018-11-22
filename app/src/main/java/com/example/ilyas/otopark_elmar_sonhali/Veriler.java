package com.example.ilyas.otopark_elmar_sonhali;

import java.util.ArrayList;

public class Veriler {

    int id;
    String plaka;
    String giris_saati;
    String giris_tarihi;
    String toplam_tutar;
    public static ArrayList<Veriler> listVeriler= new ArrayList<Veriler>();
    public String getGiris_saati() {
        return giris_saati;
    }

    public void setGiris_saati(String giris_saati) {
        this.giris_saati = giris_saati;
    }

    public String getGiris_tarihi() {
        return giris_tarihi;
    }

    public void setGiris_tarihi(String giris_tarihi) {
        this.giris_tarihi = giris_tarihi;
    }

    public String getToplam_tutar() {
        return toplam_tutar;
    }

    public void setToplam_tutar(String toplam_tutar) {
        this.toplam_tutar = toplam_tutar;
    }

    public String getAbonelik_sorgulama() {
        return abonelik_sorgulama;
    }

    public void setAbonelik_sorgulama(String abonelik_sorgulama) {
        this.abonelik_sorgulama = abonelik_sorgulama;
    }

    String abonelik_sorgulama;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

}
