package com.example.ermira.hotigroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class Adapteri extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<Product> products;
    ImageLoader imageLoader = MySingleton.getInstance().getImageLoader();

    public Adapteri(Context context, List<Product> products){
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(R.layout.listrow, null);

        if (imageLoader == null)
            imageLoader = MySingleton.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) view.findViewById(R.id.thumbnail);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView description = (TextView) view.findViewById(R.id.description);

        Product p = products.get(i);

        thumbNail.setImageUrl(p.getThumbnailUrl(),imageLoader);
        title.setText(p.getTitle());
        try {
            description.setText(p.getDescription().toString().substring(0, 40)+"...");
        } catch (Exception e){
            description.setText(p.getDescription().toString());
        }
        return view;
    }
}
