package com.design.todaynews.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.design.todaynews.ui.fragment.PlaceholderFragment;

import java.util.List;

public class NewsSectionsPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> tabTitleList;

    public NewsSectionsPagerAdapter(@NonNull FragmentManager fm, int behavior, List<String> tabList) {
        super(fm,behavior);
        this.tabTitleList = tabList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(position+1);
    }

    @Override
    public int getCount() {
        return tabTitleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleList.get(position);
    }


}
