package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.util.Log;
import android.view.textclassifier.ConversationActions;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Mydatabase extends SQLiteOpenHelper {
    private static final String dataaseName = "myData";
    private static final String tableName = "myInfo";
    private static final String name = "Name";
    private static final String age = "Age";
    private static final String phone = "Phone";
    private static final int version = 1;
    private static final String id = "Id";
    private static final String createTable = "CREATE TABLE "+tableName+" ("+id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+name+" VARCHAR(250) ,"+age+" INTEGER ,"+phone+" varchar(50)); ";
    private static final String drop_table = "DROP TABLE IF EXISTS "+tableName;
    private Context context;
    public Mydatabase(Context context) {
        super(context, dataaseName, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
           try{
               db.execSQL(createTable);
           }catch (Exception e){
               Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
           }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           try{
               db.execSQL(drop_table);
               onCreate(db);
           }catch (Exception e)
           {
               Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
           }
    }

    public long InsertData(String Name,String Age,String Phone){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(name,Name);
        contentValues.put(age,Age);
        contentValues.put(phone,Phone);
        long rowId = sqLiteDatabase.insert(tableName,null,contentValues);
        return rowId;
    }

    public ArrayList<ModelClass> getData()
    {
        ArrayList<ModelClass> info = new ArrayList<ModelClass>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+tableName,null);
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String nam = cursor.getString(1);
            String ag = cursor.getString(2);
            String phn = cursor.getString(3);
            ModelClass modelClass = new ModelClass(id,nam,ag,phn);
            info.add(modelClass);
        }
        return info;
    }
    public Cursor Search(int ID){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+tableName+" WHERE "+id+"= "+ID, null);
        return cursor;
    }
    public void Update(int ID,String Name, String Age, String Phone){
        SQLiteDatabase database  = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(name,Name);
        contentValues.put(age,Age);
        contentValues.put(phone,Phone);
        database.update(tableName,contentValues,"id="+ID,null );
    }
    public void Delete(int ID){
        SQLiteDatabase database = this.getReadableDatabase();
        database.delete(tableName,"id="+ID,null);
    }

}
