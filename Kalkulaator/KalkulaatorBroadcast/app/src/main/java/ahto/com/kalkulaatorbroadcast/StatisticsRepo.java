package ahto.com.kalkulaatorbroadcast;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aelken on 03/04/16.
 */
public class StatisticsRepo extends Repo<Statistics> {
    public StatisticsRepo(SQLiteDatabase database, String tableName, String[] allColumns) {
        super(database, tableName, allColumns);
    }

    @Override
    public Statistics cursorToEntity(Cursor cursor) {
        Statistics statistics = new Statistics();
        // ID
        statistics.setId(cursor.getLong(0));
        // Operand ID
        statistics.setOperationId(cursor.getLong(1));
        // Daystamp
        statistics.setDaystamp(cursor.getString(2));
        // Counter
        statistics.setDayCounter(cursor.getInt(3));

        return statistics;
    }

    @Override
    public ContentValues entityToContentValues(Statistics statistics) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(com.akaver.sqllitecomplex01.MySQLiteHelper.STATISTICS_COLUMN_OPERAND_ID, statistics.getOperationId());
        contentValues.put(com.akaver.sqllitecomplex01.MySQLiteHelper.STATISTICS_COLUMN_DAYSTAMP, statistics.getDaystamp());
        contentValues.put(com.akaver.sqllitecomplex01.MySQLiteHelper.STATISTICS_COLUMN_DAYCOUNTER, statistics.getDayCounter());

        return contentValues;
    }

    /*
    public List<Statistics> getForPerson(long statisticsId){
        List<Statistics> listOfEntity = new ArrayList<Statistics>();

        Cursor cursor = getDatabase().query(getTableName(),
                getAllColumns(), "statisticsId = " + statisticsId, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Statistics entity = cursorToEntity(cursor);
            listOfEntity.add(entity);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return listOfEntity;
    }*/

    public List<Statistics> getAllStatistics(){
        List<Statistics> listOfEntity = new ArrayList<Statistics>();

        Cursor cursor = getDatabase().query(getTableName(),
                getAllColumns(), "statisticsId = " + 0, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Statistics entity = cursorToEntity(cursor);
            listOfEntity.add(entity);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return listOfEntity;
    }
}
