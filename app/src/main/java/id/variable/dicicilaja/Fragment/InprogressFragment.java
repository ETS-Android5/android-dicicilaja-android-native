package id.variable.dicicilaja.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import id.variable.dicicilaja.Item.Inprogress;
import id.variable.dicicilaja.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InprogressFragment extends Fragment {

    private ListView listView;
    ArrayList<Inprogress> inprogressArrayList;
    public InprogressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inprogress, container, false);

        listView = (ListView) view.findViewById(R.id.order_in);
        inprogressArrayList = new ArrayList<>();
        inprogressArrayList.add(new Inprogress("#9432","FAWAZ RIFQI","15 menit yang lalu"));

        InprogressAdapter adapter = new InprogressAdapter(getContext(),inprogressArrayList);
        listView.setAdapter(adapter);
        return view;
    }

}
