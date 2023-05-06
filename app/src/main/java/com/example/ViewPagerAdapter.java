package com.example;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    String [] title= new String[]{"Home","Health","Science","Technology","Entertainment"};

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new HomeFragment();
            case 1:
                return new HealthFragment();
            case 2:
                return new ScienceFragment();
            case 3:
                return new TechnologyFragment();
            case 4:
                return new EntertainmentFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return title.length;
    }
}
