package com.design.todaynews.ui.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.design.todaynews.ui.fragment.NewsCategoryRankFragment;

import java.util.List;

public class NewsCategoryPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "NewsCategoryPagerAdapte";
    private final List<String> tabTitleList;
    private final List<String> categoryIDList;

    public NewsCategoryPagerAdapter(@NonNull FragmentManager fm, int behavior, List<String> tabList,List<String> idList) {
        super(fm,behavior);
        this.tabTitleList = tabList;
        this.categoryIDList = idList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //Log.d(TAG, "数据点位: 分类的ID " + categoryIDList.get(position));
        return NewsCategoryRankFragment.newInstance(categoryIDList.get(position));
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
