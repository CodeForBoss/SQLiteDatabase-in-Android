package com.example.sqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.health.PackageHealthStats;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText name,age,phone,searchEt;
    Button save,show,search;
    Mydatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nameEt);
        age = findViewById(R.id.ageEt);
        phone = findViewById(R.id.phoneEt);
        save = findViewById(R.id.savebtn);
        show = findViewById(R.id.showbtn);
        search = findViewById(R.id.searchbtn);
        searchEt = findViewById(R.id.searchEt);
        search.setOnClickListener(this);
        show.setOnClickListener(this);
        save.setOnClickListener(this);
        mydatabase = new Mydatabase(this);
        SQLiteDatabase sqLiteDatabase = mydatabase.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {

           if(v.getId()==R.id.savebtn){
               String Name = name.getText().toString();
               if(Name.isEmpty()){
                   name.setError("Must required!");
                   return;
               }
               String Age = age.getText().toString();
               if(Age.isEmpty()){
                   age.setError("Must required");
                   return;
               }
               String Phone = phone.getText().toString();
               if(Phone.isEmpty()){
                   phone.setError("Must required");
                   return;
               }
               if(Phone.length()<11){
                   phone.setError("Phone number Invalid!");
                   return;
               }
                   long rowId = mydatabase.InsertData(Name,Age,Phone);
                   if(rowId>0){
                       Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
                       name.setText("");
                       age.setText("");
                       phone.setText("");
                   }
                   else{
                       Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();
                   }
           }
           else if(v.getId()==R.id.showbtn){
               loadData();
           }
           else if(v.getId()==R.id.searchbtn){
               String s = searchEt.getText().toString();
               if(s.isEmpty()){
                   searchEt.setError("Num required");
                   return;
               }
               ShowSearchResult();
           }
    }

    private void ShowSearchResult() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.valueshow,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Button update,delete;
        final EditText name = view.findViewById(R.id.searchNameEt);
        final EditText age = view.findViewById(R.id.searchAgeEt);
        final EditText phone = view.findViewById(R.id.searchPhoneEt);
        update = view.findViewById(R.id.update);
        delete = view.findViewById(R.id.delete);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        String s = searchEt.getText().toString();
        final int id = Integer.parseInt(s);
        Cursor cursor = mydatabase.Search(id);
        if(cursor.getCount()==0){
            alertDialog.dismiss();
            Toast.makeText(this, "ID not Found", Toast.LENGTH_SHORT).show();
            return;
        }
        while (cursor.moveToNext()){
            name.setText(cursor.getString(1));
            age.setText(cursor.getString(2));
            phone.setText(cursor.getString(3));
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydatabase.Update(id,name.getText().toString(),age.getText().toString(),phone.getText().toString());
                Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mydatabase.Delete(id);
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }
    private void loadData() {
              Intent intent = new Intent(MainActivity.this,AllDataShow.class);
              startActivity(intent);
    }

}
