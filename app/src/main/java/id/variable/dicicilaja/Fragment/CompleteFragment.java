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

import java.util.HashMap;
import java.util.List;

import id.variable.dicicilaja.API.Client.ClientPengajuanProgress;
import id.variable.dicicilaja.API.Interface.InterfacePengajuanProgress;
import id.variable.dicicilaja.API.Item.PengajuanProgress;
import id.variable.dicicilaja.API.Item.PengajuanProgressResponse;
import id.variable.dicicilaja.Activity.DetailPengajuanActivity;
import id.variable.dicicilaja.Adapter.PengajuanProgressAdapter;
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
public class CompleteFragment extends Fragment {

    private static final String TAG = CompleteFragment.class.getSimpleName();
    List<PengajuanProgress> pengajuanProgresses;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_complete, container, false);

        TextView title_pengumuman = view.findViewById(R.id.title_pengumuman);
        final TextView jumlah_pengajuan = view.findViewById(R.id.jumlah_pengajuan);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        SessionManager session = new SessionManager(getActivity().getBaseContext());
        String apiKey = "Bearer " + session.getToken();

        title_pengumuman.setTypeface(opensans_bold);
        jumlah_pengajuan.setTypeface(opensans_bold);

        final RecyclerView recyclerView =  view.findViewById(R.id.recycler_pengajuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        InterfacePengajuanProgress apiService =
                ClientPengajuanProgress.getClientPengajuanProgress().create(InterfacePengajuanProgress.class);

        Call<PengajuanProgressResponse> call = apiService.getPengajuanProgress(apiKey);
        call.enqueue(new Callback<PengajuanProgressResponse>() {
            @Override
            public void onResponse(Call<PengajuanProgressResponse> call, Response<PengajuanProgressResponse> response) {
                if ( response.isSuccessful() ) {
                    pengajuanProgresses = response.body().getData();

                    jumlah_pengajuan.setText(Integer.toString(pengajuanProgresses.size()));
                    recyclerView.setAdapter(new PengajuanProgressAdapter(pengajuanProgresses, R.layout.card_pengajuan, getContext()));


                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            Intent intent = new Intent(getContext(), DetailPengajuanActivity.class);
                            intent.putExtra("EXTRA_REQUEST_ID", pengajuanProgresses.get(position).getId().toString());
                            intent.putExtra("STATUS", "COMPLETE");
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
            public void onFailure(Call<PengajuanProgressResponse> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                Log.e(TAG, t.toString());
            }
        });

        return view;
    }

}
