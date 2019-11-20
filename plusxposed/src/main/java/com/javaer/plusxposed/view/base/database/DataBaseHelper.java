package com.javaer.plusxposed.view.base.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2018/5/24.
 */

public class DataBaseHelper {

    private SQLiteDatabase db = null;

    public DataBaseHelper(String dataBasePath) {
        db = SQLiteDatabase.openOrCreateDatabase(dataBasePath, null);
    }

    public SQLiteDatabase getDataBase(){
        return db;
    }

    public void createTable(String execSQL){
        if (db == null){
            db = getDataBase();
        }
        db.execSQL(execSQL);
    }

    public void insert(String table, ContentValues values){
        db.insert(table, null, values);
    }

    public long insertWithOnConflict(String table, ContentValues values){
        return db.insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public long save(String table, ContentValues values){
        return db.insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public Cursor rawQuery(String sql){
        return rawQuery(sql, null);
    }

    public Cursor rawQuery(String sql, String[] selectionArgs){
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        return cursor;
    }

    public void update(String table, ContentValues values, String whereClause, String[] whereArgs){
        db.update(table, values, whereClause, whereArgs);
    }

    public void delete(String table, String whereClause, String[] whereArgs){
        db.delete(table, whereClause, whereArgs);
    }

    /**
     * 删除表里内容但不删除表
     * @param table 表名
     */
    public void deleteTable(String table){
        getDataBase().execSQL("delete from "+table);
    }
}
