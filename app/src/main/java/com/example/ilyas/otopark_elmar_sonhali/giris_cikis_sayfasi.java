package com.example.ilyas.otopark_elmar_sonhali;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by ilyas on 5.06.2018.
 */

public class giris_cikis_sayfasi extends Fragment {

    View view;
    String part2;
    String string, kalinan_Sure, gecen_toplam_zaman, zaman, bulunanVeri;        //zaman ve ücret hesaplama değişkenleri
    int year, month, day, hour, minute;
    int year2, month2, day2, hour2, minute2, toplam_kalinan_saat;
    int gecen_yil, gecen_ay, gecen_gun, gecen_saat;
    double birim_ucret;
    String Plaka_goster, Tutar_goster, Giris_saat_goster, Giris_tarih_goster;

    Database veritabani = null;

    Calendar mcurrentTime = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_giris_cikis, container, false);

        String yikama_ucreti, denme_ucreti, saat_ucreti;
        yikama_ucreti = "15";
        denme_ucreti = "45";
        saat_ucreti = "0";
        final EditText plaka_edittext = (EditText) view.findViewById(R.id.plaka_edittext);
        final TextView saat_texTextView = (TextView) view.findViewById(R.id.saat_textview);
        final CheckBox yikama_chCheckBox = (CheckBox) view.findViewById(R.id.yikama_checkBox);
        final CheckBox denme_CheckBox = (CheckBox) view.findViewById(R.id.denme_checkBox);
        final TextView toplam_tutar = (TextView) view.findViewById(R.id.toplam_tutar_textview);
        final TextView tarih_TextView = (TextView) view.findViewById(R.id.tarih_textview);
        final TextView hizmet_tutari_TextView = (TextView) view.findViewById(R.id.hizmet_tutari_textview);

        final Button giris_kaydet_button = (Button) view.findViewById(R.id.giris_kaydet_button);
        final Button giris_sorgula_button = (Button) view.findViewById(R.id.giris_sorgula_button);
        veritabani = new Database(getActivity());

        //sistem saati
        hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        minute = mcurrentTime.get(Calendar.MINUTE);
        final String giris_saati = (hour + ":" + minute);
        //sistem tarihi
        year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
        month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
        day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz

        if (month < 12) {
            month = month + 1;
        }
        final String giris_tarihi = (day + "/" + month + "/" + year);
        saat_texTextView.setText(giris_saati);
        tarih_TextView.setText(giris_tarihi);


    /*
        plaka_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//               if(plaka_edittext.getText().toString().equals(""))return;
//                veritabani.arama_plaka_giris_cikis(plaka_edittext.getText().toString(), "1");
//               if (plaka_edittext.getText().toString().equalsIgnoreCase(Veriler.listVeriler.get(0).plaka))
//                veritabani.arama_plaka_giris_cikis(plaka_edittext.getText().toString(), "1");
//                System.out.println("bulunan veri sorgula metod " + bulunanVeri);

            }
        });
*/

        yikama_chCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {    //yikama checkbox kontrol
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (yikama_chCheckBox.isChecked()) {
                    //   Toast.makeText(getApplicationContext(), "Yıkama Seçildi", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "Yıkama Seçildi", Toast.LENGTH_SHORT).show();
                    hizmet_tutari_TextView.setText("15");
                }
                if (denme_CheckBox.isChecked()) {
                    hizmet_tutari_TextView.setText("45");
                }
                if (denme_CheckBox.isChecked() && yikama_chCheckBox.isChecked()) {
                    hizmet_tutari_TextView.setText("60");

                }
                if (!denme_CheckBox.isChecked() && !yikama_chCheckBox.isChecked()) {
                    hizmet_tutari_TextView.setText("0");

                }
                toplam_tutar.setText(hizmet_tutari_TextView.getText());


            }
        });


        denme_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {      // //denme checkbox kontrol
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (denme_CheckBox.isChecked()) {
                    Toast.makeText(getActivity(), "Denme Seçildi", Toast.LENGTH_SHORT).show();

                    hizmet_tutari_TextView.setText("45");

                }
                if (yikama_chCheckBox.isChecked()) {
                    hizmet_tutari_TextView.setText("15");
                }
                if (denme_CheckBox.isChecked() && yikama_chCheckBox.isChecked()) {
                    hizmet_tutari_TextView.setText("60");

                }
                if (!denme_CheckBox.isChecked() && !yikama_chCheckBox.isChecked()) {
                    hizmet_tutari_TextView.setText("0");

                }


                toplam_tutar.setText(hizmet_tutari_TextView.getText());

            }
        });


        giris_sorgula_button.setOnClickListener(new View.OnClickListener() {                      //plakaya göre aracın otoparkta olup olmadığını sorgula sorgulama
            @Override
            public void onClick(View v) {

                if (plaka_edittext.getText().length() >= 6) {                                      //plaka en az 6 haneli olmalı

                    Database veritabani = new Database(getActivity());
                    veritabani.arama_plaka_giris_cikis(plaka_edittext.getText().toString(), "1");
                    string = bulunanVeri;

                    if (Veriler.listVeriler.size() == 0) {
                        Toast.makeText(getActivity(), "Aracı Kaydedin", Toast.LENGTH_LONG).show();
                        return;
                    } else {

                        Database veritabani2 = new Database(getActivity());
                        String abonelik = veritabani2.arama_abone_kaydi(plaka_edittext.getText().toString());
                        System.out.println("abonelikkk" + abonelik);

                        if (abonelik == "bulunamadi") {           //aracın otoparka girdiği tarih ve saat gönderiliyor
                            Giris_tarih_goster = (Veriler.listVeriler.get(0).getGiris_tarihi().toString());
                            Giris_saat_goster = (Veriler.listVeriler.get(0).getGiris_saati().toString());
                            gunSayisi(Giris_saat_goster, Giris_tarih_goster);

                        } else {                                   //aracın otoparka girdiği tarih ve saat gönderiliyor
                            Giris_tarih_goster = (Veriler.listVeriler.get(0).getGiris_tarihi().toString());
                            Giris_saat_goster = (Veriler.listVeriler.get(0).getGiris_saati().toString());
                            gunSayisi(Giris_saat_goster, Giris_tarih_goster);
                        }


                    }
                } else {
                    Toast.makeText(getActivity(), "Plakayı eksik girdiniz", Toast.LENGTH_SHORT).show();
                }

                plaka_edittext.setText(" ");


            }
        });


        giris_kaydet_button.setOnClickListener(new View.OnClickListener() {                              //plaka kaydetme
            @Override
            public void onClick(View view) {                                         //plaka boş mu diye kontrol ediliyor


                Database veritabani2 = new Database(getActivity());
                String abonelik = veritabani2.arama_abone_kaydi(plaka_edittext.getText().toString());

                if (abonelik == "bulunamadi") {

                    if (plaka_edittext.getText().length() <= 5) {
                        Toast.makeText(getActivity(), "Plakayı eksik yada yanlış girdiniz!!", Toast.LENGTH_SHORT).show();

                    } else {

                        Database VeriTabani = new Database(getActivity());
                        String tutar = toplam_tutar.getText().toString();                  //VT fiyat bilgisi kaydı
                        if(tutar.equals("")){
                            tutar="0";
                        }
                        VeriTabani.girisCikis(plaka_edittext.getText().toString(), giris_saati, giris_tarihi, tutar, "Abone", "1");

                        Toast.makeText(getActivity(), "Plaka kaydedildi!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (plaka_edittext.getText().length() <= 5) {
                        Toast.makeText(getActivity(), "Plakayı eksik yada yanlış girdiniz!!", Toast.LENGTH_SHORT).show();

                    } else {

                        Database VeriTabani = new Database(getActivity());
                    String tutar = toplam_tutar.getText().toString();                  //VT fiyat bilgisi kaydı
                        if(tutar.equals("")){
                            tutar="0";
                        }
                        VeriTabani.girisCikis(plaka_edittext.getText().toString(), giris_saati, giris_tarihi, tutar, "Abone degil", "1");
                        Toast.makeText(getActivity(), "Plaka kaydedildi!!", Toast.LENGTH_SHORT).show();
                    }
                }

                plaka_edittext.setText(" ");

            }

        });

        return view;
    }


    public int gunSayisi(String gelenSaat, String gelenTarih) {


        final SimpleDateFormat tarihTipi = new SimpleDateFormat("dd/MM/yyy");
        final SimpleDateFormat saatTipi = new SimpleDateFormat("hh:mm");

        //  gelenSaat=gelenSaat.replace(":","");


        String[] output = gelenSaat.split(":");
        String part1 = output[0];
        String part2 = output[1];

        int saat = Integer.valueOf(part1);


        int gun_sayisi = 0;


        Date d = null;
        try {
            d = tarihTipi.parse(String.valueOf(gelenTarih));
            Date simdikiTarih = new Date();

            String simdisaat = String.valueOf(hour);
            Date fark = new Date(simdikiTarih.getTime() - d.getTime());
            gun_sayisi = ((fark.getYear() % 70) * 365) + (fark.getMonth() * 30) + (fark.getDate() - 1);
            int GecenSaat = Integer.parseInt(simdisaat) - (saat);
            if (saat > Integer.parseInt(simdisaat))
                GecenSaat = 24 - saat + Integer.parseInt(simdisaat);
            if (minute > Integer.parseInt(part2))
                GecenSaat = GecenSaat + 1;
            giris_cikis_popup(gun_sayisi, GecenSaat);

            System.out.println("gün sayisi:" + gun_sayisi + " saat sayisi:" + GecenSaat);
            //   giris_cikis_popup(gun_sayisi);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return gun_sayisi;

    }


    private static String taridgondur(int a) {
        if (a >= 10)
            return String.valueOf(a);
        else return "" + String.valueOf(a);
    }

    public void giris_cikis_popup(int gelen_gun_sayisi, int gelen_saat_sayisi) {                                                     //abone ekle pop up sayfası açılması

        final Dialog mydialog2 = new Dialog(getActivity());
        mydialog2.setTitle("Dialog");
        mydialog2.setContentView(R.layout.activity_plaka_giris_cikis_popup);

        //pop up tanımlamaları
        Button cikis_yap = (Button) mydialog2.findViewById(R.id.abone_sil_button);
        Button kapatma = (Button) mydialog2.findViewById(R.id.kapatma_button);

        final TextView giris_tarihi_popup = (TextView) mydialog2.findViewById(R.id.giris_tarih_saat_popup_textview);
        final TextView islem_ucretleri_popup = (TextView) mydialog2.findViewById(R.id.islem_ucretler_popup_textview);
        final TextView plaka_popup = (TextView) mydialog2.findViewById(R.id.plaka_popup_textview);
        final TextView kalinan_sure_popup = (TextView) mydialog2.findViewById(R.id.kalinan_sure_popup_textview);
        final TextView toplam_ucretler_popup = (TextView) mydialog2.findViewById(R.id.toplam_ucretler_popup_textview);
        Plaka_goster = (Veriler.listVeriler.get(0).getPlaka().toString());
        Tutar_goster = (Veriler.listVeriler.get(0).getToplam_tutar().toString());

        plaka_popup.setText(Plaka_goster);
        islem_ucretleri_popup.setText(Tutar_goster);

        giris_tarihi_popup.setText(Giris_tarih_goster + " " + Giris_saat_goster);
        kalinan_sure_popup.setText(gelen_gun_sayisi + " gün " + gelen_saat_sayisi + " saat");

        int saatTutari = Integer.parseInt(veritabani.saatUcretiGetir((gelen_gun_sayisi * 24) + (gelen_saat_sayisi)));
        int toplam;
        toplam= Integer.parseInt(Tutar_goster);
        toplam=toplam+saatTutari;

        toplam_ucretler_popup.setText(String.valueOf(toplam));

        cikis_yap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database VeriTabani = new Database(getActivity());
                VeriTabani.cikis_yap(Plaka_goster, Giris_saat_goster, Giris_tarih_goster, Tutar_goster, " ", "0");
                Toast.makeText(getActivity(), "Çıkış Yapıldı..", Toast.LENGTH_SHORT).show();
                mydialog2.dismiss();
                mydialog2.dismiss();
            }
        });

        kapatma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog2.dismiss();
            }
        });


        mydialog2.show();

    }

}





