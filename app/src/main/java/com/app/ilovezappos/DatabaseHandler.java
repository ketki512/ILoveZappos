package com.app.ilovezappos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ketkitrimukhe on 2/6/17.
 */

// SQL lite database handler
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "cartDB";

    private static final String TABLE_CART = "products";

    private static final String KEY_NAME = "name";
    private static final String KEY_BRAND = "brand";
    private static final String KEY_PRICE = "price";
    private static final String KEY_IMAGE = "imageUrl";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_CART + "("
                + KEY_NAME + " TEXT," + KEY_BRAND + " TEXT,"
                + KEY_PRICE + " TEXT," + KEY_IMAGE + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);

        // Create tables again
        onCreate(db);
    }

    // Inserting entries
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getProductName());
        values.put(KEY_BRAND, product.getBrandName());
        values.put(KEY_PRICE, product.getPrice());
        values.put(KEY_IMAGE, product.getThumbnailImageUrl());

        // Inserting Row
        db.insert(TABLE_CART, null, values);
        //db.close(); // Closing database connection
    }

    // deleting entries
    public boolean deleteRow(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CART, KEY_NAME + "=" + " '" + name + "'", null) > 0;
    }

    // getting a list of all the entries in the table
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<Product>();
        String selectQuery = "SELECT  * FROM " + TABLE_CART;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setProductName(cursor.getString(0));
                product.setBrandName(cursor.getString(1));
                product.setPrice(cursor.getString(2));
                product.setThumbnailImageUrl(cursor.getString(3));
                productList.add(product);
            } while (cursor.moveToNext());
        }
        return productList;
    }

    // get count of the number of entries in the table
    public long getCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long cnt  = DatabaseUtils.queryNumEntries(db, TABLE_CART);
        db.close();
        return cnt;
    }
}