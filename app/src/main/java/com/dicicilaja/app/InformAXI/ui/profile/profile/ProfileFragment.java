package com.dicicilaja.app.InformAXI.ui.profile.profile;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dicicilaja.app.InformAXI.model.AxiProfile;
import com.dicicilaja.app.InformAXI.ui.profile.ProfileActivity;
import com.dicicilaja.app.InformAXI.ui.profile.ProfileCallback;
import com.dicicilaja.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements ProfileCallback {

    /* UI Region */
    private TextView tvName;
    private TextView tvAxiId;
    private TextView tvAxiCategory;
    private TextView tvAxiBranch;
    private TextView tvAxiBranchCode;
    private TextView tvAxiArea;
    private TextView tvAxiMentor;
    private TextView tvAxiPhone;
    private ImageView ivSms, ivWa;
    private NestedScrollView profileContainer;
    private ProgressBar pbProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("param", param);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables(view);
    }

    private void initVariables(View v) {
        tvName = v.findViewById(R.id.tv_name);
        tvAxiId = v.findViewById(R.id.tv_axi_id);
        tvAxiCategory = v.findViewById(R.id.tv_axi_category);
        tvAxiBranch = v.findViewById(R.id.tv_axi_branch);
        tvAxiBranchCode = v.findViewById(R.id.tv_axi_branch_code);
        tvAxiArea = v.findViewById(R.id.tv_axi_area);
        tvAxiMentor = v.findViewById(R.id.tv_axi_mentor);
        tvAxiPhone = v.findViewById(R.id.tv_axi_phone);
        profileContainer = v.findViewById(R.id.profile_container);
        pbProfile = v.findViewById(R.id.pb_profile);
        ivSms = v.findViewById(R.id.iv_sms);
        ivWa = v.findViewById(R.id.iv_wa);

        profileContainer.setVisibility(View.GONE);
    }

    private void setData(AxiProfile data) {
        AxiProfile.Data.Profile profile = data.getData().getProfile();

        tvName.setText(profile.getNamaAXI());
        tvAxiId.setText(String.valueOf(profile.getIdAXI()));
        if (profile.getKategoriAXI() != null) {
            tvAxiCategory.setText(profile.getKategoriAXI());
        } else {
            tvAxiCategory.setText("null");
        }
        if (profile.getAXImentor() != null) {
            tvAxiMentor.setText(profile.getAXImentor());
        } else {
            tvAxiMentor.setText("");
        }
        if (profile.getNomorHandphone() != null) {
            tvAxiPhone.setText(profile.getNomorHandphone());
        } else {
            tvAxiPhone.setText("");
        }
        tvAxiBranch.setText(profile.getNamaCabang());
        tvAxiBranchCode.setText(profile.getKodeCabang());
        tvAxiArea.setText(profile.getArea());

        ivSms.setOnClickListener(v -> {
            if (profile.getNomorHandphone() != null && !profile.getNomorHandphone().isEmpty()) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", profile.getNomorHandphone(), null)));
            } else {
                Toast.makeText(requireContext(), "Nomor telepon tidak tersedia", Toast.LENGTH_SHORT).show();
            }
        });

        ivWa.setOnClickListener(v -> {
            if (profile.getNomorHandphone() != null && !profile.getNomorHandphone().isEmpty()) {
                PackageManager pm = requireContext().getPackageManager();
                try {
                    String number = profile.getNomorHandphone();
                    Intent waIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number + "?body=" + ""));
                    waIntent.setPackage("com.whatsapp");
                    waIntent.putExtra(Intent.EXTRA_TEXT, "Edit pesan untuk mengirim whatsapp");
                    startActivity(Intent.createChooser(waIntent, "Share with"));
                } catch (Exception e) {
                    e.printStackTrace();

                    Toast.makeText(requireContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT)
                            .show();
                }
            } else {
                Toast.makeText(requireContext(), "Nomor telepon tidak tersedia", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((ProfileActivity) requireActivity()).data != null) {
            profileContainer.setVisibility(View.VISIBLE);
            pbProfile.setVisibility(View.GONE);
            setData(((ProfileActivity) requireActivity()).data);
        }
    }

    @Override
    public void update() {
        if (((ProfileActivity) requireActivity()).data != null) {
            profileContainer.setVisibility(View.VISIBLE);
            pbProfile.setVisibility(View.GONE);
            setData(((ProfileActivity) requireActivity()).data);
        }
    }

}
