package com.dicicilaja.app.Fragment;


import android.animation.Animator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.API.Interface.InterfaceDetailRequest;
import com.dicicilaja.app.API.Model.DetailRequest.Datum;
import com.dicicilaja.app.API.Model.DetailRequest.DetailRequest;
import com.dicicilaja.app.API.Model.DetailRequest.SurveyChecklist;
import com.dicicilaja.app.Activity.FullscreenActivity;
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
public class PengajuanJaminanFragment extends Fragment {
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;
    List<SurveyChecklist> surveyChecklists;
    String nikCrh;
    private static final String TAG = PengajuanJaminanFragment.class.getSimpleName();
    List<Datum> detailRequests;

    public PengajuanJaminanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pengajuan_jaminan, container, false);

        final SessionManager session = new SessionManager(getContext());
        String apiKey = "Bearer " + session.getToken();

        Bundle extras = getActivity().getIntent().getExtras();

        RelativeLayout proses = view.findViewById(R.id.proses);
        TextView title_informasi = view.findViewById(R.id.title_informasi);
        TextView title_informasi_jaminan = view.findViewById(R.id.title_informasi_jaminan);
        final TextView api_program = view.findViewById(R.id.api_program);
        final TextView api_program_no = view.findViewById(R.id.api_program_no);
        final TextView api_product = view.findViewById(R.id.api_product);
        final TextView api_channel = view.findViewById(R.id.api_channel);
        final TextView api_specification = view.findViewById(R.id.api_specification);

        final TextView api_colleteral = view.findViewById(R.id.api_colleteral);
        final TextView api_manufacturer = view.findViewById(R.id.api_manufacturer);
        final TextView api_year = view.findViewById(R.id.api_year);
        final TextView api_tenor = view.findViewById(R.id.api_tenor);
        final TextView api_ammount = view.findViewById(R.id.api_ammount);
        final TextView api_final_amount = view.findViewById(R.id.api_final_amount);
        final TextView api_area = view.findViewById(R.id.api_area);
        final TextView api_branch = view.findViewById(R.id.api_branch);
        final TextView api_zipcode = view.findViewById(R.id.api_zipcode);
        final ImageView api_ktp_image = view.findViewById(R.id.api_ktp_image);
        final ImageView api_colleteral_image = view.findViewById(R.id.api_colleteral_image);
        final TextView title_of_product = view.findViewById(R.id.title_of_product);
        final TextView title_of_spec = view.findViewById(R.id.title_of_spec);

        final TextView colon_of_product = view.findViewById(R.id.colon_of_product);
        final TextView colon_of_spec = view.findViewById(R.id.colon_of_spec);

        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_informasi.setTypeface(opensans_bold);
        title_informasi_jaminan.setTypeface(opensans_bold);



