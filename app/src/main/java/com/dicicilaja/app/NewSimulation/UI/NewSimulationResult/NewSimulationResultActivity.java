package com.dicicilaja.app.NewSimulation.UI.NewSimulationResult;

import android.app.ProgressDialog;
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

import com.dicicilaja.app.NewSimulation.Data.HitungSimulasi.HitungSimulasi;
import com.dicicilaja.app.NewSimulation.Network.ApiClient;
import com.dicicilaja.app.NewSimulation.Network.ApiService;
import com.dicicilaja.app.NewSimulation.UI.BantuanNewSimulation.BantuanNewSimulationActivity;
import com.dicicilaja.app.NewSimulation.UI.NewSimulation.NewSimulationActivity;
import com.dicicilaja.app.OrderIn.Data.Axi.Axi;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiService3;
import com.dicicilaja.app.OrderIn.UI.OrderInActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
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

    SessionManager sessionUser;

    String apiKey, text_total_prefix, jaminan, tipe_angsuran, tipe_asuransi, text_max_prefix, tipe_objek_id, objek_brand_id, area_id, tahun_kendaraan, objek_model_id, tenor_simulasi, tipe_asuransi_id, tipe_angsuran_id, max_simulasi, value_tipe_angsuran_id;
    int text_total, text_max;
    String agen_id, agen_name, spinner_jaminan, text_tenor, text_angsuran, text_tenor_angsuran, text_colleteral, text_merk, text_type, text_year, text_insurance, text_area, text_angsuran_baru;
    @BindView(R.id.asuransi_card)
    LinearLayout asuransiCard;
    @BindView(R.id.call_tasya)
    FrameLayout callTasya;
    boolean first = false;
    int check;

    final List<String> TENOR_ITEMS = new ArrayList<>();
    final List<String> TENOR_DATA = new ArrayList<>();
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

    ApiService3 apiService3;
    SessionManager session;

    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_simulation_result);
        ButterKnife.bind(this);

        apiService3 = ApiClient2.getClient().create(ApiService3.class);

        sessionUser = new SessionManager(NewSimulationResultActivity.this);
        session = new SessionManager(getBaseContext());

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

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang mengirim data...");
        progress.setCanceledOnTouchOutside(false);

        TENOR_MAP.clear();
        TENOR_ITEMS.clear();
        TENOR_DATA.clear();

        TENOR_MAP.put(1, "1");
        TENOR_MAP.put(2, "2");
        TENOR_MAP.put(3, "3");
        TENOR_MAP.put(4, "4");

        TENOR_ITEMS.add("1 Tahun");
        TENOR_ITEMS.add("2 Tahun");
        TENOR_ITEMS.add("3 Tahun");
        TENOR_ITEMS.add("4 Tahun");

        TENOR_DATA.add("12");
        TENOR_DATA.add("24");
        TENOR_DATA.add("36");
        TENOR_DATA.add("48");

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
                                        int tenor_addm = Integer.parseInt(response.body().getData().getAttributes().getInformasiJaminan().getTenor())-1;
                                        if (value_tipe_angsuran_id.equals("addm")) {
                                            text_tenor_angsuran = "x " + tenor_addm + " Bulan";
                                        } else {
                                            text_tenor_angsuran = "x " + response.body().getData().getAttributes().getInformasiJaminan().getTenor() + " Bulan";
                                        }
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
                                        int tenor_addm = Integer.parseInt(response.body().getData().getAttributes().getInformasiJaminan().getTenor())-1;
                                        if (value_tipe_angsuran_id.equals("addm")) {
                                            text_tenor_angsuran = "x " + tenor_addm + " Bulan";
                                        } else {
                                            text_tenor_angsuran = "x " + response.body().getData().getAttributes().getInformasiJaminan().getTenor() + " Bulan";
                                        }
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
                                alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi." + t.getMessage());

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
            objek_brand_id = getIntent().getStringExtra("objek_brand_id");

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
            tipe_angsuran_id = getIntent().getStringExtra("tipe_angsuran_id");
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

    @OnClick({R.id.call_tasya, R.id.next, R.id.simulation, R.id.edit})
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
                progress.show();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (spinner_jaminan.equals("1")) {
                    jaminan = "BPKB Mobil";
                } else if (spinner_jaminan.equals("2")) {
                    jaminan = "BPKB Motor";
                }

                if (tipe_angsuran_id != null) {
                    if (tipe_asuransi_id.equals("1")) {
                        tipe_asuransi = "Total Lost Only";
                    } else if (tipe_asuransi_id.equals("2")) {
                        tipe_asuransi = "All Risk";
                    }
                }

                if (tipe_angsuran_id != null) {
                    if (tipe_angsuran_id.equals("1")) {
                        tipe_angsuran = "Angsuran Per Bulan (ADDB)";
                    } else if (tipe_angsuran_id.equals("2")) {
                        tipe_angsuran = "Pembayaran Pertama (ADDM)";
                    }
                }

                Log.d("ORDERDONE", "jenis_pengajuan: " + "1");
                Log.d("ORDERDONE", "program_id: " + "1");
                Log.d("ORDERDONE", "product_id: " + "1");
                Log.d("ORDERDONE", "qty: " + "1");
                Log.d("ORDERDONE", "agen_id: " + sessionUser.getAxiId());
                Log.d("ORDERDONE", "amount: " + text_total);
                Log.d("ORDERDONE", "status_informasi_jaminan: " + true);
                Log.d("ORDERDONE", "jaminan_id: " + spinner_jaminan);
                Log.d("ORDERDONE", "area_id: " + area_id);
                Log.d("ORDERDONE", "area: " + text_area);
                Log.d("ORDERDONE", "objek_brand_id: " + objek_brand_id);
                Log.d("ORDERDONE", "brand: " + text_merk);
                Log.d("ORDERDONE", "objek_model_id: " + objek_model_id);
                Log.d("ORDERDONE", "model: " + text_type);
                Log.d("ORDERDONE", "year: " + text_year);
                Log.d("ORDERDONE", "tenor_simulasi_id: " + text_tenor);
                Log.d("ORDERDONE", "tipe_asuransi_id: " + tipe_asuransi_id);
                Log.d("ORDERDONE", "jenis_angsuran_id: " + tipe_angsuran_id);


                Call<Axi> axiReff = apiService3.getAxi(session.getAxiId());
                axiReff.enqueue(new Callback<Axi>() {
                    @Override
                    public void onResponse(Call<Axi> call, Response<Axi> response) {
                        if (response.isSuccessful()) {
                            try {
                                if (response.body().getData().size() > 0) {
                                    agen_id = response.body().getData().get(0).getAttributes().getNomorAxiId();
                                    agen_name = response.body().getData().get(0).getAttributes().getNama();
                                    Intent intent2 = new Intent(getBaseContext(), OrderInActivity.class);
                                    intent2.putExtra("amount", total.getText().toString());
                                    intent2.putExtra("max", String.valueOf(text_max));
                                    intent2.putExtra("max_prefix", text_max_prefix);
                                    intent2.putExtra("simulasi", "1");
                                    intent2.putExtra("jaminan_id", spinner_jaminan);
                                    intent2.putExtra("jaminan", jaminan);
                                    intent2.putExtra("area_id", area_id);
                                    intent2.putExtra("area", text_area);
                                    intent2.putExtra("objek_brand_id", objek_brand_id);
                                    intent2.putExtra("brand", text_merk);
                                    intent2.putExtra("objek_model_id", objek_model_id);
                                    intent2.putExtra("model", text_type);
                                    intent2.putExtra("year", text_year);
                                    intent2.putExtra("tenor_simulasi_id", text_tenor);
                                    intent2.putExtra("tipe_asuransi_id", tipe_asuransi_id);
                                    intent2.putExtra("tipe_asuransi", tipe_asuransi);
                                    intent2.putExtra("jenis_angsuran_id", tipe_angsuran_id);
                                    intent2.putExtra("jenis_angsuran", tipe_angsuran);
                                    intent2.putExtra("agen_id", agen_id);
                                    intent2.putExtra("agen_name", agen_name);
                                    startActivity(intent2);
                                    progress.hide();
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                } else {
                                    agen_id = null;
                                    agen_name = null;
                                    Intent intent2 = new Intent(getBaseContext(), OrderInActivity.class);
                                    intent2.putExtra("amount", total.getText().toString());
                                    intent2.putExtra("max", String.valueOf(text_max));
                                    intent2.putExtra("max_prefix", text_max_prefix);
                                    intent2.putExtra("simulasi", "1");
                                    intent2.putExtra("jaminan_id", spinner_jaminan);
                                    intent2.putExtra("jaminan", jaminan);
                                    intent2.putExtra("area_id", area_id);
                                    intent2.putExtra("area", text_area);
                                    intent2.putExtra("objek_brand_id", objek_brand_id);
                                    intent2.putExtra("brand", text_merk);
                                    intent2.putExtra("objek_model_id", objek_model_id);
                                    intent2.putExtra("model", text_type);
                                    intent2.putExtra("year", text_year);
                                    intent2.putExtra("tenor_simulasi_id", text_tenor);
                                    intent2.putExtra("tipe_asuransi_id", tipe_asuransi_id);
                                    intent2.putExtra("tipe_asuransi", tipe_asuransi);
                                    intent2.putExtra("jenis_angsuran_id", tipe_angsuran_id);
                                    intent2.putExtra("jenis_angsuran", tipe_angsuran);
                                    intent2.putExtra("agen_id", agen_id);
                                    intent2.putExtra("agen_name", agen_name);
                                    startActivity(intent2);
                                    progress.hide();
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                }


                            } catch (Exception ex) {
                            }
                        } else {
                            progress.hide();
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data axi gagal dipanggil, silahkan coba beberapa saat lagi.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Axi> call, Throwable t) {
                        progress.hide();
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewSimulationResultActivity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Data axi gagal dipanggil, silahkan coba beberapa saat lagi.");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(getIntent());
                            }
                        });
                        alertDialog.show();
                    }
                });

                break;
            case R.id.simulation:
                Intent intent3 = new Intent(getBaseContext(), NewSimulationActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent3);
                finish();
                break;
            case R.id.edit:
                hide.setVisibility(View.VISIBLE);
                show.setVisibility(View.GONE);
                totalEdit.requestFocus();
                totalEdit.setSelection(totalEdit.getText().length());
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(totalEdit, InputMethodManager.SHOW_IMPLICIT);
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
