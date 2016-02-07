package jojo.allright.RESTFunction;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jojo.allright.Activity.HomeActivity;

/***
 *      /$$$$$$  /$$ /$$ /$$$$$$$  /$$           /$$         /$$
 *     /$$__  $$| $$| $$| $$__  $$|__/          | $$        | $$
 *    | $$  \ $$| $$| $$| $$  \ $$ /$$  /$$$$$$ | $$$$$$$  /$$$$$$
 *    | $$$$$$$$| $$| $$| $$$$$$$/| $$ /$$__  $$| $$__  $$|_  $$_/
 *    | $$__  $$| $$| $$| $$__  $$| $$| $$  \ $$| $$  \ $$  | $$
 *    | $$  | $$| $$| $$| $$  \ $$| $$| $$  | $$| $$  | $$  | $$ /$$
 *    | $$  | $$| $$| $$| $$  | $$| $$|  $$$$$$$| $$  | $$  |  $$$$/
 *    |__/  |__/|__/|__/|__/  |__/|__/ \____  $$|__/  |__/   \___/
 *                                     /$$  \ $$
 *                                    |  $$$$$$/
 *                                     \______/
 *
 *      AUTHOR : Jojo
 *      CREATION DATE : 17/05/2015
 *
 */
public class RESTGetProject extends AsyncTask<String, Void, Void> {

    private JSONObject jsonObject;
    private HomeActivity activity;
    private boolean serverError;

    public RESTGetProject(HomeActivity activity){
        this.activity   = activity;
        jsonObject      = null;
        serverError     = false;
    }

    @Override
    protected Void doInBackground(String... params) {
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
    protected void onPostExecute(Void result) {
        activity.setJsonObject(this.jsonObject, serverError);
        activity.processJsonObject();
    }
}
