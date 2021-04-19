package com.example.expensestracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {
    //Σταθερές για τη ΒΔ (όνομα ΒΔ, έκδοση, πίνακες κλπ)
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_CATEGORY ="category";
    private static final String TABLE_CATEGORIES = "categories";
    private static final String COLUMN_PRICE = "price" ;

    //Constructor
    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Δημιουργία του σχήματος της ΒΔ (πίνακας products)
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_PRICE + " INTEGER," +
                COLUMN_CATEGORY + " TEXT" +")";

        String CREATE_CAT_TABLE= "CREATE TABLE " +
                TABLE_CATEGORIES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_CATEGORY + " TEXT," +
                COLUMN_PRICE + " INTEGER" +")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
        //  db.execSQL(CREATE_CAT_TABLE);
    }

    //Αναβάθμιση ΒΔ: εδώ τη διαγραφώ και τη ξαναδημιουργώ ίδια
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        onCreate(db);
    }

    //Μέθοδος για προσθήκη ενός προιόντος στη ΒΔ
    public void addProduct(Product product) {
        Log.d("add","product");
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY, product.getCategory());
        values.put(COLUMN_PRICE, product.getPrice());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    //Μέθοδος για εύρεση προιόντος βάσει ονομασίας του
    public Product findProduct(String productname) {
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_CATEGORY + " = '" + productname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Product product = new Product();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            product.setId(Integer.parseInt(cursor.getString(0)));
            product.setPrice(Integer.parseInt(cursor.getString(1)));
            product.setCategory(cursor.getString(2));
            cursor.close();
        } else {
            product = null;
        }
        db.close();
        return product;
    }

    //Μέθοδος για διαγραφή προιόντος βάσει ονομασίας του
    public boolean deleteProduct(String productname) {
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCTNAME + " = '" + productname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Product product = new Product();
        if (cursor.moveToFirst()) {
            product.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(product.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public Cursor getData(){
        Log.d("get","cat");
        SQLiteDatabase db= this.getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_PRODUCTS ;

        Cursor cursor = db.rawQuery(query,null);
        return cursor;
     /*  int result;
       if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            result=Integer.parseInt(cursor.getString(1));
            cursor.close();
       }
       else {
           Log.d("else"," ");
           result = 0;
       }
       Log.d("vgainw","getcat");
       db.close();
       return result; */
    }

}
