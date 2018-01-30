package id.variable.dicicilaja.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import id.variable.dicicilaja.Activity.EmployeeDashboardActivity;
import id.variable.dicicilaja.Activity.LihatDatabaseEmployeeActivity;
import id.variable.dicicilaja.Activity.ProsesPengajuan2Activity;
import id.variable.dicicilaja.Activity.RequestProcessActivity;
import id.variable.dicicilaja.Model.ResRequestProcess;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Remote.ApiUtils;
import id.variable.dicicilaja.Remote.RequestProcess;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskCRHFragment extends Fragment {

    TextView title_penugasan, title_tugas, title_nilai, title_keputusan, inputReferal, inputCatatan;
    Button analisa, ditolak1, pending1, button_survey, button_pinjaman, pencairan, ditolak, pending, button_selesai, lihat_database, button_penugasan;
    MaterialEditText input_catatan_survey, input_catatan_pinjaman, input_no_pk, input_catatan_keputusan_pinjaman;
    String id_database;
    RequestProcess interfaceCRHProcess;

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

        interfaceCRHProcess = ApiUtils.getRequestService();

        input_no_pk = view.findViewById(R.id.input_no_pk);
        input_catatan_survey = view.findViewById(R.id.input_catatan_survey);
        input_catatan_pinjaman = view.findViewById(R.id.input_catatan_pinjaman);
        button_survey   = view.findViewById(R.id.button_survey);
        button_pinjaman = view.findViewById(R.id.button_pinjaman);
        input_catatan_keputusan_pinjaman = view.findViewById(R.id.input_catatan_keputusan_pinjaman);

        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_penugasan.setTypeface(opensans_bold);
        title_tugas.setTypeface(opensans_bold);
        title_nilai.setTypeface(opensans_bold);
        title_keputusan.setTypeface(opensans_bold);

        lihat_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), LihatDatabaseEmployeeActivity.class);
                startActivityForResult(i, 1);
            }
        });

        button_penugasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String transaction_id = getActivity().getIntent().getStringExtra("TRANSACTION_ID");
                String assigned_id = inputReferal.getText().toString();
                String notes = inputCatatan.getText().toString();
//                Toast.makeText(getContext(),"transcation_id : " + transaction_id + " assigned_id : " + assigned_id + " notes : " + notes,Toast.LENGTH_LONG).show();
                doProcess(apiKey, transaction_id, assigned_id, notes);
            }
        });
        Intent intent = getActivity().getIntent();
        if(getActivity().getIntent().getStringExtra("STATUS_SURVEY").equals("1")) {
            input_catatan_survey.setVisibility(View.GONE);
            input_no_pk.setVisibility(View.GONE);
            input_catatan_keputusan_pinjaman.setVisibility(View.GONE);

            analisa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    analisa.setEnabled(false);
                    ditolak1.setEnabled(true);
                    pending1.setEnabled(true);
                    input_catatan_survey.setVisibility(View.GONE);
                }
            });
            ditolak1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ditolak1.setEnabled(false);
                    analisa.setEnabled(true);
                    pending1.setEnabled(true);
                    input_catatan_survey.setVisibility(View.VISIBLE);
                }
            });
            pending1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pending1.setEnabled(false);
                    analisa.setEnabled(true);
                    ditolak1.setEnabled(true);
                    input_catatan_survey.setVisibility(View.VISIBLE);
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
                }
            });
        }else{
            analisa.setEnabled(false);
            pending1.setEnabled(false);
            ditolak1.setEnabled(false);
            pencairan.setEnabled(false);
            pending.setEnabled(false);
            ditolak.setEnabled(false);
            input_catatan_survey.setVisibility(View.GONE);
            button_survey.setVisibility(View.GONE);
            input_catatan_pinjaman.setEnabled(false);
            button_pinjaman.setEnabled(false);
            input_no_pk.setVisibility(View.GONE);
            input_catatan_keputusan_pinjaman.setVisibility(View.GONE);
            button_selesai.setVisibility(View.GONE);
        }


        return view;
    }

    private void doProcess(final String apiKey, final String transaction_id, final String assigned_id, final String notes) {
        Call<ResRequestProcess> call = interfaceCRHProcess.assign(apiKey,transaction_id, assigned_id, notes);
        call.enqueue(new Callback<ResRequestProcess>() {
            @Override
            public void onResponse(Call<ResRequestProcess> call, Response<ResRequestProcess> response) {
                try {
                    Intent intent = new Intent(getContext(), EmployeeDashboardActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } catch(Exception ex) {
                    Log.w("Process Exception :", ex.getMessage());
                    Toast.makeText(getActivity(), "Tidak dapat memproses pengajuan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResRequestProcess> call, Throwable t) {

            }
        });
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
