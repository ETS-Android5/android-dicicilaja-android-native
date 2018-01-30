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
public class SummaryTCFragment extends Fragment {

    TextView api_employee, api_title, api_time, api_note, title_tugas;

    public SummaryTCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary_tc, container, false);

        api_employee = view.findViewById(R.id.api_employee);
        api_title = view.findViewById(R.id.api_title);
        api_time = view.findViewById(R.id.api_time);
        api_note = view.findViewById(R.id.api_note);
        title_tugas = view.findViewById(R.id.title_tugas);

        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_tugas.setTypeface(opensans_bold);

        api_employee.setText(getActivity().getIntent().getStringExtra("NAME"));
        api_title.setText(getActivity().getIntent().getStringExtra("ROLE"));
        api_time.setText(getActivity().getIntent().getStringExtra("RESPONSE_TIME"));
        api_note.setText(getActivity().getIntent().getStringExtra("NOTE"));

        return view;
    }

}
