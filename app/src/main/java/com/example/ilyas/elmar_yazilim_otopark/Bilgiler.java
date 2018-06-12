package com.example.ilyas.elmar_yazilim_otopark;

/**
 * Created by ilyas on 8.06.2018.
 */

public class Bilgiler {

    private  String id;
        private String plaka;
        private String giris_tarihi;
    private String toplam_tutar;
    private String giris_saati;

    public Bilgiler(){

    }
    public Bilgiler(String id, String plaka,String giris_tarihi, String giris_saati,String toplam_tutar){
        this.plaka=plaka;
        this.giris_saati=giris_saati;
        this.giris_tarihi=giris_tarihi;
        this.toplam_tutar=toplam_tutar;

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public void setGiris_tarihi(String giris_tarihi) {
        this.giris_tarihi = giris_tarihi;
    }

    public void setToplam_tutar(String toplam_tutar) {
        this.toplam_tutar = toplam_tutar;
    }

    public void setGiris_saati(String giris_saati) {
        this.giris_saati = giris_saati;
    }

    public String getId() {
        return id;
    }

    public String getPlaka() {
        return plaka;
    }

    public String getGiris_tarihi() {
        return giris_tarihi;
    }

    public String getToplam_tutar() {
        return toplam_tutar;
    }

    public String getGiris_saati() {
        return giris_saati;
    }




}