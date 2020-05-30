package com.dicicilaja.app.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.API.Interface.InterfaceDetailRequest;
import com.dicicilaja.app.API.Model.DetailRequest.Datum;
import com.dicicilaja.app.API.Model.DetailRequest.DetailRequest;
import com.dicicilaja.app.API.Model.DetailRequest.SurveyChecklist;
import com.dicicilaja.app.Activity.LoginActivity;
import com.dicicilaja.app.Activity.ProsesPengajuanActivity;
import com.dicicilaja.app.Activity.RequestProcessActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NasabahPemohonFragment extends Fragment {

    private static final String TAG = NasabahPemohonFragment.class.getSimpleName();
    List<Datum> detailRequests;
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

        InterfaceDetailRequest apiService = RetrofitClient.getClient().create(InterfaceDetailRequest.class);

        Call<DetailRequest> call = apiService.getDetailRequest(apiKey,Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
        call.enqueue(new Callback<DetailRequest>() {
            @Override
            public void onResponse(Call<DetailRequest> call, Response<DetailRequest> response) {
                if (response.code() == 401) {
                    session.logoutUser();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else if ( response.isSuccessful() ) {
                    detailRequests = response.body().getData();
                    surveyChecklists = response.body().getSurveyChecklist();
                    nikCrh = response.body().getResponsibleCrh();

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
            public void onFailure(Call<DetailRequest> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(), "koneksi internet tidak ditemukan", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.toString());
            }
        });

        Intent intent = getActivity().getIntent();
        if(intent.hasExtra("STATUS")) {
            proses.setVisibility(View.GONE);
        }else{
            if(session.getRole().equals("tc")){
                proses.setVisibility(View.VISIBLE);
                proses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), ProsesPengajuanActivity.class);
                        intent.putExtra("TRANSACTION_ID", getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID").toString());
                        startActivity(intent);
                    }
                });
            }else if(session.getRole().equals("crh")){
                proses.setVisibility(View.VISIBLE);
                proses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), RequestProcessActivity.class);
                        intent.putExtra("TRANSACTION_ID", getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID").toString());
                        intent.putExtra("NAME", detailRequests.get(0).getResponsiblePerson().getName());
                        intent.putExtra("ROLE", detailRequests.get(0).getResponsiblePerson().getRole());
                        intent.putExtra("RESPONSE_TIME", detailRequests.get(0).getResponsiblePerson().getResponseTime());
                        intent.putExtra("NOTE", detailRequests.get(0).getResponsiblePerson().getCatatan());
                        intent.putExtra("STATUS_SURVEY", detailRequests.get(0).getStatusSurvey().toString());
                        intent.putExtra("STATUS", detailRequests.get(0).getStatus().toString());

                        try {
                            intent.putExtra("KTP_SUAMI", surveyChecklists.get(0).getKtpSuami().toString());
                            intent.putExtra("KTP_PENJAMIN", surveyChecklists.get(0).getKtpPenjamin().toString());
                            intent.putExtra("SURAT_CERAI", surveyChecklists.get(0).getSuratCerai().toString());
                            intent.putExtra("SURAT_KEMATIAN", surveyChecklists.get(0).getSuratKematian().toString());
                            intent.putExtra("SURAT_DOMISILI", surveyChecklists.get(0).getSuratDomisili().toString());
                            intent.putExtra("KARTU_KELUARGA", surveyChecklists.get(0).getKartuKeluarga().toString());
                            intent.putExtra("BUKTI_KEPEMILIKAN_RUMAH", surveyChecklists.get(0).getBuktiKepemilikanRumah().toString());
                            intent.putExtra("BUKTI_PENGHASILAN", surveyChecklists.get(0).getBuktiPenghasilan().toString());
                            intent.putExtra("NO_RANGKA", surveyChecklists.get(0).getNoRangka().toString());
                            intent.putExtra("STNK", surveyChecklists.get(0).getStnk().toString());
                            intent.putExtra("BPKB", surveyChecklists.get(0).getBpkb().toString());

                            intent.putExtra("RESCHEDULE_DATE", surveyChecklists.get(0).getRescheduleDate().toString());
                            intent.putExtra("FINAL_AMOUNT", detailRequests.get(0).getFinalAmount().toString());
                        } catch (Exception ex) {

                        }
                        startActivity(intent);
                    }
                });
            }else if(session.getRole().equals("cro")){
                proses.setVisibility(View.VISIBLE);
                proses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), RequestProcessActivity.class);
                        intent.putExtra("TRANSACTION_ID", getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID").toString());
                        intent.putExtra("NAME", detailRequests.get(0).getResponsiblePerson().getName());
                        intent.putExtra("ID", detailRequests.get(0).getResponsiblePerson().getUserId());
                        intent.putExtra("ROLE", detailRequests.get(0).getResponsiblePerson().getRole());
                        intent.putExtra("RESPONSE_TIME", detailRequests.get(0).getResponsiblePerson().getResponseTime());
                        intent.putExtra("NOTE", detailRequests.get(0).getResponsiblePerson().getCatatan());
                        intent.putExtra("STATUS_SURVEY", detailRequests.get(0).getStatusSurvey().toString());
                        intent.putExtra("STATUS", detailRequests.get(0).getStatus().toString());

                        intent.putExtra("NIK_CRH", nikCrh.toString());


                        try {
                            intent.putExtra("KTP_SUAMI", surveyChecklists.get(0).getKtpSuami().toString());
                            intent.putExtra("KTP_PENJAMIN", surveyChecklists.get(0).getKtpPenjamin().toString());
                            intent.putExtra("SURAT_CERAI", surveyChecklists.get(0).getSuratCerai().toString());
                            intent.putExtra("SURAT_KEMATIAN", surveyChecklists.get(0).getSuratKematian().toString());
                            intent.putExtra("SURAT_DOMISILI", surveyChecklists.get(0).getSuratDomisili().toString());
                            intent.putExtra("KARTU_KELUARGA", surveyChecklists.get(0).getKartuKeluarga().toString());
                            intent.putExtra("BUKTI_KEPEMILIKAN_RUMAH", surveyChecklists.get(0).getBuktiKepemilikanRumah().toString());
                            intent.putExtra("BUKTI_PENGHASILAN", surveyChecklists.get(0).getBuktiPenghasilan().toString());
                            intent.putExtra("NO_RANGKA", surveyChecklists.get(0).getNoRangka().toString());
                            intent.putExtra("STNK", surveyChecklists.get(0).getStnk().toString());
                            intent.putExtra("BPKB", surveyChecklists.get(0).getBpkb().toString());
                            intent.putExtra("RESCHEDULE_DATE", surveyChecklists.get(0).getRescheduleDate().toString());
                            intent.putExtra("FINAL_AMOUNT", detailRequests.get(0).getFinalAmount().toString());
                        } catch (Exception ex) {

                        }
                        startActivity(intent);
                    }
                });
            }else{
                proses.setVisibility(View.GONE);
            }
        }
        return view;
    }

}
