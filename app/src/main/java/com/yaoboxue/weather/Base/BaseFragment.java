package com.yaoboxue.weather.Base;

import androidx.fragment.app.Fragment;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * @Author yaoboxue
 * @Date 2019.12.10  15:17
 * @Version 1.0
 */

/**
 * xutils加载网络数据的步骤
 * 1.声明整体模块在UniltApp中
 * 2.执行网络请求操作
 */
public class BaseFragment extends Fragment implements Callback.CommonCallback<String>
{
    private static final String TAG = "MainActivity";
    public void loadData(String path){
        RequestParams params = new RequestParams(path);
        x.http().get(params,this);
    }

    //获取数据成功时调用的
    @Override
    public void onSuccess(String result) {

    }

    //或许数据失败时调用的
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
    }

    //取消操作时调用的
    @Override
    public void onCancelled(CancelledException cex) {

    }

    //请求完成时调用的
    @Override
    public void onFinished() {

    }
}
