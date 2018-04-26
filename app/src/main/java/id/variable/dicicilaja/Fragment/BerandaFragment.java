package id.variable.dicicilaja.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import id.variable.dicicilaja.API.Client.NewRetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfacePromo;
import id.variable.dicicilaja.API.Interface.InterfaceRecommend;
import id.variable.dicicilaja.API.Interface.InterfaceSimulation;
import id.variable.dicicilaja.API.Interface.InterfaceSimulationProcess;
import id.variable.dicicilaja.API.Item.AreaRequest.AreaRequest;
import id.variable.dicicilaja.API.Item.Colleteral.Colleteral;
import id.variable.dicicilaja.API.Item.Promo.Datum;
import id.variable.dicicilaja.API.Item.Promo.Promo;
import id.variable.dicicilaja.API.Item.Recommend.Recommend;
import id.variable.dicicilaja.API.Item.Simulation.Simulation;
import id.variable.dicicilaja.Activity.AllPartnerActivity;
import id.variable.dicicilaja.Activity.AllPromoActivity;
import id.variable.dicicilaja.Activity.ProductCategoryActivity;
import id.variable.dicicilaja.Activity.PromoActivity;
import id.variable.dicicilaja.Activity.SimulasiActivity;
import id.variable.dicicilaja.Adapter.ListPartnerAdapter;
import id.variable.dicicilaja.Adapter.ListPromoAdapter;
import id.variable.dicicilaja.Adapter.ListRekomendasiAdapter;
import id.variable.dicicilaja.Content.PartnerModel;
import id.variable.dicicilaja.Content.PromoModel;
import id.variable.dicicilaja.Content.RekomendasiModel;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.WebView.AboutAxiMarketplaceActivity;
import id.variable.dicicilaja.WebView.AboutMaxiMarketplaceActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout mDemoSlider;
    private ArrayList<PromoModel> promoData;
    private ArrayList<RekomendasiModel> rekomendasiData;
    private ArrayList<PartnerModel> partnerData;
    private SnapHelper snapHelper;
    Button btn_hitung;
    id.variable.dicicilaja.Remote.AreaService AreaService;
    MaterialSpinner spinnerJaminan,spinnerArea,spinnerTenor;
    Integer jaminan_value, area_value, tenor_value;

    TextView title_program_agen, program_axi, program_maxi, simulasi_title, simulasi_subtitle, tv_title;

    LinearLayout maxi_travel, webview_axi, webview_maxi;
    RelativeLayout show_all_partner, allpromo;
    ImageView dana_multiguna;

    EditText harga_simulasi;
    fr.ganfra.materialspinner.MaterialSpinner jaminan, tenor, arearequest;


    String v_harga_simulasi;
    String v_tenor;

    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_beranda, container, false);
        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);
        title_program_agen = view.findViewById(R.id.title_program_agen);
        program_axi = view.findViewById(R.id.program_axi);
        program_maxi = view.findViewById(R.id.program_maxi);
        simulasi_title = view.findViewById(R.id.simulasi_title);
        simulasi_subtitle = view.findViewById(R.id.simulasi_subtitle);
        btn_hitung = view.findViewById(R.id.btn_hitung);
        maxi_travel = view.findViewById(R.id.maxi_travel);
        show_all_partner = view.findViewById(R.id.show_all_partner);
        allpromo = view.findViewById(R.id.allpromo);
        webview_axi = view.findViewById(R.id.webview_axi);
        webview_maxi = view.findViewById(R.id.webview_maxi);
        dana_multiguna = view.findViewById(R.id.dana_multiguna);
        harga_simulasi = view.findViewById(R.id.harga_simulasi);
        jaminan = view.findViewById(R.id.jaminan);
        tenor = view.findViewById(R.id.tenor);
        arearequest = view.findViewById(R.id.arearequest);

        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_program_agen.setTypeface(opensans_semibold);
        program_maxi.setTypeface(opensans_semibold);
        program_axi.setTypeface(opensans_semibold);
        simulasi_title.setTypeface(opensans_semibold);
        simulasi_subtitle.setTypeface(opensans_reguler);

        webview_axi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AboutAxiMarketplaceActivity.class);
                startActivity(intent);
            }
        });

        webview_maxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AboutMaxiMarketplaceActivity.class);
                startActivity(intent);
            }
        });
        promoData = new ArrayList<>();
        rekomendasiData = new ArrayList<>();
        partnerData = new ArrayList<>();

        final List<String> JAMINAN_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> JAMINAN_DATA = new HashMap<Integer, String>();

        InterfaceSimulation apiServiceColleteral =
                NewRetrofitClient.getClient().create(InterfaceSimulation.class);

        Call<Colleteral> callcolleteral = apiServiceColleteral.getColleteral();
        callcolleteral.enqueue(new Callback<Colleteral>() {
            @Override
            public void onResponse(Call<Colleteral> call, Response<Colleteral> response) {

                JAMINAN_ITEMS.clear();
                JAMINAN_DATA.clear();

                for ( int i = 0; i < response.body().getData().size(); i++ ) {
                    JAMINAN_DATA.put(response.body().getData().get(i).getId(), response.body().getData().get(i).getId().toString());
                    JAMINAN_ITEMS.add(response.body().getData().get(i).getName());
                }


            }

            @Override
            public void onFailure(Call<Colleteral> call, Throwable t) {
                JAMINAN_DATA.clear();
                JAMINAN_ITEMS.clear();
                Log.e("Error", t.getMessage());
            }
        });

        ArrayAdapter<String> jaminan_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, JAMINAN_ITEMS);
        jaminan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerJaminan = view.findViewById(R.id.jaminan);
        spinnerJaminan.setAdapter(jaminan_adapter);
        spinnerJaminan.setTypeface(opensans_semibold);


        final List<String> AREA_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> AREA_DATA = new HashMap<Integer, String>();

        InterfaceSimulation apiServiceArea =
                NewRetrofitClient.getClient().create(InterfaceSimulation.class);

        Call<AreaRequest> callarea = apiServiceArea.getAreaRequest();
        callarea.enqueue(new Callback<AreaRequest>() {
            @Override
            public void onResponse(Call<AreaRequest> call, Response<AreaRequest> response) {

                AREA_ITEMS.clear();
                AREA_DATA.clear();

                for ( int i = 0; i < response.body().getData().size(); i++ ) {
                    AREA_DATA.put(response.body().getData().get(i).getId(), response.body().getData().get(i).getId().toString());
                    AREA_ITEMS.add(response.body().getData().get(i).getName());
                }


            }

            @Override
            public void onFailure(Call<AreaRequest> call, Throwable t) {
                AREA_DATA.clear();
                AREA_ITEMS.clear();
                Log.e("Error", t.getMessage());
            }
        });

        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerArea = view.findViewById(R.id.arearequest);
        spinnerArea.setAdapter(area_adapter);
        spinnerArea.setTypeface(opensans_semibold);

        final List<String> TENOR_ITEMS = new ArrayList<>();
        TENOR_ITEMS.add("12");
        TENOR_ITEMS.add("18");
        TENOR_ITEMS.add("24");
        TENOR_ITEMS.add("30");
        TENOR_ITEMS.add("36");
        TENOR_ITEMS.add("42");
        TENOR_ITEMS.add("48");

        ArrayAdapter<String> tenor_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, TENOR_ITEMS);
        tenor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTenor = view.findViewById(R.id.tenor);
        spinnerTenor.setAdapter(tenor_adapter);
        spinnerTenor.setTypeface(opensans_semibold);



        SnapHelper snapHelper = new LinearSnapHelper();

        createDummyData();

        final RecyclerView recyclerPromo = (RecyclerView) view.findViewById(R.id.recycler_promo);
        recyclerPromo.setHasFixedSize(true);
        recyclerPromo.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelperPromo = new GravitySnapHelper(Gravity.START);
        snapHelperPromo.attachToRecyclerView(recyclerPromo);

        InterfacePromo apiService =
                NewRetrofitClient.getClient().create(InterfacePromo.class);

        Call<Promo> call = apiService.getPromo();
        call.enqueue(new Callback<Promo>() {
            @Override
            public void onResponse(Call<Promo> call, Response<Promo> response) {
                final List<Datum> promos = response.body().getData();

                recyclerPromo.setAdapter(new ListPromoAdapter(promos, getContext()));
            }

            @Override
            public void onFailure(Call<Promo> call, Throwable t) {

            }
        });

        final RecyclerView recyclerRekomendasi = (RecyclerView) view.findViewById(R.id.recycler_rekomendasi);
        recyclerRekomendasi.setHasFixedSize(true);
        recyclerRekomendasi.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelperRekomendasi = new GravitySnapHelper(Gravity.START);
        snapHelperRekomendasi.attachToRecyclerView(recyclerRekomendasi);

        InterfaceRecommend apiService2 =
                NewRetrofitClient.getClient().create(InterfaceRecommend.class);

        Call<Recommend> call2 = apiService2.getRecommend();
        call2.enqueue(new Callback<Recommend>() {
            @Override
            public void onResponse(Call<Recommend> call, Response<Recommend> response) {
                final List<id.variable.dicicilaja.API.Item.Recommend.Datum> recommends = response.body().getData();

                recyclerRekomendasi.setAdapter(new ListRekomendasiAdapter(recommends, getContext()));
            }

            @Override
            public void onFailure(Call<Recommend> call, Throwable t) {

            }
        });

        RecyclerView recyclerPartner = (RecyclerView) view.findViewById(R.id.recycler_partner);
        recyclerRekomendasi.setHasFixedSize(true);
        ListPartnerAdapter adapterPartner = new ListPartnerAdapter(partnerData, getContext());
        recyclerPartner.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelperPartner = new GravitySnapHelper(Gravity.START);
        snapHelperPartner.attachToRecyclerView(recyclerPartner);
        recyclerPartner.setAdapter(adapterPartner);


        final HashMap<Integer, String> file_maps = new HashMap<Integer, String>();
        file_maps.put(1,"https://dicicilaja.com/uploads/banner/1523528108Homebanner%20Tasya.jpg");
        file_maps.put(2,"https://dicicilaja.com/uploads/banner/banner-dana-tunai.jpg");


        for(Integer name : file_maps.keySet()){
            final DefaultSliderView sliderView = new DefaultSliderView(getContext());
            // initialize a SliderLayout
            sliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderView.setOnSliderClickListener(this);
            sliderView.bundle(new Bundle());
            sliderView.getBundle()
                    .putString("extra",name.toString());
            mDemoSlider.addSlider(sliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setCustomIndicator((PagerIndicator) view.findViewById(R.id.custom_indicator));
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Left_Bottom);
        mDemoSlider.setDuration(4000);
//        mDemoSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        mDemoSlider.addOnPageChangeListener(this);

        harga_simulasi = view.findViewById(R.id.harga_simulasi);
        jaminan = view.findViewById(R.id.jaminan);
        tenor = view.findViewById(R.id.tenor);
        arearequest = view.findViewById(R.id.arearequest);

        tenor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                v_tenor = tenor.getItemAtPosition(i).toString();
                tenor_value = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        jaminan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jaminan_value = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        arearequest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                area_value = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        v_harga_simulasi = harga_simulasi.getText().toString();

        btn_hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"area : " + area_value.toString() + " jaminan : " + jaminan_value.toString() + "harga : " + harga_simulasi.getText().toString() + " tenor : " + v_tenor,Toast.LENGTH_SHORT).show();
                hitungSimulasi(area_value.toString(), jaminan_value.toString(), harga_simulasi.getText().toString(), v_tenor);
            }
        });


        maxi_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductCategoryActivity.class);
                intent.putExtra("title","MAXI Travel");
                startActivity(intent);
            }
        });

        show_all_partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllPartnerActivity.class);
                startActivity(intent);
            }
        });
        allpromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllPromoActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void createDummyData() {
        for (int j = 1; j <= 5; j++) {
            promoData.add(new PromoModel("Umroh Free Tour Istanbul Turkey",
                    "1",
                    "Mitra Usaha : PT. SUKA JADI",
                    "Rp 20.000.000",
                    "Cicilan 60 bulan Rp 900.000/bulan",
                    "1",
                    "20%"));
        }

        for (int j = 1; j <= 5; j++) {
            rekomendasiData.add(new RekomendasiModel("Umroh Free Tour Istanbul Turkey",
                    "1",
                    "Mitra Usaha : PT. SUKA JADI",
                    "Rp 20.000.000",
                    "Cicilan 60 bulan Rp 900.000/bulan",
                    "1"));
        }

        for (int j = 1; j <= 5; j++) {
            partnerData.add(new PartnerModel("1",
                    "1"));
        }

    }

    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }
    @Override
    public void onSliderClick(BaseSliderView slider) {
        Intent intent = new Intent(getContext(), PromoActivity.class);
        intent.putExtra("ID",slider.getBundle().get("extra").toString());
        startActivity(intent);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    private void hitungSimulasi(final String area, final String jaminan, final String harga, final String tenor) {
        InterfaceSimulationProcess interfaceSimulationProcess =
                NewRetrofitClient.getClient().create(InterfaceSimulationProcess.class);

        Call<Simulation> call = interfaceSimulationProcess.assign(area,jaminan, harga, tenor);
        call.enqueue(new Callback<Simulation>() {
            @Override
            public void onResponse(Call<Simulation> call, Response<Simulation> response) {
                Simulation simulation = response.body();

                Toast.makeText(getContext(),"code : " + response.code(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SimulasiActivity.class);
                intent.putExtra("HARGA_SIMULASI", harga_simulasi.getText().toString());
                intent.putExtra("JAMINAN",jaminan_value.toString());
                intent.putExtra("TENOR",tenor_value.toString());
                intent.putExtra("AREAREQUEST",area_value.toString());
                intent.putExtra("HASIL",simulation.getInstallmentAmount().toString());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Simulation> call, Throwable t) {

            }
        });
    }

}
