package com.dicicilaja.app.Fragment;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.API.Interface.InterfaceDetailRequest;
import com.dicicilaja.app.API.Item.DetailRequest.DetailRequest;
import com.dicicilaja.app.API.Item.DetailRequest.Progress;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import com.dicicilaja.app.API.Interface.InterfaceKeputusanPinjaman;
import com.dicicilaja.app.API.Interface.InterfaceKeputusanSurvey;
import com.dicicilaja.app.API.Interface.InterfaceNilaiPinjaman;
import com.dicicilaja.app.Activity.DatabaseCRO;
import com.dicicilaja.app.Activity.EmployeeDashboardActivity;
import com.dicicilaja.app.Model.ResRequestProcess;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Remote.ApiUtils;
import com.dicicilaja.app.Remote.RequestProcess;
import com.dicicilaja.app.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskCRHFragment extends Fragment {

    List<Progress> detailRequests;
    TextView title_penugasan, title_tugas, title_nilai, title_keputusan;
    Button simpan_nilai, analisa, ditolak1, pending1, button_survey, button_pinjaman, pencairan, ditolak, pending, button_selesai, lihat_database, button_penugasan;
    MaterialEditText inputReferal, inputCatatan, input_catatan_survey, input_catatan_pinjaman, input_no_pk, input_catatan_keputusan_pinjaman;
    String id_database, decision;
    RequestProcess interfaceCRHProcess;
    InterfaceKeputusanSurvey interfaceKeputusanSurvey;
    InterfaceNilaiPinjaman interfaceNilaiPinjaman;
    InterfaceKeputusanPinjaman interfaceKeputusanPinjaman;
    Integer keputusan_survey;
    LinearLayout layout_title_penugasan, layout_title_tugas, layout_title_nilai, layout_title_keputusan;
    CardView card_pengajuan, card_tugas, card_nilai, card_keputusan;
    RelativeLayout hasil_ammount, layout_catatan;
    TextView final_ammount, title_rp;

    ProgressDialog progress;

    String assigned_id_survey;
    public TaskCRHFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_crh, container, false);

        title_penugasan = view.findViewById(R.id.title_penugasan);
        title_tugas     = view.findViewById(R.id.title_tugas);
        title_nilai     = view.findViewById(R.id.title_nilai);
        title_keputusan = view.findViewById(R.id.title_keputusan);

        final SessionManager session = new SessionManager(getContext());
        final String apiKey = "Bearer " + session.getToken();

        analisa     = view.findViewById(R.id.analisa);
        ditolak1    = view.findViewById(R.id.ditolak1);
        pending1    = view.findViewById(R.id.pending1);
        pencairan     = view.findViewById(R.id.pencairan);
        ditolak    = view.findViewById(R.id.ditolak);
        pending    = view.findViewById(R.id.pending);
        button_selesai = view.findViewById(R.id.button_selesai);
        lihat_database = view.findViewById(R.id.lihat_database);
        inputReferal = view.findViewById(R.id.inputReferal);
        inputCatatan = view.findViewById(R.id.inputCatatan);
        button_penugasan = view.findViewById(R.id.button_penugasan);
