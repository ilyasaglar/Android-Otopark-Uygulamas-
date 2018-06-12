package com.example.ilyas.elmar_yazilim_otopark;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ilyas on 5.06.2018.
 */

public class giris_cikis_sayfasi extends Activity {





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_giris_cikis);

        final String yikama_ucreti="15";
        final String denme_ucreti="45";
        final String saat_ucreti="0";

        final EditText plaka_edittext=(EditText)findViewById(R.id.plaka_edittext);
        final TextView saat_texTextView=(TextView)findViewById(R.id.saat_textview);
        final CheckBox yikama_chCheckBox=(CheckBox)findViewById(R.id.yikama_checkBox);
        final CheckBox denme_CheckBox=(CheckBox)findViewById(R.id.denme_checkBox);
        final EditText toplam_tutar=(EditText)findViewById(R.id.toplam_tutar_textview);



        TextView tarih_TextView=(TextView)findViewById(R.id.tarih_textview);
        final TextView hizmet_tutari_TextView=(TextView)findViewById(R.id.hizmet_tutari_textview);
     //   final TextView toplam_tutar_TextView=(TextView)findViewById(R.id.toplam_tutar_textview);
      //  final TextView deneme=(TextView)findViewById(R.id.textView16);
        Button kaydet_button=(Button)findViewById(R.id.kaydet_button);
        Button arama_Yap=(Button)findViewById(R.id.arama_yap);




        Calendar mcurrentTime = Calendar.getInstance();                          //sistem saati
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        final String giris_saati=(hour+":"+minute);


        Calendar mcurrentTime2 = Calendar.getInstance();                          //sistem tarihi
        int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
        int month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
        int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz


        final String giris_tarihi=(day+"/"+month+"/"+year);


        saat_texTextView.setText(giris_saati);
        tarih_TextView.setText(giris_tarihi);

        yikama_chCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {    //yikama checkbox kontrol
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(yikama_chCheckBox.isChecked()){
                    Toast.makeText(getApplicationContext(), "Yıkama Seçildi", Toast.LENGTH_SHORT).show();
                    hizmet_tutari_TextView.setText(yikama_ucreti);
                }
                if(denme_CheckBox.isChecked()){
                    hizmet_tutari_TextView.setText(denme_ucreti);
                }
                if(denme_CheckBox.isChecked() & yikama_chCheckBox.isChecked()){
                    hizmet_tutari_TextView.setText("60");

                }
                if (!denme_CheckBox.isChecked() & !yikama_chCheckBox.isChecked()){
                    hizmet_tutari_TextView.setText("0");

                }
                toplam_tutar.setText(hizmet_tutari_TextView.getText());


            }
        });



        denme_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {      // //denme checkbox kontrol
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(denme_CheckBox.isChecked()){
                    Toast.makeText(getApplicationContext(), "Denme Seçildi", Toast.LENGTH_SHORT).show();
                    hizmet_tutari_TextView.setText(denme_ucreti);

                }

                if(yikama_chCheckBox.isChecked()){
                    hizmet_tutari_TextView.setText(yikama_ucreti);
                }

                if(denme_CheckBox.isChecked() & yikama_chCheckBox.isChecked()){
                    hizmet_tutari_TextView.setText("60");

                }
                if (!denme_CheckBox.isChecked() & !yikama_chCheckBox.isChecked()){
                    hizmet_tutari_TextView.setText("0");

                }


                toplam_tutar.setText(hizmet_tutari_TextView.getText());

            }
        });



        kaydet_button.setOnClickListener(new View.OnClickListener() {                              //plaka kaydetme
            @Override
            public void onClick(View view) {                                                      //plaka boş mu diye kontrol ediliyor
                if (plaka_edittext.getText().length() < 6) {
                    Toast.makeText(getApplicationContext(), "Plakayı eksik yada yanlış girdiniz!!", Toast.LENGTH_LONG).show();
                } else {

                    Database VeriTabani = new Database(giris_cikis_sayfasi.this);
                    final String tutar=toplam_tutar.getText().toString();                  //VT fiyat bilgisi kaydı
                    VeriTabani.veriEkle(plaka_edittext.getText().toString(), giris_saati, giris_tarihi,tutar+" TL");
                    Toast.makeText(getApplicationContext(), "Plaka kaydedildi!!", Toast.LENGTH_LONG).show();
                }
            }

        });

        arama_Yap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plaka_edittext.getText().length()>0){

                    Database Veritabani=new Database(giris_cikis_sayfasi.this);
                    Veritabani.Arama(plaka_edittext.getText().toString());



                }
            }
        });





    }
}
