package com.fruit.xgh.utils;

import android.content.SharedPreferences;
import android.util.Log;

import com.fruit.xgh.main.MyApplication;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author yz
 * @version 1.0
 * @Email <1983898915@qq.com>
 * @date 2019/6/7 23:30
 */

public class HttpUtils {
    static private final SharedPreferences sharedPreferences = MyApplication.getConext().getSharedPreferences("user", MODE_PRIVATE);
    static public final String HTTP = "http://114.115.255.87/xghServer/api/";
//    static public final String HTTP = "http://192.168.1.179:8080/SchoolBackground_war_exploded/";
    static private final String API = "api/";
    private static final String TAG = "HttpUtil";
    private static Gson gson = new Gson();


    /**
     * 请求数据
     * @param url
     * @param jsonObject
     * @param handler
     */
    public static void post(String url, JSONObject jsonObject, MyResponseHandler handler) {
            RequestParams params = new RequestParams(getUrl(url));
            params.setAsJsonContent(true);
            params.setBodyContent(jsonObject.toString());
            Log.d(TAG, "post: " + jsonObject.toString());
            x.http().post(params, handler);
   }

    public static void postfrom(String url, List<KeyValue> list, MyResponseHandler handler) {
        RequestParams params = new RequestParams(getUrl(url));
        params.setAsJsonContent(true);
        params.setMultipart(true);
        MultipartBody body = new MultipartBody(list, "UTF-8");
        Log.d(TAG, "postfrom: "+body.toString());
        params.setRequestBody(body);
        x.http().post(params, handler);
    }


    /**
     * 上传文件
     * @param url
     * @param path
     * @param handler
     */
    public static void postFile(String url, String[] path, MyResponseHandler handler) {
        RequestParams params = new RequestParams(getUrl(url));
        params.setAsJsonContent(true);
        params.setMultipart(true);
        List<KeyValue> list = new ArrayList<>();
        for (String s : path) {
            list.add(new KeyValue("image", new File(s)));
//                params.addBodyParameter("image", new File(s));
            Log.d(TAG, "postFile: " + s);
        }
        list.add(new KeyValue("id", HttpUtils.getAccount()));
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);
        x.http().post(params, handler);
    }

    /**
     * 处理网络请求返回数据
     */
    public abstract static class MyResponseHandler implements Callback.CommonCallback<String> {

        @Override
        public void onSuccess(String s) {
            mySuccess(s);
        }

        protected abstract void mySuccess(String s);

        @Override
        public void onError(Throwable throwable, boolean b) {
            myError(throwable, b);
        }

        protected abstract void myError(Throwable throwable, boolean b);

        @Override
        public void onCancelled(CancelledException e){}

        @Override
        public void onFinished() {

        }
    }

    /**
     * 获取网络请求地址
     * @param api
     * @return
     */
    private static String getUrl(String api) {
        String url = HTTP+ api;
        Log.d(TAG, "getUrl: " + url);
        return url;
    }

//    /**
//     * 保存登录数据
//     * @param stu
//     */
//    public static void saveUser(StudentEntity.REQUESTBean stu) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("account", stu.getStuid());
//        editor.putString("password", stu.getStupassword());
//        editor.commit();
//    }

//    /**
//     * 获取登录用户
//     * @return
//     */
//    public static StudentEntity.REQUESTBean getUser() {
//        return new StudentEntity.REQUESTBean(sharedPreferences.getString("account", null), sharedPreferences.getString("password", null));
//    }

    /**
     * 获取当前登录用户账号(Json格式)
     * @return
     * @throws JSONException
     */
    public static JSONObject getUserAccount() throws JSONException {
        return new JSONObject().put("account", sharedPreferences.getString("account", null));
    }

    /**
     * 获取当前登录用户账号(String 格式)
     * @return
     */
    public static String getAccount() {
        return sharedPreferences.getString("account", null);
    }

    /**
     * 实体转JSON
     * @param o
     * @return
     * @throws JSONException
     */
    public static JSONObject toJSON(Object o) throws JSONException {
        return new JSONObject(gson.toJson(o));
    }

    /**
     * JSON转实体
     * @param s
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T toObject(String s, Class<T> tClass) {
        return gson.fromJson(s, tClass);
    }
}
