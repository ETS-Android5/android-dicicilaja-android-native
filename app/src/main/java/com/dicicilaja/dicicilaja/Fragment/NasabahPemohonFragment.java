package com.dicicilaja.dicicilaja.Fragment;


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

import java.util.List;

import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceDetailRequest;
import com.dicicilaja.dicicilaja.API.Item.DetailRequest.SurveyChecklist;
import com.dicicilaja.dicicilaja.API.Item.RequestDetail.RequestDetail;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NasabahPemohonFragment extends Fragment {

    private static final String TAG = NasabahPemohonFragment.class.getSimpleName();
    List<com.dicicilaja.dicicilaja.API.Item.RequestDetail.Datum> detailRequests;
    List<SurveyChecklist> surveyChecklists;
    String nikCrh;
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
        TextView button_sms_nasabah = view.findViewById(R.id.sms_button_nasabah);
        TextView button_sms_pemohon = view.findViewById(R.id.sms_button_pemohon);
        final TextView no_telp_nasabah = view.findViewById(R.id.api_hp);
        final TextView no_telp_pemohon = view.findViewById(R.id.no_telp_pemohon);

        final TextView api_client_name = view.findViewById(R.id.api_client_name);
        final TextView api_hp = view.findViewById(R.id.api_hp);
        final TextView api_email = view.findViewById(R.id.api_email);
        final TextView api_address = view.findViewById(R.id.api_address);
        final TextView api_district = view.findViewById(R.id.api_district);
        final TextView api_city = view.findViewById(R.id.api_city);

        final TextView nama_pemohon = view.findViewById(R.id.nama_pemohon);
        final TextView email_pemohon = view.findViewById(R.id.email_pemohon);
        final TextView axi_id_pemohon = view.findViewById(R.id.id_axi_pemohon);
        final TextView kecamtan_pemohon = view.findViewById(R.id.kecamatan_pemohon);
        final TextView kota_pemohon = view.findViewById(R.id.kota_pemohon);

        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_nasabah.setTypeface(opensans_bold);
        title_pemohon.setTypeface(opensans_bold);



        button_telp_nasabah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent call = new Intent(Intent.ACTION_DIAL);
                    call.setData(Uri.parse("tel:" + no_telp_nasabah.getText() ));
                    startActivity(call);
                }
        });

        button_telp_pemohon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" + no_telp_pemohon.getText() ));
                startActivity(call);
            }
        });

        button_sms_nasabah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sms = new Intent(Intent.ACTION_VIEW);
                sms.setData(Uri.parse("sms:" + no_telp_nasabah.getText() ));
                startActivity(sms);
            }
        });

        button_sms_pemohon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sms = new Intent(Intent.ACTION_VIEW);
                sms.setData(Uri.parse("sms:" + no_telp_pemohon.getText() ));
                startActivity(sms);
            }
        });

        InterfaceDetailRequest apiService = NewRetrofitClient.getClient().create(InterfaceDetailRequest.class);

        Call<RequestDetail> call = apiService.getDetailRequest(apiKey,Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
        call.enqueue(new Callback<RequestDetail>() {
            @Override
            public void onResponse(Call<RequestDetail> call, Response<RequestDetail> response) {
                if ( response.isSuccessful() ) {
                    detailRequests = response.body().getData();

                    api_client_name.setText(detailRequests.get(0).getClientName().toString());
                    api_hp.setText(detailRequests.get(0).getHp().toString());
                    api_email.setText(detailRequests.get(0).getEmail().toString());
                    api_address.setText(detailRequests.get(0).getAddress().toString());
                    api_district.setText(detailRequests.get(0).getDistrict().toString());
                    api_city.setText(detailRequests.get(0).getCity().toString());

                    nama_pemohon.setText(detailRequests.get(0).getApplicant().getName());
                    email_pemohon.setText(detailRequests.get(0).getApplicant().getEmail());
                    no_telp_pemohon.setText(detailRequests.get(0).getApplicant().getPhone());
                    kecamtan_pemohon.setText(detailRequests.get(0).getApplicant().getDistrict());
                    kota_pemohon.setText(detailRequests.get(0).getApplicant().getCity());
                    axi_id_pemohon.setText(detailRequests.get(0).getApplicant().getAxiId());

                }

            }

            @Override
            public void onFailure(Call<RequestDetail> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(), "koneksi internet tidak ditemukan", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.toString());
            }
        });

        Intent intent = getActivity().getIntent();
        if(intent.hasExtra("STATUS")) {
            proses.setVisibility(View.GONE);
        }
        return view;
    }

}
