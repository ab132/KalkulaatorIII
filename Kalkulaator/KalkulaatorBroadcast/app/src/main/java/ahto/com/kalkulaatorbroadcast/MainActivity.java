package ahto.com.kalkulaatorbroadcast;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private UOW uow;
    private OperationAdapter operationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        uow = new UOW(getApplicationContext());

        // start with fresh database
        uow.DropCreateDatabase();
        uow.SeedData();


        displayListView();
    }


    private void displayListView() {
        operationAdapter = new OperationAdapter(this, uow.operationRepo.getCursorAll(), uow);

        ListView listView = (ListView) findViewById(R.id.personsListView);

        // Assign adapter to ListView
        // listview will iterate over adapter, and get filled subview for every row
        listView.setAdapter(operationAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // Get the id
                String dbid =
                        cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                Toast.makeText(getApplicationContext(),
                        dbid, Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.clear_all) {
            AlertDialog alert = alertDialog();
            alert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private AlertDialog alertDialog()
    {
        AlertDialog alert = new AlertDialog.Builder(this)
                // Dialog title
                .setTitle("CalculatorII")
                        // Dialog message
                .setMessage("Clear the database?")
                        // Positive button
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Delete database
                        uow.DropCreateDatabase();
                        // Draw list
                        displayListView();
                        // Debug
                        Log.d("MainActivity", "Erasing database");
                        // Close dialog
                        dialog.dismiss();
                    }
                })
                        // Negative button
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Debug
                        Log.d("MainActivity", "Erase database cancelled");
                        // Close dialog
                        dialog.dismiss();
                    }
                })
                .create();

        return alert;

    }
}
