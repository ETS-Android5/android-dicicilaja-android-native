package id.variable.dicicilaja.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.variable.dicicilaja.API.Client.ClientDetailPengajuanStatus;
import id.variable.dicicilaja.API.Interface.InterfaceDetailPengajuanStatus;
import id.variable.dicicilaja.API.Item.DetailPengajuan;
import id.variable.dicicilaja.API.Item.DetailPengajuanResponse;
import id.variable.dicicilaja.API.Item.DetailPengajuanStatus;
import id.variable.dicicilaja.API.Item.DetailPengajuanStatusResponse;
import id.variable.dicicilaja.API.Item.Status;
import id.variable.dicicilaja.API.Item.StatusDetail;
import id.variable.dicicilaja.API.Item.Transaction;
import id.variable.dicicilaja.Activity.ProsesPengajuan2Activity;
import id.variable.dicicilaja.Activity.ProsesPengajuan3Activity;
import id.variable.dicicilaja.Activity.ProsesPengajuanActivity;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatPengajuanFragment extends Fragment {

    private static final String TAG = RiwayatPengajuanFragment.class.getSimpleName();
    List<StatusDetail> statusDetails;
    List<DetailPengajuanStatusResponse> detailPengajuanStatuses;

    public RiwayatPengajuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_riwayat_pengajuan, container, false);

        final SessionManager session = new SessionManager(getContext());
        String apiKey = "Bearer " + session.getToken();

        RelativeLayout proses = view.findViewById(R.id.proses);
        final LinearLayout responsiblePersonNameLayout = view.findViewById(R.id.responsible_person_name_layout);
        final LinearLayout responsiblePersonRoleLayout = view.findViewById(R.id.responsible_person_role_layout);
        final LinearLayout responsiblePersonIdLayout = view.findViewById(R.id.responsible_person_id_layout);
        final LinearLayout responsiblePersonResponseTimeLayout = view.findViewById(R.id.responsible_person_response_time_layout);
        final LinearLayout responsiblePersonNoteLayout = view.findViewById(R.id.responsible_person_note_layout);

        TextView title_jejak = view.findViewById(R.id.title_jejak);
        TextView title_penanggung_jawab = view.findViewById(R.id.title_penanggung_jawab);

        final TextView responsiblePersonName = view.findViewById(R.id.responsible_person_name);
        final TextView responsiblePersonRole = view.findViewById(R.id.responsible_person_role);
        final TextView responsiblePersonId = view.findViewById(R.id.responsible_person_id);
        final TextView responsiblePersonResponseTime = view.findViewById(R.id.responsible_person_response_time);
        final TextView responsiblePersonNote = view.findViewById(R.id.responsible_person_response_note);

        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_jejak.setTypeface(opensans_bold);
        title_penanggung_jawab.setTypeface(opensans_bold);

        if(session.getRole() == "tc"){
            proses.setVisibility(View.VISIBLE);
            proses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ProsesPengajuanActivity.class);
                    intent.putExtra("EXTRA_REQUEST_ID", Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
                    Toast.makeText(getContext(), "Id : " + Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            });
        }else if(session.getRole() == "crh"){
            proses.setVisibility(View.VISIBLE);
            proses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ProsesPengajuan2Activity.class);
                    intent.putExtra("EXTRA_REQUEST_ID", Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
                    Toast.makeText(getContext(), "Id : " + Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            });
        }else if(session.getRole() == "cro"){
            proses.setVisibility(View.VISIBLE);
            proses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ProsesPengajuan3Activity.class);
                    intent.putExtra("EXTRA_REQUEST_ID", Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
                    Toast.makeText(getContext(), "Id : " + Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            });
        }else{
            proses.setVisibility(View.GONE);
        }

        InterfaceDetailPengajuanStatus apiService = ClientDetailPengajuanStatus.getClientDetailPengajuanStatus().create(InterfaceDetailPengajuanStatus.class);
        Call<DetailPengajuanStatus> call = apiService.getDetailPengajuanStatus(apiKey, Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
        call.enqueue(new Callback<DetailPengajuanStatus>() {
            @Override
            public void onResponse(Call<DetailPengajuanStatus> call, Response<DetailPengajuanStatus> response) {
                try {
                    detailPengajuanStatuses = response.body().getData();
                    statusDetails = detailPengajuanStatuses.get(0).getStatusDetail();

//                    if (statusDetails.get(statusDetails.size() - 1).getStatusName().equals("Terkirim")) {
//                        responsiblePersonNameLayout.setVisibility(View.GONE);
//                        responsiblePersonRoleLayout.setVisibility(View.GONE);
//                        responsiblePersonIdLayout.setVisibility(View.GONE);
//                        responsiblePersonResponseTimeLayout.setVisibility(View.GONE);
//                        responsiblePersonNoteLayout.setVisibility(View.GONE);
//                    } else {
//                        responsiblePersonNameLayout.setVisibility(View.VISIBLE);
//                        responsiblePersonRoleLayout.setVisibility(View.VISIBLE);
//                        responsiblePersonIdLayout.setVisibility(View.VISIBLE);
//                        responsiblePersonResponseTimeLayout.setVisibility(View.VISIBLE);
//                        responsiblePersonNoteLayout.setVisibility(View.VISIBLE);
//
//                        responsiblePersonName.setText(statusDetails.get(0).getResponsiblePerson().getName());
//                        responsiblePersonRole.setText(statusDetails.get(0).getResponsiblePerson().getRole());
//                        responsiblePersonId.setText(statusDetails.get(0).getResponsiblePerson().getId());
//                        responsiblePersonResponseTime.setText("0");
//                        responsiblePersonNote.setText(statusDetails.get(0).getNotes());
//                    }

                } catch (Exception ex) {
                    Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DetailPengajuanStatus> call, Throwable t) {
                Log.e("ini", t.getMessage());
            }
        });

        return view;
    }

}
