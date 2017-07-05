package uk.co.hotmail.edwardquixote.courseregapp.Dialogs;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by Edward Quixote on 25/03/2016.
 */
public class CR_Dialogs {

    private Context coxContext;

    public CR_Dialogs(Context context) {
        this.coxContext = context;
    }

    public void codeToGenerateAlertDialogs(String dialogTitle, String dialogMessage) {

        AlertDialog.Builder adgbldBuilder = new AlertDialog.Builder(coxContext);
        adgbldBuilder.setTitle(dialogTitle);
        adgbldBuilder.setMessage(dialogMessage);
        adgbldBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        adgbldBuilder.setPositiveButton("Ok", null);

        AlertDialog adgDialog = adgbldBuilder.create();
        adgDialog.show();
    }
}
