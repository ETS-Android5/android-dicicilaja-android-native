package com.dicicilaja.dicicilaja.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dicicilaja.dicicilaja.Activity.LoginActivity;
import com.dicicilaja.dicicilaja.Activity.NasabahActivity;
import com.dicicilaja.dicicilaja.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotUserFragment extends Fragment {

    Button btn_regis;
    TextView login_account;
    public NotUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_not_user, container, false);

        btn_regis = view.findViewById(R.id.btn_regis);
        login_account = view.findViewById(R.id.login_account);

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NasabahActivity.class);
                startActivity(intent);
            }
        });

        login_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
