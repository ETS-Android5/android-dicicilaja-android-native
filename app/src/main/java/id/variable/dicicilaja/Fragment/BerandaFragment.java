package id.variable.dicicilaja.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

import id.variable.dicicilaja.Activity.ProductCategoryActivity;
import id.variable.dicicilaja.Activity.SimulasiActivity;
import id.variable.dicicilaja.Adapter.ListPartnerAdapter;
import id.variable.dicicilaja.Adapter.ListPromoAdapter;
import id.variable.dicicilaja.Adapter.ListRekomendasiAdapter;
import id.variable.dicicilaja.Content.PartnerModel;
import id.variable.dicicilaja.Content.PromoModel;
import id.variable.dicicilaja.Content.RekomendasiModel;
import id.variable.dicicilaja.R;

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

    TextView title_program_agen, program_axi, program_maxi, simulasi_title, simulasi_subtitle, tv_title;

    LinearLayout maxi_travel;
    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_beranda, container, false);
        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);
        title_program_agen = view.findViewById(R.id.title_program_agen);
        program_axi = view.findViewById(R.id.program_axi);
        program_maxi = view.findViewById(R.id.program_maxi);
        simulasi_title = view.findViewById(R.id.simulasi_title);
        simulasi_subtitle = view.findViewById(R.id.simulasi_subtitle);
        btn_hitung = view.findViewById(R.id.btn_hitung);
        maxi_travel = view.findViewById(R.id.maxi_travel);


        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_program_agen.setTypeface(opensans_semibold);
        program_maxi.setTypeface(opensans_semibold);
        program_axi.setTypeface(opensans_semibold);
        simulasi_title.setTypeface(opensans_semibold);
        simulasi_subtitle.setTypeface(opensans_reguler);

        promoData = new ArrayList<>();
        rekomendasiData = new ArrayList<>();
        partnerData = new ArrayList<>();
        snapHelper = new GravitySnapHelper(Gravity.START);
        createDummyData();

        RecyclerView recyclerPromo = (RecyclerView) view.findViewById(R.id.recycler_promo);
        recyclerPromo.setHasFixedSize(true);
        ListPromoAdapter adapterPromo = new ListPromoAdapter(promoData, getContext());
        recyclerPromo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        snapHelper.attachToRecyclerView(recyclerPromo);
        recyclerPromo.setAdapter(adapterPromo);

        RecyclerView recyclerRekomendasi = (RecyclerView) view.findViewById(R.id.recycler_rekomendasi);
        recyclerRekomendasi.setHasFixedSize(true);
        ListRekomendasiAdapter adapterRekomendasi = new ListRekomendasiAdapter(rekomendasiData, getContext());
        recyclerRekomendasi.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        snapHelper.attachToRecyclerView(recyclerRekomendasi);
        recyclerRekomendasi.setAdapter(adapterRekomendasi);

        RecyclerView recyclerPartner = (RecyclerView) view.findViewById(R.id.recycler_partner);
        recyclerRekomendasi.setHasFixedSize(true);
        ListPartnerAdapter adapterPartner = new ListPartnerAdapter(partnerData, getContext());
        recyclerPartner.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        snapHelper.attachToRecyclerView(recyclerPartner);
        recyclerPartner.setAdapter(adapterPartner);


        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("1",R.drawable.slider_1);
        file_maps.put("2",R.drawable.slider_2);

        for(String name : file_maps.keySet()){
            DefaultSliderView sliderView = new DefaultSliderView(getContext());
            // initialize a SliderLayout
            sliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            mDemoSlider.addSlider(sliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setCustomIndicator((PagerIndicator) view.findViewById(R.id.custom_indicator));
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Left_Bottom);
        mDemoSlider.setDuration(4000);
//        mDemoSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        mDemoSlider.addOnPageChangeListener(this);

        btn_hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SimulasiActivity.class);
                startActivity(intent);
            }
        });
        maxi_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductCategoryActivity.class);
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
        Toast.makeText(getContext(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

}
