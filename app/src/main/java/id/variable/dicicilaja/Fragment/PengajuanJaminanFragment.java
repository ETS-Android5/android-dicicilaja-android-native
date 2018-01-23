package id.variable.dicicilaja.Fragment;


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

import com.squareup.picasso.Picasso;

import java.util.List;

import id.variable.dicicilaja.API.Client.ClientDetailPengajuan;
import id.variable.dicicilaja.API.Client.ClientPengajuan;
import id.variable.dicicilaja.API.Interface.InterfaceDetailPengajuan;
import id.variable.dicicilaja.API.Interface.InterfacePengajuan;
import id.variable.dicicilaja.API.Item.DetailPengajuan;
import id.variable.dicicilaja.API.Item.DetailPengajuanResponse;
import id.variable.dicicilaja.API.Item.Pengajuan;
import id.variable.dicicilaja.API.Item.PengajuanResponse;
import id.variable.dicicilaja.Activity.DetailPengajuanActivity;
import id.variable.dicicilaja.Activity.ProsesPengajuanActivity;
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
public class PengajuanJaminanFragment extends Fragment {

    private static final String TAG = PengajuanJaminanFragment.class.getSimpleName();
    List<DetailPengajuan> detailPengajuans;

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

        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProsesPengajuanActivity.class);
                startActivity(intent);
            }
        });

        InterfaceDetailPengajuan apiService = ClientDetailPengajuan.getClientDetailPengajuan().create(InterfaceDetailPengajuan.class);
        Call<DetailPengajuanResponse> call = apiService.getDetailPengajuan(apiKey,Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
        call.enqueue(new Callback<DetailPengajuanResponse>() {
            @Override
            public void onResponse(Call<DetailPengajuanResponse> call, Response<DetailPengajuanResponse> response) {
                if ( response.isSuccessful() ) {
                    detailPengajuans = response.body().getData();

                    api_program.setText(detailPengajuans.get(0).getProgram());
                    api_channel.setText(detailPengajuans.get(0).getChannel());
                    api_colleteral.setText(detailPengajuans.get(0).getColleteral());
                    api_manufacturer.setText(detailPengajuans.get(0).getManufacturer());
                    api_year.setText(detailPengajuans.get(0).getYear().toString());
                    api_tenor.setText(detailPengajuans.get(0).getTenor().toString() + " bulan");
                    api_ammount.setText(detailPengajuans.get(0).getAmmount());
                    api_area.setText(detailPengajuans.get(0).getArea());
                    api_branch.setText(detailPengajuans.get(0).getBranch());
                    api_zipcode.setText(detailPengajuans.get(0).getZipcode());

                    String imageKtp = detailPengajuans.get(0).getKtpImage().toString();
                    Picasso.with(getContext()).load(imageKtp).into(api_ktp_image);
                    String imageColleteral = detailPengajuans.get(0).getColleteralImage().toString();
                    Picasso.with(getContext()).load(imageColleteral).into(api_colleteral_image);

                    try {
                        title_of_product.setVisibility(View.VISIBLE);
                        title_of_spec.setVisibility(View.VISIBLE);
                        colon_of_product.setVisibility(View.VISIBLE);
                        colon_of_spec.setVisibility(View.VISIBLE);
                        api_product.setVisibility(View.VISIBLE);
                        api_specification.setVisibility(View.VISIBLE);

                        api_product.setText(detailPengajuans.get(0).getProduct().toString());
                        api_specification.setText(detailPengajuans.get(0).getSpecification().toString());
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
            public void onFailure(Call<DetailPengajuanResponse> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                Log.e(TAG, t.toString());
            }
        });

        return view;
    }

}
