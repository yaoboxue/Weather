package com.yaoboxue.weather;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.yaoboxue.weather.Base.BaseFragment;
import com.yaoboxue.weather.Bean.WeatherBean;
import com.yaoboxue.weather.DB.DBmanager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass
 */

public class CityWeatherFragment extends BaseFragment implements View.OnClickListener, OnChartValueSelectedListener, OnChartGestureListener {
    private static final String TAG = "MainActivity";
    private static final String Key = "&key=8c1c69f446886d0656af1c89161aed0c";
    private static int FIRST = 0;


    private SwipeRefreshLayout swipeRefreshLayout;
    TextView temptex, citytex, conditiontex, windtex, temprangtex, datatex, clothindexTex, carIndexTex, coldIndexTex, sportIndexTex, raysIndexTex,timeTex;
    ImageView dayIma;
    LinearLayout futureLayout;
    LinearLayout lineChart;

    //折线图
    private RecyclerView recyclerView;
    List<Integer> Temdata;

    WeatherBean.ResultBean.TodayBean indexList = new WeatherBean.ResultBean.TodayBean();

    String url1 = "http://v.juhe.cn/weather/index?format=2&cityname=";
    String url2 = "&key=8c1c69f446886d0656af1c89161aed0c";

    private String city;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_weather, container, false);
        //初始化控件

        //
        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);

        Temdata = new ArrayList<>();
        Temdata.add(24);
        Temdata.add(18);
        Temdata.add(22);
        Temdata.add(19);
        Temdata.add(23);
        Temdata.add(24);
        Temdata.add(26);
        Temdata.add(28);

        initView(view);
        //通过activity传值取到当前fragment加载的是那个地方的天气情况
        Bundle data = getArguments();
        city = data.getString("city");
        Log.v(TAG,"city"+city);
        String url = url1+city+url2;
        //调取父类获取数据的方法
