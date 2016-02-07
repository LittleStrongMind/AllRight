/***
 *
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

package jojo.allright.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jojo.allright.R;

// TODO the menu of the user
// TODO the button add a project and the activity behind
// TODO add the project list

// TODO fix the login button size when usernames are big
// TODO add some animation to the caret

// TODO GLOBAL disable the return button on the android phone for connection activity
// TODO put some comments

public class HomeActivity extends Activity {

    static final String EXTRA_USERNAME = "";

    private boolean menuOn = false;
    private TextView currentUsername;
    private JSONObject jsonObject;
    private boolean serverError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        serverError = false;
        currentUsername = (TextView) findViewById(R.id.currentUsername);
        Intent intent = getIntent();
        currentUsername.setText(intent.getStringExtra(ConnectionActivity.EXTRA_USERNAME));
    }

    @Override
    public void onBackPressed() {
    }

    public void openMenu(View view) {

        RelativeLayout userMenu = (RelativeLayout) findViewById(R.id.userMenu);

        if (!menuOn) {
            userMenu.setVisibility(View.VISIBLE);
            menuOn = true;
        } else {
            userMenu.setVisibility(View.INVISIBLE);
            menuOn = false;
        }
    }
    // TODO when user disconnect modify the row isconnect in database

    public void Unsubscribe(View view) {
        Intent intent = new Intent(this, AfterUnsubscribeActivity.class);
        intent.putExtra(EXTRA_USERNAME, currentUsername.getText().toString());
        startActivity(intent);
    }

    public void backToConnection(View view) {
        Intent intent = new Intent(this, ConnectionActivity.class);
        startActivity(intent);
    }
    public void setJsonObject(JSONObject jsonObject, boolean serverError){
        this.jsonObject = jsonObject;
        this.serverError = serverError;
    }
    public void addProjectInScrollView(String projectName, String ProjectDescription){
        RelativeLayout layout = new RelativeLayout(this);
        TextView projectNameTextView = new TextView(this);

        projectNameTextView.setText(projectName);
    }
    public void processJsonObject(){

        TextView errorField = (TextView) findViewById(R.id.errorConnectionTextView);

        if(serverError){
            errorField.setText("Can't get your project !!");
        }
        else{
            try {
                JSONArray jsonArray = new JSONArray(jsonObject.getString("projects"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
