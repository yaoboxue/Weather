package com.yaoboxue.weather.City_manager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yaoboxue.weather.Base.BaseActivity;
import com.yaoboxue.weather.Bean.WeatherBean;
import com.yaoboxue.weather.MainActivity;
import com.yaoboxue.weather.R;

public class SearchCityActivity extends BaseActivity implements View.OnClickListener{
    EditText search;
    ImageView submit;
    GridView search_hot;

    String[]hotCitys = {"北京","上海","广州","深圳","珠海","佛山","南京","苏州","厦门","长沙","成都","福州",
            "杭州","武汉","青岛","西安","太原","沈阳","重庆","天津","南宁"};

    String url1 = "http://v.juhe.cn/weather/index?format=2&cityname=";
    String url2 = "&key=62057c230200c5151e6479e3ae074b48";

    String city;

    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        search = findViewById(R.id.search_editText);
        submit = findViewById(R.id.search_submit);
        search_hot = findViewById(R.id.search_gridView);
        submit.setOnClickListener(this);

        //设置适配器
        adapter = new ArrayAdapter<>(this, R.layout.item_hot_city, hotCitys);
        search_hot.setAdapter(adapter);

        setListener();

    }

    /*
    设置监听事件
     */
    private void setListener() {
        search_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               city = hotCitys[position];
               String url = url1+city+url2;
               loadData(url);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_submit:
                city = search.getText().toString();
                if (!TextUtils.isEmpty(city)) {
                    //判断是否查询到这个城市
                    String url = url1+city+url2;
                    loadData(url);
                }else {
                    Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onSuccess(String result) {
        super.onSuccess(result);
        WeatherBean weatherBean = new Gson().fromJson(result, WeatherBean.class);
        if (weatherBean.getError_code()==0){
            //开启新栈，跳转到新界面
            Intent intent = new Intent(this, MainActivity.class);
            //清空原来的栈,开启新栈
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("city",city);
            startActivity(intent);

        }else {
            Toast.makeText(this, "暂时未找到城市，请重新输入", Toast.LENGTH_SHORT).show();
        }
    }
}
