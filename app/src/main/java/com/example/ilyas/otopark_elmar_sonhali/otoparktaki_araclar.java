package com.example.ilyas.otopark_elmar_sonhali;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Calendar;
import java.util.List;

public class otoparktaki_araclar extends Fragment{

        View view2;
        ListView listele_otopark;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {



      view2 = inflater.inflate(R.layout.activity_otoparktaki_araclar, container, false);



         listele_otopark=(ListView)view2.findViewById(R.id.liste_listview);


            Database veritabani_otopark=new Database(getActivity());                           //sayfa açılınca listviewde veri görüntüleme
       //     List<String> vVeriler2=veritabani_otopark.otoparktaki_araclar_listesi();
        //    ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1,vVeriler2);
       //     listele_otopark.setAdapter(adapter2);


            return view2;
        }


    }