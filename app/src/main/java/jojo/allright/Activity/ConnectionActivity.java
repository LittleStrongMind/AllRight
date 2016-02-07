/***
 *
 *
 *                   AAA               lllllll lllllll RRRRRRRRRRRRRRRRR     iiii                     hhhhhhh                     tttt
 *                  A:::A              l:::::l l:::::l R::::::::::::::::R   i::::i                    h:::::h                  ttt:::t
 *                 A:::::A             l:::::l l:::::l R::::::RRRRRR:::::R   iiii                     h:::::h                  t:::::t
 *                A:::::::A            l:::::l l:::::l RR:::::R     R:::::R                           h:::::h                  t:::::t
 *               A:::::::::A            l::::l  l::::l   R::::R     R:::::Riiiiiii    ggggggggg   gggggh::::h hhhhh      ttttttt:::::ttttttt
 *              A:::::A:::::A           l::::l  l::::l   R::::R     R:::::Ri:::::i   g:::::::::ggg::::gh::::hh:::::hhh   t:::::::::::::::::t
 *             A:::::A A:::::A          l::::l  l::::l   R::::RRRRRR:::::R  i::::i  g:::::::::::::::::gh::::::::::::::hh t:::::::::::::::::t
 *            A:::::A   A:::::A         l::::l  l::::l   R:::::::::::::RR   i::::i g::::::ggggg::::::ggh:::::::hhh::::::htttttt:::::::tttttt
 *           A:::::A     A:::::A        l::::l  l::::l   R::::RRRRRR:::::R  i::::i g:::::g     g:::::g h::::::h   h::::::h     t:::::t
 *          A:::::AAAAAAAAA:::::A       l::::l  l::::l   R::::R     R:::::R i::::i g:::::g     g:::::g h:::::h     h:::::h     t:::::t
 *         A:::::::::::::::::::::A      l::::l  l::::l   R::::R     R:::::R i::::i g:::::g     g:::::g h:::::h     h:::::h     t:::::t
 *        A:::::AAAAAAAAAAAAA:::::A     l::::l  l::::l   R::::R     R:::::R i::::i g::::::g    g:::::g h:::::h     h:::::h     t:::::t    tttttt
 *       A:::::A             A:::::A   l::::::ll::::::lRR:::::R     R:::::Ri::::::ig:::::::ggggg:::::g h:::::h     h:::::h     t::::::tttt:::::t
 *      A:::::A               A:::::A  l::::::ll::::::lR::::::R     R:::::Ri::::::i g::::::::::::::::g h:::::h     h:::::h     tt::::::::::::::t
 *     A:::::A                 A:::::A l::::::ll::::::lR::::::R     R:::::Ri::::::i  gg::::::::::::::g h:::::h     h:::::h       tt:::::::::::tt
 *    AAAAAAA                   AAAAAAAllllllllllllllllRRRRRRRR     RRRRRRRiiiiiiii    gggggggg::::::g hhhhhhh     hhhhhhh         ttttttttttt
 *                                                                                             g:::::g
 *                                                                                 gggggg      g:::::g
 *                                                                                 g:::::gg   gg:::::g
 *                                                                                  g::::::ggg:::::::g
 *                                                                                   gg:::::::::::::g
 *                                                                                     ggg::::::ggg
 *                                                                                        gggggg
 *
 *  AUTHOR : Josua Vadeleux
 *  CREATION DATE : 08/05/2015
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
import jojo.allright.RESTFunction.RESTConnection;

// TODO put connection indicator to warn the user of the server's connection

// TODO capture the last information enter in the Subscribe Activity to fill the connection's fields with it
// TODO put some focus effect : when a field is selected, hint disappear.
// TODO put some effect on the signin/signup button
// TODO put some effect on the field : when a field is selected you change hist stroke color

// TODO OPTIONAL : put some animation, transition, etc...

// TODO rearrange the code
// TODO refactor the code

// TODO GLOBAL disable the return button on the android phone for connection activity
// TODO put some comments
// TODO improve the error textView

public class ConnectionActivity extends Activity{

    static final String EXTRA_USERNAME = "";

    private JSONObject jsonObject;
    private EditText usernameFieldValue;
    private boolean serverError;

    public ConnectionActivity(){
        jsonObject = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_activity);
    }
    @Override
    public void onBackPressed(){
    }
    /* On se connecte sur notre compte */
    public void connect(View view){
        usernameFieldValue          = (EditText) findViewById(R.id.usernameTextField);
        EditText passwordFieldValue = (EditText) findViewById(R.id.passwordTextField);

        String username = usernameFieldValue.getText().toString();
        String password = passwordFieldValue.getText().toString();

        // TODO remove the backdoor
        if(username.equals("admin") && password.equals("admin")){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else {
            String finalUrl = "http://192.168.137.234/api.php?action=connection&user=" + username + "&psswd=" + password;

            RESTConnection restConnexion = new RESTConnection(this);
            restConnexion.execute(finalUrl);
        }
    }

    /* On s'inscrit */
    public void signUp(View view){
        Intent intent = new Intent(this, SubscribeActivity.class);
        startActivity(intent);
    }

    public void setJsonObject(JSONObject jsonObject, boolean serverError){
        this.jsonObject  = jsonObject;
        this.serverError = serverError;
    }
    public void processJsonObject(){
        TextView errorField = (TextView) findViewById(R.id.errorConnectionTextView);

        if(serverError){
            errorField.setText(R.string.serverConnectionErrorStringValue);
        }
        else {
            try {
                if (jsonObject.get("querryExecuted").toString().equals("false")) {
                    errorField.setText("Erreur lors de l'éxécution de la requete");
                } else {
                    if (jsonObject.get("userFind").toString().equals("false")) {
                        errorField.setText("Impossible de vous trouver !");
                    } else {
                        Intent intent = new Intent(this, HomeActivity.class);

                        intent.putExtra(EXTRA_USERNAME, usernameFieldValue.getText().toString());
                        startActivity(intent);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                errorField.setText(R.string.errorJsonObjectNullStringValue);
            }
        }
    }
}