package ahto.com.kalkulaatorbroadcast;

/**
 * Created by akaver on 03/04/16.
 */
public class Statistics implements IEntity {

    private long id;
    private long operationId;
    private String daystamp;
    private int dayCounter;

    public Statistics() { }

    public Statistics(int operationId, String daystamp, int dayCounter){
        setOperationId(operationId);
        setDaystamp(daystamp);
        setDayCounter(dayCounter);
    }

    // ID get/set
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    // Operand ID get/set
    public long getOperationId() {
        return this.operationId;
    }
    public void setOperationId(long operandId) { this.operationId = operationId; }

    // Daystamp get/set
    public String getDaystamp() {
        return this.daystamp;
    }
    public void setDaystamp(String daystamp) {
        this.daystamp = daystamp;
    }

    // Daycounter get/set
    public int getDayCounter() {
        return this.dayCounter;
    }
    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }

    @Override
    public String toString() {
        return daystamp + " " + operationId + " " + dayCounter;
    }
}
