package com.example.ilyas.otopark_elmar_sonhali;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class otoparktaki_araclar_sayfasi extends Fragment {


    View view;
    ListView listele_otopark;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_otoparktaki_araclar, container, false);




        return view;
    }
}
