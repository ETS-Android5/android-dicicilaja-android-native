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
public class TaskCROFragment extends Fragment {


    public TaskCROFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_cro, container, false);
    }

}
