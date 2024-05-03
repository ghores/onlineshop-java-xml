package com.example.onlineshop.handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public abstract class BaseDBHandler<T> extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "OnlineShopDB";

    public BaseDBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(getDropTableQuery());
        onCreate(db);
    }

    public void addData(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(getTableName(), null, values);
        db.close();
    }

    protected abstract String getTableName();

    protected abstract String getCreateTableQuery();

    protected abstract String getDropTableQuery();
}