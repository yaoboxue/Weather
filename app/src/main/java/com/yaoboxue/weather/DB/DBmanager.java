package com.yaoboxue.weather.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBmanager {
    public static SQLiteDatabase database;
    /*
    初始化数据库
     */
    public static void initDB(Context context){
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.getReadableDatabase();
    }
    /*
    查找数据库中全部城市名称
     */
    public static List<String>querryAllCity(){
        Cursor cursor = database.query("info",null,null,null,null,null,null);
        List<String>cityList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String city = cursor.getString(cursor.getColumnIndex("city"));
            cityList.add(city);
        }
        return cityList;
    }
    /*
    根据城市名称，替换信息内容
     */
    public static int updataInfoByCity(String city,String content){
        ContentValues values = new ContentValues();
        values.put("content",content);
        return database.update("info",values,"city=?",new String[]{city});
    }
    /*
    新增一条城市记录
     */
    public static long addCityInfo(String city,String content){
        ContentValues values = new ContentValues();
        values.put("city",city);
        values.put("content",content);
        return database.insert("info",null,values);
    }
    /*
    根据城市名，查询数据库当中的内容
     */
    public static String querryInfoByCity(String city){
        Cursor cursor = database.query("info", null, "city=?", null, null, null, null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            String content = cursor.getString(cursor.getColumnIndex("content"));
            return content;
        }
        return null;
    }
    /*
    存储城市天气，最多存储10个城市，超过10个城市不能存储
     */
    public static int getCityCount(){
        Cursor cursor = database.query("info", null, null, null, null, null, null);
        int count = cursor.getCount();
        return count;

    }
    //查询数据库的全部信息
    public static List<DatabaseBean>querryAllInfo(){
        Cursor cursor = database.query("info", null, null, null, null, null, null);
        List<DatabaseBean>list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String city = cursor.getString(cursor.getColumnIndex("city"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            DatabaseBean databaseBean = new DatabaseBean(id, city, content);
            list.add(databaseBean);
        }
        return list;
    }
}
