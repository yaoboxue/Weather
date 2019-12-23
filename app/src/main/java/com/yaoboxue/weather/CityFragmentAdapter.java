package com.yaoboxue.weather;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @Author yaoboxue
 * @Date 2019.12.12  16:39
 * @Version 1.0
 */
public class CityFragmentAdapter extends FragmentStatePagerAdapter
{
    List<Fragment>fragmentList;
    public CityFragmentAdapter(FragmentManager fm, List<Fragment> fragmentLis) {
        super(fm);
        this.fragmentList = fragmentLis;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
