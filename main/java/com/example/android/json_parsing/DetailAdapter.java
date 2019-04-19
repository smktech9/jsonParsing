package com.example.android.json_parsing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public DetailAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Details object)
    {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row;
        row = convertView;
        DetailHolder detailHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context
            .LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.list_item,parent,false);
            detailHolder = new DetailHolder();
            detailHolder.tv_name=(TextView) row.findViewById(R.id.name);
            detailHolder.tv_email=(TextView) row.findViewById(R.id.email);
            detailHolder.tv_mobile=(TextView) row.findViewById(R.id.mobile);
            row.setTag(detailHolder);
        }

        else
        {
            detailHolder = (DetailHolder) row.getTag();
        }

        Details details = (Details) this.getItem(position);
        detailHolder.tv_name.setText(details.getName());
        detailHolder.tv_email.setText(details.getEmail());
        detailHolder.tv_mobile.setText(details.getMobile());
        return row;

    }

    static class DetailHolder
    {
        TextView tv_name,tv_email,tv_mobile;
    }
}
