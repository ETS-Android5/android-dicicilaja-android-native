package com.dicicilaja.dicicilaja.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.ganfra.materialspinner.MaterialSpinner;
import com.dicicilaja.dicicilaja.R;

public class RegisterAxi2Activity extends AppCompatActivity {

    int year, month, day;
    Button btnLanjut;
    EditText inputTanggal;
    MaterialSpinner spinnerStatusPerkawinan, spinnerProvinsi, spinnerKotaKab, spinnerKecamatan, spinnerKelurahan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_axi2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnLanjut = findViewById(R.id.btnLanjut);
        spinnerStatusPerkawinan = (MaterialSpinner) findViewById(R.id.spinnerStatusPerkawinan);
        spinnerProvinsi = (MaterialSpinner) findViewById(R.id.spinnerProvinsi);
        spinnerKotaKab = (MaterialSpinner) findViewById(R.id.spinnerKotaKab);
        spinnerKecamatan = (MaterialSpinner) findViewById(R.id.spinnerKecamatan);
        spinnerKelurahan = (MaterialSpinner) findViewById(R.id.spinnerKelurahan);
        inputTanggal = (EditText) findViewById(R.id.inputTanggal);
        inputTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogfragment = new DatePickerDialogTheme4();

                dialogfragment.show(getSupportFragmentManager(), "Theme 4");
            }
        });
        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        String[] ITEMS = {"Item 1", "Item 2", "Item 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatusPerkawinan.setAdapter(adapter);
        spinnerStatusPerkawinan.setTypeface(opensans_semibold);
        spinnerProvinsi.setAdapter(adapter);
        spinnerProvinsi.setTypeface(opensans_semibold);
        spinnerKotaKab.setAdapter(adapter);
        spinnerKotaKab.setTypeface(opensans_semibold);
        spinnerKecamatan.setAdapter(adapter);
        spinnerKecamatan.setTypeface(opensans_semibold);
        spinnerKelurahan.setAdapter(adapter);
        spinnerKelurahan.setTypeface(opensans_semibold);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegisterAxi3Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }

    public static class DatePickerDialogTheme4 extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT,this,year,month,day);

            return datepickerdialog;
        }
        public void onDateSet(DatePicker view, int year, int month, int day){

            EditText textview = (EditText)getActivity().findViewById(R.id.inputTanggal);

            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateInString = day + "/" + (month + 1) + "/" + year;
                Date date = formatter.parse(dateInString);

                formatter = new SimpleDateFormat("dd/MMM/yyyy");

                textview.setText(formatter.format(date).toString());

            } catch (Exception ex) {

            }

        }
    }

}

