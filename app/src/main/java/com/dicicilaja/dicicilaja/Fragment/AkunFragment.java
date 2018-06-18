package com.dicicilaja.dicicilaja.Fragment;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.API.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.API.Interface.InterfacePengajuanAxi;
import com.dicicilaja.dicicilaja.API.Item.PengajuanAxi.PengajuanAxi;
import com.dicicilaja.dicicilaja.Activity.AxiDashboardActivity;
import com.dicicilaja.dicicilaja.Activity.DetailRequestActivity;
import com.dicicilaja.dicicilaja.Activity.ProductMaxiActivity;
import com.dicicilaja.dicicilaja.Activity.ProfileActivity;
import com.dicicilaja.dicicilaja.Activity.ProfileCustomerActivity;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAllFavorite;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceProgramMaxi;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemFavorite.Datum;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemFavorite.ItemFavorite;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemProgramMaxi.Data;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemProgramMaxi.ProgramMaxi;
import com.dicicilaja.dicicilaja.Adapter.FavoriteAllAdapter;
import com.dicicilaja.dicicilaja.Adapter.PengajuanAkun;
import com.dicicilaja.dicicilaja.Adapter.ProgramMaxiAdapter;
import com.dicicilaja.dicicilaja.Listener.ClickListener;
import com.dicicilaja.dicicilaja.Listener.RecyclerTouchListener;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment {

    List<com.dicicilaja.dicicilaja.API.Item.PengajuanAxi.Datum> pengajuan;
    Button alihkan;
    List<Data> programMaxi;
    List <Datum> favorite;
    LinearLayout share_app, nilai;
    String apiKey;
    TextView nama, title_favorite;
    LinearLayout detail_profile;
    CircleImageView profile_picture;
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

        nama =  view.findViewById(R.id.nama);
        detail_profile =  view.findViewById(R.id.detail_profile);
        profile_picture =  view.findViewById(R.id.profile_picture);
        title_favorite = view.findViewById(R.id.title_favorite);

        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        title_favorite.setTypeface(opensans_bold);


        nama.setText(session.getName());
        String imageUrl = session.getPhoto();
        Picasso.with(getContext())
                .load(imageUrl)
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .into(profile_picture);

        detail_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileCustomerActivity.class);
                startActivity(intent);
            }
        });
        profile_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileCustomerActivity.class);
                startActivity(intent);
            }
        });
        InterfaceAllFavorite apiService3 =
                RetrofitClient.getClient().create(InterfaceAllFavorite.class);

        final RecyclerView recyclerView2 =  view.findViewById(R.id.recycler_related);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));

        Call<ItemFavorite> call5 = apiService3.getFavorite(apiKey);
        call5.enqueue(new Callback<ItemFavorite>() {
            @Override
            public void onResponse(Call<ItemFavorite> call, Response<ItemFavorite> response) {
                favorite = response.body().getData();

                recyclerView2.setAdapter(new FavoriteAllAdapter(favorite, R.layout.card_program, getContext()));
                recyclerView2.setNestedScrollingEnabled(false);
                recyclerView2.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView2, new ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        Intent intent = new Intent(getContext(), ProductMaxiActivity.class);
                        intent.putExtra("EXTRA_REQUEST_ID", favorite.get(position).getId().toString());
                        startActivity(intent);

                    }

                    @Override
                    public void onLongClick(View view, int position) {
                    }
                }));

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ItemFavorite> call, Throwable t) {
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

        if(session.getRole().equals("basic")){
            alihkan.setVisibility(View.GONE);
        }

        nilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });


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
