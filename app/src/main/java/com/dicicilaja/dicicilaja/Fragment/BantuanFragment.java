package com.dicicilaja.dicicilaja.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicicilaja.dicicilaja.Activity.HelpAboutActivity;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.WebView.ChatActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BantuanFragment extends Fragment {

    CardView btn_about, email, hotline1, hotline2, chat;
    public BantuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bantuan, container, false);
        btn_about = view.findViewById(R.id.btn_about);
        email = view.findViewById(R.id.email);
        hotline1 = view.findViewById(R.id.hotline1);
        hotline2 = view.findViewById(R.id.hotline2);
        chat = view.findViewById(R.id.chat);

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HelpAboutActivity.class);
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto: dicicilaja@gmail.com"));
                startActivity(Intent.createChooser(emailIntent, "Minta Bantuan"));
            }
        });

        hotline1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "08111465005";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        hotline2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "081293343334";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChatActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
