package ahto.com.kalkulaatorbroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Ahto Elken on 3/19/2016.
 */
public class KalkulaatorReceiver extends BroadcastReceiver {
    private static final String TAG = "CalculatorReceiver";
    private static String screenText = "";
    private static String currentNumber = "";
    private KalkulaatorMootor solver = new KalkulaatorMootor();

    public static void setCurrentNumber(String currentNumber) {
        KalkulaatorReceiver.currentNumber = currentNumber;
    }

    public void setShow(String show) {
        KalkulaatorReceiver.screenText = show;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isOrderedBroadcast()) {
            Bundle extras = intent.getExtras();
            String symbol;
            if (extras != null) {
                symbol = extras.getString("insertedButton");
                solver.checkBtn(symbol);
            }
            setShow(solver.getDisplayText());
            setCurrentNumber(solver.getCurrentNumber());
            setResultData(screenText + currentNumber);
            setResultCode(Activity.RESULT_OK);
        }
    }

}


