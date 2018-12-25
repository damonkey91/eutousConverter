package com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments;


import android.os.Bundle;
import android.view.View;

import com.example.mrx.exchangeandusatoeuconverter.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class Exchange extends AppCompatActivity {

    private FragmentPagerAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        createFragmentAdapter();
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void onNrButtonPressed(View view){

    }

    private void createFragmentAdapter(){
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            private final List<Fragment> fragmentList = new ArrayList<Fragment>(){{
                add(new FragmentConverter());
                add(new FragmentUStoEU());
            }};
            private final List<String> titleList = new ArrayList<String>(){{
                add(getResources().getString(R.string.currrency_converter));
                add(getResources().getString(R.string.us_to_eu));
            }};

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
    }

}
