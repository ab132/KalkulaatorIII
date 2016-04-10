package ahto.com.kalkulaatorbroadcast;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aelken on 03/04/16.
 */
public class OperationTypeRepo extends Repo<OperationType> {

    public OperationTypeRepo(SQLiteDatabase database, String tableName, String[] allColumns) {
        super(database, tableName, allColumns);
    }

    @Override
    public OperationType cursorToEntity(Cursor cursor) {
        OperationType operationType = new OperationType();
        // ID
        operationType.setId(cursor.getLong(0));
        // Operation
        operationType.setOperation(cursor.getString(1));
        // Lifetime counter
        operationType.setLifetimeCounter(cursor.getInt(2));

        return operationType;
    }

    @Override
    public ContentValues entityToContentValues(OperationType operationType) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(com.akaver.sqllitecomplex01.MySQLiteHelper.OPERATION_TYPE_COLUMN_OPERATION, operationType.getOperation());
        contentValues.put(com.akaver.sqllitecomplex01.MySQLiteHelper.OPERATION_TYPE_COLUMN_LIFETIME_COUNTER, operationType.getLifetimeCounter());

        return contentValues;
    }

    public List<OperationType> getForOperation(long operationId){
        List<OperationType> listOfEntity = new ArrayList<OperationType>();

        Cursor cursor = getDatabase().query(getTableName(),
                getAllColumns(), "operationId = " + operationId, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            OperationType entity = cursorToEntity(cursor);
            listOfEntity.add(entity);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return listOfEntity;
    }



    //public

}
