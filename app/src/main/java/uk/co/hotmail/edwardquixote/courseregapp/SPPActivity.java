package uk.co.hotmail.edwardquixote.courseregapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SPPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spp);

        ListView lstSPP = (ListView) this.findViewById(R.id.lstSPPCourses);
        lstSPP.setOnItemClickListener(clkLst);
    }

    private ListView.OnItemClickListener clkLst = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent inStartCourse = new Intent(SPPActivity.this, CoursesActivity.class);
            switch (position) {
                case 0: //  CPA
                    inStartCourse.putExtra(
                            getResources().getString(R.string.inCOURSE),
                            getResources().getString(R.string.inSPP_CPA));
                    break;

                case 1: //  ACCA
                    inStartCourse.putExtra(
                            getResources().getString(R.string.inCOURSE),
                            getResources().getString(R.string.inSPP_ACCA));
                    break;

                case 2: //  Computer Packages
                    inStartCourse.putExtra(
                            getResources().getString(R.string.inCOURSE),
                            getResources().getString(R.string.inSPP_COMPUTERPACKAGES));
                    break;

                case 3: //  Computer Programming
                    inStartCourse.putExtra(
                            getResources().getString(R.string.inCOURSE),
                            getResources().getString(R.string.inSPP_COMPUTERPROGRAMMING));
                    break;

                case 4: //  CCNA
                    inStartCourse.putExtra(
                            getResources().getString(R.string.inCOURSE),
                            getResources().getString(R.string.inSPP_CCNA));
                    break;

                case 5: //  CISCO
                    inStartCourse.putExtra(
                            getResources().getString(R.string.inCOURSE),
                            getResources().getString(R.string.inSPP_CISCO));
                    break;
            }
            startActivity(inStartCourse);
        }
    };
}
