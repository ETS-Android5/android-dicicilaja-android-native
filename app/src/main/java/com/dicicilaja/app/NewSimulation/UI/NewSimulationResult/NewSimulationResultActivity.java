package com.dicicilaja.app.NewSimulation.UI.NewSimulationResult;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.Activity.AjukanPengajuanAxiActivity;
import com.dicicilaja.app.NewSimulation.Data.HitungSimulasi.HitungSimulasi;
import com.dicicilaja.app.NewSimulation.Network.ApiClient;
import com.dicicilaja.app.NewSimulation.Network.ApiService;
import com.dicicilaja.app.NewSimulation.UI.BantuanNewSimulation.BantuanNewSimulationActivity;
import com.dicicilaja.app.NewSimulation.UI.NewSimulation.NewSimulationActivity;
import com.dicicilaja.app.R;
import com.google.android.material.textfield.TextInputLayout;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewSimulationResultActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.angsuran)
    TextView angsuran;
    @BindView(R.id.tenor_angsuran)
    TextView tenorAngsuran;
    @BindView(R.id.colleteral)
    TextView colleteral;
    @BindView(R.id.merk)
    TextView merk;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.year)
    TextView year;
    @BindView(R.id.insurance)
    TextView insurance;
    @BindView(R.id.area)
    TextView area;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.simulation)
    TextView simulation;

    String text_total_prefix, text_max_prefix, tipe_objek_id, area_id, tahun_kendaraan, objek_model_id, tenor_simulasi, tipe_asuransi_id, tipe_angsuran_id, max_simulasi, value_tipe_angsuran_id;
    int text_total, text_max;
    String spinner_jaminan, text_tenor, text_angsuran, text_tenor_angsuran, text_colleteral, text_merk, text_type, text_year, text_insurance, text_area, text_angsuran_baru;
    @BindView(R.id.asuransi_card)
    LinearLayout asuransiCard;
    @BindView(R.id.call_tasya)
    FrameLayout callTasya;
    boolean first = false;
    int check;

    final List<String> TENOR_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> TENOR_MAP = new HashMap<Integer, String>();
    @BindView(R.id.edit)
    TextView edit;
    @BindView(R.id.max_simulasi)
    TextView maxSimulasi;
    @BindView(R.id.spinnerTenor)
    AppCompatSpinner spinnerTenor;
    @BindView(R.id.layoutTenor)
    TextInputLayout layoutTenor;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;
    @BindView(R.id.total_edit)
    EditText totalEdit;
    @BindView(R.id.show)
    RelativeLayout show;
    @BindView(R.id.update)
    TextView update;
    @BindView(R.id.hide)
    RelativeLayout hide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_simulation_result);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Hasil Simulasi");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        TENOR_MAP.clear();
        TENOR_ITEMS.clear();

        TENOR_MAP.put(1, "1");
        TENOR_MAP.put(2, "2");
        TENOR_MAP.put(3, "3");
        TENOR_MAP.put(4, "4");

        TENOR_ITEMS.add("1 Tahun");
        TENOR_ITEMS.add("2 Tahun");
        TENOR_ITEMS.add("3 Tahun");
        TENOR_ITEMS.add("4 Tahun");

        MySpinnerAdapter tenor_adapter = new MySpinnerAdapter(getBaseContext(), R.layout.spinner_color, TENOR_ITEMS);
        tenor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTenor.setAdapter(tenor_adapter);

        LoadData();
        UpdateData();

        totalEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                totalEdit.removeTextChangedListener(this);
                try {
                    String rp = getResources().getString(R.string.rp_string);
                    String originalString = editable.toString();
                    originalString = originalString.replaceAll("\\.", "").replaceFirst(",", ".");
                    originalString = originalString.replaceAll("[A-Z]", "").replaceAll("[a-z]", "");
                    Double doubleval = Double.parseDouble(originalString);
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(',');
                    symbols.setGroupingSeparator('.');
                    String pattern = "#,###.##";
                    DecimalFormat formatter = new DecimalFormat(pattern, symbols);
                    String formattedString = rp + formatter.format(doubleval);
                    totalEdit.setText(formattedString);
                    totalEdit.setSelection(totalEdit.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
                totalEdit.addTextChangedListener(this);
            }
        });

        spinnerTenor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (++check > 1) {
                    if(text_colleteral.equals("Motor")) {
                        progressBar.setVisibility(View.VISIBLE);
                        ApiService apiService = ApiClient.getClient().create(ApiService.class);

                        int simulasi_convert = Integer.parseInt(TENOR_MAP.get(spinnerTenor.getSelectedItemPosition() + 1));
                        tenor_simulasi = String.valueOf(simulasi_convert);
                        String value = totalEdit.getText().toString().replace(".", "").replace("Rp","").replace(",", "");
                        int total_edit_value = Integer.parseInt(value);

                        Log.d("TAGTAG", "tipe_objek_id: " + tipe_objek_id);
                        Log.d("TAGTAG", "objek_model_id: " + objek_model_id);
                        Log.d("TAGTAG", "tahun_kendaraan: " + tahun_kendaraan);
                        Log.d("TAGTAG", "area_id: " + area_id);
                        Log.d("TAGTAG", "tenor_simulasi: " + tenor_simulasi);
                        Log.d("TAGTAG", "total_edit_value: " + total_edit_value);

                        Call<HitungSimulasi> call = apiService.reHitungMcy(tipe_objek_id, objek_model_id, tahun_kendaraan, area_id, tenor_simulasi, String.valueOf(total_edit_value));
                        call.enqueue(new Callback<HitungSimulasi>() {

                            @Override
                            public void onResponse(Call<HitungSimulasi> call, Response<HitungSimulasi> response) {
                                if (response.isSuccessful()) {
                                    try {
                                        progressBar.setVisibility(View.GONE);
                                        text_angsuran = response.body().getData().getAttributes().getHasilSimulasi().getAngsuranPerBulanPrefix();
                                        text_tenor_angsuran = "x " + response.body().getData().getAttributes().getInformasiJaminan().getTenor() + " Bulan";
                                        text_total_prefix = response.body().getData().getAttributes().getHasilSimulasi().getDanaDiterimaPrefix();
                                        text_total = response.body().getData().getAttributes().getHasilSimulasi().getDanaDiterima();
                                        text_max_prefix = response.body().getData().getAttributes().getHasilSimulasi().getMaksPencairanPrefix();
                                        text_max = response.body().getData().getAttributes().getHasilSimulasi().getMaksPencairan();
                                        text_tenor = String.valueOf(response.body().getData().getAttributes().getInformasiJaminan().getTenor());

                                        Log.d("TAGTAGTAG", "text_angsuran: " + text_angsuran);
                                        Log.d("TAGTAGTAG", "text_tenor_angsuran: " + text_tenor_angsuran);
                                        UpdateData();

                                    } catch (Exception ex) {
                                        progressBar.setVisibility(View.GONE);
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                                        alertDialog.setTitle("Perhatian");
                                        alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        });
                                        alertDialog.show();
                                    }
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                                    alertDialog.setTitle("Perhatian");
                                    alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                                    alertDialog.show();
                                }
                            }

                            @Override
                            public void onFailure(Call<HitungSimulasi> call, Throwable t) {
                                progressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                                alertDialog.setTitle("Perhatian");
                                alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                alertDialog.show();
                            }
                        });
                    } else if(text_colleteral.equals("Mobil")) {
                        progressBar.setVisibility(View.VISIBLE);
                        ApiService apiService = ApiClient.getClient().create(ApiService.class);

                        int simulasi_convert = Integer.parseInt(TENOR_MAP.get(spinnerTenor.getSelectedItemPosition() + 1));
                        tenor_simulasi = String.valueOf(simulasi_convert);
                        String value = totalEdit.getText().toString().replace(".", "").replace("Rp","").replace(",", "");
                        int total_edit_value = Integer.parseInt(value);

                        Log.d("TAGTAG", "tipe_objek_id: " + tipe_objek_id);
                        Log.d("TAGTAG", "objek_model_id: " + objek_model_id);
                        Log.d("TAGTAG", "tahun_kendaraan: " + tahun_kendaraan);
                        Log.d("TAGTAG", "area_id: " + area_id);
                        Log.d("TAGTAG", "tenor_simulasi: " + tenor_simulasi);
                        Log.d("TAGTAG", "tipe_asuransi_id: " + tipe_asuransi_id);
                        Log.d("TAGTAG", "value_tipe_angsuran_id: " + value_tipe_angsuran_id);
                        Log.d("TAGTAG", "total_edit_value: " + total_edit_value);

                        Call<HitungSimulasi> call = apiService.reHitungCar(tipe_objek_id, objek_model_id, tahun_kendaraan, area_id, tenor_simulasi, tipe_asuransi_id, value_tipe_angsuran_id, String.valueOf(total_edit_value));
                        call.enqueue(new Callback<HitungSimulasi>() {

                            @Override
                            public void onResponse(Call<HitungSimulasi> call, Response<HitungSimulasi> response) {
                                if (response.isSuccessful()) {
                                    try {
                                        progressBar.setVisibility(View.GONE);
                                        text_angsuran = response.body().getData().getAttributes().getHasilSimulasi().getAngsuranPerBulanPrefix();
                                        text_tenor_angsuran = "x " + response.body().getData().getAttributes().getInformasiJaminan().getTenor() + " Bulan";
                                        text_total_prefix = response.body().getData().getAttributes().getHasilSimulasi().getDanaDiterimaPrefix();
                                        text_total = response.body().getData().getAttributes().getHasilSimulasi().getDanaDiterima();
                                        text_max_prefix = response.body().getData().getAttributes().getHasilSimulasi().getMaksPencairanPrefix();
                                        text_max = response.body().getData().getAttributes().getHasilSimulasi().getMaksPencairan();
                                        text_tenor = String.valueOf(response.body().getData().getAttributes().getInformasiJaminan().getTenor());

                                        Log.d("TAGTAGTAG", "text_angsuran: " + text_angsuran);
                                        Log.d("TAGTAGTAG", "text_tenor_angsuran: " + text_tenor_angsuran);
                                        UpdateData();

                                    } catch (Exception ex) {
                                        progressBar.setVisibility(View.GONE);
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                                        alertDialog.setTitle("Perhatian");
                                        alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        });
                                        alertDialog.show();
                                    }
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                                    alertDialog.setTitle("Perhatian");
                                    alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                                    alertDialog.show();
                                }
                            }

                            @Override
                            public void onFailure(Call<HitungSimulasi> call, Throwable t) {
                                progressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                                alertDialog.setTitle("Perhatian");
                                alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                alertDialog.show();
                            }
                        });
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide.setVisibility(View.VISIBLE);
                show.setVisibility(View.GONE);
                totalEdit.requestFocus();
                totalEdit.setSelection(totalEdit.getText().length());
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(totalEdit, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                if (text_colleteral.equals("Motor")) {
                    String value = totalEdit.getText().toString().replace(".", "").replace("Rp", "").replace(",", "");
                    int total_edit_value = Integer.parseInt(value);
                    Log.d("TAGTAGTAG", "text_total: " + total_edit_value);
                    Log.d("TAGTAGTAG", "text_max: " + text_max);
                    if(total_edit_value < 3000000) {
                        progressBar.setVisibility(View.GONE);
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Jumlah pinjaman kurang dari nilai minimum pinjaman.\n\nNilai minimum pinjaman Rp3.000.000");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(totalEdit, InputMethodManager.SHOW_IMPLICIT);
                            }
                        });
                        alertDialog.show();
                    } else if(total_edit_value > text_max) {
                        progressBar.setVisibility(View.GONE);
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Jumlah pinjaman melebihi nilai maksimum pinjaman.\n\nNilai maksimum pinjaman " + text_max_prefix);

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(totalEdit, InputMethodManager.SHOW_IMPLICIT);
                            }
                        });
                        alertDialog.show();
                    } else {
                        ApiService apiService = ApiClient.getClient().create(ApiService.class);

                        int simulasi_convert = Integer.parseInt(TENOR_MAP.get(spinnerTenor.getSelectedItemPosition() + 1));
                        tenor_simulasi = String.valueOf(simulasi_convert);

                        Log.d("TAGTAG", "tipe_objek_id: " + tipe_objek_id);
                        Log.d("TAGTAG", "objek_model_id: " + objek_model_id);
                        Log.d("TAGTAG", "tahun_kendaraan: " + tahun_kendaraan);
                        Log.d("TAGTAG", "area_id: " + area_id);
                        Log.d("TAGTAG", "tenor_simulasi: " + tenor_simulasi);
                        Log.d("TAGTAG", "total_edit_value: " + total_edit_value);

                        Call<HitungSimulasi> call = apiService.reHitungMcy(tipe_objek_id, objek_model_id, tahun_kendaraan, area_id, tenor_simulasi, String.valueOf(total_edit_value));
                        call.enqueue(new Callback<HitungSimulasi>() {

                            @Override
                            public void onResponse(Call<HitungSimulasi> call, Response<HitungSimulasi> response) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                hide.setVisibility(View.GONE);
                                show.setVisibility(View.VISIBLE);
                                if (response.isSuccessful()) {
                                    try {
                                        progressBar.setVisibility(View.GONE);
                                        text_angsuran = response.body().getData().getAttributes().getHasilSimulasi().getAngsuranPerBulanPrefix();
                                        text_tenor_angsuran = "x " + response.body().getData().getAttributes().getInformasiJaminan().getTenor() + " Bulan";
                                        text_total_prefix = response.body().getData().getAttributes().getHasilSimulasi().getDanaDiterimaPrefix();
                                        text_total = response.body().getData().getAttributes().getHasilSimulasi().getDanaDiterima();
                                        text_max_prefix = response.body().getData().getAttributes().getHasilSimulasi().getMaksPencairanPrefix();
                                        text_max = response.body().getData().getAttributes().getHasilSimulasi().getMaksPencairan();
                                        text_tenor = String.valueOf(response.body().getData().getAttributes().getInformasiJaminan().getTenor());

                                        Log.d("TAGTAGTAG", "text_angsuran: " + text_angsuran);
                                        Log.d("TAGTAGTAG", "text_tenor_angsuran: " + text_tenor_angsuran);
                                        UpdateData();

                                    } catch (Exception ex) {
                                        progressBar.setVisibility(View.GONE);
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                                        alertDialog.setTitle("Perhatian");
                                        alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        });
                                        alertDialog.show();
                                    }
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                                    alertDialog.setTitle("Perhatian");
                                    alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                                    alertDialog.show();
                                }

                            }

                            @Override
                            public void onFailure(Call<HitungSimulasi> call, Throwable t) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                hide.setVisibility(View.GONE);
                                show.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                                alertDialog.setTitle("Perhatian");
                                alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                alertDialog.show();
                            }
                        });
                    }

                } else if (text_colleteral.equals("Mobil")) {
                    String value = totalEdit.getText().toString().replace(".", "").replace("Rp", "").replace(",", "");
                    int total_edit_value = Integer.parseInt(value);
                    Log.d("TAGTAGTAG", "text_total: " + total_edit_value);
                    Log.d("TAGTAGTAG", "text_max: " + text_max);
                    if(total_edit_value < 20000000) {
                        progressBar.setVisibility(View.GONE);
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Jumlah pinjaman kurang dari nilai minimum pinjaman.\n\nNilai minimum pinjaman Rp20.000.000");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(totalEdit, InputMethodManager.SHOW_IMPLICIT);
                            }
                        });
                        alertDialog.show();
                    } else if(total_edit_value > text_max) {
                        progressBar.setVisibility(View.GONE);
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Jumlah pinjaman melebihi nilai maksimum pinjaman.\n\nNilai maksimum pinjaman " + text_max_prefix);

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(totalEdit, InputMethodManager.SHOW_IMPLICIT);
                            }
                        });
                        alertDialog.show();
                    } else {
                        ApiService apiService = ApiClient.getClient().create(ApiService.class);

                        int simulasi_convert = Integer.parseInt(TENOR_MAP.get(spinnerTenor.getSelectedItemPosition() + 1));
                        tenor_simulasi = String.valueOf(simulasi_convert);

                        Log.d("TAGTAG", "tipe_objek_id: " + tipe_objek_id);
                        Log.d("TAGTAG", "objek_model_id: " + objek_model_id);
                        Log.d("TAGTAG", "tahun_kendaraan: " + tahun_kendaraan);
                        Log.d("TAGTAG", "area_id: " + area_id);
                        Log.d("TAGTAG", "tenor_simulasi: " + tenor_simulasi);
                        Log.d("TAGTAG", "tipe_asuransi_id: " + tipe_asuransi_id);
                        Log.d("TAGTAG", "value_tipe_angsuran_id: " + value_tipe_angsuran_id);
                        Log.d("TAGTAG", "total_edit_value: " + total_edit_value);

                        Call<HitungSimulasi> call = apiService.reHitungCar(tipe_objek_id, objek_model_id, tahun_kendaraan, area_id, tenor_simulasi, tipe_asuransi_id, value_tipe_angsuran_id, String.valueOf(total_edit_value));
                        call.enqueue(new Callback<HitungSimulasi>() {

                            @Override
                            public void onResponse(Call<HitungSimulasi> call, Response<HitungSimulasi> response) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                hide.setVisibility(View.GONE);
                                show.setVisibility(View.VISIBLE);
                                if (response.isSuccessful()) {
                                    try {
                                        progressBar.setVisibility(View.GONE);
                                        text_angsuran = response.body().getData().getAttributes().getHasilSimulasi().getAngsuranPerBulanPrefix();
                                        text_tenor_angsuran = "x " + response.body().getData().getAttributes().getInformasiJaminan().getTenor() + " Bulan";
                                        text_total_prefix = response.body().getData().getAttributes().getHasilSimulasi().getDanaDiterimaPrefix();
                                        text_total = response.body().getData().getAttributes().getHasilSimulasi().getDanaDiterima();
                                        text_max_prefix = response.body().getData().getAttributes().getHasilSimulasi().getMaksPencairanPrefix();
                                        text_max = response.body().getData().getAttributes().getHasilSimulasi().getMaksPencairan();
                                        text_tenor = String.valueOf(response.body().getData().getAttributes().getInformasiJaminan().getTenor());

                                        Log.d("TAGTAGTAG", "text_angsuran: " + text_angsuran);
                                        Log.d("TAGTAGTAG", "text_tenor_angsuran: " + text_tenor_angsuran);
                                        UpdateData();

                                    } catch (Exception ex) {
                                        progressBar.setVisibility(View.GONE);
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                                        alertDialog.setTitle("Perhatian");
                                        alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        });
                                        alertDialog.show();
                                    }
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                                    alertDialog.setTitle("Perhatian");
                                    alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                                    alertDialog.show();
                                }

                            }

                            @Override
                            public void onFailure(Call<HitungSimulasi> call, Throwable t) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                hide.setVisibility(View.GONE);
                                show.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                                alertDialog.setTitle("Perhatian");
                                alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                alertDialog.show();
                            }
                        });
                    }

                }
            }
        });

    }

    private void LoadData() {
        try {
            text_max = getIntent().getIntExtra("text_max", 0);
            text_max_prefix = getIntent().getStringExtra("text_max_prefix");
            text_total = getIntent().getIntExtra("text_total", 0);
            text_total_prefix = getIntent().getStringExtra("text_total_prefix");
            text_tenor = getIntent().getStringExtra("text_tenor");
            text_angsuran = getIntent().getStringExtra("text_angsuran");
            text_tenor_angsuran = getIntent().getStringExtra("text_tenor_angsuran");
            text_colleteral = getIntent().getStringExtra("text_colleteral");
            text_merk = getIntent().getStringExtra("text_merk");
            text_type = getIntent().getStringExtra("text_type");
            text_year = getIntent().getStringExtra("text_year");
            text_insurance = getIntent().getStringExtra("text_insurance");
            text_angsuran_baru = getIntent().getStringExtra("text_angsuran_baru");
            text_area = getIntent().getStringExtra("text_area");

            area_id = getIntent().getStringExtra("area_id");
            tipe_objek_id = getIntent().getStringExtra("tipe_objek_id");
            objek_model_id = getIntent().getStringExtra("objek_model_id");
            tahun_kendaraan = getIntent().getStringExtra("tahun_kendaraan");
            if (check == 0) {
                tenor_simulasi = getIntent().getStringExtra("tenor_simulasi");
            }

            tipe_asuransi_id = getIntent().getStringExtra("tipe_asuransi_id");
            value_tipe_angsuran_id = getIntent().getStringExtra("value_tipe_angsuran_id");

        } catch (Exception ex) {

        }

        if (text_colleteral.equals("Motor")) {
            asuransiCard.setVisibility(View.GONE);
        }
    }

    private void UpdateData() {
        maxSimulasi.setText("Max Pinjaman : " + text_max_prefix);
        total.setText(text_total_prefix);
        totalEdit.setText(text_total_prefix);
        angsuran.setText(text_angsuran);
        tenorAngsuran.setText(text_tenor_angsuran);
        colleteral.setText(text_colleteral);
        merk.setText(text_merk);
        type.setText(text_type);
        year.setText(text_year);
        insurance.setText(text_insurance);
        area.setText(text_area);

        if (check == 0) {
            if (text_tenor.equals("12")) {
                spinnerTenor.setSelection(0);
            } else if (text_tenor.equals("24")) {
                spinnerTenor.setSelection(1);
            } else if (text_tenor.equals("36")) {
                spinnerTenor.setSelection(2);
            } else if (text_tenor.equals("48")) {
                spinnerTenor.setSelection(3);
            }
        }


        if (text_colleteral.equals("Mobil")) {
            spinner_jaminan = "1";
        } else if (text_colleteral.equals("Motor")) {
            spinner_jaminan = "2";
        }
    }

    @OnClick(R.id.update)
    public void onViewClicked() {
    }

    private static class MySpinnerAdapter extends ArrayAdapter<String> {
        // Initialise custom font, for example:
        Typeface font = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/OpenSans-Bold.ttf");
        Typeface font2 = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/OpenSans-SemiBold.ttf");

        // (In reality I used a manager which caches the Typeface objects)
        // Typeface font = FontManager.getInstance().getFont(getContext(), BLAMBOT);

        private MySpinnerAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
        }

        // Affects default (closed) state of the spinner
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setPadding(0, 0, 0, 15);
            view.setTypeface(font);
            return view;
        }

        // Affects opened state of the spinner
        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getDropDownView(position, convertView, parent);

            view.setTypeface(font);
            view.setPadding(70, 5, 0, 5);
            view.setTextSize(18);
            view.setTextColor(getContext().getResources().getColor(R.color.colorAccentDark2));
