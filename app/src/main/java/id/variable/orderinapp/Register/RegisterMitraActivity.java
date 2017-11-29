package id.variable.orderinapp.Register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import id.variable.orderinapp.R;

public class RegisterMitraActivity extends AppCompatActivity {

    Spinner jenisKelamin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_mitra);

        jenisKelamin = (Spinner) findViewById(R.id.jenisKelamin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.jenis_kelamin, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        jenisKelamin.setAdapter(adapter);
    }
}
