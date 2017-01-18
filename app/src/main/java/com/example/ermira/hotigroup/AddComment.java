package com.example.ermira.hotigroup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddComment extends AppCompatActivity{
    Toolbar toolbar;
    EditText mesazhi;
    Button dergo,shfaqi;
    private ProgressDialog pDialog;

    CustomAdapteri arrayAdapter;
    List<Comment> komnetet = new ArrayList<Comment>();

    ListView listView;
    private static final String url = "http://192.168.0.109/AE/comments.php";
    private static final String urlC = "http://192.168.0.109/AE/addcomments.php";
    String id;
    int idu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcomment);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Komentet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id = getIntent().getStringExtra("idItem");


        listView = (ListView) findViewById(R.id.commentet);
        arrayAdapter = new CustomAdapteri(this, komnetet);
        listView.setAdapter(arrayAdapter);


        mesazhi = (EditText) findViewById(R.id.mesazhi);
        dergo = (Button) findViewById(R.id.addcomment);

        SharedPreferences s = getSharedPreferences("loginCrd", MODE_PRIVATE);
        idu = s.getInt("id_user", -1);

        pDialog = new ProgressDialog(AddComment.this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        shfaq();

        dergo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlC,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "U shtua me Sukses!", Toast.LENGTH_LONG).show();
                                komnetet.clear();
                                shfaq();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("textcomment", mesazhi.getText().toString());
                        params.put("id_products", id);
                        params.put("id_user", String.valueOf(idu));
                        return params;
                    }

                };

                MySingleton.getInstance().addToRequestQueue(stringRequest);
            }
        });
    }

    public void shfaq(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hidePDialog();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Comment comment = new Comment();
                        comment.setText(jsonObject.getString("textcomment"));
                        komnetet.add(comment);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                arrayAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddComment.this, "Error...!", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
                hidePDialog();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_comments", id);
                return params;
            }
        };
        MySingleton.getInstance().addToRequestQueue(stringRequest);
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
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
