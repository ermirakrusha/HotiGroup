package com.example.ermira.hotigroup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ermira on 12/4/2016.
 */
public class ProductsFragment extends Fragment {

    private static final String url = "http://192.168.1.2/AE/products.php";
    private ProgressDialog pDialog;
    private List<Product> productList = new ArrayList<Product>();
    private ListView listView;
    private Adapteri adapter;

    public ProductsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View w = inflater.inflate(R.layout.fragment_products, container, false);

        listView = (ListView) w.findViewById(R.id.list);
        adapter = new Adapteri(getContext(), productList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        hidePDialog();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                Product product = new Product();
                                    product.setId(Integer.parseInt(obj.getString("id_products")));
                                    product.setTitle(obj.getString("title"));
                                    product.setThumbnailUrl(obj.getString("img"));
                                    product.setDescription(obj.getString("description"));
                                productList.add(product);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidePDialog();

            }
        });
        MySingleton.getInstance().addToRequestQueue(movieReq);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent ie = new Intent(getContext(), ProductDetail.class);
                ie.putExtra("item", productList.get(i));
                startActivity(ie);
            }
        });
    return w;
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
