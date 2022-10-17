package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqlLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;
    private final String TABLE_NAME = "product";
    private final String COL_ID = "product_id";
    private final String COL_NAME = "product_name";
    private final String COL_PRICE = "product_price";

    public MySqlLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "( " +
                COL_ID + " Integer PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT NOT NULL, " +
                COL_PRICE + " number DEFAULT 0)" + ";";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table " + TABLE_NAME + ";");
        this.onCreate(db);
    }

    //Add records
    public void addRecord(Product product){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, product.getName());
        values.put(COL_PRICE, product.getPrice());

        SQLiteDatabase db = null;
        db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //Delete Records
    public void deleteRecord(Product product){
        SQLiteDatabase db = null;
        db = this.getWritableDatabase();
        int status = db.delete(TABLE_NAME, COL_NAME + "=?", new String[] {product.getName()});
        //db.execSQL("delete from " + TABLE_NAME + " where " + COL_NAME + " = " + product.getName());
        db.close();
    }

    //toString method
    //Selects all the rows of a specific column and returns them
    public  String databaseToString(){
        String result = "";
        SQLiteDatabase db = null;
        db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, new String[] {COL_NAME}, null, null, null, null, null);

        c.moveToFirst();
        while(!c.isLast()){
            result += c.getString(c.getColumnName(COL_NAME), null);
        }

        return result;
    }
}
