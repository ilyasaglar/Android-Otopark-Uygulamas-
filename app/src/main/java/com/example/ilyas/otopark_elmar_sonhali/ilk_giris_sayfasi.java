package com.example.ilyas.otopark_elmar_sonhali;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ilyas on 12.11.2017.
 */

public class ilk_giris_sayfasi extends Activity {

    EditText et_ad, et_sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
       //         WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ilk_giris);

        final Button giris = (Button) findViewById(R.id.giris_button);
        Button temizle = (Button) findViewById(R.id.temizle_button);


        et_ad= (EditText) findViewById(R.id.kAdi_editText);
      et_sifre= (EditText) findViewById(R.id.sifre_editText);




        giris.setOnClickListener(new View.OnClickListener() {       //KULLANICI GİRİŞİ
            @Override
            public void onClick(View view) {

                Intent girisekrani=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(girisekrani);
/*
                        if(et_ad.getText().toString().equals("admin") &&
                                et_sifre.getText().toString().equals("admin")) {
                            Toast.makeText(getApplicationContext(),
                                    "Giriş Başarılı...",Toast.LENGTH_SHORT).show();

                            girisekrani =  new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(girisekrani);

                        }else{
                            Toast.makeText(ilk_giris_sayfasi.this, "Hatalı kullanıcı adı veya şifre!!!", Toast.LENGTH_SHORT).show();


                        }
*/
                    }
                });
        temizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {     //Temizle butonu

                if (et_ad.getText().length() > 0 || et_sifre.getText().length() >0 ) {
                    et_ad.setText("");
                    et_sifre.setText("");
                }

            }

        });



    }
        }










