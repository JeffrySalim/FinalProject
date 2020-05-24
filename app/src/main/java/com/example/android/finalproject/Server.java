package com.example.android.finalproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Server {

    public static String Url = BuildConfig.BASE_URL;
    public static RequestQueue queue;
    public static StringRequest stringRequest;

    public static void Login(final String username, final String password, final Context context) {
        queue = Volley.newRequestQueue(context);
        stringRequest = new StringRequest(Request.Method.GET, Url + "User.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object1 = new JSONObject(response);
                    JSONArray array = object1.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object2 = array.getJSONObject(i);
                        User user1 = new User(object2);

                        if (user1.user.equals(username) && user1.pass.equals(password)) {
                            Intent intent = new Intent(context, HomeActivity.class);
                            context.startActivity(intent);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    static void Daftar(String pass, String user, String email, Context context){
        String UrlDaftar = Url+"Login.php?user="+user+"&pass="+pass+"&email="+email;
        stringRequest = new StringRequest(Request.Method.GET, UrlDaftar, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String kode = object.getString("kode");
                    String pesan = object.getString("pesan");
                    Log.d("TAG", "onResponse: " + pesan);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }
}