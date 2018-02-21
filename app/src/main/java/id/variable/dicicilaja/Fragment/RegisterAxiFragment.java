package id.variable.dicicilaja.Fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import id.variable.dicicilaja.Model.Branch;
import id.variable.dicicilaja.Model.Area;
import id.variable.dicicilaja.Activity.HelpActivity;
import id.variable.dicicilaja.Activity.LoginActivity;
import id.variable.dicicilaja.Activity.RegisterAxi2Activity;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Remote.ApiUtils;
import id.variable.dicicilaja.Remote.AreaService;
import id.variable.dicicilaja.WebView.AboutAxiActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
  */
public class RegisterAxiFragment extends Fragment {

    TextView titleSection, bodySection, detailSection, sudahPunyaAkun, judulSudahPunyaAkun;
    EditText inputReferal, inputNama, inputEmail, inputHandphone, inputIbu;
    Button btnLanjut, btnDaftar;
    MaterialSpinner spinnerArea, spinnerCabang;
    AreaService AreaService;

    public RegisterAxiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register_axi, container, false);
        inputNama = (EditText) view.findViewById(R.id.inputNama);
        inputEmail = (EditText) view.findViewById(R.id.inputEmail);
        inputIbu = (EditText) view.findViewById(R.id.inputIbu);
        inputReferal = view.findViewById(R.id.inputReferal);
        inputHandphone = (EditText) view.findViewById(R.id.inputHandphone);
        sudahPunyaAkun = (TextView) view.findViewById(R.id.sudahPunyaAkun);
        judulSudahPunyaAkun = (TextView) view.findViewById(R.id.judulSudahPunyaAkun);
        titleSection = (TextView) view.findViewById(R.id.titleSection);
        bodySection = (TextView) view.findViewById(R.id.bodySection);
        detailSection = (TextView) view.findViewById(R.id.detailSection);
        btnLanjut = view.findViewById(R.id.btnLanjut);
        btnDaftar = view.findViewById(R.id.btnDaftar);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        final Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        inputNama.setTypeface(opensans_semibold);
        inputEmail.setTypeface(opensans_semibold);
        inputHandphone.setTypeface(opensans_semibold);
        inputIbu.setTypeface(opensans_semibold);
        inputReferal.setTypeface(opensans_semibold);
        judulSudahPunyaAkun.setTypeface(opensans_reguler);
        sudahPunyaAkun.setTypeface(opensans_semibold);
        titleSection.setTypeface(opensans_bold);
        bodySection.setTypeface(opensans_reguler);
        detailSection.setTypeface(opensans_semibold);
        btnLanjut.setTypeface(opensans_bold);
        sudahPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RegisterAxi2Activity.class);
                startActivity(intent);
            }
        });
        detailSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AboutAxiActivity.class);
                startActivity(intent);
            }
        });

//        btnLanjut.setEnabled(false);

        // btnLanjut.setEnabled(false);

        final List<String> AREA_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> AREA_MAP = new HashMap<Integer, String>();

        final List<String> CABANG_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> CABANG_MAP = new HashMap<Integer, String>();

        AreaService = ApiUtils.getAreaService();

        Call<List<Area>> call = AreaService.getArea();
        call.enqueue(new Callback<List<Area>>() {
            @Override
            public void onResponse(Call<List<Area>> call, Response<List<Area>> response) {

                AREA_MAP.clear();
                AREA_ITEMS.clear();

                for ( int i = 0; i < response.body().size(); i++ ) {
                    AREA_MAP.put(response.body().get(i).getId(), response.body().get(i).getId().toString());
                    AREA_ITEMS.add(response.body().get(i).getName());
                }

            }

            @Override
            public void onFailure(Call<List<Area>> call, Throwable t) {
                AREA_MAP.clear();
                AREA_ITEMS.clear();
                Log.e("Error", t.getMessage());
            }
        });

        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerArea = (MaterialSpinner) view.findViewById(R.id.spinnerArea);
        spinnerArea.setAdapter(area_adapter);
        spinnerArea.setTypeface(opensans_semibold);

        spinnerCabang = (MaterialSpinner) view.findViewById(R.id.spinnerCabang);
        spinnerCabang.setEnabled(false);

        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Call<List<Branch>> callBranch = AreaService.getBranch(AREA_MAP.get(spinnerArea.getSelectedItemPosition()));
                callBranch.enqueue(new Callback<List<Branch>>() {
                    @Override
                    public void onResponse(Call<List<Branch>> call, Response<List<Branch>> response) {

                        CABANG_MAP.clear();
                        CABANG_ITEMS.clear();

                        for ( int i = 0; i < response.body().size(); i++ ) {
                            CABANG_MAP.put(response.body().get(i).getId(), response.body().get(i).getName());
                            CABANG_ITEMS.add(response.body().get(i).getName());
                        }

                        ArrayAdapter<String> branch_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, CABANG_ITEMS);
                        branch_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinnerCabang.setEnabled(true);
                        spinnerCabang.setAdapter(branch_adapter);
                        spinnerCabang.setTypeface(opensans_semibold);
                    }

                    @Override
                    public void onFailure(Call<List<Branch>> call, Throwable t) {
                        CABANG_MAP.clear();
                        CABANG_ITEMS.clear();
                        spinnerCabang.setEnabled(false);
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                AREA_ITEMS.clear();
                AREA_MAP.clear();
                CABANG_MAP.clear();
                CABANG_ITEMS.clear();
                spinnerCabang.setEnabled(false);
            }

        });

        return view;
    }

}
