package com.example.myapplication;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DataService {
    public static final String URL = "https://zen-calendar.herokuapp.com/api/v1/1/";
    Context context;

    public DataService(Context context) {
        this.context = context;
    }

    public interface ResponseListener {
        void onResponse();
    }

    public void getData(List<String> list, ResponseListener responseListener, int month, int year) {
        list.clear();
        String url = URL + month + "/" + year;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        int length = response.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject note = response.getJSONObject(i);
                            String content = note.getString("content");
                            list.add(content);
                        }
                        responseListener.onResponse();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> {
                    System.err.println(error.getMessage());
                });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
