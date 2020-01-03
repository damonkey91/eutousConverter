package com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments;


import android.os.Bundle;

import com.example.mrx.exchangeandusatoeuconverter.Adapters.MyFragmentPagerAdapter;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.GetDrawable;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.HideSoftKeyboard;
import com.example.mrx.exchangeandusatoeuconverter.R;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class Exchange extends AppCompatActivity {

    private FragmentPagerAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolbarColor(GetDrawable.getColorResource(R.color.colorToolbar));

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
                Fragment fragment = ((FragmentPagerAdapter) viewPager.getAdapter()).getItem(tab.getPosition());
                HideSoftKeyboard.hide(fragment.getView());
                if (tab.getPosition() == 1){
                    ((FragmentUStoEU) fragment).changeAdapterOrigin();
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        FragmentUStoEU fragmentUStoEU = ((FragmentUStoEU)((FragmentPagerAdapter) viewPager.getAdapter()).getItem(1));
        if (fragmentUStoEU.closeApp)
            super.onBackPressed();
        else
            fragmentUStoEU.changeAdapterOrigin();
    }

    private void createFragmentAdapter(){
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
    }

    public void setToolbarColor(int color){
        toolbar.setBackgroundColor(color);
    }

}