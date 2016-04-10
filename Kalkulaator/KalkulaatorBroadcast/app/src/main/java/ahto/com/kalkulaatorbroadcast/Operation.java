package ahto.com.kalkulaatorbroadcast;

import ahto.com.kalkulaatorbroadcast.IEntity;
import java.lang.Override;
import java.lang.String; /**
 * Created by akaver on 03/04/16.
 */
public class Operation implements IEntity {

    private long id;
    private long operationId;
    private String number1;
    private String number2;
    private String solution;
    private String timeStamp;

    public Operation() { }

    public Operation(String number1, String number2, String solution) {
        setNumber1(number1);
        setNumber2(number2);
        setSolution(solution);
    }

    public Operation(long operationId, String number1, String number2, String solution) {
        setOperationId(operationId);
        setNumber1(number1);
        setNumber2(number2);
        setSolution(solution);
    }

    // id get/set
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    // operation id get/set
    public long getOperationId() {
        return operationId;
    }
    public void setOperationId(long id) {
        this.operationId = id;
    }

    // number1 get/set
    public String getNumber1() {
        return number1;
    }
    public void setNumber1(String number1) {
        this.number1 = number1.trim();
    }

    // number2 get/set
    public String getNumber2() {
        return number2;
    }
    public void setNumber2(String number2) {
        this.number2 = number2.trim();
    }

    // solution get/set
    public String getSolution() {
        return solution;
    }
    public void setSolution(String solution) {
        this.solution = solution.trim();
    }

    // timestamp get/set
    public String getTimeStamp() { return timeStamp; }
    public void setTimeStamp(String timestamp) { this.timeStamp = timestamp.trim(); }

    public String getOperation() {
        return number1 + " " + number2 + " " + solution;
    }

    @Override
    public String toString() {
        return number1 + " " + number2 + " " + solution;
    }
}
