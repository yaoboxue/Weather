package com.yaoboxue.weather.City_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yaoboxue.weather.Bean.WeatherBean;
import com.yaoboxue.weather.DB.DatabaseBean;
import com.yaoboxue.weather.R;

import java.util.List;

public class CityManagerAdapter extends BaseAdapter {
    Context context;
    List<DatabaseBean>mdatas;

    public CityManagerAdapter(Context context, List<DatabaseBean> mdatas) {
        this.context = context;
        this.mdatas = mdatas;
    }

    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_city,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        DatabaseBean bean = mdatas.get(position);
        holder.cityTex.setText(bean.getCity());
        //拿到json数据并解析
        WeatherBean weatherBean = new Gson().fromJson(bean.getContent(), WeatherBean.class);
        WeatherBean.ResultBean.TodayBean todayBean = weatherBean.getResult().getToday();
        holder.ConditionTex.setText("天气"+todayBean.getWeather());
        WeatherBean.ResultBean.SkBean skBean = weatherBean.getResult().getSk();
        holder.currentemTex.setText("温度"+skBean.getTemp());
        holder.windTex.setText(todayBean.getWind());
        holder.temRangTex.setText(todayBean.getTemperature());
        return convertView;
    }
    class ViewHolder{
        TextView cityTex,ConditionTex,currentemTex,windTex,temRangTex;
        //初始化以上控件
        public ViewHolder(View itemView){
            cityTex = itemView.findViewById(R.id.item_city_tv_city);
            ConditionTex = itemView.findViewById(R.id.item_city_tv_condition);
            currentemTex = itemView.findViewById(R.id.item_city_tv_temp);
            windTex = itemView.findViewById(R.id.item_city_wind);
            temRangTex = itemView.findViewById(R.id.item_city_temprange);

        }
    }
}
