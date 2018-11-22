package com.example.ilyas.otopark_elmar_sonhali;

import android.app.Fragment;
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

import java.util.List;

public class saat_tanimlari extends Fragment {



    View view;
    Database veritabani = null;
    private ListView saat_tanimi_listview;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_saat_tanimlari, container, false);


        final EditText baslangic_saati = (EditText) view.findViewById(R.id.baslangıc_editText);
        final EditText bitis_saati = (EditText) view.findViewById(R.id.bitis_editText2);
        final EditText fiyat = (EditText) view.findViewById(R.id.fiyat_editText3);
        saat_tanimi_listview = (ListView) view.findViewById(R.id.saat_tamimi_Listview);
        veritabani = new Database(getActivity());
        Button fiyat_ekle = (Button) view.findViewById(R.id.ekle_button);
        Button fiyat_sil = (Button) view.findViewById(R.id.sil_button2);
        Button fiyat_guncelle = (Button) view.findViewById(R.id.guncelle_button3);

        saatTanimi_Listele();

        fiyat_ekle.setOnClickListener(new View.OnClickListener() {    //saat tanımı fiyat ekleme
            @Override
            public void onClick(View v) {

                if (!baslangic_saati.getText().equals("") && !bitis_saati.getText().equals("") && !fiyat.getText().equals("")) {

                    Database VeriTabani = new Database(getActivity());
                    VeriTabani.saat_Tanimi_ekle(baslangic_saati.getText().toString(), bitis_saati.getText().toString(), fiyat.getText().toString());
                    Toast.makeText(getActivity(), "Saat tanımı kaydedildi!!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "Alanları eksik doldurdunuz!!", Toast.LENGTH_SHORT).show();

                }

                saatTanimi_Listele();

            }
        });

        return view;
    }


    public void saatTanimi_Listele() {
        List<String> Veriler = veritabani.saat_tanimi_listele();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, Veriler);
        saat_tanimi_listview.setAdapter(adapter);
    }
}
