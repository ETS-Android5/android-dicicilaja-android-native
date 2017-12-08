package id.variable.dicicilaja.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import id.variable.dicicilaja.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextView forgotHelpText, forgotHelpButton;
    EditText inputEmail;
    Button btnVerifikasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        forgotHelpButton = (TextView) findViewById(R.id.forgotHelpButton);
        forgotHelpText = (TextView) findViewById(R.id.forgotHelpText);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        btnVerifikasi = (Button) findViewById(R.id.btnVerifikasi);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        forgotHelpText.setTypeface(opensans_reguler);
        forgotHelpButton.setTypeface(opensans_bold);
        btnVerifikasi.setTypeface(opensans_bold);
        inputEmail.setTypeface(opensans_semibold);

        forgotHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
