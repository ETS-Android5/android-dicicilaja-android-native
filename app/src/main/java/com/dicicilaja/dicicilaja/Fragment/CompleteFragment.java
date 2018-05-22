package com.dicicilaja.dicicilaja.Fragment;


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

import com.dicicilaja.dicicilaja.API.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceRequestProgress;
import com.dicicilaja.dicicilaja.API.Item.RequestProgress.Datum;
import com.dicicilaja.dicicilaja.API.Item.RequestProgress.RequestProgress;
import com.dicicilaja.dicicilaja.Activity.DetailRequestActivity;
import com.dicicilaja.dicicilaja.Adapter.RequestProgressAdapter;
import com.dicicilaja.dicicilaja.Listener.ClickListener;
import com.dicicilaja.dicicilaja.Listener.RecyclerTouchListener;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompleteFragment extends Fragment {

    private static final String TAG = CompleteFragment.class.getSimpleName();
    List<Datum> requests;

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

        InterfaceRequestProgress apiService =
                RetrofitClient.getClient().create(InterfaceRequestProgress.class);

        Call<RequestProgress> call = apiService.getRequestProgress(apiKey);
        call.enqueue(new Callback<RequestProgress>() {
            @Override
            public void onResponse(Call<RequestProgress> call, Response<RequestProgress> response) {
                if ( response.isSuccessful() ) {
                    requests = response.body().getData();
                    jumlah_pengajuan.setText(Integer.toString(requests.size()));
                    recyclerView.setAdapter(new RequestProgressAdapter(requests, R.layout.card_pengajuan, getContext()));


                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            Intent intent = new Intent(getContext(), DetailRequestActivity.class);
                            intent.putExtra("EXTRA_REQUEST_ID", requests.get(position).getId().toString());
                            intent.putExtra("STATUS", true);
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
            public void onFailure(Call<RequestProgress> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                Log.e(TAG, t.toString());
            }
        });

        return view;
    }

}
