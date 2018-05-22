package com.dicicilaja.dicicilaja.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicicilaja.dicicilaja.Activity.HelpAboutActivity;
import com.dicicilaja.dicicilaja.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BantuanFragment extends Fragment {

    CardView btn_about;
    public BantuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bantuan, container, false);
        btn_about = view.findViewById(R.id.btn_about);

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HelpAboutActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
