package id.variable.dicicilaja.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.variable.dicicilaja.API.Client.RetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfaceRequest;
import id.variable.dicicilaja.API.Interface.InterfaceTask;
import id.variable.dicicilaja.API.Item.Request.Request;
import id.variable.dicicilaja.API.Item.Task.Datum;
import id.variable.dicicilaja.API.Item.Task.Task;
import id.variable.dicicilaja.Activity.DetailRequestActivity;
import id.variable.dicicilaja.Adapter.RequestAdapter;
import id.variable.dicicilaja.Adapter.TaskAdapter;
import id.variable.dicicilaja.Listener.ClickListener;
import id.variable.dicicilaja.Listener.RecyclerTouchListener;
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
public class InprogressFragment extends Fragment {

    private static final String TAG = InprogressFragment.class.getSimpleName();
    List<id.variable.dicicilaja.API.Item.Request.Datum> requests;
    List<Datum> tasks;
    RequestProcess interfaceTCProcess;
    Integer positionCard;
    String apiKey;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inprogress, container, false);

        final SessionManager session = new SessionManager(getContext());
        apiKey = "Bearer " + session.getToken();

        TextView title_pengumuman = view.findViewById(R.id.title_pengumuman);
        final TextView jumlah_pengajuan = view.findViewById(R.id.jumlah_pengajuan);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");


        title_pengumuman.setTypeface(opensans_bold);
        jumlah_pengajuan.setTypeface(opensans_bold);

        final RecyclerView recyclerView =  view.findViewById(R.id.recycler_pengajuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        interfaceTCProcess = ApiUtils.getRequestService();

        if(session.getRole().equals("admin") || session.getRole().equals("tc")){
            InterfaceRequest apiService =
                    RetrofitClient.getClient().create(InterfaceRequest.class);

            Call<Request> call = apiService.getRequest(apiKey);
            call.enqueue(new Callback<Request>() {
                @Override
                public void onResponse(Call<Request> call, Response<Request> response) {
                    if ( response.isSuccessful() ) {
                        requests = response.body().getData();
                        jumlah_pengajuan.setText(Integer.toString(requests.size()));
                        recyclerView.setAdapter(new RequestAdapter(requests, R.layout.card_pengajuan, getContext()));


                        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
                            @Override
                            public void onClick(View view, final int position) {
                                if (requests.get(position).getStatus().toString().equals("Terkirim")) {
                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

                                    // Setting Dialog Title
                                    alertDialog.setTitle("Proses Pengajuan");

                                    // Setting Dialog Message
                                    alertDialog.setMessage("Dengan menekan tombol \"lanjutkan\" Anda bertanggung jawab untuk segera menindaklanjuti pengajuan ini.");


                                    // Setting Positive "Yes" Button
                                    alertDialog.setPositiveButton("LANJUTKAN", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            String transaction_id = requests.get(position).getId().toString();
                                            String assigned_id = session.getUserId();
                                            String notes = "-";
//                                            Toast.makeText(getContext(),"api key : " + apiKey + " transcation_id : " + transaction_id + " assigned_id : " + assigned_id + " notes : " + notes,Toast.LENGTH_LONG).show();
                                            doProcess(apiKey, transaction_id, assigned_id, notes);
                                            Intent intent = new Intent(getContext(), DetailRequestActivity.class);

                                            intent.putExtra("EXTRA_REQUEST_ID", requests.get(position).getId().toString());
                                            startActivity(intent);
                                        }
                                    });

                                    // Setting Negative "NO" Button
//                                alertDialog.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.cancel();
//                                    }
//                                });

                                    // Showing Alert Message
                                    alertDialog.show();
                                }else if (requests.get(position).getStatus().toString().equals("Verifikasi")) {
                                    Intent intent = new Intent(getContext(), DetailRequestActivity.class);
                                    intent.putExtra("EXTRA_REQUEST_ID", requests.get(position).getId().toString());
                                    startActivity(intent);
                                }


                            }

                            @Override
                            public void onLongClick(View view, int position) {
                            }
                        }));


                    } else {
                        Toast.makeText(getContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<Request> call, Throwable t) {
                    // Log error here since request failed
                    Toast.makeText(getContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                    Log.e(TAG, t.toString());
                }
            });
        }else if(session.getRole().equals("crh") || session.getRole().equals("cro")){
            InterfaceTask apiService =
                    RetrofitClient.getClient().create(InterfaceTask.class);

            Call<Task> call = apiService.getTask(apiKey);
            call.enqueue(new Callback<Task>() {
                @Override
                public void onResponse(Call<Task> call, Response<Task> response) {
                    if ( response.isSuccessful() ) {
                        tasks = response.body().getData();
                        jumlah_pengajuan.setText(Integer.toString(tasks.size()));
                        recyclerView.setAdapter(new TaskAdapter(tasks, R.layout.card_pengajuan, getContext()));


                        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
                            @Override
                            public void onClick(View view, final int position) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

                                // Setting Dialog Title
                                alertDialog.setTitle("Proses Pengajuan");

                                // Setting Dialog Message
                                alertDialog.setMessage("Dengan menekan tombol \"lanjutkan\" Anda bertanggung jawab untuk segera menindaklanjuti pengajuan ini.");


                                // Setting Positive "Yes" Button
                                alertDialog.setPositiveButton("LANJUTKAN", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        String transaction_id = tasks.get(position).getId().toString();
                                        String assigned_id = session.getUserId();
                                        String notes = "-";
                                        doProcess(apiKey, transaction_id, assigned_id, notes);
                                        Intent intent = new Intent(getContext(), DetailRequestActivity.class);
                                        intent.putExtra("EXTRA_REQUEST_ID", tasks.get(position).getTransactionId().toString());
                                        startActivity(intent);
                                    }
                                });

                                // Setting Negative "NO" Button
//                                alertDialog.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.cancel();
//                                    }
//                                });

                                // Showing Alert Message
                                alertDialog.show();
                            }

                            @Override
                            public void onLongClick(View view, int position) {
                            }
                        }));


                    } else {
                        Toast.makeText(getContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<Task> call, Throwable t) {
                    // Log error here since request failed
                    Toast.makeText(getContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                    Log.e(TAG, t.toString());
                }
            });
        }else{

        }





        return view;
    }

    private void doProcess(final String apiKey, final String transaction_id, final String assigned_id, final String notes) {
        Call<ResRequestProcess> call = interfaceTCProcess.assign(apiKey,transaction_id, assigned_id, notes);
        call.enqueue(new Callback<ResRequestProcess>() {
            @Override
            public void onResponse(Call<ResRequestProcess> call, Response<ResRequestProcess> response) {
//                Toast.makeText(getContext(),"code :" + response.code(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResRequestProcess> call, Throwable t) {

            }
        });
    }
}