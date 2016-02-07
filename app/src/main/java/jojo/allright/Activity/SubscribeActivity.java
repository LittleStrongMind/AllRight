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

package jojo.allright.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import jojo.allright.R;
import jojo.allright.RESTFunction.RESTSubscribe;

// TODO put connection indicator to warn the user of the server's connection
// TODO put some color on the word "connection"
// TODO put some effect on the signin/signup button
// TODO put some effect on the field : when a field is selected you change hist stroke color
// TODO put some focus effect : when a field is selected, hint disappear.
// TODO rearrange the code
// TODO refactor the code
// TODO put some comments
// TODO improve the error textView

public class SubscribeActivity extends Activity {

    private JSONObject jsonObject;
    private boolean serverError;

    private TextView errorField;
    private EditText usernameField;
    private EditText passwordField;
    private EditText emailField;
    private EditText comfirmPasswordField;

    public SubscribeActivity(){
        jsonObject = null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscribe_activity);
    }

    public void backToConnection(View view){
        Intent intent = new Intent(this, ConnectionActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
    }

    public void subscribe(View view){

        errorField = (TextView) findViewById(R.id.errorSubscribeTextView);
        usernameField = (EditText) findViewById(R.id.usernameTextField);
        passwordField = (EditText) findViewById(R.id.passwordTextField);

        comfirmPasswordField = (EditText) findViewById(R.id.comfirmPasswordTextField);
        emailField = (EditText) findViewById(R.id.emailTextField);

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        String comfirmPassword  = comfirmPasswordField.getText().toString();
        String email            = emailField.getText().toString();

        if(!password.equals(comfirmPassword)){
            errorField.setText(R.string.errorSubscribeWrongCorrespondancePasswordStringValue);
            passwordField.setBackgroundResource(R.drawable.custom_edit_text_error_red);
            comfirmPasswordField.setBackgroundResource(R.drawable.custom_edit_text_error_red);
        }
        else {
            if(username.equals("") || password.equals("")){

                if(username.equals("")){
                    errorField.setText(R.string.errorUsernameFieldEmptyStringValue);
                    usernameField.setBackgroundResource(R.drawable.custom_edit_text_error_orange);
                }
                if(password.equals("")){
                    errorField.setText(R.string.errorPasswordFieldEmptyStringValue);
                    passwordField.setBackgroundResource(R.drawable.custom_edit_text_error_orange);
                }
            }
            String finalUrl = "http://192.168.137.234/api.php?action=subscribe&user=" + username + "&psswd=" + password + "&email=" + email;
            RESTSubscribe restSubscribe = new RESTSubscribe(this);
            restSubscribe.execute(finalUrl);
        }
    }

    public void setJsonObject(JSONObject jsonObject, boolean serverError){
        this.jsonObject     = jsonObject;
        this.serverError    = serverError;
    }

    public void processJsonObject(){
        usernameField.setBackgroundResource(R.drawable.custom_edit_text);
        emailField.setBackgroundResource(R.drawable.custom_edit_text);
        passwordField.setBackgroundResource(R.drawable.custom_edit_text);
        comfirmPasswordField.setBackgroundResource(R.drawable.custom_edit_text);

        TextView errorField = (TextView) findViewById(R.id.errorSubscribeTextView);
        if(serverError){
            errorField.setText(R.string.serverConnectionErrorStringValue);
        }
        else {
            try {
                String errorString = "";
                if (this.jsonObject.get("usernameAlreadyExist").toString().equals("true") || this.jsonObject.get("emailAlreadyExist").toString().equals("true")) {

                    if (this.jsonObject.get("usernameAlreadyExist").toString().equals("true")) {
                        errorString = errorString + "username already exist";
                        usernameField.setBackgroundResource(R.drawable.custom_edit_text_error_orange);
                    }

                    if (this.jsonObject.get("emailAlreadyExist").toString().equals("true")) {
                        errorString = errorString + "\nemailAlreayExist";
                        emailField.setBackgroundResource(R.drawable.custom_edit_text_error_orange);
                    }
                } else {
                    Intent intent = new Intent(this, AfterSubscribeActivity.class);
                    startActivity(intent);
                }

                errorField.setText(errorString);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                errorField.setText(R.string.errorJsonObjectNullStringValue);
            }
        }
    }
}