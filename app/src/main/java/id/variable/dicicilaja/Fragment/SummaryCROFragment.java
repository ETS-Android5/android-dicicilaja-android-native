package id.variable.dicicilaja.Fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.variable.dicicilaja.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryCROFragment extends Fragment {

    TextView api_employee, api_title, api_date, api_note, title_tugas, api_time;
    String reschedule_date1;
    public SummaryCROFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary_cro, container, false);

        api_employee = view.findViewById(R.id.api_employee);
        api_title = view.findViewById(R.id.api_title);
        api_date = view.findViewById(R.id.api_date);
        api_note = view.findViewById(R.id.api_note);
        title_tugas = view.findViewById(R.id.title_tugas);
        api_time = view.findViewById(R.id.api_time);

        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_tugas.setTypeface(opensans_bold);

        ImageView icon_check1 = view.findViewById(R.id.icon_check1);
        ImageView icon_check2 = view.findViewById(R.id.icon_check2);
        ImageView icon_check3 = view.findViewById(R.id.icon_check3);
        ImageView icon_check4 = view.findViewById(R.id.icon_check4);
        ImageView icon_check5 = view.findViewById(R.id.icon_check5);
        ImageView icon_check6 = view.findViewById(R.id.icon_check6);
        ImageView icon_check7 = view.findViewById(R.id.icon_check7);
        ImageView icon_check8 = view.findViewById(R.id.icon_check8);
        ImageView icon_check9 = view.findViewById(R.id.icon_check9);
        ImageView icon_check10 = view.findViewById(R.id.icon_check10);
        ImageView icon_check11 = view.findViewById(R.id.icon_check11);

        String check_data_value1 = getActivity().getIntent().getStringExtra("KTP_SUAMI");
        String check_data_value2 = getActivity().getIntent().getStringExtra("KTP_PENJAMIN");
        String check_data_value3 = getActivity().getIntent().getStringExtra("SURAT_CERAI");
        String check_data_value4 = getActivity().getIntent().getStringExtra("SURAT_KEMATIAN");
        String check_data_value5 = getActivity().getIntent().getStringExtra("SURAT_DOMISILI");
        String check_data_value6 = getActivity().getIntent().getStringExtra("KARTU_KELUARGA");
        String check_data_value7 = getActivity().getIntent().getStringExtra("BUKTI_KEPEMILIKAN_RUMAH");
        String check_data_value8 = getActivity().getIntent().getStringExtra("BUKTI_PENGHASILAN");
        String check_data_value9 = getActivity().getIntent().getStringExtra("NO_RANGKA");
        String check_data_value10 = getActivity().getIntent().getStringExtra("STNK");
        String check_data_value11 = getActivity().getIntent().getStringExtra("BPKB");
        if(getActivity().getIntent().hasExtra("RESCHEDULE_DATE")) {
            reschedule_date1 = getActivity().getIntent().getStringExtra("RESCHEDULE_DATE");
            api_time.setText(getActivity().getIntent().getStringExtra("RESCHEDULE_DATE"));
        }
        try {
            if(check_data_value1.equals("1")){
                icon_check1.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_active));
            }
            if(check_data_value2.equals("1")){
                icon_check2.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_active));
            }
            if(check_data_value3.equals("1")){
                icon_check3.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_active));
            }
            if(check_data_value4.equals("1")){
                icon_check4.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_active));
            }
            if(check_data_value5.equals("1")){
                icon_check5.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_active));
            }
            if(check_data_value6.equals("1")){
                icon_check6.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_active));
            }
            if(check_data_value7.equals("1")){
                icon_check7.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_active));
            }
            if(check_data_value8.equals("1")){
                icon_check8.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_active));
            }
            if(check_data_value9.equals("1")){
                icon_check9.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_active));
            }
            if(check_data_value10.equals("1")){
                icon_check10.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_active));
            }
            if(check_data_value11.equals("1")){
                icon_check11.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_active));
            }
        } catch (Exception ex) {

        }

        api_employee.setText(getActivity().getIntent().getStringExtra("NAME"));
        api_title.setText(getActivity().getIntent().getStringExtra("ROLE"));
        api_date.setText(getActivity().getIntent().getStringExtra("RESPONSE_TIME"));
        api_note.setText(getActivity().getIntent().getStringExtra("NOTE"));

        return view;
    }

}
