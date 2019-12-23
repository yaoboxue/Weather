package com.yaoboxue.weather;

import android.app.Application;

import com.yaoboxue.weather.DB.DBmanager;

import org.xutils.x;

/**
 * @Author yaoboxue
 * @Date 2019.10.12  15:19
 * @Version 1.0
 */
public class UniteApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        DBmanager.initDB(this);
    }
}