//        simpan_nilai = view.findViewById(R.id.simpan_nilai);

        interfaceCRHProcess = ApiUtils.getRequestService();
        interfaceKeputusanSurvey = ApiUtils.getKeputusanSurvey();
        interfaceNilaiPinjaman = ApiUtils.getNilaiPinjaman();
        interfaceKeputusanPinjaman = ApiUtils.getKeputusanPinjaman();

        input_no_pk = view.findViewById(R.id.input_no_pk);
        input_catatan_survey = view.findViewById(R.id.input_catatan_survey);
        input_catatan_pinjaman = view.findViewById(R.id.input_catatan_pinjaman);
        button_survey   = view.findViewById(R.id.button_survey);
        button_pinjaman = view.findViewById(R.id.button_pinjaman);
        input_catatan_keputusan_pinjaman = view.findViewById(R.id.input_catatan_keputusan_pinjaman);

        layout_title_penugasan = view.findViewById(R.id.layout_title_penugasan);
        card_pengajuan = view.findViewById(R.id.card_pengajuan);
        layout_title_tugas = view.findViewById(R.id.layout_title_tugas);
        card_tugas = view.findViewById(R.id.card_tugas);
        layout_title_nilai = view.findViewById(R.id.layout_title_nilai);
        card_nilai = view.findViewById(R.id.card_nilai);
        layout_title_keputusan = view.findViewById(R.id.layout_title_keputusan);
        card_keputusan = view.findViewById(R.id.card_keputusan);
        hasil_ammount = view.findViewById(R.id.hasil_ammount);
        layout_catatan = view.findViewById(R.id.layout_catatan);
        final_ammount = view.findViewById(R.id.final_ammount);
        title_rp = view.findViewById(R.id.title_rp);

        hasil_ammount.setVisibility(View.GONE);

        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        final_ammount.setTypeface(opensans_semibold);
        title_rp.setTypeface(opensans_semibold);
        title_penugasan.setTypeface(opensans_bold);
        title_tugas.setTypeface(opensans_bold);
        title_nilai.setTypeface(opensans_bold);
        title_keputusan.setTypeface(opensans_bold);

        progress = new ProgressDialog(getContext());
        progress.setMessage("Please wait...");

        Intent intent = getActivity().getIntent();

        if(getActivity().getIntent().getStringExtra("STATUS").toLowerCase().equals("proses") || getActivity().getIntent().getStringExtra("STATUS").toLowerCase().equals("verifikasi")) {

            layout_title_penugasan.setVisibility(View.VISIBLE);
            card_pengajuan.setVisibility(View.VISIBLE);
            layout_title_tugas.setVisibility(View.GONE);
            card_tugas.setVisibility(View.GONE);
            layout_title_nilai.setVisibility(View.GONE);
            card_nilai.setVisibility(View.GONE);
            layout_title_keputusan.setVisibility(View.GONE);
            card_keputusan.setVisibility(View.GONE);

            lihat_database.setEnabled(true);
            inputReferal.setEnabled(true);
            inputCatatan.setEnabled(true);
            button_penugasan.setEnabled(true);

            lihat_database.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), DatabaseCRO.class);
                    startActivityForResult(i, 1);
                }
            });

            button_penugasan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String transaction_id = getActivity().getIntent().getStringExtra("TRANSACTION_ID");
                    String assigned_id = inputReferal.getText().toString();
                    String notes = inputCatatan.getText().toString();

                    //Toast.makeText(getContext(),"transcation_id : " + transaction_id + " assigned_id : " + assigned_id + " notes : " + notes,Toast.LENGTH_LONG).show();

                if(validateForm(assigned_id)){
                        doProcess(apiKey, transaction_id, assigned_id, notes);
                    }
                }
            });

