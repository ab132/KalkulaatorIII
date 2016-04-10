package ahto.com.kalkulaatorbroadcast;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by aelken on 03/04/16.
 */
// Step over cursor (SQLite result set for example), create subview for every item
public class  OperationTypeAdapter extends CursorAdapter{

    private final LayoutInflater layoutInflater;
    private UOW uow;
    private ViewGroup parentViewGroup;

    public OperationTypeAdapter(Context context, Cursor c, UOW uow) {
        super(context, c, 0);
        layoutInflater = LayoutInflater.from(context);
        this.uow = uow;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View view=layoutInflater.inflate(R.layout.contact_person,parent,false);
        parentViewGroup = parent;
        return view;
    }


    // this can be called several times by the system!!!
    // first pass - initial draw, get measurements
    // second pass - final draw
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        //TextView textViewName =(TextView) view.findViewById(R.id.name);

        Operation operation = uow.operationRepo.cursorToEntity(cursor);
        // textViewName.setText(operation.getOperation());
        displayOperationTypesView(view, context, operation);
    }


   private void displayOperationTypesView(View view, Context context, Operation operation) {
        // get the operationTypeList
        LinearLayout operationTypeList = (LinearLayout) view.findViewById(R.id.operationTypeList);

        // if this gets called multiple times, first clean all up
        // otherwise you will add same childs several times
        operationTypeList.removeAllViews();

        for (OperationType operationType :
            //uow.statisticsRepo.getForPerson(operation.getId())) {
                uow.operationTypeRepo.getForOperation(operation.getId())) {
            //uow.operationTypeRepo.getAllStatistics()) {

            // load the xml structure of your row
            View child = layoutInflater.inflate(R.layout.operation_type,parentViewGroup,false); //mall (operation_types.xml)

            TextView textViewoperationName =(TextView) child.findViewById(R.id.operationName);
            TextView textViewLifetimeCounter =(TextView) child.findViewById(R.id.lifetimeCounter);

            textViewoperationName.setText(operationType.getOperation());
            textViewLifetimeCounter.setText(operationType.getLifetimeCounter());

            operationTypeList.addView(child);
        }


    }

    /*private void displayOperationTypesView(View view, Context context, Operation operation) {
        // get the operationTypeList
        LinearLayout operationTypeList = (LinearLayout) view.findViewById(R.id.operationTypeList); //operationTypeListView?

        // if this gets called multiple times, first clean all up
        // otherwise you will add same childs several times
        operationTypeList.removeAllViews();

        for (OperationType operationType :
            //uow.statisticsRepo.getForPerson(operation.getId())) {
                uow.operationTypeRepo.getForOperation(operation.getId())) {
            //uow.operationTypeRepo.getAllStatistics()) {

            // load the xml structure of your row
            View child = layoutInflater.inflate(R.layout.operation_type,parentViewGroup,false); //mall (operation_types.xml)

            TextView textViewContactValue =(TextView) child.findViewById(R.id.operationName);
            TextView textViewContactType =(TextView) child.findViewById(R.id.lifetimeCounter);

            textViewContactValue.setText(operationType.getOperation());
            textViewContactType.setText(operationType.getLifetimeCounter());

            operationTypeList.addView(child);
        }


    }*/
}