//            final float scale = getContext().getResources().getDisplayMetrics().density;
//            int px = (int) (10 * scale + 0.5f);
//            view.setPadding(px, px, px, px);
            return view;
        }
    }

    @OnClick({R.id.call_tasya, R.id.next, R.id.simulation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.call_tasya:
                if (text_colleteral.equals("Mobil")) {
                    if (area_id.equals("9")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628119125005&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan" + "\nJenis Angsuran : " + text_angsuran_baru + "\nTipe Asuransi : " + text_insurance));
                        startActivity(browserIntent);
                    } else if (area_id.equals("10")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628111475505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan" + "\nJenis Angsuran : " + text_angsuran_baru + "\nTipe Asuransi : " + text_insurance));
                        startActivity(browserIntent);
                    } else if (area_id.equals("11")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628119555051&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan" + "\nJenis Angsuran : " + text_angsuran_baru + "\nTipe Asuransi : " + text_insurance));
                        startActivity(browserIntent);
                    } else if (area_id.equals("12")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628118845505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan" + "\nJenis Angsuran : " + text_angsuran_baru + "\nTipe Asuransi : " + text_insurance));
                        startActivity(browserIntent);
                    } else if (area_id.equals("13")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628118845505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : Bali %26 Nusa Tenggara\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan" + "\nJenis Angsuran : " + text_angsuran_baru + "\nTipe Asuransi : " + text_insurance));
                        startActivity(browserIntent);
                    } else if (area_id.equals("14")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628119555051&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan" + "\nJenis Angsuran : " + text_angsuran_baru + "\nTipe Asuransi : " + text_insurance));
                        startActivity(browserIntent);
                    } else if (area_id.equals("15")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628111475505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan" + "\nJenis Angsuran : " + text_angsuran_baru + "\nTipe Asuransi : " + text_insurance));
                        startActivity(browserIntent);
                    } else if (area_id.equals("16")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628119555051&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan" + "\nJenis Angsuran : " + text_angsuran_baru + "\nTipe Asuransi : " + text_insurance));
                        startActivity(browserIntent);
                    } else if (area_id.equals("17")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628118845505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan" + "\nJenis Angsuran : " + text_angsuran_baru + "\nTipe Asuransi : " + text_insurance));
                        startActivity(browserIntent);
                    }
                } else if (text_colleteral.equals("Motor")) {
                    if (area_id.equals("9")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628119125005&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("10")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628111475505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("11")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628119555051&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("12")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628118845505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("13")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628118845505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : Bali %26 Nusa Tenggara\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("14")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628119555051&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("15")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628111475505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("16")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628119555051&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("17")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628118845505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_type + "\nTahun Kendaraan : " + text_year + "\nTenor Pinjaman : " + text_tenor + " Bulan"));
                        startActivity(browserIntent);
                    }
                }
                break;
            case R.id.next:
                Intent intent2 = new Intent(getBaseContext(), AjukanPengajuanAxiActivity.class);
                intent2.putExtra("text_tenor", String.valueOf(text_tenor));
                intent2.putExtra("text_year", String.valueOf(text_year));
                intent2.putExtra("text_area", String.valueOf(text_area));
                intent2.putExtra("text_harga", String.valueOf(text_total));
                intent2.putExtra("text_merk", text_merk + " " + text_type);
                intent2.putExtra("spinner_jaminan", spinner_jaminan);
                startActivity(intent2);
                break;
            case R.id.simulation:
                Intent intent3 = new Intent(getBaseContext(), NewSimulationActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent3);
                finish();
                break;
            case R.id.edit:

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simulasi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.help:
                Intent intent = new Intent(getBaseContext(), BantuanNewSimulationActivity.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                super.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
