package com.example.ermira.hotigroup;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button loginbtn,linktoregister;
    String Username,Password;
    String login_url = "http://192.168.100.8/AE/login.php";
    AlertDialog.Builder builder;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        if(Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.login_background));
        }

        username = (EditText)findViewById(R.id.usernameL);
        password = (EditText)findViewById(R.id.passwordL);
        loginbtn = (Button)findViewById(R.id.btnLogin);
        linktoregister = (Button)findViewById(R.id.btnLinkToRegisterScreen);
        builder = new AlertDialog.Builder(Login.this);




        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Username = username.getText().toString();
                Password = password.getText().toString();

                if(Username.equals("") || Password.equals("")){
                    builder.setTitle("Somthing went worng...");
                    displayAlert("Enter a valid Username or Password!");
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");

                                if (code.equals("login_failed")){
                                    builder.setTitle("Login Error...");
                                    displayAlert(jsonObject.getString("message"));
                                } else {
                                    editor = getSharedPreferences("loginCrd",MODE_PRIVATE).edit();
                                    editor.putInt("id_user", jsonObject.getInt("id_user"));
                                    editor.putString("name", jsonObject.getString("name"));
                                    editor.putString("email", jsonObject.getString("email"));
                                    editor.commit();
                                    
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, "Error...!", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("username",Username);
                            params.put("password",Password);
                            return params;
                         }
                    };

                    MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);
                }
            }
        });


    }
    public void displayAlert(String message){
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                username.setText("");
                password.setText("");
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
