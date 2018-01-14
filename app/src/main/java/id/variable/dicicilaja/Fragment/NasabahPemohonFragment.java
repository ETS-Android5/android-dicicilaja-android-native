package id.variable.dicicilaja.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.variable.dicicilaja.API.Client.ClientDetailPengajuan;
import id.variable.dicicilaja.API.Interface.InterfaceDetailPengajuan;
import id.variable.dicicilaja.API.Item.DetailPengajuan;
import id.variable.dicicilaja.API.Item.DetailPengajuanResponse;
import id.variable.dicicilaja.Activity.ProsesPengajuanActivity;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NasabahPemohonFragment extends Fragment {

    private static final String TAG = NasabahPemohonFragment.class.getSimpleName();
    List<DetailPengajuan> detailPengajuans;

    public NasabahPemohonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_nasabah_pemohon, container, false);

        final SessionManager session = new SessionManager(getContext());
        String apiKey = "Bearer " + session.getToken();

        Bundle extras = getActivity().getIntent().getExtras();

        RelativeLayout proses = view.findViewById(R.id.proses);
        TextView title_nasabah = view.findViewById(R.id.title_nasabah);
        TextView title_pemohon = view.findViewById(R.id.title_pemohon);
        TextView button_telp_nasabah = view.findViewById(R.id.telephone_button_nasabah);
        TextView button_telp_pemohon = view.findViewById(R.id.telephone_button_pemohon);
        final TextView no_telp_nasabah = view.findViewById(R.id.api_hp);
        final TextView no_telp_pemohon = view.findViewWithTag(R.id.no_telp_pemohon);

        final TextView api_client_name = view.findViewById(R.id.api_client_name);
        final TextView api_hp = view.findViewById(R.id.api_hp);
        final TextView api_email = view.findViewById(R.id.api_email);
        final TextView api_address = view.findViewById(R.id.api_address);
        final TextView api_district = view.findViewById(R.id.api_district);
        final TextView api_city = view.findViewById(R.id.api_city);

        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_nasabah.setTypeface(opensans_bold);
        title_pemohon.setTypeface(opensans_bold);

        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProsesPengajuanActivity.class);
                startActivity(intent);
            }
        });

        button_telp_nasabah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent call = new Intent(Intent.ACTION_DIAL);
                    call.setData(Uri.parse("tel:" + no_telp_nasabah.getText()));
                    startActivity(call);
                }
        });

        button_telp_pemohon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" +no_telp_pemohon.getText() ));
                startActivity(call);
            }
        });

        InterfaceDetailPengajuan apiService =
                ClientDetailPengajuan.getClientDetailPengajuan().create(InterfaceDetailPengajuan.class);

        Call<DetailPengajuanResponse> call = apiService.getDetailPengajuan(apiKey,Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
        call.enqueue(new Callback<DetailPengajuanResponse>() {
            @Override
            public void onResponse(Call<DetailPengajuanResponse> call, Response<DetailPengajuanResponse> response) {
                if ( response.isSuccessful() ) {
                    detailPengajuans = response.body().getData();
                    api_client_name.setText(detailPengajuans.get(0).getClientName().toString());
                    api_hp.setText(detailPengajuans.get(0).getHp().toString());
                    api_email.setText(detailPengajuans.get(0).getEmail().toString());
                    api_address.setText(detailPengajuans.get(0).getAddress().toString());
                    api_district.setText(detailPengajuans.get(0).getDistrict().toString());
                    api_city.setText(detailPengajuans.get(0).getCity().toString());


                } else {
                    Toast.makeText(getContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<DetailPengajuanResponse> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                Log.e(TAG, t.toString());
            }
        });
        return view;
    }

}
