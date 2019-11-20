package com.javaer.plusxposed.util.contact;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ContactUtil {

    /**
     *
     * 获取通讯录中的所有联系人存储在Map中
     * 注：Map中号码唯一作为key，号码对应的联系人作为value
     * 对同一个号码存储多个联系人姓名的做了处理，这里只存一个姓名。即一个号码只对应一个姓名
     * @param context
     * @return
     */
    public static Map<String, String> getAllContacts(Context context){
        Map<String, String> contactsMap = new HashMap<>();
        try {
            //生成ContentResolver对象
            ContentResolver contentResolver = context.getContentResolver();
            // 获得所有的联系人
        /*Cursor cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
         */
            //这段代码和上面代码是等价的，使用两种方式获得联系人的Uri
            Cursor cursor = contentResolver.query(Uri.parse("content://com.android.contacts/contacts"),null,null,null,null);

            if (cursor == null){
                return contactsMap;
            }

            // 循环遍历
            if (cursor.moveToFirst()) {
                int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                int displayNameColumn = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                do {
                    // 获得联系人的ID
                    String contactId = cursor.getString(idColumn);
                    // 获得联系人姓名
                    String displayName = cursor.getString(displayNameColumn);
                    //显示获得的联系人信息
                    // 查看联系人有多少个号码，如果没有号码，返回0
                    int phoneCount = cursor.getInt(cursor
                            .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    if (phoneCount > 0) {
                        // 获得联系人的电话号码列表
                        Cursor phoneCursor = context.getContentResolver().query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                        + "=" + contactId, null, null);
                        if (phoneCursor == null){
                            continue;
                        }
                        if(phoneCursor.moveToFirst()){
                            do{
                                //遍历所有的联系人下面所有的电话号码
                                String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                //显示获得的号码
                                if(!contactsMap.containsKey(phoneNumber)){
                                    contactsMap.put(handlePhoneNum(phoneNumber), displayName);
                                }
                            }while(phoneCursor.moveToNext());
                        }
                    }
                } while (cursor.moveToNext());
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        return contactsMap;
    }

    /**
     *
     * 处理号码的方法
     * 规则：
     * 1、去除号码中所有的非数字
     * 2、如果号码为13位（即手机号）就去掉86
     * @param phoneNum
     * @return
     */
    private static String handlePhoneNum(String phoneNum){
        if(!TextUtils.isEmpty(phoneNum)){
            phoneNum = phoneNum.replaceAll("\\D", "");
        }
        if(phoneNum.length() == 13 && phoneNum.startsWith("86")){
            return phoneNum.substring(2);
        }
        return phoneNum;
    }


    public static void getCallRecords(Context context) {
        try {
            int type;// 类型
            long lCallTime;// 打电话时间
            long lDuration;//通话时长
            String sNumber = null;// 电话号码
            String sName = null;// 姓名
            String sCallTime = null;// 打电话时间
            String sLog;
            Date date;
            // 查询通话记录
            ContentResolver cr = context.getContentResolver();
            @SuppressLint("MissingPermission") final Cursor cursor = cr.query(CallLog.Calls.CONTENT_URI, new String[] {
                            CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME,
                            CallLog.Calls.TYPE, CallLog.Calls.DATE, CallLog.Calls.DURATION}, null, null,
                    CallLog.Calls.DEFAULT_SORT_ORDER);
            for (int i = 0; i < cursor.getCount(); i++)
            {
                cursor.moveToPosition(i);
                sNumber = cursor.getString(0);// 电话号码
                sName = cursor.getString(1);// 名字
                type = cursor.getInt(2);// 类型
                lCallTime = Long.parseLong(cursor.getString(3));// 打电话的时间
                lDuration = cursor.getLong(4);
                SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                date = new Date(lCallTime);// 打电话的日期
                sCallTime = sfd.format(date);

                sLog = "Number="+sNumber+",Name="+sName+",CallTime="+sCallTime+",Duration="+lDuration;
                //来电:1,拨出:2,未接:3
                if (type == 3)
                {
                    sLog = sLog + "--末接";
                } else if (type == 2)
                {
                    sLog = sLog + "--拨出";
                } else
                {
                    sLog = sLog + "--来电";
                }
            }
            cursor.close();
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }


    /**
     * 获取所有联系人
     * @param context
     * @return
     */
    private String[] getContacts(Context context) {
        //联系人的Uri，也就是content://com.android.contacts/contacts
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //指定获取_id和display_name两列数据，display_name即为姓名
        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };
        //根据Uri查询相应的ContentProvider，cursor为获取到的数据集
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        String[] arr = new String[cursor.getCount()];
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                Long id = cursor.getLong(0);
                //获取姓名
                String name = cursor.getString(1);
                //指定获取NUMBER这一列数据
                String[] phoneProjection = new String[] {
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                };
                arr[i] = id + " , 姓名：" + name;

                //根据联系人的ID获取此人的电话号码
                Cursor phonesCusor = context.getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        phoneProjection,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,
                        null,
                        null);

                //因为每个联系人可能有多个电话号码，所以需要遍历
                if (phonesCusor != null && phonesCusor.moveToFirst()) {
                    do {
                        String num = phonesCusor.getString(0);
                        arr[i] += " , 电话号码：" + num;
                    }while (phonesCusor.moveToNext());
                }
                i++;
            } while (cursor.moveToNext());
        }
        return arr;
    }
}
