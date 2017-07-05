package uk.co.hotmail.edwardquixote.courseregapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CoursesActivity extends AppCompatActivity {

    private TextView txtCourseInfo;

    private String sCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        txtCourseInfo = (TextView) this.findViewById(R.id.txtCourseInfo);

        Button btnRegister = (Button) this.findViewById(R.id.btnCourseRegister);
        btnRegister.setOnClickListener(clkBtnRegister);

        codeToGetIntentExtraData(this.getIntent());

    }

    private void codeToGetIntentExtraData(Intent intent) {

        sCourse = intent.getStringExtra(this.getResources().getString(R.string.inCOURSE));
        if (sCourse.equals(this.getResources().getString(R.string.inFOCIM_BSCIT))) {   //  BSc.IT
            txtCourseInfo.setText(this.getResources().getString(R.string.txtBSCITInfo));
        } else if (sCourse.equals(this.getResources().getString(R.string.inFOCIM_BBIT))) {   //  BB.IT
            txtCourseInfo.setText(this.getResources().getString(R.string.txtBBITInfo));
        } else if (sCourse.equals(this.getResources().getString(R.string.inFOCIM_BSCICT))) {   //  BSc.ICT
            txtCourseInfo.setText(this.getResources().getString(R.string.txtBSCICTInfo));
        } else if (sCourse.equals(this.getResources().getString(R.string.inFOCIM_BSCCS))) {   //  BSc.CS
            txtCourseInfo.setText(this.getResources().getString(R.string.txtBSCCSInfo));
        } else if (sCourse.equals(this.getResources().getString(R.string.inSPP_CPA))) { //  CPA
            txtCourseInfo.setText(this.getResources().getString(R.string.txtCPAInfo));
        } else if (sCourse.equals(this.getResources().getString(R.string.inSPP_CPA))) { //  ACCA
            txtCourseInfo.setText(this.getResources().getString(R.string.txtACCAInfo));
        } else if (sCourse.equals(this.getResources().getString(R.string.inSPP_CPA))) { //  Computer Packages
            txtCourseInfo.setText(this.getResources().getString(R.string.txtComputerPackagesInfo));
        } else if (sCourse.equals(this.getResources().getString(R.string.inSPP_CPA))) { //  Computer Programming
            txtCourseInfo.setText(this.getResources().getString(R.string.txtComputerProgrammingInfo));
        } else if (sCourse.equals(this.getResources().getString(R.string.inSPP_CPA))) { //  CCNA
            txtCourseInfo.setText(this.getResources().getString(R.string.txtCCNAInfo));
        } else if (sCourse.equals(this.getResources().getString(R.string.inSPP_CPA))) { //  CISCO
            txtCourseInfo.setText(this.getResources().getString(R.string.txtCISCOInfo));
        }
    }

    private Button.OnClickListener clkBtnRegister = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent inStartRegister = new Intent(CoursesActivity.this, CourseRegisterActivity.class);
            inStartRegister.putExtra(getResources().getString(R.string.inCOURSE), sCourse);
            startActivity(inStartRegister);
        }
    };
}
