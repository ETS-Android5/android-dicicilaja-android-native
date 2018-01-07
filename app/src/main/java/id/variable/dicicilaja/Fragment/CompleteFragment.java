package id.variable.dicicilaja.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.variable.dicicilaja.API.Interface.ApiPengajuan;
import id.variable.dicicilaja.API.Item.Pengajuan;
import id.variable.dicicilaja.API.Item.PengajuanResponse;
import id.variable.dicicilaja.Activity.DetailPengajuanActivity;
import id.variable.dicicilaja.Adapter.PengajuanAdapter;
import id.variable.dicicilaja.Listener.ClickListener;
import id.variable.dicicilaja.Listener.RecyclerTouchListener;
import id.variable.dicicilaja.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompleteFragment extends Fragment {

    private static final String TAG = CompleteFragment.class.getSimpleName();

    private final static String API_KEY = "5ecafcd6b64015065d4d58ba9837b7e0";


    public CompleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_complete, container, false);

        TextView title_pengumuman = view.findViewById(R.id.title_pengumuman);
        TextView jumlah_pengajuan = view.findViewById(R.id.jumlah_pengajuan);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_pengumuman.setTypeface(opensans_bold);
        jumlah_pengajuan.setTypeface(opensans_bold);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getContext(), "Harap masukan API KEY", Toast.LENGTH_LONG).show();
            return view;
        }

        if (API_KEY.isEmpty()) {
            Toast.makeText(getContext(), "Harap masukan API KEY", Toast.LENGTH_LONG).show();
            return view;
        }

        final RecyclerView recyclerView =  view.findViewById(R.id.recycler_pengajuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiPengajuan apiService =
                id.variable.dicicilaja.API.Client.ApiPengajuan.getClient().create(ApiPengajuan.class);

        Call<PengajuanResponse> call = apiService.getPengajuan(API_KEY);
        call.enqueue(new Callback<PengajuanResponse>() {
            @Override
            public void onResponse(Call<PengajuanResponse>call, Response<PengajuanResponse> response) {
                int statusCode = response.code();
                List<Pengajuan> pengajuans = response.body().getResults();
                recyclerView.setAdapter(new PengajuanAdapter(pengajuans, R.layout.card_pengajuan, getContext()));
                Log.d(TAG, "Jumlah pengajuan masuk : " + pengajuans.size());
            }

            @Override
            public void onFailure(Call<PengajuanResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Toast.makeText(getContext(), "Posisi pengajuan ke : "+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DetailPengajuanActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        return view;
    }

}
