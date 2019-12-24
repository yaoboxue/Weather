package com.yaoboxue.weather;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

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

    //表示viewPager包含的页数
    int childCount = 0;
    //当viewPager的页数发生改变时，必须要重写两个函数

    @Override
    public void notifyDataSetChanged() {
        this.childCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (childCount>0) {
            childCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
