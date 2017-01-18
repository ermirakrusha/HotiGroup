package com.example.ermira.hotigroup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

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

public class Register extends AppCompatActivity {

    Button register_button,btnLinkToLoginScreen;
    EditText name,lastname,email,username,password,conpass;
    String Name,LastName,Email,UserName,Password,ConPass;
    AlertDialog.Builder builder;
    String register_url = "http://192.168.1.2/AE/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        if(Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.register_background));
        }

        register_button = (Button)findViewById(R.id.btnRegister);
        name = (EditText)findViewById(R.id.name);
        lastname = (EditText)findViewById(R.id.lastname);
        email = (EditText)findViewById(R.id.email);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        conpass = (EditText)findViewById(R.id.conpassword);
        btnLinkToLoginScreen = (Button)findViewById(R.id.btnLinkToLoginScreen);
        btnLinkToLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

        builder = new AlertDialog.Builder(Register.this);
        Log.d("ipt> ",register_url);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = name.getText().toString();
                LastName = lastname.getText().toString();
                Email = email.getText().toString();
                UserName = username.getText().toString();
                Password = password.getText().toString();
                ConPass = password.getText().toString();

                if(Name.equals("") || LastName.equals("") || Email.equals("") || UserName.equals("") || Password.equals("") || ConPass.equals("")){
                    builder.setTitle("Somthing went wrong...");
                    builder.setMessage("Please fill all the fields...");
                } else {
                    if(!Password.equals(ConPass)){
                        builder.setTitle("Somthing went wrong...");
                        builder.setMessage("Your passwords are not matching...");
                        displayAlert("input_error");
                    } else {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, register_url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    String code = jsonObject.getString("code");
                                    String message = jsonObject.getString("message");
                                    builder.setTitle("Server Response...");
                                    builder.setMessage(message);
                                    displayAlert(code);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params = new HashMap<String, String>();
                                params.put("name",Name);
                                params.put("lastname",LastName);
                                params.put("email",Email);
                                params.put("username",UserName);
                                params.put("password",Password);
                                return params;
                            }
                        };

//                        MySingleton.getInstance(Register.this).addToRequestque(stringRequest);
                        MySingleton.getInstance().addToRequestQueue(stringRequest);
                    }
                }
            }
        });

    }

    public void displayAlert(final String code){
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(code.equals("input_error")){
                    password.setText("");
                    conpass.setText("");
                } else if(code.equals("reg_success")){
                    finish();
                } else if (code.equals("reg_failed")){
                    name.setText("");
                    lastname.setText("");
                    email.setText("");
                    username.setText("");
                    password.setText("");
                    conpass.setText("");
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
