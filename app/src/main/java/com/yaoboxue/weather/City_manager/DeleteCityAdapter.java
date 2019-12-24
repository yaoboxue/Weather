package com.yaoboxue.weather.City_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yaoboxue.weather.R;

import java.util.List;

public class DeleteCityAdapter extends BaseAdapter {
    //上下文对象
    Context context;
    //数据源
    List<String>mDatas;

    List<String>deletecity;

    public DeleteCityAdapter(Context context, List<String> mDatas, List<String> deletecity) {
        this.context = context;
        this.mDatas = mDatas;
        this.deletecity = deletecity;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_deletecity,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String city = mDatas.get(position);
        holder.textView.setText(city);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatas.remove(city);
                deletecity.add(city);

                notifyDataSetChanged();//删除以后提示适配器更新；
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView textView;
        ImageView imageView;
        public ViewHolder(View itemView){
            textView = itemView.findViewById(R.id.item_delete_tv);
            imageView = itemView.findViewById(R.id.item_delete_iv);
        }

    }
}
