package com.example.mrx.exchangeandusatoeuconverter.Adapters;

import android.view.ViewGroup;

import com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments.FragmentConverter;
import com.example.mrx.exchangeandusatoeuconverter.ActivitiesAndFragments.FragmentUStoEU;
import com.example.mrx.exchangeandusatoeuconverter.Helpers.GetDrawable;
import com.example.mrx.exchangeandusatoeuconverter.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<Fragment>(){{
        add(new FragmentConverter());
        add(new FragmentUStoEU());
    }};
    private final List<String> titleList = new ArrayList<String>(){{
        add(GetDrawable.getStringResource(R.string.currrency_converter));
        add(GetDrawable.getStringResource(R.string.us_to_eu));
    }};

    public MyFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        for (Fragment fragment : fragmentList) {
            fragment.setRetainInstance(true);
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Object ret = super.instantiateItem(container, position);
        fragmentList.remove(position);
        fragmentList.add(position, (Fragment) ret);
        return ret;
    }
}
