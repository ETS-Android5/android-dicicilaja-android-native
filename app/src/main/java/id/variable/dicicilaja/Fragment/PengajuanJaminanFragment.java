package id.variable.dicicilaja.Fragment;


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

import id.variable.dicicilaja.API.Client.ClientDetailPengajuan;
import id.variable.dicicilaja.API.Client.RetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfaceDetailPengajuan;
import id.variable.dicicilaja.API.Interface.InterfaceDetailRequest;
import id.variable.dicicilaja.API.Item.DetailPengajuan;
import id.variable.dicicilaja.API.Item.DetailPengajuanResponse;
import id.variable.dicicilaja.API.Item.DetailRequest.Datum;
import id.variable.dicicilaja.API.Item.DetailRequest.DetailRequest;
import id.variable.dicicilaja.Activity.ProsesPengajuan2Activity;
import id.variable.dicicilaja.Activity.ProsesPengajuan3Activity;
import id.variable.dicicilaja.Activity.ProsesPengajuanActivity;
import id.variable.dicicilaja.Activity.RequestProcessActivity;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengajuanJaminanFragment extends Fragment {
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;

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
                        startActivity(intent);
                    }
                });
            }else{
                proses.setVisibility(View.GONE);
            }
        }




        InterfaceDetailRequest apiService = RetrofitClient.getClient().create(InterfaceDetailRequest.class);

        Call<DetailRequest> call = apiService.getDetailRequest(apiKey,Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
        call.enqueue(new Callback<DetailRequest>() {
            @Override
            public void onResponse(Call<DetailRequest> call, Response<DetailRequest> response) {
                if ( response.isSuccessful() ) {
                    detailRequests = response.body().getData();

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

                } else {
                    Toast.makeText(getContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<DetailRequest> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                Log.e(TAG, t.toString());
            }
        });

        return view;
    }

}
