package com.dicicilaja.app.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;


import java.util.List;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfacePengajuanMarketplace;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAllPengajuan.AllPengajuan;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAllPengajuan.Datum;
import com.dicicilaja.app.Adapter.ListPengajuanAdapter;
import com.dicicilaja.app.OrderIn.Data.Axi.Axi;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiService3;
import com.dicicilaja.app.OrderIn.UI.OrderInActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
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
    MaterialProgressBar progressBar;
    String agen_id, agen_name;
    public PengajuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengajuan, container, false);

        session = new SessionManager(getContext());

        ApiService3 apiService3;
        apiService3 = ApiClient2.getClient().create(ApiService3.class);

        apiKey = "Bearer " + session.getToken();

        order = view.findViewById(R.id.order);
        search = view.findViewById(R.id.search);
        order.setVisibility(View.GONE);
        recyclerPengajuan =  view.findViewById(R.id.recycler_pengajuan);
        pengajuan = view.findViewById(R.id.pengajuan);
        progressBar = view.findViewById(R.id.progressBar);

        pengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Call<Axi> axiReff = apiService3.getAxi(session.getAxiId());
                axiReff.enqueue(new Callback<Axi>() {
                    @Override
                    public void onResponse(Call<Axi> call, Response<Axi> response) {
                        if (response.isSuccessful()) {
                            try {
                                if (response.body().getData().size() > 0) {
                                    agen_id = response.body().getData().get(0).getAttributes().getNomorAxiId();
                                    agen_name = response.body().getData().get(0).getAttributes().getNama();
                                    Intent intent2 = new Intent(getContext(), OrderInActivity.class);
                                    intent2.putExtra("agen_id", agen_id);
                                    intent2.putExtra("agen_name", agen_name);
                                    startActivity(intent2);
                                    progressBar.setVisibility(View.GONE);
                                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                } else {
                                    agen_id = null;
                                    agen_name = null;
                                    Intent intent2 = new Intent(getContext(), OrderInActivity.class);
                                    intent2.putExtra("agen_id", agen_id);
                                    intent2.putExtra("agen_name", agen_name);
                                    startActivity(intent2);
                                    progressBar.setVisibility(View.GONE);
                                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                }


                            } catch (Exception ex) {
                            }
                        } else {
                            progressBar.setVisibility(View.GONE);
                            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data axi gagal dipanggil, silahkan coba beberapa saat lagi.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().finish();
                                    startActivity(getActivity().getIntent());
                                }
                            });
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Axi> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Data axi gagal dipanggil, silahkan coba beberapa saat lagi.");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                                startActivity(getActivity().getIntent());
                            }
                        });
                        alertDialog.show();
                    }
                });
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