package com.dicicilaja.dicicilaja.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


import java.util.List;

import com.dicicilaja.dicicilaja.API.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.Activity.AjukanPengajuanAxiActivity;
import com.dicicilaja.dicicilaja.Activity.LoginActivity;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfacePengajuanMarketplace;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemAllPengajuan.AllPengajuan;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemAllPengajuan.Datum;
import com.dicicilaja.dicicilaja.Adapter.ListPengajuanAdapter;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.FALSE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengajuanFragment extends Fragment {
    private static final String TAG = PengajuanFragment.class.getSimpleName();
    LinearLayout order, search;
    RecyclerView recyclerPengajuan;
    SessionManager session;
    Button pengajuan;
    String apiKey;
    public PengajuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengajuan, container, false);

        session = new SessionManager(getContext());

        apiKey = "Bearer " + session.getToken();

        order = view.findViewById(R.id.order);
        search = view.findViewById(R.id.search);
        order.setVisibility(View.GONE);
        recyclerPengajuan =  view.findViewById(R.id.recycler_pengajuan);
        pengajuan = view.findViewById(R.id.pengajuan);

        pengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), AjukanPengajuanAxiActivity.class);
                startActivity(intent);
            }
        });

        if(session.isLoggedIn() == FALSE) {
            recyclerPengajuan.setVisibility(View.GONE);
//            search.setVisibility(View.GONE);
            order.setVisibility(View.VISIBLE);

        }
        recyclerPengajuan.setHasFixedSize(true);
        recyclerPengajuan.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

        InterfacePengajuanMarketplace apiService2 =
                RetrofitClient.getClient().create(InterfacePengajuanMarketplace.class);

        Call<AllPengajuan> call2 = apiService2.getPengajuan(apiKey);
        call2.enqueue(new Callback<AllPengajuan>() {
            @Override
            public void onResponse(Call<AllPengajuan> call, Response<AllPengajuan> response) {
                if(response.isSuccessful()) {
                    final List<Datum> pengajuan = response.body().getData();
                    if(pengajuan.size() == 0){
                        recyclerPengajuan.setVisibility(View.GONE);
                        order.setVisibility(View.VISIBLE);
                    }else{
                        recyclerPengajuan.setAdapter(new ListPengajuanAdapter(pengajuan, getContext()));

                    }


                }else{
                    recyclerPengajuan.setVisibility(View.GONE);
//                    search.setVisibility(View.GONE);
                    order.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<AllPengajuan> call, Throwable t) {

            }
        });


        return view;
    }

}
