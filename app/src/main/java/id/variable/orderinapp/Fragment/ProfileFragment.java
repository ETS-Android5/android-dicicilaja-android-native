package id.variable.orderinapp.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import id.variable.orderinapp.LoginActivity;
import id.variable.orderinapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private FirebaseAuth firebaseAuth;
    private TextView btnLogout, name, title_name, email, title_email, phone, title_phone;
    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        Typeface nunito_black = Typeface.createFromAsset(getContext().getAssets(), "fonts/Nunito-Black.ttf");
        Typeface nunito_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Nunito-Bold.ttf");
        Typeface nunito_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Nunito-SemiBold.ttf");
        Typeface nunito_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/Nunito-Regular.ttf");
        mTitle.setTypeface(nunito_bold);
        btnLogout = (TextView) view.findViewById(R.id.btnLogout);
        name = (TextView) view.findViewById(R.id.name);
        title_name = (TextView) view.findViewById(R.id.name_title);
        email = (TextView) view.findViewById(R.id.email);
        title_email = (TextView) view.findViewById(R.id.email_title);
        phone = (TextView) view.findViewById(R.id.phone);
        title_phone = (TextView) view.findViewById(R.id.phone_title);

        name.setTypeface(nunito_reguler);
        title_name.setTypeface(nunito_bold);
        email.setTypeface(nunito_reguler);
        title_email.setTypeface(nunito_bold);
        phone.setTypeface(nunito_reguler);
        title_phone.setTypeface(nunito_bold);
        btnLogout.setTypeface(nunito_bold);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(),"Berhasil keluar",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.actionbar_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
