package com.example.ilyas.otopark_elmar_sonhali;

import android.app.Dialog;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

/**
 * Created by ilyas on 20.06.2018.
 */

public class aboneler extends Fragment {
    View view;
    private ListView listele2;
    Database veritabani = null;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_aboneler, container, false);
        listele2 = view.findViewById(R.id.liste_listview123);
        veritabani = new Database(getActivity());                       //sayfa açılınca listviewde veri görüntüleme
        AboneListele();


        Button yeni_Abone_ekle = (Button) view.findViewById(R.id.yeni_abone_button);
        final Button abone_duzenle = (Button) view.findViewById(R.id.duzenle_button);
        final Button abone_Sil = (Button) view.findViewById(R.id.abone_sil_button);

        yeni_Abone_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                             //tıklayınca pop up açılması

                yeni_Abone();

            }
        });

        abone_duzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abone_guncelle();
            }
        });

        abone_Sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sil_popup();


            }
        });

        return view;
    }

    public void sil_popup() {                                                    //abone sili pop up açılması

        final Dialog mydialog_sil = new Dialog(getActivity());
        mydialog_sil.setTitle("Dialog");
        mydialog_sil.setContentView(R.layout.activity_abone_sil);

        final Button abone_sil = (Button) mydialog_sil.findViewById(R.id.abone_sil_button);
        Button abone_sil_close = (Button) mydialog_sil.findViewById(R.id.abone_sil_close_button);
        final EditText plaka = (EditText) mydialog_sil.findViewById(R.id.abone_sil_edittext);


        abone_sil.setOnClickListener(new View.OnClickListener() {           //butona tıklayınca aboneyi silme işlemi
            @Override
            public void onClick(View v) {

                Database veriTabani = new Database(getActivity());
                SQLiteDatabase db = veriTabani.getWritableDatabase();
                db.delete("table_aboneler", "plaka_abone=?", new String[]{plaka.getText().toString()});


                Database veritabani = new Database(getActivity());
                List<String> vVeriler = veriTabani.aboneListele();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, vVeriler);
                listele2.setAdapter(adapter);
                Toast.makeText(getActivity(), "Abone Silindi!!", Toast.LENGTH_SHORT).show();
                mydialog_sil.dismiss();


            }
        });

        abone_sil_close.setOnClickListener(new View.OnClickListener() {                //abone silme pop up kapatma
            @Override
            public void onClick(View v) {

                mydialog_sil.dismiss();
            }
        });

        mydialog_sil.show();
    }


    public void yeni_Abone() {                                                     //abone ekle pop up sayfası açılması


        final Dialog mydialog = new Dialog(getActivity());
        mydialog.setTitle("Dialog");
        mydialog.setContentView(R.layout.activity_abone_ekle);

        Button yeni_Abone_kaydet = (Button) mydialog.findViewById(R.id.yeni_abone_ekle_button);                  //abone ekle düzenle pop up değişkenleri tanımlama
        Button close = (Button) mydialog.findViewById(R.id.close_button);
        final EditText plaka_yeni_Abone = (EditText) mydialog.findViewById(R.id.plaka_ekle_editText);
        final EditText adi_soyadi_yeni_Abone = (EditText) mydialog.findViewById(R.id.adi_editText);
        final EditText telefon_yeni_Abone = (EditText) mydialog.findViewById(R.id.telefon_editText);
        final EditText aciklama_yeni_Abone = (EditText) mydialog.findViewById(R.id.aciklama_editText);


        Calendar mcurrentTime = Calendar.getInstance();                          //sistem tarihi
        int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
        int month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
        int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz

        if (month < 12) {
            month = month + 1;
        }

        final String giris_tarihi_yeni_abone = (day + "/" + month + "/" + year);


        yeni_Abone_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (plaka_yeni_Abone.getText().length() < 3 & adi_soyadi_yeni_Abone.getText().length() < 3 & telefon_yeni_Abone.getText().length() <= 3) {
                    Toast.makeText(getActivity(), "Bilgileri eksik girdiniz!!", Toast.LENGTH_SHORT).show();

                } else {
                    Database VeriTabani = new Database(getActivity());
                    VeriTabani.aboneEkle(plaka_yeni_Abone.getText().toString(), adi_soyadi_yeni_Abone.getText().toString(), telefon_yeni_Abone.getText().toString(), aciklama_yeni_Abone.getText().toString(), giris_tarihi_yeni_abone);
                    Toast.makeText(getActivity(), "Bilgiler kaydedildi!!", Toast.LENGTH_SHORT).show();
                    AboneListele();
                    mydialog.dismiss();

                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.dismiss();
            }
        });


        mydialog.show();


    }


    public void abone_guncelle() {                                                     //abone duzenle pop up sayfası açılması


        final Dialog mydialog_guncelle = new Dialog(getActivity());
        mydialog_guncelle.setTitle("Dialog");
        mydialog_guncelle.setContentView(R.layout.activity_abone_guncelle);

        Button guncelle = (Button) mydialog_guncelle.findViewById(R.id.abone_guncelle_button);                  //abone ekle düzenle pop up değişkenleri tanımlama
        Button close_guncelle = (Button) mydialog_guncelle.findViewById(R.id.abone_guncelle_close_button);
        final EditText plaka_guncelle = (EditText) mydialog_guncelle.findViewById(R.id.plaka_guncelle_Edittext);
        final EditText adi_soyadi_guncelle = (EditText) mydialog_guncelle.findViewById(R.id.adi_soyadi_guncelle_edittext);
        final EditText telefon_guncelle = (EditText) mydialog_guncelle.findViewById(R.id.telefon_guncelle_edittext);
        final EditText aciklama_guncelle = (EditText) mydialog_guncelle.findViewById(R.id.aciklama_guncelle_edittext);


        Calendar mcurrentTime = Calendar.getInstance();                          //sistem tarihi
        int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
        int month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
        int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz

        if (month < 12) {
            month = month + 1;
        }

        final String giris_tarihi = (day + "/" + month + "/" + year);


        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database VeriTabani = new Database(getActivity());
                if (plaka_guncelle.getText().length() < 6 & adi_soyadi_guncelle.getText().length() < 3 & telefon_guncelle.getText().length() <= 10) {
                    Toast.makeText(getActivity(), "Bilgileri eksik girdiniz!!", Toast.LENGTH_SHORT).show();

                } else {

                    VeriTabani.abone_Guncelle(plaka_guncelle.getText().toString(), adi_soyadi_guncelle.getText().toString(), telefon_guncelle.getText().toString(), aciklama_guncelle.getText().toString(), giris_tarihi);
                    Toast.makeText(getActivity(), "Bilgiler güncellendi!!", Toast.LENGTH_SHORT).show();
                    AboneListele();
                    mydialog_guncelle.dismiss();


                }

            }
        });

        close_guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog_guncelle.dismiss();
            }
        });


        mydialog_guncelle.show();


    }


    public void AboneListele() {
        List<String> Veriler = veritabani.aboneListele();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, Veriler);
        listele2.setAdapter(adapter);
    }
}



