package com.dicicilaja.app.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.dicicilaja.app.API.Client.ApiClient2;
import com.dicicilaja.app.Activity.AllProductPromoActivity;
import com.dicicilaja.app.Activity.AllProductRecommendationActivity;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceCustomerSlider;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemSlider.Data;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemSlider.Datum;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemSlider.Slider;
import com.dicicilaja.app.Adapter.BerandaImageSliderAdapter;
import com.dicicilaja.app.OrderIn.UI.OrderInActivity;
import com.dicicilaja.app.Session.SessionManager;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import fr.ganfra.materialspinner.MaterialSpinner;

import com.dicicilaja.app.API.Interface.InterfaceSimulationProcess;
import com.dicicilaja.app.API.Model.Simulation.Simulation;
import com.dicicilaja.app.Activity.AjukanPengajuanAxiActivity;
import com.dicicilaja.app.Activity.AllPartnerActivity;
import com.dicicilaja.app.Activity.ProductCategoryActivity;
import com.dicicilaja.app.Activity.RemoteMarketplace.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfacePartner;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceRecommendation;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPartner.Partner;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemRecommendation.Recommendation;
import com.dicicilaja.app.Activity.SimulasiActivity;
import com.dicicilaja.app.Adapter.ListPartnerAdapter;
//import com.dicicilaja.app.Adapter.ListPromoAdapter;
import com.dicicilaja.app.Adapter.ListRekomendasiAdapter;
import com.dicicilaja.app.Content.PartnerModel;
//import com.dicicilaja.app.Content.PromoModel;
//import com.dicicilaja.app.Content.RekomendasiModel;
import com.dicicilaja.app.R;
import com.dicicilaja.app.WebView.AboutAxiMarketplaceActivity;
import com.dicicilaja.app.WebView.AboutMaxiMarketplaceActivity;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderView sliderView;
    SliderLayout mDemoSlider;
//    private ArrayList<PromoModel> promoData;
//    private ArrayList<RekomendasiModel> rekomendasiData;
    private ArrayList<PartnerModel> partnerData;
    private SnapHelper snapHelper;
    Button btn_hitung;
    com.dicicilaja.app.Remote.AreaService AreaService;
    MaterialSpinner spinnerJaminan,spinnerArea,spinnerTenor;
    Integer jaminan_value, area_value, tenor_value;
    Integer maxSlide;

    TextView title_program_agen, program_axi, program_maxi, simulasi_title, simulasi_subtitle, tv_title;

    LinearLayout webview_axi, webview_maxi;
//    RelativeLayout allpromo;
    ImageView maxi_travel, maxi_edukasi, maxi_usaha, maxi_sehat, maxi_extraguna, maxi_griya;

    EditText harga_simulasi;
    fr.ganfra.materialspinner.MaterialSpinner jaminan, tenor, arearequest;

    HashMap<Integer, String> file_maps;
    String v_harga_simulasi;
    String v_tenor;

    HashMap<Integer, String> JAMINAN_DATA;
    HashMap<Integer, String> TENOR_DATA;
    HashMap<Integer, String> AREA_DATA;

    SessionManager session;
    String apiKey;

    ImageView dana_multiguna;

    String s_area, s_jaminan, s_tenor, s_harga;

    RelativeLayout show_all_partner, show_all_promo, show_all_recommend;

    ProgressDialog progress;

//    private final static String APP_TITLE="Dicicilaja";
//    private final static String APP_PNAME="com.dicicilaja.app";
//    private final static int DAYS_UNTIL_PROMPT = 30;//Min number of days
//    private final static int LAUNCHES_UNTIL_PROMPT = 20;//Min number of launches

    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_beranda, container, false);
