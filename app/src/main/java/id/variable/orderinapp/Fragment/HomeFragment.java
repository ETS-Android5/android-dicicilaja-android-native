package id.variable.orderinapp.Fragment;


import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.variable.orderinapp.HomePagerAdapter;
import id.variable.orderinapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_history);
        tabLayout.addTab(tabLayout.newTab().setText("MENUNGGU"));
        tabLayout.addTab(tabLayout.newTab().setText("SELESAI"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        Typeface nunito_black = Typeface.createFromAsset(getContext().getAssets(), "fonts/Nunito-Black.ttf");
        Typeface nunito_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Nunito-Bold.ttf");
        Typeface nunito_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Nunito-SemiBold.ttf");
        Typeface nunito_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/Nunito-Regular.ttf");
        mTitle.setTypeface(nunito_bold);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        viewPager.setAdapter(new HomePagerAdapter(getFragmentManager(), 2));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.actionbar_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