// Set up progress before call
        final ProgressDialog progress = new ProgressDialog(getContext());
        progress.setMessage("Sedang memanggil data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

//        Toast.makeText(getContext(),"ID : " + Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")),Toast.LENGTH_SHORT).show();
        InterfaceDetailRequest apiService = RetrofitClient.getClient().create(InterfaceDetailRequest.class);

        Call<DetailRequest> call = apiService.getDetailRequest(apiKey,Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
        call.enqueue(new Callback<DetailRequest>() {
            @Override
            public void onResponse(Call<DetailRequest> call, Response<DetailRequest> response) {

                if ( response.isSuccessful() ) {
                    detailRequests = response.body().getData();
                    surveyChecklists = response.body().getSurveyChecklist();
                    nikCrh = response.body().getResponsibleCrh();

                    api_program.setText(detailRequests.get(0).getProgram());
                    api_program_no.setText(detailRequests.get(0).getTracking().toString());
                    api_channel.setText(detailRequests.get(0).getChannel());
                    api_colleteral.setText(detailRequests.get(0).getColleteral());
                    api_manufacturer.setText(detailRequests.get(0).getManufacturer());
                    api_year.setText(detailRequests.get(0).getYear().toString());
                    api_tenor.setText(detailRequests.get(0).getTenor().toString() + " bulan");
                    api_ammount.setText(detailRequests.get(0).getAmmount());
                    api_final_amount.setText(detailRequests.get(0).getFinalAmount());
                    api_area.setText(detailRequests.get(0).getArea());
                    api_branch.setText(detailRequests.get(0).getBranch());
                    api_zipcode.setText(detailRequests.get(0).getZipcode());

                    String imageKtp = detailRequests.get(0).getKtpImage().toString();
                    Picasso.get().load(imageKtp).into(api_ktp_image);
                    String imageColleteral = detailRequests.get(0).getColleteralImage().toString();
                    Picasso.get().load(imageColleteral).into(api_colleteral_image);


                    try {
                        title_of_product.setVisibility(View.VISIBLE);
                        title_of_spec.setVisibility(View.VISIBLE);
                        colon_of_product.setVisibility(View.VISIBLE);
                        colon_of_spec.setVisibility(View.VISIBLE);
                        api_product.setVisibility(View.VISIBLE);
                        api_specification.setVisibility(View.VISIBLE);

                        api_product.setText(detailRequests.get(0).getProduct().toString());
                        api_specification.setText(detailRequests.get(0).getSpecification().toString());
                    } catch (Exception ex) {
                        title_of_product.setVisibility(View.GONE);
                        title_of_spec.setVisibility(View.GONE);
                        colon_of_product.setVisibility(View.GONE);
                        colon_of_spec.setVisibility(View.GONE);
                        api_product.setVisibility(View.GONE);
                        api_specification.setVisibility(View.GONE);
                    }
                    progress.dismiss();
                } else {
                    progress.dismiss();
                    Toast.makeText(getContext(), "Something went wrong with our server", Toast.LENGTH_SHORT).show();
                    Log.e("ERRORPENGAJUAN::", "ID: " + Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
                    try {
                        Log.e("ERRORPENGAJUAN::", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<DetailRequest> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(), "koneksi internet tidak ditemukan", Toast.LENGTH_SHORT).show();
                Log.e("ERRORPENGAJUAN::", "ID: " + Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
                t.printStackTrace();
                progress.dismiss();
            }
        });

        Intent intent = getActivity().getIntent();

        if(intent.hasExtra("STATUS")) {
            proses.setVisibility(View.GONE);
        }else{
            if(session.getRole().equals("tc")){
                api_colleteral_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), FullscreenActivity.class);
                        intent.putExtra("URL", detailRequests.get(0).getColleteralImage().toString());
                        startActivity(intent);
                    }
                });
                api_ktp_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), FullscreenActivity.class);
                        intent.putExtra("URL", detailRequests.get(0).getKtpImage().toString());
                        startActivity(intent);
                    }
                });
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
                api_colleteral_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), FullscreenActivity.class);
                        intent.putExtra("URL", detailRequests.get(0).getColleteralImage().toString());
                        startActivity(intent);
                    }
                });
                api_ktp_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), FullscreenActivity.class);
                        intent.putExtra("URL", detailRequests.get(0).getKtpImage().toString());
                        startActivity(intent);
                    }
                });
                proses.setVisibility(View.VISIBLE);
                proses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), RequestProcessActivity.class);
                        intent.putExtra("TRANSACTION_ID", getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID").toString());

                        try {
                            intent.putExtra("NAME", detailRequests.get(0).getResponsiblePerson().getName());
                            intent.putExtra("ROLE", detailRequests.get(0).getResponsiblePerson().getRole());
                            intent.putExtra("RESPONSE_TIME", detailRequests.get(0).getResponsiblePerson().getResponseTime());
                            intent.putExtra("NOTE", detailRequests.get(0).getResponsiblePerson().getCatatan());
                        } catch (Exception e) {

                        }

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
                api_colleteral_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), FullscreenActivity.class);
                        intent.putExtra("URL", detailRequests.get(0).getColleteralImage().toString());
                        startActivity(intent);
                    }
                });
                api_ktp_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), FullscreenActivity.class);
                        intent.putExtra("URL", detailRequests.get(0).getKtpImage().toString());
                        startActivity(intent);
                    }
                });
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
