package com.yaoboxue.weather;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.yaoboxue.weather.City_manager.CityManagerActivity;
import com.yaoboxue.weather.DB.DBmanager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView addCity,more;
    private LinearLayout pointLayout;
    private ViewPager mianVp;
    //ViewPager的数据源
    List<Fragment>fragmentList;
    //选中的城市的集合
    List<String>cityList;
    //ViewPager的页面指示器显示集合
    List<ImageView>imgList;

    private CityFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addCity = findViewById(R.id.main_city_add);
        more = findViewById(R.id.main_more);
        pointLayout = findViewById(R.id.main_buttom_print);
        mianVp = findViewById(R.id.main_vp);
        //添加点击事件
        addCity.setOnClickListener(this);
        more.setOnClickListener(this);
        fragmentList = new ArrayList<>();
        cityList = DBmanager.querryAllCity();
        imgList = new ArrayList<>();

        if (cityList.size()==0) {
            cityList.add("长沙");

        }
        /*
        获取搜索界面传过来的值
         */
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        if (!cityList.contains(city)&&!TextUtils.isEmpty(city)) {
            cityList.add(city);
        }
        //初始化ViewPager
        initPages();
        adapter = new CityFragmentAdapter(getSupportFragmentManager(), fragmentList);
        mianVp.setAdapter(adapter);
        //创建指示器
        initPoint();
        //设置最后一个添加得城市为主页面
        mianVp.setCurrentItem(fragmentList.size()-1);
        //设置ViewPager监听器
        setPagerListener();
    }

    private void setPagerListener() {
        /*
        设置监听事件
         */
        mianVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              /* for (int i = 0; i < imgList.size(); i++) {
                    imgList.get(i).setImageResource(R.mipmap.a1);
                }
                imgList.get(position).setImageResource(R.mipmap.a2);*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initPoint() {
        //创建小圆点
        /*for (int i = 0; i < fragmentList.size(); i++) {
            ImageView PointIma = new ImageView(this);
            PointIma.setImageResource(R.mipmap.a1);
            pointLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) pointLayout.getLayoutParams();
            layoutParams.setMargins(0,0,20,0);
            imgList.add(PointIma);
            pointLayout.addView(PointIma);
        }
        imgList.get(imgList.size()-1).setImageResource(R.mipmap.a2);*/
    }

    private void initPages() {
        //创建Fragement对象，添加到View Pager中
        for (int i = 0; i < cityList.size(); i++) {
            CityWeatherFragment cWFragment = new CityWeatherFragment();
            Bundle data = new Bundle();
            data.putString("city",cityList.get(i));
            cWFragment.setArguments(data);
            fragmentList.add(cWFragment);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        //判断哪个按钮被点击
        switch (v.getId()){
            case R.id.main_city_add:
                intent.setClass(this, CityManagerActivity.class);
                break;

            case R.id.main_more:
                break;

        }
        startActivity(intent);
    }
}
