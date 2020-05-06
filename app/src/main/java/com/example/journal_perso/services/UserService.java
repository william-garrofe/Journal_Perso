package com.example.journal_perso.services;

import android.content.Context;

import com.example.journal_perso.models.ListUser;
import com.example.journal_perso.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class UserService {

    private AsyncHttpClient client;

    private String BASE_URL = "http://10.0.2.2/api/user/";

    public UserService() {
        this.client = new AsyncHttpClient();
    }

    public void getListUser(final OnJSONResponseCallback callback) {

        String url = BASE_URL + "read.php";

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
                    ListUser listUser = gsonBuilder.fromJson(jsonObject.toString(), ListUser.class);
                    callback.onJSONResponse(true, listUser);
                } catch (JSONException exception) {
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println(error.getMessage());
            }
        });

    }

    public void createUser(final OnJSONResponseCallback callback, String nom, String prenom, String email, String motdepasse, Context context) throws JSONException, UnsupportedEncodingException {

        String url = BASE_URL + "create.php";
        JSONObject jsObj = new JSONObject();
        jsObj.put("nom", nom);
        jsObj.put("prenom", prenom);
        jsObj.put("email", email);
        jsObj.put("motdepasse", motdepasse);
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

    public void getUser(final OnJSONResponseCallback callback, String email, String motdepasse, Context context) throws JSONException, UnsupportedEncodingException {

        final String url = BASE_URL + "read_one.php";
        JSONObject jsObj = new JSONObject();

        jsObj.put("email", email);
        jsObj.put("motdepasse", motdepasse);
        StringEntity s = new StringEntity(jsObj.toString());

        client.get(context, url, s, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gsonBuilder = new GsonBuilder()
                        .serializeNulls()
                        .disableHtmlEscaping()
                        .setPrettyPrinting()
                        .create();

                try {
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    User user = gsonBuilder.fromJson(jsonObject.toString(), User.class);
                    callback.onJSONResponse(true, user);
                } catch (JSONException exception) {
                }

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
