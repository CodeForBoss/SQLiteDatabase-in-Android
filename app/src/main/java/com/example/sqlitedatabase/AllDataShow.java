package com.example.sqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllDataShow extends AppCompatActivity {
    private ListView list;
    ArrayList<ModelClass> arrayList;
    MyAdapter myAdapter;
    Mydatabase mydatabase;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data_show);
        mydatabase = new Mydatabase(this);
        list = findViewById(R.id.myList);
        loaddata();
    }
    private void loaddata() {
        arrayList = mydatabase.getData();
        myAdapter = new MyAdapter(this,arrayList);
        list.setAdapter(myAdapter);
    }
}
