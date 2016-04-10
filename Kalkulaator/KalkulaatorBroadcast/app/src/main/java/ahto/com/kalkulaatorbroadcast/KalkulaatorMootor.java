package ahto.com.kalkulaatorbroadcast;

import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Ahto Elken on 3/19/2016.
 */
public class KalkulaatorMootor {

    private static final String TAG = "KalkulaatorMootor";
    private static double calcResult;

    private static String currentNumber = "";
    private static boolean lastInserted;
    private static boolean showingResult = false;
    private static String displayText = "";
    private static String displayTextSave = "";
    private static ArrayList<String> inputArray = new ArrayList<>();

    public void setDisplayTextSave(String stateDisplayTextSave) {
        displayTextSave = stateDisplayTextSave;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        KalkulaatorMootor.displayText = displayText;
    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    /*public KalkulaatorMootor() {
    }*/

    public void checkBtn(String btnID) {
        if (showingResult) {
            showingResult = false;
            clear();
        }
        if (btnID.contains("nr")) {
            if (btnID.contains("0")) {
                if (!Objects.equals(currentNumber, "")) {
                    addNumber(btnID);
                }
            } else {
                addNumber(btnID);
            }
        } else if (btnID.contains("id/point")) {

            if (Objects.equals(currentNumber, "")) {
                addPoint("0.");
            } else if (currentNumber.contains(".")) {
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, btnID + " pressed but no new point was added");
                }
            } else {
                addPoint(".");
            }

        } else if (btnID.contains("id/ans")) {
            if (!Objects.equals(currentNumber, ""))
                inputArray.add(currentNumber);
            currentNumber = "";
            if (BuildConfig.DEBUG) {
                Log.d(TAG, inputArray.toString() + " -- array and current is : " + currentNumber);
            }
            calculateResult();
            showingResult = true;
        } else if (btnID.contains("id/C")) {
            clear();
        } else if (!Objects.equals(currentNumber, "")) {

            int len = currentNumber.length() - 1;
            if (currentNumber.substring(len).equals(".")) {
                currentNumber = currentNumber.replaceAll("[.]", "");
            } // Remove point from number, like 12.

            if (btnID.contains("id/subtract")) {
                inputArray.add(currentNumber);
                inputArray.add("-");
                currentNumber = "";

            } else if (btnID.contains("id/plus")) {
                inputArray.add(currentNumber);
                inputArray.add("+");
                currentNumber = "";

            } else if (btnID.contains("id/multiply")) {
                inputArray.add(currentNumber);
                inputArray.add("*");
                currentNumber = "";

            } else if (btnID.contains("id/divide")) {
                inputArray.add(currentNumber);
                inputArray.add("/");
                currentNumber = "";
            }
            lastInserted = true;
        } else {
            if (lastInserted) {
                int len = inputArray.size() - 1;
                if (btnID.contains("id/subtract")) {
                    inputArray.set(len, inputArray.get(len).replaceAll("[-+/*]", "-"));
                } else if (btnID.contains("id/plus")) {
                    inputArray.set(len, inputArray.get(len).replaceAll("[-+/*]", "+"));
                } else if (btnID.contains("id/multiply")) {
                    inputArray.set(len, inputArray.get(len).replaceAll("[-+/*]", "*"));
                } else if (btnID.contains("id/divide")) {
                    inputArray.set(len, inputArray.get(len).replaceAll("[-+/*]", "/"));
                } else if (btnID.contains("id/ans")) {
                    inputArray.remove(len);
                    calculateResult();
                }
            }
        }
        setDisplayT();
    }

    public void setDisplayT() {
        setDisplayText("");
        if (inputArray != null) {
            for (int i = 0; i < inputArray.size(); i++) {
                displayText = displayText.concat(inputArray.get(i));
            }
        }
    }

    private void addPoint(String toAdd) {
        currentNumber = currentNumber.concat(toAdd);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Point added");
        }
    }

    public static void addNumber(String btnID) {
        currentNumber = currentNumber.concat(btnID.substring(btnID.length() - 1));
        if (BuildConfig.DEBUG) {
            Log.d(TAG, btnID + "added");
        }
    }


    public void calculateResult() {
        double midResult = 0;
        double x = 0;
        double y = 0;
        //this.inputArray.add(currentNumber);

        while (inputArray.size() > 1) {

            x = Double.parseDouble(inputArray.get(0));
            y = Double.parseDouble(inputArray.get(2));

            if (inputArray.get(1).equals("-")) {
                midResult = x - y;
            } else if (inputArray.get(1).equals("+")) {
                midResult = x + y;
            } else if (inputArray.get(1).equals("/")) {
                midResult = x / y;
            } else if (inputArray.get(1).equals("*")) {
                midResult = x * y;
            }
            int j = 0;
            while (j <= 2) {
                inputArray.remove(0);
                j++;
            }
            inputArray.add(0, Objects.toString(midResult));
        }
        calcResult = midResult;
    }

    public void clear() {
        inputArray.clear();
        currentNumber = "";
        calcResult = -1;
        lastInserted = false;
    }

}