//        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);
        sliderView = view.findViewById(R.id.imageSlider);
        title_program_agen = view.findViewById(R.id.title_program_agen);
        program_axi = view.findViewById(R.id.program_axi);
        program_maxi = view.findViewById(R.id.program_maxi);
        simulasi_title = view.findViewById(R.id.simulasi_title);
        simulasi_subtitle = view.findViewById(R.id.simulasi_subtitle);
        btn_hitung = view.findViewById(R.id.btn_hitung);
        maxi_travel = view.findViewById(R.id.maxi_travel);
        maxi_edukasi = view.findViewById(R.id.maxi_edukasi);
        maxi_usaha = view.findViewById(R.id.maxi_usaha);
        maxi_sehat = view.findViewById(R.id.maxi_sehat);
        maxi_extraguna = view.findViewById(R.id.maxi_extraguna);
        maxi_griya = view.findViewById(R.id.maxi_griya);
        show_all_partner = view.findViewById(R.id.show_all_partner);
//        show_all_promo = view.findViewById(R.id.show_all_promo);
//        show_all_recommend = view.findViewById(R.id.show_all_recommend);
//        allpromo = view.findViewById(R.id.allpromo);
        webview_axi = view.findViewById(R.id.webview_axi);
        webview_maxi = view.findViewById(R.id.webview_maxi);
        dana_multiguna = view.findViewById(R.id.dana_multiguna);
//        harga_simulasi = view.findViewById(R.id.harga_simulasi);
        jaminan = view.findViewById(R.id.jaminan);
        tenor = view.findViewById(R.id.tenor);
        arearequest = view.findViewById(R.id.arearequest);

        session = new SessionManager(getContext());
        apiKey = "Bearer " + session.getToken();

        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_program_agen.setTypeface(opensans_bold);
        program_maxi.setTypeface(opensans_semibold);
        program_axi.setTypeface(opensans_semibold);
//        app_launched(getContext());
//        simulasi_title.setTypeface(opensans_semibold);
//        simulasi_subtitle.setTypeface(opensans_reguler);
//
//        setCurrency(harga_simulasi);

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
        dana_multiguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OrderInActivity.class);
                startActivity(intent);
            }
        });
//        promoData = new ArrayList<>();
//        rekomendasiData = new ArrayList<>();
//        partnerData = new ArrayList<>();

//        final List<String> JAMINAN_ITEMS = new ArrayList<>();
//        JAMINAN_DATA = new HashMap<Integer, String>();

        progress = new ProgressDialog(getContext());
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();


