package com.dicicilaja.dicicilaja.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import java.util.List;

import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfacePengajuanMarketplace;
import com.dicicilaja.dicicilaja.Adapter.ListPengajuanAdapter;
import com.dicicilaja.dicicilaja.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengajuanFragment extends Fragment {
    private static final String TAG = PengajuanFragment.class.getSimpleName();
    LinearLayout order, search;
    public PengajuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengajuan, container, false);

        order = view.findViewById(R.id.order);
        search = view.findViewById(R.id.search);
        order.setVisibility(View.GONE);
        final RecyclerView recyclerPengajuan =  view.findViewById(R.id.recycler_pengajuan);
        recyclerPengajuan.setHasFixedSize(true);
        recyclerPengajuan.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

        InterfacePengajuanMarketplace apiService2 =
                NewRetrofitClient.getClient().create(InterfacePengajuanMarketplace.class);

        Call<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemRequestMarketplace.Recommendation> call2 = apiService2.getRecommend();
        call2.enqueue(new Callback<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemRequestMarketplace.Recommendation>() {
            @Override
            public void onResponse(Call<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemRequestMarketplace.Recommendation> call, Response<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemRequestMarketplace.Recommendation> response) {
                final List<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemRequestMarketplace.Datum> recommends = response.body().getData();

                if(response.body().getData().size() == 0) {
                    recyclerPengajuan.setVisibility(View.GONE);
                    search.setVisibility(View.GONE);
                    order.setVisibility(View.VISIBLE);
                }
                recyclerPengajuan.setAdapter(new ListPengajuanAdapter(recommends, getContext()));
            }

            @Override
            public void onFailure(Call<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemRequestMarketplace.Recommendation> call, Throwable t) {

            }
        });


        return view;
    }

}
