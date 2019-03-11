/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

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
    public static boolean silent = false;

    public Clock(String action) {

        this.action = action;
        intermediateText = new StringBuilder();
        logText = new StringBuilder();
        if (!silent) {
            startClock(false);
        }
    }

    public Clock(String action, boolean startSilent) {

        this.action = action;
        intermediateText = new StringBuilder();
        logText = new StringBuilder();
        if (!silent) {
            startClock(startSilent);
        }
    }

    private void startClock(boolean startSilent) {

        start = System.currentTimeMillis();
        logText.append(action).append("...").append(newLine);
        if (!startSilent) {
            System.out.print(logText.toString());
        }
    }

    public void printIntermediaryText(String it) {
        intermediateText.append(it).append(newLine);
        System.out.println(intermediateText.toString());
        intermediateText = new StringBuilder();
    }

    public long getElapsedTime() {
        long currentTime = System.currentTimeMillis();
        return currentTime - start;
    }

    public void printElapsedTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - start;

        if (elapsedTime < 10000) {
            logText.append(Math.round(elapsedTime / 1000))
                    .append(" seconds, ")
                    .append(Math.round(elapsedTime % 1000))
                    .append(" milliseconds]")
                    .append(newLine + interval);
        } else if (elapsedTime < 60000) {
            logText.append(elapsedTime / 1000).append(" seconds").append(newLine + interval);
        } else {
            logText.append(elapsedTime / 60000).append(" minutes ").append(Math.round((elapsedTime % 60000) / 1000)).append(" seconds").append(newLine + interval);
        }

        if (elapsedTime
                < 1000) {
            System.out.println(action.toLowerCase() + ", elapsed time: " + elapsedTime + " milliseconds]");

        } else {
            System.out.println(action.toLowerCase() + ", elapsed time: " + elapsedTime / 1000 + " seconds]");
        }

    }

    public void closeAndPrintClock() {
        closeAndPrintClock("");

    }

    public void closeAndPrintClock(String closingMessage) {
        if (!silent) {

            long currentTime = System.currentTimeMillis();
            long totalTime = currentTime - start;
            logText = new StringBuilder();
            logText.append(intermediateText.toString());
            logText.append(closingMessage + newLine);
            logText.append("finished " + action + ". Duration: ");

            if (totalTime < 10000) {
                logText.append(Math.round(totalTime / 1000))
                        .append(" seconds, ")
                        .append(Math.round(totalTime % 1000))
                        .append(" milliseconds]")
                        .append(newLine + interval);
            } else if (totalTime < 60000) {
                logText.append(totalTime / 1000).append(" seconds").append(newLine + interval);
            } else {
                logText.append(totalTime / 60000).append(" minutes ").append(Math.round((totalTime % 60000) / 1000)).append(" seconds").append(newLine + interval);
            }

            System.out.println(logText.toString());
        }
    }
}
