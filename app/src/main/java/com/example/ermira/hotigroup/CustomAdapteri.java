package com.example.ermira.hotigroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapteri extends BaseAdapter {

    Context con;
    List<Comment> com;
    LayoutInflater layoutinflater;

    public CustomAdapteri(Context applicationContext, List<Comment> c) {
        this.con = applicationContext;
        this.com = c;
    }

    @Override
    public int getCount() {
        return com.size();
    }

    @Override
    public Object getItem(int i) {
        return com.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (layoutinflater == null)
            layoutinflater = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = layoutinflater.inflate(R.layout.simple_list_item_1, null);

        TextView teksti = (TextView) view.findViewById(R.id.teksti);

        Comment commenti = com.get(i);

        teksti.setText(commenti.toString());

        return view;
    }
}