//            analisa.setEnabled(false);
//            pending1.setEnabled(false);
//            ditolak1.setEnabled(false);
//            input_catatan_survey.setVisibility(View.GONE);
//            button_survey.setEnabled(false);
//
//            input_catatan_pinjaman.setEnabled(false);
//            button_pinjaman.setEnabled(false);
////            simpan_nilai.setEnabled(false);
//
//            pencairan.setEnabled(false);
//            pending.setEnabled(false);
//            ditolak.setEnabled(false);
//            input_no_pk.setVisibility(View.GONE);
//            input_catatan_keputusan_pinjaman.setVisibility(View.GONE);
//            button_selesai.setVisibility(View.GONE);

        } else if(getActivity().getIntent().getStringExtra("STATUS").toLowerCase().equals("survey") && getActivity().getIntent().getStringExtra("FINAL_AMOUNT") == null) {

            layout_title_penugasan.setVisibility(View.GONE);
            card_pengajuan.setVisibility(View.GONE);
            layout_title_tugas.setVisibility(View.VISIBLE);
            card_tugas.setVisibility(View.VISIBLE);
            layout_title_nilai.setVisibility(View.GONE);
            card_nilai.setVisibility(View.GONE);
            layout_title_keputusan.setVisibility(View.GONE);
            card_keputusan.setVisibility(View.GONE);

            analisa.setEnabled(true);
            pending1.setEnabled(true);
            ditolak1.setEnabled(true);
            input_catatan_survey.setVisibility(View.GONE);
            button_survey.setEnabled(false);

//            input_catatan_pinjaman.setEnabled(false);
//            button_pinjaman.setEnabled(false);
////            simpan_nilai.setEnabled(false);
//
//            pencairan.setEnabled(false);
//            pending.setEnabled(false);
//            ditolak.setEnabled(false);
//            input_no_pk.setVisibility(View.GONE);
//            input_catatan_keputusan_pinjaman.setVisibility(View.GONE);
//            button_selesai.setVisibility(View.GONE);

            analisa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    analisa.setEnabled(false);
                    ditolak1.setEnabled(true);
                    pending1.setEnabled(true);
                    input_catatan_survey.setVisibility(View.GONE);
                    button_survey.setEnabled(true);
                    decision = "analisa";
                }
            });
            ditolak1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ditolak1.setEnabled(false);
                    analisa.setEnabled(true);
                    pending1.setEnabled(true);
                    input_catatan_survey.setVisibility(View.VISIBLE);
                    button_survey.setEnabled(true);
                    decision = "ditolak";
                }
            });
            pending1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pending1.setEnabled(false);
                    analisa.setEnabled(true);
                    ditolak1.setEnabled(true);
                    input_catatan_survey.setVisibility(View.VISIBLE);
                    button_survey.setEnabled(true);
                    decision = "pending";
                }
            });


            button_survey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InterfaceDetailRequest apiService = RetrofitClient.getClient().create(InterfaceDetailRequest.class);

                    Call<DetailRequest> call = apiService.getDetailRequest(apiKey, Integer.parseInt(getActivity().getIntent().getStringExtra("TRANSACTION_ID")));
                    call.enqueue(new Callback<DetailRequest>() {
                        @Override
                        public void onResponse(Call<DetailRequest> call, Response<DetailRequest> response) {
                            if(response.isSuccessful()) {
                                detailRequests = response.body().getProgress();
                                if(decision.equals("pending")) {
                                    for (int i = 0; i < detailRequests.size(); i++){
                                        if(detailRequests.get(i).getStatus().equals("Survey")) {
                                            String assigned_id = detailRequests.get(i).getUser_id();
                                            String transaction_id = getActivity().getIntent().getStringExtra("TRANSACTION_ID");
                                            String notes = input_catatan_survey.getText().toString();
                                            /*Toast.makeText(getActivity(), "id : "
                                                    + transaction_id + "assign : "
                                                    + assigned_id + "notes : "
                                                    + notes + "decision : "
                                                    + decision, Toast.LENGTH_SHORT).show();*/
                                            keputusanSurvey(apiKey, transaction_id, assigned_id, notes, decision);
                                        }
                                    }
                                }else{
                                    for (int i = 0; i < detailRequests.size(); i++){
                                        if(detailRequests.get(i).getStatus().equals("Proses")) {
                                            String assigned_id = detailRequests.get(i).getUser_id();
                                            String transaction_id = getActivity().getIntent().getStringExtra("TRANSACTION_ID");
                                            String notes = input_catatan_survey.getText().toString();
                                            /*Toast.makeText(getActivity(), "id : "
                                                    + transaction_id + "assign : "
                                                    + assigned_id + "notes : "
                                                    + notes + "decision : "
                                                    + decision, Toast.LENGTH_SHORT).show();*/
                                            keputusanSurvey(apiKey, transaction_id, assigned_id, notes, decision);
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<DetailRequest> call, Throwable t) {
                            // Log error here since request failed
                            Toast.makeText(getContext(), "koneksi internet tidak ditemukan", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, t.toString());
                        }
                    });
                }
            });
        } else if(getActivity().getIntent().getStringExtra("STATUS").toLowerCase().equals("analisa kredit")) {
//            lihat_database.setEnabled(false);
//            inputReferal.setEnabled(false);
//            inputCatatan.setEnabled(false);
//            button_penugasan.setEnabled(false);

//            analisa.setEnabled(false);
//            pending1.setEnabled(false);
//            ditolak1.setEnabled(false);
//            input_catatan_survey.setVisibility(View.GONE);
//            button_survey.setEnabled(false);

            layout_title_penugasan.setVisibility(View.GONE);
            card_pengajuan.setVisibility(View.GONE);
            layout_title_tugas.setVisibility(View.GONE);
            card_tugas.setVisibility(View.GONE);
            layout_title_nilai.setVisibility(View.VISIBLE);
            card_nilai.setVisibility(View.VISIBLE);
            layout_title_keputusan.setVisibility(View.GONE);
            card_keputusan.setVisibility(View.GONE);

            input_catatan_pinjaman.setEnabled(true);
            setCurrency(input_catatan_pinjaman);
            button_pinjaman.setEnabled(true);
//            simpan_nilai.setEnabled(true);

//            pencairan.setEnabled(false);
//            pending.setEnabled(false);
//            ditolak.setEnabled(false);
//            input_no_pk.setVisibility(View.GONE);
//            input_catatan_keputusan_pinjaman.setVisibility(View.GONE);
//            button_selesai.setEnabled(false);

            button_pinjaman.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

                    // Setting Dialog Title
                    alertDialog.setTitle("Konfirmasi");

                    // Setting Dialog Message
                    alertDialog.setMessage("Apakah Anda sudah menghubungi nasabah terkait dengan nilai Pinjaman berdasarkan hasil Analisa Kredit?");


                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("Saya sudah menghubungi pemohon", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String transaction_id = getActivity().getIntent().getStringExtra("TRANSACTION_ID");
                            String assigned_id = session.getUserId();
                            String notes = "-";
                            String amount = input_catatan_pinjaman.getText().toString().replace(".","");
                            //Toast.makeText(getContext(),"amount : " + amount,Toast.LENGTH_LONG).show();
                            nilaiPinjaman(apiKey, transaction_id, assigned_id, notes, amount);
//                        input_catatan_pinjaman.setEnabled(false);
//                        button_pinjaman.setEnabled(false);
//                        pencairan.setEnabled(true);
//                        pending.setEnabled(true);
//                        ditolak.setEnabled(true);
//                        input_no_pk.setVisibility(View.GONE);
//                        input_catatan_keputusan_pinjaman.setVisibility(View.GONE);
//                        button_selesai.setEnabled(true);
                        }
                    });

