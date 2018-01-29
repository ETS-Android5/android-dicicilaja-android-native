package id.variable.dicicilaja.Fragment;


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
public class SummaryCROFragment extends Fragment {

    TextView api_employee, api_title, api_date, api_note;
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

        api_employee.setText(getActivity().getIntent().getStringExtra("NAME"));
        api_title.setText(getActivity().getIntent().getStringExtra("ROLE"));
        api_date.setText(getActivity().getIntent().getStringExtra("RESPONSE_TIME"));
        api_note.setText(getActivity().getIntent().getStringExtra("NOTE"));

        return view;
    }

}
