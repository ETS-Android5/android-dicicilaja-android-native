package id.variable.dicicilaja.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import id.variable.dicicilaja.R;
import id.variable.dicicilaja.WebView.AboutAxiActivity;
import id.variable.dicicilaja.WebView.AboutMaxiActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterMaxiFragment extends Fragment {

    Button btnLanjut;
    public RegisterMaxiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register_maxi, container, false);

        TextView titleSection   = view.findViewById(R.id.titleSection);
        TextView bodySection    = view.findViewById(R.id.bodySection);
        TextView detailSection  = view.findViewById(R.id.detailSection);
        btnLanjut = view.findViewById(R.id.btnLanjut);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold      = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold  = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler   = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        titleSection.setTypeface(opensans_bold);
        bodySection.setTypeface(opensans_reguler);
        detailSection.setTypeface(opensans_semibold);

        btnLanjut.setEnabled(false);
        detailSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AboutMaxiActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
