package com.dicicilaja.dicicilaja.Fragment;


import android.animation.Animator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

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
public class PengajuanJaminanFragment extends Fragment {
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;
    List<SurveyChecklist> surveyChecklists;
    String nikCrh;
    private static final String TAG = PengajuanJaminanFragment.class.getSimpleName();
    List<com.dicicilaja.dicicilaja.API.Item.RequestDetail.Datum> detailRequests;

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
        final TextView api_product = view.findViewById(R.id.api_product);
        final TextView api_channel = view.findViewById(R.id.api_channel);
        final TextView api_specification = view.findViewById(R.id.api_specification);

        final TextView api_colleteral = view.findViewById(R.id.api_colleteral);
        final TextView api_manufacturer = view.findViewById(R.id.api_manufacturer);
        final TextView api_year = view.findViewById(R.id.api_year);
        final TextView api_tenor = view.findViewById(R.id.api_tenor);
        final TextView api_ammount = view.findViewById(R.id.api_ammount);
        final TextView api_area = view.findViewById(R.id.api_area);
        final TextView api_branch = view.findViewById(R.id.api_branch);
        final TextView api_zipcode = view.findViewById(R.id.api_zipcode);
        final PhotoView api_ktp_image = view.findViewById(R.id.api_ktp_image);
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

        InterfaceDetailRequest apiService = NewRetrofitClient.getClient().create(InterfaceDetailRequest.class);

        Call<RequestDetail> call = apiService.getDetailRequest(apiKey,Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
        call.enqueue(new Callback<RequestDetail>() {
            @Override
            public void onResponse(Call<RequestDetail> call, Response<RequestDetail> response) {

                if ( response.isSuccessful() ) {

                    detailRequests = response.body().getData();
                    nikCrh = response.body().getResponsibleCrh();

                    api_program.setText(detailRequests.get(0).getProgram());
                    api_channel.setText(detailRequests.get(0).getChannel());
                    api_colleteral.setText(detailRequests.get(0).getColleteral());
                    api_manufacturer.setText(detailRequests.get(0).getManufacturer());
                    api_year.setText(detailRequests.get(0).getYear().toString());
                    api_tenor.setText(detailRequests.get(0).getTenor().toString() + " bulan");
                    api_ammount.setText(detailRequests.get(0).getAmmount());
                    api_area.setText(detailRequests.get(0).getArea());
                    api_branch.setText(detailRequests.get(0).getBranch());
                    api_zipcode.setText(detailRequests.get(0).getZipcode());

                    String imageKtp = detailRequests.get(0).getKtpImage().toString();
                    Picasso.with(getContext()).load(imageKtp).into(api_ktp_image);
                    String imageColleteral = detailRequests.get(0).getColleteralImage().toString();
                    Picasso.with(getContext()).load(imageColleteral).into(api_colleteral_image);


                    try {
                        title_of_product.setVisibility(View.VISIBLE);
                        title_of_spec.setVisibility(View.VISIBLE);
                        colon_of_product.setVisibility(View.VISIBLE);
                        colon_of_spec.setVisibility(View.VISIBLE);
                        api_product.setVisibility(View.VISIBLE);
                        api_specification.setVisibility(View.VISIBLE);

                        api_product.setText(detailRequests.get(0).getProduct());
                        api_specification.setText(detailRequests.get(0).getSpecification());
                    } catch (Exception ex) {
                        title_of_product.setVisibility(View.GONE);
                        title_of_spec.setVisibility(View.GONE);
                        colon_of_product.setVisibility(View.GONE);
                        colon_of_spec.setVisibility(View.GONE);
                        api_product.setVisibility(View.GONE);
                        api_specification.setVisibility(View.GONE);
                    }

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
