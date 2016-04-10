package ahto.com.kalkulaatorbroadcast;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aelken on 03/04/16.
 */
public class OperationRepo extends Repo<Operation> {

    public OperationRepo(SQLiteDatabase database, String tableName, String[] allColumns){
        super(database, tableName, allColumns);
    };

    @Override
    public Operation cursorToEntity(Cursor cursor) {
        Operation operation = new Operation();
        // ID
        operation.setId(cursor.getLong(0));
        // Operaion ID
        operation.setOperationId(cursor.getLong(1));
        // Number1
        operation.setNumber1(cursor.getString(2));
        // Number2
        operation.setNumber2(cursor.getString(3));
        // Solution
        operation.setSolution(cursor.getString(4));
        // Timestamp
        operation.setTimeStamp(cursor.getString(5));

        return operation;
    }

    @Override
    public ContentValues entityToContentValues(Operation operation) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(com.akaver.sqllitecomplex01.MySQLiteHelper.OPERATION_COLUMN_NUM_1, operation.getNumber1());
        contentValues.put(com.akaver.sqllitecomplex01.MySQLiteHelper.OPERATION_COLUMN_NUM_2, operation.getNumber2());
        contentValues.put(com.akaver.sqllitecomplex01.MySQLiteHelper.OPERATION_COLUMN_RES, operation.getSolution());
        contentValues.put(com.akaver.sqllitecomplex01.MySQLiteHelper.OPERATION_COLUMN_TIMESTAMP, operation.getTimeStamp());

        return contentValues;
    }

    public List<Operation> getForOperation(long operationId){
        List<Operation> listOfEntity = new ArrayList<Operation>();

        Cursor cursor = getDatabase().query(getTableName(),
                getAllColumns(), "operationId = " + operationId, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Operation entity = cursorToEntity(cursor);
            listOfEntity.add(entity);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return listOfEntity;
    }


}