//        if (FIRST == 1){
//            loadData(url);
//            Log.v(TAG,"14"+FIRST);
//        }
        loadData(url);
        Log.v(TAG,"1");

        return view;

    }

    //获取成功时调用这个重写的方法

    @Override
    public void onSuccess(String result) {
        parseShowData(result);
        int i = DBmanager.updataInfoByCity(city,result);
        if (i<=0){
            //更新数据库失败,说明没有这个城市，增加这个城市记录；
            DBmanager.addCityInfo(city,result);
        }
        Log.v(TAG,"第一个成功"+result);
        //解析并展示数据
     /*   if (FIRST == 0) {
            parseShowData(result);
            int i = DBmanager.updataInfoByCity(city,result);
            if (i<=0){
                //更新数据库失败,说明没有这个城市，增加这个城市记录；
                DBmanager.addCityInfo(city,result);
            }
            Log.v(TAG,"第一个成功"+result);
            Log.v(TAG,"13"+FIRST);

        }else if (FIRST == 1){
            dealData(result);
            int i = DBmanager.updataInfoByCity(city,result);
            if (i<=0){
                //更新数据库失败,说明没有这个城市，增加这个城市记录；
                DBmanager.addCityInfo(city,result);
            }
            Log.v(TAG,"第一个成功"+result);
            Log.v(TAG,"12"+FIRST);
        }
        //更新数据
        parseShowData(result);
        int i = DBmanager.updataInfoByCity(city,result);
        if (i<=0){
            //更新数据库失败,说明没有这个城市，增加这个城市记录；
            DBmanager.addCityInfo(city,result);
        }
        Log.v(TAG,"第一个成功"+result);
        Log.v(TAG,"1"+FIRST);*/


    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        //查找上一次的城市信息显示在Fragmentz中
       String s = DBmanager.querryInfoByCity(city);
        if (!TextUtils.isEmpty(s)) {
            parseShowData(s);
        }
    }

    private void parseShowData(String result) {
        //使用gson解析数据
        WeatherBean weatherBean = new Gson().fromJson(result, WeatherBean.class);
        WeatherBean.ResultBean resultsBean = weatherBean.getResult();
        //获取指数信息集合列表
        indexList = resultsBean.getToday();
        Log.v(TAG,indexList.getCity());
        //设置textVlew
        datatex.setText(indexList.getDate_y()+indexList.getWeek());
        Log.v(TAG,"日期"+indexList.getDate_y());
        citytex.setText(indexList.getCity());
        //获取今日天气情况

        windtex.setText(indexList.getWind());
        temprangtex.setText(indexList.getTemperature());
        conditiontex.setText(indexList.getWeather());
        //获取实时天气情况
        temptex.setText(resultsBean.getSk().getTemp()+"℃");
        timeTex.setText(resultsBean.getSk().getTime());

        //显示天气情况得图片
        //Picasso.with(getActivity()).load(currentDataBean.getDayPictureUrl()).into(dayIma);
        //获取未来6天的天气情况，加载到layout中
        List<WeatherBean.ResultBean.FutureBean> futureList = resultsBean.getFuture();
        futureList.remove(0);
        Log.v(TAG,"大小"+futureList.size());
        for(int i=0; i<futureList.size(); i++){
            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.future_weather, null);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            futureLayout.addView(itemView);
            TextView idatatex = itemView.findViewById(R.id.future_data);
            TextView iconditiontes = itemView.findViewById(R.id.future_condition);
            TextView itempRangtex = itemView.findViewById(R.id.future_temp);
            //获取对应位置得天气情况
            WeatherBean.ResultBean.FutureBean dataBean = futureList.get(i);
            idatatex.setText(dataBean.getWeek());
            iconditiontes.setText(dataBean.getWeather());
            itempRangtex.setText(dataBean.getTemperature());
        }


    }

    //未使用
    public void dealData(String result){

        WeatherBean weatherBean = new Gson().fromJson(result, WeatherBean.class);
        WeatherBean.ResultBean resultsBean = weatherBean.getResult();
        //获取指数信息集合列表
        indexList = resultsBean.getToday();
        Log.v(TAG,indexList.getCity());
        //设置textVlew
        datatex.setText(indexList.getDate_y()+indexList.getWeek());
        Log.v(TAG,"日期"+indexList.getDate_y());
        citytex.setText(indexList.getCity());
        //获取今日天气情况

        windtex.setText(indexList.getWind());
        temprangtex.setText(indexList.getTemperature());
        conditiontex.setText(indexList.getWeather());
        //获取实时天气情况
        temptex.setText(resultsBean.getSk().getTemp()+"℃");
        timeTex.setText(resultsBean.getSk().getTime());

        //



        List<WeatherBean.ResultBean.FutureBean> futureList = resultsBean.getFuture();
        futureList.remove(0);
        Log.v(TAG,"大小"+futureList.size());
        for(int i=0; i<futureList.size(); i++) {
            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.future_weather, null);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            futureLayout.addView(itemView);
            TextView idatatex = itemView.findViewById(R.id.future_data);
            TextView iconditiontes = itemView.findViewById(R.id.future_condition);
            TextView itempRangtex = itemView.findViewById(R.id.future_temp);
            //获取对应位置得天气情况
            WeatherBean.ResultBean.FutureBean dataBean = futureList.get(i);
            idatatex.setText(dataBean.getWeek());
            iconditiontes.setText(dataBean.getWeather());
            itempRangtex.setText(dataBean.getTemperature());
        }



        //未使用，废弃
        View lineView = LayoutInflater.from(getActivity()).inflate(R.layout.linechar, null);
        lineView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        lineChart.addView(lineView);
        com.github.mikephil.charting.charts.LineChart mLineChar = lineView.findViewById(R.id.mLineChar);
        //设置手势滑动事件
        mLineChar.setOnChartGestureListener(this);
        //设置数值选择监听
        mLineChar.setOnChartValueSelectedListener(this);
        //后台绘制
        mLineChar.setDrawGridBackground(false);
        //设置描述文本
        mLineChar.getDescription().setEnabled(false);
        //设置支持触控手势
        mLineChar.setTouchEnabled(true);
        //设置缩放
        mLineChar.setDragEnabled(true);
        //设置推动
        mLineChar.setScaleEnabled(true);
        //如果禁用,扩展可以在x轴和y轴分别完成
        mLineChar.setPinchZoom(true);

    }


    //初始化控件
    @SuppressLint("ResourceAsColor")
    private void initView(View view) {
        temptex = view.findViewById(R.id.frg_current_temp);
        citytex = view.findViewById(R.id.frg_current_city);
        conditiontex = view.findViewById(R.id.frg_current_weather);
        windtex = view.findViewById(R.id.frg_current_wind);
        temprangtex = view.findViewById(R.id.frg_current_temp_range);
        datatex = view.findViewById(R.id.frg_current_time);
        timeTex = view.findViewById(R.id.frg_current_timing);
        clothindexTex = view.findViewById(R.id.index_wear);
        carIndexTex = view.findViewById(R.id.index_washCar);
        coldIndexTex = view.findViewById(R.id.index_cold);
        sportIndexTex = view.findViewById(R.id.index_sport);
        raysIndexTex = view.findViewById(R.id.index_rays);
        dayIma = view.findViewById(R.id.frg_weather_icon);
        futureLayout = view.findViewById(R.id.frg_middle_layout);
        recyclerView = view.findViewById(R.id.recycler_view);
       // lineChart = view.findViewById(R.id.frg_lineChart);

        //设置折线图
        Hour_Adapter adapter = new Hour_Adapter(getActivity(),Temdata);
        recyclerView.setAdapter(adapter);


        //下拉刷新
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              //  FIRST = 1;
               // requestWeather(city);
                swipeRefreshLayout.setRefreshing(false);
               new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });
            //设置点击事件
        clothindexTex.setOnClickListener(this);
        carIndexTex.setOnClickListener(this);
        coldIndexTex.setOnClickListener(this);
        sportIndexTex.setOnClickListener(this);
        raysIndexTex.setOnClickListener(this);
    }

    //下拉刷新获取城市天气信息
    private void requestWeather(String city) {
        FIRST = 1;
        String url = url1 + city + url2;
        loadData(url);



    }

        @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        switch (v.getId()){
            case R.id.index_wear:
                builder.setTitle("穿衣指数");

                String msg = indexList.getDressing_index()+"\n"+indexList.getDressing_advice();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);

                break;
            case R.id.index_washCar:
                builder.setTitle("洗车指数");
                msg = indexList.getWash_index();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);

                break;
            case R.id.index_cold:
                builder.setTitle("旅游指数");
                msg = indexList.getTravel_index();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);

                break;
            case R.id.index_sport:
                builder.setTitle("运动指数");
                msg = indexList.getExercise_index();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);

                break;
            case R.id.index_rays:
                builder.setTitle("紫外线指数");
                msg = indexList.getUv_index();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);

                break;

        }
        builder.create().show();
    }

    //事件监听重写
    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    //手势方法重写
    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }
}
