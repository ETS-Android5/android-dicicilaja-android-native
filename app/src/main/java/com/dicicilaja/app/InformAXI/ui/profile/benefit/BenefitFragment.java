package com.dicicilaja.app.InformAXI.ui.profile.benefit;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dicicilaja.app.InformAXI.model.AxiProfile;
import com.dicicilaja.app.InformAXI.ui.incentive.IncentiveActivity;
import com.dicicilaja.app.InformAXI.ui.profile.ProfileActivity;
import com.dicicilaja.app.InformAXI.ui.profile.ProfileCallback;
import com.dicicilaja.app.R;

import static com.dicicilaja.app.InformAXI.utils.Constants.BIKE;
import static com.dicicilaja.app.InformAXI.utils.Constants.CAR;
import static com.dicicilaja.app.InformAXI.utils.Constants.INCENTIVE;
import static com.dicicilaja.app.InformAXI.utils.Constants.PROFILE_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class BenefitFragment extends Fragment implements ProfileCallback {

    /* UI Region */
    private RelativeLayout bikeIncentive;
    private RelativeLayout carIncentive;
    private TextView tvPointReward;
    private TextView tvPointTrip;
    private TextView tvFidMcy;
    private TextView tvFidCar;
    private TextView tvAxiMatrix;
    private ProgressBar pbBenefit;
    private NestedScrollView benefitContainer;

    public BenefitFragment() {
        // Required empty public constructor
    }

    public static BenefitFragment newInstance(String param) {
        BenefitFragment fragment = new BenefitFragment();
        Bundle bundle = new Bundle();
        bundle.putString("param", param);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_benefit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables(view);
        initListener();
    }

    private void initVariables(View v) {
        bikeIncentive = v.findViewById(R.id.bike_incentive);
        carIncentive = v.findViewById(R.id.car_incentive);
        tvPointReward = v.findViewById(R.id.tv_point_reward);
        tvPointTrip = v.findViewById(R.id.tv_point_trip);
        tvFidMcy = v.findViewById(R.id.tv_fid_mcy);
        tvFidCar = v.findViewById(R.id.tv_fid_car);
        tvAxiMatrix = v.findViewById(R.id.tv_axi_matrix);
        pbBenefit = v.findViewById(R.id.pb_benefit);
        benefitContainer = v.findViewById(R.id.benefit_container);

        benefitContainer.setVisibility(View.GONE);
    }

    private void initListener() {
        bikeIncentive.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), IncentiveActivity.class);
            i.putExtra(PROFILE_ID, ((ProfileActivity) requireActivity()).profileId);
            i.putExtra(INCENTIVE, BIKE);
            startActivity(i);
        });

        carIncentive.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), IncentiveActivity.class);
            i.putExtra(PROFILE_ID, ((ProfileActivity) requireActivity()).profileId);
            i.putExtra(INCENTIVE, CAR);
            startActivity(i);
        });
    }

    private void setData(AxiProfile data) {
        AxiProfile.DataBean.BenefitBean benefit = data.getData().getBenefit();

        if (benefit.getPointReward() != -1) {
            tvPointReward.setText(String.valueOf(benefit.getPointReward()));
        } else {
            tvPointReward.setText("null");
        }

        if (benefit.getPointTrip() != -1) {
            tvPointTrip.setText(String.valueOf(benefit.getPointTrip()));
        } else {
            tvPointTrip.setText("null");
        }

        if (benefit.getFIDMcy() != null) {
            tvFidMcy.setText(benefit.getFIDMcy());
        } else {
            tvFidMcy.setText("null");
        }

        if (benefit.getFIDCar() != null) {
            tvFidCar.setText(benefit.getFIDCar());
        } else {
            tvFidCar.setText("null");
        }

        if (benefit.getMatriksAXI() != null) {
            tvAxiMatrix.setText(benefit.getMatriksAXI());
        } else {
            tvAxiMatrix.setText("null");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((ProfileActivity) requireActivity()).data != null) {
            benefitContainer.setVisibility(View.VISIBLE);
            pbBenefit.setVisibility(View.GONE);
            setData(((ProfileActivity) requireActivity()).data);
        }
    }

    @Override
    public void update() {
        if (((ProfileActivity) requireActivity()).data != null) {
            setData(((ProfileActivity) requireActivity()).data);
            pbBenefit.setVisibility(View.GONE);
            benefitContainer.setVisibility(View.VISIBLE);
        }
    }

}
