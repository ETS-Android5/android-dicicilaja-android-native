package id.variable.dicicilaja.Fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.variable.dicicilaja.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengajuanJaminanFragment extends Fragment {


    public PengajuanJaminanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pengajuan_jaminan, container, false);

        TextView title_informasi = view.findViewById(R.id.title_informasi);
        TextView title_informasi_jaminan = view.findViewById(R.id.title_informasi_jaminan);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_informasi.setTypeface(opensans_bold);
        title_informasi_jaminan.setTypeface(opensans_bold);


        return view;
    }

}
