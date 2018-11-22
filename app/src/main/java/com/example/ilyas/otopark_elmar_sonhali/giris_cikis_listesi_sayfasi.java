package com.example.ilyas.otopark_elmar_sonhali;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ilyas on 5.06.2018.
 */

public class giris_cikis_listesi_sayfasi extends Fragment {

    View view;
    ListView liste1;
    List sonuc;
    Database veritabani = null;
    List<String> vVeriler = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_giris_cikis_listesi, container, false);
        liste1 = (ListView) view.findViewById(R.id.liste_listview);
       final EditText arama_text = (EditText) view.findViewById(R.id.arama_edittext);
        Button arama_button = (Button) view.findViewById(R.id.arama_button);

        Database veritabani = new Database(getActivity());
        //sayfa açılınca listviewde veri görüntüleme
        if (Aglar.oto) {
            vVeriler = veritabani.giris_cikis_Listele("1");
        } else {
            vVeriler = veritabani.giris_cikis_Listele("0");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, vVeriler);
        liste1.setAdapter(adapter);

/*
        arama_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (arama_text.getText().length() > 5) {
                    Database veritabani = new Database(getActivity());
                    List bulunanVeri = veritabani.arama_plaka_giris_cikis(arama_text.getText().toString());
                    System.out.println(bulunanVeri);
                    sonuc = bulunanVeri;


                    if (bulunanVeri.equals("bulunamadi")) {                                                   //aracın otoparka olup olmadığı kontrol ediliyor
                        Toast.makeText(getActivity(), "Plaka bulunamadı", Toast.LENGTH_SHORT).show();
                    } else {
                        /*
                        List<String> Veriler = veritabani.giris_cikis_Listele("0");             //cursor metodundan gelen veriyi kolonlara göre ayrıştırma
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1,Veriler);
                        liste1.setAdapter(adapter);
                        Toast.makeText(getActivity(), "Kayıtlar Getirildi", Toast.LENGTH_SHORT).show();

                        //Kayıtları listview de göstermede sıkıntı var


                    }

                } else {
                    Toast.makeText(getActivity(), "Plakayı eksik girdiniz", Toast.LENGTH_SHORT).show();
                }


            }
        });   */

        return view;
    }

    String string2;

    public void GirisCikisListele() {


    }
}

