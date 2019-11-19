package com.javaer.plusxposed.util.net;

import com.javaer.plusxposed.log.Vlog;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtil {

    /**
     * getString
     * @param urlString URL
     * @param method GET / POST
     * @return string
     */
    public static String getMessage(String urlString, String method){
        String result = "";
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setConnectTimeout(30 * 1000);
            connection.setReadTimeout(30 * 1000);

            InputStream is = connection.getInputStream();
            int length;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            while ((length = is.read(buffer)) != -1){
                baos.write(buffer, 0, length);
            }
            baos.flush();
            baos.close();
            is.close();
            String s = new String(baos.toByteArray());
            return s;
        } catch (Throwable throwable){
            Vlog.log(throwable);
        }
        finally {
            if (connection != null){
                connection.disconnect();
            }
        }
        return result;
    }

    /**
     * 获取信息
     * @param full_url url
     * @return result
     */
    public static String getMessage(String full_url){
        return getMessage(full_url, Method.GET);
    }

    /**
     * 提交数据
     * @param full_url 完整链接
     * @return result
     */
    public static String postMessage(String full_url){
        return getMessage(full_url, Method.POST);
    }


    /**
     * 下载文件
     * @param path 保存路径
     * @param urlString url
     * @return 结果
     */
    public static boolean download(String path, String urlString){
        boolean result = false;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(30 * 1000);
            connection.setReadTimeout(30 * 1000);

            InputStream is = connection.getInputStream();
            int length;
            FileOutputStream fos = new FileOutputStream(path);
            byte[] buffer = new byte[2048];
            while ((length = is.read(buffer)) != -1){
                fos.write(buffer, 0, length);
            }
            fos.flush();
            fos.close();
            is.close();
            result = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                connection.disconnect();
            }
        }
        return result;
    }
}