//        InterfaceSimulation apiServiceColleteral =
//                RetrofitClient.getClient().create(InterfaceSimulation.class);
//
//        Call<Colleteral> callcolleteral = apiServiceColleteral.getColleteral();
//        callcolleteral.enqueue(new Callback<Colleteral>() {
//            @Override
//            public void onResponse(Call<Colleteral> call, Response<Colleteral> response) {
//                progress.dismiss();
//                JAMINAN_ITEMS.clear();
//                JAMINAN_DATA.clear();
//
//                for ( int i = 0; i < response.body().getData().size(); i++ ) {
//                    JAMINAN_DATA.put(response.body().getData().get(i).getId(), response.body().getData().get(i).getName());
//                    JAMINAN_ITEMS.add(response.body().getData().get(i).getName());
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<Colleteral> call, Throwable t) {
//                JAMINAN_DATA.clear();
//                JAMINAN_ITEMS.clear();
//                Log.e("Error", t.getMessage());
//            }
//        });
//
//        ArrayAdapter<String> jaminan_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, JAMINAN_ITEMS);
//        jaminan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        spinnerJaminan = view.findViewById(R.id.jaminan);
//        spinnerJaminan.setAdapter(jaminan_adapter);
//        spinnerJaminan.setTypeface(opensans_semibold);


//        final List<String> AREA_ITEMS = new ArrayList<>();
//        AREA_DATA = new HashMap<Integer, String>();
//
////        InterfaceSimulation apiServiceArea =
////                NewRetrofitClient.getClient().create(InterfaceSimulation.class);
//
//
//        InterfaceSimulation apiServiceArea1 = RetrofitClient.getClient().create(InterfaceSimulation.class);
//
//        Call<Area> callarea = apiServiceArea1.getArea();
//        callarea.enqueue(new Callback<Area>() {
//            @Override
//            public void onResponse(Call<Area> call, Response<Area> response) {
//                progress.dismiss();
//                AREA_ITEMS.clear();
//                AREA_DATA.clear();
//
//                for ( int i = 0; i < response.body().getData().size(); i++ ) {
//                    AREA_DATA.put(response.body().getData().get(i).getId(), response.body().getData().get(i).getName());
//                    AREA_ITEMS.add(response.body().getData().get(i).getName());
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<Area> call, Throwable t) {
//                AREA_DATA.clear();
//                AREA_ITEMS.clear();
//                Log.e("Error", t.getMessage());
//            }
//        });
//
//        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
//        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinnerArea = view.findViewById(R.id.arearequest);
//        spinnerArea.setAdapter(area_adapter);
//        spinnerArea.setTypeface(opensans_semibold);
//
//        final List<String> TENOR_ITEMS = new ArrayList<>();
//        TENOR_DATA = new HashMap<Integer, String>();
//
//        TENOR_ITEMS.clear();
//        TENOR_DATA.clear();
//
//        TENOR_ITEMS.add("12");
//        TENOR_ITEMS.add("18");
//        TENOR_ITEMS.add("24");
//        TENOR_ITEMS.add("30");
//        TENOR_ITEMS.add("36");
//        TENOR_ITEMS.add("42");
//        TENOR_ITEMS.add("48");
//        TENOR_DATA.put(1, "12");
//        TENOR_DATA.put(2, "18");
//        TENOR_DATA.put(3, "24");
//        TENOR_DATA.put(4, "30");
//        TENOR_DATA.put(5, "36");
//        TENOR_DATA.put(6, "42");
//        TENOR_DATA.put(7, "48");
//
//        ArrayAdapter<String> tenor_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, TENOR_ITEMS);
//        tenor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinnerTenor = view.findViewById(R.id.tenor);
//        spinnerTenor.setAdapter(tenor_adapter);
//        spinnerTenor.setTypeface(opensans_semibold);



        SnapHelper snapHelper = new LinearSnapHelper();

//        createDummyData();

//        final RecyclerView recyclerPromo = (RecyclerView) view.findViewById(R.id.recycler_promo);
//        recyclerPromo.setHasFixedSize(true);
//        recyclerPromo.setLayoutManager(new LinearLayoutManager(getContext(),
//                LinearLayoutManager.HORIZONTAL, false));

//        SnapHelper snapHelperPromo = new GravitySnapHelper(Gravity.START);
//        snapHelperPromo.attachToRecyclerView(recyclerPromo);

//        com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo apiService =
//                RetrofitClient.getClient().create(com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo.class);
//
//        Call<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call = apiService.getPromo();
//        call.enqueue(new Callback<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo>() {
//            @Override
//            public void onResponse(Call<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call, Response<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo> response) {
//                progress.dismiss();
//                final List<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Datum> promos = response.body().getData();
//
//                recyclerPromo.setAdapter(new ListPromoAdapter(promos, getContext()));
//            }
//
//            @Override
//            public void onFailure(Call<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call, Throwable t) {
//
//            }
//        });
//
//        final RecyclerView recyclerRekomendasi = (RecyclerView) view.findViewById(R.id.recycler_rekomendasi);
//        recyclerRekomendasi.setHasFixedSize(true);
//        recyclerRekomendasi.setLayoutManager(new LinearLayoutManager(getContext(),
//                LinearLayoutManager.HORIZONTAL, false));
//
//        SnapHelper snapHelperRekomendasi = new GravitySnapHelper(Gravity.START);
//        snapHelperRekomendasi.attachToRecyclerView(recyclerRekomendasi);
//
//        InterfaceRecommendation apiService2 =
//                RetrofitClient.getClient().create(InterfaceRecommendation.class);
//
//        Call<Recommendation> call2 = apiService2.getRecommend();
//        call2.enqueue(new Callback<Recommendation>() {
//            @Override
//            public void onResponse(Call<Recommendation> call, Response<Recommendation> response) {
//                progress.dismiss();
//                final List<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemRecommendation.Datum> recommends = response.body().getData();
//
//                recyclerRekomendasi.setAdapter(new ListRekomendasiAdapter(recommends, getContext()));
//            }
//
//            @Override
//            public void onFailure(Call<Recommendation> call, Throwable t) {
//
//            }
//        });

        final RecyclerView recyclerPartner = (RecyclerView) view.findViewById(R.id.recycler_partner);
        recyclerPartner.setHasFixedSize(true);

        recyclerPartner.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelperPartner = new GravitySnapHelper(Gravity.START);
        snapHelperPartner.attachToRecyclerView(recyclerPartner);

        InterfacePartner apiService3 =
                ApiClient2.getClient().create(InterfacePartner.class);

        Call<Partner> call3 = apiService3.getPartner();
        call3.enqueue(new Callback<Partner>() {
            @Override
            public void onResponse(Call<Partner> call, Response<Partner> response) {
                progress.dismiss();
                final List<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPartner.Datum> partners = response.body().getData();

                recyclerPartner.setAdapter(new ListPartnerAdapter(partners, getContext()));
            }

            @Override
            public void onFailure(Call<Partner> call, Throwable t) {

            }
        });


        file_maps = new HashMap<Integer, String>();

//        file_maps.put("https://dicicilaja.com/gudang-info/Saatnya-Jadi-AXI%21-Dan-Jadilah-Pahlawan-Bagi-Keluarga","https://dicicilaja.com/uploads/news/1510289120-saatnya-jadi-axi-dan-jadilah-pahlawan-bagi-keluarga.jpg");
//        file_maps.put("","https://dicicilaja.com/uploads/banner/0persen.jpg");
        InterfaceCustomerSlider apiSlider2 =
                com.dicicilaja.app.API.Client.ApiClient2.getClient().create(InterfaceCustomerSlider.class);
        Call<Slider> call5 = apiSlider2.getSlider(apiKey, "1", "slider");
        call5.enqueue(new Callback<Slider>() {
            @Override
            public void onResponse(Call<Slider> call, Response<Slider> response) {
//                progress.dismiss();
                List<Datum> slider = response.body().getData();
                maxSlide = slider.size();
                for (int i = 0; i < slider.size(); i++) {
                    file_maps.put(i, slider.get(i).getAttributes().getGambar());
                }
                setSliderView(getContext(),maxSlide,file_maps);
            }

            @Override
            public void onFailure(Call<Slider> call, Throwable t) {
            }
        });
        maxi_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductCategoryActivity.class);
                intent.putExtra("title","MAXI Travel");
                intent.putExtra("content","travel");
                startActivity(intent);
            }
        });

        maxi_edukasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductCategoryActivity.class);
                intent.putExtra("title","MAXI Edukasi");
                intent.putExtra("content","edukasi");
                startActivity(intent);
            }
        });
        maxi_usaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductCategoryActivity.class);
                intent.putExtra("title","MAXI Usaha");
                intent.putExtra("content","usaha");
                startActivity(intent);
            }
        });
        maxi_sehat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductCategoryActivity.class);
                intent.putExtra("title","MAXI Sehat");
                intent.putExtra("content","sehat");
                startActivity(intent);
            }
        });
        maxi_griya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductCategoryActivity.class);
                intent.putExtra("title","MAXI Griya");
                intent.putExtra("content","griya");
                startActivity(intent);
            }
        });
        maxi_extraguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductCategoryActivity.class);
                intent.putExtra("title","MAXI Extraguna");
                intent.putExtra("content","extraguna");
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
//        show_all_promo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), AllProductPromoActivity.class);
//                startActivity(intent);
//            }
//        });
//        show_all_recommend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), AllProductRecommendationActivity.class);
//                startActivity(intent);
//            }
//        });
//        allpromo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), AllPromoActivity.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }

    private void setSliderView(Context context, int maxSlide, HashMap<Integer,String> file_maps){
        sliderView.setSliderAdapter(new BerandaImageSliderAdapter(context,maxSlide,file_maps));
        sliderView.startAutoCycle();
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

//    public static void app_launched(Context mContext) {
//        SharedPreferences prefs = mContext.getSharedPreferences("apprater", 0);
//        if (prefs.getBoolean("dontshowagain", false)) { return ; }
//
//        SharedPreferences.Editor editor = prefs.edit();
//
//        // Increment launch counter
//        long launch_count = prefs.getLong("launch_count", 0) + 1;
//        editor.putLong("launch_count", launch_count);
//        System.out.println("launch_count: "+ launch_count);
//
//        // Get date of first launch
//        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
//        if (date_firstLaunch == 0) {
//            date_firstLaunch = System.currentTimeMillis();
//            editor.putLong("date_firstlaunch", date_firstLaunch);
//            System.out.println("date_firstlaunch: "+ date_firstLaunch);
//        }
//        System.out.println("date_firstlaunch: "+ date_firstLaunch);
//
//        // Wait at least n days before opening
//        if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
//            if (System.currentTimeMillis() >= date_firstLaunch +
//                    (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
//                showRateDialog(mContext, editor);
//                launch_count=0;
//                editor.putLong("launch_count", launch_count);
//            }
//        }
//
//        editor.commit();
//    }
//
//    public static void showRateDialog(final Context mContext, final SharedPreferences.Editor editor) {
//        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mContext);
//        builder.setMessage("If you enjoy using " + APP_TITLE + ", please take a moment to rate it. Thanks for your support!")
//                .setTitle("Rate " + APP_TITLE);
//
//        // Add the buttons
//        builder.setPositiveButton("Rate " + APP_TITLE, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_PNAME)));
//                editor.putBoolean("dontshowagain", true);
//                dialog.dismiss();
//            }
//        });
//        builder.setNeutralButton("Remind me later " , new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.dismiss();
//            }
//        });
//        builder.setNegativeButton("No, thanks", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                if (editor != null) {
//                    editor.putBoolean("dontshowagain", true);
//                    editor.commit();
//                }
//                dialog.dismiss();
//            }
//        });
//        android.app.AlertDialog dialog = builder.create();
//        dialog.show();
//    }


//    private void createDummyData() {
//        for (int j = 1; j <= 5; j++) {
//            promoData.add(new PromoModel("Umroh Free Tour Istanbul Turkey",
//                    "1",
//                    "Mitra Usaha : PT. SUKA JADI",
//                    "Rp 20.000.000",
//                    "Cicilan 60 bulan Rp 900.000/bulan",
//                    "1",
//                    "20%"));
//        }
//
//        for (int j = 1; j <= 5; j++) {
//            rekomendasiData.add(new RekomendasiModel("Umroh Free Tour Istanbul Turkey",
//                    "1",
//                    "Mitra Usaha : PT. SUKA JADI",
//                    "Rp 20.000.000",
//                    "Cicilan 60 bulan Rp 900.000/bulan",
//                    "1"));
//        }
//
//        for (int j = 1; j <= 5; j++) {
//            partnerData.add(new PartnerModel("1",
//                    "1"));
//        }
//
//    }

    private boolean validateForm(String s_area, String s_jaminan, String s_tenor, String s_harga) {
        if (s_harga == null || s_harga.trim().length() == 0 || s_harga.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Masukan jumlah pinjaman");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(harga_simulasi);
                    harga_simulasi.performClick();
                }
            });
            alertDialog.show();
            return false;
        }
        if (s_jaminan == null || s_jaminan.trim().length() == 0 || s_jaminan.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Pilih jaminan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerJaminan);
                    spinnerJaminan.performClick();
                }
            });
            alertDialog.show();
            return false;
        }

        if (s_tenor == null || s_tenor.trim().length() == 0 || s_tenor.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Pilih tenor");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerTenor);
                    spinnerTenor.performClick();
                }
            });
            alertDialog.show();
            return false;
        }
        if (s_area == null || s_area.trim().length() == 0 || s_area.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Pilih area");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerArea);
                    spinnerArea.performClick();
                }
            });
            alertDialog.show();
            return false;
        }





        return true;
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void setCurrency(final EditText edt) {
        edt.addTextChangedListener(new TextWatcher() {
            private String current = "";

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(current)) {
                    edt.removeTextChangedListener(this);

                    Locale local = new Locale("id", "id");
                    String replaceable = String.format("[Rp,.\\s]",
                            NumberFormat.getCurrencyInstance().getCurrency()
                                    .getSymbol(local));
                    String cleanString = s.toString().replaceAll(replaceable,
                            "");

                    double parsed;
                    try {
                        parsed = Double.parseDouble(cleanString);
                    } catch (NumberFormatException e) {
                        parsed = 0.00;
                    }

                    NumberFormat formatter = NumberFormat
                            .getCurrencyInstance(local);
                    formatter.setMaximumFractionDigits(0);
                    formatter.setParseIntegerOnly(true);
                    String formatted = formatter.format((parsed));

                    String replace = String.format("[Rp\\s]",
                            NumberFormat.getCurrencyInstance().getCurrency()
                                    .getSymbol(local));
                    String clean = formatted.replaceAll(replace, "");

                    current = formatted;
                    edt.setText(clean);
                    edt.setSelection(clean.length());
                    edt.addTextChangedListener(this);
                }
            }
        });
    }

    @Override
    public void onStop() {
//        mDemoSlider.stopAutoCycle();
        super.onStop();
    }
    @Override
    public void onSliderClick(BaseSliderView slider) {
//        Intent intent = new Intent(getContext(), PromoActivity.class);
//        intent.putExtra("ID",slider.getBundle().get("link").toString());
//        startActivity(intent);


        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(slider.getBundle().get("link").toString()));
        startActivity(browserIntent);
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
                RetrofitClient.getClient().create(InterfaceSimulationProcess.class);

        Call<Simulation> call = interfaceSimulationProcess.assign(area,jaminan, harga, tenor);
        call.enqueue(new Callback<Simulation>() {
            @Override
            public void onResponse(Call<Simulation> call, Response<Simulation> response) {
                Simulation simulation = response.body();
                progress.dismiss();
                Intent intent = new Intent(getContext(), SimulasiActivity.class);
                intent.putExtra("HARGA_SIMULASI", harga_simulasi.getText().toString().replace(".",""));
                intent.putExtra("JAMINAN",JAMINAN_DATA.get(jaminan_value));
                intent.putExtra("TENOR",TENOR_DATA.get(tenor_value));
                intent.putExtra("AREAREQUEST",AREA_DATA.get(area_value));
                intent.putExtra("HASIL",simulation.getInstallmentAmount().toString());

                intent.putExtra("text_harga", harga_simulasi.getText().toString().replace(".",""));
                intent.putExtra("spinner_jaminan",String.valueOf(spinnerJaminan.getSelectedItemPosition()));
                intent.putExtra("spinner_tenor",String.valueOf(spinnerTenor.getSelectedItemPosition()));
                intent.putExtra("spinner_area",String.valueOf(spinnerArea.getSelectedItemPosition()));
//                Toast.makeText(getContext(),"area : " + AREA_DATA.get(area_value) + " kode_area : " + spinnerArea.getSelectedItemPosition(),Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Simulation> call, Throwable t) {

            }
        });
    }

}
