package id.variable.dicicilaja.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.List;

import id.variable.dicicilaja.API.Client.NewRetrofitClient;
import id.variable.dicicilaja.API.Client.RetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfaceRecommend;
import id.variable.dicicilaja.API.Interface.InterfaceRequest;
import id.variable.dicicilaja.API.Item.Recommend.Recommend;
import id.variable.dicicilaja.API.Item.Request.Datum;
import id.variable.dicicilaja.API.Item.Request.Request;
import id.variable.dicicilaja.Activity.DetailRequestActivity;
import id.variable.dicicilaja.Adapter.ListRekomendasiAdapter;
import id.variable.dicicilaja.Adapter.RequestAdapter;
import id.variable.dicicilaja.Listener.ClickListener;
import id.variable.dicicilaja.Listener.RecyclerTouchListener;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Remote.ApiUtils;
import id.variable.dicicilaja.Remote.RequestProcess;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

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

        InterfaceRecommend apiService2 =
                NewRetrofitClient.getClient().create(InterfaceRecommend.class);

        Call<Recommend> call2 = apiService2.getRecommend();
        call2.enqueue(new Callback<Recommend>() {
            @Override
            public void onResponse(Call<Recommend> call, Response<Recommend> response) {
                final List<id.variable.dicicilaja.API.Item.Recommend.Datum> recommends = response.body().getData();

                if(response.body().getData().size() == 0) {
                    recyclerPengajuan.setVisibility(View.GONE);
                    search.setVisibility(View.GONE);
                    order.setVisibility(View.VISIBLE);
                }
                recyclerPengajuan.setAdapter(new ListRekomendasiAdapter(recommends, getContext()));
            }

            @Override
            public void onFailure(Call<Recommend> call, Throwable t) {

            }
        });

        return view;
    }

}
