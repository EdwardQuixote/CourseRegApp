package uk.co.hotmail.edwardquixote.courseregapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class FOCIMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focim);

        ListView lstFOCIM = (ListView) this.findViewById(R.id.lstFOCIMCourses);
        lstFOCIM.setOnItemClickListener(clkLst);
    }

    private ListView.OnItemClickListener clkLst = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent inStartCourse = new Intent(FOCIMActivity.this, CoursesActivity.class);
            switch (position) {
                case 0: //  BSc.IT
                    inStartCourse.putExtra(
                            getResources().getString(R.string.inCOURSE),
                            getResources().getString(R.string.inFOCIM_BSCIT));
                    break;

                case 1: //  BBIT
                    inStartCourse.putExtra(
                            getResources().getString(R.string.inCOURSE),
                            getResources().getString(R.string.inFOCIM_BBIT));
                    break;

                case 2: //  BSCICT
                    inStartCourse.putExtra(
                            getResources().getString(R.string.inCOURSE),
                            getResources().getString(R.string.inFOCIM_BSCICT));
                    break;

                case 3: //  BSCCS
                    inStartCourse.putExtra(
                            getResources().getString(R.string.inCOURSE),
                            getResources().getString(R.string.inFOCIM_BSCCS));
                    break;
            }
            startActivity(inStartCourse);
        }
    };
}
