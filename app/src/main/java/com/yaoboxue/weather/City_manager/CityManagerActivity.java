package com.yaoboxue.weather.City_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yaoboxue.weather.City_manager.DeleteCityActivity;
import com.yaoboxue.weather.City_manager.SearchCityActivity;
import com.yaoboxue.weather.DB.DBmanager;
import com.yaoboxue.weather.DB.DatabaseBean;
import com.yaoboxue.weather.R;

import java.util.ArrayList;
import java.util.List;

public class CityManagerActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView addcity,deletecity,back;
    ListView cityListView;
    List<DatabaseBean>mdatas;// 显示数据源
    CityManagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manager);
        addcity = findViewById(R.id.city_add);
        deletecity = findViewById(R.id.city_delete);
        back = findViewById(R.id.city_back);
        cityListView = findViewById(R.id.city_listview);
        mdatas = new ArrayList<>();
        //设置适配器
        addcity.setOnClickListener(this);
        deletecity.setOnClickListener(this);
        deletecity.setOnClickListener(this);
        adapter = new CityManagerAdapter(this,mdatas);
        cityListView.setAdapter(adapter);
    }
    /*
    获取数据库中真是数据源，提示适配器更新。
     */
    @Override
    protected void onResume() {
        super.onResume();
        List<DatabaseBean> list = DBmanager.querryAllInfo();
        mdatas.clear();
        mdatas.addAll(list);
        //提示更新
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city_add:
                int cityCount = DBmanager.getCityCount();
                if (cityCount<10){
                    Intent intent = new Intent(this, SearchCityActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "存储已达上限，请删除以后在增加", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.city_delete:
                Intent intent1 = new Intent(this, DeleteCityActivity.class);
                startActivity(intent1);
                break;
            case R.id.city_back:
                finish();
                break;
        }
    }
}
