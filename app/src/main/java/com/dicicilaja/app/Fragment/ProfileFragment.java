package com.dicicilaja.app.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.dicicilaja.app.Activity.LoginActivity;
import com.dicicilaja.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


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
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        mTitle.setTypeface(opensans_bold);
        btnLogout = (TextView) view.findViewById(R.id.btnLogout);
        name = (TextView) view.findViewById(R.id.name);
        title_name = (TextView) view.findViewById(R.id.name_title);
        email = (TextView) view.findViewById(R.id.email);
        title_email = (TextView) view.findViewById(R.id.email_title);
        phone = (TextView) view.findViewById(R.id.phone);
        title_phone = (TextView) view.findViewById(R.id.phone_title);

        name.setTypeface(opensans_reguler);
        title_name.setTypeface(opensans_bold);
        email.setTypeface(opensans_reguler);
        title_email.setTypeface(opensans_bold);
        phone.setTypeface(opensans_reguler);
        title_phone.setTypeface(opensans_bold);
        btnLogout.setTypeface(opensans_bold);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
