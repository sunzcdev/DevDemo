package com.cnjaj.myapplication.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.cnjaj.myapplication.dao.inject.InjectUtils;
import com.cnjaj.myapplication.utils.DateUtils;
import com.cnjaj.myapplication.utils.MyLog;

import java.util.ArrayList;
import java.util.List;

public class DBDao {

    private static final String TAG = "DBDao";
    private DBOpenHelper helper;

    public DBDao(Context context) {
        helper = new DBOpenHelper(context);
    }

    public void insert(OfflineMessage message) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.insert(InjectUtils.getTableName(message.getClass()), null, InjectUtils.toContentValues(message));// 执行插入操作
            db.setTransactionSuccessful();
        } catch (Exception e) {
            MyLog.v("vehicle", "插入一条学时记录数据异常：" + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    public void clearTable(OfflineMessage message) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();
        try {
            String tableName = InjectUtils.getTableName(message.getClass());
            db.delete(tableName, null, null);
            db.setTransactionSuccessful();
            MyLog.i(TAG, "清除" + tableName + "表中所有数据");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public <M extends OfflineMessage> void delByBeans(Class<M> mClazz, M... beans) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();
        try {
            String tableName = InjectUtils.getTableName(mClazz);
            String[] ids = new String[beans.length];
            for (int i = 0; i < beans.length; i++) {
                ids[i] = String.valueOf(beans[i].getId());
            }
            db.delete(tableName, "CAST( id AS TEXT) IN (" + new String(new char[ids.length - 1]).replace("\0", "?,") + "?)", ids);
            db.setTransactionSuccessful();
            MyLog.i(TAG, "清除" + tableName + "表中所有数据");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public long update(OfflineMessage message) {
        long row = 0;
        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();
        try {
            row = db.update(InjectUtils.getTableName(message.getClass()), InjectUtils.toContentValues(message), null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            MyLog.i(TAG, "根据学时记录编号更新当前学员学时上传状态" + e.getMessage());
        } finally {
            db.endTransaction();
        }
        return row;
    }

    public <M extends OfflineMessage> List<M> query(String where, Class<M> mClass) {
        StringBuilder querySql = new StringBuilder("select * from ")
                .append(InjectUtils.getTableName(mClass))
                .append(" ");
        if (!TextUtils.isEmpty(where)) {
            querySql.append(where);
        }
        SQLiteDatabase dbs = helper.getWritableDatabase();
        List<M> beans = new ArrayList<>();
        dbs.beginTransaction();
        try {
            MyLog.i(TAG, "查询语句：" + querySql.toString());
            Cursor cursor = dbs.rawQuery(querySql.toString(), null);
            if (cursor.moveToFirst()) {
                do {
                    M m = InjectUtils.generateDataBean(cursor, mClass);
                    beans.add(m);
                } while (cursor.moveToNext());

            }
            cursor.close();
            dbs.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.e(TAG, e.getMessage());
        } finally {
            dbs.endTransaction();
        }
        MyLog.i(TAG, "查询结果条数：" + beans.size());
        return beans;
    }
    // --------------------------------------------------学时数据上传

    public <M extends OfflineMessage> List<M> queryAll(Class<M> mClass) {
        return query(null, mClass);
    }

    /**
     * 根据时间段查询学时
     *
     * @param begintime
     * @param overtime
     */
    public <M extends OfflineMessage> List<M> getClassHourRecordatTime(Class<M> mClass, String begintime, String overtime) {
        String begintime1, overtime1;
        if (begintime.length() == 12) {
            begintime1 = DateUtils.changeTime("20" + begintime);
        } else {
            begintime1 = DateUtils.changeTime(begintime);
        }
        if (overtime.length() == 12) {
            overtime1 = DateUtils.changeTime("20" + overtime);
        } else {
            overtime1 = DateUtils.changeTime(overtime);
        }
        String sql = " where rktime>='" + begintime1 + "' and rktime<'" + overtime1 + "'";
        return query(sql, mClass);
    }

    /**
     * 根据条数查询学时 根据记录产生时间
     *
     * @param mClass
     * @param number
     */
    public <M extends OfflineMessage> List<M> queryByOrderInNumber(Class<M> mClass, int number) {
        String sql = "order by rktime  desc LIMIT '" + number + "'";
        return query(sql, mClass);
    }

    /**
     * 根据条数查询学时数据
     */
    public <M extends OfflineMessage> List<M> queryByIsUploadInNumber(Class<M> mClass, int number) {
        String sql = "where isupload = 1";
        if (number > 0) {
            sql = sql + " limit " + number;
        }
        return query(sql, mClass);
    }

    /**
     * 查询未上传的学时数据
     */
    public <M extends OfflineMessage> List<M> queryAllByIsUpload(Class<M> mClass) {
        return queryByIsUploadInNumber(mClass, 0);
    }

}
