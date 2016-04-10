package ahto.com.kalkulaatorbroadcast;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by akaver on 03/04/16.
 */
public class UOW {

    // Database fields
    private SQLiteDatabase database;
    private com.akaver.sqllitecomplex01.MySQLiteHelper dbHelper;

    private final Context context;

    public OperationRepo operationRepo;
    public OperationTypeRepo operationTypeRepo;
    public StatisticsRepo statisticsRepo;

    public UOW(Context context){
        this.context = context;

        dbHelper = new com.akaver.sqllitecomplex01.MySQLiteHelper(context);
        database = dbHelper.getWritableDatabase();

        operationRepo = new OperationRepo(database, dbHelper.TABLE_OPERATION, dbHelper.OPERATION_ALL_COLUMNS);
        operationTypeRepo = new OperationTypeRepo(database, dbHelper.TABLE_OPERATION_TYPE, dbHelper.OPERATION_TYPE_ALL_COLUMNS);
        statisticsRepo = new StatisticsRepo(database, dbHelper.TABLE_STATISTICS, dbHelper.STATISTICS_ALL_COLUMNS);
    }

    public void DropCreateDatabase(){
        dbHelper.dropCreateDatabase(database);
    }

    public void SeedData(){
        //OperationType operationTypeSkype = operationTypeRepo.add(new OperationType("Skype"));
        //OperationType operationTypeEmail = operationTypeRepo.add(new OperationType("Email"));

        OperationType mul = operationTypeRepo.add(new OperationType("*"));
        OperationType div = operationTypeRepo.add(new OperationType("/"));
        OperationType sum = operationTypeRepo.add(new OperationType("+"));
        OperationType sub = operationTypeRepo.add(new OperationType("-"));

        Operation operation1 = operationRepo.add(new Operation(1, "5", "5", "25"));
        Operation operation2 = operationRepo.add(new Operation(2, "27", "3", "9"));
        Operation operation3 = operationRepo.add(new Operation(3, "47", "3", "50"));
        Operation operation4 = operationRepo.add(new Operation(4, "6", "6", "0"));

        //Operation operation1 = operationRepo.add(new Operation("Andres", "Käver"));
        //Operation operation2 = operationRepo.add(new Operation("Mait", "Poska"));

        statisticsRepo.add(new Statistics(1, "01/01/2016", 1));
        statisticsRepo.add(new Statistics(2, "01/01/2016", 1));
        statisticsRepo.add(new Statistics(3, "01/01/2016", 1));
        statisticsRepo.add(new Statistics(4, "01/01/2016", 1));

        //statisticsRepo.add(new Statistics("akaver", operation1.getId(), operationTypeSkype.getId()));
        //statisticsRepo.add(new Statistics("akaver@itcollege.ee", operation1.getId(), operationTypeEmail.getId()));
        //statisticsRepo.add(new Statistics("minamait", operation2.getId(), operationTypeSkype.getId()));
        //statisticsRepo.add(new Statistics("mait.poska@itcollege.ee", operation2.getId(), operationTypeEmail.getId()));

    }

}
