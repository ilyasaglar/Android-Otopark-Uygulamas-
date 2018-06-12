package com.example.ilyas.elmar_yazilim_otopark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ilyas on 8.06.2018.
 */

public class KayitOl extends AppCompatActivity
{

    EditText kayit_ad, kayit_email, kayit_sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_kayit_ol);
/*
        //EditText ler tanımlanıyor.

        Button kayit_tamamla=(Button)findViewById(R.id.kayit_tamamla);
        Button vazgec=(Button)findViewById(R.id.vazgec);
        kayit_ad = (EditText) findViewById(R.id.kayit_ad);
        kayit_email = (EditText) findViewById(R.id.kayit_email);
        kayit_sifre = (EditText) findViewById(R.id.kayit_sifre);


      final String kullaniciadi = kayit_ad.getText().toString();
      final String sifresi = kayit_sifre.getText().toString();
       final String emaili = kayit_email.getText().toString();


        kayit_tamamla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (kullaniciadi.isEmpty()||sifresi.isEmpty()||emaili.isEmpty())
                        Toast.makeText(getApplicationContext(), "Alanlar boş geçilemez!!!", Toast.LENGTH_SHORT).show();

                    else
                    {
                        //Bilgiler türünde bir k1 nesnesi,Bilgiler class nın parametrelerine göre değerleri alıyor.
                        //DAHA İYİ ANLAMAK İÇİN Bilgiler.java YA BAKARAK NASIL TUTULDUĞUNU GÖREBİLİRSİNİZ.
                      //  Bilgiler k1 = new Bilgiler(kullaniciadi, sifresi, emaili);

                        //Bir veritabanı bağlantısı açılıyor.
                      //  database_kullanici db = new database_kullanici(getApplicationContext());

                        //Veritabanında bulunan KayıtEkle metodunun dönüş tipi long'tur.
                        //Burada da long türünden bir değişkene KayıtEkle methodunun döndürdüğü değeri veriyoruz.
                        //Böylece kayıt başarılı mı değil mi bunu anlıyoruz.
                        //DAHA İYİ ANLAMAK İÇİN BU KISIMDA Veritabani.java YA BAKINIZ!!!

                       // long id = db.KayıtEkle(k1);

                        //Bu değer -1 dönmemeli.Eğer -1 dönüyorsa kayıt başarılı değil ve bir sorun vardır diyoruz.

                       /* if (id == -1)
                        {
                            Toast.makeText(KayitOl.this, "HAY AKSİ! Kayıt Hatası Oluştu!!!", Toast.LENGTH_SHORT).show();
                        }

                        //Başarılı olması durumunda buradan o satırın id değeri dönecektir.
                        else
                            Toast.makeText(getApplicationContext(), "Kayıt işlemi başarılı...", Toast.LENGTH_SHORT).show();

                    }


                } catch (Exception e) {
                    Toast.makeText(KayitOl.this, "Bilinmeyen Hata!\n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }




            }

        });

        vazgec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (kayit_ad.getText().length() > 0 || kayit_sifre.getText().length() >0 || kayit_email.getText().length()>0 ) {
                    kayit_ad.setText("");
                    kayit_sifre.setText("");
                    kayit_email.setText("");

                }
                Intent intent = new Intent(getApplicationContext(), ilk_giris_sayfasi.class);
                startActivity(intent);

            }
        });
        */
    }
}
