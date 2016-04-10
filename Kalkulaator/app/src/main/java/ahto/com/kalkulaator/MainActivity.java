package ahto.com.kalkulaator;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static TextView calcText;
    private static String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcText = (TextView) findViewById(R.id.textViewCalc);
        calcText.setText(text);
    }

    public void buttonClicked(View view) {
        broadcastIntent(view);

    }

    public void broadcastIntent(View view) {
        Button btn = (Button) view;
        String btnIDstring = btn.getResources().getResourceName(btn.getId());
        Intent intent = new Intent();
        intent.setAction("com.ee.calculatorRequest");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.putExtra("insertedButton", btnIDstring);

        sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String calcResult = getResultData();
                calcText.setText(calcResult);
                text = calcResult;
            }
        }, null, Activity.RESULT_OK, null, null);
    }

}
