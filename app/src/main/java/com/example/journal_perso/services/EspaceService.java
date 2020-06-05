package com.example.journal_perso.services;

import android.content.Context;

import com.example.journal_perso.models.StructData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class EspaceService {

    private AsyncHttpClient client;
    private String BASE_URL = "http://10.0.2.2/api/espace/";

    public EspaceService() {
        this.client = new AsyncHttpClient();
    }

    public void getListEspace(final OnJSONResponseCallback callback) throws JSONException, UnsupportedEncodingException {

        String url = BASE_URL + "read.php"; //""
        try {
            client.get(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Gson gsonBuilder = new GsonBuilder()
                            .serializeNulls()
                            .disableHtmlEscaping()
                            .setPrettyPrinting()
                            .create();

                    try {
                        JSONObject jsonObject = new JSONObject(new String(responseBody));
                        StructData listEspace = gsonBuilder.fromJson(jsonObject.toString(), StructData.class);
                        callback.onJSONResponse(true, listEspace);
                    } catch (JSONException exception) {
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    System.out.println(error.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createEsapce(final OnJSONResponseCallback callback, String nom, int id, Context context) throws JSONException, UnsupportedEncodingException {

        String url = BASE_URL + "create.php";
        JSONObject jsObj = new JSONObject();
        jsObj.put("nomEspace", nom);
        jsObj.put("user_idUser", id);
        StringEntity s = new StringEntity(jsObj.toString());

        client.post(context, url, s, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                callback.onJSONResponse(true, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println(error.getMessage());
            }
        });

    }


    public interface OnJSONResponseCallback {
        public void onJSONResponse(boolean success, Object object);
    }


}