//                 Setting Negative "NO" Button
//                alertDialog.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });

//                 Showing Alert Message
                    alertDialog.show();
                }
            });
            pencairan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pencairan.setEnabled(false);
                    ditolak.setEnabled(true);
                    pending.setEnabled(true);
                    input_no_pk.setVisibility(View.VISIBLE);
                    input_catatan_keputusan_pinjaman.setVisibility(View.GONE);
                    decision = "pencairan";
                }
            });
            ditolak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pencairan.setEnabled(true);
                    ditolak.setEnabled(false);
                    pending.setEnabled(true);
                    input_no_pk.setVisibility(View.GONE);
                    input_catatan_keputusan_pinjaman.setVisibility(View.VISIBLE);
                    decision = "ditolak";
                }
            });
            pending.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pencairan.setEnabled(true);
                    ditolak.setEnabled(true);
                    pending.setEnabled(false);
                    input_no_pk.setVisibility(View.GONE);
                    input_catatan_keputusan_pinjaman.setVisibility(View.VISIBLE);
                    decision = "pending";
                }
            });

            button_selesai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String transaction_id = getActivity().getIntent().getStringExtra("TRANSACTION_ID");
                    String assigned_id = session.getUserId();
                    String pk_number = input_no_pk.getText().toString();
                    String notes = input_catatan_keputusan_pinjaman.getText().toString();
                    String amount = input_catatan_pinjaman.getText().toString();
                    Toast.makeText(getContext(),"transaction_id : " + transaction_id + " assigned_id : " + assigned_id + " notes : " + input_catatan_keputusan_pinjaman.getText() + " decision : " + decision + "amount : " + input_catatan_pinjaman.getText(),Toast.LENGTH_LONG).show();
                    keputusanPinjaman(apiKey, transaction_id, assigned_id, notes, decision, pk_number,amount);
                }
            });
        } else if(getActivity().getIntent().getStringExtra("STATUS").toLowerCase().equals("pending") && getActivity().getIntent().getStringExtra("FINAL_AMOUNT") != null) {

            layout_title_penugasan.setVisibility(View.GONE);
            card_pengajuan.setVisibility(View.GONE);
            layout_title_tugas.setVisibility(View.GONE);
            card_tugas.setVisibility(View.GONE);
            layout_title_nilai.setVisibility(View.VISIBLE);
            card_nilai.setVisibility(View.VISIBLE);
            layout_title_keputusan.setVisibility(View.VISIBLE);
            card_keputusan.setVisibility(View.VISIBLE);

//            lihat_database.setEnabled(false);
//            inputReferal.setEnabled(false);
//            inputCatatan.setEnabled(false);
//            button_penugasan.setEnabled(false);
//
//            analisa.setEnabled(false);
//            pending1.setEnabled(false);
//            ditolak1.setEnabled(false);
//            input_catatan_survey.setVisibility(View.GONE);
//            button_survey.setEnabled(false);

//            input_catatan_pinjaman.setEnabled(false);
//            hasil_ammount.setText(getActivity().getIntent().getStringExtra("FINAL_AMOUNT"));

            hasil_ammount.setVisibility(View.VISIBLE);
            final_ammount.setText(getActivity().getIntent().getStringExtra("FINAL_AMOUNT"));
            layout_catatan.setVisibility(View.GONE);
            button_pinjaman.setVisibility(View.GONE);

            pencairan.setEnabled(true);
            pending.setEnabled(true);
            ditolak.setEnabled(true);
            input_no_pk.setVisibility(View.GONE);
            input_catatan_keputusan_pinjaman.setVisibility(View.GONE);
            button_selesai.setEnabled(true);

            pencairan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pencairan.setEnabled(false);
                    ditolak.setEnabled(true);
                    pending.setEnabled(true);
                    input_no_pk.setVisibility(View.VISIBLE);
                    input_catatan_keputusan_pinjaman.setVisibility(View.GONE);
                    decision = "pencairan";
                }
            });
            ditolak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pencairan.setEnabled(true);
                    ditolak.setEnabled(false);
                    pending.setEnabled(true);
                    input_no_pk.setVisibility(View.GONE);
                    input_catatan_keputusan_pinjaman.setVisibility(View.VISIBLE);
                    decision = "ditolak";
                }
            });
            pending.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pencairan.setEnabled(true);
                    ditolak.setEnabled(true);
                    pending.setEnabled(false);
                    input_no_pk.setVisibility(View.GONE);
                    input_catatan_keputusan_pinjaman.setVisibility(View.VISIBLE);
                    decision = "pending";
                }
            });

            button_selesai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String transaction_id = getActivity().getIntent().getStringExtra("TRANSACTION_ID");
                    String assigned_id = session.getUserId();
                    String pk_number = input_no_pk.getText().toString();
                    String notes = input_catatan_keputusan_pinjaman.getText().toString();
                    String amount = input_catatan_pinjaman.getText().toString();
