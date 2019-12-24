package com.yaoboxue.weather.City_manager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.yaoboxue.weather.DB.DBmanager;
import com.yaoboxue.weather.R;

import java.util.ArrayList;
import java.util.List;

public class DeleteCityActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView error,right;
    ListView delete;
    //listView的数据源
    List<String>mDatas;
    //存储了删除的城市信息
    List<String>deletecity;

    private DeleteCityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_city);
        error = findViewById(R.id.delete_iv_error);
        right = findViewById(R.id.delete_iv_right);
        delete = findViewById(R.id.delete_lv);
        mDatas = new ArrayList<>();
        deletecity = new ArrayList<>();
        //设置点击事件
        error.setOnClickListener(this);
        right.setOnClickListener(this);
        //设置适配器
        adapter = new DeleteCityAdapter(this,mDatas,deletecity);
        delete.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> cityList = DBmanager.querryAllCity();
        mDatas.addAll(cityList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_iv_error:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示信息").setMessage("你确实要取消更改")
                        .setPositiveButton("舍弃更改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //关闭当前acticity
                                finish();
                            }
                        });
                builder.setNegativeButton("取消",null);
                builder.create().show();
                break;
            case R.id.delete_iv_right:
                for (int i = 0; i < deletecity.size(); i++) {
                    String city = deletecity.get(i);
                    //调用删除城市的函数
                    DBmanager.deleteInfoByCity(city);
                }
                //删除成功后返回上机页面
                finish();
                break;
        }

    }
}
