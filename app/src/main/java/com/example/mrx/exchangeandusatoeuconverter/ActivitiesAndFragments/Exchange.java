package com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments;


import android.os.Bundle;
import android.view.View;

import com.example.mrx.exchangeandusatoeuconverter.Adapters.MyFragmentPagerAdapter;
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
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1){
                    Fragment fragment = ((FragmentPagerAdapter) viewPager.getAdapter()).getItem(tab.getPosition());
                    ((FragmentUStoEU) fragment).changeAdapterOrigin();
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void onNrButtonPressed(View view){

    }

    private void createFragmentAdapter(){
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
    }

}
