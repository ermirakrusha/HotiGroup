package com.example.ermira.hotigroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class ProductDetail extends AppCompatActivity {
    private Product item;
    TextView title, description;
    NetworkImageView networkImageView;
    ImageLoader imageLoader = MySingleton.getInstance().getImageLoader();
    Toolbar toolbar;
    Button addKoment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetails);
        item = (Product) getIntent().getSerializableExtra("item");

        title = (TextView) findViewById(R.id.titlee);
        description = (TextView) findViewById(R.id.descriptionn);
        networkImageView = (NetworkImageView) findViewById(R.id.imagee);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        addKoment = (Button) findViewById(R.id.addkoment);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(item.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title.setText(item.getTitle());
        description.setText(item.getDescription());
        networkImageView.setImageUrl(item.getThumbnailUrl(), imageLoader);

        addKoment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(ProductDetail.this, AddComment.class);
                e.putExtra("idItem", item.toString());
                startActivity(e);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }
}
