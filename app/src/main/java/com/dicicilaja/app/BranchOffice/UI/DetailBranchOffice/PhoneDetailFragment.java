package com.dicicilaja.app.BranchOffice.UI.DetailBranchOffice;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnClick;
import com.dicicilaja.app.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneDetailFragment extends BottomSheetDialogFragment {


    @BindView(R.id.text_phone11)
    TextView textPhone11;
    @BindView(R.id.copy_phone11)
    ImageView copyPhone11;
    @BindView(R.id.call_phone11)
    ImageView callPhone11;
    @BindView(R.id.phone11)
    RelativeLayout phone11;
    @BindView(R.id.text_phone12)
    TextView textPhone12;
    @BindView(R.id.copy_phone12)
    ImageView copyPhone12;
    @BindView(R.id.call_phone12)
    ImageView callPhone12;
    @BindView(R.id.phone12)
    RelativeLayout phone12;
    @BindView(R.id.text_phone13)
    TextView textPhone13;
    @BindView(R.id.copy_phone13)
    ImageView copyPhone13;
    @BindView(R.id.call_phone13)
    ImageView callPhone13;
    @BindView(R.id.phone13)
    RelativeLayout phone13;
    @BindView(R.id.group1)
    LinearLayout group1;
    @BindView(R.id.text_phone21)
    TextView textPhone21;
    @BindView(R.id.copy_phone21)
    ImageView copyPhone21;
    @BindView(R.id.call_phone21)
    ImageView callPhone21;
    @BindView(R.id.phone21)
    RelativeLayout phone21;
    @BindView(R.id.text_phone22)
    TextView textPhone22;
    @BindView(R.id.copy_phone22)
    ImageView copyPhone22;
    @BindView(R.id.call_phone22)
    ImageView callPhone22;
    @BindView(R.id.phone22)
    RelativeLayout phone22;
    @BindView(R.id.text_phone23)
    TextView textPhone23;
    @BindView(R.id.copy_phone23)
    ImageView copyPhone23;
    @BindView(R.id.call_phone23)
    ImageView callPhone23;
    @BindView(R.id.phone23)
    RelativeLayout phone23;
    @BindView(R.id.group2)
    LinearLayout group2;
    @BindView(R.id.text_phone31)
    TextView textPhone31;
    @BindView(R.id.copy_phone31)
    ImageView copyPhone31;
    @BindView(R.id.call_phone31)
    ImageView callPhone31;
    @BindView(R.id.phone31)
    RelativeLayout phone31;
    @BindView(R.id.text_phone32)
    TextView textPhone32;
    @BindView(R.id.copy_phone32)
    ImageView copyPhone32;
    @BindView(R.id.call_phone32)
    ImageView callPhone32;
    @BindView(R.id.phone32)
    RelativeLayout phone32;
    @BindView(R.id.text_phone33)
    TextView textPhone33;
    @BindView(R.id.copy_phone33)
    ImageView copyPhone33;
    @BindView(R.id.call_phone33)
    ImageView callPhone33;
    @BindView(R.id.phone33)
    RelativeLayout phone33;
    @BindView(R.id.group3)
    LinearLayout group3;

    String phone11_text, phone12_text, phone13_text, phone21_text, phone22_text, phone23_text, phone31_text, phone32_text, phone33_text;
    String phone1size, phone2size, phone3size;

    public PhoneDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phone_detail, container, false);

        group1 = view.findViewById(R.id.group1);
        phone11 = view.findViewById(R.id.phone11);
        phone12 = view.findViewById(R.id.phone12);
        phone13 = view.findViewById(R.id.phone13);

        group2 = view.findViewById(R.id.group2);
        phone21 = view.findViewById(R.id.phone21);
        phone22 = view.findViewById(R.id.phone22);
        phone23 = view.findViewById(R.id.phone23);

        group3 = view.findViewById(R.id.group3);
        phone31 = view.findViewById(R.id.phone31);
        phone32 = view.findViewById(R.id.phone32);
        phone33 = view.findViewById(R.id.phone33);

        textPhone11 = view.findViewById((R.id.text_phone11));
        textPhone12 = view.findViewById((R.id.text_phone12));
        textPhone13 = view.findViewById((R.id.text_phone13));

        textPhone21 = view.findViewById((R.id.text_phone21));
        textPhone22 = view.findViewById((R.id.text_phone22));
        textPhone23 = view.findViewById((R.id.text_phone23));

        textPhone31 = view.findViewById((R.id.text_phone31));
        textPhone32 = view.findViewById((R.id.text_phone32));
        textPhone33 = view.findViewById((R.id.text_phone33));

        copyPhone11 = view.findViewById(R.id.copy_phone11);
        copyPhone12 = view.findViewById(R.id.copy_phone12);
        copyPhone13 = view.findViewById(R.id.copy_phone13);
        copyPhone21 = view.findViewById(R.id.copy_phone21);
        copyPhone22 = view.findViewById(R.id.copy_phone22);
        copyPhone23 = view.findViewById(R.id.copy_phone23);
        copyPhone31 = view.findViewById(R.id.copy_phone31);
        copyPhone32 = view.findViewById(R.id.copy_phone32);
        copyPhone33 = view.findViewById(R.id.copy_phone33);

        callPhone11 = view.findViewById(R.id.call_phone11);
        callPhone12 = view.findViewById(R.id.call_phone12);
        callPhone13 = view.findViewById(R.id.call_phone13);
        callPhone21 = view.findViewById(R.id.call_phone21);
        callPhone22 = view.findViewById(R.id.call_phone22);
        callPhone23 = view.findViewById(R.id.call_phone23);
        callPhone31 = view.findViewById(R.id.call_phone31);
        callPhone32 = view.findViewById(R.id.call_phone32);
        callPhone33 = view.findViewById(R.id.call_phone33);


        group1.setVisibility(View.GONE);
        phone11.setVisibility(View.GONE);
        phone12.setVisibility(View.GONE);
        phone13.setVisibility(View.GONE);

        group2.setVisibility(View.GONE);
        phone21.setVisibility(View.GONE);
        phone22.setVisibility(View.GONE);
        phone23.setVisibility(View.GONE);

        group3.setVisibility(View.GONE);
        phone31.setVisibility(View.GONE);
        phone32.setVisibility(View.GONE);
        phone33.setVisibility(View.GONE);

        Bundle mArgs = getArguments();
        phone1size = mArgs.getString("phone1size");
        phone2size = mArgs.getString("phone2size");
        phone3size = mArgs.getString("phone3size");

        phone11_text = mArgs.getString("phone11");
        phone12_text = mArgs.getString("phone12");
        phone13_text = mArgs.getString("phone13");

        phone21_text = mArgs.getString("phone21");
        phone22_text = mArgs.getString("phone22");
        phone23_text = mArgs.getString("phone23");

        phone31_text = mArgs.getString("phone31");
        phone32_text = mArgs.getString("phone32");
        phone33_text = mArgs.getString("phone33");

        Log.d("TAGTAG", "phonesize (1): " + String.valueOf(phone1size));
        Log.d("TAGTAG", "phonesize (2): " + String.valueOf(phone2size));
        Log.d("TAGTAG", "phonesize (3): " + String.valueOf(phone3size));

        Log.d("TAGTAG", "phone 1-1: " + String.valueOf(phone11_text));
        Log.d("TAGTAG", "phone 1-2: " + String.valueOf(phone12_text));
        Log.d("TAGTAG", "phone 1-3: " + String.valueOf(phone13_text));

        Log.d("TAGTAG", "phone 2-1: " + String.valueOf(phone21_text));
        Log.d("TAGTAG", "phone 2-2: " + String.valueOf(phone22_text));
        Log.d("TAGTAG", "phone 2-3: " + String.valueOf(phone23_text));

        Log.d("TAGTAG", "phone 3-1: " + String.valueOf(phone31_text));
        Log.d("TAGTAG", "phone 3-2: " + String.valueOf(phone32_text));
        Log.d("TAGTAG", "phone 3-3: " + String.valueOf(phone33_text));

        if (phone1size == null || phone1size.equals("null") || phone1size.equals("-") || phone1size.equals("0")) {

        } else {
            group1.setVisibility(View.VISIBLE);

            if (phone11_text == null || phone11_text.equals("null") || phone11_text.equals("-") || phone11_text.equals("0")) {

            } else {
                phone11.setVisibility(View.VISIBLE);
                textPhone11.setText(phone11_text);
            }

            if (phone12_text == null || phone12_text.equals("null") || phone12_text.equals("-") || phone12_text.equals("0")) {

            } else {
                phone12.setVisibility(View.VISIBLE);
                textPhone12.setText(phone12_text);
            }

            if (phone13_text == null || phone13_text.equals("null") || phone13_text.equals("-") || phone13_text.equals("0")) {

            } else {
                phone13.setVisibility(View.VISIBLE);
                textPhone13.setText(phone13_text);
            }
        }

        if (phone2size == null || phone2size.equals("null") || phone2size.equals("-") || phone2size.equals("0")) {

        } else {
            group2.setVisibility(View.VISIBLE);

            if (phone21_text == null || phone21_text.equals("null") || phone21_text.equals("-") || phone21_text.equals("0")) {

            } else {
                phone21.setVisibility(View.VISIBLE);
                textPhone21.setText(phone21_text);
            }

            if (phone22_text == null || phone22_text.equals("null") || phone22_text.equals("-") || phone22_text.equals("0")) {

            } else {
                phone22.setVisibility(View.VISIBLE);
                textPhone22.setText(phone22_text);
            }

            if (phone23_text == null || phone23_text.equals("null") || phone23_text.equals("-") || phone23_text.equals("0")) {

            } else {
                phone23.setVisibility(View.VISIBLE);
                textPhone23.setText(phone23_text);
            }
        }

        if (phone3size == null || phone3size.equals("null") || phone3size.equals("-") || phone3size.equals("0")) {

        } else {
            group3.setVisibility(View.VISIBLE);

            if (phone31_text == null || phone31_text.equals("null") || phone31_text.equals("-") || phone31_text.equals("0")) {

            } else {
                phone31.setVisibility(View.VISIBLE);
                textPhone31.setText(phone31_text);
            }

            if (phone32_text == null || phone32_text.equals("null") || phone32_text.equals("-") || phone32_text.equals("0")) {

            } else {
                phone32.setVisibility(View.VISIBLE);
                textPhone32.setText(phone32_text);
            }

            if (phone33_text == null || phone33_text.equals("null") || phone33_text.equals("-") || phone33_text.equals("0")) {

            } else {
                phone33.setVisibility(View.VISIBLE);
                textPhone33.setText(phone33_text);
            }
        }

        copyPhone11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object clipboardService = getActivity().getSystemService(CLIPBOARD_SERVICE);
                final ClipboardManager clipboardManager = (ClipboardManager) clipboardService;
                String srcText = textPhone11.getText().toString();
                ClipData clipData = ClipData.newPlainText("Fax", srcText);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getContext(), "Nomor Fax berhasil disalin",Toast.LENGTH_SHORT).show();
            }
        });

        copyPhone12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object clipboardService2 = getActivity().getSystemService(CLIPBOARD_SERVICE);
                final ClipboardManager clipboardManager2 = (ClipboardManager) clipboardService2;
                String srcText2 = textPhone12.getText().toString();
                ClipData clipData2 = ClipData.newPlainText("Fax", srcText2);
                clipboardManager2.setPrimaryClip(clipData2);
                Toast.makeText(getContext(), "Nomor Fax berhasil disalin",Toast.LENGTH_SHORT).show();
            }
        });

        copyPhone13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object clipboardService3 = getActivity().getSystemService(CLIPBOARD_SERVICE);
                final ClipboardManager clipboardManager3 = (ClipboardManager) clipboardService3;
                String srcText3 = textPhone13.getText().toString();
                ClipData clipData3 = ClipData.newPlainText("Fax", srcText3);
                clipboardManager3.setPrimaryClip(clipData3);
                Toast.makeText(getContext(), "Nomor Fax berhasil disalin",Toast.LENGTH_SHORT).show();
            }
        });

        copyPhone21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object clipboardService4 = getActivity().getSystemService(CLIPBOARD_SERVICE);
                final ClipboardManager clipboardManager4 = (ClipboardManager) clipboardService4;
                String srcText4 = textPhone21.getText().toString();
                ClipData clipData4= ClipData.newPlainText("Fax", srcText4);
                clipboardManager4.setPrimaryClip(clipData4);
                Toast.makeText(getContext(), "Nomor Fax berhasil disalin",Toast.LENGTH_SHORT).show();
            }
        });

        copyPhone22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object clipboardService5 = getActivity().getSystemService(CLIPBOARD_SERVICE);
                final ClipboardManager clipboardManager5 = (ClipboardManager) clipboardService5;
                String srcText5 = textPhone22.getText().toString();
                ClipData clipData5 = ClipData.newPlainText("Fax", srcText5);
                clipboardManager5.setPrimaryClip(clipData5);
                Toast.makeText(getContext(), "Nomor Fax berhasil disalin",Toast.LENGTH_SHORT).show();
            }
        });

        copyPhone23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object clipboardService6 = getActivity().getSystemService(CLIPBOARD_SERVICE);
                final ClipboardManager clipboardManager6 = (ClipboardManager) clipboardService6;
                String srcText6 = textPhone23.getText().toString();
                ClipData clipData6 = ClipData.newPlainText("Fax", srcText6);
                clipboardManager6.setPrimaryClip(clipData6);
                Toast.makeText(getContext(), "Nomor Fax berhasil disalin",Toast.LENGTH_SHORT).show();
            }
        });

        copyPhone31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object clipboardService7 = getActivity().getSystemService(CLIPBOARD_SERVICE);
                final ClipboardManager clipboardManager7 = (ClipboardManager) clipboardService7;
                String srcText7 = textPhone31.getText().toString();
                ClipData clipData7 = ClipData.newPlainText("Fax", srcText7);
                clipboardManager7.setPrimaryClip(clipData7);
                Toast.makeText(getContext(), "Nomor Fax berhasil disalin",Toast.LENGTH_SHORT).show();
            }
        });

        copyPhone32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object clipboardService8 = getActivity().getSystemService(CLIPBOARD_SERVICE);
                final ClipboardManager clipboardManager8 = (ClipboardManager) clipboardService8;
                String srcText8 = textPhone32.getText().toString();
                ClipData clipData8 = ClipData.newPlainText("Fax", srcText8);
                clipboardManager8.setPrimaryClip(clipData8);
                Toast.makeText(getContext(), "Nomor Fax berhasil disalin",Toast.LENGTH_SHORT).show();
            }
        });

        copyPhone33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object clipboardService9 = getActivity().getSystemService(CLIPBOARD_SERVICE);
                final ClipboardManager clipboardManager9 = (ClipboardManager) clipboardService9;
                String srcText9 = textPhone33.getText().toString();
                ClipData clipData9 = ClipData.newPlainText("Fax", srcText9);
                clipboardManager9.setPrimaryClip(clipData9);
                Toast.makeText(getContext(), "Nomor Fax berhasil disalin",Toast.LENGTH_SHORT).show();
            }
        });

        callPhone11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = textPhone11.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        callPhone12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone2 = textPhone12.getText().toString();
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone2, null));
                startActivity(intent2);
            }
        });

        callPhone13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone3 = textPhone13.getText().toString();
                Intent intent3 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone3, null));
                startActivity(intent3);
            }
        });

        callPhone21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone4 = textPhone21.getText().toString();
                Intent intent4 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone4, null));
                startActivity(intent4);
            }
        });

        callPhone22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone5 = textPhone22.getText().toString();
                Intent intent5 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone5, null));
                startActivity(intent5);
            }
        });

        callPhone23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone6 = textPhone23.getText().toString();
                Intent intent6 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone6, null));
                startActivity(intent6);
            }
        });

        callPhone31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone7 = textPhone31.getText().toString();
                Intent intent7 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone7, null));
                startActivity(intent7);
            }
        });

        callPhone32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone8 = textPhone32.getText().toString();
                Intent intent8 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone8, null));
                startActivity(intent8);
            }
        });

        callPhone33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone9 = textPhone33.getText().toString();
                Intent intent9 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone9, null));
                startActivity(intent9);
            }
        });

        return view;
    }
}
