package com.example.onlineshop.handlers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.onlineshop.models.CardItem;

public class CardDBHandler extends BaseDBHandler<CardItem> {


    public CardDBHandler(@Nullable Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "CARDS";
    }

    @Override
    protected String getCreateTableQuery() {
        return "CREATE TABLE IF NOT EXISTS " + getTableName() +
                "(" + CardItem.KEY_ID + " INTEGER PRIMARY KEY," +
                CardItem.KEY_PRODUCT + " INTEGER," + CardItem.KEY_SIZE + " INTEGER," +
                CardItem.KEY_COLOR + " INTEGER," + CardItem.KEY_QUANTITY + " INTEGER)";
    }

    @Override
    protected String getDropTableQuery() {
        return "DROP TABLE IF EXISTS " + getTableName();
    }
}
