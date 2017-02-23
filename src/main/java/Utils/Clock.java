/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


/**
 *
 * @author C. Levallois
 */
public class Clock {

    private long start;
    private String action;
    private StringBuilder logText;
    private StringBuilder intermediateText;
    private final String newLine = "\n";
    private final String interval = "-------------------------------\n\n";

    public Clock(String action) {

        this.action = action;
        intermediateText = new StringBuilder();
        logText = new StringBuilder();
        startClock();
    }

    private void startClock() {

        start = System.currentTimeMillis();
        logText.append(action).append("...").append(newLine);

        System.out.print(logText.toString());
    }

    public void printIntermediaryText(String it) {
        intermediateText.append(it).append(newLine);
        System.out.println(intermediateText.toString());
        intermediateText = new StringBuilder();
    }

    public void printElapsedTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - start;

        if (elapsedTime
                < 1000) {
            System.out.println("still " + action.toLowerCase() + ", elapsed time: " + elapsedTime + " milliseconds]");

        } else {
            System.out.println("still " + action.toLowerCase() + ", elapsed time: " + elapsedTime / 1000 + " seconds]");
        }

    }

    public void closeAndPrintClock() {

        long currentTime = System.currentTimeMillis();
        long totalTime = currentTime - start;
        logText = new StringBuilder();
        logText.append(intermediateText.toString());
        logText.append("finished [took: ");


        if (totalTime < 10000) {
            logText.append(Math.round(totalTime / 1000))
                    .append(" seconds, ")
                    .append(Math.round(totalTime % 1000))
                    .append(" milliseconds]")
                    .append(newLine + interval);
        } else if (totalTime < 60000) {
            logText.append(totalTime / 1000).append(" seconds]").append(newLine + interval);
        } else {
            logText.append(totalTime / 60000).append(" minutes ").append(Math.round((totalTime % 60000) / 1000)).append(" seconds").append(newLine + interval);
        }

        System.out.println(logText.toString());

    }
}
