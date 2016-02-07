package jojo.allright.RESTFunction;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jojo.allright.Activity.AfterUnsubscribeActivity;

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

public class RESTUnsubscribe extends AsyncTask<String, Void, Void> {
    private boolean serverError;
    private AfterUnsubscribeActivity activity;
    private HttpURLConnection connection;

    public RESTUnsubscribe(AfterUnsubscribeActivity activity){
        serverError = false;
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            serverError = true;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        connection.disconnect();
        activity.backToConnection();
    }

}