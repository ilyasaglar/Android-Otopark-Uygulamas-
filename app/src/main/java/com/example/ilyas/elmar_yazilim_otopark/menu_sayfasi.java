package com.example.ilyas.elmar_yazilim_otopark;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ilyas on 5.06.2018.
 */

public class menu_sayfasi extends Activity {



    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu_sayfasi);


       Button giris_cikis = (Button) findViewById(R.id.button_giris_cikis);
       Button giris_cikis_listesi = (Button) findViewById(R.id.button_giris_cikis_listesi);

        giris_cikis.setOnClickListener(new View.OnClickListener() {                              //giriş çıkış sayafasına girişi butonu
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), giris_cikis_sayfasi.class);
                startActivity(intent);
            }
        });

        giris_cikis_listesi.setOnClickListener(new View.OnClickListener() {                       //giriş çıkış sayafasına girişi butonu
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), giris_cikis_listesi_sayfasi.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Çıkış")
                .setMessage("Uygulamadan çıkmak istediğinizden emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).setNegativeButton("Hayır", null).show();
    }







}
