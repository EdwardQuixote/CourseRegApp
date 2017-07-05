package uk.co.hotmail.edwardquixote.courseregapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import uk.co.hotmail.edwardquixote.courseregapp.Dialogs.CR_Dialogs;
import uk.co.hotmail.edwardquixote.courseregapp.Tasks_Threads_Handlers.BackgroundAsyncWorker;

public class CourseRegisterActivity extends AppCompatActivity {

    private CR_Dialogs clsDialogs;

    private ScrollView scvRegisterCourse;

    private TextView txtCourseTitle;

    private EditText edStudentID;
    private EditText edFullName;

    private RadioButton radFullTime;
    private RadioButton radPartTime;

    private String sIntentExtra;
    private String sStudentID;
    private String sFullName;
    private String sModeOfStudy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_register);

        initializeVariablesAndUIObjects();

        codeToGetIntentExtraData(this.getIntent());
    }

    /**
     * Method to initialize variablesa and objects used in UI.
     *
     * Called in this.onCreate();
     */
    private void initializeVariablesAndUIObjects() {

        clsDialogs = new CR_Dialogs(CourseRegisterActivity.this);

        scvRegisterCourse = (ScrollView) this.findViewById(R.id.scvRegisterCourse);

        txtCourseTitle = (TextView) this.findViewById(R.id.txtRegisterCourseName);

        edStudentID = (EditText) this.findViewById(R.id.edRegisterStudentID);
        edFullName = (EditText) this.findViewById(R.id.edRegisterStudentName);

        radFullTime = (RadioButton) this.findViewById(R.id.radRegisterFullTime);
        radPartTime = (RadioButton) this.findViewById(R.id.radRegisterPartTime);

        Button btnSubmit = (Button) this.findViewById(R.id.btnRegisterCourseSubmit);
        btnSubmit.setOnClickListener(clkBtnSubmit);

    }

    /**
     * Code to get Intent Extra,
     * to know which info is needed on the screen layout.
     *
     * Called in this.onCreate();
     *
     * @param intent (Intent)
     */
    private void codeToGetIntentExtraData(Intent intent) {

        sIntentExtra = intent.getStringExtra(this.getResources().getString(R.string.inCOURSE));
        if (sIntentExtra.equals(this.getResources().getString(R.string.inFOCIM_BSCIT))) {
            txtCourseTitle.setText(this.getResources().getString(R.string.sBSCIT));
        } else if (sIntentExtra.equals(this.getResources().getString(R.string.inFOCIM_BBIT))) {
            txtCourseTitle.setText(this.getResources().getString(R.string.sBBIT));
        } else if (sIntentExtra.equals(this.getResources().getString(R.string.inFOCIM_BSCICT))) {
            txtCourseTitle.setText(this.getResources().getString(R.string.sBSCICT));
        } else if (sIntentExtra.equals(this.getResources().getString(R.string.inFOCIM_BSCCS))) {
            txtCourseTitle.setText(this.getResources().getString(R.string.sBSCCS));
        } else {
            txtCourseTitle.setText("Course Name");
        }
    }

    /**
     * Code to validate the data user entered.
     *
     * Called in this.clkBtnSubmit.onClick();
     */
    private void codeToValidateUserData() {

        String sDialogMessage;

        if (edStudentID.getText().toString().equalsIgnoreCase("")) {
            sDialogMessage = this.getResources().getString(R.string.dgEmptyField) + " student ID Number.";
            clsDialogs.codeToGenerateAlertDialogs("Register Course", sDialogMessage);

            edStudentID.requestFocus();
        } else if (edFullName.getText().toString().equalsIgnoreCase("")) {
            sDialogMessage = this.getResources().getString(R.string.dgEmptyField) + " Full Name.";
            clsDialogs.codeToGenerateAlertDialogs("Register Course", sDialogMessage);

            edFullName.requestFocus();
        } else if (!radFullTime.isChecked() && !radPartTime.isChecked()) {
            sDialogMessage = this.getResources().getString(R.string.dgEmptyField) + " Mode of Study.";
            clsDialogs.codeToGenerateAlertDialogs("Register Course", sDialogMessage);
        } else {
            sStudentID = edStudentID.getText().toString();
            sFullName = edFullName.getText().toString();

            if (radFullTime.isChecked()) {
                sModeOfStudy = getResources().getString(R.string.sFullTime);
            } else if (radPartTime.isChecked()) {
                sModeOfStudy = getResources().getString(R.string.sPartTime);
            }

            codeToRegisterCourse();
        }

    }

    /**
     * Method to send course registration data to the database.
     *
     * Called in this.codeToValidateUserData();
     */
    private void codeToRegisterCourse() {

        String sEXECUTION_TAG = this.getResources().getString(R.string.sTagRegisterCourse);

        BackgroundAsyncWorker clsBackgroundAsyncWorker = new BackgroundAsyncWorker(CourseRegisterActivity.this);
        clsBackgroundAsyncWorker.execute(sEXECUTION_TAG, sStudentID, sFullName, sModeOfStudy);

        Snackbar.make(scvRegisterCourse, "Thanks for registering with KCA.", Snackbar.LENGTH_LONG).show();

        this.finish();
    }

    private Button.OnClickListener clkBtnSubmit = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

            codeToValidateUserData();
        }
    };
}
