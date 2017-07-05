package uk.co.hotmail.edwardquixote.courseregapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import uk.co.hotmail.edwardquixote.courseregapp.Dialogs.CR_Dialogs;
import uk.co.hotmail.edwardquixote.courseregapp.Tasks_Threads_Handlers.BackgroundAsyncWorker;

public class SignUpActivity extends AppCompatActivity {

    private CR_Dialogs clsDialogs;

    private EditText edFirstName;
    private EditText edMiddleName;
    private EditText edSurname;
    private EditText edRegNumber;
    private EditText edUserName;
    private EditText edPassword;
    private EditText edConfirmPassword;

    private RadioButton radFullTime;
    private RadioButton radPartTime;

    private String sFirstName;
    private String sMiddleName;
    private String sSurname;
    private String sRegNumber;
    private String sModeOfStudy;
    private String sUserName;
    private String sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initializeVariablesAndUIObjects();
    }


    /**
     * Method to initialize Variables,
     * and Objects used in UI.
     *
     * Called in this.onCreate();
     */
    private void initializeVariablesAndUIObjects() {

        clsDialogs = new CR_Dialogs(SignUpActivity.this);

        edFirstName = (EditText) this.findViewById(R.id.edSUFirstName);
        edMiddleName = (EditText) this.findViewById(R.id.edSUMiddleName);
        edSurname = (EditText) this.findViewById(R.id.edSUSurname);
        edRegNumber = (EditText) this.findViewById(R.id.edSURegNumber);
        edUserName = (EditText) this.findViewById(R.id.edSUUserName);
        edPassword = (EditText) this.findViewById(R.id.edSUPassword);
        edConfirmPassword = (EditText) this.findViewById(R.id.edSUConfirmPassword);

        radFullTime = (RadioButton) this.findViewById(R.id.radSUFullTime);
        radPartTime = (RadioButton) this.findViewById(R.id.radSUPartTime);

        Button btnSignUp = (Button) this.findViewById(R.id.btnSUSignUp);
        btnSignUp.setOnClickListener(clkBtnSignUp);

    }

    /**
     * Method to Check info that User provided,
     * and validate it.
     *
     * Called in this.clkBtnSignUp.onClick;
     */
    private void codeToValidateUserData() {

        if (edFirstName.getText().toString().equalsIgnoreCase("")) {
            clsDialogs.codeToGenerateAlertDialogs("Sign Up", getString(R.string.dgEmptyField) + " First Name!");

            edFirstName.requestFocus();
        } else if (edMiddleName.getText().toString().equalsIgnoreCase("")) {
            clsDialogs.codeToGenerateAlertDialogs("Sign Up", getString(R.string.dgEmptyField) + " Middle Name!");

            edMiddleName.requestFocus();
        } else if (edSurname.getText().toString().equalsIgnoreCase("")) {
            clsDialogs.codeToGenerateAlertDialogs("Sign Up", getString(R.string.dgEmptyField) + " Surname!");

            edSurname.requestFocus();
        } else if (edRegNumber.getText().toString().equalsIgnoreCase("")) {
            clsDialogs.codeToGenerateAlertDialogs("Sign Up", getString(R.string.dgEmptyField) + " Registration Number!");

            edRegNumber.requestFocus();
        } else if (edUserName.getText().toString().equalsIgnoreCase("")) {
            clsDialogs.codeToGenerateAlertDialogs("Sign Up", getString(R.string.dgEmptyField) + " Username!");

            edUserName.requestFocus();
        } else if (edPassword.getText().toString().equalsIgnoreCase("")) {
            clsDialogs.codeToGenerateAlertDialogs("Sign Up", getString(R.string.dgEmptyField) + " Password!");

            edPassword.requestFocus();
        } else if (edConfirmPassword.getText().toString().equalsIgnoreCase("")) {
            clsDialogs.codeToGenerateAlertDialogs("Sign Up", getString(R.string.dgEmptyField) + " Password Confirmation!");

            edConfirmPassword.requestFocus();
        } else if (!radFullTime.isChecked() && !radPartTime.isChecked()) {
            clsDialogs.codeToGenerateAlertDialogs("Sign Up", getString(R.string.dgEmptyField) + " Mode of Study!");
        } else {

            sFirstName = edFirstName.getText().toString();
            sMiddleName = edMiddleName.getText().toString();
            sSurname = edSurname.getText().toString();
            sRegNumber = edRegNumber.getText().toString();
            sUserName = edUserName.getText().toString();
            sPassword = edPassword.getText().toString();
            String sConfirmPassword = edConfirmPassword.getText().toString();

            if (radFullTime.isChecked()) {
                sModeOfStudy = this.getResources().getString(R.string.sFullTime);
            } else if (radPartTime.isChecked()) {
                sModeOfStudy = this.getResources().getString(R.string.sPartTime);
            }

            if (sPassword.equals(sConfirmPassword)) {
                codeToSaveUserData();
            } else {
                clsDialogs.codeToGenerateAlertDialogs("Sign Up", getString(R.string.dgPasswordMismatch));

                edPassword.setText("");
                edConfirmPassword.setText("");
                edPassword.requestFocus();
            }
        }
    }

    /**
     * Method to start Home Activity.
     *
     * Called in codeToSaveUserData();
     */
    private void codeToStartHomeActivity() {

        Intent inStartHomeActivity = new Intent(SignUpActivity.this, HomeActivity.class);
        this.finish();
        startActivity(inStartHomeActivity);
    }

    /**
     * This is where all the validated User Data,
     * is Saved.
     *
     * Called in this.codeToValidateUserData();
     */
    private void codeToSaveUserData() {

        String sEXECUTION_TAG = this.getResources().getString(R.string.sTagSignUp);

        BackgroundAsyncWorker clsBackgroundWorker = new BackgroundAsyncWorker(SignUpActivity.this);
        clsBackgroundWorker.execute(sEXECUTION_TAG, sFirstName, sMiddleName, sSurname, sRegNumber, sModeOfStudy, sUserName, sPassword);

        codeToStartHomeActivity();
    }


    private Button.OnClickListener clkBtnSignUp = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

            int iViewId = v.getId();
            switch (iViewId) {
                case R.id.btnSUSignUp:
                    codeToValidateUserData();
                    break;
            }
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
