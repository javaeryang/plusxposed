package com.javaer.plusxposed.util.qq;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class QQUtil {

    /****************
     *
     * @param key 由官网生成的key
     * @param activity context
     * @return 返回true表示呼起手Q成功，返回false表示呼起失败
     ******************/
    public static boolean joinGroup(String key, Context activity) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            activity.startActivity(intent);
            return true;
        } catch (Throwable e) {
            // 未安装手Q或安装的版本不支持
            Toast.makeText(activity, "未安装手Q或安装的版本不支持", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
