package id.variable.dicicilaja.Fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.variable.dicicilaja.API.Client.ClientPengajuan;
import id.variable.dicicilaja.API.Interface.InterfacePengajuan;
import id.variable.dicicilaja.API.Item.Pengajuan;
import id.variable.dicicilaja.API.Item.PengajuanResponse;
import id.variable.dicicilaja.Activity.DetailPengajuanActivity;
import id.variable.dicicilaja.Adapter.PengajuanAdapter;
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
public class InprogressFragment extends Fragment {

    private static final String TAG = InprogressFragment.class.getSimpleName();
    List<Pengajuan> pengajuans;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inprogress, container, false);

        final SessionManager session = new SessionManager(getContext());
        String apiKey = "Bearer " + session.getToken();

        TextView title_pengumuman = view.findViewById(R.id.title_pengumuman);
        final TextView jumlah_pengajuan = view.findViewById(R.id.jumlah_pengajuan);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");


        title_pengumuman.setTypeface(opensans_bold);
        jumlah_pengajuan.setTypeface(opensans_bold);

        final RecyclerView recyclerView =  view.findViewById(R.id.recycler_pengajuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        InterfacePengajuan apiService =
                ClientPengajuan.getClientPengajuan().create(InterfacePengajuan.class);

        Call<PengajuanResponse> call = apiService.getPengajuan(apiKey);
        call.enqueue(new Callback<PengajuanResponse>() {
            @Override
            public void onResponse(Call<PengajuanResponse> call, Response<PengajuanResponse> response) {
                if ( response.isSuccessful() ) {
                    pengajuans = response.body().getData();
                    jumlah_pengajuan.setText(Integer.toString(pengajuans.size()));
                    recyclerView.setAdapter(new PengajuanAdapter(pengajuans, R.layout.card_pengajuan, getContext()));


                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            Intent intent = new Intent(getContext(), DetailPengajuanActivity.class);
                            intent.putExtra("EXTRA_REQUEST_ID", pengajuans.get(position).getId().toString());
                            startActivity(intent);
                        }

                        @Override
                        public void onLongClick(View view, int position) {
                        }
                    }));


                } else {
                    Toast.makeText(getContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<PengajuanResponse> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                Log.e(TAG, t.toString());
            }
        });



        return view;
    }

}