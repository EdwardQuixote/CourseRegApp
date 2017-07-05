package uk.co.hotmail.edwardquixote.courseregapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnFOCIM = (Button) this.findViewById(R.id.btnFOCIM);
        Button btnSPP = (Button) this.findViewById(R.id.btnSPP);
        Button btnFOED = (Button) this.findViewById(R.id.btnFOED);
        Button btnSOB = (Button) this.findViewById(R.id.btnSOB);
        btnFOCIM.setOnClickListener(clkBtn);
        btnSPP.setOnClickListener(clkBtn);
        btnFOED.setOnClickListener(clkBtn);
        btnSOB.setOnClickListener(clkBtn);
    }

    private void codeToStartFOCIMDepartmentActivity() {
        Intent inStartFOCIM = new Intent(HomeActivity.this, FOCIMActivity.class);
        startActivity(inStartFOCIM);
    }

    private void codeToStartSPPDepartmentActivity() {
        Intent inStartSPP = new Intent(HomeActivity.this, SPPActivity.class);
        startActivity(inStartSPP);
    }

    private void codeToStartFOEDDepartmentActivity() {
        Intent inStartFOED = new Intent(HomeActivity.this, FOEDActivity.class);
        startActivity(inStartFOED);
    }

    private void codeToStartSOBDepartmentActivity() {
        Intent inStartSOB = new Intent(HomeActivity.this, SOBActivity.class);
        startActivity(inStartSOB);
    }


    private Button.OnClickListener clkBtn = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

            int iViewID = v.getId();
            switch (iViewID) {
                case R.id.btnFOCIM:
                    codeToStartFOCIMDepartmentActivity();
                    break;

                case R.id.btnSPP:
                    codeToStartSPPDepartmentActivity();
                    break;

                case R.id.btnFOED:
                    codeToStartFOEDDepartmentActivity();
                    break;

                case R.id.btnSOB:
                    codeToStartSOBDepartmentActivity();
                    break;
            }
        }
    };
}
