package jojo.allright.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import jojo.allright.R;
import jojo.allright.RESTFunction.RESTUnsubscribe;

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

// TODO Put some color on the word "community"
// TODO Put some color on the word "connection"
// TODO put some comments

public class AfterUnsubscribeActivity extends Activity {

    private TextView currentUsername;

    public AfterUnsubscribeActivity(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_unsubscribe_activity);
        Intent intent = getIntent();
        Log.d("BITE", "bite");
        currentUsername = (TextView) findViewById(R.id.currentusernameUnsubscribe);
        currentUsername.setText(intent.getStringExtra(ConnectionActivity.EXTRA_USERNAME));
    }
    @Override
    public void onBackPressed(){
    }
    public void comfirmUnsubscribe(View view){

        String finalUrl = "http://192.168.137.234/api.php?action=unsubscribe&user=" + currentUsername.getText().toString();
        Log.d("TEST", finalUrl);
        RESTUnsubscribe restUnsubscribe = new RESTUnsubscribe(this);
        restUnsubscribe.execute(finalUrl);
    }
    public void backToConnection(){
        Intent intent = new Intent(this, ConnectionActivity.class);
        startActivity(intent);
    }
}
