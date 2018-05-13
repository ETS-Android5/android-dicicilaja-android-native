package id.variable.dicicilaja.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.List;

import id.variable.dicicilaja.API.Client.NewRetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfacePromo;
import id.variable.dicicilaja.API.Item.Promo.Datum;
import id.variable.dicicilaja.API.Item.Promo.Promo;
import id.variable.dicicilaja.Activity.AxiDashboardActivity;
import id.variable.dicicilaja.Adapter.ListPromoAdapter;
import id.variable.dicicilaja.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment {

    Button alihkan;
    public AkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        final RecyclerView recyclerPromo = (RecyclerView) view.findViewById(R.id.recycler_related);
        recyclerPromo.setHasFixedSize(true);
        recyclerPromo.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        alihkan = view.findViewById(R.id.alihkan);

        alihkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AxiDashboardActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
