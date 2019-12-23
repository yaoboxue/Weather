package com.yaoboxue.weather.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * @Author yaoboxue
 * @Date 2019.12.23  14:48
 * @Version 1.0
 */
public class PureNetUtil {
    /**
     * get方法直接调用post方法
     * @param url 网络地址
     * @return 返回网络数据
     */
    public static String get(String url){
        return post(url,null);
    }

    /**
     * 设定post方法获取网络资源，如果参数为null，实际上设定为post方法
     * @param url 网络地址
     * @param param 请求参数键值对
     * @return 返回读取数据
     */

    private static String post(String url, Map<String,String>param) {
        HttpURLConnection conn = null;
        try {
            URL u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            StringBuffer sb = null;
            if (param != null){
                sb = new StringBuffer();
               // 默认为false,post方法需要写入参数,设定true
                conn.setDoOutput(true);
                //设定post方法，默认get
                conn.setRequestMethod("POST");
                //获得输出流
                OutputStream out = conn.getOutputStream();
                //对输出流封装成高级输出流
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                //将参数封装成键值对的形式
                for (Map.Entry<String,String> s : param.entrySet()){
                    sb.append(s.getKey()).append("=").append(s.getValue()).append("&");
                }
                //将参数通过输出流写入
                writer.write(sb.deleteCharAt(sb.toString().length() - 1).toString());
                writer.close();
                sb = null;
            }
            conn.connect();
            sb = new StringBuffer();
            int recode = conn.getResponseCode();
            BufferedReader reader = null;
            if (recode == 200){
                InputStream in = conn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                String str = null;
                sb = new StringBuffer();
                while ((str = reader.readLine()) != null){
                    sb.append(str).append(System.getProperty("line.separator"));
                }
                reader.close();
                if (sb.toString().length() == 0){
                    return null;
                }
                return sb.toString().substring(0,
                        sb.toString().length() - System.getProperty("line.separator").length());

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if (conn != null){
                conn.disconnect();
            }
        }
        return null;
    }
}
