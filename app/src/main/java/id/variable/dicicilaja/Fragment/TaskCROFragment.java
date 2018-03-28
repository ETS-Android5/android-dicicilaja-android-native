package id.variable.dicicilaja.Fragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import id.variable.dicicilaja.API.Interface.InterfaceDraft;
import id.variable.dicicilaja.API.Interface.InterfaceRequestSurvey;
import id.variable.dicicilaja.API.Interface.InterfaceSurveyFinish;
import id.variable.dicicilaja.Activity.EmployeeDashboardActivity;
import id.variable.dicicilaja.Activity.ProsesPengajuan2Activity;
import id.variable.dicicilaja.Activity.ProsesPengajuan3Activity;
import id.variable.dicicilaja.Activity.ProsesPengajuanActivity;
import id.variable.dicicilaja.Model.ResRequestProcess;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Remote.ApiUtils;
import id.variable.dicicilaja.Remote.RequestProcess;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskCROFragment extends Fragment {

    Calendar myCalendar;

    int year, month, day;
    ImageView date, time;
    EditText date_text, time_text;
    CheckBox check_data1, check_data2, check_data3, check_data4, check_data5, check_data6, check_data7, check_data8, check_data9, check_data10,check_data11;
    RelativeLayout proses;
    Button button_save;
    private int mYear, mMonth, mDay, mHour, mMinute;
    InterfaceRequestSurvey interfaceRequestSurvey;
    InterfaceDraft interfaceDraft;
    InterfaceSurveyFinish interfaceSurveyFinish;
    String nik_crh, reschedule_date1;
    String date_post, time_post;
    public TaskCROFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_cro, container, false);
        date        = view.findViewById(R.id.date);
        time        = view.findViewById(R.id.time);
        date_text   = view.findViewById(R.id.date_text);
        time_text   = view.findViewById(R.id.time_text);
        check_data1 = view.findViewById(R.id.check_data1);
        check_data2 = view.findViewById(R.id.check_data2);
        check_data3 = view.findViewById(R.id.check_data3);
        check_data4 = view.findViewById(R.id.check_data4);
        check_data5 = view.findViewById(R.id.check_data5);
        check_data6 = view.findViewById(R.id.check_data6);
        check_data7 = view.findViewById(R.id.check_data7);
        check_data8 = view.findViewById(R.id.check_data8);
        check_data9 = view.findViewById(R.id.check_data9);
        check_data10 = view.findViewById(R.id.check_data10);
        check_data11 = view.findViewById(R.id.check_data11);

        proses      = view.findViewById(R.id.proses);

        myCalendar = Calendar.getInstance();

        final SessionManager session = new SessionManager(getContext());
        final String apiKey = "Bearer " + session.getToken();

        TextView title_tugas = view.findViewById(R.id.title_tugas);
        TextView title_lampiran = view.findViewById(R.id.title_lampiran);

        proses = view.findViewById(R.id.proses);
        button_save = view.findViewById(R.id.button_save);

        date_text.setKeyListener(null);
        time_text.setKeyListener(null);

        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_tugas.setTypeface(opensans_bold);
        title_lampiran.setTypeface(opensans_bold);
        interfaceRequestSurvey = ApiUtils.getRequestSurvey();
        interfaceDraft = ApiUtils.getDraft();
        interfaceSurveyFinish = ApiUtils.getSurvey();


        final String nik_crh = getActivity().getIntent().getStringExtra("NIK_CRH");
        String check_data_value1 = getActivity().getIntent().getStringExtra("KTP_SUAMI");
        String check_data_value2 = getActivity().getIntent().getStringExtra("KTP_PENJAMIN");
        String check_data_value3 = getActivity().getIntent().getStringExtra("SURAT_CERAI");
        String check_data_value4 = getActivity().getIntent().getStringExtra("SURAT_KEMATIAN");
        String check_data_value5 = getActivity().getIntent().getStringExtra("SURAT_DOMISILI");
        String check_data_value6 = getActivity().getIntent().getStringExtra("KARTU_KELUARGA");
        String check_data_value7 = getActivity().getIntent().getStringExtra("BUKTI_KEPEMILIKAN_RUMAH");
        String check_data_value8 = getActivity().getIntent().getStringExtra("BUKTI_PENGHASILAN");
        String check_data_value9 = getActivity().getIntent().getStringExtra("NO_RANGKA");
        String check_data_value10 = getActivity().getIntent().getStringExtra("STNK");
        String check_data_value11 = getActivity().getIntent().getStringExtra("BPKB");
        if(getActivity().getIntent().hasExtra("RESCHEDULE_DATE")) {
            reschedule_date1 = getActivity().getIntent().getStringExtra("RESCHEDULE_DATE");

            date_text.setText(parseDateToddMMyyyy(reschedule_date1.substring(0,10)));
            date_post = reschedule_date1.substring(0,10);
            time_text.setText(reschedule_date1.substring(11,16));
            time_post = reschedule_date1.substring(11,16);
        }

        try {
            if(check_data_value1.equals("1")){
                check_data1.setChecked(true);
            }
            if(check_data_value2.equals("1")){
                check_data2.setChecked(true);
            }
            if(check_data_value3.equals("1")){
                check_data3.setChecked(true);
            }
            if(check_data_value4.equals("1")){
                check_data4.setChecked(true);
            }
            if(check_data_value5.equals("1")){
                check_data5.setChecked(true);
            }
            if(check_data_value6.equals("1")){
                check_data6.setChecked(true);
            }
            if(check_data_value7.equals("1")){
                check_data7.setChecked(true);
            }
            if(check_data_value8.equals("1")){
                check_data8.setChecked(true);
            }
            if(check_data_value9.equals("1")){
                check_data9.setChecked(true);
            }
            if(check_data_value10.equals("1")){
                check_data10.setChecked(true);
            }
            if(check_data_value11.equals("1")){
                check_data11.setChecked(true);
            }
        } catch (Exception ex) {

        }


        date_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "dd/MM/yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        date_text.setText(sdf.format(myCalendar.getTime()));

                        String formatDate = "yyyy-MM-dd";
                        SimpleDateFormat sdf1 = new SimpleDateFormat(formatDate);
                        date_post = sdf1.format(myCalendar.getTime());

                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "dd/MM/yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        date_text.setText(sdf.format(myCalendar.getTime()));

                        String formatDate = "yyyy-MM-dd";
                        SimpleDateFormat sdf1 = new SimpleDateFormat(formatDate);
                        date_post = sdf1.format(myCalendar.getTime());

                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        time_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                time_text.setText(String.format("%02d:%02d", hourOfDay, minute));
                                time_post = String.format("%02d:%02d", hourOfDay, minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                time_text.setText(String.format("%02d:%02d", hourOfDay, minute));
                                time_post = String.format("%02d:%02d", hourOfDay, minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

        });





        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reschedule_date = date_post + " " + time_post;
                String transaction_id = getActivity().getIntent().getStringExtra("TRANSACTION_ID");
                String assigned_id = session.getUserId();
                String notes = "-";
                String ktp_suami1, ktp_penjamin, surat_cerai, surat_kematian, surat_domisili, kartu_keluarga, bukti_kepemilikan_rumah, bukti_penghasilan, no_rangka, stnk, bpkb;
                if(check_data1.isChecked() == true){
                    ktp_suami1 = "1";
                }else{
                    ktp_suami1 = "0";
                }

                if(check_data2.isChecked() == true){
                    ktp_penjamin = "1";
                }else{
                    ktp_penjamin = "0";
                }

                if(check_data3.isChecked() == true){
                    surat_cerai = "1";
                }else{
                    surat_cerai = "0";
                }

                if(check_data4.isChecked() == true){
                    surat_kematian = "1";
                }else{
                    surat_kematian = "0";
                }

                if(check_data5.isChecked() == true){
                    surat_domisili = "1";
                }else{
                    surat_domisili = "0";
                }

                if(check_data6.isChecked() == true){
                    kartu_keluarga = "1";
                }else{
                    kartu_keluarga = "0";
                }

                if(check_data7.isChecked() == true){
                    bukti_kepemilikan_rumah = "1";
                }else{
                    bukti_kepemilikan_rumah = "0";
                }

                if(check_data8.isChecked() == true){
                    bukti_penghasilan = "1";
                }else{
                    bukti_penghasilan = "0";
                }

                if(check_data9.isChecked() == true){
                    no_rangka = "1";
                }else{
                    no_rangka = "0";
                }

                if(check_data10.isChecked() == true){
                    stnk = "1";
                }else{
                    stnk = "0";
                }

                if(check_data11.isChecked() == true){
                    bpkb = "1";
                }else{
                    bpkb = "0";
                }
