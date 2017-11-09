package com.example.user.assignment102;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by user on 09-11-2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable=" CREATE  TABLE "+Constants.TABLE_NAME + " ("
                + Constants.ID +  " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Constants.Product_NAME +  " TEXT ) ";

        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public int getRowCountFromTable() {
        int rowcount = 0;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor= database.query(false, Constants.TABLE_NAME, null, null, null, null, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            rowcount=cursor.getCount();
        }
        return rowcount;
    }
    //contentValues is class which is used to insert data in database & it maps the data to the corresponding row & column
    public long insertProductRecords(ContentValues values)
    {
        long count=0;
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            count= database.insert(Constants.TABLE_NAME,null,values);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return count;
    }

    public ArrayList<ProductData> getAllProducts()
    {
        ArrayList<ProductData> list = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "select * from "+Constants.TABLE_NAME;
        Cursor cursor = database.rawQuery(query,null);
        ProductData productData = null;
        if (cursor!=null)
        {
            cursor.moveToFirst();
            do{
                productData = new ProductData();
                productData.setId(cursor.getInt(0));
                productData.setProduct_name(cursor.getString(1));
                list.add(productData);
            }while (cursor.moveToNext());
        }
        return list;
    }
}
