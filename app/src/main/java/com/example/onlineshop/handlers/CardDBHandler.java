package com.example.onlineshop.handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.onlineshop.mock.MockDataHandler;
import com.example.onlineshop.models.CardItem;

import java.util.ArrayList;
import java.util.List;

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

    public CardItem getDataByProductId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(getTableName(), new String[]{
                CardItem.KEY_ID, CardItem.KEY_PRODUCT, CardItem.KEY_SIZE, CardItem.KEY_COLOR, CardItem.KEY_QUANTITY
        }, CardItem.KEY_PRODUCT + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToNext();
        }

        CardItem item = new CardItem();
        item.setId(Integer.parseInt(cursor.getString(0)));
        item.setProduct(MockDataHandler.getById(Long.parseLong(cursor.getString(1))));
        item.setSize(MockDataHandler.getSizeById(Long.parseLong(cursor.getString(2))));
        item.setColor(MockDataHandler.getColorById(Long.parseLong(cursor.getString(3))));
        item.setQuantity(Integer.parseInt(String.valueOf(4)));
        cursor.close();
        return item;
    }

    public CardItem getDataByDetail(long productId, long sizeId, long colorId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(getTableName(), new String[]{
                        CardItem.KEY_ID, CardItem.KEY_PRODUCT, CardItem.KEY_SIZE, CardItem.KEY_COLOR, CardItem.KEY_QUANTITY
                }, CardItem.KEY_PRODUCT + "=? AND " + CardItem.KEY_SIZE + "=? AND " + CardItem.KEY_COLOR + "=?",
                new String[]{String.valueOf(productId), String.valueOf(sizeId), String.valueOf(colorId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToNext();
        }
        if (cursor.getCount() == 0)
            return null;

        CardItem item = new CardItem();
        item.setId(Integer.parseInt(cursor.getString(0)));
        item.setProduct(MockDataHandler.getById(Long.parseLong(cursor.getString(1))));
        item.setSize(MockDataHandler.getSizeById(Long.parseLong(cursor.getString(2))));
        item.setColor(MockDataHandler.getColorById(Long.parseLong(cursor.getString(3))));
        item.setQuantity(Integer.parseInt(cursor.getString(4)));
        cursor.close();
        return item;
    }

    private int updateData(CardItem data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CardItem.KEY_QUANTITY, data.getQuantity());
        return db.update(getTableName(), values, CardItem.KEY_ID + "=?", new String[]{String.valueOf(data.getId())});
    }

    public CardItem addToBasket(CardItem data) {
        CardItem oldData = getDataByDetail(data.getProduct().getId(), data.getSize().getId(), data.getColor().getId());

        if (oldData == null) {
            ContentValues values = new ContentValues();
            values.put(CardItem.KEY_PRODUCT, data.getProduct().getId());
            values.put(CardItem.KEY_SIZE, data.getSize().getId());
            values.put(CardItem.KEY_COLOR, data.getColor().getId());
            values.put(CardItem.KEY_QUANTITY, data.getQuantity());
            addData(values);
            return data;
        }
        oldData.setQuantity(oldData.getQuantity() + 1);
        updateData(oldData);
        return oldData;
    }

    public int getAllBasketDataCount() {
        String countQuery = "SELECT * FROM " + getTableName();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public List<CardItem> getAllData() {
        String allQuery = "SELECT * FROM " + getTableName();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(allQuery, null);
        if (cursor != null) {
            cursor.moveToNext();
        }

        if (cursor.getCount() == 0)
            return new ArrayList<>();

        List<CardItem> result = new ArrayList<>();

        do {
            CardItem item = new CardItem();
            item.setId(Integer.parseInt(cursor.getString(0)));
            item.setProduct(MockDataHandler.getById(Long.parseLong(cursor.getString(1))));
            item.setSize(MockDataHandler.getSizeById(Long.parseLong(cursor.getString(2))));
            item.setColor(MockDataHandler.getColorById(Long.parseLong(cursor.getString(3))));
            item.setQuantity(Integer.parseInt(cursor.getString(4)));
            result.add(item);
        } while (cursor.moveToNext());
        cursor.close();
        return result;
    }
}
