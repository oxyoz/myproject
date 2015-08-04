package me.wangolf.utils;

import java.io.File;

import me.wangolf.ConstantValues;
import me.wangolf.GlobalConsts;
import me.wangolf.service.IOAuthCallBack;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class Xutils {
    private String result = null;
    private static int statusCode;

    /**
     * 上传文件 或者提交数据 到服务器（post方法）
     *
     * @param params         参数
     * @param iOAuthCallBack 回调服务器回来的结果
     */
    public static void getDataFromServer(RequestParams params, final IOAuthCallBack iOAuthCallBack) {
        params.addBodyParameter("terminal", "1"); // 共用的parames 写在这里吧
        HttpUtils http = new HttpUtils();
       // Log.i("wangolf",GlobalConsts.BASE_URL);
        http.send(HttpRequest.HttpMethod.POST, GlobalConsts.BASE_URL, params, new RequestCallBack<String>() {

            @Override
            public void onStart() {

            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                iOAuthCallBack.getIOAuthCallBack(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {

                iOAuthCallBack.getIOAuthCallBack(ConstantValues.FAILURE);
                // iOAuthCallBack.getIOAuthCallBack(msg);
            }
        });

    }

    public static void getDataFromServer(String url, final IOAuthCallBack iOAuthCallBack) {
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {

            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                iOAuthCallBack.getIOAuthCallBack(responseInfo.result);

            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                iOAuthCallBack.getIOAuthCallBack(ConstantValues.FAILURE);
            }
        });

    }

    /**
     * 加载网络图片
     *
     * @param context 上下文
     * @param image   imageview
     * @param url     图片网络地址
     */
    public static void getBitmap(Context context, ImageView image, String url) {

        //BitmapUtils bitmapUtils = new BitmapUtils(context);
        // 加载网络图片
        BitmapUtils bitmapUtils = new BitmapUtils(context,null,1024*10);
        bitmapUtils.configDiskCacheEnabled(true);
        bitmapUtils.display(image, url);
    }

    /**
     * 加载图片 180*180
     *
     * @param context
     * @param image
     * @param url
     */
    public static void getBitmapBysize(Context context, ImageView image, String url) {

        BitmapUtils bitmapUtils = new BitmapUtils(context,"/wangolf",1024*30);
        // 加载网络图片
        bitmapUtils.configDefaultBitmapMaxSize(180,180);

        bitmapUtils.display(image, url);
    }

    public static void loadImage(String url) {

        // 下载APK，并且替换安装
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // sdcard存在
            // afnal
            HttpUtils http = new HttpUtils();
            int p = url.lastIndexOf("/");
            String imagename = url.substring(p);
            File file = new File("/sdcard/" + imagename);
            if (file.isFile() && file.exists()) {
                file.delete();
            }

            HttpHandler hand = http.download(url, "/sdcard/wangolf/" + imagename, true, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
                    true, // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
                    new RequestCallBack<File>() {

                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onLoading(long total, long current, boolean isUploading) {
                        }

                        @Override
                        public void onSuccess(ResponseInfo<File> responseInfo) {
                        }

                        @Override
                        public void onFailure(HttpException error, String msg) {

                        }

                    });
        } else {
            return;
        }
    }
}