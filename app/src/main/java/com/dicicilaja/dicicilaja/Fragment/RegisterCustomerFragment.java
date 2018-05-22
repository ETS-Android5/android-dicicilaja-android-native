package com.dicicilaja.dicicilaja.Fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicicilaja.dicicilaja.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterCustomerFragment extends Fragment {

    EditText inputNama, inputEmail, inputHandphone, inputKataSandi, inputKonfirmasi;
    TextView textCheck, judulSudahPunyaAkun, sudahPunyaAkun, titleSection, bodySection;
    Button btnDaftar;
    public RegisterCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register_customer, container, false);
        inputNama = (EditText) view.findViewById(R.id.inputNama);
        inputEmail = (EditText) view.findViewById(R.id.inputEmail);
        inputHandphone = (EditText) view.findViewById(R.id.inputHandphone);
        inputKataSandi = (EditText) view.findViewById(R.id.inputKataSandi);
        inputKonfirmasi = (EditText) view.findViewById(R.id.inputKonfirmasi);
        textCheck = (TextView) view.findViewById(R.id.textCheck);
        judulSudahPunyaAkun = (TextView) view.findViewById(R.id.judulSudahPunyaAkun);
        sudahPunyaAkun = (TextView) view.findViewById(R.id.sudahPunyaAkun);
        titleSection = (TextView) view.findViewById(R.id.titleSection);
        bodySection = (TextView) view.findViewById(R.id.bodySection);
        btnDaftar = (Button) view.findViewById(R.id.btnDaftar);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        inputNama.setTypeface(opensans_semibold);
        inputEmail.setTypeface(opensans_semibold);
        inputHandphone.setTypeface(opensans_semibold);
        inputKataSandi.setTypeface(opensans_semibold);
        inputKonfirmasi.setTypeface(opensans_semibold);
        textCheck.setTypeface(opensans_reguler);
        judulSudahPunyaAkun.setTypeface(opensans_reguler);
        sudahPunyaAkun.setTypeface(opensans_semibold);
        titleSection.setTypeface(opensans_bold);
        bodySection.setTypeface(opensans_reguler);
        btnDaftar.setTypeface(opensans_bold);
        sudahPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

//        btnDaftar.setEnabled(false);
        return view;
    }

}
