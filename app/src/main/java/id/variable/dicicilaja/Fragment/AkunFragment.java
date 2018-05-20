package id.variable.dicicilaja.Fragment;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.List;

import id.variable.dicicilaja.API.Client.NewRetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfacePengajuanAxi;
import id.variable.dicicilaja.API.Interface.InterfacePromo;
import id.variable.dicicilaja.API.Item.PengajuanAxi.PengajuanAxi;
import id.variable.dicicilaja.API.Item.Promo.Datum;
import id.variable.dicicilaja.API.Item.Promo.Promo;
import id.variable.dicicilaja.Activity.AxiDashboardActivity;
import id.variable.dicicilaja.Activity.DetailRequestActivity;
import id.variable.dicicilaja.Adapter.ListPromoAdapter;
import id.variable.dicicilaja.Adapter.PengajuanAkun;
import id.variable.dicicilaja.Adapter.PengajuanAxiAllAdapter;
import id.variable.dicicilaja.Listener.ClickListener;
import id.variable.dicicilaja.Listener.RecyclerTouchListener;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment {

    List<id.variable.dicicilaja.API.Item.PengajuanAxi.Datum> pengajuan;
    Button alihkan;
    LinearLayout share_app, nilai;
    String apiKey;
    public AkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_akun, container, false);



        final SessionManager session = new SessionManager(getContext());
        apiKey = "Bearer " + session.getToken();

        final ProgressDialog progress = new ProgressDialog(getContext());
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        InterfacePengajuanAxi apiService =
                NewRetrofitClient.getClient().create(InterfacePengajuanAxi.class);

        final RecyclerView recyclerView =  view.findViewById(R.id.recycler_related);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Call<PengajuanAxi> call2 = apiService.getPengajuanAxi(apiKey);
        call2.enqueue(new Callback<PengajuanAxi>() {
            @Override
            public void onResponse(Call<PengajuanAxi> call, Response<PengajuanAxi> response) {
                pengajuan = response.body().getData();
                recyclerView.setAdapter(new PengajuanAkun(pengajuan, R.layout.card_pengajuan, getContext()));
                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        Intent intent = new Intent(getContext(), DetailRequestActivity.class);
                        intent.putExtra("EXTRA_REQUEST_ID", pengajuan.get(position).getId().toString());
                        startActivity(intent);

                    }

                    @Override
                    public void onLongClick(View view, int position) {
                    }
                }));
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<PengajuanAxi> call, Throwable t) {
                progress.dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setMessage("Koneksi internet tidak ditemukan");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

        alihkan = view.findViewById(R.id.alihkan);
        share_app = view.findViewById(R.id.share_app);
        nilai = view.findViewById(R.id.nilai);

        final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }

        share_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Temukan solusi kebutuhan Anda disini https://play.google.com/store/apps/details?id=com.dicicilaja.dicicilaja");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Dicicilaja");
                startActivity(Intent.createChooser(intent, "Bagikan aplikasi Dicicilaja"));
            }
        });

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
