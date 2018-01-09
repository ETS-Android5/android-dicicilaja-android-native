package id.variable.dicicilaja.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import id.variable.dicicilaja.Activity.ProsesPengajuanActivity;
import id.variable.dicicilaja.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatPengajuanFragment extends Fragment {


    public RiwayatPengajuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_riwayat_pengajuan, container, false);

        RelativeLayout proses = view.findViewById(R.id.proses);
        TextView title_jejak = view.findViewById(R.id.title_jejak);
        TextView title_penanggung_jawab = view.findViewById(R.id.title_penanggung_jawab);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_jejak.setTypeface(opensans_bold);
        title_penanggung_jawab.setTypeface(opensans_bold);

        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProsesPengajuanActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}