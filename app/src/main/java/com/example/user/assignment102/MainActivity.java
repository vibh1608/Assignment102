package com.example.user.assignment102;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper mDataBaseHelper;
    public ArrayList<ProductData> mAllProductData;
    public ArrayList<String> mProduct;


    String[] product_name = new String[]{"HP Injet Printer"," HP Mouse "," Mobile Phone"," Watches"," Clothes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAllProductData = new ArrayList<>();
        mProduct = new ArrayList<>();
        mDataBaseHelper = new DataBaseHelper(MainActivity.this,Constants.TABLE_NAME,null,Constants.DATABASE_VERSION);

        if(mDataBaseHelper.getRowCountFromTable()==0)
        {
            //TODO:INSERT DATA TO THE TABLE
            insertRecordInTable();
        }

        mAllProductData = mDataBaseHelper.getAllProducts();
        for (int j=0;j<mAllProductData.size();j++)
        {
            mProduct.add(mAllProductData.get(j).getProduct_name());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line,mProduct);
        AutoCompleteTextView textView =(AutoCompleteTextView) findViewById(R.id.text);
        textView.setAdapter(adapter);
    }

    private void insertRecordInTable() {
        for(int i=0;i<product_name.length;i++)
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.Product_NAME,product_name[i]);
            mDataBaseHelper.insertProductRecords(contentValues);
        }
    }
}
