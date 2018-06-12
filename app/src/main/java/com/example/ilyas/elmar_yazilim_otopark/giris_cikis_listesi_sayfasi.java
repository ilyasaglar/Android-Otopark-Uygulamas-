package com.example.ilyas.elmar_yazilim_otopark;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ilyas on 5.06.2018.
 */

public class giris_cikis_listesi_sayfasi extends AppCompatActivity {


    ListView liste;
    Button button_listele;



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_giris_cikis_listesi);

        liste=(ListView)findViewById(R.id.liste_listview);
        button_listele=(Button)findViewById(R.id.button_liste);



        button_listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Database veritabani=new Database(giris_cikis_listesi_sayfasi.this);
                List<String> vVeriler=veritabani.veriListele();
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(giris_cikis_listesi_sayfasi.this, android.R.layout.simple_list_item_1, android.R.id.text1,vVeriler);
                liste.setAdapter(adapter);


            }
        });








    }
}