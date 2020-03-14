package com.example.sqlitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ModelClass> arrayList;

    public MyAdapter(Context context, ArrayList<ModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,null);
        TextView id = convertView.findViewById(R.id.showidEt);
        TextView name = convertView.findViewById(R.id.showNameEt);
        TextView age = convertView.findViewById(R.id.showAgeEt);
        TextView phone = convertView.findViewById(R.id.showphnEt);
        ModelClass modelClass = arrayList.get(position);
        id.setText("ID: "+modelClass.getId());
        name.setText("Name: "+modelClass.getName());
        age.setText("Age: "+modelClass.getAge());
        phone.setText("Phone: "+modelClass.getPhone());
        return convertView;
    }
}
