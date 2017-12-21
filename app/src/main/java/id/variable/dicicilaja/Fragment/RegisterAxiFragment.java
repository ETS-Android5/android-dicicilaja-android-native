package id.variable.dicicilaja.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import id.variable.dicicilaja.Activity.HelpActivity;
import id.variable.dicicilaja.Activity.LoginActivity;
import id.variable.dicicilaja.Activity.RegisterAxi2Activity;
import id.variable.dicicilaja.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterAxiFragment extends Fragment {

    TextView titleSection, bodySection, detailSection, sudahPunyaAkun, judulSudahPunyaAkun;
    AppCompatSpinner selectTempat, selectTempat2;
    EditText inputReferal, inputNama, inputEmail, inputHandphone, inputIbu;
    Button btnLanjut;
    public RegisterAxiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register_axi, container, false);
        inputNama = (EditText) view.findViewById(R.id.inputNama);
        inputEmail = (EditText) view.findViewById(R.id.inputEmail);
        inputIbu = (EditText) view.findViewById(R.id.inputIbu);
        inputReferal = (EditText) view.findViewById(R.id.inputReferal);
        inputHandphone = (EditText) view.findViewById(R.id.inputHandphone);
        sudahPunyaAkun = (TextView) view.findViewById(R.id.sudahPunyaAkun);
        judulSudahPunyaAkun = (TextView) view.findViewById(R.id.judulSudahPunyaAkun);
        titleSection = (TextView) view.findViewById(R.id.titleSection);
        bodySection = (TextView) view.findViewById(R.id.bodySection);
        detailSection = (TextView) view.findViewById(R.id.detailSection);
        btnLanjut = (Button) view.findViewById(R.id.btnLanjut);
        selectTempat = (AppCompatSpinner) view.findViewById(R.id.selectTempat);
        selectTempat2 = (AppCompatSpinner) view.findViewById(R.id.selectTempat2);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        inputNama.setTypeface(opensans_semibold);
        inputEmail.setTypeface(opensans_semibold);
        inputHandphone.setTypeface(opensans_semibold);
        inputIbu.setTypeface(opensans_semibold);
        inputReferal.setTypeface(opensans_semibold);
        judulSudahPunyaAkun.setTypeface(opensans_reguler);
        sudahPunyaAkun.setTypeface(opensans_semibold);
        titleSection.setTypeface(opensans_bold);
        bodySection.setTypeface(opensans_reguler);
        detailSection.setTypeface(opensans_semibold);
        btnLanjut.setTypeface(opensans_bold);
        sudahPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RegisterAxi2Activity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