//                    Toast.makeText(getContext(),"transaction_id : " + transaction_id + " assigned_id : " + assigned_id + " notes : " + input_catatan_keputusan_pinjaman.getText() + " decision : " + decision + "amount : " + input_catatan_pinjaman.getText(),Toast.LENGTH_LONG).show();
                    keputusanPinjaman(apiKey, transaction_id, assigned_id, notes, decision, pk_number, amount);
                }
            });
        } else {
            layout_title_penugasan.setVisibility(View.GONE);
            card_pengajuan.setVisibility(View.GONE);
            layout_title_tugas.setVisibility(View.GONE);
            card_tugas.setVisibility(View.GONE);
            layout_title_nilai.setVisibility(View.GONE);
            card_nilai.setVisibility(View.GONE);
            layout_title_keputusan.setVisibility(View.GONE);
            card_keputusan.setVisibility(View.GONE);
        }

        return view;
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

    private void doProcess(final String apiKey, final String transaction_id, final String assigned_id, final String notes) {
        RequestProcess process =
                RetrofitClient.getClient().create(RequestProcess.class);

        Call<ResRequestProcess> call = process.assign(apiKey,transaction_id, assigned_id, notes);
        call.enqueue(new Callback<ResRequestProcess>() {
            @Override
            public void onResponse(Call<ResRequestProcess> call, Response<ResRequestProcess> response) {
                if(response.isSuccessful()) {
                    Intent intent = new Intent(getContext(), EmployeeDashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<ResRequestProcess> call, Throwable t) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setMessage("Koneksi internet tidak ditemukan");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });
    }

    private void keputusanSurvey(final String apiKey, final String transaction_id, final String assigned_id, final String notes, final String decision) {
        progress.show();

        InterfaceKeputusanSurvey survey =
                RetrofitClient.getClient().create(InterfaceKeputusanSurvey.class);

        Call<ResRequestProcess> call = survey.assign(apiKey,transaction_id, assigned_id, notes, decision);
        call.enqueue(new Callback<ResRequestProcess>() {
            @Override
            public void onResponse(Call<ResRequestProcess> call, Response<ResRequestProcess> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(getContext(), EmployeeDashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "code : " + response.code(), Toast.LENGTH_SHORT).show();
                }

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ResRequestProcess> call, Throwable t) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Oooppsss!");
                alertDialog.setMessage("Sepertinya terjadi kesalahan pada server kami.");

                alertDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setPositiveButton("Coba Lagi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        keputusanSurvey(apiKey,transaction_id, assigned_id, notes, decision);
                    }
                });

                progress.dismiss();
                alertDialog.show();
            }
        });
    }

    private void keputusanPinjaman(final String apiKey, final String transaction_id, final String assigned_id, final String notes, final String decision, final String pk_number, final String amount) {
        InterfaceKeputusanPinjaman pinjaman =
                RetrofitClient.getClient().create(InterfaceKeputusanPinjaman.class);

        Call<ResRequestProcess> call = pinjaman.assign(apiKey,transaction_id, assigned_id, notes, decision, pk_number, amount);
        call.enqueue(new Callback<ResRequestProcess>() {
            @Override
            public void onResponse(Call<ResRequestProcess> call, Response<ResRequestProcess> response) {
                try {
                    Intent intent = new Intent(getContext(), EmployeeDashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    getActivity().finish();
                } catch(Exception ex) {
                }
            }

            @Override
            public void onFailure(Call<ResRequestProcess> call, Throwable t) {

            }
        });
    }

    private void nilaiPinjaman(final String apiKey, final String transaction_id, final String assigned_id, final String notes, final String amount) {
        InterfaceNilaiPinjaman pinjaman =
                RetrofitClient.getClient().create(InterfaceNilaiPinjaman.class);

        Call<ResRequestProcess> call = pinjaman.assign(apiKey,transaction_id, assigned_id, notes, amount);
        call.enqueue(new Callback<ResRequestProcess>() {
            @Override
            public void onResponse(Call<ResRequestProcess> call, Response<ResRequestProcess> response) {
                try {
                    Intent intent = new Intent(getContext(), EmployeeDashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    getActivity().finish();
                } catch(Exception ex) {
                }
            }

            @Override
            public void onFailure(Call<ResRequestProcess> call, Throwable t) {

            }
        });
    }

    private boolean validateForm(String assigned_id) {
        if (assigned_id == null || assigned_id.trim().length() == 0 || assigned_id.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Masukan NIK CRH");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputReferal);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                id_database = data.getStringExtra("ID_DATABASE").toString();
//                Toast.makeText(getBaseContext(),"Halo : " + id_database,Toast.LENGTH_SHORT).show();
                inputReferal.setText(id_database);
            }
        }
    }

}
