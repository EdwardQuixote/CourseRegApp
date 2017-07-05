package uk.co.hotmail.edwardquixote.courseregapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import uk.co.hotmail.edwardquixote.courseregapp.Tasks_Threads_Handlers.BackgroundAsyncWorker;

public class SignInActivity extends AppCompatActivity {

    public static String sSIGN_IN_RESPONSE;

    private EditText edUserName;
    private EditText edPassword;

    private String sUsername;
    private String sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //  Calling onCreate() method which is in Super class.
        setContentView(R.layout.activity_sign_in);    //  Method for loading xml file.

        initializeVariablesAndUIObjects();
    }

    /**
     * Method to initialize variables and Objects used in UI.
     *
     * Called in this.onCreate();
     */
    private void initializeVariablesAndUIObjects() {

        edUserName = (EditText) this.findViewById(R.id.edSignInUsername);
        edPassword = (EditText) this.findViewById(R.id.edSignInPassword);

        TextView txtSignUp = (TextView) this.findViewById(R.id.txtSISignUp);
        txtSignUp.setOnClickListener(clkLogin);

        Button btnLogin = (Button) this.findViewById(R.id.btnSignIn);
        btnLogin.setOnClickListener(clkLogin);

    }

    /**
     * Method to validate the credentials,
     * user provided.
     *
     * Called in this.initializeVariablesAndUIObjects().btnLogin.onClick();
     */
    private void codeToValidateLoginCredentials() {

        sUsername = edUserName.getText().toString();
        sPassword = edPassword.getText().toString();
        if (sUsername.equalsIgnoreCase("")) {
            codeToGenerateAlertDialogs("Invalid Username.");

            edUserName.requestFocus();
        } else if (sPassword.equalsIgnoreCase("")) {
            codeToGenerateAlertDialogs("Invalid Password.");

            edPassword.requestFocus();
        } else {
            codeToSignInStudent();
        }
    }

    /**
     * Method to sign in Student into Database,
     * compares credentials with the ones in the Database.
     *
     * Called in this.codeToValidateLoginCredentials();
     */
    private void codeToSignInStudent() {

        String sEXECUTION_TAG = this.getResources().getString(R.string.sTagSignIn);

        BackgroundAsyncWorker clsBackgroundWorker = new BackgroundAsyncWorker(SignInActivity.this);
        clsBackgroundWorker.execute(sEXECUTION_TAG, sUsername, sPassword);

        Intent inStartHomeActivity = new Intent(SignInActivity.this, HomeActivity.class);
        this.finish();
        startActivity(inStartHomeActivity);

    }

    /**
     * Method to start Sign Up Activity.
     *
     * Called in this.clkLogin.onClick();
     */
    private void codeToStartSignUpActivity() {

        Intent inStartSignUp = new Intent(SignInActivity.this, SignUpActivity.class);
        this.finish();
        startActivity(inStartSignUp);

    }

    /**
     * Method to generate Alert Dialogs.
     *
     * TODO: May need to be moved to its respective class: CR_Dialogs.java;
     *
     * Called in this.codeToValidateLoginCredentials();
     *
     * @param dialogMessage (String)
     */
    private void codeToGenerateAlertDialogs(String dialogMessage) {

        AlertDialog.Builder adgbldBuilder = new AlertDialog.Builder(SignInActivity.this);
        adgbldBuilder.setTitle("Login");
        adgbldBuilder.setMessage(dialogMessage);
        adgbldBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        adgbldBuilder.setPositiveButton("Ok", null);

        AlertDialog adgDialog = adgbldBuilder.create();
        adgDialog.show();
    }


    private View.OnClickListener clkLogin = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            int iViewId = v.getId();
            switch (iViewId) {
                case R.id.btnSignIn:
                    codeToValidateLoginCredentials();
                    break;

                case R.id.txtSISignUp:
                    codeToStartSignUpActivity();
                    break;
            }
        }
    };
}
