package com.dicicilaja.app.InformAXI.ui.profile.other;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dicicilaja.app.InformAXI.model.AxiProfile;
import com.dicicilaja.app.InformAXI.ui.profile.ProfileActivity;
import com.dicicilaja.app.InformAXI.ui.profile.ProfileCallback;
import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends Fragment implements ProfileCallback {

    /* UI Region */
    private TextView tvRegisterDate;
    private TextView tvDueDate;
    private TextView tvStatus;
    private ProgressBar pbOther;
    private LinearLayout otherContainer;

    public OtherFragment() {
        // Required empty public constructor
    }

    public static OtherFragment newInstance(String param) {
        OtherFragment fragment = new OtherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("param", param);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables(view);
    }

    private void initVariables(View v) {
        tvRegisterDate = v.findViewById(R.id.tv_register_date);
        tvDueDate = v.findViewById(R.id.tv_due_date);
        tvStatus = v.findViewById(R.id.tv_status);
        pbOther = v.findViewById(R.id.pb_other);
        otherContainer = v.findViewById(R.id.other_container);

        otherContainer.setVisibility(View.GONE);
    }

    private void setData(AxiProfile data) {
        AxiProfile.Data.Lainnya other = data.getData().getLainnya();

        if (other.getTanggalPendaftaran() != null) {
            tvRegisterDate.setText(Tools.formatDateWithoutHour(other.getTanggalPendaftaran()));
        } else {
            tvRegisterDate.setText("null");
        }

        if (other.getJatuhTempoKeanggotaan() != null) {
            tvDueDate.setText(Tools.formatMonth(other.getJatuhTempoKeanggotaan()));
        } else {
            tvDueDate.setText("null");
        }

        if (other.getStatus() != null) {
            tvStatus.setText(other.getStatus());
        } else {
            tvStatus.setText("null");
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if (((ProfileActivity) requireActivity()).data != null) {
            setData(((ProfileActivity) requireActivity()).data);
            pbOther.setVisibility(View.GONE);
            otherContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void update() {
        if (((ProfileActivity) requireActivity()).data != null) {
            setData(((ProfileActivity) requireActivity()).data);
            pbOther.setVisibility(View.GONE);
            otherContainer.setVisibility(View.VISIBLE);
        }
    }

}
