package com.wap.frontend.com.wap.restful.frontend;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import com.wap.frontend.com.wap.frontend.properties.Constants;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


import javax.json.JsonObject;

import static com.wap.frontend.com.wap.frontend.properties.Constants.STATUS_OK;

public class RESTClient extends AsyncTask {
    private Class targetActivity;
    private String targetUrl;
    private JsonObject request;
    private TextView errorTv;
    private Activity parent;


    public RESTClient(Activity parent, Class targetActivity, String targetUrl, JsonObject request, TextView errorTv){
        this.parent = parent;
        this.targetActivity = targetActivity;
        this.targetUrl = targetUrl;
        this.request = request;
        this.errorTv = errorTv;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        JsonObject response;
        try{
            URL url = new URL(targetUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setReadTimeout(1000);
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(request.toString());
            out.close();

            response = JsonConverter.parse(connection.getInputStream());
            System.out.println("----------------------");
            System.out.println(response.toString());
            System.out.println("----------------------");


            return response;
        }catch(Exception e){
            e.printStackTrace();
            //TODO add exception handling
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        JsonObject response = (JsonObject) o;
        if(response != null) {
            switch (response.getString(Constants.STATUS)) {
                case STATUS_OK:
                    Intent intent = new Intent(parent, targetActivity);
                    intent.putExtra("username", request.getString("username"));
                    parent.startActivity(intent);
                    break;
                default:
                    errorTv.setText(response.getString(Constants.STATUS));
            }
        }else{
            errorTv.setText("Login url"+targetUrl);
        }
    }


}
