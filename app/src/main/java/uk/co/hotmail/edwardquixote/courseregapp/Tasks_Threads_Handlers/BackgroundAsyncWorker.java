package uk.co.hotmail.edwardquixote.courseregapp.Tasks_Threads_Handlers;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import uk.co.hotmail.edwardquixote.courseregapp.R;

/**
 * Created by Edward Quixote on 25/03/2016.
 */
public class BackgroundAsyncWorker extends AsyncTask<String, Void, String> {

    private final static String sURL_SIGNUP = "http://10.0.2.2/CourseRegistration_App/sign_up.php";
    private final static String sURL_SIGNIN = "http://10.0.2.2/CourseRegistration_App/login_into_db.php";
    private final static String sURL_REGISTERCOURSE = "http://10.0.2.2/CourseRegistration_App/register_course.php";
    private final static String sENCODING_FORMAT = "UTF-8";
    private final static String sDECODING_FORMAT = "iso-8859-1";

    private Context coxContext;

    private AlertDialog adgDialog;

    public BackgroundAsyncWorker(Context context) {
        this.coxContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        adgDialog = new AlertDialog.Builder(coxContext).create();
        adgDialog.setTitle("Login . . . .");
    }

    @Override
    protected String doInBackground(String... params) {

        String sEXECUTE_METHOD = params[0];

        if (sEXECUTE_METHOD.equals(coxContext.getString(R.string.sTagSignUp))) {
            String sFirstName = params[1];
            String sMiddleName = params[2];
            String sSurname = params[3];
            String sRegNumber = params[4];
            String sModeOfStudy = params[5];
            String sUserName = params[6];
            String sPassword = params[7];

            try {
                URL urlSignUp = new URL(sURL_SIGNUP);
                HttpURLConnection hucSignUpConnection = (HttpURLConnection) urlSignUp.openConnection();
                hucSignUpConnection.setRequestMethod("POST");
                hucSignUpConnection.setDoOutput(true);

                OutputStream osOutputStream = hucSignUpConnection.getOutputStream();
                BufferedWriter bfwBufferedWriter = new BufferedWriter(new OutputStreamWriter(osOutputStream, "UTF-8"));
                String sDataToBePosted =
                        URLEncoder.encode("first_Name", sENCODING_FORMAT) + "=" + URLEncoder.encode(sFirstName, sENCODING_FORMAT) + " & " +
                                URLEncoder.encode("middle_Name", sENCODING_FORMAT) + "=" + URLEncoder.encode(sMiddleName, sENCODING_FORMAT) + " & " +
                                URLEncoder.encode("surname", sENCODING_FORMAT) + "=" + URLEncoder.encode(sSurname, sENCODING_FORMAT) + " & " +
                                URLEncoder.encode("reg_Number", sENCODING_FORMAT) + "=" + URLEncoder.encode(sRegNumber, sENCODING_FORMAT) + " & " +
                                URLEncoder.encode("mode_Of_Study", sENCODING_FORMAT) + "=" + URLEncoder.encode(sModeOfStudy, sENCODING_FORMAT) + " & " +
                                URLEncoder.encode("user_Name", sENCODING_FORMAT) + "=" + URLEncoder.encode(sUserName, sENCODING_FORMAT) + " & " +
                                URLEncoder.encode("user_Password", sENCODING_FORMAT) + "=" + URLEncoder.encode(sPassword, sENCODING_FORMAT);
                bfwBufferedWriter.write(sDataToBePosted);
                bfwBufferedWriter.flush();
                bfwBufferedWriter.close();
                osOutputStream.close();

                InputStream isServerResponse = hucSignUpConnection.getInputStream();
                isServerResponse.close();

                return "Registration SUCCESSFUL!";
            } catch (IOException ioex) {
                Log.e(BackgroundAsyncWorker.this.toString() + " IOEXCEPTION: ", ioex.toString());
            }
        } else if (sEXECUTE_METHOD.equalsIgnoreCase(coxContext.getString(R.string.sTagSignIn))) {

            String sUsername = params[1];
            String sPassword = params[2];

            try {
                URL urlSignIn = new URL(sURL_SIGNIN);

                HttpURLConnection hucSignIn = (HttpURLConnection) urlSignIn.openConnection();
                hucSignIn.setRequestMethod("POST");
                hucSignIn.setDoOutput(true);
                hucSignIn.setDoInput(true);

                OutputStream osSignIn = hucSignIn.getOutputStream();
                BufferedWriter bfwSignIn = new BufferedWriter(new OutputStreamWriter(osSignIn, sENCODING_FORMAT));
                String sSignInCredentials =
                        URLEncoder.encode("user_name", sENCODING_FORMAT) + "=" + URLEncoder.encode(sUsername, sENCODING_FORMAT) + " & " +
                                URLEncoder.encode("password", sENCODING_FORMAT) + "=" + URLEncoder.encode(sPassword, sENCODING_FORMAT);
                bfwSignIn.write(sSignInCredentials);
                bfwSignIn.flush();
                bfwSignIn.close();
                osSignIn.close();

                InputStream isSignIn = hucSignIn.getInputStream();
                BufferedReader bfrSignIn = new BufferedReader(new InputStreamReader(isSignIn, sDECODING_FORMAT));
                String sServerReponse = "";
                String sFileLine;
                while ((sFileLine = bfrSignIn.readLine()) != null) {
                    sServerReponse += sFileLine;
                }
                bfrSignIn.close();
                isSignIn.close();
                hucSignIn.disconnect();

                return sServerReponse;
            } catch (IOException ioex) {
                Log.e(BackgroundAsyncWorker.this.toString() + " IOEXCEPTION: ", ioex.toString());
            }
        } else if (sEXECUTE_METHOD.equals(coxContext.getString(R.string.sTagRegisterCourse))) {
            String sStudentID = params[1];
            String sFullName = params[2];
            String sModeOfStudy = params[3];

            try {
                URL urlRegisterCourse = new URL(sURL_REGISTERCOURSE);
                HttpURLConnection hucRegisterCourse = (HttpURLConnection) urlRegisterCourse.openConnection();
                hucRegisterCourse.setRequestMethod("POST");
                hucRegisterCourse.setDoOutput(true);

                OutputStream osRegisterCourse = hucRegisterCourse.getOutputStream();
                BufferedWriter bfwRegisterCourse = new BufferedWriter(new OutputStreamWriter(osRegisterCourse, sENCODING_FORMAT));
                String sRegisterCourseData =
                        URLEncoder.encode("student_ID", sENCODING_FORMAT) + "=" + URLEncoder.encode(sStudentID, sENCODING_FORMAT) + " & " +
                                URLEncoder.encode("student_fullname", sENCODING_FORMAT) + "=" + URLEncoder.encode(sFullName, sENCODING_FORMAT) + " & " +
                                URLEncoder.encode("mode_of_study", sENCODING_FORMAT) + "=" + URLEncoder.encode(sModeOfStudy, sENCODING_FORMAT);
                bfwRegisterCourse.write(sRegisterCourseData);
                bfwRegisterCourse.flush();
                bfwRegisterCourse.close();
                osRegisterCourse.close();

                InputStream isServerResponse = hucRegisterCourse.getInputStream();
                isServerResponse.close();

                return "Registration SUCCESSFUL!";
            } catch (IOException ioex) {
                Log.e(BackgroundAsyncWorker.this.toString() + " IOEXCEPTION: ", ioex.toString());
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (result.equals("Registration SUCCESSFUL!")) {    //  Case for Sign Up and Register Course
            Toast.makeText(coxContext, result, Toast.LENGTH_LONG).show();
        } else {    //  Case for Login response
//            adgDialog.setMessage(result);
//            adgDialog.show();

            Toast.makeText(coxContext, "SIGN IN: " + result, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
