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
import id.variable.dicicilaja.API.Client.RetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfaceDetailPengajuanStatus;
import id.variable.dicicilaja.API.Interface.InterfaceDetailRequest;
import id.variable.dicicilaja.API.Item.DetailPengajuan;
import id.variable.dicicilaja.API.Item.DetailPengajuanResponse;
import id.variable.dicicilaja.API.Item.DetailPengajuanStatus;
import id.variable.dicicilaja.API.Item.DetailPengajuanStatusResponse;
import id.variable.dicicilaja.API.Item.DetailRequest.Datum;
import id.variable.dicicilaja.API.Item.DetailRequest.DetailRequest;
import id.variable.dicicilaja.API.Item.DetailRequest.Progress;
import id.variable.dicicilaja.API.Item.DetailRequest.SurveyChecklist;
import id.variable.dicicilaja.API.Item.Status;
import id.variable.dicicilaja.API.Item.StatusDetail;
import id.variable.dicicilaja.API.Item.Transaction;
import id.variable.dicicilaja.Activity.ProsesPengajuan2Activity;
import id.variable.dicicilaja.Activity.ProsesPengajuan3Activity;
import id.variable.dicicilaja.Activity.ProsesPengajuanActivity;
import id.variable.dicicilaja.Activity.RequestProcessActivity;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Remote.RequestProcess;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatPengajuanFragment extends Fragment {

    private static final String TAG = RiwayatPengajuanFragment.class.getSimpleName();
    List<Datum> detailRequests;
    List<Progress> progress;
    List<SurveyChecklist> surveyChecklists;

    RelativeLayout terkirimCard, verifikasiCard, prosesCard, surveyCard, survey1Card, survey2Card, pendingCard, analisaCard, ditolakCard, pencairanCard;
    TextView titleTerkirim, namaTerkirim, durasiTerkirim, titleVerifikasi, namaVerifikasi, durasiVerifikasi, titleProses, namaProses, durasiProses, titleSurvey, namaSurvey, durasiSurvey, titleSurvey1, namaSurvey1, durasiSurvey1, titleSurvey2, namaSurvey2, durasiSurvey2, titlePending, namaPending, durasiPending, titleAnalisa, namaAnalisa, durasiAnalisa, titleDitolak, namaDitolak, durasiDitolak, titlePencairan, namaPencairan, durasiPencairan;
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

        terkirimCard = view.findViewById(R.id.terkirim_card);
        verifikasiCard = view.findViewById(R.id.verifikasi_card);
        prosesCard = view.findViewById(R.id.proses_card);
        surveyCard = view.findViewById(R.id.survey_card);
        survey1Card = view.findViewById(R.id.survey1_card);
        survey2Card = view.findViewById(R.id.survey2_card);
        analisaCard = view.findViewById(R.id.analisa_card);
        pencairanCard = view.findViewById(R.id.pencairan_card);

        titleTerkirim = view.findViewById(R.id.title_terkirim);
        namaTerkirim = view.findViewById(R.id.nama_terkirim);
        durasiTerkirim = view.findViewById(R.id.durasi_terkirim);

        titleVerifikasi = view.findViewById(R.id.title_verifikasi);
        namaVerifikasi = view.findViewById(R.id.nama_verifikasi);
        durasiVerifikasi = view.findViewById(R.id.durasi_verifikasi);

        titleProses = view.findViewById(R.id.title_proses);
        namaProses = view.findViewById(R.id.nama_proses);
        durasiProses = view.findViewById(R.id.durasi_proses);

        titleSurvey = view.findViewById(R.id.title_survey);
        namaSurvey = view.findViewById(R.id.nama_survey);
        durasiSurvey = view.findViewById(R.id.durasi_survey);

        titleSurvey1 = view.findViewById(R.id.title_survey1);
        namaSurvey1 = view.findViewById(R.id.nama_survey1);
        durasiSurvey1 = view.findViewById(R.id.durasi_survey1);

        titleSurvey2 = view.findViewById(R.id.title_survey2);
        namaSurvey2 = view.findViewById(R.id.nama_survey2);
        durasiSurvey2 = view.findViewById(R.id.durasi_survey2);

        titleAnalisa = view.findViewById(R.id.title_analisa);
        namaAnalisa = view.findViewById(R.id.nama_analisa);
        durasiAnalisa = view.findViewById(R.id.durasi_analisa);

        titlePencairan = view.findViewById(R.id.title_pencairan);
        namaPencairan = view.findViewById(R.id.nama_pencairan);
        durasiPencairan = view.findViewById(R.id.durasi_pencairan);


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

        InterfaceDetailRequest apiService = RetrofitClient.getClient().create(InterfaceDetailRequest.class);

        Call<DetailRequest> call = apiService.getDetailRequest(apiKey, Integer.parseInt(getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID")));
        call.enqueue(new Callback<DetailRequest>() {
            @Override
            public void onResponse(Call<DetailRequest> call, Response<DetailRequest> response) {
                try {
                    detailRequests = response.body().getData();
                    progress = response.body().getProgress();
                    surveyChecklists = response.body().getSurveyChecklist();

                    responsiblePersonName.setText(detailRequests.get(0).getResponsiblePerson().getName());
                    responsiblePersonRole.setText(detailRequests.get(0).getResponsiblePerson().getRole());
                    responsiblePersonId.setText(detailRequests.get(0).getResponsiblePerson().getUserId());
                    responsiblePersonResponseTime.setText(detailRequests.get(0).getResponsiblePerson().getResponseTime());
                    responsiblePersonNote.setText(detailRequests.get(0).getResponsiblePerson().getCatatan());

                } catch (Exception ex) {

                }

                try {
                    titleTerkirim.setText(progress.get(0).getStatus());
                    namaTerkirim.setText(progress.get(0).getResponsiblePerson());
                    durasiTerkirim.setText(progress.get(0).getResponseTime());
                } catch (Exception ex) {
                    terkirimCard.setVisibility(View.GONE);
                }

                try {
                    titleVerifikasi.setText(progress.get(1).getStatus());
                    namaVerifikasi.setText(progress.get(1).getResponsiblePerson());
                    durasiVerifikasi.setText(progress.get(1).getResponseTime());
                } catch (Exception ex) {
                    verifikasiCard.setVisibility(View.GONE);
                }

                try {
                    titleProses.setText(progress.get(2).getStatus());
                    namaProses.setText(progress.get(2).getResponsiblePerson());
                    durasiProses.setText(progress.get(2).getResponseTime());
                } catch (Exception ex) {
                    prosesCard.setVisibility(View.GONE);
                }

                try {
                    titleSurvey.setText(progress.get(3).getStatus());
                    namaSurvey.setText(progress.get(3).getResponsiblePerson());
                    durasiSurvey.setText(progress.get(3).getResponseTime());
                } catch (Exception ex) {
                    surveyCard.setVisibility(View.GONE);
                }

                try {
                    titleSurvey1.setText(progress.get(4).getStatus());
                    namaSurvey1.setText(progress.get(4).getResponsiblePerson());
                    durasiSurvey1.setText(progress.get(4).getResponseTime());
                } catch (Exception ex) {
                    survey1Card.setVisibility(View.GONE);
                }

                try {
                    titleSurvey2.setText(progress.get(5).getStatus());
                    namaSurvey2.setText(progress.get(5).getResponsiblePerson());
                    durasiSurvey2.setText(progress.get(5).getResponseTime());
                } catch (Exception ex) {
                    survey2Card.setVisibility(View.GONE);
                }

                try {
                    titleAnalisa.setText(progress.get(6).getStatus());
                    namaAnalisa.setText(progress.get(6).getResponsiblePerson());
                    durasiAnalisa.setText(progress.get(6).getResponseTime());
                } catch (Exception ex) {
                    analisaCard.setVisibility(View.GONE);
                }

                try {
                    titlePencairan.setText(progress.get(7).getStatus());
                    namaPencairan.setText(progress.get(7).getResponsiblePerson());
                    durasiPencairan.setText(progress.get(7).getResponseTime());
                } catch (Exception ex) {
                    pencairanCard.setVisibility(View.GONE);
                }



            }

            @Override
            public void onFailure(Call<DetailRequest> call, Throwable t) {
                Log.e("ini", t.getMessage());
            }
        });

        Intent intent = getActivity().getIntent();
        if(intent.hasExtra("STATUS")) {
            proses.setVisibility(View.GONE);
        }else{
            if(session.getRole().equals("tc")){
                proses.setVisibility(View.VISIBLE);
                proses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), ProsesPengajuanActivity.class);
                        intent.putExtra("TRANSACTION_ID", getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID").toString());
                        startActivity(intent);
                    }
                });
            }else if(session.getRole().equals("crh")){
                proses.setVisibility(View.VISIBLE);
                proses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), RequestProcessActivity.class);
                        intent.putExtra("TRANSACTION_ID", getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID").toString());
                        intent.putExtra("NAME", detailRequests.get(0).getResponsiblePerson().getName());
                        intent.putExtra("ROLE", detailRequests.get(0).getResponsiblePerson().getRole());
                        intent.putExtra("RESPONSE_TIME", detailRequests.get(0).getResponsiblePerson().getResponseTime());
                        intent.putExtra("NOTE", detailRequests.get(0).getResponsiblePerson().getCatatan());
                        intent.putExtra("STATUS_SURVEY", detailRequests.get(0).getStatus_survey());
                        startActivity(intent);
                    }
                });
            }else if(session.getRole().equals("cro")){
                proses.setVisibility(View.VISIBLE);
                proses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), RequestProcessActivity.class);
                        intent.putExtra("TRANSACTION_ID", getActivity().getIntent().getStringExtra("EXTRA_REQUEST_ID").toString());
                        intent.putExtra("NAME", detailRequests.get(0).getResponsiblePerson().getName());
                        intent.putExtra("ID", detailRequests.get(0).getResponsiblePerson().getUserId());
                        intent.putExtra("ROLE", detailRequests.get(0).getResponsiblePerson().getRole());
                        intent.putExtra("RESPONSE_TIME", detailRequests.get(0).getResponsiblePerson().getResponseTime());
                        intent.putExtra("NOTE", detailRequests.get(0).getResponsiblePerson().getCatatan());
                        intent.putExtra("STATUS_SURVEY", detailRequests.get(0).getStatus_survey());

                        intent.putExtra("KTP_SUAMI", surveyChecklists.get(0).getKtpSuami());
                        intent.putExtra("KTP_PENJAMIN", surveyChecklists.get(0).getKtpPenjamin());
                        intent.putExtra("SURAT_CERAI", surveyChecklists.get(0).getSuratCerai());
                        intent.putExtra("SURAT_KEMATIAN", surveyChecklists.get(0).getSuratKematian());
                        intent.putExtra("SURAT_DOMISILI", surveyChecklists.get(0).getSuratDomisili());
                        intent.putExtra("KARTU_KELUARGA", surveyChecklists.get(0).getKartuKeluarga());
                        intent.putExtra("BUKTI_KEPEMILIKAN_RUMAH", surveyChecklists.get(0).getBuktiKepemilikanRumah());
                        intent.putExtra("BUKTI_PENGHASILAN", surveyChecklists.get(0).getBuktiPenghasilan());
                        intent.putExtra("NO_RANGKA", surveyChecklists.get(0).getNoRangka());
                        intent.putExtra("STNK", surveyChecklists.get(0).getStnk());
                        intent.putExtra("BPKB", surveyChecklists.get(0).getBpkb());
                        try {
                            intent.putExtra("RESCHEDULE_DATE", surveyChecklists.get(0).getRescheduleDate().toString());
                        } catch (Exception ex) {

                        }
                        startActivity(intent);
                    }
                });
            }else{
                proses.setVisibility(View.GONE);
            }
        }

        return view;
    }

}
