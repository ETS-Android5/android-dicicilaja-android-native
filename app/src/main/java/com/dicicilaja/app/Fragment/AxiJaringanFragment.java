package com.dicicilaja.app.Fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicicilaja.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AxiJaringanFragment extends Fragment {


    public AxiJaringanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_axi_jaringan, container, false);
    }

}
