package id.variable.dicicilaja.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.variable.dicicilaja.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterAxiFragment extends Fragment {


    public RegisterAxiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register_axi, container, false);
        return view;
    }

}
