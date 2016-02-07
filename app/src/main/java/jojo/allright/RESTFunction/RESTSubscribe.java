package jojo.allright.RESTFunction;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jojo.allright.Activity.SubscribeActivity;

/**
 * Created by Jojo on 08/05/2015.
 */
public class RESTSubscribe extends AsyncTask<String, Void, Void>{

    private JSONObject jsonObject;
    private SubscribeActivity activity;
    private boolean serverError;

    public RESTSubscribe(SubscribeActivity activity){
        this.activity       = activity;
        this.jsonObject     = null;
        this.serverError    = false;
    }

    @Override
    protected Void doInBackground(String... params){
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            String result = InputStreamOperations.InputStreamToString(inputStream);
            this.jsonObject = new JSONObject(result);
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            serverError = true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result){
        activity.setJsonObject(this.jsonObject, serverError);
        activity.processJsonObject();
    }

}