//                Toast.makeText(getContext(),"date : " + reschedule_date,Toast.LENGTH_LONG).show();
                doDraft(apiKey, transaction_id, assigned_id, notes, reschedule_date, ktp_suami1, ktp_penjamin, surat_cerai, surat_kematian, surat_domisili, kartu_keluarga, bukti_kepemilikan_rumah, bukti_penghasilan, no_rangka, stnk, bpkb);

            }
        });
        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reschedule_date = date_post + " " + time_post + ":00";
                String transaction_id = getActivity().getIntent().getStringExtra("TRANSACTION_ID");
                String assigned_id = nik_crh;
                String notes = "-";
                String ktp_suami1, ktp_penjamin, surat_cerai, surat_kematian, surat_domisili, kartu_keluarga, bukti_kepemilikan_rumah, bukti_penghasilan, no_rangka, stnk, bpkb;
                if(check_data1.isChecked() == true){
                    ktp_suami1 = "1";
                }else{
                    ktp_suami1 = "0";
                }

                if(check_data2.isChecked() == true){
                    ktp_penjamin = "1";
                }else{
                    ktp_penjamin = "0";
                }

                if(check_data3.isChecked() == true){
                    surat_cerai = "1";
                }else{
                    surat_cerai = "0";
                }

                if(check_data4.isChecked() == true){
                    surat_kematian = "1";
                }else{
                    surat_kematian = "0";
                }

                if(check_data5.isChecked() == true){
                    surat_domisili = "1";
                }else{
                    surat_domisili = "0";
                }

                if(check_data6.isChecked() == true){
                    kartu_keluarga = "1";
                }else{
                    kartu_keluarga = "0";
                }

                if(check_data7.isChecked() == true){
                    bukti_kepemilikan_rumah = "1";
                }else{
                    bukti_kepemilikan_rumah = "0";
                }

                if(check_data8.isChecked() == true){
                    bukti_penghasilan = "1";
                }else{
                    bukti_penghasilan = "0";
                }

                if(check_data9.isChecked() == true){
                    no_rangka = "1";
                }else{
                    no_rangka = "0";
                }

                if(check_data10.isChecked() == true){
                    stnk = "1";
                }else{
                    stnk = "0";
                }

                if(check_data11.isChecked() == true){
                    bpkb = "1";
                }else{
                    bpkb = "0";
                }
                doProcess(apiKey, transaction_id, assigned_id, notes, reschedule_date, ktp_suami1, ktp_penjamin, surat_cerai, surat_kematian, surat_domisili, kartu_keluarga, bukti_kepemilikan_rumah, bukti_penghasilan, no_rangka, stnk, bpkb);

            }
        });
        return view;
    }

    private void doDraft(final String apiKey, final String transaction_id, final String assigned_id, final String notes, final String reschedule_date, final String ktp_suami1, final String ktp_penjamin, final String surat_cerai, final String surat_kematian, final String surat_domisili, final String kartu_keluarga, final String bukti_kepemilikan_rumah, final String bukti_penghasilan, final String no_rangka, final String stnk, final String bpkb) {
        Call<ResRequestProcess> call = interfaceDraft.assign(apiKey, transaction_id, assigned_id, notes, reschedule_date, ktp_suami1, ktp_penjamin, surat_cerai, surat_kematian, surat_domisili, kartu_keluarga, bukti_kepemilikan_rumah, bukti_penghasilan, no_rangka, stnk, bpkb, "1");
        call.enqueue(new Callback<ResRequestProcess>() {
            @Override
            public void onResponse(Call<ResRequestProcess> call, Response<ResRequestProcess> response) {
                try {
//                    Toast.makeText(getContext(), "code : " + response.code(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), EmployeeDashboardActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } catch(Exception ex) {
                    Log.w("Process Exception :", ex.getMessage());
                    Toast.makeText(getContext(), "Tidak dapat memproses pengajuan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResRequestProcess> call, Throwable t) {

            }
        });
    }

    private void doProcess(final String apiKey, final String transaction_id, final String assigned_id, final String notes, final String reschedule_date, final String ktp_suami1, final String ktp_penjamin, final String surat_cerai, final String surat_kematian, final String surat_domisili, final String kartu_keluarga, final String bukti_kepemilikan_rumah, final String bukti_penghasilan, final String no_rangka, final String stnk, final String bpkb) {
        Call<ResRequestProcess> call = interfaceSurveyFinish.assign(apiKey, transaction_id, assigned_id, notes, reschedule_date, ktp_suami1, ktp_penjamin, surat_cerai, surat_kematian, surat_domisili, kartu_keluarga, bukti_kepemilikan_rumah, bukti_penghasilan, no_rangka, stnk, bpkb);
        call.enqueue(new Callback<ResRequestProcess>() {
            @Override
            public void onResponse(Call<ResRequestProcess> call, Response<ResRequestProcess> response) {
                try {
//                    Toast.makeText(getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), EmployeeDashboardActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } catch(Exception ex) {
                    Log.w("Process Exception :", ex.getMessage());
                    Toast.makeText(getContext(), "Tidak dapat memproses pengajuan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResRequestProcess> call, Throwable t) {

            }
        });
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}
